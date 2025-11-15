package com.abidzar.frontend;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.abidzar.frontend.command.Command;
import com.abidzar.frontend.command.JetpackCommand;
import com.abidzar.frontend.command.RestartCommand;
import com.abidzar.frontend.factories.ObstacleFactory;
import com.abidzar.frontend.observers.ScoreUIObserver;
import com.abidzar.frontend.obstacles.BaseObstacle;
import com.abidzar.frontend.obstacles.HomingMissile;

public class Main extends Game {

    private ShapeRenderer renderer;
    private SpriteBatch batch;

    private Player player;
    private Ground ground;
    private GameManager manager;

    private Background bg;
    private Command jetCmd;
    private Command restartCmd;
    private ScoreUIObserver scoreDisplay;

    private ObstacleFactory factory;
    private float spawnClock;
    private float lastX = 0f;

    private static final float SPAWN_DELAY = 2.5f;
    private static final int SPAWN_COUNT = 1;
    private static final float EXTRA_SPAWN = 300f;
    private static final float MIN_GAP = 200f;
    private static final float GAP_STEP = 250f;

    private OrthographicCamera cam;
    private float camShift = 0.2f;

    private int w, h;
    private int prevScoreMeter = -1;

    @Override
    public void create() {
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();

        manager = GameManager.getInstance();

        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, w, h);

        player = new Player(new Vector2(100, h / 2f));
        ground = new Ground();

        jetCmd = new JetpackCommand(player);
        restartCmd = new RestartCommand(player, manager);

        scoreDisplay = new ScoreUIObserver();
        manager.addObserver(scoreDisplay);

        bg = new Background();

        factory = new ObstacleFactory();
        spawnClock = 0f;

        manager.startGame();
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        step(dt);
        draw();
    }

    private void step(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            jetCmd.execute();
        }

        if (player.isDead()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                restartCmd.execute();
                prevScoreMeter = -1;
            }
            return;
        }

        player.update(dt, false);
        adjustCamera();

        bg.update(cam.position.x);
        ground.update(cam.position.x);
        player.checkBoundaries(ground, h);

        manageObstacles(dt);
        detectHit();

        int meter = (int) player.getDistanceTraveled();
        int saved = manager.getScore();

        if (meter > saved) {
            if (meter != prevScoreMeter) {
                System.out.println("Distance: " + meter + "m");
                prevScoreMeter = meter;
            }
            manager.setScore(meter);
        }
    }

    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        bg.render(batch);
        batch.end();

        renderer.setProjectionMatrix(cam.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        ground.renderShape(renderer);
        player.renderShape(renderer);

        renderer.setColor(Color.RED);
        for (BaseObstacle ob : factory.getAllInUseObstacles()) {
            ob.render(renderer);
        }

        renderer.end();

        scoreDisplay.render(manager.getScore());
    }

    private void adjustCamera() {
        float target = player.getPosition().x + w * camShift;
        cam.position.x = target;
        cam.update();
    }

    private void manageObstacles(float dt) {
        spawnClock += dt;

        if (spawnClock >= SPAWN_DELAY) {
            spawnNew();
            spawnClock = 0f;
        }

        float left = cam.position.x - w / 2f;

        for (BaseObstacle ob : factory.getAllInUseObstacles()) {
            if (ob instanceof HomingMissile) {
                HomingMissile m = (HomingMissile) ob;
                m.setTarget(player);
                m.update(dt);
            }
            if (ob.isOffScreenCamera(left)) {
                factory.releaseObstacle(ob);
            }
        }
    }

    private void spawnNew() {
        float right = cam.position.x + w / 2f;
        float ahead = right + EXTRA_SPAWN;
        float spacing = lastX + MIN_GAP;

        float baseX = Math.max(ahead, spacing);

        for (int i = 0; i < SPAWN_COUNT; i++) {
            float spawnX = baseX + (i * GAP_STEP);
            factory.createRandomObstacle(ground.getTopY(), spawnX, player.getHeight());
            lastX = spawnX;
        }
    }

    private void detectHit() {
        Rectangle hitbox = player.getCollider();

        for (BaseObstacle ob : factory.getAllInUseObstacles()) {
            if (ob.isColliding(hitbox)) {
                System.out.println("========================================================");
                System.out.println("GAME OVER!");
                System.out.println("Press SPACE to restart");
                System.out.println("========================================================");
                player.die();
                return;
            }
        }
    }

    @Override
    public void dispose() {
        renderer.dispose();
        if (batch != null) {
            batch.dispose();
            batch = null;
        }
        factory.releaseAllObstacles();

        if (scoreDisplay != null) scoreDisplay.dispose();
        if (bg != null) bg.dispose();
    }
}
