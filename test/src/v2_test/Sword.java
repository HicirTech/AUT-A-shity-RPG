package v2_test;

/**
 * Sword is a item in the game in the game sword will boot up attack how pick up
 * it
 * 
 * @author Luo Zeting ID:16938158
 *
 */
public abstract class Sword {
	private int atkBoot;
	private String swordName;
	private Level currentLevel;

	Sword(Level theLevel) {
		this.setCurrentLevel(theLevel);
	}

	public void setSword(int xAxis, int yAxis) {
		this.getCurrentLevel().getInLevelLocation()[xAxis][yAxis].setHasSword(true);
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

	public void pickUp(Player player) {
		player.setCurrentSword(this);
		player.setHasSword(true);
		player.setAttackBoot();
		this.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].setHasSword(false);
	}

	public String toString() {
		return this.swordName;
	}

}
