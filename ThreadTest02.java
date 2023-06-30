package kr.or.ddit.basic;

public class ThreadTest02 {
	public static void main(String[] args) {
		// 멀티 쓰레드

		/*
		 * Thread를 사용하는 방법
		 * 
		 * 방법1)
		 * Thread 클래스를 상속한 class 를 작성한 후
		 * 이 class의 인스턴스를 생성한 후 이 인스턴스의 start() 메서드를 호출해서 실행한다.
		 * 여기서 start() 메소드가 아닌 run() 을 호출하면 쓰레드를 실행하지 않음.
		 */
		MyThread01 th1 = new MyThread01();
		th1.start();
		
		/*
		 * 방법2)
		 * Runnable 인터페이스를 구현한 class를 작성한 후,
		 * 이 class의 인스턴스를 생성하고 이 인스턴스를,
		 * Thread 클래스의 인스턴스를 생성할 때 인수값으로 넘겨준다.
		 * 그리고 이 때 생성된 Thread 클래스의 인스턴스의 start() 메서드를 호출해서 실행한다.
		 */
		MyThread02 r2 = new MyThread02();
		Thread th2 = new Thread(r2);
		th2.start();

		/*
		 * 방법2-1) - Runnable 인터페이스를 익명구현체로 작성하여 실행하기 인터페이스 안에는 처리할 내용이 없어서, 아예 자바에서 못만들게
		 * 해놓았음 
		 * 
		 */

		Runnable r3 = new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 200; i++) {
					System.out.print("@");
					try {
						// Tread.sleep(시간); ==> 주어진 시간동안 작업을 잠시 멈춘다.
						// 시간은 밀리세컨드 단위를 사용한다. (즉, 1000은 1초를 의미한다.)
						Thread.sleep(100);
					} catch (InterruptedException e) {

					}
				}
			}
		};
		Thread th3 = new Thread(r3);
		th3.start();
		
		System.out.println("main 메서드 끝..."); //여기까지 실행되었다는 뜻, 메인메소드는 끝났는데 프로그램은 실행됨 (밑에 있는 쓰레드가 끝나지 않았기 떄문에)
		//멀티쓰레드를 사용하면 메인메소드가 끝났다고 프로그램이 끝나는 것이 아니라,
		//모든 쓰레드가 종료되어야 프로그램이 끝남 ! 
	}
}

//방법1의 class 작성 - Thread 클래스 상속받음
class MyThread01 extends Thread {

	@Override
	public void run() {
		// 이 run() 메서드 안에 쓰레드가 처리할 내용을 작성한다.
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");

			try {
				// Tread.sleep(시간); ==> 주어진 시간동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다. (즉, 1000은 1초를 의미한다.)
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}

		}
	}
}

class MyThread02 implements Runnable {
	@Override
	public void run() {
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
			try {
				// Tread.sleep(시간); ==> 주어진 시간동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다. (즉, 1000은 1초를 의미한다.)
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
		}
	}
}

