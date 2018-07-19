package v2_test;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class will set up the game frame
 * 
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
public class StoryPanel extends JPanel{

	private JLabel background;
	private JLabel[][] box; 
	private JLabel hp;
	private JLabel gold;
	private JLabel attack;
	private JLabel level;
	private ImageIcon backgrondP;
	private ImageIcon HPP;
	private ImageIcon goldP;
	private ImageIcon ATKP;
	private ImageIcon LevelP;
	
	public StoryPanel(Level current) {
            backgrondP = new ImageIcon("Backgound.png");
            this.setSize(backgrondP.getIconWidth(),backgrondP.getIconHeight());
            this.setVisible(true);
            this.setLayout(null);
            this.setBoxes(current);
            this.setMoney(current);
            this.setAttac(current);
            this.setHeath(current);
            this.setLevel(current);
            this.setComponent();
	}
	
	/**
         * this method will set up component of this panel
         */
	private void setComponent(){
            this.HPP = new ImageIcon("HP.png");
            this.goldP =new ImageIcon("Gold.png");
            this.ATKP=new ImageIcon("ATK.png");
            this.LevelP= new ImageIcon("Level.png");

            this.hp=new JLabel(HPP);
            this.hp.setSize(HPP.getIconWidth(),HPP.getIconHeight());
            this.hp.setVisible(true);
            this.hp.setLocation(20,30);
            this.add(hp);

            this.gold=new JLabel(goldP);
            this.gold.setSize(goldP.getIconWidth(),goldP.getIconHeight());
            this.gold.setVisible(true);
            this.gold.setLocation(20,229);
            this.add(gold);

            this.attack=new JLabel(ATKP);
            this.attack.setSize(ATKP.getIconWidth(),ATKP.getIconHeight());
            this.attack.setVisible(true);
            this.attack.setLocation(20,438);
            this.add(attack);


            this.level=new JLabel(LevelP);
            this.level.setSize(LevelP.getIconWidth(),LevelP.getIconHeight());
            this.level.setVisible(true);
            this.level.setLocation(0,622);
            this.add(level);


            this.background = new JLabel();
            this.background.setIcon(this.backgrondP);
            this.background.setVisible(true);
            this.background.setLocation(0, 0);
            this.background.setSize(1620,1081);
            this.add(background);
      }
        
	
	

        /**
         * this method will base on current level to set up text label
         * @param current level player in
         */
	private void setMoney(Level current){
            for(int i=0;i!=String.format("%d",(int)current.getPlayer().getMoney()).length();i++)
            {
                JLabel temp = new JLabel(new ImageIcon(String.format("%d",(int)current.getPlayer().getMoney()).charAt(i)
                                +".png"));
                temp.setSize(50,75);
                temp.setVisible(true);
                temp.setLocation((20+(i*50)),320);
                this.add(temp);
            }
	}
	
        
        /**
         * this method will base on current level to set up text label
         * @param current level player in
         */
	private void setAttac(Level current){		
            for(int i=0;i!=String.format("%d",(int)current.getPlayer().getAttack()).length();i++)
            {
                JLabel temp = new JLabel(new ImageIcon(String.format("%d",(int)current.getPlayer().getAttack()).charAt(i)
                                +".png"));
                temp.setSize(50,75);
                temp.setVisible(true);
                temp.setLocation((0+(i*50)),520);
                this.add(temp);
            }		
	}
	
        
        /**
         * this method will base on current level to set up text label
         * @param current level player in
         */
	private void setHeath(Level current){
            for(int i=0;i!=String.format("%d",(int)current.getPlayer().getHeath()).length();i++)
            {
                JLabel temp = new JLabel(new ImageIcon(String.format("%d",(int)current.getPlayer().getHeath()).charAt(i)
                                +".png"));
                temp.setSize(50,75);
                temp.setVisible(true);
                temp.setLocation((20+(i*50)),150);
                this.add(temp);
            }
	}
        
        /**
         * this method will base on current level to set up text label
         * @param current level player in
         */
	private void setLevel(Level current){
            for(int i=0;i!=String.format("%d",(int)current.getPlayer().getCurrentLevel().getLevel()).length();i++)
            {
                JLabel temp = new JLabel(new ImageIcon(String.format("%d",(int)current.getPlayer().getCurrentLevel().getLevel()).charAt(i)
                                +".png"));
                temp.setSize(50,75);
                temp.setVisible(true);
                temp.setLocation((20+(i*50)),722);
                this.add(temp);
            }
	}
	
        
        /**
         * this method will base on current level to set up the map 
         * @param current level player in
         */
	private void setBoxes(Level current){
            this.box = new JLabel[11][11];
            for(int x=0;x!=11;x++)
            {
                for(int y=0;y!=11;y++)
                {
                    this.box[x][y]=new JLabel();
                    this.box[x][y].setVisible(true);
                    this.box[x][y].setSize(100,100);

                    if(current.getInLevelLocation()[x][y].toString().equals(" o "))
                    {
                        this.box[x][y].setIcon(new ImageIcon("player.png"));
                    }
                    else if(current.getInLevelLocation()[x][y].toString().equals("###"))
                    {
                        this.box[x][y].setIcon(new ImageIcon("Wall.png"));
                    }
                    else if(current.getInLevelLocation()[x][y].toString().equals("+++"))
                    {
                        this.box[x][y].setIcon(new ImageIcon("goUp.png"));
                    }
                    else if(current.getInLevelLocation()[x][y].toString().equals("---"))
                    {
                        this.box[x][y].setIcon(new ImageIcon("goDown.png"));
                    }
                    else if(current.getInLevelLocation()[x][y].toString().equals(" * "))
                    {
                        this.box[x][y].setIcon(new ImageIcon("monster.png"));
                    }
                    else if(current.getInLevelLocation()[x][y].toString().equals(" S "))
                    {
                        this.box[x][y].setIcon(new ImageIcon("Sword.png"));
                    }
                    else
                    {
                        this.box[x][y].setVisible(false);
                    }				
                    this.box[x][y].setLocation((380+(x*100)),(0+(y*90)));
                    this.add(box[x][y]);
                }
            }
	}
}
