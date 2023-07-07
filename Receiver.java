package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.net.Socket;

//이 클래스는 소켓에서 메세지를 받아서 화면에 출력하는 역할을 담당하는 쓰레드 클래스이다.
public class Receiver extends Thread {
	private Socket socket;
	private DataInputStream din;

	public Receiver(Socket socket) {
		this.socket = socket;
		try {
			// 소켓의 수신용(입력용) 스트림 객체 생성
			din = new DataInputStream(this.socket.getInputStream());
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		while (din != null) {
			try {
				System.out.println(din.readUTF());

			} catch (Exception e) {
			}
		}

	}
}
