package Model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Main {
    public static void main (String[] args) {
        Player player1 = new Player("Agus Buntung");

        Player player2 = new Player("Heri Hermansyah");
        player2.updateHighScore(3200);
        player2.updatetotalCoins(750);
        player2.updatetotalDistance(12000);

        // Model.Score score3 = new Model.Score(player1.getPlayerId(), 1800, 300, 6000);

        player1.updateHighScore(1500);
        player1.updatetotalCoins(250);
        player1.updatetotalDistance(5000);

        //player1.updateHighScore(score3.getValue());
        //player1.updatetotalCoins(score3.getCoinsCollected());
        //player1.updatetotalDistance(score3.getDistance());

        player1.updateHighScore(1800);
        player1.updatetotalCoins(300);
        player1.updatetotalDistance(6000);

        System.out.println("================Model.Player Detail================");
        player1.showDetail();
        System.out.println();
        player2.showDetail();

    }
}

interface ShowDetail {
    void showDetail();
}

class Player implements ShowDetail {
    private UUID playerId;
    private String username;
    private int highscore;
    private int totalCoins;
    private int totalDistance;
    private LocalDateTime createdAt;

    public Player (String username) {
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

    public void updateHighScore (int newHighscore) {
        if (newHighscore > highscore) {
            this.highscore = newHighscore;
        }
    }

    public void updatetotalCoins (int coins) {
        this.totalCoins += coins;
    }

    public void updatetotalDistance (int distance) {
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

class Score implements ShowDetail {
    private UUID scoreId;
    private UUID playerId;
    private Player player;
    private int value;
    private int coinsCollected;
    private int distance;
    private LocalDateTime createdAt;

    public Score (UUID scoreId, UUID playerId) {
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