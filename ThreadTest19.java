package kr.or.ddit.basic;

import javax.sql.rowset.spi.SyncResolver;

/*하나는 공급만 하는 쓰레드, 하나는 소비만 하는 쓰레드를 구현*/

public class ThreadTest19 {
	public static void main(String[] args) {
		DataBox databox = new DataBox();

		ProducerThread th1 = new ProducerThread(databox);
		ConsumerThread th2 = new ConsumerThread(databox);

		th1.start();
		th2.start();

	}
}

// 공통으로 사용할 클래스

//데이터를 보관하고 있다가, 데이터가 (누군가) 사용해서 
class DataBox {
	private String data; // data라는 문자열을 저장하는 변수

	// 데이터를 가져가는 메서드
	// ==> data변수의 값이 null이면 공급 안된 것
	// -> data 변수에 문자열이 채워질 때까지 기다리고
	// data 변수에 값이 있으면 해당 문자열을 반환한다.
	// (문자열을 반환한 후에는 data변수의 값을 null로 만든다.)
	public synchronized String getData() { // getter// 들어오면서 lock 걺
		if (data == null) {
			try {
				wait(); // data가 null 이면 기다림
			} catch (InterruptedException e) {
			}
		}

		String temp = data; // 여기를 대피 시키고 (temp에 임시 저장)
		System.out.println("쓰레드가 읽은 데이터 : " + temp);

		data = null; // data 변수값을 null 로 만듦

		notify(); // 리턴 직전에 notify() 해서 상대방이 waiting pool 에 있다면 깨워줘라.

		return temp;

//		return data; //바로 리턴해버리면, 반환 후에 데이터를 null 로 만들 기회가 없기 떄문에 윗줄 코드처럼 함
		// 메소드 종료와 동시에 lock 풀림
	}

	// 데이터를 공급하는 메서드
	// ==> data 변수에 값이 있으면 data 변수의 값이 null이 될 때까지 기다린다.
	// data 변수의 값이 null이 되면 새로운 문자열을 data 변수에 저장한다.
	// 새로운 문자열 : 매개변수에 들어오는 문자열 data
	public synchronized void setData(String data) { // setter
		if (this.data != null) { // 지역변수와 멤버변수의 이름이 같을때 멤버변수임을 나타내기 위해 this.data
			try {
				wait(); // null 이 아니라면 waiting pool 에 들어감 그러면서 lock 이 풀림
			} catch (InterruptedException e) {
			}
		}

		this.data = data; // data를 받아 private 변수 data에 저장
		System.out.println("쓰레드에서 새로 저장한 데이터 : " + this.data);

		notify();

	}
}

// 데이터를 공급하는 쓰레드
class ProducerThread extends Thread {
	private DataBox databox;

	ProducerThread(DataBox databox) {
		super();
		this.databox = databox;
	}

	@Override
	public void run() { // 배열에 넣고 배열에 있는 글자를 하나씩 공급해줄것
		String[] dataArr = { "서울", "대전", "부산", "제주" };
		for (int i = 0; i < dataArr.length; i++) {
			databox.setData(dataArr[i]);
		}
	}
}

// 데이터를 꺼내서 사용하는 쓰레드
class ConsumerThread extends Thread {
	private DataBox databox;

	// 생성자
	public ConsumerThread(DataBox databox) {
		super();
		this.databox = databox;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 4; i++) {
			String returnData = databox.getData();

			// 가져온 데이터를 사용하는 코드들...
		}
	}
}


/*
 공급하는 쪽은 null 아닐 때 기다리고, 소비하는 쪽은 null 일때 기다림
저장하고 읽고, 반복되는 구조 확인할 수 있음

어디가 먼저 실행이 되든 데이터 공급 안됐으면 공급하고, 소비하는 패턴 반복됨


 */
