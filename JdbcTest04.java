package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		// select 문 안쓰고, insert 문 사용할 것이기 때문에 resutlSet은 필요 x

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc13", "java");

			System.out.println("계좌 번호 정보 추가하기...");
			System.out.println("추가할 계좌 번호 정보를 입력하세요...");

			System.out.print("계좌번호 >> ");
			String bank = scan.next();

			
			System.out.print("은행이름 >> ");
			String bankName = scan.next();

			System.out.print("예금주이름 >> ");
			String bankUserName = scan.next();

			// Statement 객체 이용하여 데이터 추가하기
//			String sql = "insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date) values ('" + bank + "','"
//					+ bankName + "', '" + bankUserName + "', sysdate)";

//			System.out.println("sql ==> " + sql);

//			stmt = conn.createStatement();
//			int cnt = stmt.executeUpdate(sql);

			// select 문을 실행할 때에는 executeQuery() 메서드를 사용하고,
			// insert문, update문, delete 문 등 select 문이 아닌 쿼리문을 실행할 때는
			// executeUpdate() 메서드를 사용한다.
			// executeUpdate() 메서드의 반환값 ==> 작업에 성공한 레코드 수
			
			//PreparedStatement 객체를 이용하여 처리하기
			
			//1) SQL 문을 작성할 때, SQL 문제 데이터가 들어갈 자리를 물음표(?)로 표시한다.
			String sql =  "insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date) values (?,?,?,sysdate)";
			
			//2) PreparedStatement 객체 생성 ==> 이 때 실행할 SQL 문을 인수값으로 넣어준다.
			pstmt = conn.prepareStatement(sql);
			
			//3) SQL문의 물음표(?) 자리가 들어갈 데이터를 셋팅한다.
			//형식) pstmt.set 자료형 이름 (물음표 번호, 데이터) ==> 물음표 번호는 1번부터 시작...
			pstmt.setString(1, bank);
			pstmt.setString(2, bankName);
			pstmt.setString(3, bankUserName);

			
			//4) 데이터 셋팅이 완료되면 SQL 문을 실행한다.
			int cnt = pstmt.executeUpdate();
			
			System.out.println("반환값 cnt => " + cnt);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();} catch (SQLException e1) {e1.printStackTrace();}
			if(stmt!=null) try {stmt.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}

		}

	}
}
