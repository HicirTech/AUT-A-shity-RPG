package v2_test;
/**
 * a abstract Character, now in game have monster, player
 * 
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
public abstract class Character {
	private double heath; //HP of Character when HP reach 0, Character will die
	private double attack; // attack of Character
	private int money;// money the Character carring 
	private int currentX;// X location of this Character
	private int currentY;// Y location of this Character
	private Level currentLevel;// Z location of this Character

	public Character() {
		this.setHeath(100);
		this.setMoney(0);
	}
        
         /**
         * this will set up the health of the character
         * @param heath  health of player
         */
	public void setHeath(double heath) {
		this.heath = heath;
	}
        /**
         * this will set up the attack of the character
         * @param attack of the character
         */
	public void setAttack(double attack) {
		this.attack = attack;
	}
        
        /**
         * this will set up the money of the character
         * @param money of the character
         */
	public void setMoney(int money) {
		this.money = money;
	}
        
         /**
         * this will set up the x axis of the character
         * @param currentX of character
         */
	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}
        
        /**
         * this will set up the y axis of the character
         * @param currentY of character
         */
	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}
        
        /**
         * this will setup the level of the character 
         * @param currentLevel character in 
         */
        public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}
        
        /**
         * this will get the health of the character
         * @return health of character
         */
	public double getHeath() {
		return heath;
	}

        /**
         * this will get the attack of the character
         * @return attack of character
         */
	public double getAttack() {
		return attack;
	}
  
        /**
         * this will get the money of the character
         * @return money of character
         */
	public int getMoney() {
		return money;
	}
      
        /**
         * this will get the x axis of the character
         * @return  axis
         */
	public int getCurrentX() {
		return currentX;
	}
        
         /**
         * this will get the y axis of the character
         * @return of y axis
         */
	public int getCurrentY() {
		return currentY;
	}

        /**
         * get the level of the character
         * @return level of the character
         */
	public Level getCurrentLevel() {
		return currentLevel;
	}
}
