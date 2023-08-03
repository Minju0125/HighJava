package kr.or.ddit.memberList.service;

import java.util.List;

import kr.or.ddit.memberList.dao.IMemberDao;
import kr.or.ddit.memberList.dao.MemberDaoImpl;
import kr.or.ddit.memberList.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	private static MemberServiceImpl service;
	
	private IMemberDao dao;
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance() {
		if(service==null) service = new MemberServiceImpl();
		return service;
	}
	
	
	@Override
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}

	@Override
	public int insertMember(MemberVO memvo) {
		return dao.insertMember(memvo);
	}

}
