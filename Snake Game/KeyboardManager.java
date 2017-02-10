import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener {

	private int keyCode;

	public KeyboardManager() {
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
			GameManager.start = true;
		}
		if (keyCode == KeyEvent.VK_P)
		GameManager.pause = !GameManager.pause;

		if(GameManager.end || GameManager.pause || !GameManager.start)
			return;
		
		// checks for first head
		switch (keyCode) {
		case KeyEvent.VK_UP:
			if(GameManager.head1.getY() == 0){
				//GameManager.head1.setDir("n");
				break;
			}else if (!((GameManager.snake1.get(0).getX() == GameManager.head1.getX())&& (GameManager.snake1.get(0).getY() == GameManager.head1.getY() - 1))) {
				GameManager.head1.setDir("n");
				break;
			}
			break;

		case KeyEvent.VK_LEFT:
			if(GameManager.head1.getX() == 0){
				//GameManager.head1.setDir("w");
				break;
			}
			else if (!((GameManager.snake1.get(0).getX() == GameManager.head1.getX() - 1)
					&& (GameManager.snake1.get(0).getY() == GameManager.head1.getY()))) {
				GameManager.head1.setDir("w");
				break;
			}
			
			break;

		case KeyEvent.VK_DOWN:
			if(GameManager.head1.getY() == (BoardGUI.HEIGHT/BoardGUI.boxSize) - 1){
				//GameManager.head1.setDir("s");
				break;
			}
			else if (!((GameManager.snake1.get(0).getX() == GameManager.head1.getX())
					&& (GameManager.snake1.get(0).getY() == GameManager.head1.getY() + 1))) {
				GameManager.head1.setDir("s");
				break;
			} 
			break;

		case KeyEvent.VK_RIGHT:
			if(GameManager.head1.getX() == (BoardGUI.WIDTH/BoardGUI.boxSize) - 1){
				//GameManager.head1.setDir("e");
				break;
			}
			else if (!((GameManager.snake1.get(0).getX() == GameManager.head1.getX() + 1)
					&& (GameManager.snake1.get(0).getY() == GameManager.head1.getY()))) {
				GameManager.head1.setDir("e");
				break;
			}
			break;
			
			
			
			//second snake
		case KeyEvent.VK_W:
			if(GameManager.getGameMode() == 2)
				break;
			if(GameManager.head2.getY() == 0){
				//GameManager.head2.setDir("n");
				break;
			}
			else if (!((GameManager.snake2.get(0).getX() == GameManager.head2.getX())
					&& (GameManager.snake2.get(0).getY() == GameManager.head2.getY() - 1))) {
				GameManager.head2.setDir("n");
				break;
			}
			break;

		case KeyEvent.VK_A:
			if(GameManager.getGameMode() == 2)
				break;
			
			if(GameManager.head2.getX() == 0){
				//GameManager.head2.setDir("w");
				break;
			}
			else if (!((GameManager.snake2.get(0).getX() == GameManager.head2.getX() - 1)
					&& (GameManager.snake2.get(0).getY() == GameManager.head2.getY()))) {
				GameManager.head2.setDir("w");
				break;
			}
			break;

		case KeyEvent.VK_S:
			if(GameManager.getGameMode() == 2)
				break;
			
			if(GameManager.head2.getY() == (BoardGUI.HEIGHT/BoardGUI.boxSize) - 1){
				//GameManager.head2.setDir("s");
				break;
			} else if (!((GameManager.snake2.get(0).getX() == GameManager.head2.getX())
					&& (GameManager.snake2.get(0).getY() == GameManager.head2.getY() + 1))) {
				GameManager.head2.setDir("s");
				break;
			}
			break;

		case KeyEvent.VK_D:
			if(GameManager.getGameMode() == 2)
				break;
			
			if(GameManager.head2.getX() == (BoardGUI.WIDTH/BoardGUI.boxSize) - 1){
				//GameManager.head2.setDir("e");
				break;
			}
			else if (!((GameManager.snake2.get(0).getX() == GameManager.head2.getX() + 1)
					&& (GameManager.snake2.get(0).getY() == GameManager.head2.getY()))){
				GameManager.head2.setDir("e");
				break;
			}
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