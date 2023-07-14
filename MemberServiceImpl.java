package kr.ddit.mvc.service;

import java.util.List;

import kr.ddit.mvc.dao.IMemberDao;
import kr.ddit.mvc.dao.MemberDaoimpl;
import kr.ddit.mvc.vo.MemberVO;

//서비스는 다오에게 일을 시키고 결과를 받아오면, 그대로 컨트롤러에게 전달해줌

public class MemberServiceImpl implements IMemberService {
	private IMemberDao dao; // DAO객체가 저장될 변수

	public MemberServiceImpl() {
		dao = new MemberDaoimpl(); // Dao객체 생성
	}

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

}
