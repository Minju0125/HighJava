package kr.or.ddit.fileupload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.http11.filters.BufferedInputFilter;

import kr.or.ddit.fileupload.service.FileInfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileInfoService;
import kr.or.ddit.vo.FileInfoVO;

//파일을 다운로드 하는 서블릿
@WebServlet("/fileDownload.do")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 파라미터로 넘어온 파일번호를 구한다.
		String strFileNo = request.getParameter("fileno");
		int fileNo = Integer.parseInt(strFileNo);
		
		// 파일 번호를 이용하여 DB에 해당 파일 정보를 가져온다.
		IFileInfoService service = FileInfoServiceImpl.getInstance();
		FileInfoVO fvo = service.getFileInfo(fileNo);
		
		//업로드된 파일들이 저장될 폴더 설정
		String uploadPath = "d:/d_other/uploadFiles";
		
		//저장될 폴더가 없으면 새로 만들어 놓는다.
		File f = new File(uploadPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		response.setCharacterEncoding("utf-8");
		
		//다운받을 파일의 File 객체 생성 ==> 실제 저장된 파일명을 지정한다.
		File downFile = new File(f, fvo.getSave_file_name()); //f : 저장될 파일이 저장될 경로 객체
		
		if(downFile.exists()) { //해당 파일이 있으면...
			
			//Content Type 설정
			response.setContentType("application/octet-stream; charset=utf-8");
			
			//Response객체에 content-disposition 헤더를 설정한다.
			String headerKey = "content-disposition";
			
			//헤더의 value값으로는 다운로드 할 때 클라이언트에 저장될 파일 이름을 지정하는 곳으로
			//원래의 파일명으로 지정한다.
			String headerValue = "attachment; filename=\"" + fvo.getOrigin_file_name() + "\"" ;
			
			response.setHeader(headerKey, headerValue);
			
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			
			try {
				//출력용 스트림 객체 생성 ==> response객체 이용
				bout = new BufferedOutputStream(response.getOutputStream());
				
				//파일 입력용 스트림 객체 생성
				bin = new BufferedInputStream(new FileInputStream(downFile));
				
				byte[] temp = new byte[1024];
				int len=0;
				while((len = bin.read(temp))>0) {
					bout.write(temp, 0, len);
				}
				bout.flush();
						
			}catch (Exception e) {
				System.out.println("입출력 오류 : " + e.getMessage());
			}finally {
				if(bout!=null) try {bout.close();} catch(IOException e) {}
				if(bin!=null) try {bin.close();} catch(IOException e) {}
			}
			
		}else { //해당 파일이 없을 때
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().println("<h3>" + fvo.getOrigin_file_name() + "파일이 존재하지 않습니다.</h3>");
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
