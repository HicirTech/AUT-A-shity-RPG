package v2_test;
/**
 * a sword call "Napoleon��s Sword" 
 * it is old and historical, but it is better then the old sword
 * @author Luo Zeting ID:16938158
 *
 */
public class swordNapoleon extends Sword{


	swordNapoleon(Level currentLevel) 
	{
		super(currentLevel);
		super.setSwordName("Napoleon's Sword");
		this.setAtkBoot(30);
	}
}
