package kr.or.ddit.basic.tcp;

import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {
	public static void main(String[] args) {
		// 서버소켓을 만들고 클라이언트가 접속해 오면 소켓을 만들어서
		// 메세지를 받은 쓰레드와 메세지를 보내는 쓰레드에  이 소켓을 넣어준다.
		try {
			ServerSocket server = new ServerSocket(7777);
			System.out.println("서버가 준비중입니다...");
			System.out.println();

			Socket socket = server.accept();
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}