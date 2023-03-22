package com.menes.projects.minesweeper.view;

import com.menes.projects.minesweeper.model.Board;

import javax.swing.*;
import java.awt.*;

public class BoardGUI extends JPanel {
    private CellGUI[][] cellsGUI = new CellGUI[MineSweeper.LEVEL][MineSweeper.LEVEL];
    private  Board board = new Board();;
    private int edge = GameDisplay.CELL_SIZE; // px

    public BoardGUI() {
        setPreferredSize(new Dimension(edge*MineSweeper.LEVEL,edge*MineSweeper.LEVEL));
        setLayout(null);
        setBackground(Color.MAGENTA);
        init();
    }


    public void init() {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                CellGUI cellGUI = new CellGUI(board.getBoard()[i][j]);
                cellGUI.setBounds(j * edge, i * edge, edge, edge);
                cellGUI.setPreferredSize(new Dimension(edge, edge));
                cellsGUI[i][j] = cellGUI;
                System.out.print(cellGUI.getStatus());
//                cellGUI.addActionListener(controller.action);
//                cellGUI.addMouseListener(controller.mouse);
                add(cellGUI);
            }
            System.out.println();
        }
    }

    public CellGUI[][] getCellsGUI() {
        return cellsGUI;
    }
}