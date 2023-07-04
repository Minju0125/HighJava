package kr.or.ddit.basic.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {

		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;

		byte[] temp = new byte[4]; // 4개 짜리 배열 생성

		ByteArrayInputStream bin = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
	
		
		try {
			//읽어올 데이터가 있는지 확인
			while (bin.available() > 0) {
//				bin.read(temp); //4개 읽어와서 출력
//				bout.write(temp);
				
//				배열을 사용해서 처리할 때에는 반환값은 실제 읽어온 갯수
				int len = bin.read(temp); // 실제 읽어온 데이터의 개수를 반환한다.
				
				//temp 배열의 데이터 중 0번째부터 len 개수만큼 출력한다.
				bout.write(temp,0,len); //0번째부터 len 갯수만큼 가져옴
				
				
				
				
				
				
				System.out.println("반복문 안에서 temp => " + Arrays.toString(temp));
				//temp 4개짜리라서 앞에서부터 4개 읽어옴
				//0,1,2,3/4,5,6,7/temp4개짜리인데, 남은 데이터는 2개라서 8,9,6,7 -> 여기서 6,7은 안지워지고 남아있음 따라서 [8,9,6,7]이 출력됨
				
			}
			
			outSrc = bout.toByteArray(); //출력한 데이터를 배열에 담기
			
			bin.close();
			bout.close();
			
			System.out.println();
			System.out.println(" inSrc => " + Arrays.toString(inSrc));
			System.out.println(" outSrc = > " + Arrays.toString(outSrc));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
