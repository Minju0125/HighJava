package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//JDBC 드라이버를 로딩하고 Connection객체를 생성하여 반환하는 메서드로 구성된 class 작성하기
//(dbinfo.properties 파일의 내용을 읽어와 설정하기 ) ==> Properties 객체 이용하기

public class DBUtil2 {
	private static Properties prop;

	// 초기화블럭
	static {
		prop = new Properties(); // Properties 객체 생성
		File f = new File("res/kr/or/ddit/config/dbinfo.properties"); // 프로젝트가 실행되는 현재 폴더
		FileInputStream fin = null;

		try {
			fin = new FileInputStream(f); // 스트림 객체 생성
			prop.load(fin);

//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("입출력 오류...");
			System.out.println("드라이버 로딩 실패!!");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc13", "java");
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"),
					prop.getProperty("pass"));

		} catch (SQLException e) {
			System.out.println("DB 연결 실패 !");
			e.printStackTrace();
		}
		return conn;
	}
}
