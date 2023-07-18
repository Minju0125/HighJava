package kr.or.ddit.basic.singleton;

/*
	singleton 패턴 ==> 객체가 1개만 만들어지게 하는 방법
		(외부에서 new 명령을 사용하지 못하게 한다.)
	- 사용 목적 ==> 1. 메모리 낭비 방지 2. 데이터의 공유가 쉽다.

	- singleton 패턴의 클래스를 만드는 방법 (필수 구성 요소)
	1. 자신 class의 참조값이 저장될 변수를 private static으로 선언한다.
	
	2. 생성자의 접근 제한자를 private 으로 한다.
	
	3. 자신 class의 인스턴스를 생성하고 생성된 인스턴스를 반환하는 메서드를 public static으로 작성한다.
		(이 메서드의 이름은 보통 getInstance로 한다.)
	
	
	
*/

public class MySingleton {

	// 1. 자신 class의 참조값이 저장될 변수를 private static으로 선언한다.
	private static MySingleton single;

	// 2. 생성자의 접근 제한자를 private 으로 한다.
	private MySingleton() {
		System.out.println("생성자 메서드입니다...");
	} // 기본생성자 - 이거 안만들면 컴파일러가 자동으로 만들어주는데 private으로 해야함

	// 3. 자신 class의 인스턴스를 생성하고 생성된 인스턴스를 반환하는 메서드를 public static으로 작성한다.
	// (이 메서드의 이름은 보통 getInstance로 한다.)
	public static MySingleton getInstance() {
		if (single == null)
			single = new MySingleton();
		return single;
	}

	// 기타 이 클래스가 처리할 내용들을 작성한다.
	public void displayTest() {
		System.out.println("싱글톤 클래스의 메소드 호출입니다.");
	}

}
