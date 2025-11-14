package com.abidzar.frontend.observers;

import sun.jvm.hotspot.utilities.Observer;

import java.util.List;

public class ScoreManager implements Subject {

    private final List<Observer> observerList;
    private int score = 0;


    public ScoreManager(List<Observer> observerList) {
        this.observerList = observerList;
    }

    public void addObserver(Observer observer) {

    }

    public void removeObserver(Observer observer) {

    }

    public void notifyObservers(int score) {

    }

    public void setScore(int newScore) {

    }

    public int getScore() {
        return score;
    }


}
