package com.menes.projects.minesweeper;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{
	JMenu help;
	JMenuItem howToPlay;
	public MenuBar() {
		help = new JMenu("Help");
		howToPlay = new JMenuItem("How to play");
		help.add(howToPlay);
		add(help);
	}
}
