package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionAdd.do")
public class SessionAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//Sesssion 정보 저장하는 방법
		
		//1. Session 객체를 생성하거나 현재의 세션 가져오기
		//형식) Request객체.getSession() 또는 Request객체.getSession(true);
				//getSession() -> 세션이 있으면 세션을 가져오고, 없으면 만들어라! 근데 괄호안의 매변수에 false이면 세션이 없어도 만들지 않음 
		//		==> 현재 세션이 존재하면 현재 세션을 반환하고 존재하지 않으면 새로운 세션을 생성한다.
		//형식) Request객체.getSession(false)
		//		==> 현재 세션이 존재하면 현재 세션을 반환하고 존재하지 않으면 null을 반환한다.
		
		HttpSession session = request.getSession();
		
		//2. Session 객체의 setAttribute()메서드를 이용하여 Session 값을 저장한다.
		//형식) Session객체.setAttribute("key값", session 데이터);
		//		'key값'은 문자열, 'session 데이터'는 자바의 모든 종류의 데이터
		session.setAttribute("testSession", "연습용 세션입니다...");
		session.setAttribute("userName", "홍길동");
		session.setAttribute("age", 30); //쿠키에서는 숫자를 문자열로 바꿔서 넣어야했는데, 세션은 모든 종류의 데이터 사용 가능 (숫자, 문자열, 객체,,......)
		
		out.println("<html><head><meta charset='utf-8'><title>Session 연습</title></head>");
		out.println("<body>");
		
		out.println("<h3>Session 데이터가 저장되었습니다...</h3>");
		out.println("<a href='" + request.getContextPath() + "/session/sessionTest.jsp'>시작문서로 이동하기</a>");
		
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
