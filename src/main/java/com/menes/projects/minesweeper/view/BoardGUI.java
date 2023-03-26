package com.menes.projects.minesweeper.view;

import com.menes.projects.minesweeper.model.Board;

import javax.swing.*;
import java.awt.*;

public class BoardGUI extends JPanel {
    private Board board;
    private CellGUI[][] cellsGUI;
    private int edge = GameDisplay.CELL_SIZE; // px

    public BoardGUI() {
        setLayout(null);
        setBackground(Color.MAGENTA);
        init();
    }


    public void init() {
        this.board = new Board();
        cellsGUI = new CellGUI[board.getHeight()][board.getWidth()];
        setPreferredSize(new Dimension(edge * board.getHeight(), edge * board.getWidth()));

        CellGUI cellGUI;
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                cellGUI = new CellGUI(board.getBoard()[i][j]);
                cellGUI.setBounds(j * edge, i * edge, edge, edge);
                cellGUI.setPreferredSize(new Dimension(edge, edge));
                cellsGUI[i][j] = cellGUI;
//                cellGUI.addActionListener(controller.action);
//                cellGUI.addMouseListener(controller.mouse);
                add(cellGUI);
                add(new RevealCell(cellGUI));
            }
        }
    }

    public CellGUI[][] getCellsGUI() {
        return cellsGUI;
    }
    public Board getBoard(){return this.board;}

}