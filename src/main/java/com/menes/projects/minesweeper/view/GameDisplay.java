package com.menes.projects.minesweeper.view;

import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JPanel {
    private BoardGUI boardGUI = new BoardGUI();
    private int cellSize = 30;

    public GameDisplay(BoardGUI boardGUI, int cellSize) {
        this.boardGUI = boardGUI;
        this.cellSize = cellSize;
    }

    public GameDisplay() {
    }

    public GameDisplay(BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
    }

    public void initUI(Graphics g) {
        new BoardGUI().init();
    }


    public BoardGUI getBoardGUI() {
        return boardGUI;
    }
}
