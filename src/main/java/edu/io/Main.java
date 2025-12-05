package edu.io;

import edu.io.token.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        board.placeToken(2, 2, new GoldToken(2.5));
        board.placeToken(3, 3, new PyriteToken());
        board.placeToken(1, 1, new PickaxeToken());
        board.placeToken(4, 4, new AnvilToken());
        board.placeToken(0, 3, new GoldToken(1.0));
        board.placeToken(6, 6, new SluiceboxToken()); // DODAJ RYNNĘ

        Player playerObj = new Player();
        PlayerToken playerToken = new PlayerToken(board);
        playerObj.assignToken(playerToken);
        playerToken.setPlayer(playerObj);

        Scanner scanner = new Scanner(System.in);
        boolean playing = true;

        System.out.println("=== GOLD RUSH ===");
        System.out.println("Sterowanie: W-góra, S-dół, A-lewo, D-prawo, Q-wyjście");
        System.out.println("Symbole: P-gracz, $-złoto, <-kilof, &-kowadło, 亘-rynna");

        while (playing) {
            board.display();
            System.out.println("Złoto: " + playerObj.gold.amount() + " uncji");
            System.out.print("Twój ruch: ");
            String input = scanner.nextLine().toUpperCase();

            try {
                switch (input) {
                    case "W": playerToken.move(PlayerToken.Move.UP); break;
                    case "S": playerToken.move(PlayerToken.Move.DOWN); break;
                    case "A": playerToken.move(PlayerToken.Move.LEFT); break;
                    case "D": playerToken.move(PlayerToken.Move.RIGHT); break;
                    case "Q": playing = false; break;
                    default: System.out.println("Nieznany ruch!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Nie możesz tam iść!");
            }
        }

        System.out.println("Koniec gry! Zebrano: " + playerObj.gold.amount() + " uncji złota");
        scanner.close();
    }
}