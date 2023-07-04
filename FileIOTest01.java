package kr.or.ddit.basic.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {
	public static void main(String[] args) {
		// 바이트 기반의 파일 입력용 스트림을 이용하여 파일을 읽어와 출력하기

		// 입출력 시에는 일반적으로 try-catch문 사용
		try {
			// 파일 입력용 스트림 객체 생성 => 객체를 생성할 때 읽어올 파일을 지정한다.

			// 읽어올 파일 지정하는 방법1 => 파일의 전체 경로를 문자열로 지정하기
//			FileInputStream fin = new FileInputStream("d:/d_other/test.txt");

			// 읽어올 파일 저장하는 방법2 => 읽어올 파일 정보를 갖는 File 객체로 지정하기
			
			File file = new File("d:/d_other/test.txt");
			FileInputStream fin = new FileInputStream(file);

			int c; // 읽어온 데이터가 저장될 변수

			while ((c = fin.read()) != -1) {
				// 읽어온 데이터를 화면에 출력하기
				System.out.print((char) c); // c라고 출력하면 변수 c가 정수형이라 숫자가 나옴
			}

			fin.close(); //작업 완료 후 스트림 닫기

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/*한글은 깨져서 나옴
 한글은 한 글자 표현하는데 2바이트 필요함
 1바이트씩 읽어서 출력하기 때문에, 영어, 숫자는 정상적으로 출력되지만 (1바이트만 있어도 읽어올 수 있기 떄문에)
 한글은 최소 2바이트가 있어야하기 때문에, 글자가 정상적으로 출력되지 않는다.
 바이트 기반의 스트림을 입출력하게 되면 문자를 가지고 처리를 할 때 영어권(알파벳)이 아닌 언어는 깨져서 나옴
 -> 읽어온 데이터를 가지고 분석을 해야함.
 알파벳인지 구별을 해서 , 한글의 시작데이터라고 판정하면 1바이트 읽은거랑 합쳐서 출력
 */
