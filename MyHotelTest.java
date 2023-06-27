package kr.or.ddit.solution;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class MyHotelTest {
   public static void main(String[] args) {
      Management m = new Management();
      m.start();
   }
}

class Room {
   private int roomNo;
   private String roomType;
   private String guestName;

   public int getRoomNo() {
      return roomNo;
   }

   public void setRoomNo(int roomNo) {
      this.roomNo = roomNo;
   }

   public String getRoomType() {
      return roomType;
   }

   public void setRoomType(String roomType) {
      this.roomType = roomType;
   }

   public String getGuestName() {
      return guestName;
   }

   public void setGuestName(String guestName) {
      this.guestName = guestName;
   }

   @Override
   public String toString() {
      return "Room [roomNo=" + roomNo + ", roomType=" + roomType + ", guestName=" + guestName + "]";
   }

}

class Management {
   Room r = new Room();
   Scanner sc = new Scanner(System.in);

   Management() {
   }

   Management(int roomNo, String roomType) {
      r.setRoomNo(roomNo);
      r.setRoomType(roomType);
   }

   HashMap<Integer, Management> room = new HashMap<>();

   public void start() {

      for (int i = 201; i <= 209; i++) {
         room.put(i, new Management(i, "싱글룸"));
      }

      for (int i = 301; i <= 309; i++) {
         room.put(i, new Management(i, "더블룸"));
      }

      for (int i = 401; i <= 409; i++) {
         room.put(i, new Management(i, "스위트룸"));
      }

      System.out.println();
      System.out.println("----------------------------------------------");
      System.out.println(" * 201~209 : 싱글룸");
      System.out.println(" * 301~309 : 더블룸");
      System.out.println(" * 401~409 : 스위트룸");
      System.out.println("----------------------------------------------");
      System.out.println();

      System.out.println("*********************************************");
      System.out.println("       호텔문을 열었습니다. 어서오십시오.");
      System.out.println("*********************************************");
      System.out.println();
      System.out.println();

      while (true) {
         System.out.println("-----------------------------------------------------------");
         System.out.println("어떤 업무를 하시겠습니까?");
         System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
         int answer = Integer.parseInt(sc.nextLine());
         switch (answer) {
         case 1: // 체크인
            checkIn();
            break;
         case 2: // 체크아웃
            checkOut();
            break;
         case 3: // 객실상태
            checkCurrentRoom();
            break;
         case 4: // 업무 종료
            return;

         }
      }
   }

   private void checkIn() {
      System.out.println();
      System.out.println("----------------------------------------------");
      System.out.println("         체크인작업             ");
      System.out.println("----------------------------------------------");
      System.out.println(" * 201~209 : 싱글룸");
      System.out.println(" * 301~309 : 더블룸");
      System.out.println(" * 401~409 : 스위트룸");
      System.out.println("----------------------------------------------");
      System.out.println();

      System.out.println("방 번호 입력 >> ");
      int roomNo = Integer.parseInt(sc.nextLine());
      if (!room.containsKey(roomNo)) {
         System.out.println(roomNo + "호 객실은 존재하지 않습니다.");
         return;
      }

      if (room.get(roomNo).r.getGuestName() != null) {
         System.out.println(roomNo + "호 객실은 이미 손님이 있습니다.");
         return;
      }

      System.out.println("누구를 체크인 하시겠습니까?");
      System.out.println("이름 입력 >> ");
      String name = sc.nextLine();
      room.get(roomNo).r.setGuestName(name);
      System.out.println("체크인이 완료되었습니다.");

   }

   private void checkOut() {
      System.out.println();
      System.out.println("----------------------------------------------");
      System.out.println("         체크아웃작업             ");
      System.out.println("----------------------------------------------");

      System.out.println("체크아웃할 방 번호를 입력하세요.");
      System.out.println("방 번호 입력 >> ");
      int roomNo = Integer.parseInt(sc.nextLine());
      if (!room.containsKey(roomNo)) {
         System.out.println(roomNo + "호 객실은 존재하지 않습니다.");
         return;
      }
      if (room.get(roomNo).r.getGuestName() == null) {
         System.out.println(roomNo + "객실에는 체크인 한 사람이 없습니다.");
         return;
      }
      String guest = room.get(roomNo).r.getGuestName();
      room.remove(roomNo);
      System.out.println(roomNo + "객실의 " + guest + "님 체크아웃을 완료하였습니다.");
   }

   private void checkCurrentRoom() {
      System.out.println("----------------------------------------------");
      System.out.println("현재 객실 상태");
      System.out.println("----------------------------------------------");

      Iterator keySetIter = room.keySet().iterator();
      System.out.println("방 번호\t방 종류\t투숙객 이름");
      while (keySetIter.hasNext()) {
         int key = Integer.parseInt(keySetIter.next().toString());
         System.out.print(key + "\t");
         System.out.print(room.get(key).r.getRoomType() + "\t");
         if(room.get(key).r.getGuestName()==null) {
            System.out.println("   -");
         } else {
            System.out.println("   "+  room.get(key).r.getGuestName());}
         
      }

   }

}