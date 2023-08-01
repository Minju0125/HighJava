package kr.or.ddit.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/jsonController.do")
public class JsonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// JSON 형태의 응답을 보낼 때의 ContextType 설정
		response.setContentType("application/json; charset=utf-8");

		String choice = request.getParameter("choice");

		Gson gson = new Gson();

		String jsonData = null; // JSON 형태로 변환된 문자열이 저장될 변수

		switch (choice) {
		case "str":
			String str = "안녕하세요 문자열 응답입니다."; // 이 데이터를 json으로 변환해서 보냄

			// json 문자열로 변환하기
			jsonData = gson.toJson(str);
			break;
		case "array":
			int[] arr = { 10, 20, 30, 40, 50 };
			jsonData = gson.toJson(arr);
			break;
		case "object":
			SampleVO samVo = new SampleVO(10, "홍길동");
			jsonData = gson.toJson(samVo);
			break;
		case "list":
			ArrayList<SampleVO> list = new ArrayList<SampleVO>();
			list.add(new SampleVO(100, "강감찬"));
			list.add(new SampleVO(200, "이순신"));
			list.add(new SampleVO(300, "성춘향"));
			list.add(new SampleVO(400, "이몽룡"));
			jsonData = gson.toJson(list);
			break;
		case "map":
			HashMap<String, String>map = new HashMap<String, String>();
			map.put("name", "이순신");
			map.put("tel", "010-1234-5678");
			map.put("addr", "대전시 중구 오류동");
			jsonData = gson.toJson(map);
			break;
		}

		System.out.println("jsonData => " + jsonData);

		// JSON 문자열을 응답으로 보낸다.
		PrintWriter out = response.getWriter();
		out.write(jsonData);
		response.flushBuffer();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
