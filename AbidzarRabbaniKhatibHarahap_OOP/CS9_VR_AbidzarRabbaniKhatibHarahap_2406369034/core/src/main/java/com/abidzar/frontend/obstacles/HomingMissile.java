package com.abidzar.frontend.obstacles;

import com.abidzar.frontend.Player;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class HomingMissile extends BaseObstacle {

    private Player target;
    private final Vector2 velocity;
    private float speed = 200f;
    private float width = 40f;
    private float height = 20f;

    public HomingMissile(Vector2 startPosition) {
        super(startPosition, 0);
        this.velocity = new Vector2();
        this.length = 0; // not used for missile
        updateCollider();
    }

    @Override
    public void initialize(Vector2 startPosition, int length) {
        super.initialize(startPosition, 0);
        this.velocity.set(0f, 0f);
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public boolean isTargetingPlayer() {
        if (target == null) return false;
        float missileCenterX = position.x + (width / 2f);
        float targetCenterX = target.getPosition().x + (target.getWidth() / 2f);
        boolean passed = targetCenterX > missileCenterX;
        return !passed;
    }

    public void update(float delta) {
        if (target == null || !active) return;
        if (!isTargetingPlayer()) return;

        Vector2 targetPosition = new Vector2(target.getPosition());
        targetPosition.add(target.getWidth() / 2f, target.getHeight() / 2f);

        velocity.set(targetPosition).sub(position).nor().scl(speed);
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;
        updateCollider();
    }

    @Override
    protected void updateCollider() {
        collider = new Rectangle(position.x, position.y, width, height);
    }

    @Override
    protected void drawShape(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(position.x, position.y, width, height);
    }

    @Override
    public float getRenderWidth() {
        return width;
    }
}
