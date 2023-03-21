package com.menes.projects.minesweeper.view;

import javax.swing.*;

import com.menes.projects.minesweeper.controller.GameController;
import com.menes.projects.minesweeper.model.Board;
import com.menes.projects.minesweeper.model.Level;
import com.menes.projects.minesweeper.model.Status;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class GameDisplay extends JPanel {
    private Board board;
    private GameController controller;
    private BoardGUI boardGUI = new BoardGUI();
    private int cellSize = 30;

    public GameDisplay(Board board, int cellSize) {
        this.board = board;
        this.cellSize = cellSize;
    }

    public GameDisplay() {
    }

    public GameDisplay(Board board) {
        this.board = board;
    }

    public void initUI(Graphics g) {
        new BoardGUI(controller).init();
    }



    }



}
