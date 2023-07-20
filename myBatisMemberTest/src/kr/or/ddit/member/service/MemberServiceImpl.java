package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;


//서비스는 다오에게 일을 시키고 결과를 받아오면, 그대로 컨트롤러에게 전달해줌

public class MemberServiceImpl implements IMemberService {
	
	private static MemberServiceImpl instance;
	
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance(); // Dao객체 생성
	}
	
	public static MemberServiceImpl getInstance() {
		if(instance == null) instance = new MemberServiceImpl();
		return instance;
	}
	
	//////////////////
	
	
	private IMemberDao dao; // DAO객체가 저장될 변수

	@Override
	public int insertMember(MemberVO memVO) {
		return dao.insertMember(memVO);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		return dao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}

	@Override
	public int getMemberCount(String memId) {
		return dao.getMemberCount(memId);
	}

	@Override
	public int updateMember2(Map<String, String> paraMap) {
		// TODO Auto-generated method stub
		return dao.updateMember2(paraMap);
	}

}
