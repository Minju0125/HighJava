package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//JDBC (Java DataBase Connectivity) 라이브러리를 이용한 DB 자료 처리하기

public class JdbcTest01 {
	/*
	 * JDBC를 이용한 데이터베이스 처리 순서
	 * 
	 * 1. 드라이버 로딩 ==> JDBC 라이브러리를 사용할 수 있게 메모리로 읽어들이는 작업 (JDBC API 버전 4 이상에서는
	 * getConnection() 메서드에서 자동으로 로딩해주기 때문데 이 단계는 생략할 수 있다.) --> 그렇지만 생략하지 않고 사용할 예정
	 * ! Class.forName("oracle.jdbc.driver.OracleDriver");//DriverManager에 등록
	 * -DiverManager : JVM에서 JDBC 전체를 관리하는 클래스(Driver, Connection 연결 작업 등)
	 * 
	 * 
	 * 2.DB에 접속하기 ==> 접속이 완료되면 Connection 객체가 반환된다. DriverManager.getConnection()
	 * 메서드를 이용한다.
	 * 
	 * 3. 질의 하기 ==> SQL 문장을 DB 서버로 보내서 결과를 얻어온다. (Statement 객체나 PreparedStatement
	 * 객체를 이용하여 작업한다.)
	 * 
	 * 4. 결과처리하기 ==> 질의 결과를 받아서 원하는 작업을 수행한다. 1) SQL문이 select문일 때에는 select 한 결과가
	 * ResultSet 객체에 저장되어 반환된다. 2) SQL문이 select문이 아닐 때에는 정수값이 반환된다. (정수값은 보통 작업에 성공한
	 * 레코드 수를 의미한다.) (insert, update, delete문)
	 * 
	 * 5. 사용한 자원을 반납한다. ==> close() 메서드를 이용해서 반납한다.
	 */
	public static void main(String[] args) {
		//DB 작업에 필요한 변수 선언
		Connection conn = null;
		Statement stmt = null;
		//select 문 처리 예정 -> resultSet 객체에 데이터 저장되서 반환됨
		ResultSet rs = null;
		
		//exception 발생할 위험이 크기 때문에 예외처리
		try {
			//1. 드라이버 로딩
			//JDBC 드라이버 파일을 사용할 수 있도록 메모리에 로딩하기.
			//Class.forName()에 의해 JDBC 드라이버 파일의 드라이버 인터페이스를 상속한 클래스가 동적으로 로딩될 때, 자동으로 JDBC 드라이버 인스턴스가 생성되어 준비가 완료된다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB가 연결괴면 Connection 객체가 생성됨
			//conn = DriverManager.getConnection("jdbc:oracle:thin@DB서버IP주소:포트번호:SID", user,password);
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc13","java"); //이렇게 하면 connection 객체가 만들어짐!
			//실제 자바 프로그램과 데이터베이스를 네트워크상에서 연결해주는 메소드 -> 연결에 성공하면 DB와 연결된 상태를 Connection 객체로 표현하여 반환함
			
			
			//3. 질의
			//3-1. SQL문 작성
			String sql = "select * from lprod";
			//3-2. Statement 객체 생성 (또는 PreparedStatement객체 생성)
			//		==> SQL 문을 서버로보내서 처리한 결과를 얻어오는 객체로 Connection 객체를 이용하여 생성한다.
			stmt = conn.createStatement(); //데이터베이스로 SQL문을 보내기 위한 SQLServerStatement 개체를 만듦
			
			//3-3. SQL 문을 DB 서버로 보내서 처리한 결과를 얻어온다.
			rs = stmt.executeQuery(sql);
			
			//4. 결과 처리하기 ==> 한 러코드씩 화면에 출력하기
			// ResultSet 객체에 저장된 데디터를 차례로 꺼내오려면 반복문과 next() 메서드를 이용한다.
			
			System.out.println("==쿼리문 처리 결과==");
			
			//ResultSet 객체의 next()메서드 ==> ResultSet객체의 데디터를 가리키는 포인터를
			//	다음번째 레코드 위치로 이동시키고 그 곳에 데이터가 있으면 true, 없다면 false를 반환한다
			while(rs.next()) {
				//포인터가 가리키는 위치의 자료를 가져오는 방법
				//형식1) ResultSet객체.get자료형이름("컬럼명 또는 컬럼의 Alias명")
				//형식2) ResultSet객체.get자료형이름(컬럼번호) ==> 컬럼번호는 1번부터 시작한다.
				System.out.println("LPROD_ID : " + rs.getInt("lprod_id"));
				System.out.println("LPROD_GU : " + rs.getString(2));
				System.out.println("LPROD_NM : " + rs.getString("LPROD_NM"));
				System.out.println("---------------------------------------------");
			}
		
		
		
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException  e) {
			e.printStackTrace();
		} finally {
			//5. 사용했던 자원 반납하기
			if(rs!=null) try {rs.close();} catch (SQLException e) {}
			if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
			if(conn!=null) try {conn.close();} catch (SQLException e) {}
		}

	}
}
