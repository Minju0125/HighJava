package kr.or.ddit.basic;

// 문제1) 5명의 별명을 입력 받아 ArrayList에 저장하고, 이들 중 별명의 길이가 제일 긴 별명을 출력 (단, 별명의 길이가 같을 수 있다.)

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest04 {
	public static void main(String[] args) {
		// 5명의 별명을 입력받아 길이가 가장 긴 별명을 출력. (별명의 길이가 같을 수도 있음)

		Scanner sc = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			System.out.print("[사람" + (i + 1) + "] 별명입력>>");
			list.add(sc.nextLine());
		}

		System.out.println();

		// 제일 긴 별명의 길이가 저장될 변수를 선언하고 첫번째 별명의 길이로 초기화한다.
		
		int longNicknameLength = list.get(0).length();

		for (int i=0; i<list.size(); i++) {
			if (list.get(i).length() > longNicknameLength);
			longNicknameLength = list.get(i).length();
		}
		
		System.out.println("[가장 긴 별명들] >> ");
		for (int i = 0; i < 5; i++) {
			if (longNicknameLength == list.get(i).length()) {
				System.out.println(list.get(i));
			}
		}
	}
}
