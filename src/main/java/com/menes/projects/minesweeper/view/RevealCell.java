package com.menes.projects.minesweeper.view;

import javax.swing.*;
import java.awt.*;

public class RevealCell extends JLabel {
    private CellGUI cell;
    public RevealCell(CellGUI cell){
        this.cell = cell;
        this.setFont(new Font("Courier",Font.BOLD,30));
        this.setText(cell.statusToString());
        this.setForeground(cell.statusToColor());
        this.setBounds(cell.getBounds());
        this.setHorizontalAlignment(JLabel.CENTER);
        setBackground(Color.YELLOW);
        setBorder(BorderFactory.createBevelBorder(1));
        setOpaque(true);
    }

}
