package kr.or.ddit.basic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/*문제)
 * 이름, 전화번호, 주소를 멤버로 갖는 Phone 클래스를 만들고
 * (Map의 구조는 key 값으로 '이름'을 사용하고, value의 값으로는 'Phone 클래스의 인스턴스'로 한다.)
 * HashMap<String, Phone> 변수명
 * 
 * 아래 메뉴를 구현하시오
 * 1.전화번호 등록
 * 2.전화번호 삭제
 * 3. 전화번호 수정
 * 4. 전화번호 검색
 * 5. 전화번호 전체 출력
 * 0. 프로그램 종료
 * ---------실행예시----------
 * ==========================
 * 1.전화번호 등록
 * 2.전화번호 삭제
 * 3. 전화번호 수정
 * 4. 전화번호 검색
 * 5. 전화번호 전체 출력
 * 0. 프로그램 종료
 * ==========================
 * 번호입력 >> 1
 * 
 * 새롭게 등록할 전화번호 정보를 입력하세요.
 * 이름>> 홍길동
 * 전화번호 >> 010-1234-5678
 * 주소>> 대전시 중구 오류동
 * 
 * '홍길동'전화번호 등록 완료!!

 * */

public class PhoneBookTest {
	public static void main(String[] args) {
		PhoneBookTest t = new PhoneBookTest();
		t.start();

	}

	public void start() {
		Phone p = new Phone();
		p.displayMenu();

	}
}

class Phone {
	private String name;
	private String tel;
	private String addr;

	HashMap<String, Phone> person = new HashMap<>();
	Scanner sc = new Scanner(System.in);
	PhoneBookTest t = new PhoneBookTest();

	Phone() {
	}

	Phone(String name, String tel, String addr) {
		super(); //부모클래스의 생성자를 호출하기 위해
		this.name = name;
		this.tel = tel;
		this.addr = addr;

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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public HashMap<String, Phone> getPerson() {
		return person;
	}

	public void setPerson(HashMap<String, Phone> person) {
		this.person = person;
	}

	public void displayMenu() {

		while (true) {
			System.out.println("==========================");
			System.out.println("1.전화번호 등록");
			System.out.println("2.전화번호 삭제");
			System.out.println("3. 전화번호 수정");
			System.out.println("4. 전화번호 검색");
			System.out.println("5. 전화번호 전체 출력");
			System.out.println("0. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("번호입력 >> ");
			int answer = sc.nextInt();

			switch (answer) {
			case 1:
				addPhoneNumber();
				break;
			case 2:
				removePhoneNumber();
				break;
			case 3:
				modifyPhoneNumber();
				break;
			case 4:
				searchPhoneNumber();
				break;
			case 5:
				showAllPhoneNumber();
				break;
			case 0:
				return; // 시스템 종료
			default:
				System.out.println("잘못입력하셨습니다. 다시 시도하세요.");
			}
		}
	}

	// 전화번호 등록 메소드
	public void addPhoneNumber() {
//       * 새롭게 등록할 전화번호 정보를 입력하세요.
//       * 이름>> 홍길동
//       * 전화번호 >> 010-1234-5678
//       * 주소>> 대전시 중구 오류동
		System.out.println();
		System.out.println("-------[전화번호 신규등록]-------");

		System.out.print("이름 >> ");
		String name = sc.next();
		System.out.print("전화번호 >> ");
		String tel = sc.next();
		System.out.print("주소 >> ");
		String addr = sc.next();

		person.put(name, new Phone(name, tel, addr));
		System.out.println();
//      System.out.println(person.toString());
		System.out.println("♥전화번호 등록 완료 !");
		System.out.println();
		System.out.println("[신규정보]");
		System.out.println("■ 이름 : " + person.get(name).name);
		System.out.println("■ 전화번호 : " + person.get(name).tel);
		System.out.println("■ 주소 : " + person.get(name).addr);
		System.out.println();

	}

	// 전화번호 삭제 메소드
	public void removePhoneNumber() {
		System.out.println();
		System.out.println("-------[전화번호 삭제]-------");
		System.out.print("이름 >> ");
		String name = sc.next();
		System.out.println();

		System.out.println("[현재정보]");
		System.out.println("■ 이름 : " + person.get(name).name);
		System.out.println("■ 전화번호 : " + person.get(name).tel);
		System.out.println("■ 주소 : " + person.get(name).addr);
		System.out.println();
		System.out.println("정보를 삭제하시겠습니까?");
		System.out.println("1.예   2.아니오(메뉴로 돌아가기)");
		int answer = sc.nextInt();
		while (true) {
			switch (answer) {
			case 1:
				person.remove(person.get(name));
				System.out.println("♥전화번호 삭제 완료 !");
				System.out.println();
			case 2:
				return;
			}
		}
	}

	// 전화번호 수정 메소드
	public void modifyPhoneNumber() {
		System.out.println("-------[전화번호 변경]-------");
		System.out.print("이름 >> ");
		String name = sc.next();
		System.out.println();

		System.out.println("[현재정보]");
		System.out.println("■ 이름 : " + person.get(name).name);
		System.out.println("■ 전화번호 : " + person.get(name).tel);
		System.out.println("■ 주소 : " + person.get(name).addr);
		System.out.println();
		System.out.println("정보를 변경하시겠습니까?");
		System.out.println("1.예   2.아니오(메뉴로 돌아가기)");
		int answer = sc.nextInt();
		while (true) {
			switch (answer) {
			case 1:
				System.out.print("변경할 전화번호 입력>> ");
				String modifiedTel = sc.next();
				person.get(name).tel = modifiedTel;
				System.out.println("♥전화번호 변경 완료 !");
				System.out.println();
				System.out.println("[변경정보]");
				System.out.println("■ 이름 : " + person.get(name).name);
				System.out.println("■ 전화번호 : " + person.get(name).tel);
				System.out.println("■ 주소 : " + person.get(name).addr);
				System.out.println();
			case 2:
				return;
			}
		}
	}

	// 전화번호 검색 메소드
	public void searchPhoneNumber() {
		System.out.println();
		System.out.println("-------[전화번호 조회]-------");
		System.out.print("이름 >> ");
		String name = sc.next();
		System.out.println();

		System.out.println("[현재정보]");
		System.out.println("■ 이름 : " + person.get(name).name);
		System.out.println("■ 전화번호 : " + person.get(name).tel);
		System.out.println("■ 주소 : " + person.get(name).addr);
		System.out.println();

		return;
	}

	// 전화번호 전체 출력 메소드
	public void showAllPhoneNumber() {
		System.out.println();
		System.out.println("-------[전체 전화번호 조회]-------");
		Collection<Phone> phoneValues = person.values();
		Iterator<Phone> iter = phoneValues.iterator();
		while (iter.hasNext()) {
			Phone phone = iter.next();

			System.out.println("이름: " + phone.getName());
			System.out.println("전화번호: " + phone.getTel());
			System.out.println("주소: " + phone.getAddr());
			System.out.println("---------------------------");

		}
		return;
	}

	@Override
	public String toString() {
		return "Phone [name=" + name + ", tel=" + tel + ", addr=" + addr + ", person=" + person + "]";
	}

}