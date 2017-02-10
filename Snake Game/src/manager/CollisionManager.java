package manager;

import powerup.PowerUp;

public class CollisionManager {

	private Handler handler;

	public CollisionManager(Handler handler) {
		this.handler = handler;
	}

	/*
	 * Can be made better. Right now, the only way it knows something collided
	 * is if its literally on top of another piece. It would be nice to have
	 * better collision checker that can see if the snake is going to run into
	 * another piece and stop the game before.
	 */
	public void checkEnd() {
		// oh god here we go.
		// ends if it is infinite grow

		// checks if snake is going to hit any of the outer bounds
		// first checks if it is going to hit the top
		if ((handler.getHead1().getY() == 0) && (handler.getHead1().getDir().equals("n"))) {
			handler.setEnd(true);
		}
		// checks to see if its gonna hit bottem. Scaleable
		if ((handler.getHead1().getY() == (handler.getBoard().getHeight() / handler.getBoard().getBoxSize()) - 1)
				&& (handler.getHead1().getDir().equals("s"))) {
			handler.setEnd(true);
		}
		// checks to see if its gonna hit the left/west side
		if ((handler.getHead1().getX() == 0) && (handler.getHead1().getDir().equals("w"))) {
			handler.setEnd(true);
		}
		// checks to see if its gonna hit the right/easst side
		if ((handler.getHead1().getX() == (handler.getBoard().getWidth() / handler.getBoard().getBoxSize()) - 1)
				&& (handler.getHead1().getDir().equals("e"))) {
			handler.setEnd(true);
		}

		// now repeat for the other snake
		// first checks if it is going to hit the top
		// only checks second snake if it is two player
		if (handler.getGameMode() == 0 || handler.getGameMode() == 1) {
			if ((handler.getHead2().getY() == 0) && (handler.getHead2().getDir().equals("n"))) {
				handler.setEnd(true);
			}
			// checks to see if its gonna hit bottem. Scaleable
			if ((handler.getHead2().getY() == (handler.getBoard().getHeight() / handler.getBoard().getBoxSize()) - 1)
					&& (handler.getHead2().getDir().equals("s"))) {
				handler.setEnd(true);
			}
			// checks to see if its gonna hit the left/west side
			if ((handler.getHead2().getX() == 0) && (handler.getHead2().getDir().equals("w"))) {
				handler.setEnd(true);
			}
			// checks to see if its gonna hit the right/easst side
			if ((handler.getHead2().getX() == (handler.getBoard().getWidth() / handler.getBoard().getBoxSize()) - 1)
					&& (handler.getHead2().getDir().equals("e"))) {
				handler.setEnd(true);
			}
		}

		// checks snake 1 to snake 1
		for (int b = 0; b < handler.getSnake1().size(); b++) {
			if (handler.getSnake1().get(b).getY() == handler.getHead1().getY()
					&& handler.getSnake1().get(b).getX() == handler.getHead1().getX())
				handler.setEnd(true);
			;
		}

		// only checks these if it is two player
		if (handler.getGameMode() == 0 || handler.getGameMode() == 1) {
			// checks for inter-game collisions. first off, head one to body 1
			// and body 2
			for (int a = 0; a < handler.getSnake2().size(); a++) {
				// checks for colision with snake body
				if (handler.getSnake2().get(a).getY() == handler.getHead1().getY()
						&& handler.getSnake2().get(a).getX() == handler.getHead1().getX())
					handler.setEnd(true);
			}
			// snake 2 to body 2 and body 1
			for (int a = 0; a < handler.getSnake2().size(); a++) {
				// checks for colision with snake body
				if (handler.getSnake2().get(a).getY() == handler.getHead2().getY()
						&& handler.getSnake2().get(a).getX() == handler.getHead2().getX())
					handler.setEnd(true);
			}
			for (int b = 0; b < handler.getSnake1().size(); b++) {
				if (handler.getSnake1().get(b).getY() == handler.getHead2().getY()
						&& handler.getSnake1().get(b).getX() == handler.getHead2().getX())
					handler.setEnd(true);
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
