package v2_test;

/**
 * player is a type of character,which user able to control player able to move
 * and for now is the only character able to have item(now only have sword)
 * 
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
public class Player extends Character {
	private boolean hasSword; // is player has sword
	private Sword currentSword;// player can pick sword
	private double attack; // player will have change able attack 

	// use for new game set up
	public Player(Level currentLevel) {
		this.setCurrentLevel(currentLevel);
		super.getCurrentLevel().getInLevelLocation()[0][0].setHasPlayer(true);
		this.setCurrentX(0);
		this.setCurrentY(0);
		this.attack = 10;
		this.setHeath(1000);
	}

	// use for loading from game save
	public Player(Level currentLevel, int xAxis, int yAxis, int Money, int hp, int atk) {
		this.setCurrentLevel(currentLevel);
		this.setMoney(Money);
		super.getCurrentLevel().getInLevelLocation()[xAxis][yAxis].setHasPlayer(true);
		this.setCurrentX(xAxis);
		this.setCurrentY(yAxis);
		this.attack = atk;
		this.setHeath(hp);
		this.setCurrentSword(null);
	}

	/**
         * player can move in the map, each time player can move one way by one step
         * player will not be able to cross wall and player will not be able to go 
         * above the edge of the location
         * @param way control by a s d w keys
         */
	public void move(char way) {
            try {
                if (way == 'w'||way == 'W') {                   
                    this.playerMoveUp();
                } else if (way == 'a'|| way == 'A') {
                    this.playerMoveLeft();
                } else if (way == 'd' || way == 'D') {
                    this.playerMoveRight();
                } else if (way == 's' || way == 'S') {
                   this.playerMoveDown();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("hit edge!");
            }
	}
        
        /**
         * this method will check is player hit wall
         * @return is player hit wall
         */
        private boolean nextIsWall()
        {
            return this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].isHasWall();
        }
        
        /**
         * this method will control player one location right
         */
        private void playerMoveRight()
        {
            this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(false);
            this.setCurrentX(getCurrentX() + 1);
            if (this.getCurrentX() < 11) {
                if (!nextIsWall()) {
                    this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
                } else {
                    this.setCurrentX(this.getCurrentX() - 1);
                    this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
                }
            } else {
                this.setCurrentX(this.getCurrentX() - 1);
                this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
            }
        }
         /**
         * this method will control player one location left
         */
        private void playerMoveLeft()
        {
            
            this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(false);
            this.setCurrentX(getCurrentX() - 1);
            if (this.getCurrentX() > -1) {
                if (!nextIsWall()) {
                    this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
                } else {
                    this.setCurrentX(this.getCurrentX() + 1);
                    this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
                }
            } else {
                this.setCurrentX(getCurrentX() + 1);
                this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
            }
        }
        
        /**
         * this method will control player one location down
         */
        private void playerMoveDown()
        {
            this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(false);
            this.setCurrentY(this.getCurrentY() + 1);
            if (this.getCurrentY() < 11) {
                if (!nextIsWall()) {
                        this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
                } else {
                        this.setCurrentY(this.getCurrentY() - 1);
                        this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
                }
            } else {
                this.setCurrentY(this.getCurrentY() - 1);
                this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
            }
        }

        /**
         * this method will control player one location up
         */
        private void playerMoveUp()
        {
            this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(false);
            this.setCurrentY(this.getCurrentY() - 1);
            if (this.getCurrentY() > -1) {
                if (!nextIsWall()) {
                        this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
                } else {
                        this.setCurrentY(this.getCurrentY() + 1);
                        this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
                }
            } else// hit edge redo setlocation
            {
                this.setCurrentY(this.getCurrentY() + 1);
                this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
            }            
        }
                
	/**
	 * when player pick up sword then the attack of player will be boot up
	 */
	public void setAttackBoot() {
            if (this.isHasSword()) {
                this.attack = this.getCurrentSword().getAtkBoot();
            }
        }
        
        /**
         * is player have a sword?  use for end game
         * @return is player has a sword
         */
	public boolean isHasSword() {
            return hasSword;
	}

        /**
         * Get the attack value of player
         * @return 
         */
        @Override
	public double getAttack() {
            return attack;
	}
        
        /**
         * when player pick up a sword this will be set to true
         * @param hasSword ture if a play have a sword
         */
	public void setHasSword(boolean hasSword) {
            this.hasSword = hasSword;
	}

        /**
         * return a sword object
         * @return a sword which the player using
         */
	public Sword getCurrentSword() {
            return currentSword;
	}
        /**
         * when player pick a sword this will use to set up sword
         * @param currentSword picked up
         */
	public void setCurrentSword(Sword currentSword) {
            this.currentSword = currentSword;
	}
	@Override
	public String toString() {
            return "Player [hasSword=" + hasSword + ", currentSword=" + currentSword + ", attack=" + attack
                    + ", getHeath()=" + getHeath() + ", getMoney()=" + getMoney() + "]";
	}
}
