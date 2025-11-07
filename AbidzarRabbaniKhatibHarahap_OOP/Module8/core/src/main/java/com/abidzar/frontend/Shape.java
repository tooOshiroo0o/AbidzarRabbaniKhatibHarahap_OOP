package com.abidzar.frontend;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Shape {
    protected Vector2 position;
    protected float size = 50f;
    protected String type;

    public Shape(String type) {
        this.type = type;
        position = new Vector2();
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public String getType() {
        return type;
    }

    public abstract void render(ShapeRenderer renderer);
}
