package kr.or.ddit.basic.stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferIOTest02 {

	public static void main(String[] args) {
		// 문자 기반의 입력용 Buffered 스트림 예제

		// 문자 기반의 입력용 Buffered 스트림은 한 줄 단위로 데이터를 읽어올 수 있다.
		// ==> readLine()메서드

		 String currentDirectory = System.getProperty("user.dir");
	        System.out.println("Current Directory: " + currentDirectory);
		
		try {
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/file/FileTest01.java");
			BufferedReader br = new BufferedReader(fr);

			String temp = ""; // 한줄 단위로 읽어온 데이터가 저장될 변수

			for (int i = 1; (temp = br.readLine()) != null; i++) { // 조건식 :temp= br.readLine()
				System.out.printf("%4d : %s\n", i, temp);
			}

			br.close();

		} catch (IOException e) {

		}

	}

}
