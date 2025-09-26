package Model;

import Repository.PlayerRepository;
import Repository.ScoreRepository;

import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        PlayerRepository playerRepo = new PlayerRepository();
        ScoreRepository scoreRepo = new ScoreRepository();

        // create players
        Player player1 = new Player("Stelle");
        Player player2 = new Player("gamerLooxmaxxing");
        Player player3 = new Player("Stelle123");
        Player player4 = new Player("Banananana");

        // Create and save player initial stats (as assignment)
        player1.updateHighScore(1500);
        player1.addCoins(250);
        player1.addDistance(5000);

        player2.updateHighScore(3200);
        player2.addCoins(750);
        player2.addDistance(12000);

        // create/update stats for player3 and player4 (example)
        player3.updateHighScore(2400);
        player3.addCoins(100);
        player3.addDistance(2400);

        player4.updateHighScore(1800);
        player4.addCoins(300);
        player4.addDistance(6000);

        // save players to repository
        playerRepo.save(player1);
        playerRepo.save(player2);
        playerRepo.save(player3);
        playerRepo.save(player4);

        // Create and save scores per assignment (10 scores)
        // Score 1: player2 -> 1500, coins 250, distance 5000
        scoreRepo.save(new Score(player2.getPlayerId(), 1500, 250, 5000));

        // Score 2: player4 -> 3200, coins 750, distance 12000
        scoreRepo.save(new Score(player4.getPlayerId(), 3200, 750, 12000));

        // Score 3: player1 -> 4000, coins 400, distance 32000
        scoreRepo.save(new Score(player1.getPlayerId(), 4000, 400, 32000));

        // Score 4: player4 -> 1800, coins 300, distance 6000
        scoreRepo.save(new Score(player4.getPlayerId(), 1800, 300, 6000));

        // Score 5: player3 -> 2400, coins 240, distance 2400
        scoreRepo.save(new Score(player3.getPlayerId(), 2400, 240, 2400));

        // Score 6: player2 -> 6200, coins 320, distance 5000
        scoreRepo.save(new Score(player2.getPlayerId(), 6200, 320, 5000));

        // Score 7: player4 -> 1800, coins 60, distance 1200
        scoreRepo.save(new Score(player4.getPlayerId(), 1800, 60, 1200));

        // Score 8: player1 -> 2100, coins 200, distance 7000
        scoreRepo.save(new Score(player1.getPlayerId(), 2100, 200, 7000));

        // Score 9: player1 -> 8000, coins 720, distance 6200
        scoreRepo.save(new Score(player1.getPlayerId(), 8000, 720, 6200));

        // Score 10: player3 -> 1900, coins 210, distance 4200
        scoreRepo.save(new Score(player3.getPlayerId(), 1900, 210, 4200));

        // === outputs requested by assignment ===
        System.out.println("=== TESTING CS3 ===");

        System.out.println("\nFind player by ID (show details of player3):");
        UUID p3id = player3.getPlayerId();
        playerRepo.findById(p3id).ifPresent(Player::showDetail);

        System.out.println("\nAll players:");
        List<Player> all = playerRepo.findAll();
        all.forEach(p -> {
            System.out.println(" - " + p);
        });

        System.out.println("\nSort all player based on highscore (desc):");
        List<Player> top = playerRepo.findTopPlayersByHighScore(10);
        top.forEach(p -> System.out.println(p.getUsername() + " -> " + p.getHighscore()));

        System.out.println("\nScores for player1 (sorted by value desc):");
        List<Score> player1Scores = scoreRepo.findByPlayerIdOrderByValueDesc(player1.getPlayerId());
        player1Scores.forEach(s -> System.out.println(" - " + s));

        System.out.println("\nHighest score for player1:");
        scoreRepo.findHighestScoreByPlayerId(player1.getPlayerId())
                .ifPresent(s -> System.out.println(s.getValue()));

        System.out.println("\nTotal coins aggregated from Score entries for player1:");
        System.out.println(scoreRepo.getTotalCoinsByPlayerId(player1.getPlayerId()));

        System.out.println("\nTotal distance aggregated from Score entries for player1:");
        System.out.println(scoreRepo.getTotalDistanceByPlayerId(player1.getPlayerId()));
    }
}
