package kr.or.ddit.basic;

public class ThreadTest03 {

	public static void main(String[] args) {
		// 쓰레드의 실행시간을 체크해보자..!
		Thread th = new Thread(new MyRunner());

		// (_1) 이 시점에서 현재시간을 저장해놓고
		long startTime = System.currentTimeMillis();
		
		// 1970년 1월 1일 0시 0분 0초(표준시간)부터 현재 시간까지 경과한 시간을
		// 밀리세컨드(1/1000) 단위로 반환한다.
		th.start(); //이 메소드가 처리한 시간 (cpu 입장에서는 1/1000도 안되는 짧은 시간)
		
		try {
			th.join(); // 현재의 위치에서 대상이 되는 쓰레드 (지금은 변수 th)가 종료될때까지 기다린다./ th가 끝나야 다음으로 넘어간다.(쓰레드가 다 끝나야 넘어감)
		} catch (InterruptedException e) {
			
		}
		

		// (_2) 이 시점에서 현재시간을 알아내서 위의 시간과의 차이를 구함 (쓰레드 진행 후)
		long endTime = System.currentTimeMillis();

		System.out.println("경과시간 : " + (endTime - startTime));
	}

}

class MyRunner implements Runnable {
	@Override
	public void run() {
		long sum = 0L;
		for (long i = 1L; i <= 1_000_000_000L; i++) { // 큰 숫자 표현시, 언더바('_')사용하면 자릿수를 나타낼 수 있음
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}


