package v2_test;

import java.io.*;
import sun.audio.*;
import javax.swing.*;

/**
 * this class will set up a panel use for playing
 * 
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
public class GamePanelStart extends JPanel {
	private JButton start;
	private JButton load ;
	private JButton exit;
	private	JButton author;
	private JLabel background;
	private ImageIcon myID;
	private ImageIcon startP;
	private ImageIcon backgrondP;
	private JButton help;
	private ImageIcon helpP;
	private ImageIcon loadP;
	private ImageIcon exitP;
	private ImageIcon authorP;

	public GamePanelStart()
	{
            try {
                    AudioPlayer.player.start(new AudioStream(new FileInputStream(new File("Ucangonow.wav"))));
           } catch (FileNotFoundException e) {} catch (IOException e) {}

           this.backgrondP = new ImageIcon("Backgound.png");
           this.myID = new  ImageIcon("myID.png");
           this.startP=new ImageIcon("start.png");
           this.helpP=new ImageIcon("help.jpg");
           this.loadP=new ImageIcon("load.png");
           this.exitP = new ImageIcon("exit.png");
           this.authorP= new ImageIcon("author.png");

           this.setSize(backgrondP.getIconWidth(),backgrondP.getIconHeight());
           this.setVisible(true);
           this.setLayout(null);


           this.start = new JButton(startP);
           this.start.setContentAreaFilled(false);
           this.start.setVisible(true);
           this.start.setLocation(800,127);
           this.start.setSize(325,161);

           this.help = new JButton(this.helpP);
           this.help.setSize(this.helpP.getIconWidth()
                           ,this.helpP.getIconHeight());
           this.help.setVisible(true);
           this.help.setLocation(20,850);
           this.add(help);

           this.load = new JButton(this.loadP);
           this.load.setContentAreaFilled(false);
           this.load.setVisible(true);
           this.load.setLocation(800,349);
           this.load.setSize(325,161);	

           this.exit = new JButton(this.exitP);
           this.exit.setContentAreaFilled(false);
           this.exit.setVisible(true);
           this.exit.setLocation(800,795);
           this.exit.setSize(325,161);

           this.add(exit);
           this.add(load);
           this.add(start);

           this.author = new JButton(authorP);

           this.author.setSize(authorP.getIconWidth()
                           ,authorP.getIconHeight());
           this.author.setLocation(500,547);
           this.author.setVisible(true);
           this.author.setBorder(null);
           this.add(author);

           this.background = new JLabel();
           this.background.setIcon(backgrondP);
           this.background.setVisible(true);
           this.background.setLocation(0, 0);
           this.background.setSize(backgrondP.getIconWidth(),backgrondP.getIconHeight());
           this.add(background);
	}
//------------getter and setters--------
	public ImageIcon getMyID() {
		return myID;
	}
	public JButton getHelp() {
		return help;
	}
	public JButton getStart() {
		return start;
	}
	public JButton getLoad() {
		return load;
	}
	public JButton getExit() {
		return exit;
	}
	public JButton getAuthor() {
		return author;
	}
}
