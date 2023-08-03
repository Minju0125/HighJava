package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
/*
	이 예제는 애노테이션(@webServlet)을 이용하여 Servlet을 설정하여 실행하는 예제이다.
	애노테이션(@WebServlet)은 Servlet 버전 3.0이상에서 사용할 수 있다.
*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	@WebServlet 애노테이션의 속성들
	1. name : 서블릿의 이름을 설정한다.( 기본값 : 빈문자열(""))
	2. urlPatterns : 서블릿의 URL 패턴의 목록을 설정한다. (여러개 지정 가능) (기본값 : 빈 배열 ({}))
		예) urlPatterns="/url1" 또는 urlPatterns={"/url1"} ==> url 패턴이 1개일 경우
		예) urlPatterns={"/url1", "/url2", ...} ==> url 패턴이 2개 이상일 경우
	3. value : urlPatterns 속성과 동일한 기능을 한다.
	4. description : 주석(설명글)을 설정한다.

*/

public class ServletTest02 extends HttpServlet {

	// doGet() 메서드 ==> GET 방식의 요청을 처리하는 메서드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8"); // 응답 문서의 인코딩 방식 지정
		response.setContentType("text/html; charset=utf-8"); // 응답문서의 ContentType 지정

		// 처리한 내용을 응답으로 보내기 위해 PrintWriter 객체를 생성한다.
		PrintWriter out = response.getWriter(); 
		
		// 처리한 내용을 출력한다.
		// 방법2) print(), println() 메서드 이용하기
		out.println("<html><head><meta charset='utf-8'><title>두번째 Servlet연습</title></head>");
		out.println("<body>");
		out.println("<h2 style='text-align:center;'>");
		out.println("안녕하세요. @WebServlet 애노테이션을 이용한 서블릿입니다.");
		out.println("</h2>");
		out.println("</body></html>");
		
	}

	// doPost() 메서드 ==> POST 방식의 요청을 처리하는 메서드
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
