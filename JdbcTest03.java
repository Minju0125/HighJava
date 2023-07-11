package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//문제) Lprod_id 값을 2개 입력 받아서 두 값 중 작은 값부터 큰 값까지 데이터를 출력하시오

public class JdbcTest03 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.print("LPROD_ID. \n값 1 입력 : ");
		int input1 = Integer.parseInt(sc.nextLine());

		System.out.print("값 2 입력 : ");
		int input2 = Integer.parseInt(sc.nextLine());

//		//어떤게 크고 작은지 구별해야함 !
//		//3항 연산자
//		int max = input1 > input2 ? input1 : input2 ;
//		int min = input1 > input2 ? input2 : input1 ;
//				// 조건문				참		거짓

		// 메서드 이용
		int max = Math.max(input1, input2);
		int min = Math.min(input1, input2);

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc13", "java");

			stmt = conn.createStatement();
			/*
			 * String sql = "select * from LPROD where LPROD_ID between " + min + " and " +
			 * max + " order by LPROD_ID ASC";
			 * 
			 * rs = stmt.executeQuery(sql);
			 */
			
			
			 String sql = "select * from LPROD where LPROD_ID between ? and ? order by LPROD_ID ASC";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1,  min);
			 pstmt.setInt(2,  max);
			 
			 rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("-----------------------------------------------");
				System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID"));
				System.out.println("LPROD_GU : " + rs.getString("LPROD_GU"));
				System.out.println("LPROD_NM : " + rs.getString("LPROD_NM"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
