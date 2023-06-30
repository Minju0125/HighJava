package kr.or.ddit.basic;

/*
 3개의 쓰레드가 각각 알파벳을 A~Z까지 출력하는데
 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기
  
 * */

public class ThreadTest12 {
	public static void main(String[] args) {
		DisplayCharacter[] disArr = new DisplayCharacter[] {
				new DisplayCharacter("홍길동"),
				new DisplayCharacter("이순신"),
				new DisplayCharacter("강감찬")
		};
		
		for (DisplayCharacter dc : disArr) {
			dc.start(); 
		}

		//3개의 쓰레드 다 끝나야 최종 결과 나옴
		for (DisplayCharacter dc : disArr) {
			try {
				dc.join();
			}catch(InterruptedException e) {
				
			}
		}
		
		System.out.println();
		System.out.println("... 경기 결과 ...");
		System.out.println("순 위 | " + DisplayCharacter.setRank);
	}

}

//A~Z까지 출력하는 쓰레드
class DisplayCharacter extends Thread {
	public static String setRank = "";
	private String name;

	public DisplayCharacter(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (char c = 'A'; c <= 'Z'; c++) {
			System.out.println(name + "의 출력 문자 : " + c);
			// 잠시 멈추는 기능
			try {
				// 난수를 이용하여 일시 정지 시간을 변경하여 처리한다.
				Thread.sleep((int) (Math.random() * 500));
			} catch (InterruptedException e) {

			}

		}

		System.out.println(name + "출력 끝...");

		// 출력을 끝낸 순서대로 이름을 배치한다.
		setRank += name + " ";
		

	}

}
