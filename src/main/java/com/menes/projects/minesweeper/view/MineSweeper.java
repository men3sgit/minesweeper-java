package com.menes.projects.minesweeper.view;

import com.menes.projects.minesweeper.controller.GameController;
import com.menes.projects.minesweeper.model.Board;
import com.menes.projects.minesweeper.model.Level;

import javax.swing.*;
import java.awt.*;

public class MineSweeper extends JFrame {
    public static int LEVEL = Level.NORMAL;
    private GameDisplay game = new GameDisplay();
    private Home home = new Home();
    private GameController controller = new GameController(game,true);
    private boolean gameOver;
    public MineSweeper(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        getContentPane().add(game);
        getContentPane().add(home);
        pack();
        setLocationRelativeTo(this);
        setVisible(true);
    }


}
