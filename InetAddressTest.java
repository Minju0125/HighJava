package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		// InetAddress 클래스 ==> IP 주소를 다루기 위한 클래스

		// www.naver.com 의 IP 주소 가져오기
		InetAddress ip = InetAddress.getByName("www.nate.com");
		System.out.println("HostName : " + ip.getHostName());
		System.out.println("HostAddress : " + ip.getHostAddress());
		System.out.println("toString : " + ip.toString());
		System.out.println();
		
		//자신의 컴퓨터의 IP 주소 가져오기
		InetAddress localIP = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 HostName : " + localIP.getHostAddress());
		System.out.println("내 컴퓨터의 HostHostAddress : " + localIP.getHostAddress());

		//IP 주소가 여러개인 호스트의 IP 정보 가죠오기
		InetAddress[] ipArr = InetAddress.getAllByName("www.naver.com");
		for(InetAddress nip : ipArr) {
			System.out.println(nip.toString());
		}
	}
}
