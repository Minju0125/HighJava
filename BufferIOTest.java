package kr.or.ddit.basic.stream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*버퍼의 크기와 출력하려는 데이터의 크기가 일치하는 경우, 데이터를 파일에 정확히 호추라려면,
 * 버퍼 크기를 데이터 크기보다 크게 설정하거나, 데이터를 모두 출력한 후에 flush()메서드를 호출하여 버퍼를 비워줘야함
 * -> 버퍼에 남아있는 데이터가 강제로 출력되어 파일에 올바르게 저장한다.*/

public class BufferIOTest {

	public static void main(String[] args) {
		//Buffered 스트림 사용 예제
		
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/bufferTest.txt");
			
			//버퍼의 크기가 5인 버퍼 스트림 객체 생성 -> 5byte
			
			//출력용 버퍼는 가득차야만 출력됨 -> 버퍼의 크기가 9라면?
			BufferedOutputStream bout = new BufferedOutputStream(fout, 9);
			
			for(int i='1'; i<='9'; i++) {
				bout.write(i);
			}
//			bout.flush(); //작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력시킨다.
			
//			fout.close(); 
			bout.close(); //보조 스트림을 닫으면 보조 스트림에서 사용한 기반 스트림도 같이 닫힌다.
			System.out.println("작업 끝...");
				
		}catch(IOException e) {
			
		}
		
	}

}
