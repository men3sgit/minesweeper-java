package com.menes.projects.minesweeper;

import java.util.Random;

 enum Cell {
	 EMPTY, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, BOOM
}

public class Matrix {

	Cell[][] matrix;
	int boom, row, col;

	public Matrix(int booms, int row, int col) {
		this.matrix = new Cell[row][col];
		this.row = row;
		this.boom = booms;
		this.col = col;
		setupBooms(boom);
		setNumbers();
	}

	public void setupBooms(int boom) {
		if (boom == 0)
			return;
		int row = new Random().nextInt(matrix.length);
		int col = new Random().nextInt(matrix[0].length);
		if (matrix[row][col] != Cell.BOOM) {
			matrix[row][col] = Cell.BOOM;
			boom--;
		}
		setupBooms(boom);
	}

	public void setNumbers() {
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				check(i, j);
	}

	private void check(int x, int y) {
		if (matrix[x][y] == Cell.BOOM)
			return;
		int i, j, limX, limY, count = 0;
		i = x == 0 ? x : x - 1;
		j = y == 0 ? y : y - 1;
		limX = x == matrix.length - 1 ? x + 1 : x + 2;
		limY = y == matrix[0].length - 1 ? y + 1 : y + 2;
		for (int row = i; row < limX; row++)
			for (int col = j; col < limY; col++)
				if (matrix[row][col] == Cell.BOOM)
					count++;
		matrix[x][y] = Cell.values()[count];
	}

	public void setGame() {
		this.matrix = new Cell[row][col];
		setupBooms(boom);
		setNumbers();

	}

	public Cell[][] getMatrix() {
		return matrix;
	}

	public int getBooms() {
		return boom;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public static void main(String[] args) {
	}

}
