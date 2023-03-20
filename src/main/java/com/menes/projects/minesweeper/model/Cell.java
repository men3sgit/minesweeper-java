package com.menes.projects.minesweeper.model;

public class Cell {
    private boolean hasMine;
    private int numNeighborMines;
    private boolean isRevealed;
    private boolean isFlagged;

    public Cell(boolean hasMine) {
        this.hasMine = hasMine;
        this.numNeighborMines = 0;
        this.isRevealed = false;
        this.isFlagged = false;
    }

    public boolean hasMine() {
        return hasMine;
    }

    public int getNumNeighborMines() {
        return numNeighborMines;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setNumNeighborMines(int numNeighborMines) {
        this.numNeighborMines = numNeighborMines;
    }

    public void reveal() {
        isRevealed = true;
    }

    public void toggleFlag() {
        isFlagged = !isFlagged;
    }

    public boolean isHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
}
