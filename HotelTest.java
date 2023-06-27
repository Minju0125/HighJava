package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class HotelTest {
	private Scanner sc;
	private HashMap<Integer, Room> hotelMap;

	public HotelTest() {
		sc = new Scanner(System.in);
		hotelMap = new HashMap<Integer, Room>();

		// 관리클래스의 생성자에서는 방번호와 방종류를 초기화한다.
		for (int i = 2; i <= 4; i++) {
			String type = null;
			switch (i) {
			case 2:
				type = "싱글룸";
				break;
			case 3:
				type = "더블룸";
				break;
			case 4:
				type = "스위트룸";
				break;
			}

			for (int j = 1; j <= 9; j++) {
				int num = i * 100 + j;
				hotelMap.put(num, new Room(num, type));
			}
		}
	} // 생성자 끝...

	// 시작 메서드
	public void hotelStart() {
		System.out.println();
		System.out.println("*********************************************");
		System.out.println("       호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		System.out.println();

		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1: // 체크인
				checkIn();
				break;
			case 2: // 체크아웃
				checkOut();
				break;
			case 3: // 객실상태
				displayRoom();
				break;
			case 4: // 업무종료
				System.out.println();
				System.out.println("*********************************************");
				System.out.println("       호텔문을 닫았습니다.");
				System.out.println("*********************************************");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다...");
				System.out.println("다시 선택하세요...");
			}
		}
	}

	private void displayRoom() {
		System.out.println();

		// 방 번호를 순서대로 나오게 하기 위해서 방번호(Map의 key값)만 List에 넣은 후 정렬하여 사용한다.
		ArrayList<Integer> numList = new ArrayList<Integer>(hotelMap.keySet());

		Collections.sort(numList); // 정렬하기

		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("      현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호    방 종류    투숙객 이름");
		System.out.println("----------------------------------------------");

		// List에서 방번호를 하나씩 차례로 꺼내와 Map에서 해당 방번호와 짝이 되는 Room객체르 구해서 출력한다.
		for (int num : numList) {
			Room r = hotelMap.get(num);

			System.out.print(r.getRoomNum() + "\t" + r.getRoomType() + "\t");
			String name = r.getGuestName();
			System.out.println(name == null ? "-" : name);

			System.out.println("----------------------------------------------");

		}

	}

	private void checkOut() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("   체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방번호 입력 >>");
		int num = sc.nextInt();

		if (!hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실은 존재하지 않습니다...");
			return;
		}

		if (hotelMap.get(num).getGuestName() == null) {
			System.out.println(num + "호 객실에는 체크인 한 손님이 없습니다...");
			return;
		}

		// 체크 아웃 작업 ==> 해당 객실의 투숙객 이름을 null로 변경하면 된다.
		String name = hotelMap.get(num).getGuestName(); // 체크아웃 할 투숙객 이름 구하기
		hotelMap.get(num).setGuestName(null); // 투숙객 이름을 null로 변경하기

		System.out.println(num + "호 객실의 " + name + "님이 체크아웃을 완료하였습니다...");

	}

	private void checkIn() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("   체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸");
		System.out.println(" * 301~309 : 더블룸");
		System.out.println(" * 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");
		System.out.print("방 번호 입력 >>");
		int num = sc.nextInt();

		// 입력한 방번호가 Map의 key값에 없으면 없는 방번호이다.
		if (!hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실은 존재하지 않습니다...");
			return;
		}

		// 입력한 방번호에 손님이 있는지 여부 검사
		if (hotelMap.get(num).getGuestName() != null) {
			System.out.println(num + "호 객실에는 이미 손님이 있습니다...");
			return;
		}

		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >>");
		String name = sc.next();

		// 입력한 이름을 해당 방번호의 투숙객 이름을 저장할 변수에 저장한다.
		hotelMap.get(num).setGuestName(name);

		System.out.println(num + "호 객실에 " + name + "씨가 체크인을 완료하였습니다...");
	}

	// 메뉴를 출력하고 작업 번호를 반환하는 메서드
	private int displayMenu() {
		// alt+shift+a => 블록지정
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print("선택>>");
		return sc.nextInt();
	}

	public static void main(String[] args) {
		new HotelTest().hotelStart();
	}
}

class Room {
	private int roomNum;
	private String roomType;
	private String guestName;

	public Room() {
	}

	public Room(int roomNum, String roomType) {
		this.roomNum = roomNum;
		this.roomType = roomType;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	@Override
	public String toString() {
		return "Room [roomNum=" + roomNum + ", roomType=" + roomType + ", guestName=" + guestName + "]";
	}

}