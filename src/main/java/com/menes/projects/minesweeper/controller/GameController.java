package com.menes.projects.minesweeper.controller;

import com.menes.projects.minesweeper.view.CellGUI;
import com.menes.projects.minesweeper.view.GameDisplay;

import java.awt.event.*;
import java.util.Arrays;

import com.menes.projects.minesweeper.model.*;
import com.menes.projects.minesweeper.view.MineSweeper;

import javax.swing.*;


//revealed

public class GameController {
    GameDisplay gameDisplay;
    boolean running = false;

    public GameController(GameDisplay gameDisplay, boolean running) {
        this.gameDisplay = gameDisplay;
        this.running = running;
        addEvents();
    }

    public void addEvents() {
        for (CellGUI[] cells : this.gameDisplay.getBoardGUI().getCellsGUI()) {
            for (CellGUI cell : cells) {
                cell.addMouseListener(mouse);
            }
        }
    }

    public ActionListener action = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            CellGUI cellGUI;
//                status.timer.start();
//                status.switched = false;
            for (int i = 0; i < MineSweeper.LEVEL; i++) {
                for (int j = 0; j < MineSweeper.LEVEL; j++) {
                    cellGUI = gameDisplay.getBoardGUI().getCellsGUI()[i][j];
                    if (source == cellGUI && gameDisplay.
                            getBoardGUI().
                            getCellsGUI()[i][j].
                            getStatus() == Status.EMPTY && !running
                    ) {
                        gameDisplay.getBoardGUI().init();
                        actionPerformed(e);
                        break;
                    }
                    if (source == cellGUI && clicked(cellGUI)) {
                        revealCell(cellGUI);
                        if (cellGUI.getStatus() == Status.EMPTY) {
                            spread(i, j);
                        }
                        if (!running) {
                            running = true;
                        }
//                        gameOver(button);
//                        speakerMode();
                        return;
                    }

                }
            }
        }

        private void spread(int x, int y) {
            if (gameDisplay.getBoardGUI().getCellsGUI()[x][y].getStatus() != Status.EMPTY)
                return;
            int i, j, limX, limY;
            i = x == 0 ? x : x - 1;
            j = y == 0 ? y : y - 1;
            limX = x == MineSweeper.LEVEL - 1 ? x + 1 : x + 2;
            limY = y == MineSweeper.LEVEL - 1 ? y + 1 : y + 2;
            for (int row = i; row < limX; row++)
                for (int col = j; col < limY; col++) {
                    CellGUI cellGUI = gameDisplay.getBoardGUI().getCellsGUI()[row][col];
                    if (cellGUI.getStatus() == Status.EMPTY) {
//                        if (button.getIcon().toString().equals(flag.toString()))
//                            status.setQuantityFlagsLabel(++status.quantityFlags);
                        revealCell(cellGUI);
                        spread(row, col);
                        cellGUI.setVisible(false);
                    }
                }
        }


        private void revealCell(CellGUI source) {
            CellGUI cellGUI;
            for (int i = 0; i < MineSweeper.LEVEL; i++) {
                for (int j = 0; j < MineSweeper.LEVEL; j++) {
                    cellGUI = gameDisplay.getBoardGUI().getCellsGUI()[i][j];
                    if (cellGUI == source) {
                        gameDisplay.add(cellGUI);
                    }
                }

            }
        }

        private boolean clicked(CellGUI cellGUI) {
            return cellGUI.getStatus() == Status.EMPTY || cellGUI.getStatus() != Status.FLAG;
        }
    };
    public MouseListener mouse = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() instanceof CellGUI) {
                CellGUI cell = (CellGUI) e.getSource();
                cell.setIcon(new ImageIcon("./flags/abstract.png"));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() instanceof CellGUI) {
                CellGUI cell = (CellGUI) e.getSource();
                cell.setIcon(new ImageIcon("./flags/happy.png"));
            }
        }
    };
}
