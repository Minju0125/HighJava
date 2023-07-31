package kr.or.ddit.session.login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.session.login.service.IMemberService;
import kr.or.ddit.session.login.service.MemberServiceImpl;
import kr.or.ddit.session.login.vo.MemberVO;

@WebServlet("/login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//GET 방식으로 요청하면 무조건 로그인 폼이 있는 페이지로 이동한다.
		//forward 방식으로 보냄
		RequestDispatcher rd = request.getRequestDispatcher("/session/db/login.jsp");
		rd.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//파라미터로 넘어 온 ID와 Password를 구한다.
		String userId = request.getParameter("userid");
		String pass = request.getParameter("userpass");
	
		//파라미터로 넘어온 값을 VO 객체에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(userId);
		memVo.setMem_pass(pass);

		//DB와 연동하여 처리하기 위해 Service객체를 생성한다. (MVC 패턴)
		IMemberService service = MemberServiceImpl.getInstance();
		
		//VO를 파라미터로 넘겨서 로그인 작업을 수행한다.
		MemberVO loginMemberVo = service.getMemberLogin(memVo);
		
		HttpSession session = request.getSession();
		
		if(loginMemberVo != null) { //로그인 성공
			session.setAttribute("loginMember", loginMemberVo);
		} 
		
		response.sendRedirect(request.getContextPath() + "/login.do");
			
		}
	}


