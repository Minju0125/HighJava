package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*

Vector, Hashtable 등과 같이 예전부터 존재하던 Collection 객체들은
 내부에 동기화 처리가 되어있다.
 
 그런데 새롭게 구성된 Collection 객체들은 동기화 처리가 되어있지 않다.
 그래서, 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면
 동기화 처리를 한 후에 사용해야한다.
(방법 => Collections 객체의 synchronized로 시작하는 메서드 이용 ex)Collections.synchronizedList())




 */

public class ThreadTest17 {
	public static Vector<Integer> vec = new Vector<Integer>();
	
	public static List<Integer> list = new ArrayList<Integer>(); //list는 동기화 처리 안되어있음
	
	public static List<Integer> list2 = Collections.synchronizedList(new ArrayList<Integer>()); //Collections 클래스에 map, set, list 동기화 처리하는 메소드 다 있음.

	public static void main(String[] args) {
		// 익명구현체
		Runnable r = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
//					vec.add(i);
//					list.add(i);
					list2.add(i);
				}
			}
		};
		
//---------------------------------------------------------
		
		Thread[] thArr = new Thread[] {
				new Thread(r), new Thread(r), new Thread(r), new Thread(r), new Thread(r)
		};
		
		for(Thread th : thArr) {
			th.start();
		}

		for(Thread th : thArr) {
			try {
				th.join(); //쓰레드가 다 끝날때까지 기다리기
				
			} catch (InterruptedException e) {
				
			}
		}
		
//		System.out.println("vec의 개수 : " + vec.size());
//		System.out.println("list의 개수 : " + list.size()); // 실행하면 중간중간에 오류도 뜸 
		//오류 뜨는 이유는 벡터, 어레이리스트는 내부에서 배열을 사용하는데, 처음애는 작은 크기의 배열을 만들다가 데이터가 추가될 수록 2배수의 저장공간을 반복해서 만듦
		//배열이 모자라면 새롱누 배열을 만들고 원래 있던 데이터를 큰 크기의 배열에 옮기는 작업을 수행하는데
		// 동기화 작업 안되면 데이터끼리 엉키거나, 여러개의 쓰레드가 하나의 자리에 겹쳐서 넣는 경우도 발생하기 떄문에 list의 갯수가 실행시마다 달라짐
		// list도 필요시, 동기화 처리 가능함 !
	
		System.out.println("list2의 개수 : " + list2.size()); // 동기화 처리되어서 정상작동함
	}
}
