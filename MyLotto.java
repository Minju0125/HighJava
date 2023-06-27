package kr.or.ddit.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MyLotto {
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		MyLotto l = new MyLotto();
		l.start();
	}

	// 프로그램 실행 메소드
	public void start() {
		System.out.println("==========================");
		System.out.println("        Lotto 프로그램");
		System.out.println("--------------------------");
		System.out.println(" 1. Lotto 구입");
		System.out.println(" 2. 프로그램 종료");
		System.out.println("==========================");
		System.out.print("메뉴선택 : ");
		while (true) {
			switch (Integer.parseInt(sc.nextLine())) {
			case 1:
				setMoney();
			case 2:
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	// 로또를 구매할 금액을 입력하는 메소드
	public void setMoney() {
		// 구매할 금액을 입력하고,

		System.out.print("■■■금액입력 >>> ");
		int money = Integer.parseInt(sc.nextLine());
		while (true) {
			if (money < 1000) {
				System.out.println("※입력 금액이 너무 적습니다. 로또번호 구입 실패!!");
				return;
			} else if (money >= 101000) {
				System.out.println("※입력 금액이 너무 많습니다. 로또번호 구입 실패!!");
				return;
			} else {
				System.out.println("■■■구입시작■■■");
				System.out.println("♣ 행운의 로또번호는 아래와 같습니다.");
				buyLotto(money);
				return;
			}

		}
	}

	// 로또를 구매하는 메소드
	public void buyLotto(int money) {

		int lottoTickets = money / 1000;
		int change = money % 1000;

		for (int i = 1; i <= lottoTickets; i++) {
			lottoNum();
		}
		System.out.println("■ 받은 금액은 " + money + "원이고, 거스름돈은 " + change + "원입니다.");

	}

	// 로또 번호를 발생시키는 메소드
	public void lottoNum() {
		// 난수발생시켜서 set에 저장한 후에, 배열에 저장하기
		Set<Integer> numSet = new HashSet<>();

		while (numSet.size() < 6) {
			numSet.add((int) (Math.random() * (45 - 1 + 1) + 1));
		}

		ArrayList<Integer> numList = new ArrayList<>(numSet);
		Collections.shuffle(numList);

		Collections.sort(numList);
		System.out.println(numList);// 정렬까지 해야함!

		return;

	}
}
