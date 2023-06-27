package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MapTest {
	public static void main(String[] args) {
		/*
		 * Map => key 값과 value 값을 한 쌍으로 관리하는 객체 -key 값은 중복을 허용하지 않고 순서가 없다. (Set의 특징을
		 * 갖는다,) -value값은 중복을 허용한다.
		 */

		HashMap<String, String> map = new HashMap<String, String>();
		// 자료 추가 => put(key값, value값)

		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");

		System.out.println("map ==> " + map);

		// 자료 수정 ==> put() 메소드를 이용하여 자료를 추가하는데 'key값'이 같으면
		// 나중에 추가한 값이 저장된다.

		map.put("addr", "서울");
		System.out.println("map ==> " + map);

		// 자료 삭제
		// -remove(key값) ==> 'key값'이 같은 데이터를 찾아서 삭제한다.
		/*
		 * 반환값 ==> 삭제된 자료의 value값이 반환된다. String removeTel = map.remove("tel");
		 * System.out.println("map ==> " + map); System.out.println("removeTel ==> " +
		 * removeTel );
		 */

		// 자료 읽기 => get(key값) ==> 주어진 'key값'과 한쌍인 'value값'을 반환한다.
		System.out.println("이름 : " + map.get("name"));

		// key값이 존재하는지 여부 나타내는 메서드 ==> containsKey(key값)
		// ==> 주어진 'key'값이 있으면 true 없으면 false 반환
		System.out.println("tel 키값의 존재 여부 >> " + map.containsKey("tel"));
		System.out.println("tel 키값의 존재 여부 >> " + map.containsKey("no"));
		System.out.println();

		// Map에 저장된 모든 데이터를 차례로 읽어와 처리하기
		// 방법1) 모든 key값들을 읽어와서 처리하기 ==> keySet()메서드 이용하기
		// keySet()메서드 =>Map의 모든 key 값들을 Set 형으로 반환하는 메소드

		Set<String> keySet = map.keySet();

		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next(); // 데이터 (key값) 가져오기
			String value = map.get(key);
			System.out.println(key + " ==> " + value);
		}

		System.out.println();

		for (String key2 : keySet) {
			String value2 = map.get(key2);
			System.out.println(key2 + " : " + value2);
		}
		System.out.println();

		// 방법 2 ==> value 값만 읽어와 처리하기 ==> values()메서드 이용
		for (String value3 : map.values()) {
			System.out.println(value3);
		}
		System.out.println();

	}

}
