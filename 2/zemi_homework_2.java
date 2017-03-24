import java.io.*;
import java.util.Random;
public class zemi_homework_2 {
	public static void main(String[] args) throws IOException {
		battleUser user1, user2;
		user1 = new battleUser("自分");
		user2 = new battleUser("敵");
		battle(new battleUser[] { user1, user2 });
	}
	private static void battle (battleUser users[]) throws IOException {
		int i, atkUser, defUser, winUser;
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in), 1);
		System.out.println("ターン数を入力してください");
		int turnCount = Integer.parseInt(r.readLine());
		Random rnd = new Random();
		int firstAttack = rnd.nextInt(2);
		int firstDefense = (firstAttack + 1) % users.length;
		for (i=0; i<users.length*turnCount; i++) {
			atkUser = ((i + firstAttack) % users.length);
			defUser = ((i + firstDefense) % users.length);
			attack(users[atkUser], users[defUser]);
			if (users[defUser].getHp() == 0) {
				break;
			}
		}
		winUser = 0;
		int winUserHp = 0;
		for (i=0; i<users.length; i++) {
			if (users[i].getHp() > winUserHp) {
				winUser = i;
				winUserHp = users[i].getHp();
			}
		}
		System.out.println(users[winUser].getName() + "の勝ち");
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
	private boolean buffed;
	private static final double buffRate = 1.2;
	battleUser (String n, int hp, int a) {
		name = n;
		hitPoint = hp;
		attack = a;
		buffed = false;
	}
	battleUser (String userTitle) throws IOException {
		System.out.println(userTitle + "の名前を入力してください");
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in), 1);
		name = r.readLine();
		System.out.println(userTitle + "のHPを入力してください");
		hitPoint = Integer.parseInt(r.readLine());
		System.out.println(userTitle + "の攻撃力を入力してください");
		attack = Integer.parseInt(r.readLine());
		System.out.println(userTitle + "のバフスキル発動フラグを入力してください");
		buffed = Boolean.valueOf(r.readLine());
	}

	void hit(int damage) {
		this.hitPoint -= damage;
	}
	int getAtk() {
		if (this.buffed) {
			return (int) ((double) this.attack * this.buffRate);
		} else {
			return this.attack;
		}
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
