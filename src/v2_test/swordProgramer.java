package v2_test;
/**
 * a Sword called "Programer's Sword", programmer is the designer of this world
 * it has the highest attack boot, able to kill all monster in the game by 1 hit
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
public class swordProgramer extends Sword{
	swordProgramer(Level currentLevel)
	{
		super(currentLevel);
		super.setSwordName("Programer's Sword");
		this.setAtkBoot(867034512);
	}
}
