package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/forwardTest.do")
public class ForwardTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POST 방식의 인코딩 방식 설정
		request.setCharacterEncoding("utf-8");
		
		//파라미터로 전달되는 데이터 받기
		String userName = request.getParameter("username");
		
		// 이전 문서에서 SetAttribute() 메서드로 셋팅한 데이터 받기
		// 받을 때 형식 ) Request객체.getAttribute("key값")
		String tel = (String)request.getAttribute("tel"); //꺼내오면 Object 이고 이걸 String 에 넣으려면 형변환 해야함
		
		//응답처리 
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'><title>결과</title></head>");
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
