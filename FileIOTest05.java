package kr.or.ddit.basic.stream;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest05 {
	public static void main(String[] args) {

		// 한글이 저장된 파일 읽어오기 ==> 한글의 인코딩을 지정해서 읽어오기

		try {
//			FileReader fr = new FileReader("d:/d_other/test_ansi.txt");
//			FileReader fr = new FileReader("d:/d_other/test_utf8.txt");

//			int c;
//			while ((c=fr.read()) != -1) {
//				System.out.print((char)c);
//			
//			}
//			fr.close();

//------------------------------------------------------------------------------			
			FileInputStream fin = new FileInputStream("d:/d_other/test_ansi.txt");
//			FileInputStream fin = new FileInputStream("d:/d_other/test_utf8.txt");

			// 기본 인코딩 방식으로 읽어온다.
//			InputStreamReader isr = new InputStreamReader(fin);

			// 인코딩 방식을 지정해서 읽어오기
			// 형식) new InputStreamReader(기반 스트림, 인코딩 방식);
			// <인코딩 방식 예시>
			// -MS949 ==> 윈도우의 기본 한글 인코딩 방식(ANSI와 같다.)
			// -UTF-8 ==> 유니코드 UTF-8 인코딩 방식
			// -US-ASCII ==> 영문 전용 인코딩 방식

			InputStreamReader isr = new InputStreamReader(fin, "ms949");

			int c;
			while ((c = isr.read()) != -1) {
				System.out.print((char) c);
			}
			isr.close();
		} catch (IOException e) {

		}

	}

}
