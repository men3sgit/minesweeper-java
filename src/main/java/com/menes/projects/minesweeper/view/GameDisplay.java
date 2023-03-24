package com.menes.projects.minesweeper.view;

import com.menes.projects.minesweeper.model.Board;

import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JPanel {
    public static int CELL_SIZE = 40;

    private BoardGUI boardGUI = new BoardGUI();

    public GameDisplay(int cellSize) {

        initGUI();
    }

    public GameDisplay() {
        setBackground(Color.MAGENTA);
        initGUI();
    }

    public GameDisplay(BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
    }

    public void initGUI() {
        setPreferredSize(new Dimension(1080, 700));
        setLayout(new BorderLayout());
        JPanel westPanel = new JPanel();
        JPanel northPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(64,0));
        northPanel.setPreferredSize(new Dimension(0,64));
        this.add(boardGUI, BorderLayout.CENTER);
        this.add(westPanel,BorderLayout.WEST);
        this.add(northPanel,BorderLayout.NORTH);

    }


    public BoardGUI getBoardGUI() {
        return boardGUI;
    }
}
