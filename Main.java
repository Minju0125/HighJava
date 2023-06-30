package kr.or.ddit.game;

import java.util.Random;

public class Main {
   // 게임에 필요한 자원들을 Main클래스 안에서 초기화를 진행하고 시작한다.
   
   Character c;
   Monster[] monsterPool;
   Item[] itemPool;
   Fish[] fishes;
   int day;
   
   Main(){
      itemPool=new Item[6];
      itemPool[0]=new Item("나무검",0,0,10,0,0,0);
      itemPool[1]=new Item("천갑옷",0,0,0,10,0,0);
      itemPool[2]=new Item("강철검",0,0,20,0,0,0);
      itemPool[3]=new Item("가죽옷",0,0,0,20,0,0);

      itemPool[4]=new Item("낚시",0,0,0,0,1,0);
      itemPool[5]=new Item("무지개파편",0,0,0,0,0,50);

      Item[] items1= {itemPool[0],itemPool[1]};
      Item[] items2= {itemPool[0],itemPool[1],itemPool[2],itemPool[3]};
      
      monsterPool=new Monster[5];
      monsterPool[0]=new Monster("박쥐",30,20,10,5,10,items1);
      monsterPool[1]=new Monster("두더지",100,40,15,10,20,items1);
      monsterPool[2]=new Monster("슬라임",200,80,20,10,30,items2);
      monsterPool[3]=new Monster("바위게",400,160,25,20,40,items2);
      monsterPool[4]=new Monster("해골",800,320,300,150,50,items2);
      
      fishes = new Fish[3];
      fishes[0]=new Fish("붕어",20,10);
      fishes[1]=new Fish("광어",50,30);
      fishes[2]=new Fish("도미",100,50);
      
      day=1;
   }
   
   public static void main(String[] args) {
      new Main().start();
   }

   // 게임 시작
   private void start() {
      clearConsole();
      System.out.print("캐릭터의 이름 입력>> ");
      String name=ScanUtil.nextLine();
      c=new Character(name);
      
      clearConsole();
      System.out.println("============ 게임시작 ============");
      
      game : while(true) {
         if(c.fatigue<=0) {
            System.out.printf("%s의 행동력이 없습니다!\n",c.name);
            System.out.println("자러 집으로 갑니다...zzzZ");
            c.fatigue=300;
            day++;
         }
         
         if(c.hp<=0) {
            System.out.println("플레이어의 체력이 없습니다.");
            System.out.println("게임을 종료합니다!");
            break game;
         }
         
         System.out.printf("------ 메뉴를 선택하세요 --%d일---\n",day);
         System.out.println("1.정보 2.낚시 3.사냥 4.상점 5.휴식 6.종료");
         System.out.println("-------------------------------");
         System.out.print("메뉴를 선택 >> ");
         int input=ScanUtil.nextInt();
         
         clearConsole();
         
         switch(input) {
         case 1:
            c.showInfo();
            break;
         case 2:
            fish : while(true) {
               if(c.fatigue<=0) {
                  System.out.printf("%s의 행동력이 없습니다.\n", c.name);
                  System.out.println("자러 집으로 갑니다...zzzZ");
                  c.fatigue=300;
                  day++;
                  break fish;
               }
               
               break;
            }
            fishing();
            break;
         case 3:
            attack:while(true) {
               if(c.hp<=0) {
                  System.out.println("플레이어의 체력이 없습니다.");
                  System.out.println("게임을 종료합니다.");
                  break game;
               }
               if(c.fatigue<=0) {
                  System.out.printf("%s의 행동력이 없습니다.\n", c.name);
                  System.out.println("자러 집으로 갑니다...zzzZ");
                  c.fatigue=300;
                  day++;
                  break attack;
               }
               
               hunt();
            }
         case 4:
            c.showInfo();
            break;
         case 5:
            c.showInfo();
            break;
         case 6:
            c.showInfo();
            break;
         }
      }
   }
   
   private void hunt() {
      Monster tempMonster=monsterPool[new Random().nextInt(monsterPool.length)];
      Monster m = new Monster(tempMonster.name,tempMonster.maxHp,tempMonster.maxMp,tempMonster.att,tempMonster.def,tempMonster.exp,tempMonster.items);
      
      int input=0;
      
      battle:while(true) {
            System.out.println(m.name+"을 만났습니다.");
            System.out.print("1.공격 2.도망 >> ");
            input=ScanUtil.nextInt();
            
            clearConsole();
            
            switch (input) {
            case 1:
               c.attack(m);
               if(m.hp<=0) {
                  System.out.println(m.name+"을(를) 처치하였습니다.");
                  Item i=m.dropItem();
                  c.getItem(i);
                  System.out.println("--- 전투종료 ----\n");
                  c.getExp(m.exp);
                  break battle;
               }
               m.attack(c);
               if(c.hp<=0) {
                  System.out.println(c.name+"이(가) 사망했습니다.\n");
                  System.out.println("---- 전투 종료 ----\n");
                  break battle;
               }
               break;
            case 2:
               System.out.println(m.name+"(으)로부터 도망쳤습니다.\n");
               break battle;
            default:
               System.out.println("유효하지 않은 입력입니다.\n");
            }
      }
   }

   // 낚시하기
   private void fishing() {
      int check=1;
      Fish tempFish= fishes[new Random().nextInt(fishes.length)];
      Fish fi=new Fish(tempFish.name,tempFish.maxHp,tempFish.def);
      
      int input=0;
      
      System.out.println(fi.name+"이(가) 잡혔습니다! \n");
      fish : while(true) {
         System.out.printf("[%s] 1.잡기 2.놓아주기\n",fi.name);
         input=ScanUtil.nextInt();
         switch (input) {
         case 1:
            if(check>=5) {
               System.out.println(fi.name+"을 놓쳤습니다!\n");
               break fish;
            }else {
               c.pull(fi);
               check+=1;
               if(fi.hp<=0) {
                  Item i=fi.dropItem();
                  System.out.println("물고기를 잡았습니다.");
                  c.getItem(i);
                  c.pointFishing+=1;
                  c.fatigue-=10;
                  break fish;
               }
            }
            break;
         case 2:
            System.out.println(fi.name+"을 놓쳤습니다.\n");
            break fish;
         default:
            System.out.println("유효하지 않은 입력입니다.\n");
         }
      }
   }

   public void clearConsole() {
      for(int i=0;i<100;i++) {
         System.out.println();
      }
   }
}