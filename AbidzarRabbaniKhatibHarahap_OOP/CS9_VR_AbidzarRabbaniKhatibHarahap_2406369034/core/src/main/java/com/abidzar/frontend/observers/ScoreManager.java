package com.abidzar.frontend.observers;


import java.util.ArrayList;
import java.util.List;


public class ScoreManager implements Observer {


    private List<Observer> observers;
    private int score;


    public ScoreManager() {
        observers = new ArrayList<>();
        score = 0;
    }


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }


    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }


    @Override
    public void notifyObservers(int score) {
        for (Observer o : observers) {
            o.update(score);
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

    @Override
    public void update(int score) {

    }
}
