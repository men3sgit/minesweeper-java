package com.menes.projects.minesweeper.model;

import com.menes.projects.minesweeper.view.MineSweeper;

import java.util.Random;

public class Board {
    private Cell[][] board = new Cell[MineSweeper.LEVEL][MineSweeper.LEVEL];


    public Board() {
        init();
    }
    public void init(){
        initEmptyCells();

        setMines(MineSweeper.LEVEL);

        initMineInBound();
    }
    private void initEmptyCells(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell(Status.EMPTY);
            }
        }
    }
    private void initMineInBound(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                checkMineInBound(i, j); // bound = 3x3
            }
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public int getHeight() {
        return this.getBoard().length;
    }

    public int getWidth() {
        return this.getBoard()[0].length;
    }

    public void setMines(int mines) {
        if (mines == 0) return;
        Random rd = new Random();
        int x = rd.nextInt(MineSweeper.LEVEL);
        int y = rd.nextInt(MineSweeper.LEVEL);
        if (board[x][y].isEmpty()) {
            board[x][y].setStatus(Status.MINE);
            mines--;
        }

        setMines(mines);
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public void displayBoard() {
        for (Cell[] array : board) {
            for (Cell cell : array) {
                System.out.print(cell + " | ");
            }
            System.out.println();
            System.out.println("_____________________________________________________________");
            System.out.println();
        }
    }

    private void checkMineInBound(int x, int y) {
        Cell currentCell = board[x][y];
        if (currentCell.isMine())
            return;
        int i, j, limX, limY, count = 0;
        i = x == 0 ? x : x - 1;
        j = y == 0 ? y : y - 1;
        limX = x == board.length - 1 ? x + 1 : x + 2;
        limY = y == board[0].length - 1 ? y + 1 : y + 2;
        for (int row = i; row < limX; row++)
            for (int col = j; col < limY; col++) {
                Cell cell = board[row][col];
                if (cell.isMine())
                    count++;
            }

        currentCell.setStatus(Status.values()[count]);
    }

    public static void main(String[] args) {
        new Board().displayBoard();
    }

}
