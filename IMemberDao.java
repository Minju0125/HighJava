package kr.ddit.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.ddit.mvc.vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서 Service에게 전달하는 DAO의 interface
 * 
 * 메서드 하나가 DB와 관련된 작업 1개를 수행하도록 작성한다.
 * 
 * @author PC-13
 *
 */

public interface IMemberDao {

	/**
	 * MemberVO 객체를 인수값으로 받아서 MemberVO 에 저장된 데이터를 DB에 insert 하는 메서드
	 * 
	 * @param memVO insert할 데이터가 저장된 MemberVO 객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int insertMember(MemberVO memVO); // 보내야할 매개변수가 여러개라면 이 데이터들을 한데 뭉쳐서 보내는 방법이 좋음 -> Colleciton 사용 (Map, List 등
												// !)

	/**
	 * 회원 ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * 
	 * @param memId
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int deleteMember(String memId);

	/**
	 * MemberVO 자료를 이용하여 DB에 update하는 메서드
	 * 
	 * @param memVo update할 회원 정보가 저장된 MemberVO객체
	 * @return 작업성공 : 1, 작업 실패 : 0
	 */
	public int updateMember(MemberVO memVo);

	/**
	 * DB의 전체 회원 정보를 가져와 List에 담아서 반환하는 메소드
	 * 
	 * @return MemberVO 객체가 저장된 List
	 */
	public List<MemberVO> getAllMember();

	/**
	 * 회원 아이디를 매개변수로 받아서 해당 회원아이디의 개수를 반환하는 메서드
	 * 
	 * @param memId 검색할 회원 아이디
	 * @return 검색된 회원 아이디 개수
	 */
	public int getMemberCount(String memId);

	/**
	 * 회원ID, 수정할 컬럼명, 수정할 데이터를 갖는 Map객체를 매개변수로 받아서\
	 * 회원정보를 수정하는 메서드
	 * (key 값 정보 ==> 회원 ID(memId), 수정할컬럼명(field), 수정할 데이터(data)
	 * 
	 * @param paraMap 수정할 회원 정보가 저장된 Map 객체
	 * @return 작업성공 : 1, 작업 실패 : 0
	 */
	public int updateMember2(Map<String, String> paraMap); // dao에 저장할 때는 되도록이면 매개변수 한개로 //

	
	
}
