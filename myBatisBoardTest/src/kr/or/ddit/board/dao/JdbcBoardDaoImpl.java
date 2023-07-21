package kr.or.ddit.board.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.util.DBUtil3;
import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.MybatisUtil;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {

	private static JdbcBoardDaoImpl instance;

	private JdbcBoardDaoImpl() {
	}

	public static JdbcBoardDaoImpl getInstance() {
		if (instance == null) {
			instance = new JdbcBoardDaoImpl();
		}
		return instance;
	}

	SqlSession session = null;
	

	
	/////////////////////////////
	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		session = MybatisUtil.getSqlSession();

		int cnt = 0;
		try {
			
			cnt = session.insert("board.insertBoard", boardVo);
			if(cnt >0) {
				System.out.println("작업 성공");
				session.commit();
			}else {
				System.out.println("작업 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {if(session!=null)session.close();}

		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		
		
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.delete("board.deleteBoard", boardNo);
			if(cnt >0) {
				System.out.println("작업 성공");
				session.commit();
			}else {
				System.out.println("작업 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {if(session!=null)session.close();}

		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {

		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.update("board.updateBoard", boardVo);
			if(cnt >0) {
				System.out.println("작업 성공");
				session.commit();
			}else {
				System.out.println("작업 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {if(session!=null)session.close();}

		return cnt;
	}

	@Override
	public List<JdbcBoardVO> getAllBoard() {

		List<JdbcBoardVO> boardList=null;
		try {
			session = MybatisUtil.getSqlSession();

			boardList = session.selectList("board.getAllBoard");
			
		} catch (Exception e) {
			e.printStackTrace();

		}  finally {if(session!=null)session.close();}
		return boardList;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		JdbcBoardVO boardVo = null;
		try {
			session = MybatisUtil.getSqlSession();

			boardVo = session.selectOne("board.getBoard", boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {if(session!=null)session.close();}
		return boardVo;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoard(String title) {
		List<JdbcBoardVO> boardList =null;
		try {
			session = MybatisUtil.getSqlSession();

			boardList = session.selectList("board.getSearchBoard");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {	if (session != null) session.close();}
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.update("board.setCountIncrement", boardNo);
			if(cnt>0) {
				System.out.println("작업 성공");
				session.commit();
			}else {
				System.out.println("작업 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {if(session!=null) {session.close();}
		}
		return cnt;
	}
}