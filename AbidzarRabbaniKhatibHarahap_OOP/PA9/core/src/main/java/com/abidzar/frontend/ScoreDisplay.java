package com.abidzar.frontend;

public class ScoreDisplay implements ScoreObserver {
    @Override
    public void onScoreUpdate(int newScore) {
        System.out.println("Score: " + newScore);
    }
}
