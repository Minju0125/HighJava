package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//접속을 기다려서 클라이언트가 접속하면 소켓 연결
//접속한 소켓과 연결된 소켓을 만들고 클라이언트가 신호 보냄
//접속하면 환영의 메세지 문자열로 보냄
//클라이언트는 접속 성공하면 서버가 그 문자열을 보내고
//화면에 문자열을 출력함

public class TcpServer01 {
	public static void main(String[] args) throws IOException {
		// TCP소켓 통신을 위해서 ServerSocket 객체를 생성한다.

		ServerSocket server = new ServerSocket(7777);
		// 0~65530?? 까지 숫자쓸수 있음
		// 이중에 0~1024번까지는 시스템용 포트임! 그래서 사용할때는 1024번 뒤에 있는걸 사용하는게 좋음
		// 포트번호가 중복되면 나중에 사용하는 포트번호가 실행이 안됨

		System.out.println("클라이언트의 접속을 기다립니다.");

		// accept() 메서드 ==> 클라이언트에서 연결 요청이 올 때까지 계속기다린다.
//						==> 연결요청이 오면 새로운 Socket 객체를 생성해서, 클라이언트의 Socket과 
//							연결한다. 연결된 Socket 객체를 반환한다.(
		// accept 메소드의 반환값 : Socket
		Socket socket = server.accept(); // 소켓 객체 변수 생성하고, accept() 메소드

		// accept() 메서드 명령 이후는 클라이언트와 연결된 후에 처리할 내용을 기술하면 된다.
		System.out.println();
		System.out.println("클라이언트와 연결 되었습니다.");
		System.out.println();
		
		// Socket 객체를 이용하여 (나와 연결된) 상대방의 정보를 구할 수 있다.
		System.out.println("상대방의 정보 확인...");
		System.out.println("IP 주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("Port 번호 : " + socket.getPort());
		System.out.println();
		
		// 자신의 정보도 구할 수 있다.
		System.out.println("자기자신의 정보 확인..");
		System.out.println("IP 주소 : " + socket.getLocalAddress());
		System.out.println("Port 번호 : " + socket.getLocalPort());
		System.out.println();
		
//		-------------------------------------
		
		//클라이언트에게 메세지 보내기 ==> Socket의 OutputStream 객체를 이용하여 보낸다.
		OutputStream out = socket.getOutputStream(); //소켓의 OutputStream 객체 구하기
		//=> 얘네는 바이트 기반의 스트림을 사용함
		
		DataOutputStream dout = new DataOutputStream(out); 
			//자바의 primitive 타입과 같은 데이터를 출력해주는 스트림
			//writeUTF를 통해 문자열 보낼 수 있음
		
		//클라이언트에게 메세지 전송
		dout.writeUTF("환영합니다. 어서오세요. :)");
		System.out.println("메세지를 보냈습니다..."); //서버의 콘솔창에 출력하는 메세지
		
		//자원 반납
		//소켓과 스트림 닫기
		dout.close();
		socket.close();
		server.close(); //서버소켓 닫기
		
		
		
		
		
		
		
	}
}
