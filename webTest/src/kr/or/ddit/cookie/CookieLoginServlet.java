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

		request.setCharacterEncoding("utf-8");
		
		//uesrid, userpass, chekid 받기
		
		String userId = request.getParameter("userid");
		String pass = request.getParameter("userpass");
		
		String chkid = request.getParameter("chkid"); //체크박스는 체크된 것만 넘어감 => 체크안하면 null
		
		//userId를 쿠키값으로 갖는 Cookie 객체 생성 (쿠키이름: USERID)
		Cookie userCookie = new Cookie("USERID", userId);
		System.out.println("체크박스의 체크 여부 : " + chkid);
		
		//체크박스의 체크 여부에 따라 쿠키 저장 확인
		if(chkid!=null) { //체크박스가 체크 되었을 때
			response.addCookie(userCookie);
		}else {
			userCookie.setMaxAge(0);
			response.addCookie(userCookie);
		}
		
		if("test".equals(userId) && "1234".equals(pass)) {
			response.sendRedirect(request.getContextPath() + "/cookie/cookieMain.jsp");
		}else {
			response.sendRedirect(request.getContextPath() + "/cookie/cookieLogin.jsp");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
