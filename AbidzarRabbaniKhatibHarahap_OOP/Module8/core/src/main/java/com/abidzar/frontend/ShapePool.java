package com.abidzar.frontend;

import java.util.ArrayList;

public class ShapePool {
    private final ArrayList<Shape> circlePool;
    private final ArrayList<Shape> squarePool;

    public ShapePool() {
        circlePool = new ArrayList<>();
        squarePool = new ArrayList<>();
    }

    public Shape obtain(String type) {
        if (type.equalsIgnoreCase("Circle") && !circlePool.isEmpty()) {
            return circlePool.remove(0);
        } else if (type.equalsIgnoreCase("Square") && !squarePool.isEmpty()) {
            return squarePool.remove(0);
        }
        return null;
    }

    public void release(Shape shape) {
        if (shape == null) return;

        if (shape.getType().equals("Circle")) {
            if (circlePool.size() < 3) circlePool.add(shape);
        } else if (shape.getType().equals("Square")) {
            if (squarePool.size() < 3) squarePool.add(shape);
        }
    }

    public ArrayList<Shape> getPool(String type) {
        if (type.equalsIgnoreCase("Circle")) return circlePool;
        if (type.equalsIgnoreCase("Square")) return squarePool;
        return null;
    }
}
