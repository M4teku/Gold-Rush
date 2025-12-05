package edu.io;

public class Gold {
    private double amount;

    public Gold() {
        this(0.0);
    }

    public Gold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }

    public void gain(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount += amount;
    }

    public void lose(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (this.amount - amount < 0) {
            throw new IllegalArgumentException("Gold cannot go below zero");
        }
        this.amount -= amount;
    }
}