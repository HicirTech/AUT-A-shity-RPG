package test;

public abstract class  Sword {
	private int atkBoot;
	private String swordName;
	private Level currentLevel;
	
	Sword(Level theLevel)
	{
		this.setCurrentLevel(theLevel);
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}
	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}
	public int getAtkBoot() {
		return atkBoot;
	}

	public void setAtkBoot(int atkBoot) {
		this.atkBoot = atkBoot;
	}

	public String getSwordName() {
		return swordName;
	}

	public void setSwordName(String swordName) {
		this.swordName = swordName;
	}
	
	public void pickUp(Player player)
	{
		player.setCurrentSword(this);
		player.setHasSword(true);
		player.setAttackBoot();
		this.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].setHasSword(false);
	}
	public String toString()
	{
		return this.swordName;
	}
	
}
