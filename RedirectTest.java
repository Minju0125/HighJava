package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RedirectTest
 */
@WebServlet("/redirectTest.do")
public class RedirectTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//파라미터로 전달되는 데이터 받기
		String userName = request.getParameter("username");
		String tel = request.getParameter("mytel");
		
		// 이전 문서에서 SetAttribute() 메서드로 셋팅한 데이터 받기
		// 받을 때 형식 ) Request객체.getAttribute("key값")
		//String tel = (String)request.getAttribute("tel"); //꺼내오면 Object 이고 이걸 String 에 넣으려면 형변환 해야함
		
		//응답처리 
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'><title>Redirect 연습</title></head>");
		out.println("<body>");
		
		out.println("<h3>Forward 이동 결과</h3>");
		out.println("<table border ='1'>");
		out.println("<tr><td>이 름</td>");
		out.println("<td>" + userName + "</td></tr>");
		out.println("<tr><td>전 화</td>");
		out.println("<td>" + tel + "</td></tr>");
		out.println("</table>");
		
		out.println("</body></html>");

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	};

}
