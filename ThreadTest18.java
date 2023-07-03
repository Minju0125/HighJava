package kr.or.ddit.basic;

/*
 wait(), notify() 메서드를 이용한 두 쓰레드가 번갈아 한번씩 실행하는 예제*/

public class ThreadTest18 {
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();

		Thread thA = new ThreadA(workObj);
		Thread thB = new ThreadB(workObj);

		thA.start();
		thB.start();

	}
}

//공통으로 사용할 객체
class WorkObject {
	public synchronized void testMethodA() {// 객체에 lock을 걸고,
		System.out.println("testMethodA()메서드 실행중,,,");
		notify(); // 이 코드 실행하면 제어 어디로? 웨이팅 풀에 신호만 보내고 하던일 계속함, ->아래 예외처리문 wait

		try {
			wait();
		} catch (InterruptedException e) {
		}
		// 메소드 끝남과 동시에 lock 풀림

		System.out.println("testMethodA()메서드 끝 !");

	}

	public synchronized void testMethodB() {
		System.out.println("testMethodB()메서드 실행중,,,");
		notify(); // 이 코드 실행하면 제어 어디로? //wait 들어갔던애를 깨우고 그대로 진행하고 //notify는 임의로 깨움?

		try {
			wait(); // 웨이팅 풀에 들어가고
		} catch (InterruptedException e) {
		}

		System.out.println("testMethodB()메서드 끝 !");
	}
}

//WorkObject의 testMethodA()메서드만 호출하는 쓰레드
class ThreadA extends Thread {
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workObj.testMethodA();
		}
//		notify();

//		notify(), wait()는 동기화 영역에서만 실행할 수 있음
//		이 자리에는 동기화처리가 안되어있음
//		여기에 쓰면, ThreadA() 여기에 있는 객체를 깨워주라는 얘긴데, 여기는 깨워주려는 스레드가 없음

		synchronized (workObj) { // 싱크로 나이즈 블록 안에,
			workObj.notify(); // workObj의 쓰레드 객체를 깨워라.

		}
	}
}

class ThreadB extends Thread {
	private WorkObject workObj;

	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workObj.testMethodB();
		}
//		notify(); 
		synchronized (workObj) { // 싱크로 나이즈 블록 안에,
			workObj.notify(); // workObj의 쓰레드 객체를 깨워라.
		}
	}
}

//	notify(), wait()는 동기화 영역에서만 실행할 수 있음
//	하나라도 웨이팅 풀에 들어가면 프로그램이 종료되지 않음.

