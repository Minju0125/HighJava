package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieAdd.do")
public class CookieAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//Cookie 저장하는 방법
		//1. Cookie 객체를 생성한다. ==> '쿠키이름'과 '쿠키값'을 문자열로 지정하여 생성한다.
		//		형식) Cookie cookie 변수 = new Cookie("쿠키이름", "쿠키값");
		//			==> 쿠키값에 한글을 사용할 경우에는 URLEncoder객체로 인코딩한 후 저장한다.
		//			==> 하나의 쿠키는 4KB(4096byte)까지 저장 가능 
		//			==> 하나의 도메인 당 20개까지 저장 가능
		
		Cookie nameCookie	= new Cookie("name", URLEncoder.encode("홍길동", "utf-8"));
		Cookie ageCookie = new Cookie("age", "20"); //문자열만 저장가능 하므로 숫자도 문자열로 저장해ㅑㅇ함
		Cookie genderCookie = new Cookie("gender", "Male");
		
		//2. Cookie 속성 설정
		// cookie변수.setPath("적용경로"); ==> 지정한 경로와 그 하위 경로에서 사용 가능하다.
		// 						       ==> 생략하면 쿠키를 설정할 당시의 경로가 설정된다.
		
		//cookie변수.setMaxAge(유지시간) ==> 쿠키가 유지될 시간(단위 : 초)
		//						     ==> 생략하면 -1 값을 갖는다.
		// 							 ==> (-1 브라우저가 종료될 떄까지 유지된다., 0: 즉시 삭제된다.)
		
		//cookie변수.setDomain("적용도메인명"); ==> 예) ".ddit.or.kr" --> www.ddit.or.kr, mail.ddit.or.kr, cafe.ddit.or.kr
		
		//cookie변수.setSecure(보안여부); ==> (true : 보안적용, false:보안미적용(기본값))
		
		//3. Response객체를 이용하여 쿠키를 웹브라우저로 보내면 웹브라우저로 보내면 웹브라우저가 이 쿠키 정보를 받아서 저장한다.
		// 형식) response객체.addCookie(1번에서 만든 Cookie객체)
		response.addCookie(nameCookie);
		response.addCookie(ageCookie);
		response.addCookie(genderCookie);
		
		//---------------------------------------------------------------------------
		
		out.println("<html><head><meta charset='utf-8'><title>쿠키저장</title></haed>");
		out.println("<body>");
		
		out.println("<h3>Cookie 데이터가 저장되었습니다.</h3><hr>");
		
		out.println("<a href='" + request.getContextPath() + "/cookie/cookieTest01.jsp'>" + "시작 문서로 이동하기</a>");
	
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
