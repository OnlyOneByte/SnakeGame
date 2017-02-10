package powerup;

import manager.Handler;

public class Food extends PowerUp{
	public Food(Handler handler){
		super(handler);	
		x = 0; y = 0;
		
		spawn();
	}

	@Override
	public void sideEffect(int snake) {
		
		if(snake == 1){
			handler.setGrowing1(true);
			handler.setGrowCount1(0);
			handler.setGrowThresh1((int) (Math.random() * handler.getGrowMax()) + handler.getGrowMin());; //creates a random amount to grow for.
		} else
		if(snake == 2){
			handler.setGrowing2(true);
			handler.setGrowCount2(0);
			handler.setGrowThresh2((int) (Math.random() * handler.getGrowMax()) + handler.getGrowMin());; //creates a random amount to grow for.
		}
		
		
		this.spawn();
	}
	


	
}
