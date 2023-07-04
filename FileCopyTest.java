package kr.or.ddit.basic.stream;

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

		try {

			File file = new File("d:/d_other/펭귄.jpg");
			File copyFile = new File("d:/d_other/펭귄_copy.jpg");

			FileInputStream fin = new FileInputStream(file);
			FileOutputStream fout = new FileOutputStream(copyFile);

			int c;

			while ((c = fin.read()) != -1) {
				fout.write(c);
			}

			fin.close();
			fout.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
