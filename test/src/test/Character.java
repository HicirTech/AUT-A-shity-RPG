package test;

public abstract class Character {
	private double heath;
	private double attack;
	private int money;
	private int currentX;
	private int currentY;
	private Level currentLevel;

	Character(Level theLevel)
	{
		this.setHeath(100);
		this.setAttack(5);
		this.setMoney(0);
		this.setCurrentLevel(theLevel);
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
	public void setAttack(double attack) 
	{
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
	
	
	
}
