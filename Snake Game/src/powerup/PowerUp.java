
package powerup;

import manager.Handler;
public abstract class PowerUp {
	protected int x, y;
	protected Handler handler;
	
	
	public PowerUp(Handler handler){
		this.handler = handler;
	}
	
	public void spawn(){
		//ends the mothod if it is infitie grow
		if(handler.getGameMode() == 1)
			return;
		
		boolean uniqueLocation = false;  //if true, it will end and draw the food.
		
		while(!uniqueLocation){
			uniqueLocation = true; //sets it to true, and if an intersection is found, it will be false.
			
			x = ((int) (Math.random() * (handler.getBoard().getHeight()/handler.getBoard().getBoxSize())));
			y = ((int) (Math.random() * (handler.getBoard().getHeight()/handler.getBoard().getBoxSize())));
			
			//checks for intersection with snake 1's body
			for(int a = 0; a < handler.getSnake1().size(); a++)
				if((handler.getSnake1().get(a).getX() == x) && handler.getSnake1().get(a).getY() == y)
					uniqueLocation = false;
			
			//checks for intersection with snake 2's body
			if(!(handler.getGameMode() == 2))
				for(int a = 0; a < handler.getSnake2().size(); a++)
					if((handler.getSnake2().get(a).getX() == x) && handler.getSnake2().get(a).getY() == y)
						uniqueLocation = false;
			
			//checks for intersection with the head of the first snake.
			if(handler.getHead1().getX() == x && handler.getHead1().getY() == y)
				uniqueLocation = false;
			
			//checks for intersection with the ehad of the second sanke
			if(!(handler.getGameMode() == 2))
				if(handler.getHead2().getX() == x && handler.getHead2().getY() == y)
					uniqueLocation = false;
			
			//checks for intersection with other powerups
			for(PowerUp element : handler.getPowerUps()){
				if(element.getX() == x && element.getY() == y && !element.equals(this)){
					uniqueLocation = false;
				}
			}
		} //end of while loop
		
	}
	
	public abstract void sideEffect(int snake);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
