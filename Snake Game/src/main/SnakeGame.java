package main;

import manager.GameManager;
import manager.Handler;

public class SnakeGame {
	public static Handler handler = new Handler();
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {	

	    // This is the next comment.
		
		@SuppressWarnings("unused")
		GameManager game = new GameManager(handler);
		
	}
}
