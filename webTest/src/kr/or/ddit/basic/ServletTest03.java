package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 - 서블릿 동작 방식
 	1. 사용자 (클라이언트)가 URL을 클릭하면 HTTP요청(Request)를 Servlet Container로 전송(요청)한다.
 	2. 컨테이너는 web.xml에 정의된 url 패턴을 확인하여 어느 서블릿을 통해 처리해야 할지를 검색한다.
 	   (로딩이 안된 경우에는 로딩을 한다. 처음 로딩시 init()메서드가 호출된다.)
 	   (Servlet 버전 3.0 이상에서는 애노테이션(@WebServlet)으로 설정할 수 있다.)
 	3. Servlet Container는 개별 요청을 처리할 쓰레드를 생성하여 해당 서블릿 객체의 service()메서드를 호출한다.
 	   (이 때 HttpServletRequest 객체와 HttpServletResponse 객체를 생성하여 파라미터로 넘겨준다.)
 	4. service() 메서드는 전송방식(GET,POST 등 메서드 타입)을 체크하여 적절한 메서드를 호출한다.
       (doGet(), doPost(), doPut(), doDelete() 등...)
    5. 요청 및 응답 처리가 모두 완료되면 HttpServletRequest객체와 HttpServletResponse객체는
              자동으로 소멸된다.
    6. Servlet Container로부터 서블릿이 제거되는 경우에는 destroy() 메서드가 호출된다.
 	      

 */

/**
 * Servlet implementation class ServletTest03
 */

//Servlet의 LiftCycle 예제
@WebServlet("/servletTest03.do")
public class ServletTest03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet : " + this.getServletName() + "에서 init() 메서드 호출하기 ...");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() 메서드 시작...");
	
	//GET 방식과 POST 방식에 맞는 메서드 호출하기
	
	//방법1 ==> 부모 클래스인 HttpServlet의 service() 메서드로 위임하기	
	//super.service(request, response);
		
	//방법2 ==> 클라이언트의 전송방식(GET, POST등)을 구분해서 직접 메서드 호춣하기
		String method=request.getMethod();//전송방식을 구하는 메서드
		System.out.println("methode="+ method);
		
		if("GET".equals(method)) {
			doGet(request, response);
		}else {
			doPost(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 메서드 시작...");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out= response.getWriter();
		out.println("<html><head><meta charset='utf-8'></head><body><h2 style='color:red;'>doGet()메서드의 처리결과입니다.</body></html>");
	
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 메서드 시작...");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out= response.getWriter();
		out.println("<html><head><meta charset='utf-8'></head><body><h2 style='color:blue;'>doPost()메서드의 처리결과입니다.</body></html>");
	}
	
	@Override
	public void destroy() {
		System.out.println("Servlet : " +this.getServletName() + "에서 destroy() 메서드 호출입니다.");
	
	}
}
