package test;

public class Monster extends Character{
	
	Monster(Level thisLevel)
	{
		super(thisLevel);
		this.getCurrentLevel().getInLevelLocation()[7][7].setHasMonster(true);
		this.setCurrentX(7);
		this.setCurrentY(7);
	}
	public void Attack(Player player)
	{
		player.setHeath(player.getHeath()-5);
		player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].setHasPlayer(false);
		player.setCurrentX(player.getCurrentX()-1);
		player.setCurrentY(player.getCurrentY()-1);
		player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].setHasPlayer(true);
		//this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasMonster(false);
	}
	public void die(Player player)
	{
		player.setMoney(getMoney()+20);
		super.getCurrentLevel().getInLevelLocation()[getCurrentX()][getCurrentY()].setHasMonster(false);
	}
}
