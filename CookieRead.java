package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieRead.do")
public class CookieRead extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 저장된 쿠키 읽어오기

		// 1. 전체 쿠키 정보를 Request 객체의 getCookies()메서드를 통해서 가져온다.
		//	    가져온 쿠키 정보는 배열에 저장된다.
		// 형식) Cookie[] 쿠키배열변수 = Request객체.getCookies();
		Cookie[] cookieArr = request.getCookies();
		
		out.println("<html><head><meta charset='utf-8'><title>쿠키 정보 읽기</title></head>");
		out.println("<body>");
		
		out.println("<h3>저장된 쿠키 정보 확인하기</h3>");
		
		if(cookieArr == null || cookieArr.length == 0) {
			out.println("<h3>저장된 쿠키가 하나도 없습니다.</h3>");
		}else {
			//2. 쿠키 배열에서 해당 쿠키 정보를 구해온다.
			for(Cookie cookie : cookieArr) {
				String name = cookie.getName();
				
				//'쿠키값' 가져오기 ==> 한글이 저장되었을 가능성이 있기 때문에
				// 쿠키값을 URLDecoder객체를 이용하여 디코딩해서 사용한다.
				String value = URLDecoder.decode(cookie.getValue(), "utf-8");
				
				out.println("쿠키이름 : " + name + "<br>");
				out.println("쿠키값 : " + value + "<hr>");
				
			}
		}
		out.println("<br>");
		out.println("<a href='" + request.getContextPath() + "/cookie/cookieTest01.jsp'>" + "시작 문서로 이동하기</a>");
		
		out.println("</body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

	}

}
