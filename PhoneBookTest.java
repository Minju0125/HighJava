package kr.or.ddit.basic.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/*
   문제 ) 이름 , 전화번호 , 주소를 멤버로 갖는 Phone클래스를 만들고
        Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
        (Map의 구조는 key값으로 '이름'을 사용하고
                 value값으로 'Phone클래스의 인스턴스'를 갖는다.)
        
        아래 메뉴를 구현하시오.
        1. 전화번호 등록
        2. 전화번호 삭제
        3. 전화번호 수정
        4. 전화번호 검색
        5. 전화번호 전체 출력
        0. 프로그램 종료
        
        삭제, 검색기능은 '이름'을 입력받아 처리한다.
 -----------------------------------------------------------------------------------------------------------------
 -추가 조건)
 1) 6. 전화번호 저장 메뉴를 추가하고 그 기능을 구현한다.
 		(저장 파일명 : 'phoneBookData.dat'로 한다.)
 2) 프로그램이 시작될 떄 저장된 파일이 있으면 그 데이터를 읽어와서 사용한다.
 		(읽어온 데이터를 Map에 저장한다.)
 3) 프로그램을 종료할 떄 Map의 데이터가 추가, 수정, 삭제 등으로 변경되었으면 저장한 후에 종료되도록 한다.
 -----------------------------------------------------------------------------------------------------------------
 
실행 예시
        1. 전화번호 등록
        2. 전화번호 삭제
        3. 전화번호 수정
        4. 전화번호 검색
        5. 전화번호 전체 출력
        0. 프로그램 종료
        번호입력 >> 1
        
        새롭게 등록할 전화번호 정보를 입력하세요 .
        이름 >> 홍길동
        전화번호 >> 010-1234-5678
        주소 >> 대전 어쩌구
        
        '홍길동' 전화번호 등록 완료!!
-----------------------------------------------------
        1. 전화번호 등록
        2. 전화번호 삭제
        3. 전화번호 수정
        4. 전화번호 검색
        5. 전화번호 전체 출력
        0. 프로그램 종료
        번호입력 >> 1
        이름 >> 홍길동
        '홍길동'은 이미 등록된 사람입니다...
-----------------------------------------------------
        1. 전화번호 등록
        2. 전화번호 삭제
        3. 전화번호 수정
        4. 전화번호 검색
        5. 전화번호 전체 출력
        0. 프로그램 종료
        번호입력 >> 5
-----------------------------------------------------
        번호   이름      전화번호         주소
-----------------------------------------------------
        1      홍길동   010-1234-5678   대진서 어쩌구
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
-----------------------------------------------------
        1. 전화번호 등록
        2. 전화번호 삭제
        3. 전화번호 수정
        4. 전화번호 검색
        5. 전화번호 전체 출력
        0. 프로그램 종료
        번호입력 >> 1

*/
public class PhoneBookTest {
	private Scanner sc;
	private HashMap<String, Phone> phoneBookMap;
	// 1)맵에 있는 데잍터를 하나하나 꺼내서 폰 객체로 저장하는 방법
	// 2)맵 자체를 저장하는 방법
	private final String filename = "d:/d_other/phoneBookData.dat";

	
	//데이터가 변경되었는지 여부를 나타내는 변수 선언
	private boolean dataChange; //데이터가 변경되면 true 로 변경된다.
	
	
	// 생성자 (이 객체에서 가장 먼저 실행되는 곳)
	public PhoneBookTest() {
		sc = new Scanner(System.in);
		phoneBookMap = load();

		if (phoneBookMap == null) {
			phoneBookMap = new HashMap<String, Phone>();

		}
	}

	// 메뉴를 출력하고 작업 번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("=====================================");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 삭제");
		System.out.println("3. 전화번호 수정");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("6. 전화번호 저장");
		System.out.println("0. 프로그램 종료");
		System.out.println("=====================================");
		System.out.print("번호 입력 : ");
		return sc.nextInt();

	}

	// 프로그램을 시작하는 메서드
	public void phoneStart() {
		System.out.println();
		System.out.println("=====================================");
		System.out.println("          전 화 번 호 관 리 프 로 그 램");
		System.out.println("=====================================");
		System.out.println();
		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1:// 등록
				insert();
				break;
			case 2:// 삭제
				delete();
				break;
			case 3:// 수정
				update();
				break;
			case 4:// 검색
				search();
				break;
			case 5:// 전체 출력
				displayAll();
				break;
			case 6:// 전화번호 저장
				save();
				break;
			case 0:// 종료
				if(dataChange) { //if(dataChange ==true)로 써도됨.
					save();
				}
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다...");
				System.out.println("다시 입력해주세요");
				break;
			}
		}
	}

	// 파일에 저장된 전화번호 정보를 읽어와서 Map 에 셋팅한 후 반환하는 메서드
	private HashMap<String, Phone> load() {
		HashMap<String, Phone> pMap = null; // 읽어오은 데이터가 저장될 변수
		File file = new File(filename);
		if (!file.exists()) { // 저장된 파일이 없으면...
			System.out.println("");
			return null;

		}

		// 객체가 저장된 파일을 읽어올 스트림 객체 변수 선언
		ObjectInputStream oin = null;
		try {
			oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));

//			저장을 '방법1'로 했을 때...
//			pMap = (HashMap<String, Phone>)oin.readObject();

			// 저장을 '방법2'로 했을 때...
			Object pObj; // 읽어온 Phone객체가 저장될 변수 선언

			pMap = new HashMap<String, Phone>();

			while ((pObj = oin.readObject()) != null) {
				Phone p = (Phone) pObj;
				String name = p.getName();
				pMap.put(name, p);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return pMap;
	}

	// 전화번호를 저장하는 메소드
	private void save() {

		ObjectOutputStream oout = null;

		try {
			// 객체 출력용 스트림 객체 생성
			oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));// 버퍼기능도 사용하기 위해 !

			// 객체를 저장하는 방법1 => 화번호 정보가 저장된 Map 객체를 저장한다.
//			oout.writeObject(phoneBookMap);

			// 객체를 저장하는 방법 2 => Map에 저장된 Phone 객체를 하나씩 저장한다.
			for (String name : phoneBookMap.keySet()) {
				Phone p = phoneBookMap.get(name);
				oout.writeObject(p);
			}
			oout.writeObject(null); // 마지막을 나타내는 null 값 저장

			System.out.println("저장이 완료되었습니다...");
			dataChange =false; //저장 후 save나 insert, update, delete 하기 전까지 false=> if문에서 걸림
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (oout != null)
				try {
					oout.close();
				} catch (IOException e) {
				}
		}

	}

	// 전화번호 정보를 검색하는 메서드
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요...");
		System.out.print("이름 >> ");
		String name = sc.next();
		// 등록된 사람인지 검사
		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			return;
		}

		Phone p = phoneBookMap.get(name);
		System.out.println(name + "씨 전화번호 정보");
		System.out.println("------------------------------");
		System.out.println(" 이     름 : " + p.getName());
		System.out.println(" 전화번호 : " + p.getTle());
		System.out.println(" 주     소 : " + p.getAddr());
		System.out.println("------------------------------");
		System.out.println();

	}

	// 전화번호 정보를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요...");

		System.out.print("이름 >> ");
		String name = sc.next();
		// 등록된 사람인지 검사
		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			return;
		}

		System.out.print("새로운 전화번호 >> ");
		String newTel = sc.next();
		sc.nextLine(); // 버퍼 비우기
		System.out.print("새로운 주소 >> ");
		String newAddr = sc.nextLine();

		// 같은 key값으로 새로운 데이터를 추가하면 수정작업이 완료된다.
		phoneBookMap.put(name, new Phone(name, newTel, newAddr));

		System.out.println(name + "씨의 전화번호 정보를 변경했습니다...");
		dataChange = true;
	}

	// 전화번호 정보를 삭제하는 메서드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요...");
		System.out.print("이름 >> ");
		String name = sc.next();
		// 등록된 사람인지 검사
		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			return;
		}

		phoneBookMap.remove(name);
		System.out.println(name + "씨의 전화번호 정보를 삭제했습니다...");
		dataChange = true;
	}

	// 전체 자료를 출력하는 메서드
	private void displayAll() {
		System.out.println();
		System.out.println("--------------------------------------------------------------------------");
		System.out.println(" 번호        이 름     전화번호           주소");
		System.out.println("--------------------------------------------------------------------------");

		// Map의 key값들만 모두 가져온다.
		Set<String> phoneKeySet = phoneBookMap.keySet();

		if (phoneKeySet.size() == 0) {
			System.out.println("등록된 전화번호 정보가 하나도 없습니다...");
		} else {
			int num = 0; // 번호
			/*
			 * Iterator<String> keyIt = phoneKeySet.iterator(); while(keyIt.hasNext()) {
			 * num++; String key = keyIt.next(); // key값(이름) 구하기 Phone p =
			 * phoneBookMap.get(key); // value값(Phone클래스의 인스턴스) 구하기 System.out.println(num +
			 * "\t" + p.getName() + "\t" + p.getTle() + "\t" + p.getAddr()); }
			 */
			for (String key : phoneKeySet) {
				num++;
				Phone p = phoneBookMap.get(key);
				System.out.println(num + "\t" + p.getName() + "\t" + p.getTle() + "\t" + p.getAddr());
			}
		}
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("출력 끝...");

	}

	// 새로운 전화번호 정보 등록 메서드
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");

		String name = sc.next();
		if (phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 이미 등록된 사람입니다...");
			return;
		}
		System.out.print("전화번호 >> ");
		String tel = sc.next();
		sc.nextLine(); // 입력 버퍼 비우기
		System.out.print("회원주소 >> ");
		String addr = sc.nextLine();

//      Phone p = new Phone(name, tel, addr);
//      phoneBookMap.put(name, p);
		phoneBookMap.put(name, new Phone(name, tel, addr));
		System.out.println(name + "씨 전화번호 등록 완료!!!");
		
		dataChange = true;
	}

	public static void main(String[] args) {
		new PhoneBookTest().phoneStart();
	}
//   Scanner sc = new Scanner(System.in);
//   HashMap<String, Phone> list = new HashMap<>();
//   
//   
//   
//   
//   public PhoneBookTest() {
//      list.put("홍길동",new Phone("홍길동", "010-1234-5678", "대전 한밭도서관 옆 박스깔린곳"));
//   }
//
//   /*전화번호 추가*/
//   public void addNum() {
//      System.out.print("이름 : ");
//      String nem = sc.nextLine();
//      if(list.containsKey(nem)) {
//         System.out.println("'"+nem+"' 은(는) 이미 등록된 사람입니다.");
//         return;
//      }
//      System.out.print("전화번호 : ");
//      String tn = sc.nextLine();
//      System.out.print("주소 : ");
//      String ju = sc.nextLine();
//      
//      int after = list.size();
//      list.put(nem, new Phone(nem, tn, ju));
//      int before = list.size();
//      if(after!=before) System.out.println("'"+nem+"' 전화번호 등록 완료!!");
//   }
//   
//   /*전화번호 삭제*/
//   public void removeNum() {
//      if(list.size()==0) {
//         System.out.println("전화번호부에 내역이 존재하지 않습니다.");
//         return;
//      }
//      System.out.print("삭제할 이름 : ");
//      String nem = sc.nextLine();
//      if(!list.containsKey(nem)) {
//         System.out.println("'"+nem+"' 은(는) 등록되지 않은 사람입니다.");
//         return;
//      }
//      Phone result = list.remove(nem);
//      if(result!=null) System.out.println("'"+nem+"' 전화번호 삭제 완료!!");
//   }
//   
//   /*전화번호 수정*/
//   public void modiNum() {
//      if(list.size()==0) {
//         System.out.println("전화번호부에 내역이 존재하지 않습니다.");
//         return;
//      }
//      System.out.print("수정할 이름 : ");
//      String nem = sc.nextLine();
//      if(!list.containsKey(nem)) {
//         System.out.println("'"+nem+"' 은(는) 등록되지 않은 사람입니다.");
//         return;
//      }
//      System.out.print("수정할 전화번호 : ");
//      String targetNum = sc.nextLine();
//      String after = list.get(nem).getTel();
//      list.get(nem).setTel(targetNum);
//      String before = list.get(nem).getTel();
//      if(!after.equals(before)) System.out.println("'"+nem+"' 전화번호 변경 완료!!");
//   }
//   
//   /*전화번호 검색*/
//   public void searchNum() {
//      if(list.size()==0) {
//         System.out.println("전화번호부에 내역이 존재하지 않습니다.");
//         return;
//      }
//      System.out.print("검색할 이름 : ");
//      String nem = sc.nextLine();
//      if(!list.containsKey(nem)) {
//         System.out.println("'"+nem+"' 은(는) 등록되지 않은 사람입니다.");
//         return;
//      }
//      System.out.println("==============================================");
//      System.out.println("이름        전화번호            주소");
//      System.out.println("----------------------------------------------");
//      System.out.printf("%s  %13s\t%s\n",list.get(nem).getName(),list.get(nem).getTel(),list.get(nem).getAddr());
//   }
//   
//   /*전체 출력*/
//   public void printAllNum() {
//      if(list.size()==0) {
//         System.out.println("전화번호부에 내역이 존재하지 않습니다.");
//         return;
//      }
//      Set<String> forPrint = list.keySet();
//      Iterator<String> it = forPrint.iterator();
//      System.out.println("==============================================");
//      System.out.println(" 번호     이름          전화번호                 주소");
//      System.out.println("----------------------------------------------");
//      int i = 1;
//      while(it.hasNext()) {
//         String keyName = it.next();
//         System.out.printf("%3d   %s     %13s      %s\n",i++,keyName,list.get(keyName).getTel(),list.get(keyName).getAddr());
//
//      }
//   }
//   
//   /*메인화면*/
//   public void mainScreen() {
//      while(true) {
//         System.out.println("==============================================");
//         System.out.println("1. 전화번호 등록");
//         System.out.println("2. 전화번호 삭제");
//         System.out.println("3. 전화번호 수정");
//         System.out.println("4. 전화번호 검색");
//         System.out.println("5. 전화번호 전체 출력");
//         System.out.println("0. 프로그램 종료");
//         System.out.println("==============================================");
//         System.out.print("번호 입력 : ");
//         switch (sc.nextLine()) {
//         case "1":
//            addNum();
//            break;
//         case "2":
//            removeNum();
//            break;
//         case "3":
//            modiNum();
//            break;
//         case "4":
//            searchNum();
//            break;
//         case "5":
//            printAllNum();
//            break;
//         case "0":
//            System.out.println("프로그램을 종료합니다.");
//            return;
//
//         default:
//            System.out.println("잘못 입력하셨습니다.");
//            break;
//         }
//      }
//      
//   }
//   
//   public static void main(String[] args) {
//      new PhoneBookTest().mainScreen();
//   }
//
//}
//
//
//
//class Phone{
//   private String name;
//   private String tel;
//   private String addr;
//   public String getName() {
//      return name;
//   }
//   public void setName(String name) {
//      this.name = name;
//   }
//   public String getTel() {
//      return tel;
//   }
//   public void setTel(String tel) {
//      this.tel = tel;
//   }
//   public String getAddr() {
//      return addr;
//   }
//   public void setAddr(String addr) {
//      this.addr = addr;
//   }
//   public Phone(String name, String tel, String addr) {
//      this.name = name;
//      this.tel = tel;
//      this.addr = addr;
//   }
//   
//   

}

class Phone implements Serializable {
	private String name;
	private String tle;
	private String addr;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTle() {
		return tle;
	}

	public void setTle(String tle) {
		this.tle = tle;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Phone(String name, String tle, String addr) {
		super();
		this.name = name;
		this.tle = tle;
		this.addr = addr;
	}

	public Phone() {
	}

}