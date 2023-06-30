package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;

/*
 10마리의 말들이 경주하는 경마 프로그램 작성하기
 
 말은 Horse 라는 이름의 쓰레드 클래스로 작성하는데
 이 클래스는 말 이름(String), 현재위치(int), 등수(int)를 멤버변수로 가진다.
 그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준이 있다.
 (Comparable 인터페이스 구현)

 경기 구간은 1~50 구간으로 되어 있다. 
 
 경기가 끝나면 등수 순으로 출력한다.
 
 그리고 경기 중 중간중간에 각 말들의 현재 위치를 아래와 같이 나타낸다.
 
 ex)
 01번말 : ---------->----------------------------------------
 02번말 : -------------->------------------------------------
 ...
 10번말 : ---->----------------------------------------------
*/

public class ThreadTest13 {
	public static void main(String[] args) {
		Horse[] horseArr = new Horse[] { new Horse("01번말"), new Horse("02번말"), new Horse("03번말"), new Horse("04번말"),
				new Horse("05번말"), new Horse("06번말"), new Horse("07번말"), new Horse("08번말"), new Horse("09번말"),
				new Horse("10번말") };

		GameState gs = new GameState(horseArr);
		for (Horse h : horseArr) {
			h.start();
		}
		gs.start(); // 말들의 경주 상태를 출력하는 쓰레드 시작

		for (Horse h : horseArr) {
			try {
				h.join();
			} catch (InterruptedException e) {
			}
		}

		try {
			gs.join();
		} catch (InterruptedException e) {
		}

		System.out.println();
		System.out.println("경기 끝,,,");
		System.out.println();

		System.out.println("== 경기 결과 == ");

		/*
		 * 정렬 방법1) Arrays.sort(horseArr);
		 * 
		 * for(Horse h : horseArr) { System.out.println(h); }
		 * 
		 */

		// 정렬방법2)
		ArrayList<Horse> horseList = new ArrayList<Horse>();
		for (Horse h : horseArr) {
			horseList.add(h);
		}

		Collections.sort(horseList);

		for (Horse h : horseList) {
			System.out.println(h);
		}

	}
}


/*
 * 01번말 : ---------->---------------------------------------- 02번말 :
 * -------------->------------------------------------ ... 10번말 :
 * ---->----------------------------------------------
 */


// 말의 현재 위치를 나타내는 쓰레드
class GameState extends Thread {
	private Horse[] horseArr;

	// 생성자
	public GameState(Horse[] horseArr) {
		this.horseArr = horseArr;
	}

	@Override
	public void run() { 
		while (true) {
			// 모든 말들의 경기가 종료되었는지 여부 검사
			if (Horse.currentRank == horseArr.length) { //currentRank는 고정적임 => 말이 추가되면 고쳐야함 ==>배열의 갯수를 구함
				break;
			}
			
			for(int i=1; i<=15; i++) {
				System.out.println();
			} //15줄 띄워줌

			for (int i = 0; i < horseArr.length; i++) {
				System.out.print(horseArr[i].getHorseName() + " : ");

				for (int j = 1; j <= 50; j++) { // Horse 클래스 run메소드에서 j값 맞춰줘야함
					// 말의 현재 위치에는 '>'로 표시하기
					if (horseArr[i].getLocation() == j) {
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
			}

			
			//중간중간 말들의 위치를 출력하기 위함.
			//sleep 괄호 안의 숫자를 더 길게 하면 출력하는 텀이 짧아짐
			try { 
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

}

// Horse 클래스 작성
class Horse extends Thread implements Comparable<Horse> {
	public static int currentRank = 0; // 말들이 공통으로 사용할 변수 (현재까지의 말의 등수를 나타낸다.)

	private String horseName; // 말이름
	private int location; // 현재 위치
	private int rank; // 등수

	public Horse(String horseName) {
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "는(은)" + rank + "등 입니다...";
	}

	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			this.location = i; // 말의 현재 위치 저장

			try {
				Thread.sleep((int) (Math.random() * 500));
			} catch (Exception e) {
			}

		}

		// 반복문이 끝나면 경기가 끝남 -> 한 마리의 말의 경주가 끝나면 현재 등수를 구해서 저장한다.
		currentRank++;
		this.rank = currentRank;
	}

	// 등수의 오름차순 내부 정렬 기준 만들기
	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(this.rank, horse.getRank());
	}

}
