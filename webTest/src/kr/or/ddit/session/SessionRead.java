package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionRead.do")
public class SessionRead extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		// 저장된 Session 정보 읽어오기

		// 1. Session 객체 생성 또는 현재 Session 가져오기
		HttpSession session = request.getSession();

		out.println("<html><head><meta charset='utf-8'><title>Session 연습</title></head>");
		out.println("<body>");

		out.println("<h2>저장된 Session 데이터 확인하기</h2>");

		out.println("<h3>세션 데이터 1개확인하기 </h3>");

		// 2. Session 객체의 getAttribute()메서드를 이용하여 저장된 'session 데이터'를 가져온다.
		// 형식) Session 객체.getAttribute("key값");
		// ==> 'key 값'은 setAttribute()메서드로 'session'데이터 저장할 때 사용한 'key값'이 같아야한다.
		String sessionValue = (String) session.getAttribute("testSession"); // object 로 반환
		if (sessionValue == null) { // 해당 키값에 저장된 세션이 없으면 널이기 때문에
			out.println("<h3>testSession의 세션값이 없습니다.</h3>");
		} else {
			out.println("testSession의 세션값 : " + sessionValue + "<br>");
		}


		out.println("<h3>전체 세션 데이터 확인하기</h3>");
		out.println("<hr>");
		out.println("<ul>");

		// 세션의 setAttribute()에 사용된 전체 key값들을 가져온다.
		Enumeration<String> sessionNames = session.getAttributeNames();
		int cnt = 0;
		while (sessionNames.hasMoreElements()) {
			cnt++; // 세션이 하나도 없으면 cnt 증가 안됨
			String sessionKey = sessionNames.nextElement();
			out.println("<li>" + sessionKey + " : " + session.getAttribute(sessionKey) + "</li>");
		}

		if (cnt == 0)
			out.println("<li>세션 데이터가 하나도 없습니다.</li>");
		
		out.println("</ul>");
		
		out.println("<hr>");
		out.println("<h3>Session 관련 정보 확인하기</h3>");
		
		//세션ID ==> 세션을 구분하기 위한 고유한 값
		out.println("세션 ID : " + session.getId() + "<br>");
		
		//세션 생성 시간 ==> 1970년1월1일부터 경과한 시간(밀리세컨드 단위)
		out.println("세션 생성 시간 : " + session.getCreationTime()+ "<br>");

		//세션 최근 접근 시간 ==> 
		out.println("세션 최근 접근 시간: " + session.getLastAccessedTime() + "<br>");
		
		//세션 유효 시간 ==> 초 단위 (세션이 유지되는 시간)
		//유효 시간 설정은 session.getMaxInactiveInterval(초 단위 시간)
		out.println("세션 유효 시간 : " +session.getMaxInactiveInterval() +"<br>");
		
		out.print("<hr>");
		
		out.println("<a href='" + request.getContextPath() + "/session/sessionTest.jsp'>시작문서로 이동하기</a>");

		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
