package edu.io;

import edu.io.token.Tool;
import edu.io.token.NoTool;
import java.util.Stack;

public class Shed {
    private final Stack<Tool> tools;
    private final Tool noTool;

    public Shed() {
        this.tools = new Stack<>();
        this.noTool = new NoTool();
    }

    public boolean isEmpty() {
        return tools.isEmpty();
    }

    public void add(Tool tool) {
        if (tool == null) {
            throw new IllegalArgumentException("Tool cannot be null");
        }
        tools.push(tool);
    }

    public Tool getTool() {
        if (tools.isEmpty()) {
            return noTool;
        }
        return tools.peek();
    }

    public void dropTool() {
        if (!tools.isEmpty()) {
            tools.pop();
        }
    }
}