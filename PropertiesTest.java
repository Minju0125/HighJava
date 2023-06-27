package kr.or.ddit.basic;

import java.util.Properties;

public class PropertiesTest {
	public static void main(String[] args) {
		/*
		 * Properties 객체 - Properties 는 Map 보다 축소된 기능의 객체라고 할 수 있다.
		 * 
		 * - Map은 key 값과 value 값에 모든 종류의 객체를 사용할 수 있지만 Properties는 key 값과 value 값에
		 * String 만 사용할 수 있다.
		 * 
		 * - Map은 put(), get() 메서드를 이용하여 데이터를 입출력하지만 Properties는 setProterty(),
		 * getProperty()메서드를 이용하여 입출력한다.
		 * 
		 * - Properties 객체는 데이터를 파일로 입출력할 수 있다. 
		 * => 어떤 프로그램이 처음 실행될 때 그 프로그램의 환경변수값/초기값을 세팅하고자 할때 사용.
		 * 	=> 처음 프로그램이 시작되면 이걸 이용해서 초기값을 설정할 떄 사용함.
		 * 	=> ex) JDBC 
		 */

		Properties prop = new Properties();

		prop.setProperty("name", "홍길동");
		prop.setProperty("age", "20");
		int temp = 30;
//		prop.setProperty("age2", temp + ""); // 숫자를 문자열로 바꿀때 +"" 연산자 쓰면 문자열이 됨
		prop.setProperty("age2", String.valueOf(temp));
		prop.setProperty("tel", "010-1234-5678");
		prop.setProperty("addr", "대전");

		// --------------------------------------------------
		String name = prop.getProperty("name");
		int age = Integer.parseInt(prop.getProperty("age")); // 문자열로 저장된 age값을 정수형으로 형변환
		int age2 = Integer.parseInt(prop.getProperty("age2"));
		String tel = prop.getProperty("tel");
		String addr = prop.getProperty("addr");

		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("나이2 : " + age2);
		System.out.println("전화 : " + tel);
		System.out.println("주소 : " + addr);
	}
}
