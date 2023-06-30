package kr.or.ddit.basic;

//yield()메서드 연습용 예제

public class ThreadTest10 {
	public static void main(String[] args) {
		YieldThread th1 = new YieldThread("1번 쓰레드");
		YieldThread th2 = new YieldThread("2번 쓰레드");

		//객체 생성하고 두개의 쓰레드가 start() 됐을 때///////////////////////////////////////////////////
		//이 때의 조건은 stop이 false, work가 true
		
		th1.start();
		th2.start();
		// 처음에 두 쓰레드의 조건은 같음

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {

		}
		System.out.println("----------------------------------------------------------------1111111111111111111");
		/////////////////////////////////////////////////////////////////////
		th1.setWork(false); //1번쓰레드의 work 값만 바뀜

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			
		}
		System.out.println("----------------------------------------------------------------2222222222222222222");

		/////////////////////////////////////////////////////////////////////
		th1.setWork(true); //1번쓰레드의 work 값을 다시 work로 -> 처음 실행했던것과 같이 두 쓰레드가 둘다 작업함
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			
		}
		System.out.println("----------------------------------------------------------------3333333333333333333");

		th1.setStop(true);
		th2.setStop(true); 
	}
}

// yield() 연습용 메서드
class YieldThread extends Thread {
	private boolean stop = false;
	private boolean work = true;

	public void setWork(boolean work) {
		this.work = work;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	// 생성자
	public YieldThread(String name) {
		super(name); // 부모객체의 생성자 호출_쓰레드의 이름 설정하기

	}

	@Override
	public void run() {
		while (!stop) {
			if (work) {
				System.out.println(getName() + " 작업 중...");
				// 실제 하는 작업은 이건데, 조건에 만족할 때만 수행함 _ 조건을 만족하지 않을 때는 공회전 하게 되는데 이를 방지하기 위해 양보
				// yeild();
			} else {
				System.out.println(getName() + "양보...");
				Thread.yield(); //work 가 true 일때만 작업하고 원래 여기는 작업이 없어서 공회전 하는데, 공회전 시키지 않고 공회전 할 동안 다른 쓰레드에게 양보하는게 낫겠다.
				//1번쓰레드에게 제어가 오면 양보만 함 -> 양보하더라도 스케줄러가 1번 쓰레드에게 일을 또 시킬수 있음 -> 1번 쓰레드에게 제어가 오면 또 양보함
			}
		}
	}
}