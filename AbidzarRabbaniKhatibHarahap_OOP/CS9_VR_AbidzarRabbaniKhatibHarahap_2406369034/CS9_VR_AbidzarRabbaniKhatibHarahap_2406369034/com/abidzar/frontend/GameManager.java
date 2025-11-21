package com.abidzar.frontend;

import com.abidzar.frontend.observers.Observer;
import com.abidzar.frontend.observers.ScoreManager;

public class GameManager {
    private static GameManager instance;

    private final ScoreManager scoreManager;
    private boolean gameActive;

    private GameManager() {
        scoreManager = new ScoreManager();
        gameActive = false;
    }

    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void startGame() {
        gameActive = true;
        scoreManager.setScore(0);
        System.out.println("Game Started!");
    }

    public void stopGame() {
        gameActive = false;
    }

    public void setScore(int newScore) {
        if (gameActive) {
            scoreManager.setScore(newScore);
        }
    }

    public int getScore() {
        return scoreManager.getScore();
    }

    public void addObserver(Observer observer) {
        scoreManager.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        scoreManager.removeObserver(observer);
    }
}
