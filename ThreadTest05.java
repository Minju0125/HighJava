package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest05 {
	public static void main(String[] args) {
		
		// 사용자로부터 데이터 입력 받기
		String str = JOptionPane.showInputDialog("아무거나 입력하세용");
		System.out.println("입력 값 >> " + str);
		
		//10초를 기다리는 쓰레드
		for (int i = 10; i > 0; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000); // 1초동안 멈춘다.
			} catch (InterruptedException e) {
			}
		}
	}
}

