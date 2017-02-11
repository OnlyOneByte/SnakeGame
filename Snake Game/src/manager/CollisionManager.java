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
			
			//checks snake 2 to snake two
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
	
	public void checkSnakeCollide(){
		currX1 = handler.getHead1().getX();
		currY1 = handler.getHead1().getY();
		currX2 = 0;
		currY2 = 0;
		
		//snake one to snake one
		//first interates through all of the snake bodies
		for (SnakeBody element : handler.getSnake1()) {
			switch(handler.getHead1().getDir()){
			//if it is north, check to see if there is something above.
			case "n": 
				if(element.getX() == currX1 && element.getY() == currY1 - 1){
					handler.setEnd(true);
					handler.setSnakeOneLose(true);
				}
				break;
			case "s": 
				if(element.getX() == currX1 && element.getY() == currY1 + 1){
					handler.setEnd(true);
					handler.setSnakeOneLose(true);
				}
				break;
			case "w": 
				if(element.getX() == currX1 - 1 && element.getY() == currY1){
					handler.setEnd(true);
					handler.setSnakeOneLose(true);
				}
				break;
			case "e": 
				if(element.getX() == currX1 + 1 && element.getY() == currY1){
					handler.setEnd(true);
					handler.setSnakeOneLose(true);
				}
				break;
			default:
				break;
			}
		}

		// only checks these if it is two player
		if (handler.getGameMode() != 2) {
			
			//checks snake 2 to snake two
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
			
			
			
			//head 1 to boyd 2
			for (int i = 0; i < handler.getSnake2().size(); i++) {
				switch(handler.getHead1().getDir()){
				//if it is north, check to see if there is something above.
				case "n": 
					//checks head1 to snake 2
					if(handler.getSnake2().get(i).getX() == currX1 && handler.getSnake2().get(i).getY() == currY1 - 1){
						handler.setEnd(true);
						handler.setSnakeOneLose(true);
					}
					break;
				case "s": 
					if(handler.getSnake2().get(i).getX() == currX1 && handler.getSnake2().get(i).getY() == currY1 + 1){
						handler.setEnd(true);
						handler.setSnakeOneLose(true);
					}
					break;
				case "w": 
					if(handler.getSnake2().get(i).getX() == currX1 - 1 && handler.getSnake2().get(i).getY() == currY1){
						handler.setEnd(true);
						handler.setSnakeOneLose(true);
					}
					break;
				case "e": 
					if(handler.getSnake2().get(i).getX() == currX1 + 1 && handler.getSnake2().get(i).getY() == currY1){
						handler.setEnd(true);
						handler.setSnakeOneLose(true);
					}
					break;
				default:
					break;
				}
			}		
			//snakke head 2 to snake 1
			//first interates through all of the snake bodies
			for (int i = 0; i < handler.getSnake1().size(); i++) {
				switch(handler.getHead2().getDir()){
				//if it is north, check to see if there is something above.
				case "n": 
					if(handler.getSnake1().get(i).getX() == currX2 && handler.getSnake1().get(i).getY() == currY2 - 1){
						handler.setEnd(true);
						handler.setSnakeOneLose(true);
					}
					break;
				case "s": 
					if(handler.getSnake1().get(i).getX() == currX2 && handler.getSnake1().get(i).getY() == currY2 + 1){
						handler.setEnd(true);
						handler.setSnakeOneLose(true);
					}
					break;
				case "w": 
					if(handler.getSnake1().get(i).getX() == currX2 - 1 && handler.getSnake1().get(i).getY() == currY2){
						handler.setEnd(true);
						handler.setSnakeOneLose(true);
					}
					break;
				case "e": 
					if(handler.getSnake1().get(i).getX() == currX2 + 1 && handler.getSnake1().get(i).getY() == currY2){
						handler.setEnd(true);
						handler.setSnakeOneLose(true);
					}
					break;
				default:
					break;
				}
			}

			
			
			

			// checks for head to head
			if (handler.getHead1().getX() == handler.getHead2().getX()
					&& handler.getHead1().getY() == handler.getHead2().getY())
				handler.setEnd(true);
		}

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
