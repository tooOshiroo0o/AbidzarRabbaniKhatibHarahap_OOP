package Model;

import java.time.LocalDateTime;
import java.util.UUID;

class Score implements ShowDetail {
    private UUID scoreId;
    private UUID playerId;
    private Player player;
    private int value;
    private int coinsCollected;
    private int distance;
    private LocalDateTime createdAt;

    public Score(UUID scoreId, UUID playerId) {
        this.scoreId = scoreId;
        this.playerId = playerId;
        this.value = value;
        this.coinsCollected = coinsCollected;
        this.distance = distance;
        this.createdAt = LocalDateTime.now();
    }

    public int getValue() {
        return value;
    }

    public int getCoinsCollected() {
        return coinsCollected;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public void showDetail() {
        System.out.println("Model.Score ID: " + scoreId);
        System.out.println("Model.Player ID: " + playerId);
        System.out.println("Model.Score Value : " + value);
        System.out.println("Total Coins: " + coinsCollected);
        System.out.println("Total Distance: " + distance);
        System.out.println("Created At: " + createdAt);
    }
}
