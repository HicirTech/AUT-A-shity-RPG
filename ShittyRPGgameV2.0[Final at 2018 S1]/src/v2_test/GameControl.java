 package v2_test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * this class will will support the game control including build frame and react

 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
 
public class GameControl{
	 private StoryPanel story ;
	 private  World model;
	 private GameFrame gameFrame;
         
	public  GameControl(boolean load)
	 {				 
            this.buildFrame(load);
            this.buildListener();
		
	 }
         
         /**
          * this method will set up listeners of the frame
          */
	 private void buildListener()
	 {
            this.gameFrame.addKeyListener(new KeyListener (){
                @Override
                public void keyPressed(KeyEvent e) {
                        newFrameEventHandle(e);
                }
                @Override
                public void keyReleased(KeyEvent e) {
                        playerDieEventHandle();
                }
                @Override
                public void keyTyped(KeyEvent e) {/*no event*/}
            });
	 }
	 
         /**
          * this method will control frame change
          * each time player move game will make a new frame
          * by making new frame this will able display change
          * @param userInput from keyboard
          */
	 private void newFrameEventHandle(KeyEvent userInput){
            if(keyVaildCheck(userInput))
            {
                if(model.getPlayer().getHeath()>0)
                {
                    model.changer(userInput.getKeyChar());
                    story = new StoryPanel(model.getPlayerLevel());
                    gameFrame.changeP(story);
                    gameFrame.repaint();
                }
            }
	 }
	 
         /**
          * this method will check dose user input valid
          * @param userInput from keyboard
          * @return is valid or not
          */
	 private boolean keyVaildCheck(KeyEvent userInput) {
		 return userInput.getKeyChar()=='a'||
				 userInput.getKeyChar()=='s'||
				 userInput.getKeyChar()=='w'||
				 userInput.getKeyChar()=='d'||
				 userInput.getKeyChar()=='A'||
				 userInput.getKeyChar()=='S'||
				 userInput.getKeyChar()=='W'||
				 userInput.getKeyChar()=='D';
	 }
	 
         /**
          * this method will handle player die
          * if player die return to first frame
          */
	 private void playerDieEventHandle(){
            if(model.getPlayer().getHeath()<0)
            {
                gameFrame.setFocusable(false);
                gameFrame.setVisible(false);
                StartMemuControl re = new StartMemuControl();
                System.out.println(re.getClass()+"STARTED!!!");
            }		 
	 }
	 
         /**
          * this method will handle build a new frame of game
          * @param needLoad if need load
          */
	 private void buildFrame(boolean needLoad){
            this.gameFrame=  new GameFrame();
            this.gameFrame.setFocusable(true);

            this.model= new World(needLoad);

            this.story = new StoryPanel(model.getPlayerLevel());

            this.gameFrame.changeP(story);
	 }
	  
}

