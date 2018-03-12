package v2_test;

public class Monster extends Character{
	
	Monster(Level thisLevel)
	{
		this.setCurrentLevel(thisLevel);
	}
	
	
	public void Attack(Player player)
	{
		player.setHeath(player.getHeath()-this.getAttack());
		player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].setHasPlayer(false);
		player.setCurrentX(player.getCurrentX()-(int)(Math.random()*1.1+0.5));
		player.setCurrentY(player.getCurrentY()-(int)(Math.random()*1.1+0.5));
		player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].setHasPlayer(true);
		System.out.println(String.format("Monster attacked you!!!\nYou lost %d heath",(int)this.getAttack()));
	}
	public void setMonster(int xAxis, int yAxis)
	{
		this.setCurrentX(xAxis);
		this.setCurrentY(yAxis);
		super.getCurrentLevel().getInLevelLocation()[xAxis][xAxis].setHasMonster(true);
	}
	public void die(Player player)
	{
		player.setMoney(getMoney()+20);
		super.getCurrentLevel().getInLevelLocation()[getCurrentX()][getCurrentY()].setHasMonster(false);
	}
	@Override
	public void move(char way) {
		// TODO Auto-generated method stub
		
	}
}
