package kr.or.ddit.memberList.dao;

import java.util.List;

import kr.or.ddit.memberList.vo.MemberVO;

public interface IMemberDao {
	/**
	 * 회원 정보를 모두 가져와서 출력
	 * @return List<MemberVO>
	 */
	public List<MemberVO> getAllMember();
	
	/**
	 * 회원정보 입력란 정보를 데이터베이스에 insert
	 * @param memvo
	 * @return
	 */
	public int insertMember(MemberVO memvo);
}
