package com.abidzar.frontend;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.abidzar.frontend.factories.ObstacleFactory;
import com.abidzar.frontend.obstacles.BaseObstacle;
import com.abidzar.frontend.obstacles.HomingMissile;

import java.util.List;

public class Main extends Game {
    private ShapeRenderer shapeRenderer;

    // Game objects
    private Player player;
    private Ground ground;
    private GameManager gameManager;

    // obstacle system
    private ObstacleFactory obstacleFactory;
    private float obstacleSpawnTimer = 0f;
    private float lastObstacleSpawnX = 0f;
    private static final float OBSTACLE_SPAWN_INTERVAL = 2.5f;
    private static final int OBSTACLE_DENSITY = 1;
    private static final float SPAWN_AHEAD_DISTANCE = 300f;
    private static final float MIN_OBSTACLE_GAP = 200f;
    private static final float OBSTACLE_CLUSTER_SPACING = 250f;

    private OrthographicCamera camera;
    private float cameraOffset = 0.2f;
    private int screenWidth;
    private int screenHeight;
    private int lastLoggedScore = -1;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        this.gameManager = GameManager.getInstance();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);

        player = new Player(new Vector2(100, screenHeight / 2f));
        ground = new Ground();

        obstacleFactory = new ObstacleFactory();
        obstacleSpawnTimer = 0f;

        gameManager.startGame();
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        update(delta);
        renderGame(shapeRenderer);
    }

    private void update(float delta) {
        boolean isFlying = Gdx.input.isKeyPressed(Input.Keys.SPACE);

         if (player.isDead() && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            resetGame();
            return;
        }

        player.update(delta, isFlying);
        updateCamera(delta);
        ground.update(camera.position.x);
        player.checkBoundaries(ground, screenHeight);

        updateObstacles(delta);
        checkCollisions();

        int currentScoreMeters = (int) player.getDistanceTraveled();
        int previousScoreMeters = gameManager.getScore();
        if (currentScoreMeters > previousScoreMeters) {
            if (currentScoreMeters != lastLoggedScore) {
                System.out.println("Distance: " + currentScoreMeters + "m");
                lastLoggedScore = currentScoreMeters;
            }
            gameManager.setScore(currentScoreMeters);
        }
    }

    private void renderGame(ShapeRenderer shapeRenderer) {
        ScreenUtils.clear(Color.SKY);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.BROWN);
        shapeRenderer.rect(camera.position.x - screenWidth / 2f, 0, screenWidth, ground.getTopY());

        shapeRenderer.setColor(Color.GREEN);
        Vector2 p = player.getPosition();
        shapeRenderer.rect(p.x, p.y, player.getWidth(), player.getHeight());

        shapeRenderer.setColor(Color.RED);
        List<BaseObstacle> obstacles = obstacleFactory.getAllInUseObstacles();
        for (BaseObstacle o : obstacles) {
            o.render(shapeRenderer);
        }

        shapeRenderer.end();
    }

    private void updateCamera(float delta) {
        float cameraFocus = player.getPosition().x + screenWidth * cameraOffset;
        camera.position.x = cameraFocus;
        camera.update();
    }

    private void updateObstacles(float delta) {
        obstacleSpawnTimer += delta;
        if (obstacleSpawnTimer >= OBSTACLE_SPAWN_INTERVAL) {
            spawnObstacle();
            obstacleSpawnTimer = 0f;
        }

        float cameraLeftEdge = camera.position.x - screenWidth / 2f;

        List<BaseObstacle> obstacles = obstacleFactory.getAllInUseObstacles();
        for (BaseObstacle o : obstacles) {
            if (o instanceof HomingMissile) {
                HomingMissile hm = (HomingMissile) o;
                hm.setTarget(player);
                hm.update(delta);
            }
            if (o.isOffScreenCamera(cameraLeftEdge)) {
                obstacleFactory.releaseObstacle(o);
            }
        }
    }

    private void spawnObstacle() {
        float cameraRightEdge = camera.position.x + screenWidth / 2f;
        float spawnAheadOfCamera = cameraRightEdge + SPAWN_AHEAD_DISTANCE;
        float spawnAfterLastObstacle = lastObstacleSpawnX + MIN_OBSTACLE_GAP;
        float baseSpawnX = Math.max(spawnAheadOfCamera, spawnAfterLastObstacle);

        for (int i = 0; i < OBSTACLE_DENSITY; i++) {
            float spawnX = baseSpawnX + i * OBSTACLE_CLUSTER_SPACING;
            BaseObstacle obstacle = obstacleFactory.createRandomObstacle(ground.getTopY(), spawnX, player.getHeight());
            lastObstacleSpawnX = spawnX;
        }
    }

    private void checkCollisions() {
        Rectangle playerCollider = player.getCollider();
        List<BaseObstacle> obstacles = obstacleFactory.getAllInUseObstacles();
        for (BaseObstacle o : obstacles) {
            if (o.isColliding(playerCollider)) {
                System.out.println("GAME OVER - Press SPACE to restart");
                player.die();
                return;
            }
        }
    }

    private void resetGame() {
        player.reset();
        obstacleFactory.releaseAllObstacles();
        obstacleSpawnTimer = 0f;
        lastObstacleSpawnX = 0f;
        camera.position.x = player.getPosition().x + screenWidth * cameraOffset;
        camera.update();
        gameManager.setScore(0);
        lastLoggedScore = -1;
        System.out.println("Game reset!");
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        obstacleFactory.releaseAllObstacles();
    }
}
