package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {
	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<Member>();
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-1111"));
		memList.add(new Member(9, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "강감찬", "010-4444-1111"));
		memList.add(new Member(6, "일지매", "010-5555-1111"));
		memList.add(new Member(2, "변학도", "010-6666-1111"));

		System.out.println("정렬 전...");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("---------------------------------------------------");

		Collections.sort(memList); // sort는 내부정렬 기준으로 맞춰서 정렬 -> 근데 memList안의 데이터는 Member (여기엔 내부정렬 기준 x)

		System.out.println("정렬 후...");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("---------------------------------------------------");
		
		// 회원 이름의 오름차순으로 정렬되는 외부 정렬 기준을 만들어 정렬하여 출력하시오.
		// 클래스명 (NameSort)
		Collections.sort(memList, new NameSort());
		System.out.println("회원 이름의 오름차순 정렬 후");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("---------------------------------------------------");

		
	}
}

// Member 클래스 작성
// 회원번호의 오름차순의 내부 정렬기준 만들기
class Member implements Comparable<Member> { // 내부정렬에서는 클래스랑 같은 걸 제네릭으로 넣어주면 됨
	private int num; // 회원번호
	private String name;
	private String tel;

	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	// 회원 번호의 오름차순 정렬 기준 설정하기
	@Override
	public int compareTo(Member mem) { // 이 클래스 객체가 현재가지고 있는 값과 매개변수에 들어있는 값을 비교(내(현재)가 기준, 매개변수 값이 뒤의 데이터)
		if (this.getNum() > mem.getNum()) {// this는 모든 객체에서 사용가능. this는 자기 자신의 참조값 들어감
			return 1;
		} else if (this.getNum() < mem.getNum()) {
			return -1;
		} else {
			return 0;
		}
	}
	

/*
 * Wrapper 클래스 이용하는 방법1
	return new Integer(this.getNum()).compareTo(mem.getNum());

*  Wrapper 클래스 이용하는 방법2
	return Integer.compare(this.getNum(), mem.getNum());
*/	
	
}

//회원이름의 오름차순으로 정렬되는 외부 정렬 기준 클래스 작성
class NameSort implements Comparator<Member> { //<>안에는 정렬하려는 데이터
	// 회원 이름의 오름차순으로 정렬되는 외부 정렬 기준을 만들어 정렬하여 출력하시오.
	// 클래스명 (NameSort)

	@Override
	public int compare(Member mem1, Member mem2) {
		 //String은 오름차순
		return mem1.getName().compareTo(mem2.getName());
	}
}
