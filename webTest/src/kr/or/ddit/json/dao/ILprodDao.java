package kr.or.ddit.json.dao;

import java.util.List;

import kr.or.ddit.vo.LprodVO;

public interface ILprodDao {
	/**
	 * 
	 * @param lprod_id
	 * @return 
	 */
	public List<LprodVO> getAllLprod();
}
