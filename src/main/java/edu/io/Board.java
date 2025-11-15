package edu.io;

import edu.io.token.*;

public class Board {
    private int size;
    private Token[][] grid;

    public Board() {
        this.size = 5;
        this.grid = new Token[size][size];
        clean();
    }

    public int size() {
        return size;
    }

    public Token peekToken(int col, int row) {
        return square(col, row);
    }

    public void clean() {
        EmptyToken empty = new EmptyToken();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[col][row] = empty;
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
        System.out.println("\n=== PLANSZA ===");
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(grid[col][row].label() + " ");
            }
            System.out.println();
        }
        System.out.println("===============\n");
    }

    public record Coords(int col, int row) {}
}