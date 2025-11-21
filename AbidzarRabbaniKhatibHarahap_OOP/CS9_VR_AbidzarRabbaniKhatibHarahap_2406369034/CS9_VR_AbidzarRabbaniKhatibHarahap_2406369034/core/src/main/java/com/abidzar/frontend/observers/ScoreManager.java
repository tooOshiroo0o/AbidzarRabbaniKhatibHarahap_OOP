package com.abidzar.frontend.observers;

import java.util.ArrayList;
import java.util.List;

public class ScoreManager implements Subject {
    private final List<Observer> observers;
    private int score = 0;

    public ScoreManager() {
        observers = new ArrayList<>();
        score = 0;
    }

    @Override
    public void addObserver(Observer observer) {
        if (observer == null) return;
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int score) {
        for (Observer observer : new ArrayList<>(observers)) {
            observer.update(score);
        }
    }

    public void setScore(int newScore) {
        if (newScore != score) {
            score = newScore;
            notifyObservers(score);
        }
    }

    public int getScore() {
        return score;
    }
}
