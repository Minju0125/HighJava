package kr.or.ddit.basic;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest01 {
	public static void main(String[] args) throws MalformedURLException {
		//URL클래스 ==> 인터넷에 존재하는 서버들의 자원에 접근할 수 있는 주소를 다루는 클래스
		
		//URl 주소의 구조 ==> '프로토콜 ://호스트명:포트번호/경로명/파일명?쿼리리스트#참조'
		//		예) https://www.ddir.or.kr:80/index.html?ttt=123

		
		URL url = new URL("https", "ddir.or.kr", 80, "index.html?ttt=123");
		
		System.out.println("Protocol : " + url.getProtocol());
		System.out.println("Host : " + url.getHost());
		System.out.println("Port : " + url.getPort());
		System.out.println("File : " + url.getFile());
		System.out.println("Path : " + url.getQuery());
		System.out.println();
		System.out.println(url.toExternalForm());
		
		
		
		
	
	}
}
