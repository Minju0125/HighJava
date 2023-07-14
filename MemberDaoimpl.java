package kr.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import kr.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

//인터페이스를 구현한다는 것은 인터페이스가 가지고 있는 모든 메소드를 재정의해서 사용하겠다는 의미(구현할 기본틀)

public class MemberDaoimpl implements IMemberDao {

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
			String sql = "delete * from mymember where mem_id = ?";
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

	@Override
	public List<MemberVO> getAllMember() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<MemberVO> list = null;

		
		try {
			conn = DBUtil3.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from mymember";
			rs = stmt.executeQuery(sql);
			
			MemberVO memVo = new MemberVO();
			
			while(rs.next()) {
				memVo.setMem_id(rs.getString("mem_id"));
				memVo.setMem_pass(rs.getString("mem_pass"));
				memVo.setMem_name(rs.getString("mem_name"));
				memVo.setMem_tel(rs.getString("mem_tel"));
				memVo.setMem_addr(rs.getString("mem_addr"));
				list.add(memVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return list;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cnt=0;
		
		try {
		conn = DBUtil3.getConnection();
		
		String sql = "select count(*) cnt from mymember where mem_id = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		rs = pstmt.executeQuery();
		
		
		
		if(rs.next()) {cnt =rs.getInt("cnt");}
		
		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try{pstmt.close();}catch (Exception e) {};
			if(conn!=null)try{conn.close();}catch (Exception e) {};
		}
		
		return cnt;
	}

}
