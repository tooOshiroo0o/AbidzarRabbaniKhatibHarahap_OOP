package Model;

import Model.Player;
import Model.Score;
import Repository.PlayerRepository;
import Repository.ScoreRepository;
import Service.PlayerService;
import Service.ScoreService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        PlayerRepository playerRepository = new PlayerRepository();
        ScoreRepository scoreRepository = new ScoreRepository();
        PlayerService playerService = new PlayerService(playerRepository);
        ScoreService scoreService = new ScoreService(scoreRepository, playerRepository, playerService);

        System.out.println("=== CS 4 ===\n");


        Player player1 = new Player("NanaBanana");
        Player player2 = new Player("Yingko");
        Player player3 = new Player("LegdontWork");


        playerService.createPlayer(player1);
        playerService.createPlayer(player2);
        playerService.createPlayer(player3);

        System.out.println("Players created\n");

        System.out.println("All Players:");
        for (Player p : playerService.getAllPlayers()) {
            System.out.println(p);
        }
        System.out.println();


        Score s1 = new Score(player1.getPlayerId(), 1500, 50, 3000);
        Score s2 = new Score(player1.getPlayerId(), 2000, 75, 4500);
        Score s3 = new Score(player2.getPlayerId(), 1800, 60, 3500);
        Score s4 = new Score(player3.getPlayerId(), 1200, 40, 2500);
        Score s5 = new Score(player3.getPlayerId(), 2500, 90, 5000);

        scoreService.createScore(s1);
        scoreService.createScore(s2);
        scoreService.createScore(s3);
        scoreService.createScore(s4);
        scoreService.createScore(s5);

        System.out.println("Scores created!\n");

        System.out.println("Player Score:");
        for (Player p : playerService.getAllPlayers()) {
            System.out.println(p);
        }
        System.out.println();

        System.out.println("Top 2 players by high score:");
        List<Player> top2 = playerService.getLeaderboardByHighScore(2);
        for (Player p : top2) {
            System.out.println(p);
        }
        System.out.println();


        System.out.println("All scores for " + player1.getUsername() + ":");
        List<Score> player1Scores = scoreService.getScoresByPlayerId(player1.getPlayerId());
        for (Score sc : player1Scores) {
            System.out.println(sc);
        }
        System.out.println();


        System.out.println("Top 3 scores overall:");
        List<Score> top3Scores = scoreService.getLeaderboard(3);
        for (Score sc : top3Scores) {
            System.out.println(sc);
        }
        System.out.println();


        System.out.println("Searching for player 'NanaBanana':");
        Optional<Player> searchResult = playerService.getPlayerByUsername("NanaBanana");
        if (searchResult.isPresent()) {
            System.out.println("Found: " + searchResult.get());
        } else {
            System.out.println("Player not found!");
        }
        System.out.println();

        System.out.println("Totals for " + player3.getUsername() + ":");
        int totalCoins = scoreService.getTotalCoinsByPlayerId(player3.getPlayerId());
        int totalDistance = scoreService.getTotalDistanceByPlayerId(player3.getPlayerId());
        System.out.println("Total coins = " + totalCoins);
        System.out.println("Total distance = " + totalDistance);
        System.out.println();

        System.out.println("Recent scores (ordered by creation time):");
        List<Score> recentScores = scoreService.getRecentScores();
        for (Score sc : recentScores) {
            System.out.println(sc);
        }
    }
}
