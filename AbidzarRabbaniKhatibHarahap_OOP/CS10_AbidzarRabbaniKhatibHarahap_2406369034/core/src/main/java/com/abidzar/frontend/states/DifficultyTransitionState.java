package com.abidzar.frontend.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.abidzar.frontend.strategies.DifficultyStrategy;

public class DifficultyTransitionState implements GameState {
    private final GameStateManager gsm;
    private final PlayingState playingState;
    private final DifficultyStrategy newStrategy;
    private final BitmapFont font;

    private float timer;

    public DifficultyTransitionState(GameStateManager gsm,
                                     PlayingState playingState,
                                     DifficultyStrategy newStrategy) {
        this.gsm = gsm;
        this.playingState = playingState;
        this.newStrategy = newStrategy;
        this.font = new BitmapFont();
        this.timer = 2.0f;
    }

    @Override
    public void update(float delta) {
        timer -= delta;
        if (timer <= 0) {
            playingState.setDifficulty(newStrategy);
            gsm.pop();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        playingState.render(batch);

        batch.begin();

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        String mainText = "DIFFICULTY INCREASED!";
        String strategyName = newStrategy.getClass().getSimpleName();

        font.getData().setScale(1.5f);
        font.draw(batch, mainText,
            screenWidth / 2 - 100, screenHeight / 2 + 20);
        font.getData().setScale(1.0f);
        font.draw(batch, strategyName,
            screenWidth / 2 - 60, screenHeight / 2 - 20);

        batch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
