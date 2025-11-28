package edu.io;

import edu.io.token.*;

public class Main {
    public static void main(String[] args) {

        Board board = new Board();
        board.placeToken(2, 2, new GoldToken());
        PlayerToken player = new PlayerToken(board);
        board.display();


        Game game = new Game();
        Player playerObj = new Player();
        game.join(playerObj);

        game.board().placeToken(2, 2, new GoldToken(2.5));
        game.board().placeToken(3, 3, new PyriteToken());

        game.start();


        System.out.println("--- Test poruszania ---");
        playerObj.token().move(PlayerToken.Move.RIGHT);
        playerObj.token().move(PlayerToken.Move.DOWN);
        game.board().display();
        System.out.println("Złoto gracza: " + playerObj.gold() + " uncji");
    }
}