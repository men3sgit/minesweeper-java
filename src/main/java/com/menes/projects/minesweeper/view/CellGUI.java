package com.menes.projects.minesweeper.view;

import com.menes.projects.minesweeper.model.Status;

import javax.accessibility.AccessibleRole;
import javax.swing.*;
import java.awt.*;

public class CellGUI extends JButton {
    private Status status;

    public CellGUI(Status status) {
        this.setText((this.status = status).toString());
        this.setBackground(new Color(201, 201, 201));
        this.setFocusable(false);
        this.setFont(new Font("", Font.PLAIN, -5));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    public Status getStatus() {
        return this.status;
    }

    public void reveal() {
        Icon icon = new ImageIcon("");
        switch (status) {
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


}