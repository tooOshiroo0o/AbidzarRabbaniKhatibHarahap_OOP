package com.abidzar.frontend.observers;

import sun.jvm.hotspot.utilities.Observer;

public interface Subject {

    public default void addObserver(Observer observer) {

    }

    public default void removeObserver(Observer observer) {

    }

    public default void notifyObservers(int score) {

    }
}
