package com.abidzar.frontend;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Vector2 position;
    private Vector2 startPosition;
    private Vector2 velocity;
    private float width = 40f;
    private float height = 40f;
    private boolean isDead = false;
    private float distanceTraveled = 0f;

    public Player(Vector2 startPosition) {
        this.startPosition = new Vector2(startPosition);
        this.position = new Vector2(startPosition);
        this.velocity = new Vector2(0,0);
    }

    public void update(float delta, boolean isFlying) {
        if (isDead) return;

        float forwardSpeed = 120f;
        position.x += forwardSpeed * delta;
        if (isFlying) {
            velocity.y = 150f;
        } else {
            velocity.y -= 400f * delta;
        }
        position.y += velocity.y * delta;
        if (position.y < 0) position.y = 0;
        distanceTraveled = position.x / 10f;
    }

    public void die() {
        isDead = true;
        velocity.set(0,0);
    }

    public void reset() {
        isDead = false;
        position.set(startPosition);
        velocity.set(0,0);
        distanceTraveled = 0f;
    }

    public boolean isDead() {
        return isDead;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getCollider() {
        return new Rectangle(position.x, position.y, width, height);
    }

    public float getDistanceTraveled() {
        return distanceTraveled;
    }

    public void checkBoundaries(Ground ground, int screenHeight) {
        if (position.y < ground.getTopY()) {
            position.y = ground.getTopY();
            velocity.y = 0;
        }
        if (position.y + height > screenHeight) {
            position.y = screenHeight - height;
            velocity.y = 0;
        }
    }

    public void renderShape(ShapeRenderer renderer) {
    }
}
