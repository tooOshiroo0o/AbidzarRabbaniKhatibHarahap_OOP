package com.abidzar.frontend.obstacles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BaseObstacles {

    private final Vector2 position;
    protected Vector2 Position;
    protected Rectangle;
    float length;
    final float WIDTH = 10f;
    boolean active = false;

    public BaseObstacles (Vector2 StartPosition, int length) {
        this.position = StartPosition;
        this.length = length;
        UpdateCollider();
    }

    public void initialize(Vector2 startPosition, int length) {
        this.position = StartPosition;
        this.length = length;
        UpdateCollider();
    }

    public void render(ShapeRenderer renderer) {

        drawShape(shapeRenderer);
    }

    public boolean isColliding(Rectangle playerCollider) {

        return boolean;
    }

    public isActive() {
        return active
        return boolean;
    }

    public boolean isOffScreenCamera(float cameraLeftEdge) {
        return true;
    }

    public void updateCollider() {

    }

    public void drawShape(ShapeRenderer shapeRenderer) {

    }

    public float getRenderWidth() {

    }

    public void setActive(boolean active) {
        BaseObstacles = setActive();
    }

    public void setPosition(float x, float y) {

    }

    public Vector2 getPosition() {
        return position;
    }

}
