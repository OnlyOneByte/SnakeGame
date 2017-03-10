package manager;

import powerup.PowerUp;
import snake.SnakeBody;

public class CollisionManager {

	/**
	 * The handler that handles everything the handler should handle
	 */
	private Handler handler;
	
	/**
	 * The coordinates of the first snake
	 */
	int currX1 = 0, currY1 = 0;
	/**
	 * The coordinates of the second snake
	 */
	int currX2 = 0, currY2 = 0;

	/**
	 * This is the main constructor
	 * @param handler The passed in handler
	 */
	public CollisionManager(Handler handler) {
		this.handler = handler;
	}

	/*
	 * Can be made better. Right now, the only way it knows something collided
	 * is if its literally on top of another piece. It would be nice to have
	 * better collision checker that can see if the snake is going to run into
	 * another piece and stop the game before.
	 */
	/**
	 * This checks to see if either snake has hit a wall.
	 */
	public void checkWall() {
		// oh god here we go.
		// ends if it is infinite grow
		
		currX1 = handler.getHead1().getX();
		currY1 = handler.getHead1().getY();
		currX2 = 0;
		currY2 = 0;
		

		// checks if snake is going to hit any of the outer bounds
		// first checks if it is going to hit the top
		if ((currY1 == 0) && (handler.getHead1().getDir().equals("n"))) {
			handler.setEnd(true);
			handler.setSnakeOneLose(true);
		}
		// checks to see if its gonna hit bottem. Scaleable
		if ((currY1== (handler.getBoardHeight() / handler.getBoxSize()) - 1)
				&& (handler.getHead1().getDir().equals("s"))) {
			handler.setEnd(true);
			handler.setSnakeOneLose(true);
		}
		// checks to see if its gonna hit the left/west side
		if ((currX1 == 0) && (handler.getHead1().getDir().equals("w"))) {
			handler.setEnd(true);
			handler.setSnakeOneLose(true);
		}
		// checks to see if its gonna hit the right/easst side
		if ((currX1 == (handler.getBoardWidth() / handler.getBoxSize()) - 1)
				&& (handler.getHead1().getDir().equals("e"))) {
			handler.setEnd(true);
			handler.setSnakeOneLose(true);
		}

		// now repeat for the other snake
		// first checks if it is going to hit the top
		
		
		// only checks second snake if it is two player
		if (handler.getGameMode() != 2) {
			
			//checks snake 2 to wall
			currX2 = handler.getHead2().getX();
			currY2 = handler.getHead2().getY();
			
			if (currY2 == 0 && handler.getHead2().getDir().equals("n")){
				handler.setEnd(true);
				handler.setSnakeTwoLose(true);
			}

			// checks to see if its gonna hit bottem. Scaleable
			if ((currY2) == (handler.getBoardHeight() / handler.getBoxSize() - 1) 
					&& (handler.getHead2().getDir().equals("s"))) {
				
				handler.setEnd(true);
				handler.setSnakeTwoLose(true);
			}
			// checks to see if its gonna hit the left/west side
			if ((currX2 == 0) && (handler.getHead2().getDir().equals("w"))) {
				handler.setEnd(true);
				handler.setSnakeTwoLose(true);
			}
			// checks to see if its gonna hit the right/easst side
			if ((currX2 == (handler.getBoardWidth() / handler.getBoxSize()) - 1)
					&& (handler.getHead2().getDir().equals("e"))) {
				handler.setEnd(true);
				handler.setSnakeTwoLose(true);
			}
		}

	}
	
	/**
	 * This checks to see if the snakes collide with each other.
	 */
	public void checkSnakeCollide(){
		currX1 = handler.getHead1().getX();
		currY1 = handler.getHead1().getY();
		
		//head 1 to body 1
		if(handler.getGameMode() == 2){
			for(SnakeBody element : handler.getSnake1()){
				if(element.getX() == currX1 && element.getY() == currY1){
					handler.setEnd(true);
					handler.setSnakeOneLose(true);
				}
			}
		}
		
		if(handler.getGameMode() != 2){
			
			currX2 = handler.getHead2().getX();
			currY2 = handler.getHead2().getY();
			
			
			//head 2 to body 1
			for(SnakeBody element : handler.getSnake1()){
				if(element.getX() == currX2 && element.getY() == currY2){
					handler.setEnd(true);
					handler.setSnakeTwoLose(true);
				}
			}
			
			//head 2 to body 2
			for(SnakeBody element : handler.getSnake2()){
				
				//Head 1 to body 2
				if(element.getX() == currX1 && element.getY() == currY1){
					handler.setEnd(true);
					handler.setSnakeOneLose(true);
				}
			}
			
			
			
			// checks for head to head
			if (handler.getHead1().getX() == handler.getHead2().getX()
					&& handler.getHead1().getY() == handler.getHead2().getY())
				handler.setEnd(true);
		}

	}

	/**
	 * checks for collision with other stuff, like powerups. Only food so far tho.
	 */
	public void powerUpCheck() {
		for (PowerUp element : handler.getPowerUps()) {
			if (element.getX() == handler.getHead1().getX() && element.getY() == handler.getHead1().getY()) {
				element.sideEffect(1);
			}

			if (handler.getGameMode() == 0) {
				if (element.getX() == handler.getHead2().getX() && element.getY() == handler.getHead2().getY()) {
					element.sideEffect(2);
				}
			}
		}
	}

}
