package com.abidzar.frontend.obstacles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class VerticalLaser extends BaseObstacle {
    public VerticalLaser(Vector2 startPosition, int length) {
        super(startPosition, length);
        this.active = true;
    }

    @Override
    public void initialize(Vector2 startPosition, int length) {
        super.initialize(startPosition, length);
        this.active = true;
    }

    @Override
    protected void updateCollider() {
        collider = new Rectangle(position.x, position.y, WIDTH, length);
    }

    @Override
    protected void drawShape(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.RED);   // <-- add
        shapeRenderer.rect(position.x, position.y, WIDTH, length);
    }

    @Override
    public boolean isOffScreenCamera(float left) {
        return position.x + WIDTH < left - 100;
    }


    @Override
    protected float getRenderWidth() {
        return WIDTH;
    }
}
