package com.abidzar.frontend;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.Random;

public class Main extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    private ShapePool pool;
    private ShapeFactory factory;
    private ArrayList<Shape> activeShapes;
    private Random random;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        pool = new ShapePool();
        factory = new ShapeFactory(pool);
        activeShapes = new ArrayList<>();
        random = new Random();

        System.out.println("Press 1 = Circle, 2 = Square, R = Release");
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) createShape("Circle");
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) createShape("Square");
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) releaseAll();

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Shape s : activeShapes) {
            s.render(shapeRenderer);
        }
        shapeRenderer.end();
    }

    private void createShape(String type) {
        if (activeShapes.size() >= 3) {
            System.out.println("Max 3 shapes only.");
            return;
        }

        Shape shape = factory.createShape(type);
        if (shape != null) {
            float x = random.nextFloat() * (Gdx.graphics.getWidth() - 100) + 50;
            float y = random.nextFloat() * (Gdx.graphics.getHeight() - 100) + 50;
            shape.setPosition(x, y);
            activeShapes.add(shape);
            System.out.println(type + " added.");
        }
    }

    private void releaseAll() {
        for (Shape s : activeShapes) {
            pool.release(s);
        }
        activeShapes.clear();
        System.out.println("Shapes released back to pool.");
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
