package kr.or.ddit.mvc.vo;

/*
	DB 테이블에 있는 컬럼을 기준으로 데이터를 객체화할 클래스
	
	DB 테이블의 '컬럼명'이 이 클래스의 '멤버변수명'이 된다.
	
	DB 테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행한다.

*/


public class MemberVO {
	
	//필수로 만들어줘야하는 것 : 1) 변수	2) getter, setter
	
	// 컬럼이름과 변수명을 똑같이 만들어주는 것이 일반적임
	// join을 하게되면 컬럼이 더 늘어날 수 있는데, 이 컬럼의 이름도 선언함.
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_tel;
	private String mem_addr;
	
	// 생성자를 만들어놓으면, 기본 생성자가 자동으로 만들어지지 않기 때문에,
	// VO 클래스에서 별도의 생성자를 만들 때에는 기본생성자도 만들어줘야함.
	
	// 기본 생성자가 있든 없든 상관 없는 경우도 있지만, DB에서 가져온 데이터를 자동으로 세팅해주는 경우가 있는데,
	// 나중에 필요한 경우가 있기 때문에 일단 기본생성자를 만들어줌
	
	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pass() {
		return mem_pass;
	}

	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_tel() {
		return mem_tel;
	}

	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}

	public String getMem_addr() {
		return mem_addr;
	}

	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}

	@Override
	public String toString() {
		return "MemberVO [mem_id=" + mem_id + ", mem_pass=" + mem_pass + ", mem_name=" + mem_name + ", mem_tel="
				+ mem_tel + ", mem_addr=" + mem_addr + "]";
	}
	
	
	
}
