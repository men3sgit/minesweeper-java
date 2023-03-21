package com.menes.projects.minesweeper.view;

import com.menes.projects.minesweeper.model.Status;
import com.menes.projects.minesweeper.service.IconFactoryService;
import com.menes.projects.minesweeper.service.IconService;

import javax.accessibility.AccessibleRole;
import javax.swing.*;
import java.awt.*;

public class CellGUI extends JButton {
    private Status status;

    public CellGUI(Status status) {
        this.setText((this.status = status).toString());
        this.setBackground(new Color(201, 201, 201));
        this.setIcon(IconFactoryService.createIcon(IconService.ALARM));
        this.setFocusable(false);
        this.setFont(new Font("", Font.PLAIN, -5));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    public Status getStatus() {
        return this.status;
    }

    public void reveal() {
        Icon icon =  new ImageIcon("");
        switch (status) {
            case _1 -> icon = new ImageIcon("");
            case _2 -> icon = new ImageIcon("");
            case _3 -> icon = new ImageIcon("");
            case _4 -> icon = new ImageIcon("");
            case _5 -> icon = new ImageIcon("");
            case _6 -> icon = new ImageIcon("");
            case _7 -> icon = new ImageIcon("");
            case _8 -> icon = new ImageIcon("");
            case MINE -> icon = new ImageIcon("");
            case FLAG -> icon = new ImageIcon("");
        }
        this.setIcon(icon);
        this.setVisible(false);

    }


}