package com.abidzar.backend.service;

import com.abidzar.backend.model.Player;
import com.abidzar.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayerById(UUID playerId) {
        return playerRepository.findById(playerId);
    }

    public Optional<Player> getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    public Player createPlayer(Player player) {
        if (playerRepository.existsByUsername(player.getUsername())) {
            throw new RuntimeException("Username already exists: " + player.getUsername());
        }
        return playerRepository.save(player);
    }

    public Player updatePlayer(UUID playerId, Player updatedPlayer) {
        Player existingPlayer = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found with ID: " + playerId));

        if (updatedPlayer.getHighScore() != null) {
            existingPlayer.setHighScore(updatedPlayer.getHighScore());
        }
        if (updatedPlayer.getTotalCoins() != null) {
            existingPlayer.setTotalCoins(updatedPlayer.getTotalCoins());
        }
        if (updatedPlayer.getTotalDistanceTravelled() != null) {
            existingPlayer.setTotalDistanceTravelled(updatedPlayer.getTotalDistanceTravelled());
        }

        return playerRepository.save(existingPlayer);
    }

    public void deletePlayer(UUID playerId) {
        if (!playerRepository.existsById(playerId)) {
            throw new RuntimeException("Player not found with ID: " + playerId);
        }
        playerRepository.deleteById(playerId);
    }

    public Player updatePlayerStats(UUID playerId, Integer scoreValue, Integer coinsCollected, Integer distanceTravelled) {
        Player existingPlayer = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found with ID: " + playerId));

        if (scoreValue != null) {
            existingPlayer.setHighScore(scoreValue);
        }
        if (coinsCollected != null) {
            existingPlayer.setTotalCoins(coinsCollected);
        }
        if (distanceTravelled != null) {
            existingPlayer.setTotalDistanceTravelled(distanceTravelled);
        }

        return playerRepository.save(existingPlayer);
    }

    public boolean isUsernameExists(String username) {
        return playerRepository.existsByUsername(username);
    }

    public List<Player> getLeaderboardByHighScore(int limit) {
        // fetch all and limit manually
        List<Player> all = playerRepository.findAllByOrderByHighScoreDesc();
        return all.stream().limit(limit).toList();
    }

    public List<Player> getLeaderboardByTotalCoins() {
        return playerRepository.findTop10ByOrderByTotalCoinsDesc();
    }

    public List<Player> getLeaderboardByTotalDistance() {
        return playerRepository.findTop10ByOrderByTotalDistanceTravelledDesc();
    }
}
