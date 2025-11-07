package com.abidzar.frontend.lwjgl3;

import com.abidzar.frontend.MyGdxGame;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Lwjgl3Launcher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Simple Demo Game");
        config.setWindowedMode(1000, 800);
        config.useVsync(true);
        new Lwjgl3Application(new MyGdxGame(), config);
    }
}
