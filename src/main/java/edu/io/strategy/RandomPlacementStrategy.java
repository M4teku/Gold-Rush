package edu.io.strategy;

import edu.io.Board;
import edu.io.PlacementStrategy;
import edu.io.token.EmptyToken;
import java.util.Random;

public class RandomPlacementStrategy implements PlacementStrategy {
    private Random random = new Random();

    @Override
    public Board.Coords findAvailableSquare(Board board) {
        int size = board.size();
        int attempts = 0;
        int maxAttempts = size * size * 2;

        while (attempts < maxAttempts) {
            int col = random.nextInt(size);
            int row = random.nextInt(size);
            if (board.peekToken(col, row) instanceof EmptyToken) {
                return new Board.Coords(col, row);
            }
            attempts++;
        }

        //szuka sekwencyjnie jak nie znajdzie losowo
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.peekToken(col, row) instanceof EmptyToken) {
                    return new Board.Coords(col, row);
                }
            }
        }

        throw new IllegalStateException("No available squares on the board");
    }
}