package com.abidzar.frontend.pools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.abidzar.frontend.obstacles.VerticalLaser;

public class VerticalLaserPool extends ObjectPool<VerticalLaser> {

    @Override
    public VerticalLaser createObject() {
        return new VerticalLaser(new Vector2(0, 0), 100);
    }

    @Override
    public void resetObject(VerticalLaser obstacle) {
        obstacle.setPosition(Gdx.graphics.getWidth(), 0);
        obstacle.setActive(true);
    }

    public VerticalLaser obtain(Vector2 position, int length) {
        VerticalLaser obstacle = super.obtain();
        obstacle.initialize(position, length);
        obstacle.setActive(true);
        return obstacle;
    }


}
