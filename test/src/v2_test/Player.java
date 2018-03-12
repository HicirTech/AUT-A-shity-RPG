package v2_test;

public  class Player extends Character {
	private boolean hasSword;
	private Sword currentSword;
	private double attack;
	Player(Level currentLevel)
	{
		this.setCurrentLevel(currentLevel);
		super.getCurrentLevel().getInLevelLocation()[0][0].setHasPlayer(true);
		this.setCurrentX(0);
		this.setCurrentY(0);
		this.attack=5;
	}
	
	
	
	public boolean isHasSword() {
		return hasSword;
	}


	public double getAttack() {
		return attack;
	}

	public void setAttackBoot() {
		if(this.isHasSword())
		{
			this.attack = 5*this.getCurrentSword().getAtkBoot();
		}
		else
		{
			this.attack = 5;
		}
		
	}

	public void setHasSword(boolean hasSword) {
		this.hasSword = hasSword;
	}


	public Sword getCurrentSword() {
		return currentSword;
	}


	public void setCurrentSword(Sword currentSword) {
		this.currentSword = currentSword;
	}

	@Override
	public void move(char way)
	{
		try
		{
			if(way=='w')
			{
				this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(false);
				this.setCurrentY(this.getCurrentY()-1);
					if(this.getCurrentY()>-1)
					{
						if(!this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].isHasWall())
						{
							this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
						}
						else
						{
							this.setCurrentY(this.getCurrentY()+1);;
							this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
						}
					}
					else//hit edge redo setlocation
					{
						this.setCurrentY(this.getCurrentY()+1);
						this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
					}
			}
			else if(way=='a')
			{
				this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(false);
				this.setCurrentX(getCurrentX()-1);
				if(this.getCurrentX()>-1)
				{
					if(!this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].isHasWall())
					{
						this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
					}
					else
					{
						this.setCurrentX(this.getCurrentX()+1);;
						this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
					}
				}
				else
				{
					this.setCurrentX(getCurrentX()+1);
					this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
				}
			}
			else if(way=='d')
			{
				this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(false);
				this.setCurrentX(getCurrentX()+1);
				if(this.getCurrentX()<10)
				{
					if(!this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].isHasWall())
					{
						this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
					}
					else
					{
						this.setCurrentX(this.getCurrentX()-1);
						this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
					}			
				}
				else
				{
					this.setCurrentX(this.getCurrentX()-1);
					this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
				}
			}
			else if(way=='s')
			{
				this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(false);
				this.setCurrentY(this.getCurrentY()+1);
				if(this.getCurrentY()<10)
				{
					if(!this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].isHasWall())
					{
						this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
					}
					else
					{
						this.setCurrentY(this.getCurrentY()-1);;
						this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
					}
				}
				else
				{
					this.setCurrentY(this.getCurrentY()-1);
					this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasPlayer(true);
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("hit edge!");
		}
	}

	public String toString()
	{
		return String.format("%d", this.getHeath());
	}



	
}
