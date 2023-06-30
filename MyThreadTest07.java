package kr.or.ddit.mine;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오.
 * 
 * 컴퓨터의 가위바위보는 난수를 이용하여 구하고
 * 사용자의 입력은 showInputDialog()메서드를 이용해서 입력받는다.
 * 
 * 입력시간은 5초로 제한하고 카운트다운을 진행한다.
 * 5초 안에 입력이 없으면 게임 진 것으로 처리하고 프로그램을 멈춘다.
 * 
 * 5초 안에 입력이 완료되면 승패를 구해서 출력한다.
 * 결과 예시)
 * 1. 5초 안에 입력을 못했을 경우 ->
  	-- 결 과 --
 	시간초과로 당신이 졌습니다..
 	
   2. 5초 안에 입력했을 경우
   -- 결 과 --
   	컴퓨터 : 가위
   	사용자 : 바위
   	결   과 : 당신이 이겼습니다.
 * */

public class MyThreadTest07 {
	public static void main(String[] args) {

		GameInput th1 = new GameInput();
		CountDown5sec th2 = new CountDown5sec();

		th1.start();
		th2.start();

	}

}

//데이터를 입력하는 쓰레드 ? 여기서 비교?
class GameInput extends Thread {
	public static boolean inputCheck = false;
	String str;

	@Override
	public void run() {

		do {
			str = JOptionPane.showInputDialog("가위 바위 보를 입력하세요.");
			}while(!(str.equals("가위")||str.equals("바위")||str.equals("보")));
		
		
		
		inputCheck = true;
		System.out.println("입력값 >> " + str);

		compare(str);
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public void compare(String str) {
		// 여기서 난수 발생하고, str 비교하는게 맞는지?
		int random = (int) (Math.random() * 3 + 1);

		String randomStr = null;
		switch (random) {
		case 1:
			randomStr = "가위";
			break;
		case 2:
			randomStr = "바위";

			break;
		case 3:
			randomStr = "보";

			break;
		}
		System.out.println();
		System.out.println("---- 결 과 ----");
		if (str.equals(randomStr)) {
			System.out.println("컴퓨터 : " + randomStr);
			System.out.println("사용자 : " + str);
			System.out.println("결 과 : 비겼습니다.");
		} else if (str.equals("가위")) {
			if (randomStr.equals("바위")) {
				System.out.println("컴퓨터 : " + randomStr);
				System.out.println("사용자 : " + str);
				System.out.println("결 과 : 당신이 이겼습니다.");
				System.exit(0);
			} else { // 컴퓨터가 보
				System.out.println("컴퓨터 : " + randomStr);
				System.out.println("사용자 : " + str);
				System.out.println("결 과 : 당신이 졌습니다.");
				System.exit(0);
			}
		} else if (str.equals("바위")) {
			if (randomStr.equals("가위")) {
				System.out.println("컴퓨터 : " + randomStr);
				System.out.println("사용자 : " + str);
				System.out.println("결 과 : 당신이 이겼습니다.");
				System.exit(0);
			} else { // 컴퓨터가 보
				System.out.println("컴퓨터 : " + randomStr);
				System.out.println("사용자 : " + str);
				System.out.println("결 과 : 당신이 졌습니다.");
				System.exit(0);
			}
		} else if (str.equals("보")) {
			if (randomStr.equals("바위")) {
				System.out.println("컴퓨터 : " + randomStr);
				System.out.println("사용자 : " + str);
				System.out.println("결 과 : 당신이 이겼습니다.");
				System.exit(0);
			} else { // 컴퓨터가 가위
				System.out.println("컴퓨터 : " + randomStr);
				System.out.println("사용자 : " + str);
				System.out.println("결 과 : 당신이 졌습니다.");
				System.exit(0);
			}

		}

	}
}

//카운트다운을 진행하는 쓰레드
class CountDown5sec extends Thread {

	@Override
	public void run() {
		for (int i = 5; i > 0; i--) {
			if (GameInput.inputCheck == true) {
				return;
			}
			System.out.println(i);

			try {

				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
		}
		System.out.println();
		System.out.println("---- 결 과 ----");
		System.out.println("시간초과로 당신이 졌습니다.");
		System.exit(0);
	}
}