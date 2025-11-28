package edu.io;

import edu.io.token.Token;
import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;

public class Player {
    private PlayerToken token;
    private double gold;

    public void assignToken(PlayerToken token) {
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }

    public double gold() {
        return gold;
    }

    public void gainGold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.gold += amount;
    }

    public void loseGold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (this.gold - amount < 0) {
            throw new IllegalArgumentException("Gold cannot go below zero");
        }
        this.gold -= amount;
    }

    public void interactWithToken(Token token) {
        if (token instanceof GoldToken goldToken) {
            gainGold(goldToken.amount());
            System.out.println("Zebrano złoto: " + goldToken.amount() + " uncji!");
        }
    }
}