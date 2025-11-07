package com.abidzar.frontend.lwjgl3;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGdxGame extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    private float boxX, boxY;
    private final float boxSize = 100;
    private final Color[] colors = {Color.RED, Color.YELLOW, Color.BLUE};
    private int colorIndex = 0;
    private Color boxColor = colors[colorIndex];

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        centerBox();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();

        // Safety: center if window just resized or started
        if (boxX == 0 && boxY == 0) centerBox();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(boxColor);
        shapeRenderer.rect(boxX, boxY, boxSize, boxSize);
        shapeRenderer.end();
    }

    private void handleInput() {
        float moveSpeed = 300 * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
            boxX -= moveSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
            boxX += moveSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W))
            boxY += moveSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))
            boxY -= moveSpeed;

        // Stay inside window
        boxX = Math.max(0, Math.min(boxX, Gdx.graphics.getWidth() - boxSize));
        boxY = Math.max(0, Math.min(boxY, Gdx.graphics.getHeight() - boxSize));

        // Mouse click → cycle color
        if (Gdx.input.justTouched()) {
            colorIndex = (colorIndex + 1) % colors.length;
            boxColor = colors[colorIndex];
            System.out.println("Box color changed to: " + getColorName(boxColor));
        }
    }

    private String getColorName(Color c) {
        if (c.equals(Color.RED)) return "Red";
        if (c.equals(Color.YELLOW)) return "Yellow";
        if (c.equals(Color.BLUE)) return "Blue";
        return "Unknown";
    }

    @Override
    public void resize(int width, int height) {
        centerBox();
    }

    private void centerBox() {
        boxX = (Gdx.graphics.getWidth() - boxSize) / 2f;
        boxY = (Gdx.graphics.getHeight() - boxSize) / 2f;
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
