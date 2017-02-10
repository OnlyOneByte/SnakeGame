package popout;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import manager.Handler;
import ui.MenuBar;

@SuppressWarnings("serial")
public class SpeedMenu extends JFrame implements ChangeListener {
	public static final int FPS_MIN = 0;
	public static final int FPS_MAX = 30;
	public int fpsInit;
	public  int fpsHold = fpsInit;
	
	private JPanel panel = new JPanel();
	private JPanel directionPanel = new JPanel();
	private JPanel fps = new JPanel();
	
	private JLabel fpsShow = new JLabel();
	
	private Handler handler;
	
	public SpeedMenu(Handler handler) {
		this.handler = handler;
		
		this.setLayout(null);
		
		fpsInit = (int) (1000 / handler.getDelayTimer());
		
		// Create the label.
		Font font = new Font("sans-serif", Font.PLAIN, 30);
		JLabel sliderLabel = new JLabel("Frames Per Second");
		sliderLabel.setFont(font);
		sliderLabel.setVisible(true);
		panel.add(sliderLabel);
		
		panel.setPreferredSize(new Dimension(150,90));
		
		panel.setLocation(60, 60);
		panel.setVisible(true);
		this.add(panel);

		// Create the slider.
		JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, fpsInit);

		framesPerSecond.addChangeListener(this);

		framesPerSecond.setMajorTickSpacing(2);
		framesPerSecond.setMinorTickSpacing(1);
		framesPerSecond.setPaintTicks(true);
		framesPerSecond.setPaintLabels(true);
		framesPerSecond.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		font = new Font("Serif", Font.PLAIN, 16);
		framesPerSecond.setFont(font);
		
		framesPerSecond.setLocation(85, 150);
		framesPerSecond.setSize(400, 200);

		this.add(framesPerSecond);
		
		
		JLabel directions = new JLabel();
		
		//directions menu
		font = new Font("sans-serif", Font.ROMAN_BASELINE, 18);
		
		directions.setText("<html><body>The Game Speed slider is in Frames Per Second.<br>"
				+ "Speed is 1000 / FPS to get the delay between each frame<br>"
				+ "in Milliseconds. Your score counter will also have the speed,<br>"
				+ "to ensure difficulty of game is displayed with the score.</body></html>");
		directions.setFont(font);
		directionPanel.add(directions);
		directionPanel.setLocation(0, 35);
		directionPanel.setSize(new Dimension(600,200));
		directionPanel.setVisible(true);

		this.add(directionPanel);
		
		
		//display the fps panel
		font = new Font("sans-serif", Font.ROMAN_BASELINE, 16);
		fpsShow.setText("FPS: " + fpsHold);
		fpsShow.setFont(font);
		fps.add(fpsShow);
		fps.setSize(new Dimension(100, 100));
		fps.setLocation(225, 300);
		fpsShow.setText("FPS: " + fpsHold);
		
		fpsShow.setVisible(true);
		fps.repaint();
		
		this.add(fps);
				
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {		            
		        	MenuBar.isSpeedMenuOpen = false;		        	
		        }
		});
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 400);
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		int fps = (int) source.getValue();
		if(!(fps == 0)){
			handler.setDelayTimer((long) (1000.0 / fps));
			fpsHold = fps;
			fpsShow.setText("FPS: " + fpsHold);
		}
	}

}
