package kr.or.ddit.basic;

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

public class ThreadTest07 {
	// 입력여부를 나타내는 변수 선언
	public static boolean inputCheck;

	public static void main(String[] args) {
		GameTimer gt = new GameTimer();

		// 난수를 이용해서 컴퓨터의 가위바위보 정하기
		String[] data = { "가위", "바위", "보" };
		int index = (int) (Math.random() * 3); // 0~2 사이의 난수 만들기
		String com = data[index];

		// 사용자로부터 가위 바위 보 입력 받기
		gt.start(); // 카운트다운 쓰레드 작동

		//"가위","바위","보" 아닐 때에는 다시 입력받도록 반복
	
		String man = null;
		
		do {
		man = JOptionPane.showInputDialog("가위 바위 보를 입력하세요.");
		}while(!man.equals("가위") && !man.equals("바위") && !man.equals("보"));
//		while(!(man.equals("가위")||man.equals("바위")||man.equals("보")));
		
			
		inputCheck = true;

		// 결과 판정하기
		String result = "";
		
		
		/*
		if (man.equals(com)) {
			result = "비겼습니다.";
		} else if (man.equals("가위") && com.equals("보") 
				|| man.equals("보") && com.equals("바위")
				|| man.equals("바위") && com.equals("가위")) {
			result = "당신이 이겼습니다.";
		} else {
			result = "당신이 졌습니다.";
		}
		
		*/
		
		//사용자와 컴퓨터(난수)의 문자열을 합친 swtich-case 문 사용한 경우
		switch(man + com) {
		case "가위가위" : 
		case "바위바위" :
		case "보보" :
			result = "비겼습니다."; break;
		case "가위보" :
		case "보바위" :
		case "바위가위" :
			result = "당신이 이겼습니다."; break;
		default :
			result = "당신이 졌습니다.";}
		

		//결과 출력
		System.out.println("--- 결 과 - --");
		System.out.println("컴퓨터 : " + com);
		System.out.println("사용자 : " + man);
		System.out.println("결 과 : " + result);
		
	}
}

class GameTimer extends Thread {
	@Override
	public void run() {
		System.out.println("카운트 다운 시작...");
		for (int i = 5; i > 0; i--) {
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
			if (ThreadTest07.inputCheck) {
				return;
			}
		}

		System.out.println("--- 결 과 - --");
		System.out.println("시간 초과로 당신이 졌습니다.");
	}
}