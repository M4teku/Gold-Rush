package edu.io.token;

public class SluiceboxToken extends Token implements Tool {
    private double gainFactor;
    private int durability;
    private Token withToken;
    private boolean wasUsed = false;

    public SluiceboxToken() {
        this(1.2, 5);
    }

    public SluiceboxToken(double gainFactor, int durability) {
        super(Label.SLUICEBOX_TOKEN_LABEL);
        if (gainFactor <= 1.0) {
            throw new IllegalArgumentException("Gain factor must be greater than 1.0");
        }
        if (durability <= 0) {
            throw new IllegalArgumentException("Durability must be positive");
        }
        this.gainFactor = gainFactor;
        this.durability = durability;
    }

    public double gainFactor() {
        return gainFactor;
    }

    public int durability() {
        return durability;
    }

    @Override
    public boolean isBroken() {
        return durability <= 0;
    }

    @Override
    public Tool useWith(Token withToken) {
        this.withToken = withToken;
        this.wasUsed = false;
        return this;
    }

    @Override
    public Tool ifWorking(Runnable action) {
        if (!isBroken() && withToken instanceof GoldToken && !wasUsed) {
            wasUsed = true;
            double currentGain = gainFactor;
            action.run();
            durability--;
            gainFactor -= 0.04;
            if (gainFactor < 1.0) {
                gainFactor = 1.0;
            }
        }
        return this;
    }

    @Override
    public Tool ifBroken(Runnable action) {
        if (isBroken()) {
            action.run();
        }
        return this;
    }

    @Override
    public Tool ifIdle(Runnable action) {
        if (!(withToken instanceof GoldToken)) {
            action.run();
        }
        return this;
    }
}