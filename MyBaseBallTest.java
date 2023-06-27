package kr.or.ddit.solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

//선생님 풀이 후 보완할 사항 : 중복되는 숫자 입력 못하게 하기! 볼, 스트라이크 카운트가 따로 됨

/*
문제) Set 을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
컴퓨터의 숫자는 난수를 이용하여 구한다.
(스트라키크는 S, 볼은 B로 나타낸다.)
 
예시) 컴퓨터의 난수 ==> 9 5 7 (Set 이용)-> list나 배열에 담고 , 사용자에게 숫자 입력받아 list나 배열에 담고 두개를 비교해서 .
	
실행예시) 

숫자입력 >> 3 4 5
3 4 5 ==> 0S 1B

숫자입력 >> 7 8 9
7 8 9 ==> 0S 2B

숫자입력>> 9 7 5 
9 7 5 ==> 1S 2B

숫자입력 >> 9 5 7
9 5 7 ==> 3S 0B

축하합니다... 
당신은 4번째만에 맞췄습니다...!
<종료>

 *
 */

public class MyBaseBallTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		HashSet<Integer> cRandom = new HashSet<>(); // 난수를 발생하여 중복 피해서 Set에 저장
		while (cRandom.size() < 3) {
			cRandom.add((int) (Math.random() * (9) + 1));
		}

//		System.out.println(cRandom);
		System.out.print("컴퓨터의 난수 >> ");
		List<Integer> com = new ArrayList<>(cRandom); // Set 에 저장한 정수를 List에 저장
		System.out.println(com);

		ArrayList<Integer> user = new ArrayList<>();

		System.out.println("(사용자) 숫자입력"); // 사용자에게 입력받은 정수를 List에 저장

		int a = 0;
		int b = 0; // 여기가 이상함!!!!!!

		int i;
		for (i = 0; a < 3; i++) { // i번째 시도

			System.out.println((i + 1) + "번째 시도입니다.");

			for (int j = 0; j < 3; j++) {
				System.out.print("숫자 입력 >> ");
				user.add(Integer.parseInt(sc.nextLine()));
			}
			System.out.println(user);

			// 스트라이크
			for (int k = 0; k < 3; k++) {
				for (int l = 0; l < 3; l++) {
					if ((com.get(k) == user.get(l)) && (k == l)) {
						a++; // 인덱스가 같으면 a++
					} else if ((com.get(k) == user.get(l) && (k != l))) {
						b++;
					}
				}

			}

			if (a > 3) {
				a = 3;

			}
			if (b > 3) {
				b = 3;
			}

			System.out.println(a + "S " + b + "B");

			user.clear();

		}

		System.out.println("축하합니다. 당신은 " + i + "번째에 맞췄습니다!");

	}
}

/*
 * 값이 같고 인덱스 같으면 strike 값이 같고 인덱스 다르면 ball
 */
