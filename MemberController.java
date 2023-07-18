package kr.ddit.mvc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.ddit.mvc.service.IMemberService;
import kr.ddit.mvc.service.MemberServiceImpl;
import kr.ddit.mvc.vo.MemberVO;

//컨트롤러가 서비스에 대한 정보를 알아야함

public class MemberController {
	private Scanner scan;
	private IMemberService service;

	// 생성자
	public MemberController() {
		scan = new Scanner(System.in);
		service = MemberServiceImpl.getInstance();

	}

	public static void main(String[] args) {
		new MemberController().startMember();
	}

	public void startMember() {
		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1: // 추가
				insertMember();
				break;
			case 2: // 삭제
				deleteMember();
				break;
			case 3: // 수정
				updateMember();
				break;
			case 4: // 전체 출력
				displayAllMember();
				break;
			case 5: // 수정2
				updateMember2();
				break;
			case 0:
				System.out.println("회원 관리 작업을 마칩니다...");
				return;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요...");
			}

		}
	}

	private void updateMember2() {
		Connection conn = null;
		PreparedStatement pstmt = null;

		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String memId = scan.next();

		int count = service.getMemberCount(memId);
		if (count == 0) { // 없는 회원이면...
			System.out.println(memId + "은(는) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 종료합니다...");
			return;
		}

		int num; // 수정할 항목 번호가 저장될 변수
		String updateField = null; // 수정할 컬럼명이 저장될 변수
		String updateTitle = null; // 변경할 데이터를 입력할 때 출력할 메시지가 저장될 변수
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요...");
			System.out.println("1.비밀번호   2.회원이름    3.전화번호    4.회원주소");
			System.out.println("----------------------------------------------------");
			System.out.print("수정 항목 선택 >> ");
			num = scan.nextInt();

			switch (num) {
			case 1:
				updateField = "mem_pass";
				updateTitle = "비밀번호";
				break;
			case 2:
				updateField = "mem_name";
				updateTitle = "회원이름";
				break;
			case 3:
				updateField = "mem_tel";
				updateTitle = "전화번호";
				break;
			case 4:
				updateField = "mem_addr";
				updateTitle = "회원주소";
				break;
			default:
				System.out.println("수정 항목을 잘못 선택 했습니다...");
				System.out.println("다시 선택 하세요...");
			}

		} while (num < 1 || num > 4);

		scan.nextLine(); // 버퍼 비우기
		System.out.println();
		System.out.print("새로운 " + updateTitle + " >> ");
		String updateData = scan.nextLine();

		// 수정할 정보를 Map에 추가한다.
		Map<String, String> paraMap = new HashMap<String, String>();

		paraMap.put("memid", memId);
		paraMap.put("field", updateField);
		paraMap.put("data", updateData);

		int cnt = service.updateMember2(paraMap);

		if (cnt > 0) {
			System.out.println(memId + " 회원 정보 수정 완료!!!");
		} else {
			System.out.println(memId + "회원 정보 수정 실패!!!");
		}

	}

	private void displayAllMember() {
		System.out.println();

		// Service 객체를 통해서 전체 회원 목록을 가져온다.
		List<MemberVO> memList = service.getAllMember();

		System.out.println();
		System.out.println("-------------------------------------------------------------");
		System.out.println(" ID      이름     비밀번호      전화번호         주소");
		System.out.println("-------------------------------------------------------------");

		if (memList == null || memList.size() == 0) {
			System.out.println("등록된 회원 정보가 하나도 없습니다..");
		} else {
			// List에 저장된 데이터 갯수만큼 반복해서 데이터를 출력한다.
			for (MemberVO memVo : memList) {
				String id = memVo.getMem_id();
				String name = memVo.getMem_name();
				String pass = memVo.getMem_pass();
				String tel = memVo.getMem_tel();
				String addr = memVo.getMem_addr();

				System.out.println(id + "\t" + name + "\t" + pass + "\t" + tel + "\t" + addr);

			}

		}
		System.out.println("-------------------------------------------------------------");

	}

	public void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String memId = scan.next();

		int count = service.getMemberCount(memId);
		if (count == 0) { // 없는 회원이면...
			System.out.println(memId + "은(는) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 종료합니다...");
			return;
		}

		System.out.println();
		System.out.println("새로운 비밀번호 >> ");
		String newMemPass = scan.next();

		System.out.println("새로운 회원이름 >> ");
		String newMemName = scan.next();

		System.out.println("새로운 전화번호 >> ");
		String newMemTel = scan.next();

		scan.nextLine(); // 버퍼 비우기
		System.out.println("새로운 회원주소 >> ");
		String newMemAddr = scan.next();

		// 수정할 데이터를 VO객체에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(newMemPass);
		memVo.setMem_name(newMemName);
		memVo.setMem_tel(newMemTel);
		memVo.setMem_addr(newMemAddr);

		int cnt = service.updateMember(memVo);

		if (cnt > 0) {
			System.out.println(memId + " 회원 정보 수정 완료!!!");
		} else {
			System.out.println(memId + "회원 정보 수정 실패!!!");
		}
	}

	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String memId = scan.next();

		int cnt = service.deleteMember(memId);

		if (cnt > 0) {
			System.out.println("회원ID가 " + memId + "인 회원 정보 삭제 완료!!! ");
		} else {
			System.out.println("회원 정보 삭제 실패~~~");
		}
	}

	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");

		int count;
		String memId = null; // 회원ID가 저장될 변수
		do {
			System.out.print("회원ID >> ");
			memId = scan.next();
			count = service.getMemberCount(memId);

			if (count > 0) {
				System.out.println(memId + "는(은) 이미 등록된 회원ID 입니다...");
				System.out.println("다른 회원ID를 입력하세요..");
				System.out.println();
			}

		} while (count > 0);

		System.out.print("비밀번호 >> ");
		String memPass = scan.next();

		System.out.print("회원이름 >> ");
		String memName = scan.next();

		System.out.print("전화번호 >> ");
		String memTel = scan.next();

		scan.nextLine(); // 입력 버퍼 비우기
		System.out.print("회원주소 >> ");
		String memAddr = scan.nextLine();

		// 입력 받은 insert할 데이터를 VO 객체에 저장한다.
		MemberVO memVo = new MemberVO();

		memVo.setMem_id(memId);
		memVo.setMem_pass(memPass);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);

		// Service의 insert 하는 메서드를 호출한다. 이 때 insert할 데이터를 인수값으로 넣어준다.
		int cnt = service.insertMember(memVo);

		if (cnt > 0) {
			System.out.println(memId + "회원 정보 insert 성공 !!!");
		} else {
			System.out.println(memId + "회원 정보 insert 실패~~");
		}
	}

	private int displayMenu() {
		System.out.println();
		System.out.println("------------------------");
		System.out.println("   1. 자료 추가");
		System.out.println("   2. 자료 삭제");
		System.out.println("   3. 자료 수정");
		System.out.println("   4. 전체 자료 출력");
		System.out.println("   5. 자료 수정2");
		System.out.println("   0. 작업 끝.");
		System.out.println("------------------------");
		System.out.print("작업 번호 입력 >> ");
		return scan.nextInt();
	}

}
