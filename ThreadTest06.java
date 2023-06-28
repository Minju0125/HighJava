package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {
	public static void main(String[] args) {
		DataInput th1 = new DataInput();
		CountDown th2 = new CountDown();

		th1.start();
		th2.start();
		// 이렇게하면 카운트 다운 중간에 입력을 해도 카운트다운이 멈추지 못함.

	}
}

// 입력이 완료됐다는 신호를 보내면 밑에 카운트 다운 쓰레드에서 인식해야함 -. (public static boolean inputCheck)

// 데이터를 입력하는 쓰레드
class DataInput extends Thread {
	// 입력 여부를 확인하기 위한 변수 선언 -> 쓰레드에서 공통으로 사용되는 변수
	public static boolean inputCheck = false; // 데이터를 공유할 때 static 사용하는 것이 좋음

	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세용");
		inputCheck = true; // 입력이 완료된 시점에서, 입력이 완료되면 inputCheck을 true 로 바꿔줌 (아래에서 true 로 바뀐 이 값을 인식해야함!)
		System.out.println("입력 값 >> " + str);
	}
}

// 카운트 다운을 진행하는 쓰레드
class CountDown extends Thread {

	@Override
	public void run() {
		for (int i = 10; i > 0; i--) {
			// 입력 여부를 확인하여 입력이 완료되면 쓰레드를 종료시킨다.
			if (DataInput.inputCheck == true) {
				return;
				// 쓰레드 하나를 끝내려면 메소드를 끝내면 됨 !-> return;
				// run()메서드가 종료되면 해당 쓰레드가 종료되는 것과 같다.
			}

			System.out.println(i);
			try {
				Thread.sleep(1000); // 1초동안 멈춘다.
			} catch (InterruptedException e) {
			}
		}
		System.out.println("10초가 지났습니다... 프로그램을 종료합니다...");
		System.exit(0);

	}
}