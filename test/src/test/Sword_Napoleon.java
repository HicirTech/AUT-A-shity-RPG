package test;

public class Sword_Napoleon extends Sword{


	Sword_Napoleon(Level currentLevel) 
	{
		super(currentLevel);
		super.setSwordName("Napoleon¡¯s Sword");
		this.setAtkBoot(5);
		this.getCurrentLevel().getInLevelLocation()[7][0].setHasSword(true);
	}
}
