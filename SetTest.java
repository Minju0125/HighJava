package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SetTest {
	/*
	 * - List와 Set의 차이점
	 * 
	 * 1. List - 데이터의 순서 (index) - 중복되는 데이터를 저장할 수 있다. - ex) 책꽂이에 책 꽃아놓는 형태?
	 * 
	 * 2. Set - 데이터의 순서(index)가 없다. - 중복되는 데이터를 저장할 수 없다. 중복되는 데이터를 포함시키고 싶지 않을 때 !
	 * 
	 * 로또 번호 -> 중복되면 안됨.(전에는 난수만들어서 배열에 넣고 비교해서 중복되면 제거되는 방식으로 만들었었는데, Set을 사용하면 가능)
	 * 
	 */

	public static void main(String[] args) {
		HashSet hs1 = new HashSet();

		// Set 객체에 데이터를 추가할 때도 add() 메서드를 사용한다.
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);

		System.out.println("set 데이터 >> " + hs1); // Set에는 추가한 순서와 출력 순서가 다름 (set에서 출력하는 고유한 순서가 있긴 함)
		System.out.println("set의 개수 >> " + hs1.size());

		// Set 에 중복되는 데이터를 추가하면 false를 반환하고 데이터는 추가되지 않는다.
		boolean isAdd = hs1.add("FF");
		System.out.println("\n중복되지 않을 때 >> " + isAdd);
		System.out.println("set 데이터 >> " + hs1);
		System.out.println();

		isAdd = hs1.add("CC");
		System.out.println("중복될때 >> " + isAdd);
		System.out.println("set 데이터 >> " + hs1);
		System.out.println();

		// Set의 데이터 수정하기 index가 없기 때문에 어떤 데이터를 수정할지 모름 -> 수정하는 명령이 따로 없음
		// ==> 해당 자료를 삭제하고 새로운 데이터를 추가하는 방법을 사용한다.

		// 삭제하는 메서드 : remove(삭제할 자료) ==> 삭제 성공 (true), 삭제 실패 (false) 반환
		// clear()==> 전체 삭제

		// 예) "FF" 데이터를 "EE"로 변경하기
		hs1.remove("FF");
		System.out.println("삭제 후 Set >> " + hs1);
		hs1.add("EE");
		System.out.println("EE 추가 후 >> " + hs1);
		System.out.println();

//		hs1.clear(); set의 데이터를 비울 때 사용
//		System.out.println("clear 후 Set >> " + hs1);

		/*
		 * Set의 데이터는 순서(index)가 없기 때문에 List처럼 index로 데이터를 하나씩 불러올 수 없다. 그래서 데이터를 하나씩 얻기
		 * 위해서는 Iterator형 객체로 변환해야 한다. (순서대로 처리할 수 있도록 하는 객체)
		 * 
		 * -Set 형의 데이터들을 Iterator형 객체로 변환하는 메서드==> Iterator()
		 * 
		 * 
		 */

		Iterator it = hs1.iterator();

		/*
		 * Iterator의 hasNext() 메서드 ==> Iterator의 포인트가 가리키는 곳의 다음번째데 데이터가 있으면 true/ 없으면
		 * false 반환함.
		 * 
		 * pointer가 가리키는 곳의 다음에 ? 데이터가 있냐? -> 있으면 true 데이더가 있으면 데이터 꺼내옴 (next())
		 */
		while (it.hasNext()) {
			// Iterator의 next()메서드
			// => Iterator의 포인터를 다음번째에 이동한 후 이동한 자리의 데이터를 반환함.
			System.out.println(it.next()); // 꺼내온 값을 출력함.

		}
		System.out.println("\n---향상된 for문---");
		for (Object obj : hs1) {
			System.out.println(obj);
		}
		System.out.println();

		// 우리반 학생들의 번호를 이용하여 추첨하는 프로그램을 작성해보자.
		// 번호는 1번부터 28번까지 있고, 추첨할 인원은 3명
		// 당첨자를 출력해보시오...

		// 최소값~최대값 사이의 정수형 난수 만들기
		// (int)Math.random()*(최대값-최소값+1) + 최소값

		// set에서 중복되는 데이터 있으면 추가 안시킴( 추가 안시키면 사이즈 변하지 않음, 가장 처음 실행 size는 0)
		HashSet<Integer> testSet = new HashSet<Integer>();
		while (testSet.size() < 3) {
			testSet.add((int) (Math.random() * (28 - 1 + 1) + 1));
		}
		System.out.println("당첨자 번호 >> " + testSet);
		
		// Set 유형의 자료를 List 형으로 변환하기
		ArrayList<Integer> testList = new ArrayList<Integer>(testSet);
		
		System.out.println("List의 데이터 출력...");
		for(int i=0; i<testList.size(); i++) {
			System.out.println(testList.get(i));
		}
		
		

	}
}
