package autoplay;

import manager.Handler;

public abstract class ComputerPlayer {
	
	protected Handler handler;
	
	public ComputerPlayer(Handler handler){
		this.handler = handler;
	}
	
	public abstract void move();
	
}
