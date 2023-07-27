package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.vo.BoardVO;

public interface IBoardService {

	public int insertBoard(BoardVO boardVO);

	public List<BoardVO> selectBoardList();
}
