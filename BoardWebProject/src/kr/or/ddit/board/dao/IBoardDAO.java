package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;

public interface IBoardDAO {

	public int insertBoard(BoardVO boardVO);

	public List<BoardVO> selectBoardList();
}
