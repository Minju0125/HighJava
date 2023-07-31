package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionDelete.do")
public class SessionDelete extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//Session 삭제하기
		
		//1. Session 객체를 생성하거나 현재 세션 가져오기
		HttpSession session = request.getSession();
		
		//2. Session 삭제
		
		//	1) Session 자체는 삭제되지 않고 개별적인 'session데이터' 삭제하기
		//		형식) Session객체.removeAttribue("삭제할 key값");
//		session.removeAttribute("testSession"); //개별적으로 testSession 만 삭제
		
		// 2) Session 자체를 삭제하기
		// 형식) Session객체.invalidate();
		session.invalidate(); //자체를 삭제되고, 메인화면으로 돌아가서 새로 세션을 확인할 떄 getsession메소드를 통해 확인하는데.
		//세션이 없으면 그 때 새로 만들기 때문에 새로운 세션 id가 생기는거!!!
	
		out.println("<html><head><meta charset='utf-8'><title>Session 연습</title></head>");
		out.println("<body>");
		
		out.println("<h3>Session 데이터 삭제하기</h3>");
		
		out.println("<a href='" + request.getContextPath() + "/session/sessionTest.jsp'>시작문서로 이동하기</a>");
		
		out.println("</body></html>");
		

		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
