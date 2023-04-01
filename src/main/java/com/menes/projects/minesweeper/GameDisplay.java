package com.menes.projects.minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameDisplay extends JPanel {
	GameBoard game;
	StatusPane status;
	JPanel west, east, south;
	JLabel label1, label2;
	Timer timer;
	int distance = 0;
	int row, col;

	public GameDisplay() {
		this.status = new StatusPane();
		col = status.getCol();
		row = status.getRow();
		this.game = new GameBoard(new Matrix(status.splitBoom(status.levelShow.getText()), row, col), status);
		setLayout(new BorderLayout());
		add(this.game, BorderLayout.CENTER);
		add(this.status, BorderLayout.NORTH);
		hide();
		effect();

	}

	public void hide() {
		add(west = new JPanel(), BorderLayout.WEST);
		add(east = new JPanel(), BorderLayout.EAST);
		add(south = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.gray);
				g.drawLine(-10, 12, 1200, 12);
			}
		}, BorderLayout.SOUTH);
		west.setBackground(Color.black);
		east.setBackground(Color.black);
		south.setBackground(Color.black);
		west.setPreferredSize(new Dimension(70, 0));
		east.setPreferredSize(new Dimension(70, 0));
		south.setPreferredSize(new Dimension(0, 45));
	}

//	void newLevel() {
//		game.setVisible(false);
//		col = status.getCol();
//		row = status.getRow();
//		game = new GamePane(new Matrix(status.splitBoom(status.levelShow.getText()), row, col), status);///////////////////////////////////////////////////// 2
//		add(this.game,BorderLayout.CENTER);
//		
//	}
	private void effect() {

		label1 = new JLabel("Sorry, if you click too fast, our sound effects will not respond!");
		label2 = new JLabel("Sorry, if you click too fast, our sound effects will not respond!");
		label1.setBounds(0, 15, 200, 30);
		label1.setForeground(Color.cyan);
		label1.setFont(new Font("MV Boli", Font.BOLD, 12));

		label2.setBounds(900, 15, 200, 30);
		label2.setForeground(Color.cyan);
		label2.setFont(new Font("MV Boli", Font.BOLD, 12));

		south.add(label1);
		south.add(label2);
		south.setLayout(null);
		timer = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				if(status.switchLevel) {
//					newLevel();
//					status.switchLevel = false;///////////////////////////////////////////////////////////////////////////////////////////////////// 1
//					System.out.println();
//				}
				if (distance >= 1100)
					distance = 0;
				label1.setBounds(0 - distance, 15, 700, 30);
				label2.setBounds(1100 - distance, 15, 700, 30);
				distance += 2;
				if (distance % 90 == 0) {
					label1.setForeground(
							new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
					label2.setForeground(
							new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
				}
			}

		});
		timer.start();
	}

}
