package v2_test;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class will set up the panel of story frame
 * 
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
public class StartStoryPanel extends JFrame{

	private ImageIcon backgrondP;
	private JButton goNext;
	private JLabel background;
	private JLabel pointDown;
	private JLabel pointUp;
	private ImageIcon pointDP;
	private ImageIcon nextP;
	private ImageIcon pointUPP;
        
	public StartStoryPanel(){
            this.backgrondP= new ImageIcon("Backgound.png");
            this.pointDP=new ImageIcon("pointDown.png");
            this.pointUPP=new ImageIcon("pointup.png");
            this.nextP=new ImageIcon("next.png");
            this.background = new JLabel(backgrondP);
            
            this.pointDown = new JLabel(pointDP);
            this.pointDown.setVisible(true);
            this.pointDown.setLocation(70,200);
            this.pointDown.setSize(pointDP.getIconWidth(),pointDP.getIconHeight());
            this.add(this.pointDown);


            this.pointUp = new JLabel(pointUPP);
            this.pointUp.setVisible(true);
            this.pointUp.setLocation(70,620);
            this.pointUp.setSize(pointUPP.getIconWidth(),pointUPP.getIconHeight());
            this.add(this.pointUp);

            this.goNext= new JButton(nextP);
            this.goNext.setVisible(true);
            this.goNext.setSize(nextP.getIconWidth(),nextP.getIconHeight());
            this.goNext.setLocation(10,540);

            this.add(this.goNext);
            this.setBackgrond();
            this.buildFrame();
            this.addLabel();
            this.setBackgrond();
	}
	
        /**
         * this method will set up the back ground
         */
	private void setBackgrond(){	
            this.background = new JLabel();
            this.background.setIcon(backgrondP);
            this.background.setVisible(true);
            this.background.setLocation(0, 0);
            this.background.setSize(backgrondP.getIconWidth(),backgrondP.getIconHeight());
            this.add(background);
	}
        
        /**
         * this method will set up the text labels 
         */
	private void addLabel(){
            int linecount=0;
            Scanner scanner;
            try {
                scanner = new Scanner(new File("story.txt"));

            String next;
            while(scanner.hasNext())
            {	
                next=scanner.nextLine();			
                for(int i=0;i!=next.toUpperCase().toCharArray().length;i++)
                {	
                    JLabel temp = new JLabel(new ImageIcon(next.charAt(i)+".png"));
                    temp.setSize(new ImageIcon(next.charAt(i)+".png").getIconWidth()
                                ,new ImageIcon(next.charAt(i)+".png").getIconHeight());
                    temp.setVisible(true);
                    temp.setLocation(350+(i*45),5+linecount*50);
                    this.add(temp);
                }
                linecount++;
            }	
            } catch (FileNotFoundException e1) {
                System.out.println("Letter not found?");
            }
	}
        /**
         * this method will setup a new frame
         */
	private void buildFrame(){
            this.setVisible(true);
            this.getContentPane();
            this.setSize(backgrondP.getIconWidth(),backgrondP.getIconHeight());
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);	  
	 }
	
        public JButton getGoNext() {
		return goNext;
	}

	 
	

}
