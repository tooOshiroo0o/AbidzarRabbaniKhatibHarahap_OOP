package com.abidzar.frontend;

import com.abidzar.frontend.observers.ScoreUIObserver;

public class GameManager {
    private static GameManager instance;
    private int score = 0;

    private GameManager() {}

    public static synchronized GameManager getInstance() {
        if (instance == null) instance = new GameManager();
        return instance;
    }

    public void startGame() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int s) {
        score = s;
    }

    public void addObserver(ScoreUIObserver scoreUIObserver) {

    }
}
