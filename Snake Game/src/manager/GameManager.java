package manager;
import java.util.ArrayList;

import powerup.*;
import snake.SnakeBody;
import snake.SnakeHead;
import ui.BoardGUI;
import ui.GUI;
import ui.MenuBar;
import ui.ScorePanel;

public class GameManager {
	Handler handler;
	
	
	
	public GameManager(Handler handler){
		this.handler = handler;
		this.handler.setBoard(new BoardGUI(this.handler));
		this.handler.setMenu(new MenuBar(this.handler));
		this.handler.setScorePanel(new ScorePanel(this.handler));

		
		this.handler.setCollisionCheck(new CollisionManager(this.handler));
		
		
		initialize();
		//add power ups here (temp)
		this.handler.getPowerUps().add(new Food(this.handler));
		
		
		this.handler.setGui(new GUI(this.handler));
		
		
		
		
		gameRun();
	}
	
	public void initialize(){
		
		checkGameMode();
		
		//if its the normal 2p or 1p spawn init snek this way
		if(handler.getGameMode() == 0 || handler.getGameMode() == 2){
			//initializes values for the first snake
			randomSpawn(true);
		}
		//normal snek spawn
		if(handler.getGameMode() == 0){
			//initializes values for second snake
			randomSpawn(false);
		}
		
		//if inifinite grow 2p
		if(handler.getGameMode() == 1){
			handler.setHead1(new SnakeHead(1,0, "e"));
			handler.getSnake1().add(new SnakeBody(0,0));
			handler.setHead2(new SnakeHead((handler.getBoard().getWidth()/handler.getBoard().getBoxSize()) - 2, (handler.getBoard().getHeight()/handler.getBoard().getBoxSize()) - 1, "w"));
			handler.getSnake2().add(new SnakeBody((handler.getBoard().getWidth()/handler.getBoard().getBoxSize()) - 1, (handler.getBoard().getHeight()/handler.getBoard().getBoxSize()) - 1));
		}
		
		
		handler.getScorePanel().redoScore();
	}
	
	private void randomSpawn(boolean one) {
		int offset = 5;
		int dir = (int) (Math.random() * 4); // 0 NORTH 1 SOUTH 2 EAST 3 WEST
		// the snake will spawn at least 5 blocks from sides
		int upBound = (offset -1), downBound = handler.getVertBoxes() - (offset), leftBound = (offset -1), rightBound = handler.getHoriBoxes() - (offset);
		
		int x = (int) (Math.random() * (rightBound -leftBound)) + leftBound; //ensures that it isnt on side
		int y = (int) (Math.random() * (downBound - upBound)) + upBound; //ensures that it isnt on side
		//if it is spawning the first snake;
		if(one){
			//spawns snakes
			switch(dir){
			case 0:	//north.
				handler.setHead1(new SnakeHead(x, y, "n"));
				handler.getSnake1().add(new SnakeBody(x, y +1));
				handler.getSnake1().add(new SnakeBody(x, y +2));
				handler.getSnake1().add(new SnakeBody(x, y +3));
				break;
			case 1: //south
				handler.setHead1(new SnakeHead(x, y, "s"));
				handler.getSnake1().add(new SnakeBody(x, y -1));
				handler.getSnake1().add(new SnakeBody(x, y -2));
				handler.getSnake1().add(new SnakeBody(x, y -3));
				break;
			case 2:	//EAST
				handler.setHead1(new SnakeHead(x, y, "e"));
				handler.getSnake1().add(new SnakeBody(x -1, y));
				handler.getSnake1().add(new SnakeBody(x -2, y));
				handler.getSnake1().add(new SnakeBody(x -3, y));
				break;
			
			case 3: //WEST
				handler.setHead1(new SnakeHead(x, y, "w"));
				handler.getSnake1().add(new SnakeBody(x +1, y));
				handler.getSnake1().add(new SnakeBody(x +2, y));
				handler.getSnake1().add(new SnakeBody(x +3, y));
				break;
			default: break;
			}
			
			return;
		}
		
		
		
		
		//spawning for the second snake
		//makes sure it is at least 6 blocks away from other snake
		x = (int) (Math.random() * (rightBound -leftBound)) + leftBound; //ensures that it isnt on side
		y = (int) (Math.random() * (downBound - upBound)) + upBound; //ensures that it isnt on side
		boolean uniqueSpawnX = false, uniqueSpawnY = false;
		//makes sure the x is at least 6 blocks away
		while(!uniqueSpawnX){
			if(Math.abs(handler.getHead1().getX() - x) < 6)
				x = (int) (Math.random() * (rightBound -leftBound)) + leftBound; //ensures that it isnt on side
			else
				uniqueSpawnX = true;
		}
		while(!uniqueSpawnY){
			if(Math.abs(handler.getHead1().getY() - y) < 6)
				y = (int) (Math.random() * (downBound - upBound)) + upBound; //ensures that it isnt on side
			else
				uniqueSpawnY = true;
		}
		//annother random dir
		dir = (int) (Math.random() * 4); // 0 NORTH 1 SOUTH 2 EAST 3 WEST
		//spawns the sanke
		switch(dir){
		case 0:	//north.
			handler.setHead2(new SnakeHead(x, y, "n"));
			handler.getSnake2().add(new SnakeBody(x, y +1));
			handler.getSnake2().add(new SnakeBody(x, y +2));
			handler.getSnake2().add(new SnakeBody(x, y +3));
			break;
		case 1: //south
			handler.setHead2(new SnakeHead(x, y, "s"));
			handler.getSnake2().add(new SnakeBody(x, y -1));
			handler.getSnake2().add(new SnakeBody(x, y -2));
			handler.getSnake2().add(new SnakeBody(x, y -3));
			break;
		case 2:	//EAST
			handler.setHead2(new SnakeHead(x, y, "e"));
			handler.getSnake2().add(new SnakeBody(x -1, y));
			handler.getSnake2().add(new SnakeBody(x -2, y));
			handler.getSnake2().add(new SnakeBody(x -3, y));
			break;
		
		case 3: //WEST
			handler.setHead2(new SnakeHead(x, y, "w"));
			handler.getSnake2().add(new SnakeBody(x +1, y));
			handler.getSnake2().add(new SnakeBody(x +2, y));
			handler.getSnake2().add(new SnakeBody(x +3, y));
			break;
		default: break;
		}
		
		return;
		
		
	}

	//the actual continual process of the game
	public void gameRun(){
		//spawns the food
//		handler.getFood().spawnFood();
		if(handler.getGameMode() != 1){
			handler.getBoard().drawPowerUps();
		}
		
		//draws the snakes
		handler.getBoard().drawSnake1();
		if(!(handler.getGameMode() == 2))
			handler.getBoard().drawSnake2();
		
		//pauses the game while it waits to start
		while(!handler.isStart()){
			checkReset();
			checkGameMode();
			handler.getScorePanel().redoFPS();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//main game function
		while(!handler.isEnd()){									
			//checks for powerups taken
			handler.getCollisionCheck().powerUpCheck();
			
			//makes sure it isn't infinite grow
			checkGrow();	
			
			checkReset();
			
			checkPause();
			
			handler.getCollisionCheck().checkEnd();
			
			if(handler.isEnd())
				break;
			
			moveSnake();
			
			
			if(handler.getGameMode() != 1){
				handler.getBoard().drawPowerUps();
			}
			
			//draws the snakes
			handler.getBoard().drawSnake1();
			if(!(handler.getGameMode() == 2))
				handler.getBoard().drawSnake2();
			
			handler.getGui().repaint();
			handler.getScorePanel().redoScore();
			handler.getMenu().repaint();

			try {
				Thread.sleep(handler.getDelayTimer());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//
		}
		
		handler.setWait(true);
		gameEndWait();
		
	}
	
	private void checkPause(){
		while(handler.isPause()){
			checkGameMode();
			checkReset();
			handler.getScorePanel().redoFPS();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void gameEndWait(){
		while(handler.isWait()){
			checkGameMode();
			checkReset();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void checkReset() {
		if(handler.isReset()){
			resetGame();
		}
		
	}

	private void moveSnake() {
		//removes the end piece
		//only removes if gamemode is not the infinite grow
		if(!(handler.getGameMode() == 1)){
			if(!handler.isGrowing1()){
				//removes from the board
				handler.getBoard().removeSnakePart(handler.getSnake1().get(handler.getSnake1().size()-1));			
				//removes from the arraylists
				handler.getSnake1().remove(handler.getSnake1().size()-1);
			}
			
			//if its the two player game
			if(handler.getGameMode() == 0){
				if(!handler.isGrowing2()){
					//removes from the board
					handler.getBoard().removeSnakePart(handler.getSnake2().get(handler.getSnake2().size()-1));			
					//removes from the arraylists
					handler.getSnake2().remove(handler.getSnake2().size()-1);
				}
			}
		}
		
		
		//ads the new body location
		SnakeBody body1 = new SnakeBody(handler.getHead1().getX(), handler.getHead1().getY());
		handler.getSnake1().add(0, body1);
		
		//moves second snake's head
		switch(handler.getHead1().getDir()){
		case "n" : 
			handler.getHead1().setY(handler.getHead1().getY()-1);
			break;
		case "s":
			handler.getHead1().setY(handler.getHead1().getY()+1);
			break;
		case "w":
			handler.getHead1().setX(handler.getHead1().getX()-1);
			break;
		case "e":
			handler.getHead1().setX(handler.getHead1().getX()+1);
			break;
		default: break;
		}
		
		//if it is not single player, check the second head.
		if(!(handler.getGameMode() == 2)){
			
			SnakeBody body2 = new SnakeBody(handler.getHead2().getX(), handler.getHead2().getY());
			handler.getSnake2().add(0, body2);
			
			//checks the direction of the second snake, then moves
			switch(handler.getHead2().getDir()){
			case "n" : 
				handler.getHead2().setY(handler.getHead2().getY()-1);
				break;
			case "s":
				handler.getHead2().setY(handler.getHead2().getY()+1);
				break;
			case "w":
				handler.getHead2().setX(handler.getHead2().getX()-1);
				break;
			case "e":
				handler.getHead2().setX(handler.getHead2().getX()+1);
				break;
			default: break;
			}
		}		
	}


	private void checkGrow() {
		//ends the mothod if it is infiite grow
		if(handler.getGameMode() == 1){
			return;
		}
		//checks for growing for the first snake
		if(handler.isGrowing1())
			if(handler.getGrowCount1() < handler.getGrowThresh1())
				handler.setGrowCount1(handler.getGrowCount1() + 1);
			 else 
				handler.setGrowing1(false);
			
		//checks for growing for the second snake
		//checks to make sure it is two player first
		if(!(handler.getGameMode() == 2) && handler.isGrowing2())
			if(handler.getGrowCount2() < handler.getGrowThresh2())
				handler.setGrowCount2(handler.getGrowCount2() + 1);
			else
				handler.setGrowing2(false);
	}

	public void resetGame(){
		//resets all major values first
		handler.setEnd(false);
		handler.setStart(false);;
		handler.setGrowing1(false);
		handler.setGrowing2(false);
		handler.setReset(false);
		handler.setWait(false);    
		handler.setPause(false);
		
		handler.setSnake1(new ArrayList <SnakeBody>());
		handler.setSnake2(new ArrayList <SnakeBody>());

		//now everything should be reset on the logic side except some other things
		//however, those things are reset automatically by invoking other methods
		
		handler.getBoard().reset();
		
		for(PowerUp element : handler.getPowerUps()){
			element.spawn();
		}
		
		initialize();
		
		//dont ask. it just works
		handler.getGui().remove(handler.getScorePanel());
		handler.setScorePanel(new ScorePanel(handler));
		handler.getGui().add(handler.getScorePanel());
		
		
		gameRun();
		
	}


	private void checkGameMode(){
		if(handler.getGameMode() != handler.getGameModeHold()){
			handler.setGameMode(handler.getGameModeHold());
			resetGame();				
		}
	}


}