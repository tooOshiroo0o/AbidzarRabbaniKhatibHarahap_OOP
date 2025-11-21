package com.abidzar.frontend.observers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreUIObserver implements Observer {
    private BitmapFont font;
    private SpriteBatch batch;

    public ScoreUIObserver() {
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        batch = new SpriteBatch();
    }

    @Override
    public void update(int score) {
        System.out.println("[ScoreUIObserver] Score updated: " + score);
    }

    public void render(int score) {
        if (batch == null) return;
        batch.begin();
        String text = "Score: " + score;
        float x = 10f;
        float y = Gdx.graphics.getHeight() - 10f;
        font.draw(batch, text, x, y);
        batch.end();
    }

    public void dispose() {
        if (font != null) font.dispose();
        if (batch != null) batch.dispose();
        font = null;
        batch = null;
    }
}
