package manager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardManager implements KeyListener {

	private int keyCode;
	
	private Handler handler;

	public KeyboardManager(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		keyCode = e.getKeyCode();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_SPACE){
			handler.setStart(true);
		}
		if (keyCode == KeyEvent.VK_P)
		handler.setPause(!handler.isPause());

		if(handler.isEnd() || handler.isPause() || !handler.isStart())
			return;
		
		// checks for first head
		switch (keyCode) {
		case KeyEvent.VK_UP:
			if(handler.getHead1().getY() == 0){
				handler.getHead1().setDir("n");
				break;
			} else if (!((handler.getSnake1().get(0).getX() == handler.getHead1().getX())
					&& (handler.getSnake1().get(0).getY() == handler.getHead1().getY() - 1))) {
				handler.getHead1().setDir("n");
				break;
			}
			break;

		case KeyEvent.VK_LEFT:
			if(handler.getHead1().getX() == 0){
				handler.getHead1().setDir("w");
				break;
			}
			else if (!((handler.getSnake1().get(0).getX() == handler.getHead1().getX() - 1)
					&& (handler.getSnake1().get(0).getY() == handler.getHead1().getY()))) {
				handler.getHead1().setDir("w");
				break;
			}
			
			break;

		case KeyEvent.VK_DOWN:
			if(handler.getHead1().getY() == (handler.getBoard().getHeight()/handler.getBoard().getBoxSize()) - 1){
				handler.getHead1().setDir("s");
				break;
			}
			else if (!((handler.getSnake1().get(0).getX() == handler.getHead1().getX())
					&& (handler.getSnake1().get(0).getY() == handler.getHead1().getY() + 1))) {
				handler.getHead1().setDir("s");
				break;
			} 
			break;

		case KeyEvent.VK_RIGHT:
			if(handler.getHead1().getX() == (handler.getBoard().getHeight()/handler.getBoard().getBoxSize()) - 1){
				handler.getHead1().setDir("e");
				break;
			}
			else if (!((handler.getSnake1().get(0).getX() == handler.getHead1().getX() + 1)
					&& (handler.getSnake1().get(0).getY() == handler.getHead1().getY()))) {
				handler.getHead1().setDir("e");
				break;
			}
			break;
			
			
			
			//second snake
		case KeyEvent.VK_W:
			if(handler.getGameMode() == 2)
				break;
			if(handler.getHead2().getY() == 0){
				handler.getHead2().setDir("n");
				break;
			}
			else if (!((handler.getSnake2().get(0).getX() == handler.getHead2().getX())
					&& (handler.getSnake2().get(0).getY() == handler.getHead2().getY() - 1))) {
				handler.getHead2().setDir("n");
				break;
			}
			break;

		case KeyEvent.VK_A:
			if(handler.getGameMode() == 2)
				break;
			
			if(handler.getHead2().getX() == 0){
				handler.getHead2().setDir("w");
				break;
			}
			else if (!((handler.getSnake2().get(0).getX() == handler.getHead2().getX() - 1)
					&& (handler.getSnake2().get(0).getY() == handler.getHead2().getY()))) {
				handler.getHead2().setDir("w");
				break;
			}
			break;

		case KeyEvent.VK_S:
			if(handler.getGameMode() == 2)
				break;
			
			if(handler.getHead2().getY() == (handler.getBoard().getHeight()/handler.getBoard().getBoxSize()) - 1){
				handler.getHead2().setDir("s");
				break;
			} else if (!((handler.getSnake2().get(0).getX() == handler.getHead2().getX())
					&& (handler.getSnake2().get(0).getY() == handler.getHead2().getY() + 1))) {
				handler.getHead2().setDir("s");
				break;
			}
			break;

		case KeyEvent.VK_D:
			if(handler.getGameMode() == 2)
				break;
			
			if(handler.getHead2().getX() == (handler.getBoard().getHeight()/handler.getBoard().getBoxSize()) - 1){
				handler.getHead2().setDir("e");
				break;
			}
			else if (!((handler.getSnake2().get(0).getX() == handler.getHead2().getX() + 1)
					&& (handler.getSnake2().get(0).getY() == handler.getHead2().getY()))){
				handler.getHead2().setDir("e");
				break;
			}
			break;

		case KeyEvent.VK_N:
			handler.setReset(true);
			break;
		default:break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public int keyCode() {
		return keyCode;
	}

}