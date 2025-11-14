package com.abidzar.frontend;

public class MoveCommand implements Command {
    private Player p;
    private float dx, dy;

    public MoveCommand(Player p, float dx, float dy) {
        this.p = p;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute() {
        p.move(dx, dy);
    }
}
