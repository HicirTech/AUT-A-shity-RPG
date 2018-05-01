package v2_test;

import java.util.*;
import java.io.*;

/**
 * A world have 10 level and each level have 11*11 location, use this world can
 * show a 3D space
 * 
 * @author Luo Zeting ID:16938158
 *
 */
public class World {

	private Player player;
	private Monster m[] = new Monster[20];// max for 20 monsters
	private Level inGameLevel[];
	private Sword s;
	private PrintWriter playersaver;
	private Scanner stairUp;
	private Scanner stairDown;
	private Scanner monster;
	private Scanner map;
	private Scanner playerLoad;
	private Scanner story;
        private Scanner end;
	World(boolean load) {// load from file or not

		if (load == false) {// if not load from file then start a new game
			try {
				map = new Scanner(new File("map.txt"));
				monster = new Scanner(new File("monster.txt"));
				stairUp = new Scanner(new File("stairUp.txt"));
				stairDown = new Scanner(new File("stairDown.txt"));
				story = new Scanner(new File("story.txt"));
                                end=new Scanner(new File("end.txt"));
				storyReader();
			} catch (FileNotFoundException e) {// map set up file is not found
				System.out.println("File error, please check your game files");
			}

			this.MapManager();
			this.player = new Player(this.getInGameLevel()[0]);
			this.player.setCurrentLevel(this.inGameLevel[0]);
			this.inGameLevel[0].setPlayer(player);
			this.SwordManager();
			this.WorldStart();
		}

		else {

			try {
				map = new Scanner(new File("map.txt"));
				monster = new Scanner(new File("monster.txt"));
				stairUp = new Scanner(new File("stairUp.txt"));
				stairDown = new Scanner(new File("stairDown.txt"));
				playerLoad = new Scanner(new File("player.txt"));
				end=new Scanner(new File("end.txt"));
				this.MapManager();
				try {
					String next;
					next = playerLoad.nextLine();
					this.player = new Player(this.getInGameLevel()[Integer.valueOf(next.split(",")[0])],
							Integer.valueOf(next.split(",")[1]), Integer.valueOf(next.split(",")[2]),
							Integer.valueOf(next.split(",")[3]), Integer.valueOf(next.split(",")[4]),
							Integer.valueOf(next.split(",")[5]));
					this.inGameLevel[Integer.valueOf(next.split(",")[0])].setPlayer(player);
					playerLoad.close();
				} catch (NoSuchElementException e) {
					System.out.println("Gamer Save not error! Setting up new game");
					this.player = new Player(this.getInGameLevel()[0]);
					this.player.setCurrentLevel(this.inGameLevel[0]);
					this.inGameLevel[0].setPlayer(player);
				}
				this.SwordManager();
				this.WorldStart();
			} catch (FileNotFoundException e) {
				System.out.println("Gamer Save not found! Setting up new game");
				try {
					map = new Scanner(new File("map.txt"));
					monster = new Scanner(new File("monster.txt"));
					stairUp = new Scanner(new File("stairUp.txt"));
					stairDown = new Scanner(new File("stairDown.txt"));
					story = new Scanner(new File("story.txt"));
                                        end=new Scanner(new File("end.txt"));
					storyReader();
				} catch (FileNotFoundException a) {
					System.out.println("File error, please check your game files");
				}
				this.MapManager();
				this.player = new Player(this.getInGameLevel()[0]);
				this.player.setCurrentLevel(this.inGameLevel[0]);
				this.inGameLevel[0].setPlayer(player);
				this.WorldStart();

			}
		}

	}
	/**
         * read story at begin
         */
	private void storyReader()
	{
		String next = null;
		do {
			try {
				next = story.nextLine();
				System.out.println(next);
				Thread.sleep(1000);
			} catch (NoSuchElementException e) {
				break;
			} catch (InterruptedException e) {}
		} while (next!=null);
	}
        
        /**
         * end story reading story
         */
        private void endReader()
	{
		String next = null;
		do {
			try {
				next = end.nextLine();
				System.out.println(next);
				Thread.sleep(1000);
			} catch (NoSuchElementException e) {
				break;
			} catch (InterruptedException e) {}
		} while (next!=null);
	}
        private void endGamechecker()
        {
            if(player.isHasSword())
            {
            if(player.getCurrentSword().getSwordName().toLowerCase().contains("programer"))
            {
                this.endReader();
            }
            }
        }

	/**
	 * this method will keep runing the world until player die
	 */
	private void WorldStart() {
		System.out.println(this.getPlayer().getCurrentLevel());
		do {
			try {
				player.move(new Scanner(System.in).next().charAt(0));
				checkList();
				System.out.println(this.getPlayer().getCurrentLevel());
			} catch (NullPointerException e) {//next step
				player.move(new Scanner(System.in).next().charAt(0));
				checkList();
				System.out.println(this.getPlayer().getCurrentLevel());
			}
		} while (this.getPlayer().getHeath() > 0);
		System.out.println("You died, the world is over!");
	}

	/**
	 * when sword picked up happen
	 */
	private void swordPickUpEevent() {
		if (player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].isHasSword()) {
			s.pickUp(player);
                        player.setHasSword(true);
		}
	}

	/**
	 * use at stage 2 when player go in a check point location then save
	 * (checked this method is works ok)
	 */
	private void savePointEevent() {
		if (player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()]
				.isHasSavePoint()) {
			try {
				playersaver = new PrintWriter(new File("player.txt"));
				playersaver.println(this.player.getCurrentLevel().getLevel() + "," + this.player.getCurrentX() + ","
						+ this.player.getCurrentY() + "," + this.player.getMoney() + ","
						+ String.format("%d", this.player.getHeath()) + ","
						+ String.format("%d", this.player.getAttack()));
				playersaver.flush();
				playersaver.close();
			} catch (FileNotFoundException e) {
                             System.out.println("New game save created");
			}
		}
	}

	/**
	 * save player into file this action will happen each time player go up or
	 * down level
	 */
	public void saveEvent() {
		try {
			playersaver = new PrintWriter(new File("player.txt"));
			playersaver.println(this.player.getCurrentLevel().getLevel() + "," + this.player.getCurrentX() + ","
					+ this.player.getCurrentY() + "," + this.player.getMoney() + "," + (int) this.player.getHeath()
					+ "," + (int) this.player.getAttack());
			playersaver.flush();
			playersaver.close();
		} catch (FileNotFoundException e) {
                    System.out.println("New game save created");
		}
	}

	/**
	 * check player and monster location if player location == monster location
	 * then fight will happen
	 * 
	 * @param monsterNo
	 *            of monster
	 */
	private void monsterEvent(int monsterNo) {
		try {
			if (player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()]
					.isHasMonster()) {

				m[monsterNo].Attack(player);
				m[monsterNo].setHeath(m[monsterNo].getHeath() - player.getAttack());
				if (!(m[monsterNo].getHeath() > 0)) {
					m[monsterNo].die(player);
					this.getInGameLevel()[player.getCurrentLevel().getLevel()].getInLevelLocation()[player
							.getCurrentX()][player.getCurrentY()].setHasMonster(false);
					this.getInGameLevel()[player.getCurrentLevel().getLevel()].getInLevelLocation()[player
							.getCurrentX()][player.getCurrentY()].setHasPlayer(true);
					m[monsterNo] = null; // monster die
				}
			}
		} catch (NullPointerException e) {
                    System.out.println("catch:Null catch");
		}
	}

	/**
	 * manage player's level up or down when player go up stair then it will
	 * look for next level's down stair when player go down stair then it will
	 * look for last level's up stair also every time player change level, then
	 * it will save player to file also sword will be reflash
	 */
	private void levelChangeChecker() {
		if (getPlayer().getCurrentLevel().getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()]
				.isHasUpStairs()) {
			getInGameLevel()[getPlayer().getCurrentLevel().getLevel() + 1].setPlayer(getPlayer());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer()
					.getCurrentX()][getPlayer().getCurrentY()].setHasPlayer(false);
			getPlayer().setCurrentLevel(getInGameLevel()[getPlayer().getCurrentLevel().getLevel() + 1]);
			this.player.setCurrentX(getPlayer().getCurrentLevel().lookForLower().getxAxis());
			this.player.setCurrentY(getPlayer().getCurrentLevel().lookForLower().getyAxis());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer()
					.getCurrentLevel().lookForLower().getxAxis()][getPlayer().getCurrentLevel().lookForLower()
							.getyAxis()].setHasPlayer(true);
			this.SwordManager();
			this.saveEvent();
		} else if (getPlayer().getCurrentLevel().getInLevelLocation()[getPlayer().getCurrentX()][getPlayer()
				.getCurrentY()].isHasDownStairs()) {
			getInGameLevel()[getPlayer().getCurrentLevel().getLevel() - 1].setPlayer(getPlayer());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer()
					.getCurrentX()][getPlayer().getCurrentY()].setHasPlayer(false);
			getPlayer().setCurrentLevel(getInGameLevel()[getPlayer().getCurrentLevel().getLevel() - 1]);
			this.player.setCurrentX(getPlayer().getCurrentLevel().lookForUpper().getxAxis());
			this.player.setCurrentY(getPlayer().getCurrentLevel().lookForUpper().getyAxis());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer()
					.getCurrentLevel().lookForUpper().getxAxis()][getPlayer().getCurrentLevel().lookForUpper()
							.getyAxis()].setHasPlayer(true);
			this.SwordManager();
			this.saveEvent();
		}

	}

	/**
	 * create 11 new levels and load wall location from file also call up
	 * upStair and downStair and monster setup method
	 */
	private final void MapManager() {
		this.inGameLevel = new Level[11];

		for (int levelSet = 0; levelSet != 11; levelSet++) {
			this.inGameLevel[levelSet] = new Level();
			this.inGameLevel[levelSet].setLevel(levelSet);
		}
		String next;
		do {
			try {
				next = map.nextLine();
				if (next != null) {
					this.inGameLevel[Integer.valueOf(next.split(",")[0])].getInLevelLocation()[Integer
							.valueOf(next.split(",")[1])][Integer.valueOf(next.split(",")[2])].setHasWall(true);
				} else {
					break;
				}
			} catch (NoSuchElementException e) {
				break;
			}
		} while (true);

		this.upStairManager();
		this.downStairManager();
		this.MonsterManager();

	}

	/**
	 * load upStair location from file
	 */
	private final void upStairManager() {
		String next;
		do {
			try {
				next = stairUp.nextLine();
				if (next != null) {
					this.inGameLevel[Integer.valueOf(next.split(",")[0])].getInLevelLocation()[Integer
							.valueOf(next.split(",")[1])][Integer.valueOf(next.split(",")[2])].setHasUpStairs(true);
				} else {
					break;
				}
			} catch (NoSuchElementException e) {
				break;
			}
		} while (true);

	}

	/**
	 * load downStair location from file
	 */
	private final void downStairManager() {
		String next;

		do {
			try {
				next = stairDown.nextLine();
				if (next != null) {
					this.inGameLevel[Integer.valueOf(next.split(",")[0])].getInLevelLocation()[Integer
							.valueOf(next.split(",")[1])][Integer.valueOf(next.split(",")[2])].setHasDownStairs(true);
				} else {
					break;
				}
			} catch (NoSuchElementException e) {
				break;
			}
		} while (true);

	}

	/**
	 * load monster location from file
	 */
	private final void MonsterManager() {
		String next;
		do {
			try {
				next = monster.nextLine();
				if (next != null) {
					m[Integer.valueOf(next.split(",")[5])] = new Monster(
							this.getInGameLevel()[Integer.valueOf(next.split(",")[0])],
							Integer.valueOf(next.split(",")[3]), Integer.valueOf(next.split(",")[4]));
					m[Integer.valueOf(next.split(",")[5])].setMonster(Integer.valueOf(next.split(",")[1]),
							Integer.valueOf(next.split(",")[2]));
					getInGameLevel()[Integer.valueOf(next.split(",")[0])].getInLevelLocation()[Integer
							.valueOf(next.split(",")[1])][Integer.valueOf(next.split(",")[2])].setHasMonster(true);
				} else {
					break;
				}

			} catch (NoSuchElementException e) {
				break;
			}
		} while (true);

	}

	/**
	 * set up all sword on the world
	 */
	private void SwordManager() {
		s = null;
		switch (this.getPlayer().getCurrentLevel().getLevel()) {
		case (0): {
			s = new swordOld(this.player.getCurrentLevel());
			s.setSword(5, 6);
			break;
		}
		case (1): {

			s = new swordNapoleon(this.player.getCurrentLevel());
			s.setSword(6, 3);
			break;
		}
		case (4): {

			s = new swordDS(this.player.getCurrentLevel());

			s.setSword(8, 2);
			break;
		}
		case (7): {
			s = new swordMaster(this.player.getCurrentLevel());
			s.setSword(5, 2);
			break;
		}
		case (9):
			s = new swordProgramer(this.player.getCurrentLevel());
			s.setSword(5, 7);
			break;
		}

	}

	/**
	 * every time player move this checklist will be call to check is some thing
	 * happened on the world
	 */
	private void checkList() {
		this.levelChangeChecker();
		this.savePointEevent();
		for (int monsterNO = 0; monsterNO != m.length; monsterNO++) {
			this.monsterEvent(monsterNO);
		}
		this.swordPickUpEevent();
                this.endGamechecker();
	}

	public Player getPlayer() {
		return player;
	}

	public Level[] getInGameLevel() {
		return inGameLevel;
	}

	public void setInGameLevel(Level[] inGameLevel) {
		this.inGameLevel = inGameLevel;
	}

}
