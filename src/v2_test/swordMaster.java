package v2_test;

/**
 * who able to use this sword must be a master 
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
public class swordMaster extends Sword {
	swordMaster(Level currentLevel)
	{	super(currentLevel);
		super.setSwordName("Master's Sword");
		this.setAtkBoot(70);
	}
	
}
