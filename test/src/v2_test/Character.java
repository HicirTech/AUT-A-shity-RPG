package v2_test;

/**
 * a abstract Character, now in game have monste, player, and trader 3 type of
 * character future may have more
 * 
 * @author Luo Zeting ID:16938158
 * @version 1.0
 */
public abstract class Character {
	private double heath;
	private double attack;
	private int money;
	private int currentX;
	private int currentY;
	private Level currentLevel;

	Character() {
		this.setHeath(100);
		this.setMoney(0);
	}

	public double getHeath() {
		return heath;
	}

	public void setHeath(double heath) {
		this.heath = heath;
	}

	public double getAttack() {
		return attack;
	}

	public void setAttack(double attack) {
		this.attack = attack;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getCurrentX() {
		return currentX;
	}

	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}

	public int getCurrentY() {
		return currentY;
	}

	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}

	public abstract void move(char way);// only player able to move

}
