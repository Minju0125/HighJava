package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class StudentTest {

	// 등수를 구하는 메소드
	public void createRank(List<Student> list) {
		// 등수를 구할 대상은 메인메소드의 List에 들어있음 -> 매개변수로 List를 받아와야함.
		for (Student std1 : list) { // 기준 데이터 구하기 위한 반복문
			int rank = 1; // 처음에는 1등으로 설정해놓고 시작한다.

			for (Student std2 : list) { // 비교대상을 나타내는 반복문
				if (std1.getTot() < std2.getTot()) { // 나보다 비교대상의 총점이 더 크면 rank 가 하나씩 밀려남
					rank++; // rank 값 증가
				}
			}

			std1.setScoreRank(rank); // 구해진 등수를 Student 객체의 rank 변수에 저장한다.

		}

	}

	/*
	 * 내부, 외부 정렬 기준 구현 등수는 StudentsTest에서(List에 데이터가 다 들ㅇ간 다음에) 과제로 올리기~(이름 폴더 만들어서)
	 * 
	 * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다. 이 클래스의 생성자에서는
	 * 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 처리를 한다.
	 * 
	 * 이 Student객체는 List에 저장하여 관리한다.
	 * 
	 * List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고, 총점의 역순으로 정렬하는데 총점이 같으면
	 * 이름의 오름차순으로 정렬이 되는 외부 정렬 기준 클래스를 작성하여 정렬된 결과를 출력하시오.
	 * 
	 * (단, 등수는 List에 전체 데이터가 추가된 후에 구해서 저장되도록 한다.)
	 * 
	 */

	public static void main(String[] args) {
		System.out.println("■■■ 학번의 오름차순으로 정렬 (내부정렬) ■■■");
		List<Student> List = new ArrayList<Student>();
		List.add(new Student(1, "박민주", 80, 90, 70));
		List.add(new Student(3, "김민주", 90, 60, 90));
		List.add(new Student(7, "이민주", 96, 90, 100));
		List.add(new Student(5, "최민주", 70, 80, 85));
		List.add(new Student(9, "강민주", 95, 70, 73));
		List.add(new Student(4, "김이박", 95, 88, 70));
		List.add(new Student(2, "이몽룡", 75, 55, 70));
		List.add(new Student(6, "이순신", 99, 47, 60));
		List.add(new Student(8, "홍길동", 95, 70, 96));

		StudentTest test = new StudentTest();

		test.createRank(List); // static 객체를 생성하지 않고도 실행되는 정적 메소드 -> static 안붙은건 instance 메소드(instance(객체)를
								// 통해서만 실행되는 메소드) -> 객체를 생성해서 실행됨
								// StudentTest 테스트 객체를 만들어야 실행할 수 있음.

		System.out.println("------------------------------ before ------------------------------");
		for (Student std : List) {
			System.out.println(std);
		}
		Collections.sort(List); // 학번 오름차순으로 정렬하기 (내부정렬)

		System.out.println("------------------------------ 학번 오름차순 ------------------------------");
		for (Student std : List) {
			System.out.println(std);
		}

		Collections.sort(List, new SortByTotal());

		System.out.println("------------------------------ 학생이름 오름차순 ------------------------------");
		for (Student std : List) {
			System.out.println(std);
		}

	}
}

class Student implements Comparable<Student> {
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int tot;
	private int scoreRank;

	public Student(int num, String name, int kor, int eng, int math) {
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.tot = kor + eng + math;

	}

	public int getNum() {
		return num;
	}

	public void setnum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public int getkor() {
		return kor;
	}

	public void setkor(int kor) {
		this.kor = kor;
	}

	public int geteng() {
		return eng;
	}

	public void seteng(int eng) {
		this.eng = eng;
	}

	public int getmath() {
		return math;
	}

	public void setmath(int math) {
		this.math = math;
	}

	public int getTot() {
		return tot;
	}

	public void settot(int tot) {
		this.tot = tot;
	}

	public int getScoreRank() {
		return scoreRank;
	}

	public void setScoreRank(int scoreRank) {
		this.scoreRank = scoreRank;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", tot=" + tot + ", scoreRank=" + scoreRank + "]";
	}

	@Override
	public int compareTo(Student std) {
		return Integer.compare(this.num, std.getNum());
	}
}

//총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬 기준
class SortByTotal implements Comparator<Student> {

	@Override
	public int compare(Student std1, Student std2) {
		if (std1.getTot() == std2.getTot()) {
			return std1.getName().compareTo(std2.getName());
		} else {
			return Integer.compare(std1.getTot(), std2.getTot() * -1); // 역순이니까 -1 곱함
		}

	}

}
