package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
LPROD테이블에 새로운 데이터 추가하기

Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고,
Lprod_id는 현재의 Lprod_id 중에서 제일 큰 값보다 1 크게 한다.

입력받은 Lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
*/

public class JdbcTest05 {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc13", "java");
			while (true) {
				System.out.println("===== 새로운 상품 등록 =====");
				System.out.println("■ Lprod_gu ==> ");
				String gu = scan.next();
				
				
				
				
				System.out.println("■ Lprod_nm ==> ");
				String nm = scan.next();

				String sql = "select count(*) from lprod where lprod_gu = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, gu);

				int cnt = pstmt.executeUpdate();

				if (cnt != 0) {
					sql = "insert into lprod (lprod_id, lprod_gu, lprod_nm) values ((select max(lprod_id) from lprod)+1, ?, ?)";
					pstmt = conn.prepareStatement(sql);

					pstmt.setString(1, gu);
					pstmt.setString(1, nm);
					System.out.println("상품 등록완료");

				} else {
					System.out.println("이미 등록된 상품입니다.");
					System.out.println("새로 등록해주세요.");
				}

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}
}
