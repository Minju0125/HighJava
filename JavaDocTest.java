package kr.or.ddit.basic;

//소스코드에 메뉴얼도 같이 작성하는 것 ==> 여기에 글씨를 쓰게 되면 컴파일러가 컴파일할 때 해석하면 안됨 !
// 작성한 코드중에 해석이 안되는 건 주석처리함
//------------------------------------
//[주석처리방법]
//JavaDoc 파일 만들기 예제
/*JavaDoc은 프로그램과 메뉴얼을 같이 만드는 방법입니다.*/

// 아래의 주석 안에는 html 태그를 쓸 수 있음

/**
 * 
 * @author PC-13
 * @version 1.0
 * 
 * <p>
 * 파일명 : JavDocTest.java<br>
 * 설   명 : JavaDoc문서 작성을 위한 연습용 interface<br><br>
 * 
 * 변경 이력<br>
 * ----------------------------------------<br>
 * 변경 일자 : 2023-07-13
 * 변  경  인 : 홍길동 <br>
 * 변경 내용 : 최초작성<br>  
 * ----------------------------------------<br>
 * </p>
 *
 */

public interface JavaDocTest {
	
	/**
	 * 메서드명 : methodTest<br>
	 * 설      명 : 반환값이 없는 메서드 <br>
	 * 
	 * @param a 첫번째 매개변수 (정수형)
	 * @param b 두번째 매개변수 (정수형)
	 */
	public void methodTest(int a, int b);
	
	/**
	 * 메서드명 : methodAdd<br>
	 * 설      명 : 반환값이 있는 메서드 <br>
	 *
	 * @param x 첫번째 정수형 매개변수
	 * @param y 두번째 정수형 매개변수
	 * @return 처리된 결과를 정수형으로 반환한다.
	 */
	public int methodAdd(int x, int y);
	
	/**
	 * 메서드명 : methodInput<br>
	 * 설      명 : 매개변수가 없는 메서드<br>
	 * 
	 * @return 처리가 완료된 정수형 데이터
	 */
	public int methodInput();
	
}
