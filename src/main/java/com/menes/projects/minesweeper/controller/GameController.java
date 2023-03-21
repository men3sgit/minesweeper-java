package com.menes.projects.minesweeper.controller;

import java.awt.event.*;

public class GameController {
    public ActionListener action = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            CellGUI cellGUI;
//                status.timer.start();
//                status.switched = false;
            for (int i = 0; i < board.getHeight(); i++)
                for (int j = 0; j < board.getWidth(); j++) {
                    cellGUI = boardGUI.cellsGUI[i][j];
                    if (source == cellGUI && matrix.getMatrix()[i][j] != Cell.EMPTY && !startGame) {
                        matrix.setGame();
                        actionPerformed(e);
                        break;
                    }
                    if (source == cellGUI && condition(button)) {
//                            appear(cellGUI);
                        if (cellGUI.getText().equals("  "))
                            check(i, j);
                        if (!startGame)
                            startGame = true;
                        gameOver(button);
                        speakerMode();
                        break;
                    }

                }
        }
        };
    public MouseListener mouse = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e){

        }
    };
}
