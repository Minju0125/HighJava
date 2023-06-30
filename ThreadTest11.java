package kr.or.ddit.basic;

/*
 Thread 의 stop() 메서드를 호출하면 쓰레드가 바로 멈춘다.
 
( 교착상태에 빠진다는 것은 자원 정리를 잘못한것. )
 이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어 나중에 실행되는 
 프로그램에 영향을 줄 수 있다. 그래서 stop() 메서드는 비추천으로 되어 있다.
 
 stop() 안쓰고 멈추게 하기~~!!
 
*/

public class ThreadTest11 {
	public static void main(String[] args) {
		/*
		 * ThreadStopTest01 th1 = new ThreadStopTest01(); th1.start();
		 * 
		 * try { Thread.sleep(1000); } catch (InterruptedException e) { } // th1.stop();
		 * th1.setStop(true);
		 */
//-------------------------------------------------------------------------------------------
		// interrupt() 를 이용한 쓰레드 멈추기
		ThreadStopTest02 th2 = new ThreadStopTest02();
		th2.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		th2.interrupt();
		;

	}
}

// 쓰레드를 멈추게 하는 연습용 쓰레드
class ThreadStopTest01 extends Thread {
	private boolean stop;

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public void run() {
		while (!stop) {
			System.out.println("쓰레드 실행 중...");
		}
		System.out.println("자원 정리..."); // 쓰레드 실행 시 사용하던 자원들을 , 쓰레드 멈췄을 때 시스템에게 자원을 쓰지 않겠다고 알려주는 것
		System.out.println("쓰레드 종료...");
	}
}

// interrupt()메서드를 이용하여 쓰레드를 멈추기 하기
class ThreadStopTest02 extends Thread {
	@Override
	public void run() {
		// 방법 1 ==> interrupt()메서드와 sleep()메서드를 이용하는 방법

		/*
		 * try { while (true) { System.out.println(".... 실행중 ...."); Thread.sleep(1); }
		 * } catch (InterruptedException e) {
		 * 
		 * }
		 */

		while(true) {
			System.out.println("Thread 실행 중.......");
			// 방법2 ==> interrupt() 메서드가 호출되었는지 여부를 검사해서  처리한다.
			
			// 검사 방법1 ==> 쓰레드의 인스턴스 메서드인 isInterrupted()메서드 이용하기
			//isInterrupted() ==> interrupt() 메서드가 호출되면 ture 를 반환한다.
			
			/*if(this.isInterrupted()) {
				break;
			}
		}*/
			
			// 검사방법 2 ==> 쓰레드의 정적 메서드인 interrupted() 메서드 이용하기
			// interrupted() ==> interrupt()메서드가 호출되면 true 를 반환한다.
			
			if(Thread.interrupted()) {
				break;
			}
		}
		System.out.println("자원정리..........");
		System.out.println("쓰레드 종료..........");
	}
		
		
	
}
