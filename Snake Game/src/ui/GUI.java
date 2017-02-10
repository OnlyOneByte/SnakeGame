package ui;
import java.awt.Color;

import javax.swing.JFrame;

import manager.Handler;
import manager.KeyboardManager;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	private BoardGUI board;
	private MenuBar menu;
	private ScorePanel score;
	
	@SuppressWarnings("unused")
	private Handler handler;
	private int width;
	private int height;
	private KeyboardManager keys;
	
	public GUI(Handler handler){
		this.handler = handler;
		
		this.board = handler.getBoard();
		this.menu = handler.getMenu();
		this.score = handler.getScorePanel();
		
		setWidth(handler.getBoard().getWidth() + handler.getBoard().getEdgeBorder()*2 + 10);
		setHeight(handler.getBoard().getHeight() + MenuBar.HEIGHT + handler.getBoard().getEdgeBorder()*2 + 15);
		
		
		keys = new KeyboardManager(handler);
		
		
		//begin stuff
		this.setLayout(null);
		
		this.addKeyListener(keys);
		
		this.getContentPane().setBackground(Color.DARK_GRAY);
		
		this.add(menu);
		this.menu.isFocusable();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(board);
		
		this.add(score);
		
		
		this.board.isFocusable();
		
		this.setSize(width, height);
		this.setResizable(false);
		this.setVisible(true);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
