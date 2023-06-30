package kr.or.ddit.game;

import java.util.Random;

public class Character {
	String name; // 캐릭터의 이름
	int maxHp; // 캐릭터 최대 HP
	int maxMp; // 캐릭터 현재 MP
	int hp; // 캐릭터 현재 HP
	int mp; // 캐릭터 현재 MP
	int att; // 캐릭터 공력력
	int def; // 캐릭터 방어력
	int lv; // 캐릭터 현재 레벨
	int exp; // 캐릭터 현재 경험치
	int nextExp; // 레벨업을 위한 다음 경험치
	int money; // 캐릭터 가지고 있는 자본

	int maxFishing; // 낚시 최대 숙련도
	int fishing; // 낚시 숙련도 레벨
	int pointFishing; // 낚시포인트
	int maxFatigue; // 캐릭터 최대 피로도
	int fatigue; // 캐릭터 피로도
	Item[] inven; // 캐릭터 인벤토리
	int input; // 입력

	public Character(String name) {
		this.name = name; // 이름 설정
		this.maxHp = 100; // 최대 HP 100
		this.hp = this.maxHp; // 현재 HP 100
		this.maxMp = 100; // 최대 MP 50
		this.mp = this.maxMp; // 최대 MP 50
		this.att = 200; // 공격력200
		this.def = 10; // 방어력 10
		this.lv = 1; // 현재 레벨 1
		this.exp = 0; // 현재 경험지 0
		this.money = 500; // 현재 자본 500
		this.nextExp = (this.lv * 100); // 다음레벨업까지 달성해야할 경험치
		this.inven = new Item[0]; // 기본적인 인벤토리 생성 <-왜 0개짜리 배열 ?

		this.fishing = 1; // 낚시 레벨 1
		this.maxFishing = (this.fishing * 10);

		this.maxFatigue = 300; // 최대 피로도 300
		this.fatigue = this.maxFatigue; // 현재 피로도 300 <-왜?

	}

	// 캐릭터 정보를 확인(캐릭터와 관련된 스택 정보를 확인할 수 있다.)
	public void showInfo() {
		System.out.println("-----상태------");
		System.out.printf("이 름 : %s\n", this.name);
		System.out.printf("레 벨 : %d\n", this.lv);
		System.out.printf("행동력 : %d / %d\n", this.fatigue, this.maxFatigue);
		System.out.printf("체 력 : %d / %d\n", this.hp, this.maxHp);
		System.out.printf("마 나 : %d / %d\n", this.mp, this.maxMp);
		System.out.printf("공 격 : %s\n", this.att);
		System.out.printf("방 어 : %s\n", this.def);
		System.out.printf("경험치 : %s\n", this.name);
		System.out.println("---- 기술 ----");
		System.out.printf("낚 시 : %d (point :%d)\n", this.fishing, this.pointFishing);
		System.out.println("---- 아이템 ----");
		System.out.printf("골 드 : %d\n", this.money);
		for (int i = 0; i < inven.length; i++) {
			System.out.println(this.inven[i] + " ");
			if ((i + 1) % 5 == 0) { // 인벤토리 장비 5개씩 정렬
				System.out.println();
			}
		}
		System.out.println();
		System.out.println("-------------------\n");
	}

	// 몬스터를 공격한다.
	public void attack(Monster m) { // 주체가 필요함 - 어떤 몬스터에 공격을 했는지
		int damage = this.att - m.def; // 캐릭터 공격력 - 몬스터 방어력(데미지)

		// 몬스터에 데미지가 들어갔는데 0과 같고 음수값의 데미지인 경우 1을 설정
		// 그렇지 않은 경우엔 계산된 데미지로 설정
		damage = damage <= 0 ? 1 : damage;
		m.hp -= damage; // 몬스터 Hp - 데미지
		m.hp = m.hp < 0 ? 0 : m.hp;

		// 공격 후, 내용 출력
		System.out.printf("%s(이)가 공격으로 %s에게 %s만틈 데미지를 주었습니다.", this.name, m.name, damage);
		System.out.printf("%s의 남은 체력은 HP : %d\n", m.name, m.hp);
		System.out.println();
	}

	// 캐릭터 경험치 얻기
	public void getExp(int epx) {
		System.out.println(exp + "의 경험치를 획득하였습니다. \n");

		this.exp += exp; // 캐릭터 경험치 설정
		while (this.exp >= this.nextExp) {
			// 현재 경험치 - 다음 레벨업에 필요한 경험치 (레벨업 후 남은 경험치)
			this.exp -= this.nextExp;
			levelUp(); // 레벨업
		}

	}

	// 캐릭터 레벨업 메소드
	public void levelUp() {
		this.lv++;
		this.maxHp += 20;
		this.hp = this.maxHp;
		this.maxMp += 10;
		this.mp = this.maxMp;
		this.att += 10;
		this.def += 10;
		this.nextExp = (this.lv * 100);
		this.maxFishing = (this.fishing * 100);
		System.out.println("LEVEL UP !!!");
	}

	// 아이템 얻기
	public void getItem(Item i) {
		System.out.println(i.name + "을(를) 획득하였습니다 !");
		// 아이템을 얻을 때 인벤토리 공간을 넓혀준다.
		Item[] tmp = new Item[this.inven.length + 1];

		for (int z = 0; z < this.inven.length; z++) {
			tmp[z] = this.inven[z];
		}
		// 마지막으로 얻은 아이템을 임시공간 제일 끝자락에 추가한다.
		tmp[tmp.length - 1] = i;
		this.inven = tmp;

		// 아이템 얻고 나서 캐릭터에 이득 준다.
		this.maxHp += i.maxHp;
		this.maxMp += i.maxMp;
		this.att += i.att;
		this.def += i.def;
		this.fishing += i.fishing;
		this.maxFatigue += i.maxFatigue;
	}

	// 낚시 숙련도를 올리기 위한 기능
	public void pointUp(int point) {// 낚시 관련 숙련도 레벨 체크
		if (this.pointFishing >= maxFishing) {
			// 낚시 숙련도가 max 까지 다다랐을 때 다시 0부터 초기화
			this.pointFishing -= maxFatigue;
			this.fishing += 1;
		}
	}

	// 낚시 - 잡기
	public void pull(Fish h) {
		int damage = 0;
		// 몬스터에 데미지 줌 !
		if (this.att >= h.def) {
			damage = new Random().nextInt((this.att - h.def) + 1);
		}
		damage = damage <= 0 ? 1 : damage; // damage가 0보다 낮을 때는 ? 1이 들어가고, 그렇지 않을때는 damage
		h.hp -= damage;
		h.hp = h.hp < 0 ? 0 : h.hp;
		System.out.printf("%s의 힘이빠진게 느껴집니다! \n", h.name);
		System.out.printf("%s의 남은 체력은 HP : %d\n", h.name, h.hp);
		pointUp(pointFishing);
		System.out.println();
	}

	public void clearConsole() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
}
