package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.BoardVO;

public class BoardDAOImpl implements IBoardDAO {

private static BoardDAOImpl dao;
	
	private BoardDAOImpl() { }
	
	public static BoardDAOImpl getInstance() {
		if(dao==null) dao = new BoardDAOImpl();
		return dao; 
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		//데이터베이스와 연결해서 내가 원하는결과를 얻기 위한 쿼리를 실행할 준비를 한다.
		SqlSession session = null;
		int cnt=0;
		
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.insert("Board.insertBoard", boardVO);
			
			if(cnt > 0) session.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> selectBoardList() {
		
		SqlSession session = null;
		List<BoardVO> boardList = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			boardList = session.selectList("Board.selectBoardList");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}
		return boardList;
	}
	
}
