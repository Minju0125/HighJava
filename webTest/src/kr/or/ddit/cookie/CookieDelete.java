package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieDelete.do")
public class CookieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//쿠키 정보 삭제하기
		// - 쿠키 데이터의 삭제는 쿠키의 유지시간을 0으로 설정하는 방법을 사용한다.
		//	  쿠키의 유지시간은 쿠키를 저장하는 addCookie() 메서드를 호출하기 전에 해당
		//	  쿠키 객체의 setMaxAge() 메서드를 사용해서 설정한다.
		//	 형식) cookie변수.setMaxAge(유지시간); // 유지시간은 '초'단위로 지정한다.
		
		Cookie[] cookieArr = request.getCookies();
		
		out.println("<html><head><meta charset='utf-8'><title>쿠키 정보 삭제하기</title></head>");
		out.println("<body>");
		
		out.println("<h3>저장된 쿠키 정보 확인하기</h3>");
		
		if(cookieArr == null || cookieArr.length == 0) {
			out.println("<h3>저장된 쿠키가 하나도 없습니다.</h3>");
		}else {
			//쿠키 배열에서 삭제할 쿠키 정보를 찾는다.
			for(Cookie cookie : cookieArr) {
				String name = cookie.getName(); // '쿠키이름' 가져오기
				// '쿠키이름'을 이용하여 삭제할 쿠키를 찾는다.
				if("gender".equals(name)) {
					// 찾은 쿠키의 유지시간을 0으로 설정하고 다시 저장한다.
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				
			}
		}
		
		out.println("<br>");
		
		out.println("<a href='" + request.getContextPath() + "/cookie/cookieTest01.jsp'>" + "시작 문서로 이동하기</a>");
	
		out.println("</body></html>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
