package com.abidzar.frontend;

import com.badlogic.gdx.math.Vector2;

public class Player {
    private Vector2 pos;

    public Player(float x, float y) {
        pos = new Vector2(x, y);
    }

    public void move(float dx, float dy) {
        pos.add(dx, dy);
    }

    public Vector2 getPosition() {
        return pos;
    }
}
