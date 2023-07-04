package kr.or.ddit.basic.stream;

import java.io.FileReader;
import java.io.IOException;

public class FileIOTest03 {

	public static void main(String[] args) {
		// 문자 기반의 스트림을 이용하여 파일 내용 읽어와 출력하기
		
		try {
			//문자기반 스트림
			FileReader fr  = new FileReader("d:/d_other/test.txt");
			
			int c; //데이터를 읽어와 저장할 변수
			
			while((c=fr.read()) !=-1){
				System.out.print((char)c);
			}
			
			//스트림 닫기
			fr.close();
		}catch(IOException e) {
			e.printStackTrace();
			
		}
	}

}
