package com.menes.projects.minesweeper.view;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JPanel {
    JLabel loginLabel = new JLabel("LOGIN");
   public LoginGUI(){
       setPreferredSize(new Dimension(1000, 600));
       setBackground(Color.black);
       setLayout(new FlowLayout(FlowLayout.CENTER));
       add(loginLabel);
       add(new JButton("Hello"));

    }

}
