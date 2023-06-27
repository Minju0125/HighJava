package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {
		// 객체 생성
		Vector v1 = new Vector(); //collection 은 많은 양의 데이터(객체만) 저장 가능
		
		System.out.println("처음 크기 : " + v1.size());
		
// 데이터 추가하기 : add(추가할 데이터)
		// ==> 반환값 : 추가성공(true), 추가실패(false)
		// 리스트나 벡터에서는 추가 할 때 실패하는 경우가 거의 없음 (set에서는 있을수도?)
		
		v1.add("AAA");
		v1.add(new Integer(111)); // 데이터 111을 랩퍼 객체로 만든 것(이건 객체임)
		v1.add(123); // 일반데이터 ==> 객체화시키기 (박싱) ==> 오토박싱 기능이 있다. 
				// 오토 언박싱 기능도 있다.
		// collection은 객체만 저장할 수 있는데 어떻게 123이 저장? 
		// collection은 일반 데이터를 넣어주면 이 데이터를 객체화시킴 (내부적인 기능)
		
		v1.add('a');
		v1.add(true);
		boolean r = v1.add(3.14);
		
		System.out.println("현재 크기 : " + v1.size());
		System.out.println("반환값 r => " + r);
		System.out.println("------------------------------------------------");
		
//데이터 추가하기2 : addElement(추가할 데이터) ==> 벡터의 초기 버전 추가용 메서드
		v1.addElement("CCC");
		
		System.out.println("v1 => " + v1.toString());
		System.out.println("v1 toString 생략=> " + v1); // 출력할때는 투스트링 잘 안쓴다는데 왜?
		System.out.println("------------------------------------------------");
		
//데이터 추가하기 3 : add(index, 데이터)
		//	==> 'index'번째 '데이터'를 끼워 넣는다.
		//  ==> 'index'는 0부터 시작한다.
		v1.add(1, "KKK");
		System.out.println("v1 => " + v1); // 끼워넣으면 나머지 데이터가 한칸씩 뒤로 밀려남
		System.out.println("------------------------------------------------");
		
// 데이터 꺼내오기 : get(index)
		// ==> 'index'번째의 데이터를 꺼내서 반환한다.
		System.out.println("0번째 데이터 : " + v1.get(0));
		
		int iTemp = (int)v1.get(2); // 형변환해야함 
		// int iTemp = v1.get(2); // 형변환해야함 
			// 위에서 벡터만들때 여러가지 데이터타입으로 만들었음 -> 아무거나 다 저장하기 위해 object 타입으로 받음
			// 문자열 정수형 등 다양한 데이터를 사용했지만, 이걸 object로 받아서 저장했기 떄문에.
			// 부모 데이터를 자식쪽에 저장하려면 부모가 더 크기 때문에 강제형변환 시켜서 저장해줘야함
		// Integer iTemp = (Integer)v1.get(2);
		// int iTemp = (Integer)v1.get(2);
		System.out.println("2번째 데이터 : " + iTemp);
		System.out.println("------------------------------------------------");
		
//데이터 수정하기 : set(index, 새로운데이터)
		// ==> 'index'번째의 데이터를 '새로운 데이터'로 변경한다.
		// ==> 반환값 : 변경되기 전 데이터 (원래의 데이터)
		System.out.println("변경전 v1 => " + v1);
		String sTemp = (String)v1.set(0, "ZZZ");
		System.out.println("변경후 v1 => " + v1);
		System.out.println("반환값 sTemp => " +sTemp + "\n반환값은 변경되기 전 데이터 출력");
		System.out.println("------------------------------------------------");
		
//데이터 삭제하기 : remove(index)
			//=> 'index'번째의 데이터를 삭제한다.
		// ==> 반환값 : 삭제된 데이터
		v1.remove(0);
		System.out.println("삭제 후 v1 => " + v1);
		
		sTemp = (String) v1.remove(0);
		System.out.println("삭제 후 v1 => " + v1);
		System.out.println("삭제된 데이터 => " + sTemp);
		System.out.println("------------------------------------------------");
		
//데이터 삭제하기 2 : remove(삭제할데이터) // 오버로딩! -> 메소드 이름은 같은데 괄호안의 매개변수 타입이나 갯수만 다르게 사용하는 것
			//오버라이딩은 부모의 메소드 구조 똑같은 걸 가져다가 쓰는 것
		//==> '삭제할 데이터' 를 찾아서 삭제한다.
		// ** 벡터나 리스트에는 같은 데이터가 여러개 들어갈 수 있음
		//==> '삭제할 데이터'가 여러개이면 이중에 제일 첫번째 자료가 삭제된다.
		//==> 반환값 : 삭제 성공 (true), 삭제 실패(false)
		//==> 삭제할 데이터가 '정주형'이거나 'char형'일 경우에는 반드시 객체로 변환해서 사용해야한다.
		v1.remove("CCC");
		System.out.println("CCC 삭제 후 v1 => " + v1);
		
		/*System.out.println(v1.remove(123)); 이건 오류가 뜸 ! 
		 * => 123을 쓰면 인덱스로 인식 ! -> 123번째 배열에 데이터가 존재하지 않기 때문
		 v1.remove(123); 
		 이 메소드를 사용하면 괄호 속 값을 가지고 판단하는데, 지금 괄호 안의 데이터가 인티저라서 index 타입인 인티저를 사용하고 있는 remove(index) 여기로 감
		*/	
		
		//v1.remove(123); // 사용불가
		//v1.remove(new Integer(123)); // 자바 버전 1.9 이전
		v1.remove(Integer.valueOf(123)); //자바 버전 1.9 이상 
		/* v1.remove((Integer)123); 
		 * v1.remove(new Integer(123);
		 * v1.remove(Integer.valueOf(123);
		 * 이 셋의 차이?
		 */
		
		System.out.println("123 삭제 후 v1 => " + v1);

		// v1.remove('a'); 대문자 A 65 , 소문자 a 97 // 사용불가
		v1.remove(new Character('a'));
		System.out.println("a 삭제 후 v1 => " + v1);
		
		v1.remove(3.14);
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.remove(true);
		System.out.println("삭제 후 v1 => " + v1);
		
		System.out.println("------------------------------------------------");
		
//전체 데이터 삭제하기 : clear();
		v1.clear();
		System.out.println("clear 삭제 후 v1 => " + v1);
		System.out.println("------------------------------------------------");
		
		
/* 제네릭타입 (Generic Type)
		 * ==> 클래스 내부에서 사용할 데이터 타입을 객체 생성할 때 외부에서 지정해주는 기법으로,
		 *     객체를 선언할 때 <> 괄호 안에 그 객체의 내부에서 사용할 데이터 타입을 지정해주는 것을 말한다.
		 * 	- 이런식으로 선언하게 되면 그 데이터 타입 이외의 다른 종류의 데이터를 저장할 수 없다.	
		 *	- 제네릭으로 선언 될 수 있는 데이터 타입은 클래스형으로 지정해주어야한다.
		 *		(예 : int ==> Integer, boolean ==> Boolean, char => Character 등...)
		 *  - 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도이 형변환이 필요없다.							
		 */
		
		Vector<Integer> v2 = new Vector<Integer>();
		//Vector<Integer> v2 = new Vector<>(); 등호 오른쪽 괄호에는 데이터타입을 기술하지 않아도 왼쪽과 같은 데이터타입으로 선언됨.
		Vector<String> v3 = new Vector<String>();
		
		v3.add("안녕하세요");
		//v3.add(100); //오류 : 제네릭 타입에서 지정한 자료형과 다른 종류의 자료형은 저장할 수 없다.
		
		String sTemp2 = v3.get(0); // 형변환 없이 사용할 수 있다. -> 처음 선언할 때 데이터타입을 지정해주었기 때문에.
		
		Vector<Vector> vv = new Vector<Vector>(); //벡터안의 벡터
		Vector<Vector<Vector>> vvv = new Vector<Vector<Vector>> ();
		System.out.println("------------------------------------------------");
		
		//---------------------------------------------------------------------
		v3.clear();
		
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		
		Vector<String> v4 = new Vector<String> ();
		v4.add("BBB");
		v4.add("EEE");
		
		System.out.println("v3 => " + v3);
		System.out.println("v4 => " + v4);
		
		System.out.println("------------------------------------------------");
		
// 데이터 삭제하기 3 : removeAll(Collection 객체)
		//==> 전체 데이터 중에서 'Collection 객체'가 가지고 있는 모든 데이터를 삭제한다.
		//==> 반환값 : 작업성공(true), 작업실패(false)
		v3.removeAll(v4); //v3에는 ABCDE, v4에는 BE 들어있는데 v3 에서 v4 에 들어있는 데이터를 삭제

		System.out.println("v3 => " + v3);
		System.out.println("v4 => " + v4);
		
		
	}

}
