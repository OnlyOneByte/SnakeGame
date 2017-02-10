package ui;
import javax.swing.*;

import manager.Handler;
import powerup.PowerUp;
import snake.SnakeBody;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.*;

@SuppressWarnings("serial")
public class BoardGUI extends JPanel {
	private static int boxSize = 25;
	private static int edgeBorder = 25;
	private final static int WIDTH = 700;
	private final static int HEIGHT = 700;
	
	public static final int DEFAULT_WIDTH = 700;
	public static final int DEFAULT_HEIGHT = 700;
	public static final int DEFAULT_BOX_SIZE = 25;
	
	private Handler handler;
	
	private ArrayList <JPanel> grid = new ArrayList <JPanel>();

	public BoardGUI(Handler handler) {
		
		this.handler = handler;
		
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(edgeBorder, MenuBar.HEIGHT + 10);
		
		initializeBoard();
		
		this.setLayout(new GridLayout((WIDTH / boxSize), (HEIGHT / boxSize )));
		
		this.setRequestFocusEnabled(true);		

	}
	
	public void initializeBoard(){
		for (int y = 0; y < (HEIGHT / boxSize); y++) {
			for (int x = 0; x< (WIDTH / boxSize); x++) {
				JPanel panel = new JPanel();
				panel.setSize(boxSize,boxSize);				
				panel.setBackground(Color.WHITE);
				grid.add(panel);
				this.add(grid.get(coord(x,y)));	
			}
		}
	}
	
	
	public int coord(int x, int y){
		return (y * (WIDTH / boxSize)) + x;		
	}
	
	public void drawSnake1(){
		//snake 1
		grid.get(coord(handler.getHead1().getX(), handler.getHead1().getY())).setBackground(Color.LIGHT_GRAY);
		for(int x = 0; x < handler.getSnake1().size(); x++){
			grid.get(coord(handler.getSnake1().get(x).getX(),handler.getSnake1().get(x).getY())).setBackground(Color.gray);
		}
	}
	public void drawSnake2(){
		grid.get(coord(handler.getHead2().getX(), handler.getHead2().getY())).setBackground(Color.BLUE);
		for(int x = 0; x < handler.getSnake2().size(); x++){
			grid.get(coord(handler.getSnake2().get(x).getX(),handler.getSnake2().get(x).getY())).setBackground(Color.cyan);
		}	
	}
	
	public void removeSnakePart(SnakeBody snake){
		grid.get(coord(snake.getX(), snake.getY())).setBackground(Color.WHITE);
	}
	
	public void drawPowerUps(){
		for(PowerUp element : handler.getPowerUps()){
			grid.get(coord(element.getX(), element.getY())).setBackground(Color.RED);
		}
	}

	public void reset(){
		for(int x = 0; x < grid.size(); x++){
			grid.get(x).setBackground(Color.WHITE);
		}
	}
	
	
	public int getHeight(){
		return HEIGHT;
	}
	
	public int getWidth(){
		return WIDTH;
	}
	
	public int getBoxSize(){
		return boxSize;
	}
	
	public int getEdgeBorder(){
		return edgeBorder;
	}
}
