package com.menes.projects.minesweeper.view;

import com.menes.projects.minesweeper.model.Board;
import com.menes.projects.minesweeper.model.Status;

import java.awt.*;

public class BoardGUI extends Board {
   private CellGUI[][] cellsGUI;
    private int edge = 40; // px


    public void init() {
        for (int i = 0; i < super.getHeight(); i++) {
            for (int j = 0; j < super.getWidth(); j++) {
                CellGUI cellGUI = (cellsGUI[i][j] = new CellGUI(Status.EMPTY));
                cellGUI.setBounds(j * edge, i * edge, edge, edge);
                cellGUI.setPreferredSize(new Dimension(edge, edge));
//                cellGUI.addActionListener(controller.action);
//                cellGUI.addMouseListener(controller.mouse);
//                add(cellGUI);
            }
        }
    }

    public CellGUI[][] getCellsGUI() {
        return cellsGUI;
    }
}