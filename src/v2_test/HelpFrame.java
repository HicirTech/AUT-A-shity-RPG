package v2_test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * this class will set up a frame use for help player
 * 
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
public class HelpFrame extends JFrame {
	
	private JLabel background;
	private ImageIcon backgroundP;
	private double hdTimes;
	private String hdPath;
        
	public HelpFrame()
	{		
		this.setSize(800,600);
		this.setVisible(true);
		
		this.backgroundP = new ImageIcon("Helpback.png");
		
		this.background = new JLabel(backgroundP);
		this.background.setVisible(true);
		this.background.setLocation(0, 0);
		this.background.setSize(backgroundP.getIconWidth(),backgroundP.getIconHeight());
		this.setSize(backgroundP.getIconWidth(),backgroundP.getIconHeight());
		
		this.addLabel();	
		this.add(background);
	}
        /**
         * this method will load all text label from file
         */
	private void addLabel()
	{
		int linecount=0;
		Scanner scanner;
		try {
			scanner = new Scanner(new File("help.txt"));
		
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
				temp.setLocation(0+(i*45),0+(linecount*50));
				this.add(temp);
			}
			linecount++;
		}	
		} catch (FileNotFoundException e1) {
			System.out.println("Letter not found?");
		}
	}

}
