package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemberVO;


//인터페이스에는 클래스에서 꼭 구현해야하는 메소드만 들어있다.
//따라서 인터페이스를 implement 할 때에는, 안에 있는 모든 메소드들을 재정의하게 된다
//인터페이스를 재정의하지 않으면 추상메소드인것

/**
 * Service 객체은 DAO에 만들어진 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고 받아온 결과 자료를 Controller에게
 * 보내주는 역할을 한다.
 * 
 * 보통은 Service의 메소드 구조는 DAO 의 메서드 구조과 같게 작성한다.
 * 
 * @author PC-13
 *
 */
public interface IMemberService {

	/**
	 * MemberVO 객체를 인수값으로 받아서 MemberVO 에 저장된 데이터를 DB에 insert 하는 메서드
	 * 
	 * @param memVO insert할 데이터가 저장된 MemberVO 객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int insertMember(MemberVO memVO);// 보내야할 매개변수가 여러개라면 이 데이터들을 한데 뭉쳐서 보내는 방법이 좋음 -> Colleciton 사용 (Map, List 등)

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
	public int updateMember2(Map<String, String> paraMap);


}
