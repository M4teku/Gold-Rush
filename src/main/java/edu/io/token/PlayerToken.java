package edu.io.token;

import edu.io.Board;

public class PlayerToken extends Token {
    private Board board;
    private int col;
    private int row;

    public enum Move {
        NONE, LEFT, RIGHT, UP, DOWN
    }

    public PlayerToken(Board board) {
        this(board, 0, 0);
    }

    public PlayerToken(Board board, int startCol, int startRow) {
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;
        this.col = startCol;
        this.row = startRow;
        board.placeToken(col, row, this);
    }

    public void move(Move dir) {
        int oldCol = col;
        int oldRow = row;

        switch (dir) {
            case LEFT: col -= 1; break;
            case RIGHT: col += 1; break;
            case UP: row -= 1; break;
            case DOWN: row += 1; break;
            case NONE: return;
        }

        if (col < 0 || col >= 5 || row < 0 || row >= 5) {
            col = oldCol;
            row = oldRow;
            throw new IllegalArgumentException("Cannot move outside the board");
        }

        board.placeToken(oldCol, oldRow, new EmptyToken());
        board.placeToken(col, row, this);
    }

    public Board.Coords pos() {
        return new Board.Coords(col, row);
    }
}