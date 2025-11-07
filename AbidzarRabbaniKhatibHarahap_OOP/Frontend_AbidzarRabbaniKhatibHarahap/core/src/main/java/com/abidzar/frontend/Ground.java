package com.abidzar.frontend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Ground {

    private static final float GROUND_HEIGHT = 50f;

    private Rectangle collider;


    public Ground() {
        float screenWidth = Gdx.graphics.getWidth();

        collider = new Rectangle(0, 0, screenWidth * 2f, GROUND_HEIGHT);
    }

    public void update(float cameraX) {

        float screenWidth = Gdx.graphics.getWidth();
        collider.x = cameraX - screenWidth / 2f - 500f;
        collider.y = 0f;

        collider.width = screenWidth * 2f;

        collider.height = GROUND_HEIGHT;
    }

    public boolean isColliding(Rectangle playerCollider) {
        return collider.overlaps(playerCollider);
    }

    public float getTopY() {
        return GROUND_HEIGHT;
    }

    public void renderShape(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1f);
        shapeRenderer.rect(collider.x, collider.y, collider.width, collider.height);
    }
}
