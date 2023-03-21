package com.menes.projects.minesweeper.view;

import com.menes.projects.minesweeper.controller.GameController;
import com.menes.projects.minesweeper.model.Board;
import com.menes.projects.minesweeper.model.Level;

import javax.swing.*;

public class MineSweeper extends JFrame {
    public static int LEVEL = Level.NORMAL;
    private GameDisplay game = new GameDisplay();
    private GameController controller = new GameController(game,true);
    private boolean gameOver;
    public MineSweeper(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(game);
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }


}
