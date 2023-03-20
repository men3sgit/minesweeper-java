package com.menes.projects.minesweeper.model;

public class Cell{
    private Status status;
    public boolean isMine(){
        return status == Status.MINE;
    }
    @Override
    public String toString(){
        return status.toString();
    }

    public Cell(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
    public boolean isEmpty(){
        return this.status == Status.EMPTY;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}