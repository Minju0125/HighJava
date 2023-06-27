package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

// 문제1) 5명의 별명을 입력 받아 ArrayList에 저장하고, 이들 중 별명의 길이가 제일 긴 별명을 출력 (단, 별명의 길이는 모두 다르게 입력)

public class ArrayListTest03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			System.out.print("[사람" + (i + 1) + "] 별명 입력>>");
			list.add(sc.nextLine());
		}
		System.out.println();

		// 제일 긴 별명이 저장될 변수를 선언하고 이 변수에는 list의 첫번째 데이터로 초기화된다.
		String maxNickName = list.get(0);

		for (int i = 0; i < list.size(); i++) {
			if (maxNickName.length() < list.get(i).length()) {
				maxNickName = list.get(i);
			}
		}
		System.out.println("<가장 긴 별명 > " + maxNickName);

	}

}
