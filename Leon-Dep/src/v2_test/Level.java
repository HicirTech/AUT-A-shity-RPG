package v2_test;

/**
 * this is a Level class, level contains 11x11 locations by use those
 * combination of locations, a 2D map will be show player can move in the 2D
 * level, also level up or down by this way a 3D space will be show
 * 
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
public class Level {
	private int level;
	private Location inLevelLocation[][];
	private Player player;
	
	public Level() {
            this.inLevelLocation = new Location[11][11];
            for (int yAxis = 0; yAxis != 11; yAxis++) {
                for (int xAxis = 0; xAxis != 11; xAxis++) {
                    this.getInLevelLocation()[xAxis][yAxis] = new Location(xAxis, yAxis);
                }
            }
	}

        /**
         * this method will look for upper stair in next level
         * @return the location of the stair
         */
	public Location lookForUpper() {
            Location tempLocation = new Location();
            for (int xAxis = 0; xAxis != 11; xAxis++) {
                for (int yAxis = 0; yAxis != 11; yAxis++) {
                    if (this.inLevelLocation[xAxis][yAxis].isHasUpStairs()) {
                        tempLocation = this.inLevelLocation[xAxis][yAxis];
                    }
                }
            }
            return tempLocation;
	}

        /**
         * this method will look for lower stair in next level
         * @return the location of the stair
         */
	public Location lookForLower() {
            Location tempLocation = new Location();
            for (int xAxis = 0; xAxis != 11; xAxis++) {
                for (int yAxis = 0; yAxis != 11; yAxis++) {
                    if (this.inLevelLocation[xAxis][yAxis].isHasDownStairs()) {
                        tempLocation = this.inLevelLocation[xAxis][yAxis];
                    }
                }
            }
            return tempLocation;
	}
        
        /**
         * this method will return a string shows the map
         * @return 
         */
	public String toString() {
            String returning = "___________________________________\n";
            for (int yAxis = 0; yAxis != 11; yAxis++) {
                for (int xAxis = 0; xAxis != 11; xAxis++) {
                    if (xAxis == 0) {
                            returning = returning + "|" + this.getInLevelLocation()[xAxis][yAxis].toString();
                    } else if (xAxis == 10) {
                            returning = returning + this.getInLevelLocation()[xAxis][yAxis].toString() + "|";
                    } else

                            returning = returning + "" + this.getInLevelLocation()[xAxis][yAxis].toString();
                }
                if (yAxis == 1) {
                    returning = returning + "Player information:" + "		" + "Lables:";
                } else if (yAxis == 2) {
                    returning = returning + "heath: " + player.getHeath() + "		" + "o:Player";
                } else if (yAxis == 3) {
                    returning = returning + "money: " + player.getMoney() + "			" + "*:Monster";
                } else if (yAxis == 4) {
                    returning = returning + "Your location is: Level " + player.getCurrentLevel().getLevel() + "	"
                                    + "#:Walls";
                } else if (yAxis == 5 && this.player.isHasSword()) {
                    returning = returning + "You are using: " + player.getCurrentSword() + " +++:GoUp";
                } else if (yAxis == 5) {
                    returning = returning + "				" + "+++:GoUp";
                } else if (yAxis == 6) {
                    returning = returning + "Your ATK is: " + this.player.getAttack() + "		" + "---:GoDown";
                }
                returning = returning + "\n";
            }
            return returning ;
	}
//---------------getter and sette--------------
        public void setPlayer(Player player) {
            this.player = player;
	}                
        public void setInLevelLocation(Location[][] inLevelLocation) {
		this.inLevelLocation = inLevelLocation;
	}
	public Player getPlayer()
	{
            return this.player;
	}
	public int getLevel() {
            return level;
	}
	public void setLevel(int level) {
            this.level = level;
	}
	public Location[][] getInLevelLocation() {
            return inLevelLocation;
	}
}
