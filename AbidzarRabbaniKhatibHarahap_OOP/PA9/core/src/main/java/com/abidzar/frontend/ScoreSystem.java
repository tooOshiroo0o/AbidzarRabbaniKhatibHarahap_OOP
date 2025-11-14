package com.abidzar.frontend;

import java.util.ArrayList;

public class ScoreSystem {
    private final ArrayList<ScoreObserver> obs = new ArrayList<>();
    private int score;

    public void registerObserver(ScoreObserver o) {
        obs.add(o);
    }

    public void removeObserver(ScoreObserver o) {
        obs.remove(o);
    }

    private void notifyAllObs() {
        for (ScoreObserver o : obs) o.onScoreUpdate(score);
    }

    public void addScore(int amt) {
        score += amt;
        notifyAllObs();
    }
}
