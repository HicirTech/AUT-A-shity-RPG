package v2_test;

/**
 * this is a location in a level, it works like a pixel different type of
 * location will contain different toString output
 * 
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */

public class Location {
	private int xAxis;
	private int yAxis;
        
	private boolean hasPlayer;
	private boolean hasWall;
	private boolean hasSword;
	private boolean hasMonster;
	private boolean hasUpStairs;
	private boolean hasDownStairs;
	private boolean hasSavePoint;

	public Location() {
		this.setxAxis(-1);
		this.setyAxis(-1);
	}

	public Location(int xAxis, int yAxis) {
		this.setxAxis(xAxis);
		this.setyAxis(yAxis);
		this.setHasPlayer(false);
	}

        /**
         * this method will show the location what it is
         * @return the location icon
         */
        public String toString() {
            if (this.hasPlayer) {
                    return " o ";
            } else if (this.hasWall) {
                    return "###";
            } else if (this.hasMonster) {
                    return " * ";
            } else if (this.hasSword) {
                    return " S ";
            } else if (this.hasUpStairs) {
                    return "+++";
            } else if (this.hasDownStairs) {
                    return "---";
            } else {
                    return "___";
            }
	}
        
//----------getter and setter------------
	public int[] getUpperLocation() {
		int[] a = { this.xAxis, this.yAxis };
		return a;
	}
	public int getxAxis() {
		return xAxis;
	}
	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}
	public int getyAxis() {
		return yAxis;
	}
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}
	public boolean isHasPlayer() {
		return hasPlayer;
	}
	public void setHasPlayer(boolean hasPlayer) {
		this.hasPlayer = hasPlayer;
	}
	public boolean isHasWall() {
		return hasWall;
	}
	public void setHasWall(boolean hasWall) {
		this.hasWall = hasWall;
	}
	public boolean isHasUpStairs() {
		return hasUpStairs;
	}
	public void setHasUpStairs(boolean hasUpStairs) {
		this.hasUpStairs = hasUpStairs;
	}
	public boolean isHasDownStairs() {
		return hasDownStairs;
	}
	public void setHasDownStairs(boolean hasDownStairs) {
		this.hasDownStairs = hasDownStairs;
	}
	public boolean isHasMonster() {
		return hasMonster;
	}
	public void setHasMonster(boolean hasMonster) {
		this.hasMonster = hasMonster;
	}
	public boolean isHasSword() {
		return hasSword;
	}
	public void setHasSword(boolean hasSword) {
		this.hasSword = hasSword;
	}
	public boolean isHasSavePoint() {
		return hasSavePoint;
	}
	public void setHasSavePoint(boolean hasSavePoint) {
		this.hasSavePoint = hasSavePoint;
	}        
}
