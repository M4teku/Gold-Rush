package edu.io;

public class Board {
    public int size;
    public Token[][] grid;

    public Board() {
        this.size = 5;
        this.grid = new Token[size][size];
        clean();
    }

    public void clean() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[col][row] = new Token("・");
            }
        }
    }

    public void placeToken(int col, int row, Token token) {
        if (col >= 0 && col < size && row >= 0 && row < size) {
            grid[col][row] = token;
        }
    }

    public Token square(int col, int row) {
        if (col >= 0 && col < size && row >= 0 && row < size) {
            return grid[col][row];
        }
        return null;
    }

    public void display() {
        System.out.println("=== PLANSZA ===");
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(grid[col][row].label + " ");
            }
            System.out.println();
        }
    }
}