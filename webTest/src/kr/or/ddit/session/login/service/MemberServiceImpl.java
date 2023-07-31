package kr.or.ddit.session.login.service;

import kr.or.ddit.session.login.dao.IMemberDao;
import kr.or.ddit.session.login.dao.MemberDaoImpl;
import kr.or.ddit.session.login.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{
	
	private IMemberDao dao;
	
	private static MemberServiceImpl service;
	
	private MemberServiceImpl() {
		dao=MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance() {
		if(service==null) service = new MemberServiceImpl();
		return service;
	}

	@Override
	public MemberVO getMemberLogin(MemberVO memVo) {
		return dao.getMemberLogin(memVo);
	}

}
