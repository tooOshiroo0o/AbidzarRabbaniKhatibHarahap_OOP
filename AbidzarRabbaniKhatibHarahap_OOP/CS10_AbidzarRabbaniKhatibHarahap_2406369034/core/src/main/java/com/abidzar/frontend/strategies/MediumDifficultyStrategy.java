package com.abidzar.frontend.strategies;

import java.util.HashMap;
import java.util.Map;

public class MediumDifficultyStrategy implements DifficultyStrategy {

    @Override
    public Map<String, Integer> getObstacleWeights() {
        Map<String, Integer> w = new HashMap<>();
        w.put("VerticalLaser", 2);
        w.put("HorizontalLaser", 2);
        w.put("HomingMissile", 1);
        return w;
    }

    @Override
    public float getSpawnInterval() {
        return 1.25f;
    }

    @Override
    public int getDensity() {
        return 2;
    }

    @Override
    public float getMinGap() {
        return 250f;
    }
}
