package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {

	private IBoardDAO dao;
	
	private static BoardServiceImpl service;
	
	private BoardServiceImpl() {
		dao = BoardDAOImpl.getInstance();
	}
	
	public static BoardServiceImpl getInstance() {
		if(service==null) service = new BoardServiceImpl();
		return service;
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		// TODO Auto-generated method stub
		return dao.insertBoard(boardVO);
	}

	@Override
	public List<BoardVO> selectBoardList() {
		// TODO Auto-generated method stub
		return dao.selectBoardList();
	}
	

}
