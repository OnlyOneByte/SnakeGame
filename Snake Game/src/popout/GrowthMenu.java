package popout;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.DefaultFormatter;

import manager.Handler;
import ui.MenuBar;

@SuppressWarnings("serial")
public class GrowthMenu extends JFrame {
	@SuppressWarnings("unused")
	private Handler handler;
	public static final int FPS_MIN = 0;
	public static final int FPS_MAX = 30;
	public static int FPS_INIT;
	private JPanel panel = new JPanel();
	private JPanel directionPanel = new JPanel();
	
	int valueMin;
	int valueMax;
	int valueHold1;
	int valueHold2;
	
	JSpinner min, max;
	SpinnerNumberModel enterMin, enterMax;
	JLabel labelMin, labelMax, directions;
	
	public GrowthMenu(Handler handler) {
		
		this.handler = handler;
		
		valueMax = handler.getGrowMax();
		valueMin = handler.getGrowMin();
		
		valueHold1 = valueMin;
		valueHold2 = valueMax;
		
		FPS_INIT = (int) (1000 / handler.getDelayTimer());
		
		this.setLayout(null);
		
		//In initialization code:
	    //Create the radio buttons.
		
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		Font font2 = new Font("SansSerif", Font.PLAIN, 16);
		
		enterMin = new SpinnerNumberModel(valueMin, 0, valueMax, 1);
		enterMax = new SpinnerNumberModel(valueMax, valueMin, 50, 1);
		
		labelMin = new JLabel("Minimum Value:");
		labelMin.setSize(new Dimension(100,100));
		
		labelMax = new JLabel("Maximum Value:");
		
		
		min = new JSpinner(enterMin);
		min.setPreferredSize(new Dimension(50,50));
		min.setFont(font1);
		
		//this is all for the listening
	    JComponent compmin = min.getEditor();
	    JFormattedTextField fieldmin = (JFormattedTextField) compmin.getComponent(0);
	    DefaultFormatter formattermin = (DefaultFormatter) fieldmin.getFormatter();
	    formattermin.setCommitsOnValidEdit(true);
	    min.addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            valueMin = (int) min.getValue();
	            enterMax.setMinimum(valueMin);
	            max.setModel(enterMax);
	            handler.setGrowMin(valueMin);
	            panel.repaint();
	        }
	    });
	    panel.add(labelMin);
		panel.add(min);
		
		
		
		max = new JSpinner(enterMax);
		max.setPreferredSize(new Dimension(50,50));
		max.setFont(font1);
		
		//this is also listening
	    JComponent compmax = max.getEditor();
	    JFormattedTextField fieldmax = (JFormattedTextField) compmax.getComponent(0);
	    DefaultFormatter formattermax = (DefaultFormatter) fieldmax.getFormatter();
	    formattermax.setCommitsOnValidEdit(true);
	    max.addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            valueMax = (int) max.getValue();
	            enterMin.setMaximum(valueMax);
	            min.setModel(enterMin);
	            handler.setGrowMax(valueMax);
	            panel.repaint();
	        }
	    });
	    panel.add(labelMax);
		panel.add(max);
		//end of the spinners, now the directions
		font2 = new Font("sans-serif", Font.ROMAN_BASELINE, 18);
		
		directions = new JLabel();
		directions.setText("<html><body>The growth rate will be a random number<br>"
				+ "between the Minimum and Maximum,<br>"
				+ "Set the Minumum and Maximum equal to each other <br>"
				+ "if no random growth is wanted.</body></html>");
		directions.setFont(font2);
		directionPanel.add(directions);
		directionPanel.setLocation(0, 50);
		directionPanel.setSize(new Dimension(600,100));
		directionPanel.setVisible(true);
		
		
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {		            
		        	MenuBar.isGrowthMenuOpen = false;		        	
		        }
		});	
		
		panel.setLocation(50,200);
		panel.setSize(400, 300);
		panel.setVisible(true);
		
		this.add(directionPanel);
		this.add(panel);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 400);
		this.setResizable(false);
		this.setVisible(true);
	}


}
