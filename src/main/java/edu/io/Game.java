package edu.io;
import edu.io.Player;
import edu.io.token.PlayerToken;

public class Game {
    private Board board;
    private Player player;

    public Game() {
        this.board = new Board();
    }

    public void join(Player player) {
        this.player = player;
        PlayerToken token = new PlayerToken(board);
        player.assignToken(token);
        token.setPlayer(player);
    }

    public void start() {
        System.out.println("=== GRA ROZPOCZĘTA ===");
        board.display();
        System.out.println("Złoto gracza: " + player.gold.amount() + " uncji");
    }

    public Board board() {
        return board;
    }
}