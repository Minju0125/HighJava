package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
	회원 관리를 하는 프로그램을 작성하시오. (MYMEMBER 테이블 이용)
	
	아래 메뉴의 기능을 모두 구현하시오. (CRUD 기능 구현하기)
	
	메뉴 예시)
		1. 자료 추가 - insert(C)
		2. 자료 삭제 - delete(D)
		3. 자료 수정 - update(U)
		4. 전체 자료 출력 - select(R)
		0. 작업 끝.
		--------------------------------
		조건)
		1. 자료 추가 기능에서 '회원 ID'는 중복되지 않는다. (중복되면 다시 입력 받는다.)
		2. 자료 삭제는 '회원 ID'를 입력받아 처리한다.
		3. 자료 수정에서 '회원 ID'는 변경되지 않는다. -> 이름, 주소, 비밀번호 등만 변경 가능함
		

*/
public class JdbcTest06 {
	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		JdbcTest06 jt = new JdbcTest06();
		jt.start();
	}

	public void start() {

		while (true) {

			System.out.println("====== 회원 관리 프로그램 ======");
			System.out.println("1) 신규 회원 정보 추가");
			System.out.println("2) 회원 정보 삭제");
			System.out.println("3) 회원 정보 수정");
			System.out.println("4) 전체 회원 정보 출력");
			System.out.println("0) 프로그램 종료");
			System.out.println();
			System.out.print("====== 메뉴선택 ==> ");

			int choiceMenu = Integer.parseInt(scan.nextLine());
			switch (choiceMenu) {
			case 1:
				add();
				break;
			case 2:
				remove();
				break;
			case 3:
				modify();
				break;
			case 4:
				showAll();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다...");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
				System.out.print("====== 메뉴선택 ==> ");
			}
		}

	}

	public void add() {
		Connection conn = DBUtil.getConnection(); // 메소드마다 conn을 쓰는게 맞나?
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		System.out.println();
		System.out.println("===== 신규 회원 정보 추가 =====");

		String id = "";
		int cnt = 0;

		do {
			System.out.print("ID >> ");
			id = scan.nextLine();

			try {

				// 여기서 select 를 써서 id 중복확인
				String sqlSelect = "select count(*) cnt from mymember where mem_id = ?";

				pstmt = conn.prepareStatement(sqlSelect);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					cnt = rs.getInt("cnt");
				}

				if (cnt > 0) {
					System.out.println("※※※ id 중복. 다시 입력해주세요.※※※");
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} while (cnt > 0);

		System.out.print("PASS >> ");
		String pass = scan.nextLine();

		System.out.print("이름 >> ");
		String name = scan.nextLine();

		System.out.print("전화번호 >> ");
		String tel = scan.nextLine();

		System.out.print("주소 >> ");
		String addr = scan.nextLine();

		// insert
		String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr) values (?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);

			int cnt2 = pstmt.executeUpdate();

			if (cnt2 > 0) {
				System.out.println("회원정보 등록 완료 !");
				System.out.println();
				System.out.println();
				return;
			} else {
				System.out.println("회원정보 등록 실패...");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void remove() {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("===== 회원 정보 삭제 =====");
		System.out.println();

		while (true) {
			System.out.print("삭제할 id 입력 >> ");
			String id = scan.next();

			try {
				String sql = "select count(*) cnt from mymember where mem_id = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);

				rs = pstmt.executeQuery();

				int cnt = 0;
				if (rs.next()) {
					cnt = rs.getInt("cnt");
				}

				if (cnt > 0) {

					while (true) {
						System.out.println("※※※  정말 삭제하시겠습니까?");
						System.out.println("1.예");
						System.out.println("2.아니오");
						System.out.print(">>");
						int choice = scan.nextInt();
						switch (choice) {
						case 1:

							String sql2 = "delete from mymember where mem_id = ?";
							pstmt = conn.prepareStatement(sql2);
							pstmt.setString(1, id);

							int result = pstmt.executeUpdate();
							if (result > 0) {
								System.out.println("삭제되었습니다.");
								System.out.println();
								System.out.println();
								System.out.println();

								return;
							} else {
								System.out.println("삭제 실패 !");
								return;
							}

						case 2:
							return;
						default:
							System.out.println("※※※  잘못입력하셨습니다. 다시 입력하세요 .※※※ ");

						}
					}
				} else {
					System.out.println("※※※ 존재하지 않는 id입니다. 다시 입력하세요.");
					System.out.println();
					continue;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void modify() {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("===== 회원 정보 수정 =====");
		System.out.println();

		while (true) {

			System.out.print("수정할 id 입력 >> ");
			String id = scan.nextLine();

			try {

				String sql = "select count(*) cnt from mymember where mem_id = '" + id + "'";

				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);

//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, id);
//
//				rs = pstmt.executeQuery();

				int cnt = 0;
				if (rs.next()) {
					cnt = rs.getInt("cnt");
				}

				if (cnt > 0) {

					System.out.println("■■■ 수정할 정보 입력 ■■■");
					System.out.print("password >> ");
					String pass = scan.nextLine();

					System.out.print("이름 >> ");
					String name = scan.nextLine();

					System.out.print("전화번호 >> ");
					String tel = scan.nextLine();

					System.out.print("주소 >> ");
					String addr = scan.nextLine();

					String sql2 = "update mymember set mem_pass = ?, mem_name = ? , mem_tel = ?, mem_addr = ? where mem_id=?";
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, pass);
					pstmt.setString(2, name);
					pstmt.setString(3, tel);
					pstmt.setString(4, addr);
					pstmt.setString(5, id);

					int result = pstmt.executeUpdate();

					if (result > 0) {
						System.out.println("변경완료 !!");
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						break;
					} else {
						System.out.println("변경실패 !!");
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();
						break;
					}

				} else {
					System.out.println("※※※ 존재하지 않는 id입니다. 다시 입력하세요.");
					System.out.println();
					continue;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void showAll() {
		Connection conn = DBUtil.getConnection();

		Statement stmt = null;
		ResultSet rs = null;

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("===== 회원 정보 전체 출력 =====");
		try {
			String sql = "select * from mymember";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println("■ Id : " + rs.getString("mem_id"));
				System.out.println("■ 이름 : " + rs.getString("mem_name"));
				System.out.println("■ 번호 : " + rs.getString("mem_tel"));
				System.out.println("■ 주소 : " + rs.getString("mem_id"));
				System.out.println("-----------------------");
			}

			System.out.println("1. 메뉴로 돌아가기");
			System.out.print("입력 >> ");
			int choice = Integer.parseInt(scan.nextLine());
			while (true) {
				switch (choice) {
				case 1:
					return;
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
					continue;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
