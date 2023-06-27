package kr.or.ddit.basic;

import java.util.Stack;

public class StackTest {
   public static void main(String[] args) {
      Browser b = new Browser();
      
      b.goURL("1. 네이버");
      b.history();
      
      b.goURL("2. 네이트");
      b.history();
      
      b.goURL("3. 구글");
      b.goURL("4. 다음");

      b.history();
      
      System.out.println("뒤로가기 후...");
      b.goBack();
      b.history();
      
      System.out.println("앞으로 가기 후...");
      b.goForward();
      b.history();
      
      System.out.println("새로운 사이트 접속하기...");
      b.goURL("5. 야후");
      b.history();
   }
   
}

//웹브라우저의 앞으로 가기, 뒤로 가기 기능 구현 예제(스택 이용)
class Browser{
   private Stack<String> back;      //이전 방문 내역이 저장될 스택(주소 저장)
   private Stack<String> forward;    //다음 방문 내역이 저장될 스택
   private String currentURL;      //현재 페이지
   
   public Browser() {
      back = new Stack<String>();
      forward = new Stack<String>();
      currentURL = "";
   }
   
   //사이트를 방문하는 메서드(주소창에 뭔가를 입력했을 때 처리하는 메소드)
   public void goURL(String url) {   //매개변수에 입력한 주소(방문할 URL) 전달
      System.out.println(url + " 사이트에 접속합니다...");
      System.out.println();
      
      if(currentURL != null && !"".equals(currentURL)) {   //현재 페이지가 있으면..
                                             //처음에는 false나옴 - currentURL이 null이기 때문
         back.push(currentURL);            //현재 페이지를 back스택에 저장한다.
      }
      currentURL = url;       //현재 페이지를 이동할 페이지로 바꿔줌
      forward.clear(); //forward 스택 데이터를 비워줌
   }
   
   //뒤로 가기 메서드
   public void goBack() {   //현재 주소는 앞으로가기 쪽으로 넘기고 뒤로가기 주소를 현재페이지에 넣어야함
      if(!back.isEmpty()) {   //isEmpty() 메서드 ==> Collection에 데이터가 없으면 true, 있으면 false를 반환
                        //뒤로가기가 있는지 검사
         forward.push(currentURL);       //현재 페이지를 forward스택에 추가
         currentURL = back.pop();      //back스택에서 1개의 요소를 꺼내와 현재 페이지로 저장
         
      }
   }
   
   //앞으로 가기 메서드
   public void goForward() {
      if(!forward.isEmpty()) {
         back.push(currentURL);
         currentURL = forward.pop();
      }
   }
   
   //방문 기록 확인하는 메서드
   public void history() {
      System.out.println("-----------------------");
      System.out.println("    방      문      기      록");
      System.out.println("-----------------------");
      System.out.println("back >> "+back);
      System.out.println("현    재 >>"+currentURL);
      System.out.println("forward >>"+ forward);
      System.out.println("------------------------");
      System.out.println();
   }
}