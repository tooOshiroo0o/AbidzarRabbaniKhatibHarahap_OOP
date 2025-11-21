package com.abidzar.frontend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {
    private Texture backgroundTexture;
    private TextureRegion backgroundRegion;
    private float width = 2688f;
    private float height = 1536f;
    private float currentCameraX = 0f;

    public Background() {
        backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        backgroundRegion = new TextureRegion(backgroundTexture);
    }

    public void update(float cameraX) {
        this.currentCameraX = cameraX;
    }

    public void render(SpriteBatch batch) {
        if (backgroundRegion == null || batch == null) return;

        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        float scale = (float) screenHeight / height;
        float scaledW = width * scale;
        float scaledH = height * scale;

        float cameraLeft = currentCameraX - screenWidth / 2f;

        float startCol = (float) Math.floor(cameraLeft / scaledW);
        float startX = startCol * scaledW;

        float drawX = startX;
        float endX = cameraLeft + screenWidth + scaledW;

        while (drawX < endX) {
            batch.draw(backgroundRegion, drawX, 0f, scaledW, scaledH);
            drawX += scaledW;
        }
    }

    public void dispose() {
        if (backgroundTexture != null) {
            backgroundTexture.dispose();
            backgroundTexture = null;
        }
    }
}
