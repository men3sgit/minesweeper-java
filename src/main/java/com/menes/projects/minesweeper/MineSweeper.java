package com.menes.projects.minesweeper;

import javax.swing.JFrame;

public class MineSweeper extends JFrame{
	
	MenuBar menuBar;
	public MineSweeper() {
		super("MineSweeper");
		
		getContentPane().add(new GameDisplay());
//		setJMenuBar(menuBar = new MenuBar());
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	/*
	 *  Audio, effect sound cannot reply with click speed 
	 */
	public static void main(String[] args) {
		new MineSweeper();
	}
}
