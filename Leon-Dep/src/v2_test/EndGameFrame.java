package v2_test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * this is the class a frame of end
 * 
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
public class EndGameFrame extends JFrame {

	private final JLabel background;
	private final ImageIcon backgroundP;
	private final ImageIcon exitP;
	private final JButton exit;	
	public EndGameFrame()
	{
            this.setSize(800,600);
            this.setVisible(true);

            this.backgroundP = new ImageIcon("Helpback.png");
            this.exitP=new ImageIcon("exit.png");

            this.background = new JLabel(backgroundP);
            this.background.setVisible(true);
            this.background.setLocation(0, 0);
            this.background.setSize(backgroundP.getIconWidth(),backgroundP.getIconHeight());
            this.setSize(backgroundP.getIconWidth(),backgroundP.getIconHeight());

            this.exit = new JButton(exitP);
            this.exit.setContentAreaFilled(false);
            this.exit.setVisible(true);
            this.exit.setLocation(500, 450);
            this.exit.setSize(325,161);
            this.add(exit);

            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            addLabel();	
            this.add(background);

	}
	
	public JButton getExit() {
		return exit;
	}

        /**
         * this method will add all text block to the frame
         */
	private void addLabel()
	{
            int linecount=0;
            Scanner scanner;
            try {
                scanner = new Scanner(new File("end.txt"));
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
                            temp.setLocation(0+(i*45),0+linecount*50);
                            this.add(temp);

                    }
                    linecount++;
                }
                scanner.close();
            }catch (FileNotFoundException e1) {
                    System.out.println("Letter not found?");
            }
		
	}
}
