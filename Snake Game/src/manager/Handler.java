package manager;

import java.util.ArrayList;

import snake.SnakeBody;
import snake.SnakeHead;
import ui.GUI;
import ui.ScorePanel;
import powerup.*;

public class Handler {
	// Snake # 1
	private static SnakeHead head1;
	private static ArrayList<SnakeBody> snake1 = new ArrayList<SnakeBody>();

	// snake # 2
	private static SnakeHead head2;
	private static ArrayList<SnakeBody> snake2 = new ArrayList<SnakeBody>();

	// miscelanous variables.
	private static boolean end = false; // determines when the game ends
	private static boolean start = false; // doesn't start the game until
											// something turns this to true
	private static boolean wait = false; // after the game ends, this keeps
											// the program running waiting for
											// input
	private static boolean pause = false; // pauses the game
	private final static int DEFAULT_DELAY = 200; // reverts to this delay if
													// the reset to default
													// button is clicked

	private static int gameModeHold = 0; // holds the game mode, only stores
											// it back in after game or before
	private static int gameMode = 0; // 0 = normal 2p, 1 = infinite grow 2p, 2
										// = 1p

	private static int growMin = 1;
	private static int growMax = 4;
	
	//stuff, for the board GUI
	public static final int DEFAULT_WIDTH = 700;
	public static final int DEFAULT_HEIGHT = 700;
	public static final int DEFAULT_BOX_SIZE = 25;
	
	private static int boxSize = 25;
	private static int edgeBorder = 25;
	private static int boardWidth = 700;
	private static int boardHeight = 700;
	
	
	
	
	
	
	private int vertBoxes = DEFAULT_HEIGHT/DEFAULT_BOX_SIZE;
	private int horiBoxes = DEFAULT_WIDTH/DEFAULT_BOX_SIZE;

	// growing and length variables for snake1
	private boolean isGrowing1 = false; // grows the snake1 if true
	private int growCount1 = 0; // counts the grow for snake 1
	private int growThresh1 = 3; // how much the snake 1 grows for each food
									// eaten
	// growing and length variables for snake2
	private boolean isGrowing2 = false; // grows the snake2 if true
	private int growCount2 = 0; // counts the grow for snake 2
	private int growThresh2 = 3; // how much the snake 2 grows for each food
									// eaten

	private static long delayTimer = getDefaultDelay(); // in milliseconds, the
	// delay between each frame

	private boolean reset = false; // will check in game run, if true it resets
									// the game

	private int score; // for the score counter
	
	private ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	
	private ScorePanel scorePanel;	//has to be initiated after delay timer, or error
	
	private CollisionManager collisionCheck;
	
	private GUI gui; //the board

	public Handler() {

	}

	// head 1
	public SnakeHead getHead1() {
		return head1;
	}

	public void setHead1(SnakeHead head1) {
		Handler.head1 = head1;
	}

	// snake 1
	public ArrayList<SnakeBody> getSnake1() {
		return snake1;
	}

	public void setSnake1(ArrayList<SnakeBody> snake1) {
		Handler.snake1 = snake1;
	}

	// head 2
	public SnakeHead getHead2() {
		return head2;
	}

	public void setHead2(SnakeHead head2) {
		Handler.head2 = head2;
	}

	// snake 2
	public ArrayList<SnakeBody> getSnake2() {
		return snake2;
	}

	public void setSnake2(ArrayList<SnakeBody> snake2) {
		Handler.snake2 = snake2;
	}

	// end
	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		Handler.end = end;
	}

	// start
	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		Handler.start = start;
	}

	// wait
	public boolean isWait() {
		return wait;
	}

	public void setWait(boolean wait) {
		Handler.wait = wait;
	}

	// pause
	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		Handler.pause = pause;
	}

	// gamemodeHold
	public int getGameModeHold() {
		return gameModeHold;
	}

	public void setGameModeHold(int gameModeHold) {
		Handler.gameModeHold = gameModeHold;
	}

	// gameMode
	public int getGameMode() {
		return gameMode;
	}

	public void setGameMode(int gameMode) {
		Handler.gameMode = gameMode;
	}

	// growMin
	public int getGrowMin() {
		return growMin;
	}

	public void setGrowMin(int growMin) {
		Handler.growMin = growMin;
	}

	// GrowMax
	public int getGrowMax() {
		return growMax;
	}

	public void setGrowMax(int growMax) {
		Handler.growMax = growMax;
	}

	// Vert Boxes
	public int getVertBoxes() {
		return vertBoxes;
	}

	public void setVertBoxes(int vertBoxes) {
		this.vertBoxes = vertBoxes;
	}

	// Horizontal Boxes
	public int getHoriBoxes() {
		return horiBoxes;
	}

	public void setHoriBoxes(int horiBoxes) {
		this.horiBoxes = horiBoxes;
	}

	public boolean isGrowing1() {
		return isGrowing1;
	}

	public void setGrowing1(boolean isGrowing1) {
		this.isGrowing1 = isGrowing1;
	}

	public int getGrowCount1() {
		return growCount1;
	}

	public void setGrowCount1(int growCount1) {
		this.growCount1 = growCount1;
	}

	public int getGrowThresh1() {
		return growThresh1;
	}

	public void setGrowThresh1(int growThresh1) {
		this.growThresh1 = growThresh1;
	}

	public boolean isGrowing2() {
		return isGrowing2;
	}

	public void setGrowing2(boolean isGrowing2) {
		this.isGrowing2 = isGrowing2;
	}

	public int getGrowCount2() {
		return growCount2;
	}

	public void setGrowCount2(int growCount2) {
		this.growCount2 = growCount2;
	}

	public int getGrowThresh2() {
		return growThresh2;
	}

	public void setGrowThresh2(int growThresh2) {
		this.growThresh2 = growThresh2;
	}


	public static int getDefaultDelay() {
		return DEFAULT_DELAY;
	}

	public long getDelayTimer() {
		return delayTimer;
	}

	public void setDelayTimer(long delayTimer) {
		Handler.delayTimer = delayTimer;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isReset() {
		return reset;
	}

	public void setReset(boolean reset) {
		this.reset = reset;
	}

	public GUI getGui() {
		return gui;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}

	public ScorePanel getScorePanel() {
		return scorePanel;
	}

	public void setScorePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
	}

	public CollisionManager getCollisionCheck() {
		return collisionCheck;
	}

	public void setCollisionCheck(CollisionManager collisionCheck) {
		this.collisionCheck = collisionCheck;
	}

	public ArrayList<PowerUp> getPowerUps() {
		return powerUps;
	}

	public void setPowerUps(ArrayList<PowerUp> powerUps) {
		this.powerUps = powerUps;
	}

	public int getEdgeBorderSize() {
		return edgeBorder;
	}

	public void setEdgeBorderSize(int edgeBorder) {
		Handler.edgeBorder = edgeBorder;
	}

	public int getBoxSize() {
		return boxSize;
	}

	public void setBoxSize(int boxSize) {
		Handler.boxSize = boxSize;
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public static void setBoardWidth(int boardWidth) {
		Handler.boardWidth = boardWidth;
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public static void setBoardHeight(int boardHeight) {
		Handler.boardHeight = boardHeight;
	}

}
