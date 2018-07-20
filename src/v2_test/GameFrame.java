package v2_test;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * this class will set up a frame use for game
 * 
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
 
@SuppressWarnings("serial")
public class GameFrame extends JFrame{

	public GameFrame()
	{
		this.buildFrame();
	}
	
	 private void buildFrame()
	 {
            this.setVisible(true);
            this.getContentPane();
            this.setSize(1620,1081);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);	  
	 }
	 
         /**
          * this method will remove all things in this frame
          * and add new Panel to it 
          * @param changeTo the panel change to
          */
	 public void changeP(JPanel changeTo)
	 {
		  this.getContentPane().removeAll();
		  this.getContentPane().add(changeTo);
		  this.repaint();
	 }
}
