package Model;

import java.time.LocalDateTime;
import java.util.UUID;

class Player implements ShowDetail {
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

    public UUID getPlayerId() {
        return playerId;
    }

    public void updateHighScore(int newHighscore) {
        if (newHighscore > highscore) {
            this.highscore = newHighscore;
        }
    }

    public void updatetotalCoins(int coins) {
        this.totalCoins += coins;
    }

    public void updatetotalDistance(int distance) {
        this.totalDistance += distance;
    }

    @Override
    public void showDetail() {
        System.out.println("Model.Player ID: " + playerId);
        System.out.println("Username: " + username);
        System.out.println("Highscore: " + highscore);
        System.out.println("Total Coins: " + totalCoins);
        System.out.println("Total Distance: " + totalDistance);
        System.out.println("Created At: " + createdAt);
    }

}
