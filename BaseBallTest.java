package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//선생님이 풀어주신 방법 !

public class BaseBallTest {
	ArrayList<Integer> numList; // 난수가 저장될 List
	ArrayList<Integer> userList; // 사용자가 입력한 값이 저장될 List

	int strike, ball; // 스트라이크 개수와 볼의 개수가 저장될 변수

	Scanner scan = new Scanner(System.in);

	// 1~9 사이의 중복되지 않는 난수 3개를 만들어서 List에 저정하는 메서드 (Set 이용)
	public void createNum() {
		Set<Integer> numSet = new HashSet<Integer>();

		// 1~9 사이의 난수 만들기
		while (numSet.size() < 3) {
			numSet.add((int) (Math.random() * 9 + 1));
		}

		// 만들어진 난수를 List에 저장하기ㄴ
		numList = new ArrayList<Integer>(numSet);

		// List의 데이터 섞기
		Collections.shuffle(numList);
	}

	// 사용자로부터 3개의 정수를 입력받아 List에 저장하는 메서드
	// 입력한 정수는 중복되지 않게 한다.
	public void inputNum() {
		int n1, n2, n3;

		// 중복되는 값이 있으면 다시 입력받도록
		do {
			System.out.println("숫자입력 >> ");
			n1 = scan.nextInt();
			n2 = scan.nextInt();
			n3 = scan.nextInt();

			if (n1 == n2 || n1 == n3 || n2 == n3) {
				System.out.println("중복되는 숫자는 입력할 수 없습니다....");
				System.out.println("다시 입력하세요..");
			}

		} while (n1 == n2 || n1 == n3 || n2 == n3);

		// 입력한 데이터를 List에 저장한다.
		userList = new ArrayList<Integer>();
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
	}

	// 스트라이크와 볼을 판정하고 결과를 출력하는 메서드
	public void ballCount() {
		strike = 0;
		ball = 0;

		for (int i = 0; i < userList.size(); i++) {
			for (int j = 0; j < numList.size(); j++) {
				if (userList.get(i) == numList.get(j)) {// 값이 같은지 비교
					if (i == j) {
						strike++;
					} else {
						ball++;
					}

				}
			}
		}

		
	//구해진 볼카운트 출력하기
	System.out.println(userList.get(0) + " " + userList.get(1)+ " " + userList.get(2)+ "==>" + strike + "S " + ball + "B" );
		
	}
	
	public void startGame() {
		createNum(); // 난수 만드는 메서드 호출 (이건 한번만 만드는것!)
		System.out.println(" 확인용 >> " + numList);
		
		int cnt = 0; // 실행횟수가 저장될 변수
		
		do {
			cnt++;
			inputNum(); //사용자 입력 메서드 호출
			ballCount(); //볼카운트 구하는 메서드 호출
		} while(strike!=3);
		
		System.out.println();
		System.out.println("축하합니다...");
		System.out.println("당신은 " + cnt + "번째만에 맞췄습니다.");
		
		
	}

	public static void main(String[] args) {
		BaseBallTest t = new BaseBallTest();
		t.startGame();
	}

}
