package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.IJdbcBoardService;
import kr.or.ddit.board.service.JdbcBoardServiceImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardController {
	private Scanner scan;
	private IJdbcBoardService service;

	public JdbcBoardController() {
		scan = new Scanner(System.in);
		service = JdbcBoardServiceImpl.getInstance();

	}

	public static void main(String[] args) {
		new JdbcBoardController().boardStart();
	}

	// 시작 메서드
	public void boardStart() {
		String searchTitle = null;
		while (true) {
			int choice = displayMenu(searchTitle);
			
			switch (choice) {
			case 1:
				// 게시글 입력
				insertBoard();
				searchTitle = null;
				break;
			case 2:
				// 게시글보기
				viewBoard();
				searchTitle = null;
				break;
			case 3:
				//검색
				searchTitle = searchBoard(); //얘는 반환값이 있음
				break;
			case 0:
				System.out.println();
				System.out.println("게시판 프로그램 종료.....");
				return;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요.");
			}
		}

	}
	
	//검색할 제목을 입력받아 반환하는 메서드
	private String searchBoard() {
		scan.nextLine(); //버퍼비우기
		System.out.println();
		System.out.println("검색 작업");
		System.out.println("-------------------------------");
		System.out.println("검색할 제목 입력 >> ");
		return scan.nextLine();
	}
	
	
	//게시글 보기
	private void viewBoard() {
		System.out.println();
		System.out.println("보기를 원하는 게시글 번호 입력  >> ");
		int boardNo = scan.nextInt();

		JdbcBoardVO boardVO = service.getBoard(boardNo);

		if (boardVO == null) {
			System.out.println(boardNo + "번의 게시글이 존재하지 않습니다...");
			return;
		}

		System.out.println();
		System.out.println(boardNo + "번 글 내용");
		System.out.println("----------------------------------");
		System.out.println("- 제   목  : " + boardVO.getBoard_title());
		System.out.println("- 작성자  : " + boardVO.getBoard_writer());
		System.out.println("- 내   용  : " + boardVO.getBoard_content());
		System.out.println("- 작성일   : " + boardVO.getBoard_date());
		System.out.println("- 조회수   : " + boardVO.getBoard_cnt());
		System.out.println("----------------------------------");

		System.out.println("메뉴 : 1. 수정    2.삭제    3.리스트로 가기");
		System.out.println("작업선택 >> ");
		int num = scan.nextInt();

		switch (num) {
		case 1:
			updateBoard(boardNo);
			break;
		case 2:
			deleteBoard(boardNo);
			break;
		case 3:
			return;
		}
	}

	// 게시글 삭제
	private void deleteBoard(int boardNo) {
		int cnt = service.deleteBoard(boardNo);
		if (cnt > 0) {
			System.out.println(boardNo + "번 글이 삭제되었습니다...");

		} else {
			System.out.println(boardNo + "번 글의 삭제 작업 실패~~~");
		}
	}

	// 게시글 수정
	private void updateBoard(int boardNo) {
		System.out.println();
		scan.nextLine();// 입력버퍼 비우기
		System.out.println("수정작업하기");
		System.out.println("----------------------------------");
		System.out.println("제  목 : ");
		String newTitle = scan.nextLine();

		System.out.print("내  용 : ");
		String newContent = scan.nextLine();

		JdbcBoardVO boardVo = new JdbcBoardVO();
		boardVo.setBoard_no(boardNo);
		boardVo.setBoard_title(newTitle);
		boardVo.setBoard_content(newContent);

		int cnt = service.updateBoard(boardVo);

		if (cnt > 0) {
			System.out.println(boardNo + "번 글이 수정되었습니다...");
		} else {
			System.out.println(boardNo + "번 글의 수정 작업 실패~~~");
		}
	}

	// 새글 작성
	private void insertBoard() {
		System.out.println();

		scan.nextLine(); // 입력 버퍼 비우기
		System.out.println("새글 작성하기");
		System.out.println("----------------------------------");
		System.out.println("- 제  목 : ");
		String title = scan.nextLine();

		System.out.println("- 작성자 : ");
		String writer = scan.nextLine();

		System.out.println("- 내  용  : ");
		String content = scan.nextLine();

		// 입력한 값을 VO에 저장하기
		JdbcBoardVO boardVO = new JdbcBoardVO();
		boardVO.setBoard_title(title);
		boardVO.setBoard_writer(writer);
		boardVO.setBoard_content(content);

		int cnt = service.insertBoard(boardVO);

		if (cnt > 0) {
			System.out.println("새글이 추가되었습니다...");
		} else {
			System.out.println("새글 추가 실패!~");
		}

	}

	// 게시판 목록을 보여주고 메뉴를 나타내며 사용자가 입력한 작업 번호를 반환하는 메섣,
	public int displayMenu(String searchTitle) {
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println(" No \t 제 목 \t 작성자 \t 조회수   ");
		System.out.println("-------------------------------------");

		List<JdbcBoardVO> boardList = null;

		if (searchTitle == null) {
			// 검색할 제목이 없으면 이거
			boardList = service.getAllBoard();

		} else {
			boardList = service.getSearchBoard(searchTitle);

		}

		if (boardList == null || boardList.size() == 0) {
			System.out.println("  출력할 게시글이 하나도 없습니다...");
		} else {
			for (JdbcBoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "\t" + boardVo.getBoard_title() + "\t"
						+ boardVo.getBoard_writer() + "\t" + boardVo.getBoard_cnt());
			}
		}

		System.out.println("-------------------------------------");
		System.out.println("메뉴 : 1.새글작성    2.게시글보기    3.검색     0.작업 끝");
		System.out.println("작업선택 >> ");
		return scan.nextInt();
	}

}
