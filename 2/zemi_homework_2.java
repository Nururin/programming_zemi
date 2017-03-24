import java.io.*;
public class zemi_homework_2 {
	public static void main(String[] args) throws IOException {
		battleUser user1, user2;
		user1 = new battleUser("自分");
		user2 = new battleUser("敵");
		battle(user1, user2);
	}
	private static void battle (battleUser user1, battleUser user2) throws IOException {
		String name1, name2;
		name1 = user1.getName();
		name2 = user2.getName();
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in), 1);
		System.out.println("ターン数を入力してください");
		int turnCount = Integer.parseInt(r.readLine());
		for (int i=0; i<turnCount; i++) {
			attack(user1, user2);
			if (user2.getHp() == 0) {
				break;
			}
			attack(user2, user1);
			if (user1.getHp() == 0) {
				break;
			}
		}
		if (user1.getHp() > user2.getHp()) {
			System.out.println(user1.getName() + "の勝ち");
		} else if (user1.getHp() < user2.getHp()) {
			System.out.println(user2.getName() + "の勝ち");
		} else {
			System.out.println("引き分け");
		}
	}
	private static void attack (battleUser atkUser, battleUser defUser) {
		defUser.hit(atkUser.getAtk());
		System.out.println(
			defUser.getName() + "は" +
			atkUser.getAtk() + "のダメージを受けた。 残りHP" +
			defUser.getHp()
		);
	}
}

class battleUser {
	private String name;
	private int hitPoint;
	private int attack;
	battleUser (String n, int hp, int a) {
		name = n;
		hitPoint = hp;
		attack = a;
	}
	battleUser (String userTitle) throws IOException {
		System.out.println(userTitle + "の名前を入力してください");
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in), 1);
		name = r.readLine();
		System.out.println(userTitle + "のHPを入力してください");
		hitPoint = Integer.parseInt(r.readLine());
		System.out.println(userTitle + "の攻撃力を入力してください");
		attack = Integer.parseInt(r.readLine());
	}

	void hit(int damage) {
		this.hitPoint -= damage;
	}
	int getAtk() {
		return this.attack;
	}
	int getHp() {
		if (this.hitPoint < 0) {
			return 0;
		} else {
			return this.hitPoint;
		}
	}
	String getName() {
		return this.name;
	}
}