package com.menes.projects.minesweeper;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

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
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GameBoard extends JPanel {
	JButton[][] buttons;
	Matrix matrix;
	int row, col, edge, rowOver = 0, colOver = 0, count = 1, y = 0;
	static int risk = 40;
	public static final int WIDTH = 60 * 16, HEIGHT = 60 * 9 + risk;
	boolean startGame = false;
	File file;
	Clip click, firstBoom, finishBoom, putFlag;
	Timer clickSoundReset, appearOneLine, finishBoomReset, putFlagReset, slideUp, signatureRun;
	StatusPane status;
	String empty = " ", _0 = "  ";
	JPanel paneGameOver;
	JLabel gameOverText, signature;
	ImageIcon flag, square, boom, target;

	public GameBoard(Matrix matrix, StatusPane status) {
		this.status = status;
		this.matrix = matrix;
		this.row = matrix.getRow();
		this.col = matrix.getCol();
		this.buttons = new JButton[row][col];
		this.edge = WIDTH / col;

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.black);
		setLayout(null);

		setupImageIcons();
		creatButtons();
		setupSound();
		setupPaneGameOver();

	}

	private void setupImageIcons() {
		String level = status.levelShow.getText();
		if (level.equals("EASY")) {
			flag = new ImageIcon("Icons/putflag.png");
			boom = new ImageIcon("Icons/bomb.png");
			square = new ImageIcon("Icons/square.png");
			target = new ImageIcon("Icons/select.png");

		} else if (level.equals("MEDIUM")) {
			flag = new ImageIcon("Icons/putflag1.png");
			boom = new ImageIcon("Icons/bomb1.png");
			square = new ImageIcon("Icons/square1.png");
			target = new ImageIcon("Icons/select1.png");

		} else {
			flag = new ImageIcon("Icons/putflag2.png");
			boom = new ImageIcon("Icons/bomb2.png");
			square = new ImageIcon("flags/france.png");
			target = new ImageIcon("Icons/select2.png");
		}
	}

	private void creatButtons() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				JButton button = new JButton(empty);
				button.setBounds(j * edge, i * edge, edge, edge);
				button.setPreferredSize(new Dimension(edge, edge));
				button.setBackground(new Color(201, 201, 201));
				button.setIcon(square);
				button.setFocusable(false);
				button.setFont(new Font("", Font.PLAIN, -5));
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
				button.addActionListener(action);
				button.addMouseListener(mouse);
				this.add(button);
				buttons[i][j] = button;

			}
		}
	}

	ActionListener action = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			JButton button;
			status.timer.start();
			status.switched = false;
			for (int i = 0; i < row; i++)
				for (int j = 0; j < col; j++) {
					button = buttons[i][j];
					if (source == button && matrix.getMatrix()[i][j] != Cell.EMPTY && !startGame) {
						matrix.setGame();
						actionPerformed(e);
						break;
					}
					if (source == button && condition(button)) {
						appear(button);
						if (button.getText().equals(_0))
							check(i, j);
						if (!startGame)
							startGame = true;
						gameOver(button);
						speakerMode();
						break;
					}

				}
		}

		private boolean condition(JButton button) {
			return button.getText().equals(empty) && !button.getIcon().toString().equals(flag.toString());
		}

	};

	private void appear(JButton b) {
		Cell value;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				value = matrix.getMatrix()[i][j];
				JButton button = buttons[i][j];
				if (b == button) {
					Color color = setNumberColor(value);
					b.setForeground(color);
					b.setIcon(null);
					JLabel label = replaceButton(b);
					if (value == Cell.EMPTY) {
						b.setText(_0);
						label.setText(_0);
					}

					else if (value == Cell.BOOM) {
						b.setText("");
						b.setIcon(boom);
						label.setText("");
						label.setIcon(boom);

					} else {
						b.setText("" + wordToNumber(value));
						label.setText("" + wordToNumber(value));
					}
					button.setVisible(false);
					add(label);
					return;
				}

			}
		}

//		UIManager.getDefaults().put("Button.disabledText", new ColorUIResource(Color.black));

	}

	private int wordToNumber(Cell value) {
		// TODO Auto-generated method stub
		return switch (value) {
			case ONE -> 1;
			case TWO -> 2;
			case THREE -> 3;
			case FOUR -> 4;
			case FIVE -> 5;
			case SIX -> 6;
			case SEVEN -> 7;
			case EIGHT -> 8;
			default -> Integer.MIN_VALUE;
		};
	}

	private JLabel replaceButton(JButton b) {
		JLabel label = new JLabel();
		label.setForeground(b.getForeground());
		label.setFont(new Font("", Font.BOLD, (int) (edge * 0.9)));
		label.setBounds(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		label.setBackground(new Color(230, 230, 230));
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.gray));
		return label;
	}

	private void check(int x, int y) {
		if (!buttons[x][y].getText().equals(_0))
			return;
		int i, j, limX, limY;
		i = x == 0 ? x : x - 1;
		j = y == 0 ? y : y - 1;
		limX = x == buttons.length - 1 ? x + 1 : x + 2;
		limY = y == buttons[0].length - 1 ? y + 1 : y + 2;
		for (int row = i; row < limX; row++)
			for (int col = j; col < limY; col++) {
				JButton button = buttons[row][col];
				if (button.getText().equals(empty) && matrix.getMatrix()[row][col] != Cell.BOOM) {
					if (button.getIcon().toString().equals(flag.toString())) {
						status.setQuantityFlagsLabel(++status.quantityFlags);
					}
					appear(button);
					button.setBackground(new Color(230, 230, 230));
					button.setIcon(null);
					if (button.getText().equals(_0)) {
						check(row, col);
					}
					button.setVisible(false);
				}
			}
	}

	private void statusOverGame() {
		status.timer.stop();
		status.clip.close();
		status.setColorEndGame();
	}

	private void gameOver(JButton b) {
		if (!b.getText().isEmpty())
			return;
		setAllEnabled();
		firstBoom.start();
		statusOverGame();

		appearOneLine = new Timer(550, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (rowOver == row) {
					appearOneLine.stop();
					status.gameOverSound.start();
					gameOverShowText();
					slideUp.start();
				} else
					for (int j = 0; j < col; j++)
						appear(buttons[rowOver][j]);
				if (rowOver < row) {
					if (rowOver == row - 1) {
						firstBoom.setMicrosecondPosition(0);
						firstBoom.start();
					}
					finishBoom.start();

				}
				rowOver++;

			}

		});
		appearOneLine.start();

	}

	private void gameOverShowText() {
		status.turnOnOverGameText();

	}

	private void speakerMode() {
		if (status.isMuteClickSound())
			click.start();

		else
			click.stop();

	}

	MouseAdapter mouse = new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			Object source = e.getSource();
			JButton button;
			for (int i = 0; i < row; i++)
				for (int j = 0; j < col; j++) {
					button = buttons[i][j];
					if (source == button && button.getText().equals(empty)) {
						if (button.getIcon().toString().equals(square.toString()))
							button.setIcon(target);
						break;
					}
				}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			Object source = e.getSource();
			JButton button;
			for (int i = 0; i < row; i++)
				for (int j = 0; j < col; j++) {
					button = buttons[i][j];
					if (source == button && button.getText().equals(empty)) {
						if (button.getIcon().toString().equals(target.toString()))
							button.setIcon(square);
						break;
					}
				}
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1)//////////////////////////////////////////////////////////////////////////
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < col; j++) {
						JButton button = buttons[i][j];
						if (e.getSource() == button) {
							putFlag.start();
							if (!button.getIcon().toString().equals(flag.toString())) {
								if (status.getQuantityFlags() == 0)
									return;
								status.setQuantityFlagsLabel(--status.quantityFlags);
								button.setIcon(flag);
							}

							else {
								status.setQuantityFlagsLabel(++status.quantityFlags);
								button.setIcon(square);
							}
						}

					}
				}
		}
	};

	private void setupSound() {
		setupClickSound("./Sounds/click.wav");
		setupFirstBoomSound("./Sounds/first.wav");
		setupFinishBoomSound("./Sounds/endgame.wav");
		setupPutFlagSound("./Sounds/putsound.wav");
	}

	void setupClickSound(String source) {
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(source));
			try {
				click = AudioSystem.getClip();
				click.open(audioStream);

			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setResetClickSound();
	}

	private void setResetClickSound() {
		clickSoundReset = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (click.getMicrosecondLength() == click.getMicrosecondPosition())
					click.setMicrosecondPosition(0);
			}
		});
		clickSoundReset.start();
	}

	void setupFirstBoomSound(String source) {
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(source));
			try {
				firstBoom = AudioSystem.getClip();
				firstBoom.open(audioStream);

			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setupFinishBoomSound(String source) {
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(source));
			try {
				finishBoom = AudioSystem.getClip();
				finishBoom.open(audioStream);

			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setResetFinishBoomSound();
	}

	private void setResetFinishBoomSound() {
		finishBoomReset = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (finishBoom.getMicrosecondLength() == finishBoom.getMicrosecondPosition())
					finishBoom.setMicrosecondPosition(0);
			}
		});
		finishBoomReset.start();
	}

	private void setupPutFlagSound(String source) {
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(source));
			try {
				putFlag = AudioSystem.getClip();
				putFlag.open(audioStream);

			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setResetPutFlagSound();
	}

	private void setResetPutFlagSound() {
		putFlagReset = new Timer(5, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (putFlag.getMicrosecondLength() == putFlag.getMicrosecondPosition())
					putFlag.setMicrosecondPosition(0);
			}
		});
		putFlagReset.start();

	}

	public void setAllEnabled() {
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++) {
				JButton button = buttons[i][j];
				button.setEnabled(false);
			}
	}

	Color setNumberColor(Cell value) {
		Color color;
		switch (value) {
		case ONE:
			color = new Color(27, 201, 28);
			break;
		case TWO:
			color = new Color(13, 147, 222, 142);
			break;
		case THREE:
			color = new Color(176, 32, 190);
			break;
		case FOUR:
			color = new Color(187, 192, 31);
			break;
		case FIVE:
			color = new Color(182, 34, 69);
			break;
		case SIX:
			color = new Color(217, 106, 27);
			break;
		case SEVEN:
			color = new Color(96, 29, 199);
			break;
		case EIGHT:
			color = new Color(122, 117, 112);
			break;
		case BOOM:
			color = new Color(0, 70, 10);
			break;
		default:
			color = Color.CYAN;
			break;
		}
		return color;
	}

	void setSignature() {
		paneGameOver.add(gameOverText = status.getGameOverText());
		signature = status.signature;
		signature.setBounds(0, 320, 200, 50);
		signature.setFont(new Font("MV Boli", Font.ITALIC, 10));
		signatureRun = new Timer(20, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (signature.getX() > 800)
					signatureRun.stop();
				signature.setBounds(signature.getX() + 12, 320, 200, 50);
			}

		});
	}

	private void setupPaneGameOver() {

		paneGameOver = new JPanel();
		paneGameOver.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		paneGameOver.setBackground(Color.BLACK);
		paneGameOver.setBounds(0, HEIGHT, WIDTH, HEIGHT);
		paneGameOver.setLayout(null);
		status.setGameOverText();
		setSignature();
		add(paneGameOver);
		setupSlideUp();

	}

	private void setupSlideUp() {

		slideUp = new Timer(30, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				y += 2;
				paneGameOver.setBounds(0, HEIGHT - y, WIDTH, HEIGHT);
				gameOverText.setBounds(125, 100, 900, 200);
				if (y >= HEIGHT) {
					slideUp.stop();
					status.timeShowText.start();
					paneGameOver.add(signature);
					signatureRun.start();
				}
			}
		});
	}

	// "(1)" = default
	// "(2)" = 0
}
