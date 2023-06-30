package kr.or.ddit.mine;

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

* */

public class MyThreadTest13 {
	public static void main(String[] args) {
		String name = "";

		Horse[] horseArr = new Horse[10];

		// 객체를 배열에 담음
		for (int i = 0; i < horseArr.length; i++) {
			name = (i + 1) + "번말";
			horseArr[i] = new Horse(name);
		}

		// 객체 배열을 쓰레드 배열에 담음

		Thread[] th = new Thread[10];

		for (int i = 0; i < horseArr.length; i++) {
			th[i] = new Thread(horseArr[i]);
		}

		// 쓰레드 배열에 있는 쓰레드 10개를 start() 시킴
		for (int i = 0; i < horseArr.length; i++) {
			th[i].start();
		}

		// 10개가 다 끝나기를 기다렸다가,,,
		for (int i = 0; i < horseArr.length; i++) {
			try {
				th[i].join();
			} catch (InterruptedException e) {
			}
		}

		System.out.println();
		System.out.println("모든 말이 결승선에 도착하였습니다.");
		System.out.println();
		System.out.println("------ 결과 ------ ");

//		System.out.println(Horse.rankList);

		for (int i = 0; i < Horse.rankList.size(); i++) {
			System.out.println((i + 1) + "위 >> " + Horse.rankList.get(i));
		}

	}
}

class Horse implements Runnable {
	private String name;
	private int location;
	private int rank;
	public static ArrayList<String> rankList = new ArrayList<>();

	public Horse() {
	};

	public Horse(String name) {
		this.name = name;
	};

	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			location = i;
			System.out.println((""));
			System.out.println(name + "*");
//			System.out.println(name + "의 현재 위치 : " + location);

			try {
				Thread.sleep((int) (Math.random() * 200));
			} catch (InterruptedException e) {
			}
		}
		System.out.println("■■■■■■■■■ " + name + " 결승선 도착 ! ■■■■■■■■■");
		rankList.add(name); // 결승선에 도착하면 rankList에 말의 이름을 넣음 (순서대로)
	}

//	@Override
//	public String toString() {
//		return "Horse [name=" + name + ", location=" + location + ", rank=" + rank + "]";
//	}
}
