package kr.or.ddit.json.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.LprodVO;

public class LprodDaoImpl implements ILprodDao{
	
	private static LprodDaoImpl instance;
	
	private LprodDaoImpl() {}
	
	public static LprodDaoImpl getInstance() {
		if(instance==null) instance = new LprodDaoImpl();
		return instance;
	}

	
	@Override
	public List<LprodVO> getAllLprod() {
		SqlSession session = null;
		List<LprodVO> list= null;
		try {
			session = MybatisUtil.getSqlSession();
			 list = session.selectList("lprod.getAllLprod");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
		
		return list;
	}

}
