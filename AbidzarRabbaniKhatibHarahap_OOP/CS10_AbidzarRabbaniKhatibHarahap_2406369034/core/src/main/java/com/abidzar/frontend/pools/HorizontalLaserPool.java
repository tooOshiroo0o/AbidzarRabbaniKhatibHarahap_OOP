package com.abidzar.frontend.pools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.abidzar.frontend.obstacles.HorizontalLaser;

public class HorizontalLaserPool extends ObjectPool<HorizontalLaser> {

    @Override
    public HorizontalLaser createObject() {
        return new HorizontalLaser(new Vector2(0, 0), 100);
    }

    @Override
    public void resetObject(HorizontalLaser obstacle) {
        obstacle.setPosition(Gdx.graphics.getWidth(), 0);
        obstacle.setActive(true);
    }

    public HorizontalLaser obtain(Vector2 position, int length) {
        HorizontalLaser obstacle = super.obtain();
        obstacle.initialize(position, length);
        obstacle.setActive(true);
        return obstacle;
    }
}
