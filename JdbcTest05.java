package kr.or.ddit.basic;

import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

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
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			// Lprod_id는 현재의 Lprod_id 중에서 제일 큰 값보다 1 크게 한다.
			// nvl(데이터,0) => 데이터가 null 이면 0으로
			String sql = "select nvl(max(lprod_id),0) +1 maxId from lprod"; // 쿼리문 안에 외부에서 들어가는 데이터가 없기 때문에 statement 객체
																			// 사용

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql); // resultSet 이 처음에 가리키는 곳은 자료 위 (=> 바로 꺼내올 수 없음)

			// while(rs.next()) { //데이터를 가리키는 포인터를 실제 데이터가 있는 곳으로 옮기기 위해
			// next() 로 포인터를 한칸 옮겼는데, 데이터가 있으면 true, 없으면 false
			// 현재 사용한 쿼리문의 결과는 1개이기 때문에, while문이 아닌 if문으로 사용해도 가능함

			int maxId = 0;

			if (rs.next()) {
				maxId = rs.getInt("maxId");
			}

			// --------------------------------------------------------------------------------------------------------

			// 입력받은 Lprod_gu(상품분류코드)가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
			String gu; // 입력받은 상품 분류코드가 저장될 변수
			int count = 0; // 입력한 상품 분류 코드의 개수가 저장될 변수

			do {
				System.out.println("상품 분류 코드(LPROD_GU) 입력 >> ");
				gu = scan.next();

				String sql2 = "select count(*) cnt from lprod where lprod_gu = ?";

				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, gu);

				rs = pstmt.executeQuery(); // => 얘도 실행하면 값은 한개만 나옴

				if (rs.next()) {
					count = rs.getInt("cnt");
				}

				if (count > 0) {
					System.out.println("입력한 상품 분류 코드 " + gu + "는 이미 등록된 코드입니다.");
				}
			} while (count > 0);

			System.out.println("상품 분류명 (LPROD_NM) 입력 >> ");
			String nm = scan.next();

			String sql3 = "insert into lprod (lprod_id, lprod_gu, lprod_nm) values (?,?,?)";

			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, maxId);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("등록 성공!");
			} else {
				System.out.println("등록 실패..");
			}

//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
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
