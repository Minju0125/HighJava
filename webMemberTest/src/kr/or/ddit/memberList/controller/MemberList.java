package kr.or.ddit.memberList.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.memberList.service.IMemberService;
import kr.or.ddit.memberList.service.MemberServiceImpl;
import kr.or.ddit.memberList.vo.MemberVO;

@WebServlet("/memberList.do")
public class MemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//service 객체 생성해서 memberList에 List<MemberVO> 저장
		MemberServiceImpl service = MemberServiceImpl.getInstance();
		List<MemberVO> memberList = service.getAllMember();
		
		request.setAttribute("memberList", memberList);
		request.getRequestDispatcher("/memberList/memberList.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
