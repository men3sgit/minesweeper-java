package com.menes.projects.minesweeper.view;

import com.menes.projects.minesweeper.model.Board;

import javax.swing.*;
import java.awt.*;

public class BoardGUI extends JPanel {
    private  Board board = new Board();;
    private CellGUI[][] cellsGUI = new CellGUI[board.getWidth()][board.getWidth()];
    private int edge = GameDisplay.CELL_SIZE; // px

    public BoardGUI() {
        setPreferredSize(new Dimension(edge*board.getHeight(),edge*board.getWidth()));
        setLayout(null);
        setBackground(Color.MAGENTA);
        init();
    }


    public void init() {
        CellGUI cellGUI;
        RevealCell label;
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
              cellGUI = new CellGUI(board.getBoard()[i][j]);
                cellGUI.setBounds(j * edge, i * edge, edge, edge);
                cellGUI.setPreferredSize(new Dimension(edge, edge));

                cellsGUI[i][j] = cellGUI;
                label = new RevealCell(cellGUI);
//                cellGUI.addActionListener(controller.action);
//                cellGUI.addMouseListener(controller.mouse);
                add(label);
                add(cellGUI);
            }
        }
    }

    public CellGUI[][] getCellsGUI() {
        return cellsGUI;
    }
}