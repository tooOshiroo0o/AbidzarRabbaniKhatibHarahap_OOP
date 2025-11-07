package com.abidzar.frontend;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {

    private final Vector2 position;
    private final Vector2 velocity;

    private final Rectangle collider;
    private final float width = 64f;
    private final float height = 64f;

    private final float baseSpeed = 300f;
    private float distanceTraveled = 0f;

    public Player(Vector2 startPosition) {
        this.position = new Vector2(startPosition);

        this.velocity = new Vector2(baseSpeed, 0f);

        this.collider = new Rectangle(position.x, position.y, width, height);
    }

    public void update(float delta, boolean isFlying) {
        updateDistanceAndSpeed(delta);
        updatePosition(delta);
        applyGravity(delta);
        if (isFlying) {
            fly(delta);
        }
        updateCollider();
    }

    private void updateDistanceAndSpeed(float delta) {

        distanceTraveled += velocity.x * delta;
    }

    private void updatePosition(float delta) {
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;
    }

    private void applyGravity(float delta) {

        float gravity = 2000f;
        velocity.y -= gravity * delta;

        velocity.x = baseSpeed;

        float maxVerticalSpeed = 700f;
        if (velocity.y > maxVerticalSpeed) {
            velocity.y = maxVerticalSpeed;
        } else if (velocity.y < -maxVerticalSpeed) {
            velocity.y = -maxVerticalSpeed;
        }
    }

    private void fly(float delta) {
        float force = 4500f;
        velocity.y += force * delta;
    }

    private void updateCollider() {
        collider.set(position.x, position.y, width, height);
    }

    public void checkBoundaries(Ground ground, float ceilingY) {

        if (ground.isColliding(collider)) {

            position.y = ground.getTopY();
            velocity.y = 0f;
            updateCollider();
        }


        float playerTop = position.y + height;
        if (playerTop > ceilingY) {
            position.y = ceilingY - height;
            velocity.y = 0f;
            updateCollider();
        }

        if (position.y < 0f) {
            position.y = 0f;
            velocity.y = 0f;
            updateCollider();
        }
    }

    public void renderShape(ShapeRenderer shapeRenderer) {

        shapeRenderer.setColor(0f, 0.7f, 1f, 1f);
        shapeRenderer.rect(position.x, position.y, width, height);
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
        return collider;
    }

    public float getDistanceTraveled() {
        return distanceTraveled / 10f;
    }
}
