package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.JdbcBoardDaoImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardServiceImpl implements IJdbcBoardService {

	private JdbcBoardDaoImpl dao;
	private static JdbcBoardServiceImpl service;

	private JdbcBoardServiceImpl() {
		dao = JdbcBoardDaoImpl.getInstance();
	}

	public static JdbcBoardServiceImpl getInstance() {
		if (service == null) {
			service = new JdbcBoardServiceImpl();
		}
		return service;
	}

	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		// TODO Auto-generated method stub
		return dao.insertBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		// TODO Auto-generated method stub
		return dao.updateBoard(boardVo);
	}

	@Override
	public List<JdbcBoardVO> getAllBoard() {
		// TODO Auto-generated method stub
		return dao.getAllBoard();
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		// TODO Auto-generated method stub
		int cnt = dao.setCountIncrement(boardNo);
		if (cnt == 0) {
			return null;
		}
		return dao.getBoard(boardNo);
	}

	@Override
	public List<JdbcBoardVO> getSearchBoard(String title) {
		// TODO Auto-generated method stub
		return dao.getSearchBoard(title);
	}

	@Override
	public int setCountIncrement(int boardNo) {
		// TODO Auto-generated method stub
		return dao.setCountIncrement(boardNo);
	}

}
