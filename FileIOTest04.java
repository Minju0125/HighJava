package kr.or.ddit.basic.stream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {

	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장하기

		try {

			// inputStream : 데이터를 읽어옴
			// System.in ==> 콘솔(표준 입출력 장치)의 입력 장치와 연결된 입력용 스트림 객체

			// 입력
			// 입력용 바이트기반 스트림을 문자 기반스트림으로 변환하는 보조 스트림
			// ==> InputStreamReader (inputStream을 reader로 변환)
			InputStreamReader isr = new InputStreamReader(System.in);

			// 출력
			FileWriter fw = new FileWriter("d:/d_other/testChar.txt");

			System.out.println("아무 내용이나 입력하세요. (입력의 끝은 Ctrl + Z키 입니다.)");

			int c;

			// 콘솔에서 입력할 때 입력의 끝은 'Ctrl' + 'Z'키를 누르면 된다.
			while ((c = isr.read()) != -1) {
				fw.write(c); // 콘솔로 입력 받은 데이터를 파일에 출력한다.
			}

			// 작업이 끝나면 스트림 닫기
			isr.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		{

		}
	}

}
