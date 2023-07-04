package kr.or.ddit.basic.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

// Byte 배열을 이용한 입츌력을 담당하는 ByteArrayInputStream과 ByteArrayOutputStream을 이용하여
// 입출력하는 기본 사용법을 익힌다.

public class ByteArrayIOTest01 {
	public static void main(String[] args) {
		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;

		// 입력용 스트림 객체 생성
		ByteArrayInputStream bin = new ByteArrayInputStream(inSrc);

		// 출력용 스트림 객체 생성
		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		int data; // 읽어온 자료가 저장될 변수

		// read()메서드 ==> 더이상 읽어올 데이터가 없으면 -1을 반환한다.
		while ((data = bin.read()) != -1) {
			// 읽어온 데이터를 사용하는 코드를 이곳에 작성한다.
			bout.write(data); // 출력하기

		}
		
		//출력이 완료된 데이터는 현재 스트림에 있는데 이것을 배열로 꺼내서 저장한다.
		outSrc= bout.toByteArray();
		
		//작업이 완료되면 사용했던 스트림을 닫는다.
		try {
			bin.close();
			bout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
	}
}
