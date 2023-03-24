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
        this.setBackground(new Color(201, 201, 201));
        this.setFocusable(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setEnabled(false);

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

    public void reveal() {
        Icon icon = new ImageIcon("");
        switch (cell.getStatus()) {
            case FLAG -> icon = IconFactory.icons[0];
            case _1 -> icon = IconFactory.icons[1];
            case _2 -> icon = IconFactory.icons[2];
            case _3 -> icon = IconFactory.icons[3];
            case _4 -> icon = IconFactory.icons[4];
            case _5 -> icon = IconFactory.icons[5];
            case _6 -> icon = IconFactory.icons[6];
            case _7 -> icon = IconFactory.icons[7];
            case _8 -> icon = IconFactory.icons[8];
            case MINE -> icon = IconFactory.icons[9];
        }
        this.setIcon(icon);
        this.setVisible(false);

    }

    public String statusToString() {
        String res = "";
        switch (getStatus()) {
            case FLAG -> res = "F";
            case _1 -> res = "1";
            case _2 -> res = "2";
            case _3 -> res = "3";
            case _4 -> res = "4";
            case _5 -> res = "5";
            case _6 -> res = "6";
            case _7 -> res = "7";
            case _8 -> res = "8";
            case MINE -> res = "O";
        }
        return res;
    }


}