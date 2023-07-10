package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {

	public static void main(String[] args) {
		new TcpMultiChatClient().clientStart();

	}

	// 시작 메소드
	public void clientStart() {
		Socket socket = null;
		try {
			socket = new Socket("192.168.35.55", 7777);
			System.out.println("서버에 연결되었습니다...");
			System.out.println();
			// ---------------------------------------------------------
			//전송용 쓰레드 객체 생성
			ClientSender sender = new ClientSender(socket);
			//수신용 쓰레드 객체 생성
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
	}// 시작 메서드 끝..

	// ------------------------------------------------------------------
	// 메세지 전송용 쓰레드(쓰레드 이너 클래스)
	class ClientSender extends Thread {
		private Socket socket;
		private DataInputStream din;
		private DataOutputStream dout;

		private String name;
		private Scanner scan;

		// 전송용 쓰레드의 생성자
		public ClientSender(Socket socket) {
			this.socket = socket;
			scan = new Scanner(System.in);

			try {
				din = new DataInputStream(this.socket.getInputStream()); // 서버가 중복된것을 체크해서 보내주는데 그걸 받아와야하기 때문에- 수신용
				dout = new DataOutputStream(this.socket.getOutputStream()); // 송신용

				if (dout != null) {
					while (true) {
						// 처음에 클라이언트 접속하면 대화명 이름을 보낼거임
						// 클라이언트용 프로그램은 처음 실행하면 서버에 접속하고 접속에 성공하면 첫번째로 '대화명'을 입력받아 전송하고, '대화명'의 중복 여부를
						// 응답으로 받아서 확인한다.
						System.out.println("대화명 >> ");
						String name = scan.nextLine(); // 대화명 입력받기

						dout.writeUTF(name); // 대화명 전송
						// 전송한걸 서버에서 중복 여부 검사함

						// 대화명 중복 여부를 응답으로 받는다.
						String feedBack = din.readUTF(); // feedBack 변수에는 '대화명중복' 또는 'OK'가 담겨있음

						if ("대화명 중복".equals(feedBack)) {// 대화명이 중복될때
							System.out.println(name + "은 중복되는 대화명입니다.");
							System.out.println("다른 대화명을 입력하세요.");
							System.out.println();
						} else { // 대화명이 중복되지 않을 때
							this.name = name;
							System.out.println(name + "대화명으로 대화방에 접속했습니다.");
							System.out.println();
							break; // 반복문 탈출
						}
					} // while문 끝..

				} // if문 끝..

			} catch (Exception e) {
				// TODO: handle exception
			}
		}// 생성자 끝..

		@Override
		public void run() {
			try {
				while (dout != null) {
					// 키보드로 입력한 메시지를 서버로 전송한다.
					dout.writeUTF("[" + this.name + "]" + scan.nextLine());

				}
			} catch (Exception e) {
			}
		}
	}

	// -------------------------------------------------------------------
	// 메세지 수신용 쓰레드
	class ClientReceiver extends Thread {
		private Socket socket;
		private DataInputStream din;

		// 생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;

			try {
				din = new DataInputStream(this.socket.getInputStream());
			} catch (Exception e) {
			}
		} // 생성자 끝..

		@Override
		public void run() {
			try {
				while (din != null) {
					// 서버에서 받은 메시지를 화면에 출력한다.
					System.out.println(din.readUTF());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}
