package com.menes.projects.minesweeper.view;

import javax.swing.*;
import java.awt.*;

public class Home extends JPanel {
    public Home(){
        setPreferredSize(new Dimension(1000, 600));
        setBackground(Color.YELLOW);
        setLayout(new BorderLayout());
        add(new LoginGUI());
    }




}
