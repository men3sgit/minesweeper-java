package com.menes.projects.minesweeper.view;

import com.menes.projects.minesweeper.model.Board;
import com.menes.projects.minesweeper.model.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GameDisplay extends JPanel {
    public static int CELL_SIZE = 40;

    private BoardGUI boardGUI = new BoardGUI();

    public GameDisplay(int cellSize) {

        initGUI();
    }

    public GameDisplay() {
        setBackground(Color.MAGENTA);
        initGUI();
        displayBoard();
    }

    public GameDisplay(BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
    }

    public void initGUI() {
        setPreferredSize(new Dimension(1080, 700));
        setLayout(new BorderLayout());
        JPanel westPanel = new JPanel();
        JPanel northPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(64,0));
        northPanel.setPreferredSize(new Dimension(0,64));
        this.add(boardGUI, BorderLayout.CENTER);
        this.add(westPanel,BorderLayout.WEST);
        this.add(northPanel,BorderLayout.NORTH);

    }


    public BoardGUI getBoardGUI() {
        return boardGUI;
    }
    public void displayBoard(){
        String label = "MINESWEEPER BY MENES";
        int rowLength  = getBoardGUI().getCellsGUI()[0].length;
        System.out.print("\n\n\n\t");
        for(int i = 0;i<rowLength/2;i++){
            System.out.print("   ");
        }
        System.out.println(label);
        System.out.print("\t\t\t+");
       for(int i = 0;i<rowLength;i++){
           System.out.print("---");
       }
        System.out.println("--+");
        for (CellGUI[] cells : getBoardGUI().getCellsGUI()) {
            System.out.print("\t\t\t|  ");
            for(CellGUI cell : cells){
                System.out.print(cell + "  ");
            }
            System.out.println("|");

        }
        System.out.print("\t\t\t+");
        for(int i = 0;i<rowLength;i++){
            System.out.print("---");
        }
        System.out.print("--+\n\n\n");


    }
}
