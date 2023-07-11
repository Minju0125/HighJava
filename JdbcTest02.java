package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//문제) 사용자로부터 Lprod_id 값을 입력받아 입력한 값보다 Lprod_id가 큰 자료들을 출력하시오.
public class JdbcTest02 {
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버 로딩
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc13", "java");
			
			stmt = conn.createStatement();
			
			System.out.println("LPROD_IP 값을 입력하세요. (숫자입력)");
			Scanner sc = new Scanner(System.in);
			
			int input = Integer.parseInt(sc.nextLine());
						
			//select * from LPROD where input > LPROD_ID;
			String sql = "select * from LPROD where LPROD_ID > " + input ;
			rs = stmt.executeQuery(sql);
			
			System.out.println("======= 쿼리문 처리 결과 =======");
			while(rs.next()) {
				System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID"));
				System.out.println("LPROD_GU : " + rs.getString("LPROD_GU"));
				System.out.println("LPROD_NM : " + rs.getString("LPROD_NM"));
				System.out.println("---------------------------------------------");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
