package kr.or.ddit.json.service;

import java.util.List;

import kr.or.ddit.json.dao.ILprodDao;
import kr.or.ddit.json.dao.LprodDaoImpl;
import kr.or.ddit.vo.LprodVO;

public class LprodServiceImpl implements ILprodService{

	private ILprodDao dao;
	private static LprodServiceImpl instance;
	
	private LprodServiceImpl() {
		dao = LprodDaoImpl.getInstance();
	}
	
	public static ILprodService getInstance() {
		if(instance==null) instance = new LprodServiceImpl();
		return instance;
	}
	
	
	@Override
	public List<LprodVO> getAllLprod() {
		return dao.getAllLprod();
	}
	
}
