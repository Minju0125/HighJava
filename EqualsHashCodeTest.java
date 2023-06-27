package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Objects;

public class EqualsHashCodeTest {
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setId(1);
		p1.setName("홍길동");

		Person p2 = new Person();
		p2.setId(1);
		p2.setName("홍길동");

		Person p3 = p1;

		System.out.println(p1 == p2); // 주소값 비교(참조값 비교해서 결과 반환하도록)
		System.out.println(p1 == p3); // p3의 주소가 그대로 p1d으로 (true)
		System.out.println();

		System.out.println(p1.equals(p2));
		System.out.println(p1.equals(p3));

		System.out.println("p1 ==>" + p1);
		System.out.println("p2 ==>" + p1);
		System.out.println("p3 ==>" + p1);

		// 객체를 만들면 object 를 상속받음
		// String 에서는 값을 비교함 -> String도 객체니까 object 상속 받음 (String을 값을 가지고 비교하도록 equals)
		// 부모의 메소드를 재정의한 것 (String 객체 만들면서 String 객체 만들때, equals라는 메소드를 재정의한것)

		HashSet<Person> testSet = new HashSet<Person>();
		testSet.add(p1);
		testSet.add(p2);
		System.out.println("set의 개수 >> " + testSet.size());

		// set 에 들어가는 값을 검사할때(중복불가) 다른 걸 가지고도 검사함.
		// 객체마다 객체가 가지고 있는 고유한 hashcode를 가지고 있는데, hashcode는 객체가 만들어질때마다 생성됨 (참조값과는 별개임)
		// 해시코드가 같으면 같은 객체임 - 해시코드는 참조값을 기본으로 만드는데, 참조값이 다르면 해시코드 값도 다름

		System.out.println("p1 hashCode >> " + p1.hashCode());
		System.out.println("p2 hashCode >> " + p1.hashCode());
		System.out.println("p3 hashCode >> " + p1.hashCode());

	}

}

//id 변수 값과 name 변수값이 모두 같으면 true 가 반환되는 equals()메서드 저정의하기
class Person { // extends Object (object 상속받을 때에는 생략 가능하게)
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override // 세개의비교항목을 가지고 메소드 오버라이딩
	public boolean equals(Object obj) { // Object 매개변수 => 아무객체나 받게하기 위해
		if (this == obj) { // 이 경우는 무조건 참
			return true;
		}

		if (obj == null) { // 이 경우는 무조건 거짓
			return false; 
		}

		if (this.getClass() != obj.getClass()) { // 비교하려면 클래스의 종류가 같아야 가능함. (같은 종류의 클래스인지 검사하는것)
			return false;
		} //여기까지 하면 나와 상대방은 참조값은 다르고, 상태방은 널도 아니고, 나랑 클래스 종류가 같은 것!

		Person that = (Person) obj; // 원래의 자료형으로 형변환 // 나랑 같은 형태로 만들어주는 것 

		return this.id == that.id && Objects.equals(this.name, that.name); // 얘네가 가지고 있는 null값까지 비교해서 결과 반환
		//형변환된 것을 이용해서 내가 가지고 있는 변수와 비교하는것, 숫자(id)는 ==으로 비교 // name 비교시에는 괄호안의 값을 가지고 비교함
	}

	// 확률적으로 해시코드가 엄청 많아지면 그 중에 같은 값이 나올 가능성도 있음(희박하긴 함)
	// set에서 데이터가 같은지 검사할 때, hash코드만을 가지고 검사하지 않는 이유
	// hashcode비교하고 같으면 equals로 한번더 비교함 (따라서, 해시코드까지 같이 재정의해야함,)
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);// 위의 (id,name)두개의 값이 같으면 같은 객체로 취급 -> 두개의 값을 이용한 해시코드 만들기
	}
	
	//hash가 들어가는 객체들은 equals만 비교하는 것이 아니라, 해시코드까지 비교해야 함.
	
	/*
	 * - equals() 메서드 = > 두 객체의 내용이 같은지 비교하는 연산자 메서드
	 * - hashCode() 메서드 => 두 객체가 같은 객체인지를 비교하는 메서드
	 * 
	 * - HashSet, HashMap, Hashtable과 같이 Hash로 시작하는 컬렉션 객체들은
	 * 객체의 의미상의 동일성 비교를 위해서 HashCode()메서드와 equals() 메서드를 호출해서 비교한다.
	 * - 그래서 객체가 같은지 여부를 결정하려면 equals() 메서드와 hashCode() 메서드를 같이 재정의해야한다.
	 * 
	 * - HashCode()메서드에서 사용되는 해싱 알고리즘은 서로 다른 객체들에 대해 같은 hascode()값을 만들어 낼 수 잇다,
	 * 
	 * 
	 * */
	
	
	
	
	
	
}
