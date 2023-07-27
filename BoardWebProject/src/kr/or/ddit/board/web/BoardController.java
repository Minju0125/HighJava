package kr.or.ddit.board.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
import sun.print.resources.serviceui_de;

//보드로 시작하는 모든 요청은 여기로 들어옴
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	
	private IBoardService service = BoardServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청 URI를 얻어온다.
		String requestUri = request.getRequestURI();
		//ContextPath를 얻어온다.
		//ContextPath란 요청시 Root 를 뜻한다.
		String contextPath = request.getContextPath();
		//ContextPath를 제외한 나머지 URI를 command라고 지칭한다.
		String command = requestUri.substring(contextPath.length());
		
		System.out.println(requestUri);				//	/BoardWebProject/board/list.do
		System.out.println(contextPath);			//	/BoardWebProject
		System.out.println(command);				//	/board/list.do
		
		request.setCharacterEncoding("UTF-8");
		
		String goPage =""; //이동할 페이지를 담을 공간
		
		//이동할 페이지 설정
		if(command.equals("/board/list.do")) {
			boardList(request);
			goPage = "/WEB-INF/views/board/list.jsp";
		}else if(command.equals("/board/form.do")) {
			goPage = "/WEB-INF/views/board/form.jsp";
		}
		
		//어떤 형식으로 페이지 이동방식을 선택하여 이동할건지 결정 ! -> 기본적인 페이지를 리턴하는 요청
		//forward요청
		RequestDispatcher rd = request.getRequestDispatcher(goPage);
		rd.forward(request, response);
		
		
		
	}

	private void boardList(HttpServletRequest request) {
		List<BoardVO> boardList =  service.selectBoardList();
		//값을 담아서 보내줘야함 - 페이지 이동 방식 forward - request안에 담아서 보내는 방식이기 때문에 
		///////////////
		request.setAttribute("boardList", boardList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//요청 URI를 얻어온다.
				String requestUri = request.getRequestURI();
				//ContextPath를 얻어온다.
				//ContextPath란 요청시 Root 를 뜻한다.
				String contextPath = request.getContextPath();
				//ContextPath를 제외한 나머지 URI를 command라고 지칭한다.
				String command = requestUri.substring(contextPath.length());
				
				System.out.println(requestUri);				//	/BoardWebProject/board/list.do
				System.out.println(contextPath);			//	/BoardWebProject
				System.out.println(command);				//	/board/list.do
				
				request.setCharacterEncoding("UTF-8");
				
				String goPage =""; //이동할 페이지를 담을 공간
				
				if(command.equals("/board/insert.do")) {
					//게시글 등록 처리 이벤트
					int cnt = boardInsert(request);
					if(cnt>0) { //등록 성공
						//페이지 이동 방식 선택해야함
						//페이지 이동방식: 리다이렉트
						response.sendRedirect(contextPath + "/board/list.do");
					}else { //등록실패
						request.getRequestDispatcher("/WEB-INF/views/board/form.jsp").forward(request, response);
					}
				}
				
	}

	private int boardInsert(HttpServletRequest request) {
		String title = request.getParameter("bo_title"); //제목
		String content = request.getParameter("bo_content"); //내용
		
		BoardVO boardVO = new BoardVO();
		boardVO.setBo_title(title);
		boardVO.setBo_content(content);
		boardVO.setBo_writer("a001");
		
		return service.insertBoard(boardVO);
	}
}
