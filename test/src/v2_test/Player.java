package v2_test;

/**
 * player is a type of character,which user able to contral player able to move
 * and for now is the only character able to have item(now only have sword)
 * 
 * @author Luo Zeting ID:16938158
 *
 */
public class Player extends Character {
	private boolean hasSword;
	private Sword currentSword;
	private double attack;

	// use for new game set up
	Player(Level currentLevel) {
		this.setCurrentLevel(currentLevel);
		super.getCurrentLevel().getInLevelLocation()[0][0].setHasPlayer(true);
		this.setCurrentX(0);
		this.setCurrentY(0);
		this.attack = 10;
		this.setHeath(1000);
	}

	// use for loading from game save
	Player(Level currentLevel, int xAxis, int yAxis, int Money, int hp, int atk) {
		this.setCurrentLevel(currentLevel);
		super.getCurrentLevel().getInLevelLocation()[xAxis][yAxis].setHasPlayer(true);
		this.setCurrentX(xAxis);
		this.setCurrentY(yAxis);
		this.attack = atk;
		this.setHeath(hp);
		this.setCurrentSword(null);
	}

	/**
	 * when player pick up sword then the attack of player will be boot up
	 */
	public void setAttackBoot() {
		if (this.isHasSword()) {
			this.attack = this.getCurrentSword().getAtkBoot();
		}

	}

	public boolean isHasSword() {
		return hasSword;
	}

	public double getAttack() {
		return attack;
	}

	public void setHasSword(boolean hasSword) {
		this.hasSword = hasSword;
	}

	public Sword getCurrentSword() {
		return currentSword;
	}

	public void setCurrentSword(Sword currentSword) {
		this.currentSword = currentSword;
	}

	/**
	 * in this move method, player is set not able to go pass the wall
	 */
	@Override
	public void move(char way) {
		try {
			if (way == 'w') {
				this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(false);
				this.setCurrentY(this.getCurrentY() - 1);
				if (this.getCurrentY() > -1) {
					if (!this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
							.isHasWall()) {
						this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
								.setHasPlayer(true);
					} else {
						this.setCurrentY(this.getCurrentY() + 1);
						;
						this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
								.setHasPlayer(true);
					}
				} else// hit edge redo setlocation
				{
					this.setCurrentY(this.getCurrentY() + 1);
					this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
							.setHasPlayer(true);
				}
			} else if (way == 'a') {
				this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(false);
				this.setCurrentX(getCurrentX() - 1);
				if (this.getCurrentX() > -1) {
					if (!this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
							.isHasWall()) {
						this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
								.setHasPlayer(true);
					} else {
						this.setCurrentX(this.getCurrentX() + 1);
						;
						this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
								.setHasPlayer(true);
					}
				} else {
					this.setCurrentX(getCurrentX() + 1);
					this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
							.setHasPlayer(true);
				}
			} else if (way == 'd') {
				this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(false);
				this.setCurrentX(getCurrentX() + 1);
				if (this.getCurrentX() < 11) {
					if (!this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
							.isHasWall()) {
						this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
								.setHasPlayer(true);
					} else {
						this.setCurrentX(this.getCurrentX() - 1);
						this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
								.setHasPlayer(true);
					}
				} else {
					this.setCurrentX(this.getCurrentX() - 1);
					this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
							.setHasPlayer(true);
				}
			} else if (way == 's') {
				this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(false);
				this.setCurrentY(this.getCurrentY() + 1);
				if (this.getCurrentY() < 11) {
					if (!this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
							.isHasWall()) {
						this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
								.setHasPlayer(true);
					} else {
						this.setCurrentY(this.getCurrentY() - 1);
						;
						this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
								.setHasPlayer(true);
					}
				} else {
					this.setCurrentY(this.getCurrentY() - 1);
					this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()]
							.setHasPlayer(true);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("hit edge!");
		}
	}

	@Override
	public String toString() {
		return "Player [hasSword=" + hasSword + ", currentSword=" + currentSword + ", attack=" + attack
				+ ", getHeath()=" + getHeath() + ", getMoney()=" + getMoney() + "]";
	}

}
