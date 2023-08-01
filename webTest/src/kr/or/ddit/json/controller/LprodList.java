package kr.or.ddit.json.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import kr.or.ddit.json.service.LprodServiceImpl;
import kr.or.ddit.vo.LprodVO;

//db에서 lprod에 있는 테이블을 가져와서 json으로 변환해서 보내주기

@WebServlet("/lprodList.do")
public class LprodList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
//		ILprodService service = LprodServiceImpl.getInstance();
//		List<LprodVO> list = service.getAllLprod();
		
		List<LprodVO> list = LprodServiceImpl.getInstance().getAllLprod();
//		String jsonData = gson.toJson(list);
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(list);
		
		PrintWriter out = response.getWriter();
		out.write(jsonData);
		response.flushBuffer();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
