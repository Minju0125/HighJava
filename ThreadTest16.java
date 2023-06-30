package kr.or.ddit.basic;

//은행의 입출금을 쓰레드로 처리하는 예제

public class ThreadTest16 {
	private int balance; // 잔액이 저장될 변수

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	//입금하는 메서드
	public void deposit(int money) {
		balance += money;
	}
	
	//출금하는 메서드 (출금 성공 : true, 출금 실패 : false 반환)
	public synchronized boolean withdraw(int money) { //메소드에 동기화 처리함
		if(balance >= money) {
			//잔액이 더 많으니까 출금 진행
			for(int i=1; i<=100_000_000;i++) { //시간을 지연하기 위한 목적 (시간을 늦춰줘야 제어가 다른쓰레드로 갈 확률 높아지기때문에_ 확인을 위한 용도임!!)
				int a = i;
			}
			balance -=money;
			System.out.println("메서드 안에서 balance = " + balance);
			return true;
		}else {
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		ThreadTest16 account = new ThreadTest16(); // 공통객체
		account.setBalance(10000); //잔액을 100000원으로 설정
		
		//--------------------------------------------------------------
		//익명구현체 쓰레드로 작성
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				boolean result = account.withdraw(6000); //6000원 출금
				System.out.println("쓰레드에서 result(출금여부) = " + result + ", balance(잔액) = " + account.getBalance());
			}
		};
		
		
		//---------------------------------------------------------------
		Thread th1 = new Thread(r);
		Thread th2 = new Thread(r);
		
		th1.start();
		th2.start();
		
	}

}
