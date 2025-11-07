package com.abidzar.frontend.obstacles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class HorizontalLaser extends BaseObstacles {

    public HorizontalLaser(Vector2 startPosition,int length) {
        super(StartPosition, length);
    }

    @Override
    public void initialize(Vector2 startPosition, int length) {
        super(StarPosition, )
    }

   @Override
    public void updateCollider() {
        collider = new Rectangle(position.x,position.y, length, WIDTH);
   }

   @Override
    public void drawShape(ShapeRenderer shapeRenderer) {
       ShapeRenderer.rectangle(position.x,position.y, length, WIDTH);
   }

   public void getRenderWidth() {

   }

}
