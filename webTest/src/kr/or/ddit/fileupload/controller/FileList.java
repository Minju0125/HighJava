package kr.or.ddit.fileupload.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.fileupload.service.FileInfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileInfoService;
import kr.or.ddit.vo.FileInfoVO;

//DB에 저장된 파일 목록을 가져와 View 로 보내는 Servlet
@WebServlet("/fileList.do")
public class FileList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//service 객체 생성
		IFileInfoService service = FileInfoServiceImpl.getInstance();
		
		//정체 파일 목록을 가져와서 fileList에 담아준다.
		List<FileInfoVO> fileList= service.getAllFileInfo();
		
		//구해진 전체 파일 목록을 View 페이지로 보낸다.(포워딩 방식)
		request.setAttribute("fileList", fileList);
		
		request.getRequestDispatcher("/fileupload/fileList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
