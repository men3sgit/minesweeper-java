package com.menes.projects.minesweeper.view;

import com.menes.projects.minesweeper.model.Cell;
import com.menes.projects.minesweeper.model.Status;

import javax.swing.*;
import java.awt.*;

public class CellGUI extends JButton {
    private Cell cell;

    public CellGUI(Cell cell) {
        this.cell = cell;
        this.decorator();
        this.setFont(new Font("Courier",Font.BOLD,12));
        this.setBackground(new Color(250, 191, 191));
        this.setFocusable(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setIcon(new ImageIcon("./flags/happy.png"));


    }

    private void decorator() {
//        this.setText(statusToString());

        this.setForeground(statusToColor());

    }
    public Color statusToColor(){
        Color color = Color.BLACK;
        switch (getStatus()){
            case FLAG -> color = new Color(0,0,0);
            case _1 -> color = new Color(38, 234, 5);
            case _2 -> color = new Color(21, 64, 246, 161);
            case _3 -> color = new Color(53, 212, 229);
            case _4 -> color = new Color(81, 18, 100);
            case _5 -> color = new Color(183, 71, 71);
            case _6 -> color = new Color(255, 4, 4);
            case _7 -> color = new Color(234, 12, 155);
            case _8 -> color = new Color(68, 66, 66);
            case MINE -> color = new Color(0,0,0);
        }
        return color;
    }

    public Status getStatus() {
        return this.cell.getStatus();
    }

    public String statusToString() {
        String status = " ";
        switch (getStatus()) {
            case FLAG -> status = "F";
            case _1 -> status = "1";
            case _2 -> status = "2";
            case _3 -> status = "3";
            case _4 -> status = "4";
            case _5 -> status = "5";
            case _6 -> status = "6";
            case _7 -> status = "7";
            case _8 -> status = "8";
            case MINE -> status = "@";
        }
        return status;
    }
    public String toString(){
        return this.statusToString();
    }


}