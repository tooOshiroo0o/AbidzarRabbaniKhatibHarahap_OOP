package com.abidzar.frontend;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.HashMap;
import java.util.Map;

public class Main extends ApplicationAdapter {

    private ShapeRenderer sr;
    private Player player;
    private ScoreSystem scoreSys;
    private ScoreDisplay scoreDisp;
    private Map<Integer, Command> cmdMap;

    @Override
    public void create() {
        sr = new ShapeRenderer();
        player = new Player(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        scoreSys = new ScoreSystem();
        scoreDisp = new ScoreDisplay();
        scoreSys.registerObserver(scoreDisp);

        cmdMap = new HashMap<>();
        cmdMap.put(Input.Keys.W, new MoveCommand(player, 0, 5));
        cmdMap.put(Input.Keys.A, new MoveCommand(player, -5, 0));
        cmdMap.put(Input.Keys.S, new MoveCommand(player, 0, -5));
        cmdMap.put(Input.Keys.D, new MoveCommand(player, 5, 0));

        System.out.println("Use W A S D to move, SPACE to add score");
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) cmdMap.get(Input.Keys.W).execute();
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) cmdMap.get(Input.Keys.A).execute();
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) cmdMap.get(Input.Keys.S).execute();
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) cmdMap.get(Input.Keys.D).execute();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) scoreSys.addScore(10);

        ScreenUtils.clear(0.1f, 0.1f, 0.2f, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(1, 1, 0, 1);
        sr.circle(player.getPosition().x, player.getPosition().y, 20);
        sr.end();
    }

    @Override
    public void dispose() {
        sr.dispose();
    }
}
