package com.menes.projects.minesweeper.view;

import javax.swing.*;
import com.menes.projects.minesweeper.model.Board;
import com.menes.projects.minesweeper.model.Level;

import java.awt.*;

public class GameDisplay extends JPanel{
    private Board board;
    private int cellSize = 30;
    public GameDisplay(Board board, int cellSize){
        this.board = board;
        this.cellSize = cellSize;
    }
    public GameDisplay(){}

    public GameDisplay(Board board){
        this.board = board;
    }
    public void initUI(Graphics g){

    }

}
