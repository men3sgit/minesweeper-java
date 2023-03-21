package com.menes.projects.minesweeper.view;

import javax.swing.*;

import com.menes.projects.minesweeper.model.Level;

import static com.menes.projects.minesweeper.model.Level.*;

public class IconFactory {
    static char level;
    static ImageIcon[] icons = new ImageIcon[10];

    static {
        level = MineSweeper.LEVEL == EASY ? '1' : MineSweeper.LEVEL == NORMAL ? '2' : '3';
        icons[0] = new ImageIcon("./icons/flag" + level +".png");
        icons[1] = new ImageIcon("./icons/one" + level +".png");
        icons[2] = new ImageIcon("./icons/two" + level +".png");
        icons[3] = new ImageIcon("./icons/three" + level +".png");
        icons[4] = new ImageIcon("./icons/four" + level +".png");
        icons[5] = new ImageIcon("./icons/five" + level +".png");
        icons[6] = new ImageIcon("./icons/six" + level +".png");
        icons[7] = new ImageIcon("./icons/seven" + level +".png");
        icons[8] = new ImageIcon("./icons/eight" + level +".png");
        icons[9] = new ImageIcon("./icons/mine" + level +".png");
    }




}
