package Model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Score implements ShowDetail {
    private UUID scoreId;
    private UUID playerId;
    private int value;
    private int coinsCollected;
    private int distance;
    private LocalDateTime createdAt;

    // Full constructor (assigns scoreId and createdAt)
    public Score(UUID scoreId, UUID playerId, int value, int coinsCollected, int distance) {
        this.scoreId = scoreId;
        this.playerId = playerId;
        this.value = value;
        this.coinsCollected = coinsCollected;
        this.distance = distance;
        this.createdAt = LocalDateTime.now();
    }

    // Convenience constructor (generate scoreId automatically)
    public Score(UUID playerId, int value, int coinsCollected, int distance) {
        this(UUID.randomUUID(), playerId, value, coinsCollected, distance);
    }

    // getters & setter for scoreId (repo might assign id)
    public UUID getScoreId() {
        return scoreId;
    }

    public void setScoreId(UUID scoreId) {
        this.scoreId = scoreId;
    }

    public UUID getPlayerId() {
        return playerId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public void showDetail() {
        System.out.println("Score ID: " + scoreId);
        System.out.println("Player ID: " + playerId);
        System.out.println("Value : " + value);
        System.out.println("Coins: " + coinsCollected);
        System.out.println("Distance: " + distance);
        System.out.println("Created At: " + createdAt);
    }

    @Override
    public String toString() {
        return "Score ID: " + scoreId + "\n" +
                "Player ID: " + playerId + "\n" +
                "Score Value: " + value + "\n" +
                "Coins Collected: " + coinsCollected + "\n" +
                "Distance: " + distance + "\n" +
                "Created At: " + createdAt + "\n";
    }



}
