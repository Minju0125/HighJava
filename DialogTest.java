package kr.or.ddit.basic.stream;

import java.awt.Panel;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

//Dialog -> 윈도우용 프로그램에서 작업표시줄? 눌렀을때 뜨는 창들
public class DialogTest {
	public static void main(String[] args) {
		//자바의 GUI 라이브러리 발전 과정 (AWT=>SWING=>JAVAFX)
		//SWING의 파일 열기, 저장 창 연습
		JFileChooser chooser = new JFileChooser();
		
		//보여줄 파일 목록의 확장자 설정하기
		//열거형 표시방법
		FileNameExtensionFilter doc = new FileNameExtensionFilter("Word 파일", "docx","doc", "hwp");
		//배열을 통한 표시방법
		FileNameExtensionFilter img = new FileNameExtensionFilter("이미지", new String[] {"png","jpg", "gif"});
		FileNameExtensionFilter txt = new FileNameExtensionFilter("text 파일", "txt");		
	
		chooser.setAcceptAllFileFilterUsed(true); //여기에 쓰면 모든 목록 중 '모든 파일'이 가장 위애 뜸
		//생성한 확장자 목록은 Chooser에 추가한다.
		chooser.addChoosableFileFilter(doc);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(txt);
		
		//'모든 파일' 목록 표시 설정 여부 (true : 설정, false:해제)
		//창 띄웠을 때 파일 유형 목록에서 모든 파일 목록 표시 여부 설정  - 기본값은 true
//		chooser.setAcceptAllFileFilterUsed(true); 
		
		//Dialog창에 나타날 기본 경로 설정하기
		chooser.setCurrentDirectory(new File("d:/d_other"));

		//아래 두개 중 하나만 쓸 수 있음
//////////////////////////////////////이건 그냥 창을 띄우는건가??? 띄워진 창의 형식만 다른건가????????!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// 이 이후에는 알아서 ?
//		int result = chooser.showOpenDialog(new Panel());//열기용 창
		int result = chooser.showSaveDialog(new Panel());//저장용 창
		
		//Dialog 창에서 '저장' 또는 '열기' 버튼을 클랙했을 때 처리
		if(result == JFileChooser.APPROVE_OPTION) { //APPROVE_OPTION가 상수형으로 되어있음(파일 선택대화상자에서 확인/열기를 눌렀을 때 반환되는 값)
			File selectedFile = chooser.getSelectedFile(); //선택한 파일 정보 가져오기
			System.out.println("선택 파일 : " +selectedFile.getAbsolutePath());
		}
		
		
	}
}
