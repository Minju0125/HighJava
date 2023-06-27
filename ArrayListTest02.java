package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*문제 : 5명의 사람 이름을 입력받아 ArrayList에 저장한 후에 이들 중 '김'씨 성의 이름을 모두 출력하시오.
 * 	(단, 입력은 Scanner객체를 이용한다.)
 * */
public class ArrayListTest02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> list1 = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			System.out.print("[사람" + (i + 1) + "]");
			list1.add(sc.nextLine());
			
			/*String name = sc.next();
			 * list1.add(name);
			 * */
		}
		
		System.out.println("[김씨 성 가진 사람들 출력]");
								
		System.out.println("---------<toCharArray>-----------");
		for(String irum : list1) {
			if(irum.toCharArray()[0]=='김') {
				System.out.println(irum);
			}
		}
		
		System.out.println("---------<toCharArray()[index]>-----------");
		for (int i = 0; i < 5; i++) {
			String name =list1.get(i);
			if (name.toCharArray()[0] == '김') {
				System.out.println(name);
			}
		}
		
		System.out.println("---------<split>-----------");
		for (int i = 0; i < 5; i++) {
			String name =list1.get(i);
			String[] str = name.split("");
			if (str[0].equals("김")) {
				System.out.println(name);
			}
		}
		
		System.out.println("---------<subString>-----------");
		for (int i = 0; i < 5; i++) {
			String name =list1.get(i);
			if(name.substring(0, 1).equals("김")) {
			System.out.println(name);
			}
		}
		
		System.out.println("---------<startsWith>-----------");
		for (int i = 0; i < 5; i++) {
			String name =list1.get(i);
			if(name.startsWith("김")) {
				System.out.println(name);
			}
		}
		
		System.out.println("---------<charAt>-----------");
		for(String irum : list1) {
			if(irum.charAt(0)=='김') {
				System.out.println(irum);
			}
		}
		
		System.out.println("---------<indexOf>-----------");
		for(String irum : list1) {
			if(irum.indexOf("김") == 0) {
				System.out.println(irum);
			}
		}
		
			

	}

}
