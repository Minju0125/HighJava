package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTestSolution {
	private Scanner scan;
	private HashMap<String, Phone> phoneBookMap;

	public PhoneBookTestSolution() {
		scan = new Scanner(System.in);
		phoneBookMap = new HashMap<String, Phone>();
	}

	// 메뉴를 출력하고 작업 번호를 입력받아 반환하는 메소드
	public int displayMenu() {
		System.out.println("==========================");
		System.out.println("1.전화번호 등록");
		System.out.println("2.전화번호 삭제");
		System.out.println("3. 전화번호 수정");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("==========================");
		System.out.print("번호입력 >> ");
		return scan.nextInt();
	}

	// 프로그램을 시작하는 메소드
	public void phoneStart() {
		System.out.println();
		System.out.println("==========================");
		System.out.println("     전 화 번 호 관 리 프 로 그 램  ");
		System.out.println("==========================");
		while (true) { // 반복문을 통해 반복적인 작업 실행 -> 메뉴에 종료하는 기능 존재하기 떄문에 무한반복문으로 만듦.
			int choice = displayMenu();

			switch (choice) {
			case 1: // 등록
				insert();
				break;
			case 2: // 삭제
				delete();
				break;
			case 3: // 수정
				update();
				break;
			case 4: // 검색
				search();
				break;
			case 5: // 전체출력
				displayAll();
				break;
			case 0: // 종료
				System.out.println("프로그램을 종료합니다.");
				return; // 메인메소드로 돌아가는데, 메인메소드에 더 이상 실행할 작업이 존재하지 않기 때문에 프로그램 종료함.
			default:
				System.out.println("작업 번호를 잘못 입력했습니다...");
				System.out.println("다시 입력하세요.");
			}
		}
	}

	// 새로운 전화번호 정보를 등록하는 메소드
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.println("이름 >> ");
		String name = scan.next();

		// 이미 등록된 사람인지 검사
		if (phoneBookMap.containsKey(name)) { // containsKey (키 값이 존재하는지 판별하는 메소드 / boolean)
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return;
		}
		System.out.println("전화번호 >> ");
		String tel = scan.next();

		//입력 버퍼 비우기
		scan.nextLine(); // 버퍼를 비움 // 위의 next에서 남아있던 엔터를 먹어버림(이 명령 실행을 통해 버퍼가 비워짐)
				
		System.out.println("주 소 >> ");
		
		String addr = scan.nextLine(); 
		//nextLine말고, next, nextInt 등은 데이터 다 가져가도 찌꺼기(엔터) 남음 -> nextLine(); 으로 버퍼 비워줌
		
		/*nextLine을 쓰면 주소 입력할 칸 없이 그대로 넘어가버리는 이유:
		
		*nextLine이전 명령이 next 인데, 010-1111-1111 (엔터)
		*next 명령에서 엔터키 없이 010-1111-1111 만 남음 -> 다음 nextLine명령에서 이 엔터를 가져가버림-> 
		*빈 공백이 nextLine명령으로 들어가버림. => nextLine하기 전에 엔터키를 지워서 버퍼를 없애여 함.
		*nextLine을 한번 더 씀으로써 해결함.
		*
		*/

//		Phone p = new Phone(name, tel, addr);
//		phoneBookMap.put(name, p);

		phoneBookMap.put(name, new Phone(name, tel, addr));
		System.out.println(name + "씨 전화번호 등록 완료 !");
	}

	// 전체 자료를 출력하는 메소드
	private void displayAll() {
		System.out.println("---------------------------------------");
		System.out.println("번호             이름             전화번호             주소");
		System.out.println("---------------------------------------");

		// Map의 key 값들만 모두 가져온다.
		Set<String> phoneKeySet = phoneBookMap.keySet();

		// phoneKeySet에 데이터가 한개라도 있으면 반복해서 출력하도록.
		if (phoneKeySet.size() == 0) {
			System.out.println("등록된 전화번호 정보가 하나도 없습니다...");
		} else {
			int num = 1; // 번호
			Iterator<String> keyIt = phoneKeySet.iterator();
			while (keyIt.hasNext()) {
				String key = keyIt.next(); // key값(이름) 구하기
				Phone p = phoneBookMap.get(key); // value값(phone클래스의 인스턴스) 구하기
				System.out.println(num + "\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
				num++;

			}
		}
		System.out.println("---------------------------------------");
		System.out.println("출력 끝..");
	}

	// 전화번호 정보를 수정하는 메소드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요...");
		System.out.println("이 름 >> ");
		String name = scan.next();

		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 등록되지 않은 사람입니다...");
			return;
		}

		System.out.println("새로운 전화번호 >> ");
		String newTel = scan.next();

		scan.nextLine();
		
		System.out.println("새로운 주소 >> ");
		String newAddr = scan.nextLine();

		// 같은 key 값으로 새로운 데이터를 추가하면 수정작업이 완료된다.
		phoneBookMap.put(name, new Phone(name, newTel, newAddr));

		System.out.println(name + "씨의 전화번호 정보를 변경했습니다...");

	}

	// 전화번호 정보를 검색하는 메소드
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요.");
		System.out.println("이  름 >> ");
		String name = scan.next();

		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨의 전화번호 정보는 없습니다...");
			return;
		}

		Phone p = phoneBookMap.get(name);
		System.out.println(name + "씨 전화번호 정보");
		System.out.println("---------------------");
		System.out.println("이   름 : " + p.getName());
		System.out.println("전화번호 : " + p.getTel());
		System.out.println("주  소 : " + p.getAddr());
		System.out.println("---------------------");
		System.out.println();

	}

	// 전화번호 정보를 삭제하는 메소드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.println("이 름 >> ");
		String name = scan.next();

		// 등록된 사람인지 검사
		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 등록되지 않은 사람입니다...");
			return;
		}

		phoneBookMap.remove(name);
		System.out.println(name + "씨의 전화번호가 삭제되었습니다.");

	}

	public static void main(String[] args) {
		new PhoneBookTestSolution().phoneStart();
	}
}
