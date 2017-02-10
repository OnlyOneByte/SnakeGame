package ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

import manager.Handler;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel{
	private int gameMode = 0;
	private boolean onePlayer = false;
	private Handler handler;
		
	private int fps;
	
	private int snake1Length = 0;
	
	Font scoreLabel = new Font("sans-serif", Font.BOLD, 16);
	
	JLabel score1= new JLabel();
	JLabel FPS = new JLabel();
	
	//bugs: last score is stuck after reset.
	
	public ScorePanel (Handler handler){
		
		this.handler = handler;
		
		fps = (int) (1000 / handler.getDelayTimer());
		
		recheckMode();
		snakeLength();
		
		//creates the outer bounds
		this.setSize(250, 75);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setLocation(525, 5);
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.RED));
		this.add(Box.createRigidArea(new Dimension(5,8))); //spacer
		
		if(onePlayer){
			score1.setFont(scoreLabel);
			score1.setSize(200, 25);
			this.add(score1);
			panelOnePlayer();
		} else {
			panelTwoPlayer();
		}
		
		
		//draws the JLabel for the other things
		drawFPS();
		this.add(Box.createRigidArea(new Dimension(5,5))); //spacer
		this.add(FPS);
		this.setVisible(true);
		
		
	}
	
	private void drawFPS() {
		recheckFPS();
		FPS.setText("Speed / FPS: " + fps);
		FPS.setFont(scoreLabel);		
		
		FPS.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	private void panelTwoPlayer() {
		//double score counter and a end game screen with winner
		
		
	}

	private void panelOnePlayer() {
		//score counter
		snake1Length = handler.getSnake1().size() + 1;
		score1.setText("Score: " + snake1Length);
		score1.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	private void snakeLength(){
		snake1Length = handler.getSnake1().size() + 1;
	}
	
	public void redoScore(){
		recheckMode();
		recheckFPS();
		snakeLength();
		drawFPS();
		
		if(onePlayer)
			panelOnePlayer();
		else
			panelTwoPlayer();
		
		this.repaint();
		this.revalidate();
	}
	

	//checks the gamemode and makes it and stuff
	public void recheckMode(){
		gameMode = handler.getGameMode();
		
		if(gameMode == 0 || gameMode == 1)
			onePlayer = false;
		else
			onePlayer = true;
		//checks ay lmao
	}
	
	public void recheckFPS(){
		fps = (int) (1000 / handler.getDelayTimer());
	}
	
	public void redoFPS(){
		drawFPS();
	}
}
