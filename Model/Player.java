package Model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Player model used by repositories.
 */
public class Player implements ShowDetail {
    private UUID playerId;
    private String username;
    private int highscore;
    private int totalCoins;
    private int totalDistance;
    private LocalDateTime createdAt;

    public Player(String username) {
        this.playerId = UUID.randomUUID();
        this.username = username;
        this.highscore = 0;
        this.totalCoins = 0;
        this.totalDistance = 0;
        this.createdAt = LocalDateTime.now();
    }

    // getters
    public UUID getPlayerId() {
        return playerId;
    }

    public String getUsername() {
        return username;
    }

    public int getHighscore() {
        return highscore;
    }

    public int getTotalCoins() {
        return totalCoins;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // update methods (assignment uses these names)
    public void updateHighScore(int newHighscore) {
        if (newHighscore > this.highscore) {
            this.highscore = newHighscore;
        }
    }

    // alias names requested in main
    public void addCoins(int coins) {
        this.totalCoins += coins;
    }

    public void addDistance(int distance) {
        this.totalDistance += distance;
    }

    // alternative older names (kept in case other code calls them)
    public void updatetotalCoins(int coins) {
        addCoins(coins);
    }

    public void updatetotalDistance(int distance) {
        addDistance(distance);
    }

    @Override
    public void showDetail() {
        System.out.println("Player ID: " + playerId);
        System.out.println("Username: " + username);
        System.out.println("Highscore: " + highscore);
        System.out.println("Total Coins: " + totalCoins);
        System.out.println("Total Distance: " + totalDistance);
        System.out.println("Created At: " + createdAt);
    }

    @Override
    public String toString() {
        return String.format("%s (id=%s) hs=%d coins=%d dist=%d",
                username, playerId, highscore, totalCoins, totalDistance);
    }
}
