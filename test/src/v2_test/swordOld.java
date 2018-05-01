package v2_test;
/**
 * a old Sowrd only boot up 20 attack
 * @author Luo Zeting ID:16938158
 *
 */
public class swordOld extends Sword {
	swordOld(Level currentLevel)
	{
		super(currentLevel);
		super.setSwordName("Old Sword");
		this.setAtkBoot(20);
	}

}
