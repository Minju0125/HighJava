package kr.or.ddit.json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.json.service.ILprodService;
import kr.or.ddit.json.service.LprodServiceImpl;
import kr.or.ddit.vo.LprodVO;

/**
 * Servlet implementation class LprodList2
 */
@WebServlet("/lprodList2.do")
public class LprodList2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		ILprodService service = LprodServiceImpl.getInstance();

		// DB에서 원하는 작업을 수행한다. (Service 객체 이용)
		List<LprodVO> lprodList = service.getAllLprod();

		// 작업 결과를 Request객체에 저장한다.
		request.setAttribute("lprodList", lprodList);

		// View 페이지로 forwarding 한다.
		// getRequestDispatcher() 을 통해 forwarding 방식 => 제어권이 완전히 jsp로 이동. 다시 서블릿으로 돌아오지 않음
		// => 서블릿에서 PrintWriter객체를 사용할 일이 없다.
		request.getRequestDispatcher("/json/lprodView.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
