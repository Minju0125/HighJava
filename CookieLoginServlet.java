package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieLoginServlet
 */
@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String checkbox = request.getParameter("checkbox");
		
		System.out.println(checkbox);

		
		if("test".equals(id) && "1234".equals(pass)) {
//			out.println("성공");
			//맞다면, 쿠키에 저장하고 cookieMain.jsp로 이동
			
			Cookie[] cookieArr = request.getCookies();
			if (cookieArr != null) {
				for (Cookie cookie : cookieArr) {
					String name = cookie.getName();
					if ("id".equals(name)) {
						cookie.setValue(id);
						response.addCookie(cookie);
					}
					
					if("checkbox".equals(checkbox)){
						cookie.setValue(checkbox);
						response.addCookie(cookie);
					}
					
				}
			}
				
			Cookie idCookie = new Cookie("id", id);
			Cookie checkboxCookie = new Cookie("checkbox", checkbox);
			response.addCookie(idCookie);
			response.addCookie(checkboxCookie);
			
			out.println("<a href='" + request.getContextPath() + "/cookie/cookieMain.jsp'>" + "시작 문서로 이동하기</a>");
			
		}else{
			//아니라면, 쿠키에 저장할 필요 없고, cookieLogin.jsp로 이동
			out.println("<a href='" + request.getContextPath() + "/cookie/cookieLogin.jsp'>" + "로그인 창으로 이동하기</a>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
