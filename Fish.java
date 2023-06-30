package kr.or.ddit.game;

public class Fish {
	String name;
	int maxHp, hp, def;

	public Fish(String name, int maxHp, int def) {
		this.name = name;
		this.maxHp = maxHp;
		this.hp = this.maxHp;
		this.def = def;

	}

	public void showInfo() {
		System.out.println("----- 상태 ------");
		System.out.printf("이 름 : %s\n", this.name);
		System.out.printf("체 력 : %d / %d\n", this.hp, this.maxHp);
		System.out.printf("방 어 : %d\n", this.def);
		System.out.println("---------------------------\n");
	}

	// 물고기 자기 자신이 아이템이 되어 떨궈진다
	// 낚시 후 얻게 될 물고기 아이템
	public Item dropItem() {
		Item fish = new Item(this.name);
		return fish;
	}

}
