package kr.or.ddit.basic.args;

public class ArgsTest {

	/*
	 * - 가변형 인수 ==> 메서드의 인수의 개수가 호출될 때마다 다를 때 사용한다. 가변형 인수는 메서드 안에서는 배열로 처리된다.
	 */

	// 매개변수로 받은 정수들의 합계를 구하는 메소드를 작성하시오.
	// (단, 이 정수들의 개수는 상황에 따라 다르다)

	// static 안붙은 (인스턴스 메소드)를 호출하려면 소속되어있는 클래스의 객체를 만들어서
	// 해당 메소드를 호출해야함.

	// 배열을 이용한 경우
	public int sumArr(int[] data) {
		int sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}

		return sum;
	}

	// 가변형 인수를 이용한 경우
	// data는 이 메소드 안에서 배열로 처리됨
	public int sumArg(int... data) {
		int sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	// 가변형 인수와 일반 적인 인수를 같이 사용할 경우에는
	// 가변형 인수를 제일 뒤쪽에 배치해야 한다.
	public String sumArgs(String name, int ...data) {
		int sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return name + "씨의 합계 : " + sum;
	}
	

	public static void main(String[] args) {
		ArgsTest at = new ArgsTest();

		int[] nums = { 100, 200, 300 };

//		int[] nums2; //선언하고
//		nums2= {1,2,3,4,5}; // 나중에 초기화할때는 이렇게 못씀

		int[] nums3 = new int[] { 1, 2, 3, 4, 5 };

		int[] nums4;
		nums4 = new int[] { 1, 2, 3, 4, 5 };

		System.out.println(at.sumArr(nums));
		System.out.println(at.sumArr(new int[] { 1, 2, 3, 4, 5 })); // 1,2,3,4,5를 배열에 넣어서 보내기
		System.out.println();

		System.out.println(at.sumArg(100,200,300));
		System.out.println(at.sumArg(1,2,3,4,5));
		System.out.println();
		
		System.out.println(at.sumArgs("박민주", 1,2,3,4,5));
	}

}
