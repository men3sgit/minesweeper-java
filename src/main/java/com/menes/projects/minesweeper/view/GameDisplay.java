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
        setPreferredSize(new Dimension(1000, 600));
        setLayout(new BorderLayout());
        this.add(new JTextField(),BorderLayout.NORTH);
        this.add(boardGUI, BorderLayout.CENTER);

    }


    public BoardGUI getBoardGUI() {
        return boardGUI;
    }
}
