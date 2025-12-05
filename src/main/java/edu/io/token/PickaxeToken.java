package edu.io.token;

public class PickaxeToken extends Token implements Tool, Repairable {
    private final double gainFactor;
    private final int initialDurability;
    private int durability;
    private Token withToken;

    public PickaxeToken() {
        this(1.5, 3);
    }

    public PickaxeToken(double gainFactor, int durability) {
        super(Label.PICKAXE_TOKEN_LABEL);
        if (gainFactor < 1.0) {
            throw new IllegalArgumentException("Gain factor must be greater than 1.0");
        }
        if (durability <= 0) {
            throw new IllegalArgumentException("Durability must be positive");
        }
        this.gainFactor = gainFactor;
        this.initialDurability = durability;
        this.durability = durability;
    }

    public PickaxeToken(double gainFactor) {
        this(gainFactor, 3);
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
        return this;
    }

    @Override
    public Tool ifWorking(Runnable action) {
        if (!isBroken() && withToken instanceof GoldToken) {
            use();
            action.run();
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

    public void use() {
        if (durability > 0) {
            durability--;
        }
    }

    @Override
    public void repair() {
        this.durability = this.initialDurability;
    }
}