package ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

import manager.Handler;
import popout.GrowthMenu;
import popout.SpeedMenu;

@SuppressWarnings("serial")
public class MenuBar extends JPanel implements ActionListener {
	JMenuBar menubar = new JMenuBar();
	private static int height = 75;
	public static boolean isSpeedMenuOpen = false;
	public static boolean isGameBoardSizeMenuOpen = false; //so large ayyy
	public static boolean isGrowthMenuOpen = false;
	
	public static final int HEIGHT = height;
	
	private Handler handler;

	public MenuBar(Handler handler) {
		this.handler = handler;
		
		
		
		// Where the GUI is created:
		JMenu GameMenu;
		JMenu OptionMenu;
		JMenu GameModeMenu;
		
		Font f = new Font("sans-serif", Font.PLAIN, 25);
		UIManager.put("Menu.font", f);
		f = new Font("sans-serif", Font.PLAIN, 18);
		UIManager.put("MenuItem.font", f);

		// Build the first GameMenu.
		GameMenu = new JMenu("Game");
		GameMenu.setMnemonic(KeyEvent.VK_F);
		GameMenu.getAccessibleContext().setAccessibleDescription("Game menu");

		// first menu slots, game reset, pause, and exit
		// JGameMenus show the menu items
		JMenuItem menuItem = new JMenuItem("New", new ImageIcon("images/new.gif"));
		menuItem.setMnemonic(KeyEvent.VK_N); // opens menu if this is clicked
		menuItem.addActionListener(this); // adds the action listener
		GameMenu.add(menuItem);

		GameMenu.addSeparator();

		// pause menu item
		menuItem = new JMenuItem("Pause/Unpause", new ImageIcon("images/pause.gif"));
		menuItem.setMnemonic(KeyEvent.VK_P); // mnemonic = p
		menuItem.addActionListener(this); // action listener
		GameMenu.add(menuItem);

		// exit menu iem
		menuItem = new JMenuItem("Exit", new ImageIcon("images/exit.gif"));
		menuItem.setMnemonic(KeyEvent.VK_E);
		menuItem.addActionListener(this); // action listener
		GameMenu.add(menuItem);

		// game menu size location and set visiable
		GameMenu.setPreferredSize(new Dimension(150, 60));
		GameMenu.setVisible(true);

		// second set, game options
		// game speed, game board size, game mode
		// Build the options Menu.
		OptionMenu = new JMenu("Options");
		OptionMenu.setMnemonic(KeyEvent.VK_K);
		OptionMenu.getAccessibleContext().setAccessibleDescription("Options");

		// gameboard size
		menuItem = new JMenuItem("Board Size", new ImageIcon("images/BoardSize.gif"));
		menuItem.setMnemonic(KeyEvent.VK_B);
		menuItem.addActionListener(this);
		OptionMenu.add(menuItem);
		// game speed
		menuItem = new JMenuItem("Game Speed", new ImageIcon("images/GameSpeed.gif"));
		menuItem.setMnemonic(KeyEvent.VK_G);
		menuItem.addActionListener(this);
		OptionMenu.add(menuItem);
		
		menuItem = new JMenuItem("Growth", new ImageIcon("images/Growth.gif"));
		menuItem.addActionListener(this);
		OptionMenu.add(menuItem);
		
		menuItem = new JMenuItem("Ai Toggle", new ImageIcon("images/AiToggle.gif"));
		menuItem.addActionListener(this);
		OptionMenu.add(menuItem);
		
		menuItem = new JMenuItem("Ai Diff. Toggle", new ImageIcon("images/AiDiffToggle.gif"));
		menuItem.addActionListener(this);
		OptionMenu.add(menuItem);

		OptionMenu.setPreferredSize(new Dimension(150, 60));
		OptionMenu.setVisible(true);
		
		
		
		//creates the epic gamemode menu
		GameModeMenu = new JMenu("Game Mode");
		
		//gamemode 0 (2 player normal)
		menuItem = new JMenuItem("Two Player (Normal)", new ImageIcon("images/2pN.gif"));
		menuItem.addActionListener(this);
		GameModeMenu.add(menuItem);
		
		//gamemode 1 (2 player infinite grow)
		menuItem = new JMenuItem("Two Player (Tron)", new ImageIcon("images/2pI.gif"));
		menuItem.addActionListener(this);
		GameModeMenu.add(menuItem);
		
		//gamemode 2 1 player normal
		menuItem = new JMenuItem("One Player", new ImageIcon("images/1pN.gif"));;
		menuItem.addActionListener(this);
		GameModeMenu.add(menuItem);
		
		GameModeMenu.setPreferredSize(new Dimension(160, 60));
		GameModeMenu.setVisible(true);
		
		// adds to the jpanel
		menubar.add(GameMenu);
		menubar.add(OptionMenu);
		menubar.add(GameModeMenu);
		menubar.setVisible(true);
		this.add(menubar);
		
		this.setBounds(0, 6, 515, height);
		
		this.setBackground(Color.DARK_GRAY);

		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		//game meny
		case "Exit":
			System.exit(0);
			break;
		case "New":
			handler.setReset(true);
			break;
			
		//options menu
		case "Game Speed":
			if(!isSpeedMenuOpen){
				isSpeedMenuOpen = true;
				@SuppressWarnings("unused")
				SpeedMenu speed = new SpeedMenu(handler);
			}
			break;
		case "Board Size":
			if(!isSpeedMenuOpen){
				isSpeedMenuOpen = true;
				@SuppressWarnings("unused")
				SpeedMenu speed = new SpeedMenu(handler);
			}
			break;
		case "Growth":
			if(!isGrowthMenuOpen){
				isGrowthMenuOpen = true;
				@SuppressWarnings("unused")
				GrowthMenu growth = new GrowthMenu(handler);
			}
			break;
		case "Pause/Unpause":
			handler.setPause(!handler.isPause());;		
			break;
		case "Ai Toggle":
			handler.setAiON(!handler.isAiON());
			break;
		case "Ai Diff. Toggle":
			handler.setAIHard(!handler.isAIHard());
			break;
			
		
		//gamemode menu
		case "Two Player (Normal)": 
			handler.setGameModeHold(0);
			break;
		
		case "Two Player (Tron)":
			handler.setGameModeHold(1);
			break;
		
		case "One Player":
			handler.setGameModeHold(2);
			break;

		
		default:
			break;
		}
	}
	
	
	public int getHeight(){
		return height;
	}

}
