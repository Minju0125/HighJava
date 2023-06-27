package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* 정렬과 관련된 interface는 Comparable, Comparator 이렇게 두가지가 있다.
 * Comparable 은 Collection 에 추가되는 데이터자체에 정렬기준을 넣고 싶을 때 구현하는 인터페이스(내부정렬 기준)이고,
 * Comparator 는   외부에 별도로 정렬 기준을 구현하고 싶을 때 구현하는 인터페이스(외부정렬 기준)이다.
 * 
 * Comparable 에서는 compareTo() 메서드를 재정의하고,
 * Comparator 에서는 compare()메서드를 재정의해야 한다.
 * 
 * String 클래스, Wrapper 클래스, Date 클래스, File 클래스 등에는 내부 정렬 기준이 구현되어 있다.
 * (내부 정렬 기준으로 오름 차순으로 처리되도록 구현되어 있다.)
 * 
 * 
 * 
 * */

public class ListSortTest01 {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");

		System.out.println("정렬 전 : " + list);

		// 정렬은 Collection.sort() 메소드를 이욯하여 정렬한다.
		// Collection.sort() 메소드는 기본적으로 내부 정렬 기준으로 정렬한다. ->
		// 예를 들어 위의 list 는 String 타입이기 때문에, 내부정렬기준은 한글 가나다 오름차순

		Collections.sort(list); // 내부정렬 기준을 이용해서 정렬하라.

		System.out.println("정렬 후 : " + list);

		Collections.shuffle(list); // (난수 같은 걸 사용해서) 데이터를 섞어줌 -> 데이터 흩어짐

		System.out.println("자료 섞기 후 : " + list);

		// 외부 정렬 기준을 적용해서 정렬하기
		Collections.sort(list, new Desc()); // 외부정렬 객체를 생성해서 넣어줌

		System.out.println("내림차순 정렬 후 : " + list);
	}
}

// 정렬 방식을 정해주는 class 작성하기(외부정렬 기준 클래스라고 한다.)
class Desc implements Comparator<String> {

	/*
	 * Comparator 는 외부에 별도로 정렬 기준을 구현하고 싶을 때 구현하는 인터페이스(외부정렬 기준)이다. Comparator 에서는
	 * compare()메서드를 재정의해야 한다.
	 * 
	 * compare() 메서드를 이용해서 정렬하고자 하는 기준을 정해준다. compare() 메서드의 반환값의 의미 - 반환값이 0 -> 두
	 * 값이 같다. - 반환값이 양수 -> 두 값의 순서를 바꾼다. ( 앞 데이터와 뒤 데이터를 바꾼다는 뜻) - 반환값이 음수 -> 두 값의
	 * 순서를 바꾸지 않는다.
	 * 
	 * ex) 오름차순일 경우 ==> 앞의 값이 크면 양수 반환(순서바꿈), 같으면 0 반환, 앞의 값이 작으면 음수 반환 (순서안바꿈)
	 * 
	 */

	@Override // 구현해야할 메소드 // 내림차순으로 구현하려고 한다.
	public int compare(String str1, String str2) { // ★ 반환값이 int !!

		if (str1.compareTo(str2) > 0) { // 0보다 크다는건 내림차순으로 되어있다는 의미
			return -1;
		} else if (str1.compareTo(str2) < 0) { // 현재가 오름차순
			return 1;
		} else {
			return 0;
		}
	}
}