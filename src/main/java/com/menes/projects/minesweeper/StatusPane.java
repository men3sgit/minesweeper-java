package com.menes.projects.minesweeper;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StatusPane extends JPanel {
	public static final String levelOfGame = "EASY";
	Timer timer;
	Timer timeShowText;
	 int second = 1;
	 JLabel time;
	 JLabel watch;
	 JLabel controller;
	JLabel levelShow;
	 JLabel flag;
	 JLabel quantityFlagsLabel;
	 JLabel signature = new JLabel("Created by MenesSE");
	 JLabel gameOverText = new JLabel();;
	 JButton speaker, mute;
	 File file;
	 AudioInputStream audioStream;
	Clip clip;
	Clip gameOverSound;
	 ActionListener action;
	 boolean isMuteClickSound = true, end = false;
	 int hideShow = 1, quantityFlags = 0;
	 Color color = Color.GREEN;
	 double rare;
	public static final int EASY = 12;
	public static final int MEDIUM = 20;
	public static final int HARD = 30;
	 boolean switchLevel = false;
	boolean switched = true;

	public StatusPane() {
		setPreferredSize(new Dimension(0, 90));
		setBackground(Color.BLACK);
		setLayout(null);
		action = getAction();
		setItems();
	}

	private ActionListener getAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == speaker) {
					speaker.setVisible(false);
					mute.setVisible(true);
					muteSpeaker(clip);
					isMuteClickSound = false;
					if (end)
						gameOverSound.stop();
				} else {
					mute.setVisible(false);
					speaker.setVisible(true);
					turnOnSpeaker(clip);
					isMuteClickSound = true;
					if (end)
						gameOverSound.start();
				}
			}
		};

	}

	void muteSpeaker(Clip c) {
		c.stop();
	}

	void turnOnSpeaker(Clip c) {
		c.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	private void setItems() {
		setWatch();
		setLevelShowText();
		setFlag();
		setAudio();

	}

	private void setWatch() {
		watch = new JLabel(new ImageIcon("./Icons/alarm.png"));
		watch.setBounds(40, 20, 40, 40);
		time = new JLabel("000");
		time.setBounds(90, 20, 150, 40);
		time.setForeground(color);
		time.setFont(new Font("Ink Free", Font.PLAIN, 50));
		add(watch);
		add(time);
		setTimer();
	}

	void setTimer() {
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				time.setText(second < 10 ? "00" + second : second < 100 ? "0" + second : "" + second);
				++second;
			}

		});

	}

	private void setControllerLabel() {
		controller = new JLabel(new ImageIcon("./Icons/controller.png"));
		controller.setBounds(420, 10, 50, 50);
		add(controller);
	}

	private MouseAdapter switchLevel() {
		MouseAdapter mouse = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				String level = levelShow.getText();
//				int booms = splitBoom(level);
//				setQuantityFlagsLabel(booms);
//				if (switched) {
//					selectLevel();
//					switchLevel = true;
//				}
			}
		};
		return mouse;
	}

	private void setLevelShowText() {
		setControllerLabel();
//		String levelText = level == MineSweeper.EASY ? "EASY" : level == MineSweeper.MEDIUM ? "MEDIUM" : "HARD";
		levelShow = new JLabel(levelOfGame);
		levelShow.setFont(new Font("Ink Free", Font.BOLD, 50));
		levelShow.setForeground(color);
		levelShow.setHorizontalAlignment(JLabel.CENTER);
		levelShow.setBounds(470, 15, 250, 50);// 370
		levelShow.setCursor(new Cursor(Cursor.HAND_CURSOR));
		levelShow.addMouseListener(switchLevel());
		add(levelShow);

		setRare();

	}

	void setRare() {
		String level = levelShow.getText();
		rare = level.equals("HARD") ? 8 : level.equals("MEDIUM") ? 3 : 1;
	}

	private void setFlag() {
		flag = new JLabel(new ImageIcon("./Icons/vietnam.png"));
		flag.setBounds(820, 10, 50, 50);
		add(flag);
		quantityFlags = splitBoom(this.levelShow.getText());
		quantityFlagsLabel = new JLabel(quantityFlags < 10 ? "0" + quantityFlags : quantityFlags + "");
		quantityFlagsLabel.setBounds(880, 10, 90, 50);
		quantityFlagsLabel.setForeground(color);
		quantityFlagsLabel.setFont(new Font("Ink Free", Font.PLAIN, 45));
		add(quantityFlagsLabel);

	}

	void setColorEndGame() {
		color = Color.RED;
		time.setForeground(color);
		quantityFlagsLabel.setForeground(color);
		levelShow.setForeground(color);
	}

	private void setAudio() {
		setSounds();
		setSpeaker();
		setMute();
		setupGameOverSound();

	}

	private void setSounds() {

		file = new File("Sounds/minesweeper.wav");
		try {
			audioStream = AudioSystem.getAudioInputStream(file);
			try {
				clip = AudioSystem.getClip();
				clip.open(audioStream);

			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	private void setSpeaker() {
		speaker = new JButton(new ImageIcon("./Icons/speaker.png"));
		speaker.setBounds(1000, 20, 40, 40);
		speaker.setBackground(Color.black);
		speaker.setBorder(BorderFactory.createEmptyBorder());
		speaker.setCursor(new Cursor(Cursor.HAND_CURSOR));
		speaker.addActionListener(action);
		add(speaker);
	}

	private void setMute() {
		mute = new JButton(new ImageIcon("./Icons/mute.png"));
		mute.setBounds(1000, 20, 40, 40);
		mute.setBackground(Color.black);
		mute.setBorder(BorderFactory.createEmptyBorder());
		mute.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mute.addActionListener(action);
		add(mute);
	}

	private void setupGameOverSound() {
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("./Sounds/EDM.wav"));
			try {
				gameOverSound = AudioSystem.getClip();
				gameOverSound.open(audioStream);

			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}

	}

	void setGameOverText() {
		gameOverText.setText("GAME OVER");
		gameOverText.setFont(new Font("Ink Free", Font.BOLD, 120));
		gameOverText.setForeground(Color.RED);
		gameOverText.setVisible(true);
	}

//==========================================Interaction GamePane======================================================================

	public void turnOnOverGameText() {

		timeShowText = new Timer(600, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Random rd = new Random();
				signature.setForeground(new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255)));
				gameOverText.setVisible(hideShow % 2 != 1);
//				if (hideShow % 2 == 0 && hideShow % 4 != 0)
//					gameOverText.setForeground(Color.RED);
//				else if (hideShow % 4 == 0) {
//					gameOverText.setForeground(Color.MAGENTA);
//				}
				hideShow++;

				// private
				if (gameOverSound.getMicrosecondPosition() == gameOverSound.getMicrosecondLength()) {
					gameOverSound.setMicrosecondPosition(0);
					gameOverSound.start();
				}

			}

		});
		end = true;

	}

	public boolean isMuteClickSound() {
		return isMuteClickSound;
	}

	public void setMuteClickSound(boolean isMuteClickSound) {
		this.isMuteClickSound = isMuteClickSound;
	}

	public int getQuantityFlags() {
		return quantityFlags;
	}

	public void setQuantityFlags(int quantityFlags) {
		this.quantityFlags = quantityFlags;
	}

	public void setQuantityFlagsLabel(int quantity) {
		setQuantityFlags(quantity);
		quantityFlagsLabel.setText("" + quantity);
	}

//==========================================Interaction GamePane=============================================================================	

	public JLabel getGameOverText() {
		return this.gameOverText;
	}

	int splitBoom(String level) {
		if (level.equals("EASY"))
			return (int) (EASY * rare);
		else if (level.equals("MEDIUM"))
			return (int) (MEDIUM * rare);
		else
			return (int) (HARD * rare);

	}

	int getCol() {
		String level = levelShow.getText();
		return level.equals("HARD") ? HARD : level.equals("MEDIUM") ? MEDIUM : EASY;
	}

	int getRow() {
		return (int) (getCol() * 0.6);
	}

//	void selectLevel() {
//		String level = levelShow.getText();
//		if (level.equals("HARD")) {
//			levelShow.setText("EASY");
//		} else if (level.equals("EASY")) {
//			levelShow.setText("MEDIUM");
//		} else
//			levelShow.setText("HARD");
//	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.gray);
		g.drawLine(-10, 70, 1200, 70);
	}
}
