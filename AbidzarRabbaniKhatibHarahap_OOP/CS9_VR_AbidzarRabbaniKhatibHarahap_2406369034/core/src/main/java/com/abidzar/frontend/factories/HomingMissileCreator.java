package com.abidzar.frontend.factories;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.abidzar.frontend.obstacles.BaseObstacle;
import com.abidzar.frontend.obstacles.HomingMissile;
import com.abidzar.frontend.pools.HomingMissilePool;

public class HomingMissileCreator implements ObstacleFactory.ObstacleCreator {
    private final HomingMissilePool pool = new HomingMissilePool();

    @Override
    public BaseObstacle create(float groundTopY, float spawnX, float playerHeight, Random rng) {
        float maxY = Math.max(groundTopY, 0f);
        float randomY = groundTopY + rng.nextFloat() * Math.max(0f, (300f)); // allow some vertical range
        Vector2 pos = new Vector2(spawnX, randomY);
        return pool.obtain(pos);
    }

    @Override
    public void release(BaseObstacle obstacle) {
        if (obstacle instanceof HomingMissile) {
            pool.release((HomingMissile) obstacle);
        }
    }

    @Override
    public void releaseAll() {
        pool.releaseAll();
    }

    @Override
    public List<HomingMissile> getInUse() {
        return pool.getInUse();
    }

    @Override
    public boolean supports(BaseObstacle obstacle) {
        return obstacle instanceof HomingMissile;
    }

    @Override
    public String getName() {
        return "HomingMissile";
    }
}
