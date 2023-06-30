package kr.or.ddit.basic;

//쓰레드의 상태를 출력하는 예제

public class ThreadTest09 {
	public static void main(String[] args) {
		StatePrintThread th = new StatePrintThread(new TargetThread());
		
		th.start();
	}
}

//쓰레드 상태의 검사대상이 되는 쓰레드---????
class TargetThread extends Thread{
	@Override
	public void run() {
		long temp = 0;
		//처리중인 것(runnable상태)을 표현하기 위해 반복문 사용
		for(long i = 1L; i<=20_000_000_000L; i++) { //시간 지연용
			temp = i % 8L; 
		} // 이부분 실행중일 때 상태값은 실행 중 또는 실행 대기 상태 -> runnable 상태
		
		try {
			Thread.sleep(1500); //1.5 초 쉬는 동안 아래서는 0.5초마다 검사하므로 3번 검사함 -> TIMED_WAITING 상태
		} catch(InterruptedException e) {
			
		}
		
		for(long i=1L; i<20_000_000_000L; i++) { //시간 지연용
			temp = i % 8L; //RUNNABLE 상태
		}
	}
	//쓰레드의 run 메소드 끝나면 TERMINATED 가 되고 TERMINATED 되면 밑에 쓰레드에서  마지막 if 문제 걸려서 -> break 걸ㄹ
}


//검사 대상의 쓰레드의 상태를 출력하는 쓰레드 - 위의 쓰레드의 정보를 알아야함.
class StatePrintThread extends Thread{
	private TargetThread target;
	
	public StatePrintThread (TargetThread target) {
		super();
		this.target=target;
	}
	
	@Override
	public void run() {
		while(true) {
			// 쓰레드의 현재 상태값 구하기
			// getState()==> 쓰레드의 현재 상태값을 Thread.State의 열거형으로 반환한다.
			Thread.State state = target.getState(); //열거형을 저장할 수 있는 변수를 만들고, 타겟의 쓰레드를 알려면 getState메소드 (열거형을 반환)
													//생성은 됐지만, 작동이 안됐기 때문에 상태값은 NEW 
			
			System.out.println("TargetThread의 현재 상태값 : " + state);
			
			//TargetThread 의 상태가 NEW 상태이면...
			if(state ==Thread.State.NEW) {
				target.start(); //타겟쓰레드도 작동됨
				
			}
			// new 가 아니면 그냥 넘어옴
			
			//TargetThread 의 상태가 TERMINATED 상태이면... (TargetThread가 끝난 상태)
			if(state == Thread.State.TERMINATED) {
				// 종결상태이면 더 이상 검사할 필요가 없음
				break; // 반복문 빠져나감. (run 메소드 빠져나가게 됨)
				
			}
			
			//new 도 아니고 TERMINATED 도 아니면 다시 위로 올라가서 반복하는데, 너무 빨리 실행되니까
			//아래서 sleep(500)을 통해 0.5초마다 검사
			try {
				Thread.sleep(500); //0.5초간 일시정지
			}catch(InterruptedException e) {
				
			}
			
		}
		
		/*
		 * RUNNABLE 상태에서 TIME_WAITING 상태가 되는건 언제?
		 * 
		 * 
		 * */
	
	}
}