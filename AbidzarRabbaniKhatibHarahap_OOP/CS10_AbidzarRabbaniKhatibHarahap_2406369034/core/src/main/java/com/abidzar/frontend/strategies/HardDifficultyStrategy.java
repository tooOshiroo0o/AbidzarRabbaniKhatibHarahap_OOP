package com.abidzar.frontend.strategies;

import java.util.HashMap;
import java.util.Map;

public class HardDifficultyStrategy implements DifficultyStrategy {

    @Override
    public Map<String, Integer> getObstacleWeights() {
        Map<String, Integer> w = new HashMap<>();
        w.put("VerticalLaser", 3);
        w.put("HorizontalLaser", 3);
        w.put("HomingMissile", 4);
        return w;
    }

    @Override
    public float getSpawnInterval() {
        return 0.9f;
    }

    @Override
    public int getDensity() {
        return 3;
    }

    @Override
    public float getMinGap() {
        return 200f;
    }
}
