package com.menes.projects.minesweeper.view;

import com.menes.projects.minesweeper.model.Status;

import javax.swing.*;
import java.awt.*;

public class CellGUI extends JButton {
    public CellGUI(Status status) {
        this.setText(status.toString());
        this.setBackground(new Color(201, 201, 201));
        this.setIcon(square);
        this.setFocusable(false);
        this.setFont(new Font("", Font.PLAIN, -5));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

}