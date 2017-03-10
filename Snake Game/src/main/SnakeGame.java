package main;

import manager.GameManager;
import manager.Handler;

public class SnakeGame {
	/**
	 * This is the handler that is passed to everything.
	 */
	public static Handler handler = new Handler();
	
	/**
	 * The main method
	 * @param args
	 */
	public static void main(String[] args)  {	

	    // This is the next comment.
		
		@SuppressWarnings("unused")
		GameManager game = new GameManager(handler);
		
	}
}
