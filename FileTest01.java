package kr.or.ddit.basic.file;

import java.io.File;

public class FileTest01 {
	public static void main(String[] args) {
		/*
		 * File 객체 만들기 연습
		 */

		// 1.new File(String 파일 또는 경로) ==> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분문자는
		// 역슬레쉬('\')를 사용하거나 슬레쉬('\')를 사용할 수 있다.
		// 텍스트 파일에 들어있는 정보를 가지고 파일 객체 만들기
//		File file1 = new File("d:\\D_other\\test.txt"); //구분문자를 역슬레쉬('\')로 사용
		File file1 = new File("d:/D_Other/test.txt"); // 구분문자를 슬레쉬('/')로 사용
		// 이렇게 하면 파일 객체가 만들어진 것 !

		System.out.println("파일명 : " + file1.getName());
		System.out.println("디렉토리인가? ===> " + file1.isDirectory());
		System.out.println("파일인가 ? ==> " + file1.isFile()); // true
		System.out.println();

		File file2 = new File("d:/D_Other"); // 얘는 디렉토리 // 디렉토리 정보도 파일 객체로 다룰 수 있음
		System.out.println("파일명 : " + file2.getName());
		System.out.println("디렉토리인가? ===> " + file2.isDirectory()); // true
		System.out.println("파일인가 ? ==> " + file2.isFile());
		System.out.println();

//		 2. new File(File parent, String child) => 'parent' 디렉토리 안에 있는 'child' 파일의 정보를 
//			갖는 File 객체 생성

//		File file1 = new File("d:\\D_other\\test.txt");
		File file3 = new File(file2, "test.txt"); // 경로부분까지만 파일 객체로 만들어서 쓸 수 있음

		System.out.println("파일명 : " + file3.getName());
		System.out.println("디렉토리인가? ===> " + file3.isDirectory());
		System.out.println("파일인가 ? ==> " + file3.isFile());
		System.out.println();

		// 3. new File(File parent, String child)
		// ==> 'parent' 디렉토리 안에 있는 'child'파일의 정보를 갖는 File 객체 생성
		File file4 = new File("d:/D_Other", "test.txt");

		System.out.println("파일명 : " + file4.getName());
		System.out.println("디렉토리인가? ===> " + file4.isDirectory());
		System.out.println();
		System.out.println("--------------------------------------------------------------");
		System.out.println();

		// 디렉토리(폴더)만들기
		/*
		 * - mkdir() ==> File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		 * ==> 반환값: 만들기 성공(true), 만들기 실패(false)
		 * ==> 지정한 전체 경로 중에서 중간 부분의 경로가 모두 만들어져 있어야 마지막 위치의 경로를 만들 수 있다.
		 * 
		 * - mkdirs() ==> 전체 경로 중에서 중간 부분이 없으면 중간 부분의 경로도 같이 만들어준다.
		 */

		File file5 = new File("d:/d_Other/연습용"); // d 드라이브에 이 파일 없는데, 넣을 수도 있음??????????????
		System.out.println(file5.getName() + "의 존재 여부 : " + file5.exists());
		System.out.println();

		if (file5.mkdir()) {
			System.out.println(file5.getName() + "만들기 성공 !");
		} else {
			System.out.println(file5.getName() + "만들기 실패 !");
		}

		System.out.println();
		System.out.println("------------------------------------------------------------------------");
		
		
		File file6 = new File("d:/D_Other/test/java/src"); // 전체중에 마지막인 src를 폴더로 만듦 -> 마지막 앞부분의 경로가 다 만들어져 있어야, 마지막 위치의 경로를 폴더로 만들 수 있음
															// 현재 test도 없고, java 도 없기 때문에
		
		System.out.println(file6.getName() + "의 존재 여부 : " + file6.exists());
		if(file6.mkdirs()) {
			System.out.println(file6.getName() + "만들기 성공 !!!!");
		} else {
			System.out.println(file6.getName() + "만들기 실패 !!!!");
		}
		
		
		
		
	}
}
