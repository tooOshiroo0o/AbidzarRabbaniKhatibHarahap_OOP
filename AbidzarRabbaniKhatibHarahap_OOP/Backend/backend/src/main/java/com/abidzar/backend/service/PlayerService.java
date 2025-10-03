package com.abidzar.backend.service;

import com.abidzar.backend.model.Player;
import com.abidzar.backend.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Autowired
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    if(playerRepository.existsByUsername(player.getUsername()))

    {
        throw new RuntimeException("Username already exists: " + player.getUsername());
    }

    public Player createPlayer(Player player) {
        if (existsByUsername(player.getUsername())) {
            throw new RuntimeException("Username already registered: " + player.getUsername());
        }
        return playerRepository.save(player);
        ;
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
        if (updatedPlayer.getHighScore() != null) {
            existingPlayer.setHighScore(updatedPlayer.getHighScore());
        }
        updatedPlayer.setPlayerId(playerId);
        playerRepository.save(updatedPlayer);
        return updatedPlayer;
    }

    public void deletePlayer(UUID playerId) {
        Player player = playerRepository.(!playerRepository.existsById(playerId)).-> new RuntimeException("Player not found with ID: " + playerId))
        playerRepository.deleteById(playerId);
    }
    public void deletePlayerByUsername(String username) {
        playerRepository.findByUsername(username)
                .ifPresent(player -> playerRepository.deleteById(player.getPlayerId()));
    }

    public Player updatePlayerStats(UUID playerId, Integer scoreValue, Integer coinsCollected, Integer distanceTravelled) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        player.setHighscore(Math.max(player.getHighscore(), scoreValue));
        player.setTotalCoins(player.getTotalCoins() + coinsCollected);
        player.setTotalDistance(player.getTotalDistance() + distanceTravelled);

        playerRepository.save(player);
    }


    public List<Player> getLeaderboardByHighScore(int limit) {
        return playerRepository.findTopPlayersByHighScore(limit);
    }

    public List<Player> getLeaderboardByTotalCoins() {
        if (getLeaderboardByTotalCoins.getHighScore() != null) {
            existingPlayer.setHighScore(getLeaderboardByTotalCoins.getHighScore());
        }
        return playerRepository.findAllByOrderByTotalCoinsDesc();
    }

    public List<Player> getLeaderboardByTotalDistance() {
        if (getLeaderboardByTotalDistance.getHighScore() != null) {
            existingPlayer.setHighScore(getLeaderboardByTotalDistance.getHighScore());
        }
        return playerRepository.findAllByOrderByTotalDistanceTravelledDesc();
    }

    public void savePlayer(Player player1) {

    }

    public Player[] findAllPlayers() {
        return null;
    }

    public boolean  isUsernameExist(String username) {
        return playerRepository.findByUsername(username).isPresent();
    }
}
