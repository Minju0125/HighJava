package kr.or.ddit.basic.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 'd:/d_other/'폴더에 있는 '펭귄.jpg'파일을
 'd:/d_other/연습용' 폴더에 '복사본_사자.jpg' 파일로 복사하는 프로그램 작성하기
 * */

public class FileCopyTest {
	public static void main(String[] args) {

		File file = new File("d:/d_other/펭귄.jpg"); // 원본파일이 있어야 함

		// 파일이 있는지 확인하기
		if (!file.exists()) {
			System.out.println(file.getPath() + "파일이 없습니다...");
			System.out.println("복사 작업을 마칩니다...");
			return;
		}

		// 복사될 것의 디렉토리
		File dir = new File("d:/d_other/연습용");

		// 연습용이라는 폴더가 존재하는지 확인하기
		if (!dir.exists()) { // 복사될 대상 폴더가 없으면 새로 만든다...
			dir.mkdirs(); // 만들고자 하는 디렉토리의 상위 디렉토리가 존재하지 않을 경우, 상위 디렉토리까지 생성 (mkdir과 차이가 있음)
			//dir.mkdir : 한 번에 하나의 디렉토리만 생성. -> 이걸로 해도 됨
		}

		// 바이트 단위로 데이터 읽어오는 것이기 때문에 복사할때는 바이트 기반 스트림 사용 권장
		FileInputStream fin = null;
		FileOutputStream fout = null;
		
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;

		try {
			// 원본 파일을 읽어올 스트림 객체 생성
			fin = new FileInputStream(file);
			bin = new BufferedInputStream(fin);

			// 대상 파일을 출력할 스트림 객체 생성
			fout = new FileOutputStream(new File(dir, "복사본_펭귄.jpg"));
			bout = new BufferedOutputStream(fout); //버퍼 스트림이용
			
			System.out.println("복사 작업 시작");

			int data; // 읽어올 데이터가 저장될 변수

//			while ((data = fin.read()) != -1) {
//				fout.write(data);
//			} // 여기서 작업을 하다가 예외 발생하면 catch 구문으로 제어 이동 -> 작업하던 스트림을 못닫을 수 도 있음

			while((data=bin.read()) != -1) {
				bout.write(data);
			}
			
			fout.flush();
			
			System.out.println("복사 작업 완료");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fin != null)
				try {
					fin.close();
				} catch (IOException e) {
				}
			if (fout != null)
				try {
					fout.close();
				} catch (IOException e) {
				}
//input, outputstream이 발생했으 때만 if문을 실행해라.
		}
	}
}