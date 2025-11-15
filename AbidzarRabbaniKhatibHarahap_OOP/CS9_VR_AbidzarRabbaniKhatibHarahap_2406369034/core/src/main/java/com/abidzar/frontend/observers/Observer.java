package com.abidzar.frontend.observers;


public interface Observer {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(int score);

    void update(int score);
}
