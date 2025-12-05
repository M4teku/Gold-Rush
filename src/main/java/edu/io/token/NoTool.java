package edu.io.token;

public class NoTool implements Tool {
    public NoTool() {}

    @Override
    public Tool useWith(Token withToken) {
        return this;
    }

    @Override
    public Tool ifWorking(Runnable action) {
        return this;
    }

    @Override
    public Tool ifBroken(Runnable action) {
        return this;
    }

    @Override
    public Tool ifIdle(Runnable action) {
        action.run();
        return this;
    }

    @Override
    public boolean isBroken() {
        return false;
    }
}