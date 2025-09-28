package Service;

import Model.Player;
import Repository.PlayerRepository;

import java.util.*;

public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public boolean existsByUsername(String username) {
        return playerRepository.findByUsername(username).isPresent();
    }

    public Player createPlayer(Player player) {
        if (existsByUsername(player.getUsername())) {
            throw new RuntimeException("Username already registered: " + player.getUsername());
        }
        playerRepository.save(player);
        return player;
    }

    public Optional<Player> getPlayerById(UUID playerId) {
        return playerRepository.findById(playerId);
    }

    public Optional<Player> getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player updatePlayer(UUID playerId, Player updatedPlayer) {
        Player existing = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        if (!existing.getUsername().equals(updatedPlayer.getUsername())
                && existsByUsername(updatedPlayer.getUsername())) {
            throw new RuntimeException("Username already exists for another player");
        }
        updatedPlayer.setPlayerId(playerId);
        playerRepository.save(updatedPlayer);
        return updatedPlayer;
    }

    public void deletePlayer(UUID playerId) {
        playerRepository.deleteById(playerId);
    }

    public void deletePlayerByUsername(String username) {
        playerRepository.findByUsername(username)
                .ifPresent(player -> playerRepository.deleteById(player.getPlayerId()));
    }

    public void updatePlayerStats(UUID playerId, int scoreValue, int coinsCollected, int distanceTravelled) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        player.setHighscore(Math.max(player.getHighscore(), scoreValue));
        player.setTotalCoins(player.getTotalCoins() + coinsCollected);
        player.setTotalDistance(player.getTotalDistance() + distanceTravelled);

        playerRepository.save(player); // ✅ this compiles now
    }



    public List<Player> getLeaderboardByHighScore(int limit) {
        return playerRepository.findTopPlayersByHighScore(limit);
    }

    public List<Player> getLeaderboardByTotalCoins() {
        return playerRepository.findAllByOrderByTotalCoinsDesc();
    }

    public List<Player> getLeaderboardByTotalDistance() {
        return playerRepository.findAllByOrderByTotalDistanceTravelledDesc();
    }

    public void savePlayer(Player player1) {

    }

    public Player[] findAllPlayers() {
        return null;
    }
}
