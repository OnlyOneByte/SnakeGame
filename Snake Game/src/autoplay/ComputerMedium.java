package autoplay;

import manager.Handler;

public class ComputerMedium extends ComputerPlayer {
	
	/**
	 * Thie constructor
	 * @param handler	The handler that is passed in
	 */
	public ComputerMedium(Handler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This is a nice move.
	 * It basically moves towards the food whenever possible.
	 */
	@Override
	public void move() {
		// a few basic tenents.
		// moves at random and tries to keep at least 2 blocks away.
		// moves the second snake
		// sets the foodX
		foodX = handler.getPowerUps().get(0).getX();
		foodY = handler.getPowerUps().get(0).getY();

		x = handler.getHead2().getX();
		y = handler.getHead2().getY();

		// AI goes for food.
		// 25% chance of changing to either direction, and 50% of going straight
		// collision detection so that it moves.

		// this is what is relative left right to the snake
		left = null;
		right = null;
		// this switch determines left and right
		switch (handler.getHead2().getDir()) {
		case "n":
			left = "w";
			right = "e";
			break;
		case "s":
			left = "e";
			right = "w";
			break;
		case "w":
			left = "s";
			right = "n";
			break;
		case "e":
			left = "n";
			right = "s";
			break;
		}

		forward = handler.getHead2().getDir();

		// first the collision detection
		// checks the front.
		if (frontBlocked()) {
			// this checks to see if anything is blocked first
			// if both sides are blocked then this "dumb" ai will just kill
			// itself.
			if (leftBlocked()) {
				// then moves right
				handler.getHead2().setDir(right);
				return;
			}

			else if (rightBlocked()) {
				// moves left
				handler.getHead2().setDir(left);
				return;
			}

			else {
				// first checks if either direction is better.
				// This is if going left is better than right
				if (evaluateMove(left) < evaluateMove(right)) {
					handler.getHead2().setDir(left);
					return;
				} else
				// if the right is better than left
				if (evaluateMove(right) < evaluateMove(left)) {
					handler.getHead2().setDir(right);
					return;
				}
				// if they are equal, so it just chooses one at random
				else {
					// 50% chance for either side.
					int dir = (int) (Math.random() * 2);
					if (dir == 0)
						handler.getHead2().setDir(right);
					else
						handler.getHead2().setDir(left);
					return;
				}
			}
		} // end of if front is blocked

		// if the front is not blocked
		else {
			// moves forward
			if (leftBlocked() && rightBlocked()) {
				return;
			}

			// left blocked means 50% chance for right or forward
			if (leftBlocked()) {
				// first checks if either direction is better.
				// This is if going forward is better than right
				if (evaluateMove(forward) < evaluateMove(right)) {
					// does nothing, and just moves forward
					handler.getHead2().setDir(forward);
					return;
				} else
				// if the right is better than left
				if (evaluateMove(right) < evaluateMove(forward)) {
					handler.getHead2().setDir(right);
					return;
				}
				// if they are equal, so it just chooses one at random
				else {
					// 50% chance for either side.
					int dir = (int) (Math.random() * 2);
					if (dir == 0)
						handler.getHead2().setDir(right);
					else
						;
					// does nothing, so it moves forward
					return;
				}

			}
			// right blocked means 50% chance for left or forward
			else if (rightBlocked()) {
				// first checks if either direction is better.
				// This is if going left is better than forward
				if (evaluateMove(left) < evaluateMove(forward)) {
					handler.getHead2().setDir(left);
					return;
				} else
				// if the forward is better than left
				if (evaluateMove(forward) < evaluateMove(left)) {
					// does nothing
					// does not change direction
					return;
				}
				// if they are equal, so it just chooses one at random
				else {
					// 50% chance for either side.
					int dir = (int) (Math.random() * 2);
					if (dir == 0)
						handler.getHead2().setDir(left);
					else
						;
					// does nothing, does not change direction
					return;
				}
			}

			// if neither direction is blocked, then it chooses the best move
			if (!leftBlocked() && !rightBlocked()) {
				// if left is better than forward, it chooses left,
				if (evaluateMove(left) < evaluateMove(forward)) {
					handler.getHead2().setDir(left);
					return;
				}
				// if they are eqaul
				else if (evaluateMove(left) == evaluateMove(forward)) {
					// check to see if right is better
					if (evaluateMove(right) < evaluateMove(left)) {
						handler.getHead2().setDir(right);
						return;
					} else {
						// 50% chance for either left or forward
						int dir = (int) (Math.random() * 2);
						if (dir == 0)
							handler.getHead2().setDir(left);
						else {
							handler.getHead2().setDir(forward);

							return;
						}
					}
				}

				// if right is better than forward, it chooses right,
				else if (evaluateMove(right) < evaluateMove(forward)) {
					handler.getHead2().setDir(right);
					return;
				}
				// if they are equal
				else if (evaluateMove(right) == evaluateMove(left)) {
					if (evaluateMove(left) < evaluateMove(right))
						handler.getHead2().setDir(left);
					else {
						// 50% for either side
						int dir = (int) (Math.random() * 2);
						if (dir == 0)
							handler.getHead2().setDir(right);
						else {
							handler.getHead2().setDir(forward);
						}
						return;
					}
				}

				// if right == left, and forward is less than the two, then it
				// will go for either side.
				else if (evaluateMove(right) == evaluateMove(left)) {

					// if forward is worse than either of the two sides
					if (evaluateMove(right) < evaluateMove(forward)) {
						// 50% chance for either side.
						int dir = (int) (Math.random() * 2);
						if (dir == 0)
							handler.getHead2().setDir(left);
						else {
							handler.getHead2().setDir(right);
						}
						return;
					}

				}

				else {
					handler.getHead2().setDir(forward);
				}
			}

		} // end if Front is blocked

		
	}// end move method

	/**
	 * This method evaluates the distance between the possible move and the
	 * food. This way it chooses the best move to the food
	 * 
	 * @param String
	 *            dir, the relative direction of the move, in the class (ie 'n'
	 *            or 'w', for north or west)
	 * @return the distance to the food, so the lower the better.
	 */
	private double evaluateMove(String dir) {
		double out = 0;

		double tempX, tempY;
		// will be writing out the equation over mutiple lines to increase
		// readibility
		switch (dir) {
		// if it is checking north, remove one from y and check the distance.
		case "n":
			// Distance formula
			tempX = Math.pow((foodX - x), 2);
			// y-1 for the test
			tempY = Math.pow(foodY - (y - 1), 2);
			// final forumla
			out = Math.sqrt(tempX + tempY);
			break;
		// if it is checking south, Add one to y and check the distance.
		case "s":
			// Distance formula
			tempX = Math.pow((foodX - x), 2);
			// y+1 for the test
			tempY = Math.pow(foodY - (y + 1), 2);
			// final forumla
			out = Math.sqrt(tempX + tempY);
			break;
		// if it is checking west, remove one from x and check the distance.
		case "w":
			// x-1 for the test
			tempX = Math.pow((foodX - (x - 1)), 2);
			// distance formula
			tempY = Math.pow(foodY - y, 2);
			// final forumla
			out = Math.sqrt(tempX + tempY);
			break;
		// if it is checking east, add one to x and check the distance.
		case "e":
			// X + 1 for test
			tempX = Math.pow((foodX - (x + 1)), 2);
			// distance formula
			tempY = Math.pow(foodY - y, 2);
			// final forumla
			out = Math.sqrt(tempX + tempY);
			break;
		}

		return out;
	}

}
