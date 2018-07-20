package v2_test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this class will will support the story load control including build frame and react

 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
public class storyLoadControl {
    
	private static	GameControl GT;
	private StartStoryPanel storyLoading;
	
	public storyLoadControl()
        {
            this.storyLoading= new StartStoryPanel();
            this.addListeners();
		
	}
	
        /**
         * this method will add all listeners
         */
	private void addListeners()
	{
            this.storyLoading.getGoNext().addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                        GTLoadedEvent();
                }
            });	
	}
        
        /**
         * this will load a new game control
         */
	private void GTLoadedEvent()
	{
            this.breakFrame();
            GT= new GameControl(false);
            System.out.println(GT.getClass()+"STARTED!!!!");
	}
        
        /**
         * this method will break this frame
         */
	private void breakFrame()
        {
            this.storyLoading.setVisible(false);
            this.storyLoading= null;
	}
	
}
