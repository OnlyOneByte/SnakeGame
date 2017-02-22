package manager;

import powerup.PowerUp;
import snake.SnakeBody;

public class CollisionManager {

	private Handler handler;
	
	int currX1 = 0, currY1 = 0;
	int currX2 = 0, currY2 = 0;

	public CollisionManager(Handler handler) {
		this.handler = handler;
	}

	/*
	 * Can be made better. Right now, the only way it knows something collided
	 * is if its literally on top of another piece. It would be nice to have
	 * better collision checker that can see if the snake is going to run into
	 * another piece and stop the game before.
	 */
	public boolean checkWall() {
		boolean out = false;
		
		// oh god here we go.
		// ends if it is infinite grow
		
		currX1 = handler.getHead1().getX();
		currY1 = handler.getHead1().getY();
		currX2 = 0;
		currY2 = 0;
		

		// checks if snake is going to hit any of the outer bounds
		// first checks if it is going to hit the top
		if ((currY1 == 0) && (handler.getHead1().getDir().equals("n"))) {
			out = true;
			handler.setSnakeOneLose(true);
		}
		// checks to see if its gonna hit bottem. Scaleable
		if ((currY1== (handler.getBoardHeight() / handler.getBoxSize()) - 1)
				&& (handler.getHead1().getDir().equals("s"))) {
			out = true;
			handler.setSnakeOneLose(true);
		}
		// checks to see if its gonna hit the left/west side
		if ((currX1 == 0) && (handler.getHead1().getDir().equals("w"))) {
			out = true;
			handler.setSnakeOneLose(true);
		}
		// checks to see if its gonna hit the right/easst side
		if ((currX1 == (handler.getBoardWidth() / handler.getBoxSize()) - 1)
				&& (handler.getHead1().getDir().equals("e"))) {
			out = true;
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
				out = true;
				handler.setSnakeTwoLose(true);
			}

			// checks to see if its gonna hit bottem. Scaleable
			if ((currY2) == (handler.getBoardHeight() / handler.getBoxSize() - 1) 
					&& (handler.getHead2().getDir().equals("s"))) {
				
				out = true;
				handler.setSnakeTwoLose(true);
			}
			// checks to see if its gonna hit the left/west side
			if ((currX2 == 0) && (handler.getHead2().getDir().equals("w"))) {
				out = true;
				handler.setSnakeTwoLose(true);
			}
			// checks to see if its gonna hit the right/easst side
			if ((currX2 == (handler.getBoardWidth() / handler.getBoxSize()) - 1)
					&& (handler.getHead2().getDir().equals("e"))) {
				out = true;
				handler.setSnakeTwoLose(true);
			}
		}
		
		
		return out;
	}
	
	public boolean checkSnakeCollide(){
		
		boolean out = false;
		
		
		currX1 = handler.getHead1().getX();
		currY1 = handler.getHead1().getY();
		
//		for(SnakeBody element : handler.getSnake1()){
//			if(currX1 == element.getX() && currY2 == element.getY()){
//				out = true;
//				handler.setSnakeOneLose(true);
//			}
//		}

		
		//Head 1 to Snake 1
		for (SnakeBody element : handler.getSnake1()) {
			switch(handler.getHead1().getDir()){
			//if it is north, check to see if there is something above.
			case "n": 
				if(element.getX() == currX1 && element.getY() == currY1 - 1){
					out = true;
					handler.setSnakeOneLose(true);
				}
				break;
			case "s": 
				if(element.getX() == currX1 && element.getY() == currY1 + 1){
					out = true;
					handler.setSnakeOneLose(true);
				}
				break;
			case "w": 
				if(element.getX() == currX1 - 1 && element.getY() == currY1){
					out = true;
					handler.setSnakeOneLose(true);
				}
				break;
			case "e": 
				if(element.getX() == currX1 + 1 && element.getY() == currY1){
					out = true;
					handler.setSnakeOneLose(true);
				}
				break;
			default:
				break;
			}
		}
		
		

		// only checks these if it is two player
		if (handler.getGameMode() != 2) {			
			//Snake 2 coordinates
			currX2 = handler.getHead2().getX();
			currY2 = handler.getHead2().getY();	

			//Head 1 to Snake 2 and Head 2 to Snake 2
			for (SnakeBody element : handler.getSnake2()) {
				//head 1
				switch(handler.getHead1().getDir()){
				//if it is north, check to see if there is something above.
				case "n": 
					if(element.getX() == currX1 && element.getY() == currY1 - 1){
						out = true;
						handler.setSnakeOneLose(true);
					}
					break;
				case "s": 
					if(element.getX() == currX1 && element.getY() == currY1 + 1){
						out = true;
						handler.setSnakeOneLose(true);
					}
					break;
				case "w": 
					if(element.getX() == currX1 - 1 && element.getY() == currY1){
						out = true;
						handler.setSnakeOneLose(true);
					}
					break;
				case "e": 
					if(element.getX() == currX1 + 1 && element.getY() == currY1){
						out = true;
						handler.setSnakeOneLose(true);
					}
					break;
				default:
					break;
				}
				
				//Head 2
				switch(handler.getHead2().getDir()){
				//if it is north, check to see if there is something above.
				case "n": 
					if(element.getX() == currX2 && element.getY() == currY2 - 1){
						out = true;
						handler.setSnakeTwoLose(true);
					}
					break;
				case "s": 
					if(element.getX() == currX2 && element.getY() == currY2 + 1){
						out = true;
						handler.setSnakeTwoLose(true);
					}
					break;
				case "w": 
					if(element.getX() == currX2 - 1 && element.getY() == currY2){
						out = true;
						handler.setSnakeTwoLose(true);
					}
					break;
				case "e": 
					if(element.getX() == currX2 + 1 && element.getY() == currY2){
						out = true;
						handler.setSnakeTwoLose(true);
					}
					break;
				default:
					break;
				}
			}

			//Head 2 to Snake 1
			for (SnakeBody element : handler.getSnake1()) {
				switch(handler.getHead2().getDir()){
				//if it is north, check to see if there is something above.
				case "n": 
					if(element.getX() == currX2 && element.getY() == currY2 - 1){
						out = true;
						handler.setSnakeTwoLose(true);
					}
					break;
				case "s": 
					if(element.getX() == currX2 && element.getY() == currY2 + 1){
						out = true;
						handler.setSnakeTwoLose(true);
					}
					break;
				case "w": 
					if(element.getX() == currX2 - 1 && element.getY() == currY2){
						out = true;
						handler.setSnakeTwoLose(true);
					}
					break;
				case "e": 
					if(element.getX() == currX2 + 1 && element.getY() == currY2){
						out = true;
						handler.setSnakeTwoLose(true);
					}
					break;
				default:
					break;
				}
			}
			
//			
//			for(SnakeBody element : handler.getSnake1()){
//				if(currX2 == element.getX() && currY2 == element.getY()){
//					out = true;
//					handler.setSnakeTwoLose(true);
//				}
//			}
//			
//			for(SnakeBody element : handler.getSnake2()){
//				if(currX2 == element.getX() && currY2 == element.getY()){
//					out = true;
//					handler.setSnakeTwoLose(true);
//				}
//				if(currX1 == element.getX() && currY1 == element.getY()){
//					out = true;
//					handler.setSnakeOneLose(true);
//				}
//			}
			
			
			// checks for head to head
			if (handler.getHead1().getX() == handler.getHead2().getX()
					&& handler.getHead1().getY() == handler.getHead2().getY())
				out = true;
		}
		
		
		
		return out;
	}

	/**
	 * checks for collision with other stuff
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
