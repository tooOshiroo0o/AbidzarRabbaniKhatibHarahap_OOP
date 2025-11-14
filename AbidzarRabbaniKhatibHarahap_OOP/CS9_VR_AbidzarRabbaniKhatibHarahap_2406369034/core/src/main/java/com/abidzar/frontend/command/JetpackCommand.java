package com.abidzar.frontend.command;

import com.abidzar.frontend.Player;
import com.badlogic.gdx.math.Vector2;

public class JetpackCommand extends Player {

    public JetpackCommand(Vector2 startPosition) {
        super(startPosition);
    }

    @Override
    public void execute() {
        if (!player.IsDead) {
            player.fly
        }
    }
}
