package Service;

import Model.Score;
import Repository.ScoreRepository;
import Repository.PlayerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final PlayerRepository playerRepository;
    private final PlayerService playerService;

    public ScoreService(ScoreRepository scoreRepository, PlayerRepository playerRepository, PlayerService playerService) {
        this.scoreRepository = scoreRepository;
        this.playerRepository = playerRepository;
        this.playerService = playerService;
    }

    public Score createScore(Score score) {
        UUID playerId = score.getPlayerId();
        if (playerRepository.findById(playerId).isEmpty()) {
            throw new RuntimeException("Player not found with id: " + playerId);
        }
        scoreRepository.save(score);
        playerService.updatePlayerStats(playerId, score.getValue(), score.getCoinsCollected(), score.getDistance());
        return score;
    }

    public Optional<Score> getScoreById(UUID scoreId) {
        return scoreRepository.findById(scoreId);
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    public List<Score> getScoresByPlayerId(UUID playerId) {
        return scoreRepository.findByPlayerId(playerId);
    }

    public List<Score> getScoresByPlayerIdOrderByValue(UUID playerId) {
        return scoreRepository.findByPlayerIdOrderByValueDesc(playerId);
    }

    public List<Score> getLeaderboard(int limit) {
        return scoreRepository.findTopScores(limit);
    }

    public Optional<Score> getHighestScoreByPlayerId(UUID playerId) {
        return scoreRepository.findHighestScoreByPlayerId(playerId);
    }

    public List<Score> getScoresAboveValue(int minValue) {
        return scoreRepository.findByValueGreaterThan(minValue);
    }

    public List<Score> getRecentScores() {
        return scoreRepository.findAllByOrderByCreatedAtDesc();
    }

    public Integer getTotalCoinsByPlayerId(UUID playerId) {
        return scoreRepository.getTotalCoinsByPlayerId(playerId);
    }

    public Integer getTotalDistanceByPlayerId(UUID playerId) {
        return scoreRepository.getTotalDistanceByPlayerId(playerId);
    }

    public Score updateScore(UUID scoreId, Score updatedScore) {
        if (scoreRepository.findById(scoreId).isEmpty()) {
            throw new RuntimeException("Score not found");
        }
        updatedScore.setScoreId(scoreId);
        scoreRepository.save(updatedScore);
        return updatedScore;
    }

    public void deleteScore(UUID scoreId) {
        scoreRepository.deleteById(scoreId);
    }

    public void deleteScoresByPlayerId(UUID playerId) {
        scoreRepository.findByPlayerId(playerId)
                .forEach(score -> scoreRepository.deleteById(score.getScoreId()));
    }

    public void saveScore(Score s1) {

    }

    public int getTotalCoinsByPlayer(UUID playerId) {
        return  scoreRepository.getTotalCoinsByPlayerId(playerId);
    }
}
