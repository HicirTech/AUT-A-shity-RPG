package v2_test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * This class will set up the control of the start menu also build the frame
 * 
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
public class StartMemuControl {
	private GamePanelStart startScreen;
	private GameFrame thisFrame;
	private boolean LoadRequire;
	private static	GameControl GT;

	public StartMemuControl(){
            this.buildStartFrame();
            this.buildActionListener();
	}
	
        /**
         * this method will build up the frame
         */
	private void buildStartFrame(){
            this.startScreen = new GamePanelStart();
            this.thisFrame = new GameFrame();
            this.thisFrame.changeP(this.startScreen);
	}
	
        /**
         * this method will add action listener
         */
	private void buildActionListener(){
            this.startScreen.getStart().addActionListener(new ActionListener() 
            {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            eventHandleStartButton();				 
                    }
            });
             this.startScreen.getHelp().addActionListener(new ActionListener() 
                    {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    helpNeedEventHandle();
                            }
                    });
            this.startScreen.getLoad().addActionListener(new ActionListener() 
            {	
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            eventHandleLoadButton();
                    }
            });
            this.startScreen.getExit().addActionListener(new ActionListener() 
            {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            eventExitHandle();
                    }
            });

            this.startScreen.getAuthor().addActionListener(new ActionListener() 
            {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            myIdHandel();
                    }
            });	
	}
	
        /**
         * this will handle exit button
         * stop the program
         */
	private void eventExitHandle(){
            System.exit(0);
	}
        
        /**
         * this will handle the id event
         */
	private void myIdHandel(){
            this.startScreen.getAuthor().setIcon(this.startScreen.getMyID());
            this.startScreen.getAuthor().setLocation(700,713);
            this.startScreen.getAuthor().setSize(this.startScreen.getMyID().getIconWidth()
                        ,this.startScreen.getMyID().getIconHeight());		
            this.audioPlayer("ThatSAmazing");
	}
        /**
         * this will break a frame
         */
	private void breakFrame(){
            this.thisFrame.setVisible(false);
            this.thisFrame=null;
	}
        
        /**
         * this will load a the game control class
         */
	private void GtLoader(){
		GT= new GameControl(LoadRequire);
		System.out.println(GT.getClass()+"STARTED!");
	}
	
        /**
         * this will handle the audio for start and load
         */
	private void audioLoader(){
            if(this.LoadRequire==true)
            {
                this.audioPlayer("LetsGiveItAGo");
            }
            else
            {
                this.audioPlayer("AH");
            }
	}
	
        /**
         * this will handle the help button
         */
	private void helpNeedEventHandle() {
            HelpFrame help = new HelpFrame();
            System.out.println(help+"HelpGived");
	}
        
        /**
         * this method will player a audio base on input
         * @param audioName name of file
         */
	private void audioPlayer(String audioName){
            try {
                AudioPlayer.player.start(new AudioStream(new FileInputStream(new File(audioName+".wav"))));
            } catch (IOException e) {
                System.out.println("Audio Error");		
            }
	}
        /**
         * this will handle the load button
         */
	private void eventHandleLoadButton(){
            this.breakFrame();
            this.LoadRequire=true;
            this.audioLoader();
            this.GtLoader();
	}
        /**
         * this will handle the start button
         */
	private void eventHandleStartButton(){
            this.breakFrame();
            this.audioLoader();
            this.storyLoadControlLoader();
		
	}
        
        /**
         * this will handle new store start
         */
	private void storyLoadControlLoader(){
		storyLoadControl newStart= new storyLoadControl();
		System.out.println(newStart.getClass()+"STARTED!!");
	}
}
