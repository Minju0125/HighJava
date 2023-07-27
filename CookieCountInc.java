package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountInc.do")
public class CookieCountInc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		// 쿠키 이름 정하기 => "count"

		//전체 쿠키를 가져와서
		Cookie[] cookieArr = request.getCookies();

		int count = 0; // 읽어온 count 값이 저장될 변수

		// cookieArr중에서 count라는 쿠키가 있는지 검사
		// count라는 쿠키가 있으면 현재 count 값이 들어있을것이고, 그 값을 꺼내와야함
		if (cookieArr != null) {
			for (Cookie cookie : cookieArr) {
				String name = cookie.getName(); // 쿠키 이름 구하기
				if ("count".equals(name)) { // "count"라는 쿠키이름을 갖는 쿠키 찾기
					String value = cookie.getValue(); // 쿠키값 (현재의 count값) 구하기
					count = Integer.parseInt(value);
				}
			}
		}
		count++; // count 증가

		// 증가된 count가 저장된 쿠키 객체 생성
		Cookie countCookie = new Cookie("count", String.valueOf(count));
		//이름이 같으면 나중에 저장된 쿠키로 덮어쓰기
		
		response.addCookie(countCookie); //쿠키추가
		
		out.println("<html><head><meta charset='utf-8'><title>쿠키count연습</title></head>");
		out.println("<body>");
		
		out.println("<h3>어서오세요. 당신은 " + count + "번째 방문입니다.</h3>");
		out.println("<br><br><hr>");
		
		out.println("<a href='" + request.getContextPath() + "/cookieCountInc.do'>카운트 증가하기</a>");
		out.println("<a href='" + request.getContextPath() + "/cookie/cookieTest02.jsp'>시작문서로 이동하기</a>");
		
		out.println("</body></html>");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
