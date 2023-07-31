package kr.or.ddit.session.login.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.session.login.vo.MemberVO;
import kr.or.ddit.util.MybatisUtil;

public class MemberDaoImpl implements IMemberDao {
	private static MemberDaoImpl dao;
	
	private MemberDaoImpl() {}
	
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		return dao;
	}
	
	@Override
	public MemberVO getMemberLogin(MemberVO memVo) {
		SqlSession session = null;
		MemberVO LoginMemVo = null;
	
		
		try {
			session = MybatisUtil.getSqlSession();
			LoginMemVo = session.selectOne("member.getMemberLogin", memVo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return LoginMemVo;
	
	}

}
