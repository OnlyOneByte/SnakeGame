package ui;

import javax.swing.JPanel;

import manager.Handler;
import powerup.PowerUp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

@SuppressWarnings("serial")
public class BoardGUI extends JPanel {

	private Handler handler;

	public BoardGUI(Handler handler) {

		this.handler = handler;

		initializeBoard();

	}

	public void initializeBoard() {
		this.setSize(handler.getBoardWidth(), handler.getBoardHeight());
		this.setLocation(handler.getEdgeBorderSize(), MenuBar.HEIGHT + 10);
		this.setLayout(null);
		this.setDoubleBuffered(true);

		this.repaint();

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setBackground(Color.WHITE);

		g2.fillRect(0, 0, 700, 700);;
		
		// draws the powerups if it is not infinite grow/tron mode
		g2.setColor(Color.RED);
		if (handler.getGameMode() != 1)
			for (PowerUp element : handler.getPowerUps())
				g2.fillOval(element.getX() * handler.getBoxSize(), element.getY() * handler.getBoxSize(), handler.getBoxSize(), handler.getBoxSize());

		
		// snake body one
		g2.setColor(Color.GRAY);
		for (int x = 0; x < handler.getSnake1().size(); x++)
			g2.fillOval(handler.getSnake1().get(x).getX() * handler.getBoxSize(),
					handler.getSnake1().get(x).getY() * handler.getBoxSize(), handler.getBoxSize(),
					handler.getBoxSize());

		
		// snake head one first
		g2.setColor(Color.DARK_GRAY);
		g2.fillOval(handler.getHead1().getX() * handler.getBoxSize(), handler.getHead1().getY() * handler.getBoxSize(),
				handler.getBoxSize(), handler.getBoxSize());
		
		// draws the second snake
		if (handler.getGameMode() != 2) {
			
			
			
			// snake body one
			g2.setColor(Color.CYAN);
			for (int x = 0; x < handler.getSnake2().size(); x++)
				g2.fillOval(handler.getSnake2().get(x).getX() * handler.getBoxSize(),
						handler.getSnake2().get(x).getY() * handler.getBoxSize(), handler.getBoxSize(),
						handler.getBoxSize());
		
			
			// snake head two first
			g2.setColor(Color.BLUE);
			g2.fillOval(handler.getHead2().getX() * handler.getBoxSize(),
					handler.getHead2().getY() * handler.getBoxSize(), handler.getBoxSize(), handler.getBoxSize());
		}

	}
}
