package kr.or.ddit.basic.stream;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileCopyDialogTest {
   public void fileCopyStart() {
      File file =showDialog("OPEN");
      if(file==null) {
         System.out.println("복사할 원본 파일을 선택하지 않았습니다.");
         System.out.println("복사 작업을 마칩니다...");
         return;
      }
      
      if(!file.exists()) {
         System.out.println(file.getPath() + " 파일이 없습니다...");
         System.out.println("복사 작업을 마칩니다...");
         return;
      }
      
      File dir = new File("d:/d_other/연습용");
      if(!dir.exists()) {      //복사될 대상 폴더가 없으면 새로 만든다...
         dir.mkdirs();
      }
      
      File targetFile = showDialog("SAVE");
      if(targetFile == null) {
         System.out.println("저장할 대상 파일을 선택하지 않았습니다...");
         System.out.println("복사 작업을 마칩니다...");
         return;
      }
      
      FileInputStream fin=null;         //복사작업은 byte기반 스트림 쓰는게 좋음
      FileOutputStream fout = null;
      
      BufferedInputStream bin = null;
      BufferedOutputStream bout = null;
      
      try {
         //원본 파일을 읽어올 스트림 객체 생성
         fin = new FileInputStream(file);
         bin = new BufferedInputStream(fin);
         
         //대상 파일을 출력할 스트림 객체 생성
         //fout = new FileOutputStream(new File(dir, "복사본_펭귄.jpg"));   //그 안에 들어있는 파일 - 파일객체 만들때 지정할 수 있음
         fout = new FileOutputStream(targetFile);
         bout = new BufferedOutputStream(fout);
         
         System.out.println("복사 작업 시작...");
         
         int data;    // 읽어온 데이터가 저장될 변수
         
         while( (data=bin.read())!=-1) {
            bout.write(data);
         }
         
         bout.flush();
         
         System.out.println("복사 작업 완료...");
         
      } catch (IOException e) {
         e.printStackTrace();
      }finally {
         if(fin!=null)try {bin.close(); } catch (Exception e2) {}
               //생성하다가 exception발생하면 fin이 null인 상태에서 닫아버릴 수 있음 => 오류남
         if(fout!=null)try {bout.close(); } catch (Exception e2) {}
      }
   }
   
   //Dialog창을 나타내고 작업할 파일을 선택해서 반환하는 메서드
   private File showDialog(String option) {
      File selectedFile = null;
      
      JFileChooser chooser = new JFileChooser();
      
      // 보여줄 파일 목록을 확장자 설정하기
      FileNameExtensionFilter doc = new FileNameExtensionFilter("Word 파일", "docx", "doc", "hwp");
            //화면에 보일 글씨, 확장자 지정-열거해도 되고 배열로 써도 됨
      FileNameExtensionFilter img = new FileNameExtensionFilter("이미지 파일", new String[] {"png", "jpg", "gif"});
      FileNameExtensionFilter txt = new FileNameExtensionFilter("text 파일", "txt");
      
      // 생성한 확장자 목록을 Chooser에 추가한다.
      chooser.addChoosableFileFilter(doc);
      chooser.addChoosableFileFilter(img);
      chooser.addChoosableFileFilter(txt);
      
      // '모든 파일' 목록 표시 설정 여부 ( true : 설정,  false : 헤제
      chooser.setAcceptAllFileFilterUsed(true);
      
      //Dialog창에 나타날 기본 경로 설정하기
      chooser.setCurrentDirectory(new File("d:/d_other"));
      
      int result;
      if("SAVE".equals(option.toUpperCase())) {
         result = chooser.showSaveDialog(new Panel());   //저장용 창
      }else if("OPEN".equals(option.toUpperCase())) {
         result = chooser.showOpenDialog(new Panel());   //열기용 창
      }else {
         System.out.println("option은 'SAVE' 또는 'OPEN'만 사용가능합니다...");
         return null;
      }
      //Dialog창에서 '저장' 또는 '열기' 버튼을 클릭했을 때 처리하기
      if(result == JFileChooser.APPROVE_OPTION) {            //열기 또는 저장버튼을 눌렀다
         selectedFile = chooser.getSelectedFile();      //선택한 파일 정보 가져오기
         //System.out.println("선택 파일 : " + selectedFile.getAbsolutePath());
      }
      
      return selectedFile;
   }
   
   
   public static void main(String[] args) {
      new FileCopyDialogTest().fileCopyStart();
   }

}