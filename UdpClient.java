package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// 수신 받은 데이터가 저장될 byte 형 배열 생성
		byte[] bMsg = new byte[512];

		try {

			// Socekt 객체 생성
			DatagramSocket socket = new DatagramSocket();

			// 접속할 곳의 주소 정보 생성
			InetAddress address = InetAddress.getByName("192.168.35.55");

			while (true) {
				// 전송할 메시지 입력
				System.out.println("보낼 메시지 입력 >> ");
				String msg = scan.nextLine();

				// 전송할 메시지를 byte형 배열로 변환하기
				byte[] sendMsg = msg.getBytes("utf-8");

				// 전송할 패킷 객체 생성
				DatagramPacket outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, 8888);

				// 전송하기
				socket.send(outpacket);

				if ("/end".equals(msg)) { // 작업 종료 시점 설정
					break;
				}

				// ----------------------------------------------------------------여기까지가 메시지 전송

				// 서버에서 응답으로 온 데이터를 받아서 출력하기

				// 수신용 패킷 객체 생성
				DatagramPacket inpacket = new DatagramPacket(bMsg, bMsg.length);

				// 수신하기
				socket.receive(inpacket);

				System.out.println("서버의 응답 메시지 : " + new String(inpacket.getData(), 0, inpacket.getLength(), "utf-8"));
				System.out.println();

			} // while문 끝..........
			System.out.println("통신 끝..");
			socket.close();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
