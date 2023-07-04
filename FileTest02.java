package kr.or.ddit.basic.file;

import java.io.File;

public class FileTest02 {
	public static void main(String[] args) {
		File f1 = new File("d:/D_Other/test.txt");
		System.out.println(f1.getName() + "의 파일 크기 : " + f1.length() + " bytes"); // 단위가 bytes
																					// 1KB = 1024byte
																					// 윈도우가 1KB 미만인건 다 1KB로 표시함
		System.out.println("path : " + f1.getPath()); // path는 파일객체 만들때 지정해준것을 path
		System.out.println("absolutePath : " + f1.getAbsolutePath());
		System.out.println();

		// 이클립스를 이용하여 java프로그램을 실행하면 실행된 java프로그램이 소속된 project 폴더가 현재 폴더가 된다.
		File f2 = new File("."); // 자바프로그램이 실제 실행되는 현재위치 ? ->
		System.out.println("path : " + f2.getPath());
		System.out.println("absolutePath : " + f2.getAbsolutePath()); // 절대경로
		System.out.println();

	}
}
