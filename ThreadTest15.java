package kr.or.ddit.basic;

public class ThreadTest15 {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject(); // 공통객체생성

		// 클래스 한개를 통해 서로 다른 객체 2개 생성(하는 일(내용)은 똑같음)
		TestThread th1 = new TestThread("Test1", sObj);
		TestThread th2 = new TestThread("Test2", sObj);

		th1.start(); // 쓰레다 하나가 하는 일은 ShareObject의 add()메소드 10번 호출
		th2.start();
	}
}

class TestThread extends Thread {
	private ShareObject sObj; // 공통으로 사용할 클래스의 참조값이 저장될 변수 선언

	// 생성자 만들기
	public TestThread(String name, ShareObject sObj) {
		super(name); // 부모의 생성자 호출 -> 쓰레드 이름(name) 설정
		this.sObj = sObj;

	}

	// 쓰레드 하나가 add() 메서드 10번 호출
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			sObj.add();
		}
	}
}

//공통 클래스
class ShareObject {
	private int sum = 0;

//	public synchronized void add() { // [방법 1] 메소드에 동기화 설정하는 방법

	public void add() {
		synchronized (this) { // [방법2] 동기화 블록으로 설정

			int n = sum;
			n += 10;

			sum = n;

			System.out.println(Thread.currentThread().getName() + "합계 : " + sum);

		}
	}
}

/*
 * 1번, 2번 쓰레드 모두 add 메소드 열번씩 호출 add() 메소드 실행 중에, 1번, 2번쓰레드가 교차되는 시점이 존재함
 */
