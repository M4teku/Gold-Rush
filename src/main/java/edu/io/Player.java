package edu.io;

import edu.io.token.*;

public class Player {
    private PlayerToken token;
    public final Gold gold;
    private final Shed shed;
    private Tool tool;

    public Player() {
        this.gold = new Gold();
        this.shed = new Shed();
        this.tool = new NoTool();
    }

    public void assignToken(PlayerToken token) {
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }

    public void gainGold(double amount) {
        gold.gain(amount);
    }

    public void loseGold(double amount) {
        gold.lose(amount);
    }

    public void interactWithToken(Token token) {
        if (token instanceof GoldToken goldToken) {
            useToolOnGold(goldToken);
        } else if (token instanceof PickaxeToken pickaxeToken) {
            shed.add(pickaxeToken);
            this.tool = pickaxeToken;
            System.out.println("ZDOBYTO KILOF! Wzmocnienie: " + pickaxeToken.gainFactor() + "x");
            System.out.println("Wytrzymałość: " + pickaxeToken.durability() + " użyć");
        } else if (token instanceof SluiceboxToken sluiceboxToken) {
            shed.add(sluiceboxToken);
            this.tool = sluiceboxToken;
            System.out.println("ZDOBYTO RYNNĘ! Wzmocnienie: " + sluiceboxToken.gainFactor() + "x");
            System.out.println("Wytrzymałość: " + sluiceboxToken.durability() + " użyć");
        } else if (token instanceof AnvilToken) {
            if (tool instanceof Repairable repairable) {
                repairable.repair();
                System.out.println("NAPRAWIONO NARZĘDZIE! Wytrzymałość przywrócona.");
            } else {
                System.out.println("Nie masz narzędzia do naprawy.");
            }
        }
    }

    private void useToolOnGold(GoldToken goldToken) {
        final double amount = goldToken.amount();

        tool.useWith(goldToken)
                .ifWorking(() -> {
                    double collectedAmount = amount;
                    if (tool instanceof PickaxeToken pickaxe) {
                        collectedAmount = amount * pickaxe.gainFactor();
                        gainGold(collectedAmount);
                        System.out.println("ZEBRANO " + collectedAmount + " uncji złota (z kilofem " +
                                pickaxe.gainFactor() + "x)");
                        System.out.println("Wytrzymałość kilofa: " + pickaxe.durability() + " użyć");
                    } else if (tool instanceof SluiceboxToken sluicebox) {
                        collectedAmount = amount * sluicebox.gainFactor();
                        gainGold(collectedAmount);
                        System.out.println("ZEBRANO " + collectedAmount + " uncji złota (z rynną " +
                                String.format("%.2f", sluicebox.gainFactor()) + "x)");
                    }
                })
                .ifIdle(() -> {
                    gainGold(amount);
                    System.out.println("ZEBRANO " + amount + " uncji złota (bez narzędzia)");
                })
                .ifBroken(() -> {
                    gainGold(amount);
                    System.out.println("ZEBRANO " + amount + " uncji złota (narzędzie zepsute!)");
                    shed.dropTool();
                    tool = new NoTool();
                    System.out.println("Narzędzie się zepsuło i zostało wyrzucone!");
                });
    }
}