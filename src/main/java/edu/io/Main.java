package edu.io;

import edu.io.token.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.placeToken(2, 2, new GoldToken());
        PlayerToken player = new PlayerToken(board);
        board.display();
    }
}