package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/*
 메인에서 서버 소켓을 열어두고 accept() 메서드를 이용하여 클라이언트에 해당되는 user 소켓을 끊임없이 
 계속 수용하는 방식
 */
public class TcpMultiChatServer {
	// 접속한 클라이언트 정보를 저장할 Map 객체 변수 선언
	// key 값 ==> 접속한 사람 이름
	// value 값 ==> 접속한 클라이언트와 연결된 socket 객체
	// =>얘를 어딘가에 잘 보관
	private Map<String, Socket> clientMap;

	public TcpMultiChatServer() {
		// clientMap이 동기화 처리가 되도록 생성한다.
		clientMap = Collections.synchronizedMap(new HashMap<String, Socket>());

	}

	public static void main(String[] args) {
		new TcpMultiChatServer().serverStart();
	}

	// 시작 메서드
	public void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다...");

			while (true) {
				socket = server.accept();
				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] 에서 접속했습니다..."); // 어디서
																												// 접속했는지/
																												// 상대방
																												// 정보구하기
				System.out.println();

				// ------------------------------------------
				// 여기부터는 쓰레드가 작동되어야함
				ServerReceiver serverThread = new ServerReceiver(socket);
				serverThread.start();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (server != null)
				try {
					server.close();
				} catch (Exception e) {
				}
		}
	}
	// 시작 메서드 끝,,

	// clientMap에 저장된 전체 클라이언트들에게 메세지를 전송하는 메서드
	private void sendToAll(String msg) {
		// clientMap 의 데이터 개수만큼 반복
		for (String name : clientMap.keySet()) {
			try {
				DataOutputStream dout = new DataOutputStream(clientMap.get(name).getOutputStream());
				// 맵에 있는 클라이언트의 소켓을 구한것
				dout.writeUTF(msg);// 메세지보내기
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
// sendToAll 메서드 끝,,
//-----------------------------------------------------------------------------

// Inner Class (내부클래스) 로 서버에서 클라이언트로 메세지를 전송하는 Thread를 작서한다.
// ==> Outer class 의 멤버변수를 자유롭게 사용하기 위해서,,,
	class ServerReceiver extends Thread {
		private Socket socket;
		private DataInputStream din;
		private DataOutputStream dout;

		// 생성자
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				/// 송신용 스트림 객체 생성
				dout = new DataOutputStream(this.socket.getOutputStream());

				// 수신용 스트림 객체 생성
				din = new DataInputStream(this.socket.getInputStream());
			} catch (Exception e) {

			}
		} // 생성자 끝...

		@Override
		public void run() {
			String name = "";
			try {
				// 클라이언트가 연결이 완료되면 첫번째로 '대화명(이름)'을 입력받아 보낸다.
				// 서버는 이 '대화명'을 받아서 현재 Map 에 저장된 목록에서 중복되는지 여부를
				// 응답으로 클라이언트에게 보낸다.

				// 클라이언트가 보내온 '대화명'이 중복되지 않을 때까지 반복한다.
				while (true) {
					name = din.readUTF(); // 클라이언트가 보낸 '대화명' 받기

					if (clientMap.containsKey(name)) { // '대화명'이 중복되는지 검사 -> 대화명이 중복되면
						dout.writeUTF("대화명 중복"); // 클라이언트에게 '대화명 중복' 메세지 전송
					} else {// 중복되지 않으면
						dout.writeUTF("OK"); // 'OK' 메세지 전송
						break; // 반복문 탈출
					}
				} // while문 끝...

				// 접속한 사람의 대화명을 이용하여 다른 전체 클라이언트들에게 대화방 참여 메세지를 전송한다.
				sendToAll("[" + name + "]님이 대화방에 입장했습니다.");

				// 접속한 사람의 대화명과 Socket 정보를 Map에 추가한다.
				clientMap.put(name, this.socket);

				System.out.println("현재 접속자 수 :  " + clientMap.size() + "명");

				// 한 클라이언트가 보낸 메세지를 전체 클라이언트들에게 전송
				while (din != null) {
					sendToAll(din.readUTF());

				}
			} catch (Exception e) {

			} finally {
				// 사용자 목록에서 해당 대화명을 삭제한다.
				clientMap.remove(name);
				// 이 finally 영역이 실행된다는 것은 , 현재 이 쓰레드가 담당하는 클라이언트의 접속이 종료됐다!는 의미
				sendToAll("[" + name + "]님이 접속을 종료했습니다...");

				System.out.println(
						"[" + this.socket.getInetAddress() + ":" + this.socket.getPort() + "] 에서 접속을 종료했습니다...");
				System.out.println();
				System.out.println("현재 접속자 수 : " + clientMap.size() + "명");
				System.out.println();

			}

		}

	} //쓰레드 끝..
}
