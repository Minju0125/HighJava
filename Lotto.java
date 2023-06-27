package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

//선생님이 풀어주신 풀이법

public class Lotto {
	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Lotto l = new Lotto();
		l.displayMenu();
		l.startLotto();
		l.buyLotto();

	}

	public void startLotto() {
		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1: // 로또 구매
				buyLotto();
				break;
			case 2: // 프로그램 종료
				System.out.println();
				System.out.println("감사합니다...");
				return;
			default:
				System.out.println("잘못입력했습니다...");
				System.out.println("다시 선택하세요.");
			}
		}
	}

	private int displayMenu() {
		System.out.println("==========================");
		System.out.println("     	Lotto 프로그램");
		System.out.println("--------------------------");
		System.out.println(" 1. Lotto 구입");
		System.out.println(" 2. 프로그램 종료");
		System.out.println("==========================");
		System.out.print("메뉴선택 >> ");
		return scan.nextInt();

	}

//로또 구매 작업을 진행하는 메서드
	private void buyLotto() {
		System.out.println();
		System.out.println("Lotto 구입 시작");
		System.out.println();
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.println("금액 입력 : ");
		int money = scan.nextInt();

		System.out.println();
		if (money < 1000) {
			System.out.println("입력 금액이 너무 적습니다. 로또 번호 구입 실패!!!");
			return;
		}
		if (money >= 101000) {
			System.out.println("입력금액이 너무 많습니다. 로또 번호 구입실패!!!");
			return;
		}

		// 로또번호 만들기
		HashSet<Integer> lottoSet = new HashSet<Integer>();

		int count = money / 1000; // 구매할 로또의 개수 (정수/정수는 소수점 뒤에 다 버리고 정수로)
		System.out.println();
		System.out.println("행운의 로또 번호는 아래와 같습니다...");
		for (int i = 1; i <= count; i++) {
			while (lottoSet.size() < 6) {
				lottoSet.add((int) (Math.random() * (45 - 1) + 1));
			}
			ArrayList<Integer> lottoList = new ArrayList<Integer>(lottoSet);
			Collections.sort(lottoList);

			System.out.println("로또번호 " + i + ": ");
			for (int j = 0; j < lottoList.size(); j++) {
				if (j > 0) {
					System.out.println(", ");
				}
				System.out.println(lottoList.get(j));
			}
			System.out.println(); // 줄바꿈
			lottoSet.clear();
		}
		System.out.println("받은 금액은 " + money + "원이고, 거스름돈은 " + (money % 1000) + "원 입니다.");

	}

}
