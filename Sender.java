package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

//이 클래스는 소켓을 통해서 메세지를 보내는 역할을 담당하는 쓰레드이다.

public class Sender extends Thread {

	// 얘가 상대방에게 메세지를 보내려고 하는데 필요한것은 -> 작업할 도구 필요
	// 소켓을 통해 통신 => 소켓 정보를 알아야함

	private Socket socket; // 소켓의 참조값을 저장할 변수
	private DataOutputStream dout;
	private String name;
	private Scanner scan;

	// 이 쓰레드는 서버의 역할을 하는 클래스에서 서버에서 접속완료되면 소켓이 만들어지는데, 이 소켓을 받아와야함

	// 객체가 생성될 때 소켓 받아서 이름 입력 받아놓고, 소켓을 이용한 출력용 객체 생성

	// 생성자 -> 소켓 초기화
	public Sender(Socket socket) {
		this.socket = socket;
		scan = new Scanner(System.in);

		// 보낼 때 사용할 객체 생성
		try {
			System.out.println("이름입력 >> ");
			name = scan.nextLine();
			
			//소켓을 이용한 전송용 (출력용) 스트림 객체 생성
			dout = new DataOutputStream(this.socket.getOutputStream());
		} catch (Exception e) {

		}
	}

	@Override
	public void run() {
		while (dout != null) {
			try {
				dout.writeUTF(name + " : " + scan.nextLine());
			} catch (Exception e) {
			}
		}

	}
}
