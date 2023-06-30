package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class StudentTest {

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
		ArrayList<Student> List = new ArrayList<>();
		List.add(new Student(1111, "박민주", 80, 90, 70));
		List.add(new Student(5555, "김민주", 90, 60, 90));
		List.add(new Student(7777, "이민주", 90, 90, 100));
		List.add(new Student(3333, "최민주", 70, 80, 85));
		List.add(new Student(9999, "강민주", 95, 70, 70));
		

		Iterator i = List.iterator();
		System.out.println("------------------------------ before ------------------------------");
		while (i.hasNext()) {
			System.out.println(i.next());
		}

		Collections.sort(List); // 학번 오름차순으로.

		Iterator j = List.iterator();
		System.out.println("------------------------------ 학번 오름차순 ------------------------------");
		while (j.hasNext()) {
			System.out.println(j.next());
		}

		Collections.sort(List, new NameSort());

		Iterator k = List.iterator();
		System.out.println("------------------------------ 학생이름 오름차순 ------------------------------");
		while (k.hasNext()) {
			System.out.println(k.next());

		}

	}
}

class Student implements Comparable<Student> {
	private int stdNo;
	private String stdName;
	private int scoreKorean;
	private int scoreEnglish;
	private int scoreMath;
	private int scoreSum;
	private int scoreRank;

	public Student(int stdNo, String stdName, int scoreKorean, int scoreEnglish, int scoreMath) {
		super();
		this.stdNo = stdNo;
		this.stdName = stdName;
		this.scoreKorean = scoreKorean;
		this.scoreEnglish = scoreEnglish;
		this.scoreMath = scoreMath;

	}

	public int getStdNo() {
		return stdNo;
	}

	public void setStdNo(int stdNo) {
		this.stdNo = stdNo;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public int getScoreKorean() {
		return scoreKorean;
	}

	public void setScoreKorean(int scoreKorean) {
		this.scoreKorean = scoreKorean;
	}

	public int getScoreEnglish() {
		return scoreEnglish;
	}

	public void setScoreEnglish(int scoreEnglish) {
		this.scoreEnglish = scoreEnglish;
	}

	public int getScoreMath() {
		return scoreMath;
	}

	public void setScoreMath(int scoreMath) {
		this.scoreMath = scoreMath;
	}

	@Override
	public String toString() {
		return "Student [stdNo=" + stdNo + ", stdName=" + stdName + ", scoreKorean=" + scoreKorean + ", scoreEnglish="
				+ scoreEnglish + ", scoreMath=" + scoreMath + ", scoreSum=" + scoreSum + ", scoreRank=" + scoreRank
				+ "]";
	}

	@Override
	public int compareTo(Student std) {
		if (this.getStdNo() > std.getStdNo()) {
			return 1;
		} else if (this.getStdNo() == std.getStdNo()) {
			return 0;
		} else {
			return -1;
		}
	}
}

class NameSort implements Comparator<Student> {

	@Override
	public int compare(Student std1, Student std2) {
		return std1.getStdName().compareTo(std2.getStdName());
	}

}
