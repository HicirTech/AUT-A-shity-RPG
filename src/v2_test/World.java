package v2_test;

import java.util.*;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


/**
 * A world have 10 level and each level have 11*11 location, use this world can
 * show a 3D space
 * 
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
public class World {

        private Player player;
        private Monster m[] = new Monster[20];// max for 20 monsters
        private Level inGameLevel[];
        private Sword s;
        private Scanner stairUp;
        private Scanner stairDown;
        private Scanner monster;
        private Scanner map;
        private Scanner story;
        private Scanner end;
        private DatabaseControl database;

        public World(boolean load) {// load from file or not

                worldLoader(load);

        }
        /**
         * this method will load the game
         * @param needload need loading or not
         */
        private void worldLoader(boolean needload)
        {
            this.database=new DatabaseControl();
            database.establishConnection();
            System.out.println("Log: NEED LOAD:"+needload);
            this.worldLoad();

            if (needload==false) {// if not load from file then start a new games
                this.storyReader();
                this.MapManager();
                this.playerReset();
                this.SwordManager();
            }
            else {
                this.playerLoader();
            }
        }
        
        /**
         * this method will load player
         */
        private void playerLoader()
        {
            this.MapManager();//map will be build first
            try {
                if(this.database.hasTable()==true)//if have save data
                {
                    this.player= new Player(this.getInGameLevel()[this.database.getLastLevel()],this.database.getLastX(),this.database.getLastY()
                                    ,this.database.getLastMoney(),this.database.getLastHp(),this.database.getLastAtk());
                    this.inGameLevel[this.database.getLastLevel()].setPlayer(player);
                }
                else//give new database
                {			
                    System.out.println("Gamer Save not error! Setting up new game");
                    this.playerReset();
                }
            }catch (NoSuchElementException e) {//if some how database have error
                System.out.println("Gamer Save not error! Setting up new game");
                this.playerReset();
            }
        }
        
        /**
         * this method will set up a new game
         */
        private void playerReset()//reset the game
        {
            this.worldLoad();
            this.MapManager();
            this.player = new Player(this.getInGameLevel()[0]);
            this.player.setCurrentLevel(this.inGameLevel[0]);
            this.inGameLevel[0].setPlayer(player);
            this.database.CreateTable();
            this.database.reSetdatabse();
            this.saveEvent();// reset finish clean the save databse
        }

        /**
         * this method will load files for set up
         */
        private void worldLoad() //load up the map
        {
            try {
                this.map = new Scanner(new File("map.txt"));
                this.monster = new Scanner(new File("monster.txt"));
                this.stairUp = new Scanner(new File("stairUp.txt"));
                this.stairDown = new Scanner(new File("stairDown.txt"));
                this.story = new Scanner(new File("story.txt"));
                this.end=new Scanner(new File("end.txt"));
            } catch (FileNotFoundException a) {
                System.out.println("File error, please check your game files!");
                System.exit(0);//if dose file not exists, this game will not be play
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
                } catch (NoSuchElementException e) {
                    break;
                }
            } while (next!=null);
        }

        /**
         * end story reading story
         */
        private void endReader()
        {
            String next = null;
            do {try {
                next = end.nextLine();
                System.out.println(next);
            } catch (NoSuchElementException e) {
                break;
            }} while (next!=null);	
            //Game end
            EndGameFrame EOG = new EndGameFrame();
            EOG.getExit().addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0); 
                }
            });
        }
        
        /**
         * this will check shall we need the game
         */
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
         * a player move control 
         */
        public void changer(char in)
        {
                try {
                    player.move(in);
                    checkList();
                    System.out.println(this.getPlayer().getCurrentLevel());
            } catch (NullPointerException e) {//next step
                    player.move(in);
                    checkList();
                    System.out.println(this.getPlayer().getCurrentLevel());
            }
        }

        /**
         * when sword picked up happen
         */
        private void swordPickUpEevent() {
            if (player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].isHasSword()) 
            {
                s.pickUp(player);
                player.setHasSword(true);
                try {
                     AudioPlayer.player.start(new AudioStream(new FileInputStream(new File("WOO.wav"))));
                } catch (IOException e) {
                    System.out.println("Audio error!");
                }
            }
        }

        /**
         * save player into file this action will happen each time player move
         */
        private void saveEvent() {
            this.database.input(this.player.getCurrentLevel().getLevel(),
                             this.player.getCurrentX()
                             , this.player.getCurrentY()
                             , this.player.getMoney(), 
                            (int) this.player.getHeath(),
                             (int)this.player.getAttack());
        }

        /**
         * this will check player hit monster or not
         * @return 
         */
        private boolean checkIsMonster()
        {
            return player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()]
                            .isHasMonster();
        }
        
        /**
         * this will handle a monster die 
         * @param monsterNo 
         */
        private void monsterDieEvent(int monsterNo)
        {
            this.m[monsterNo].die(player);
            this.getInGameLevel()[player.getCurrentLevel().getLevel()].getInLevelLocation()[player
                            .getCurrentX()][player.getCurrentY()].setHasMonster(false);
            this.getInGameLevel()[player.getCurrentLevel().getLevel()].getInLevelLocation()[player
                            .getCurrentX()][player.getCurrentY()].setHasPlayer(true);
        }
        /**
         * check player and monster location if player location == monster location
         * then fight will happen
         * 
         * @param monsterNo of monster
         */
        private void monsterEvent(int monsterNo) {
            try {
                if (checkIsMonster ()== true) {
                    m[monsterNo].Attack(player);
                    try {
                        AudioPlayer.player.start(new AudioStream(new FileInputStream(new File("hurt.wav"))));
                    } catch (IOException e) {
                        System.out.println("Audio error");
                    }
                    
                    m[monsterNo].setHeath(m[monsterNo].getHeath() - player.getAttack());
                    if (!(m[monsterNo].getHeath() > 0)) {
                        monsterDieEvent(monsterNo);					
                    }
                }
            } catch (NullPointerException e) {
                System.out.println("catch:Null catch");
            }
        }

        /**
         * this will check player hit upper stair or not
         * @return upper stair or not
         */
        private boolean checkIsUpperStair(){
                return getPlayer().getCurrentLevel().getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()]
                                .isHasUpStairs();
        }

         /**
         * this will check player hit lower stair or not
         * @return lower stair or not
         */
        private boolean checkIsLowerStair(){
                return getPlayer().getCurrentLevel().getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].isHasDownStairs();
        }


        /**
         * when level change sound will be play
         */
        private void playLevelChangeSound(){
            try {
                AudioPlayer.player.start(new AudioStream(new FileInputStream(new File("changeLevel.wav"))));
                } catch (IOException e) {
                System.out.println("Audio error");     
            }
        }

        /**
         * this will change player level up
         */
        private void levelGoUpEvent()
        {
            this.getInGameLevel()[getPlayer().getCurrentLevel().getLevel() + 1].setPlayer(getPlayer());
            this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer()
                            .getCurrentX()][getPlayer().getCurrentY()].setHasPlayer(false);
            this.getPlayer().setCurrentLevel(getInGameLevel()[getPlayer().getCurrentLevel().getLevel() + 1]);
            this.player.setCurrentX(getPlayer().getCurrentLevel().lookForLower().getxAxis());
            this.player.setCurrentY(getPlayer().getCurrentLevel().lookForLower().getyAxis());
            this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer()
                            .getCurrentLevel().lookForLower().getxAxis()][getPlayer().getCurrentLevel().lookForLower()
                                            .getyAxis()].setHasPlayer(true);
        }

        /**
         * this will change player level down
         */
        private void levelGoDownEvent()
        {
            this.getInGameLevel()[getPlayer().getCurrentLevel().getLevel() - 1].setPlayer(getPlayer());
            this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer()
                            .getCurrentX()][getPlayer().getCurrentY()].setHasPlayer(false);
            this.getPlayer().setCurrentLevel(getInGameLevel()[getPlayer().getCurrentLevel().getLevel() - 1]);
            this.player.setCurrentX(getPlayer().getCurrentLevel().lookForUpper().getxAxis());
            this.player.setCurrentY(getPlayer().getCurrentLevel().lookForUpper().getyAxis());
            this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer()
                            .getCurrentLevel().lookForUpper().getxAxis()][getPlayer().getCurrentLevel().lookForUpper()
                                            .getyAxis()].setHasPlayer(true);
        }
        
        /**
         * manage player's level up or down when player go up stair then it will
         * look for next level down stair when player go down stair then it will
         * look for last level up stair also every time player change level, then
         * it will save player to file also sword will be update
         */
        private void levelChangeChecker() {
            if(checkIsUpperStair()==true)
            {
                this.levelGoUpEvent();
                this.SwordManager();
                this.saveEvent();
                this.playLevelChangeSound();	
            } else if (checkIsLowerStair()==true) {
                this.levelGoDownEvent();
                this.SwordManager();
                this.saveEvent();
                this.playLevelChangeSound();
            }
            try {
                AudioPlayer.player.start(new AudioStream(new FileInputStream(new File("step.wav"))));
                } catch (IOException e) {
                System.out.println("Audio error");      
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
            case (9):{
                s = new swordProgramer(this.player.getCurrentLevel());
                s.setSword(5, 7);
                break;
            }
        }
    }

        /**
         * every time player move this checklist will be call to check is some thing
         * happened on the world
         */
        private void checkList() {
            this.levelChangeChecker();
            this.saveEvent();
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

        public Level getPlayerLevel()
        {
            return player.getCurrentLevel();
        }
        public void setInGameLevel(Level[] inGameLevel) {
            this.inGameLevel = inGameLevel;
        }
}
