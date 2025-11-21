package com.abidzar.frontend.strategies;

import java.util.HashMap;
import java.util.Map;

public class EasyDifficultyStrategy implements DifficultyStrategy {

    @Override
    public Map<String, Integer> getObstacleWeights() {
        Map<String, Integer> w = new HashMap<>();
        w.put("VerticalLaser", 1);
        w.put("HorizontalLaser", 1);
        return w;
    }

    @Override
    public float getSpawnInterval() {
        return 2.0f;
    }

    @Override
    public int getDensity() {
        return 1;
    }

    @Override
    public float getMinGap() {
        return 300f;
    }
}
