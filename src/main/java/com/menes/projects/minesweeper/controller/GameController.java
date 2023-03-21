package com.menes.projects.minesweeper.controller;

import com.menes.projects.minesweeper.view.CellGUI;
import com.menes.projects.minesweeper.view.GameDisplay;
import com.menes.projects.minesweeper.model.Board;

import java.awt.*;
import java.awt.event.*;

import com.menes.projects.minesweeper.model.*;
import com.menes.projects.minesweeper.view.MineSweeper;


//revealed

public class GameController {
    GameDisplay gameDisplay;
    boolean running = false;
    public ActionListener action = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            CellGUI cellGUI;
//                status.timer.start();
//                status.switched = false;
            for (int i = 0; i < MineSweeper.LEVEL; i++)
                for (int j = 0; j < MineSweeper.LEVEL; j++) {
                    cellGUI = gameDisplay.getBoardGUI().getCellsGUI()[i][j];
                    if (source == cellGUI && gameDisplay.getBoardGUI().getCellsGUI()[i][j].getStatus() == Status.EMPTY && !running) {
                        gameDisplay.getBoardGUI().init();
                        actionPerformed(e);
                        break;
                    }
                    if (source == cellGUI && clicked(cellGUI)) {
                        revealCell(cellGUI);
                        if (cellGUI.getText().equals("  ")) check(i, j);
                        if (!running) {
                            running = true;
                        }
//                        gameOver(button);
//                        speakerMode();
                        break;
                    }

                }
        }

        private void revealCell(CellGUI source) {
            CellGUI cellGUI;
            for (int i = 0; i < MineSweeper.LEVEL; i++) {
                for (int j = 0; j < MineSweeper.LEVEL; j++) {
                    cellGUI = gameDisplay.getBoardGUI().getCellsGUI()[i][j];
                    if (cellGUI == source) {
                        if (cellGUI.getStatus() == Status.EMPTY) {
                            cellGUI.setText("");
                        } else if (cellGUI.getStatus() == Status.MINE) {
                            cellGUI.setIcon(boom);
                        } else {
                            cellGUI.reveal();
                        }
                        cellGUI.setVisible(false);
                        gameDisplay.add(cellGUI);
                        break;
                    }

                }
            }
        }

        private Color setNumberColor(CellGUI value) {
            return new Color(1, 1, 1);
        }

        private boolean clicked(CellGUI cellGUI) {
            return cellGUI.getStatus() == Status.EMPTY || cellGUI.getStatus() != Status.FLAG;
        }
    };
    public MouseListener mouse = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {

        }
    };
}
