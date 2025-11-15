package com.abidzar.frontend.observers;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;


public class ScoreUIObserver implements Observer {


    private BitmapFont font;
    private SpriteBatch batch;


    public ScoreUIObserver() {
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        batch = new SpriteBatch();
    }


    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers(int score) {

    }

    @Override
    public void update(int score) {
        System.out.println("Score updated: " + score);
    }


    public void render(int score) {
        batch.begin();
        font.draw(batch, "Score: " + score, 20, 720);
        batch.end();
    }


    public void dispose() {
        if (font != null) font.dispose();
        if (batch != null) batch.dispose();
    }
}
