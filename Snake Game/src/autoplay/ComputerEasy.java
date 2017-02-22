package autoplay;

import manager.Handler;

//the most basic of computer players. really easy.
public class ComputerEasy extends ComputerPlayer {

	public ComputerEasy(Handler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This is a nice move method. 
	 * -	If front is blocked. This is most immediate because if it moves forward it will definitely lose.
				Then it checks to see if either side is blocked as well.
					If either side is blocked, it will choose the side that is not blocked
					If both sides are blocked, it moves forward to a forced loss
					If both sides are open, then it chooses a side on chance. There should be a 50% chance for either side.
			If front is not blocked.
				Checks for blocked sides.
					If only one side is blocked
						The Snake will weigh going forward as a higher chance than going to the unblocked side. (75% to 25%)
					If Both sides are blocked
						The snake will move forward
					If neither side is blocked
						The snake will weigh each side equally, but will weigh going forward higher than the two sides. (80% forward, 10% for each side.)
	 */
	@Override
	public void move() {
		// a few basic tenents.
		// moves at random and tries to keep at least 2 blocks away.
		// moves the second snake

		// simple AI does not go for food.
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

		// first the collision detection
		// checks the front.
		if (frontBlocked()) {
			// this checks to see if anything is blocked first
			// if both sides are blocked then this "dumb" ai will just kill
			// itself.
			if (leftBlocked()) {
				// then moves right
				handler.getHead2().setDir(right);
			} else if (rightBlocked()) {
				// moves left
				handler.getHead2().setDir(left);
			}

			else {
				// 50% chance for either side.
				int dir = (int) (Math.random() * 2);
				if (dir == 0)
					handler.getHead2().setDir(right);
				else
					handler.getHead2().setDir(left);
			}
		} // end of if front is blocked

		// if the front is not blocked
		else {
			//moves forward
			if(leftBlocked() && rightBlocked()){
				return;
			}
			
			// left blocked means 50% chance for right or forward
			if (leftBlocked()) {
				// 33% chance for right
				int dir = (int) (Math.random() * 3);
				if (dir == 0)
					handler.getHead2().setDir(right);
				else
					;
				// does noting, as it would continue forward.
			}
			// right blocked means 50% chance for left or forward
			else if (rightBlocked()) {
				// 33% chance for left
				int dir = (int) (Math.random() * 3);
				if (dir == 0)
					handler.getHead2().setDir(left);
				else
					;
				// does nothing, as it would continue forward
			}
			

			// if neither direction is blocked, then 75% chance of going
			// straight, and 12.5% chance of going either direction
			if(!leftBlocked() && ! rightBlocked()){
				int dir = (int) (Math.random() * 8);

				if (dir == 0) {
					handler.getHead2().setDir(right);
				} else if (dir == 1) {
					handler.getHead2().setDir(left);
				} else {
					// continues forward, so no change in direction
				}
			}

		}

	}


}
