package kr.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

//인터페이스를 구현한다는 것은 인터페이스가 가지고 있는 모든 메소드를 재정의해서 사용하겠다는 의미(구현할 기본틀)

public class MemberDaoimpl implements IMemberDao {
	
	//싱글톤
	//1. 자신 class의 참조값이 저장될 변수를 private static 으로 
	private static MemberDaoimpl dao;
	
	//2. 기본 생성자를 private으로
	private MemberDaoimpl() {}
	
	//3. 
	public static MemberDaoimpl getInstance() {
		if(dao==null) dao = new MemberDaoimpl();
		return dao;
	}
	
	
	

	@Override
	public int insertMember(MemberVO memVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수 선언

		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr) values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memVO.getMem_id());
			pstmt.setString(2, memVO.getMem_pass());
			pstmt.setString(3, memVO.getMem_name());
			pstmt.setString(4, memVO.getMem_tel());
			pstmt.setString(5, memVO.getMem_addr());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memId);

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return cnt;
	}

	//전체업데이트
	@Override
	public int updateMember(MemberVO memVo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();
			String sql = "update mymember set mem_pass = ?, mem_name = ? , mem_tel = ?, mem_addr = ? where mem_id=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memVo.getMem_pass());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			pstmt.setString(5, memVo.getMem_id());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return cnt;
	}

	/*
	 @Override public List<MemberVO> getAllMember() {
	
	 Connection conn = null; Statement stmt = null; ResultSet rs = null;
	 List<MemberVO> list = null;
	 
	
	try { conn = DBUtil3.getConnection(); stmt = conn.createStatement(); String
	sql = "select * from mymember"; rs = stmt.executeQuery(sql);
	
	MemberVO memVo = new MemberVO();
	
	while(rs.next()) { memVo.setMem_id(rs.getString("mem_id"));
	memVo.setMem_pass(rs.getString("mem_pass"));
	memVo.setMem_name(rs.getString("mem_name"));
	memVo.setMem_tel(rs.getString("mem_tel"));
	memVo.setMem_addr(rs.getString("mem_addr")); list.add(memVo); } } catch
	(SQLException e) { e.printStackTrace(); } finally { if (rs != null) try {
	rs.close(); } catch (SQLException e) { } if (stmt != null) try {
	stmt.close(); } catch (SQLException e) { } if (conn != null) try {
	conn.close(); } catch (SQLException e) { } }
	
	return list; }
	 */

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<MemberVO> memList = null;

		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from mymember";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			memList = new ArrayList<MemberVO>();

			while (rs.next()) {
				// 각 컬럼의 값들을 가져와 VO 객체에 저장한다.
				MemberVO memVo = new MemberVO(); // VO 객체 생성
				memVo.setMem_id(rs.getNString("mem_id"));
				memVo.setMem_pass(rs.getNString("mem_pass"));
				memVo.setMem_name(rs.getNString("mem_name"));
				memVo.setMem_tel(rs.getNString("mem_tel"));
				memVo.setMem_addr(rs.getNString("mem_addr"));

				memList.add(memVo); // 데이터가 저장된 VO 객체를 List에 추가한다.

			}

		} catch (SQLException e) {
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			;
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
			;
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}

		}
		return memList;

	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();

			String sql = "select count(*) cnt from mymember where mem_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			;
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
			;
		}

		return cnt;
	}

	//(key 값 정보 ==> 회원 ID(memId), 수정할 컬럼명(field), 수정할 데이터(data)
	@Override
	public int updateMember2(Map<String, String> paraMap) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt =0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "update mymember set " + paraMap.get("field") + "= ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("data"));
			pstmt.setString(2, paraMap.get("memid"));
			
			cnt = pstmt.executeUpdate();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
		return cnt;
	}

}
