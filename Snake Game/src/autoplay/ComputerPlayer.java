package autoplay;

import manager.Handler;

public abstract class ComputerPlayer {
	
	/**
	 * The food X and foodY
	 */
	protected int foodX, foodY;
	/**
	 * The X and Y of the head of the snake.
	 */
	protected int x, y;
	
	/**
	 * The relative directions of the snake.
	 */
	protected String left, right, forward;
	
	/**
	 * The handler.
	 */
	protected Handler handler;
	
	public ComputerPlayer(Handler handler){
		this.handler = handler;
	}
	
	/**
	 * This is the method that will move the snake.
	 */
	public abstract void move();
	
	
	/**
	 * This sees if the right of the snake is blocked
	 * @return 	true, if either the wall or a part of the other snake is blocking
	 */
	protected boolean rightBlocked() {
		boolean out = false;
		// the head Y
		int currX2 = handler.getHead2().getX();
		int currY2 = handler.getHead2().getY();

		// checks for walls.
		// checks to see if the top is on the right
		if (currY2 == 0 && handler.getHead2().getDir().equals("w")) {
			out = true;
		}
		// checks to see if the bottom is on the right
		if ((currY2) == (handler.getBoardHeight() / handler.getBoxSize() - 1)
				&& (handler.getHead2().getDir().equals("e"))) {
			out = true;
		}
		// checks to see if the west side is on the right
		if ((currX2 == 0) && (handler.getHead2().getDir().equals("s"))) {
			out = true;
		}
		// checks to see if east is on the right
		if ((currX2 == (handler.getBoardWidth() / handler.getBoxSize()) - 1)
				&& (handler.getHead2().getDir().equals("n"))) {
			out = true;
		}

		// checks for snake1 parts.
		for (int i = 0; i < handler.getSnake1().size(); i++) {
			switch (handler.getHead2().getDir()) {
			// if it is north, check to see if there is something to the right.
			case "n":
				if (handler.getSnake1().get(i).getX() == currX2 + 1 && handler.getSnake1().get(i).getY() == currY2) {
					out = true;
				}
				break;
			case "s":
				if (handler.getSnake1().get(i).getX() == currX2 - 1 && handler.getSnake1().get(i).getY() == currY2) {
					out = true;
				}
				break;
			case "w":
				if (handler.getSnake1().get(i).getX() == currX2 && handler.getSnake1().get(i).getY() == currY2 - 1) {
					out = true;
				}
				break;
			case "e":
				if (handler.getSnake1().get(i).getX() == currX2 && handler.getSnake1().get(i).getY() == currY2 + 1) {
					out = true;
				}
				break;
			default:
				break;
			}
		}
		// head 2 to body 2
		for (int i = 0; i < handler.getSnake2().size(); i++) {
			switch (handler.getHead2().getDir()) {
			// if it is north, check to see if there is something above.
			case "n":
				// checks head1 to snake 2
				if (handler.getSnake2().get(i).getX() == currX2 + 1 && handler.getSnake2().get(i).getY() == currY2) {
					out = true;
				}
				break;
			case "s":
				if (handler.getSnake2().get(i).getX() == currX2 - 1 && handler.getSnake2().get(i).getY() == currY2) {
					out = true;
				}
				break;
			case "w":
				if (handler.getSnake2().get(i).getX() == currX2 && handler.getSnake2().get(i).getY() == currY2 - 1) {
					out = true;
				}
				break;
			case "e":
				if (handler.getSnake2().get(i).getX() == currX2 && handler.getSnake2().get(i).getY() == currY2 + 1) {
					out = true;
				}
				break;
			default:
				break;
			}
		}

		return out;
	}
	/**
	 * This sees if the left of the snake is blocked
	 * @return 	true, if either the wall or a part of the other snake is blocking
	 */
	protected boolean leftBlocked() {
		boolean out = false;
		// the head Y
		int currX2 = handler.getHead2().getX();
		int currY2 = handler.getHead2().getY();

		// checks for walls.
		// checks to see if the top is on the left
		if (currY2 == 0 && handler.getHead2().getDir().equals("e")) {
			out = true;
		}
		// checks to see if the bottom is on the left
		if ((currY2) == (handler.getBoardHeight() / handler.getBoxSize() - 1)
				&& (handler.getHead2().getDir().equals("w"))) {
			out = true;
		}
		// checks to see if thge west side is on the left
		if ((currX2 == 0) && (handler.getHead2().getDir().equals("n"))) {
			out = true;
		}
		// checks to see if east is on the left
		if ((currX2 == (handler.getBoardWidth() / handler.getBoxSize()) - 1)
				&& (handler.getHead2().getDir().equals("s"))) {
			out = true;
		}

		// checks for snake1 parts.
		for (int i = 0; i < handler.getSnake1().size(); i++) {
			switch (handler.getHead2().getDir()) {
			// if it is north, check to see if there is something to the left.
			case "n":
				if (handler.getSnake1().get(i).getX() == currX2 - 1 && handler.getSnake1().get(i).getY() == currY2) {
					out = true;
				}
				break;
			case "s":
				if (handler.getSnake1().get(i).getX() == currX2 + 1 && handler.getSnake1().get(i).getY() == currY2) {
					out = true;
				}
				break;
			case "w":
				if (handler.getSnake1().get(i).getX() == currX2 && handler.getSnake1().get(i).getY() == currY2 + 1) {
					out = true;
				}
				break;
			case "e":
				if (handler.getSnake1().get(i).getX() == currX2 && handler.getSnake1().get(i).getY() == currY2 - 1) {
					out = true;
				}
				break;
			default:
				break;
			}
		}

		// head 2 to body 2
		for (int i = 0; i < handler.getSnake2().size(); i++) {
			switch (handler.getHead2().getDir()) {
			// if it is north, check to see if there is something above.
			case "n":
				// checks head1 to snake 2
				if (handler.getSnake2().get(i).getX() == currX2 - 1 && handler.getSnake2().get(i).getY() == currY2) {
					out = true;
				}
				break;
			case "s":
				if (handler.getSnake2().get(i).getX() == currX2 + 1 && handler.getSnake2().get(i).getY() == currY2) {
					out = true;
				}
				break;
			case "w":
				if (handler.getSnake2().get(i).getX() == currX2 && handler.getSnake2().get(i).getY() == currY2 + 1) {
					out = true;
				}
				break;
			case "e":
				if (handler.getSnake2().get(i).getX() == currX2 && handler.getSnake2().get(i).getY() == currY2 - 1) {
					out = true;
				}
				break;
			default:
				break;
			}
		}

		return out;
	}
	/**
	 * This sees if the front of the snake is blocked
	 * @return 	true, if either the wall or a part of the other snake is blocking
	 */
	protected boolean frontBlocked() {
		boolean out = false;
		// the head Y
		int currX2 = handler.getHead2().getX();
		int currY2 = handler.getHead2().getY();

		// checks for walls.
		// chekcs to see if its gonna hit top.
		if (currY2 == 0 && handler.getHead2().getDir().equals("n")) {
			out = true;
		}
		// checks to see if its gonna hit bottem. Scaleable
		if ((currY2) == (handler.getBoardHeight() / handler.getBoxSize() - 1)
				&& (handler.getHead2().getDir().equals("s"))) {
			out = true;
		}
		// checks to see if its gonna hit the left/west side
		if ((currX2 == 0) && (handler.getHead2().getDir().equals("w"))) {
			out = true;
		}
		// checks to see if its gonna hit the right/easst side
		if ((currX2 == (handler.getBoardWidth() / handler.getBoxSize()) - 1)
				&& (handler.getHead2().getDir().equals("e"))) {
			out = true;
		}

		// checks for snake1 parts.
		for (int i = 0; i < handler.getSnake1().size(); i++) {
			switch (handler.getHead2().getDir()) {
			// if it is north, check to see if there is something above.
			case "n":
				if (handler.getSnake1().get(i).getX() == currX2 && handler.getSnake1().get(i).getY() == currY2 - 1) {
					out = true;
				}
				break;
			case "s":
				if (handler.getSnake1().get(i).getX() == currX2 && handler.getSnake1().get(i).getY() == currY2 + 1) {
					out = true;
				}
				break;
			case "w":
				if (handler.getSnake1().get(i).getX() == currX2 - 1 && handler.getSnake1().get(i).getY() == currY2) {
					out = true;
				}
				break;
			case "e":
				if (handler.getSnake1().get(i).getX() == currX2 + 1 && handler.getSnake1().get(i).getY() == currY2) {
					out = true;
				}
				break;
			default:
				break;
			}
		}
		// checks for snake2 parts.
		for (int i = 0; i < handler.getSnake2().size(); i++) {
			switch (handler.getHead2().getDir()) {
			// if it is north, check to see if there is something above.
			case "n":
				if (handler.getSnake2().get(i).getX() == currX2 && handler.getSnake2().get(i).getY() == currY2 - 1) {
					out = true;
				}
				break;
			case "s":
				if (handler.getSnake2().get(i).getX() == currX2 && handler.getSnake2().get(i).getY() == currY2 + 1) {
					out = true;
				}
				break;
			case "w":
				if (handler.getSnake2().get(i).getX() == currX2 - 1 && handler.getSnake2().get(i).getY() == currY2) {
					out = true;
				}
				break;
			case "e":
				if (handler.getSnake2().get(i).getX() == currX2 + 1 && handler.getSnake2().get(i).getY() == currY2) {
					out = true;
				}
				break;
			default:
				break;
			}
		}

		return out;
	}
}
