package com.abidzar.backend.service;

<<<<<<< HEAD
import com.abidzar.backend.model.Player;
=======
>>>>>>> dcd5b0f617b3a3fa3659afd34a6a0a6b492c3130
import com.abidzar.backend.model.Score;
import com.abidzar.backend.repository.ScoreRepository;
import com.abidzar.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Transactional
    public Score createScore(Score score) {
<<<<<<< HEAD
        UUID playerId = score.getPlayerId();
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found with ID: " + playerId));
        score.setPlayer(player);
=======
        UUID playerId = score.getPlayer().getPlayerId();
        if (!playerRepository.existsById(playerId)) {
            throw new RuntimeException("Player not found with ID: " + playerId);
        }
>>>>>>> dcd5b0f617b3a3fa3659afd34a6a0a6b492c3130
        return scoreRepository.save(score);
    }

    public Optional<Score> getScoreById(UUID scoreId) {
        return scoreRepository.findById(scoreId);
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    public List<Score> getScoresByPlayerId(UUID playerId) {
        return scoreRepository.findByPlayer_PlayerId(playerId);
    }

    public List<Score> getScoresByPlayerIdOrderByValue(UUID playerId) {
        return scoreRepository.findByPlayer_PlayerIdOrderByValueDesc(playerId);
    }

    public List<Score> getLeaderboard(int limit) {
        return scoreRepository.findTopScores(PageRequest.of(0, limit));
    }

    public Optional<Score> getHighestScoreByPlayerId(UUID playerId) {
        List<Score> scores = scoreRepository.findHighestScoreByPlayerId(playerId);
        return scores.isEmpty() ? Optional.empty() : Optional.of(scores.get(0));
    }

    public List<Score> getScoresAboveValue(Integer minValue) {
        return scoreRepository.findByValueGreaterThan(minValue);
    }

    public List<Score> getRecentScores() {
        return scoreRepository.findAllByOrderByCreatedAtDesc();
    }

    public Integer getTotalCoinsByPlayerId(UUID playerId) {
        Integer total = scoreRepository.getTotalCoinsByPlayerId(playerId);
        return total != null ? total : 0;
    }

    public Integer getTotalDistanceByPlayerId(UUID playerId) {
        Integer total = scoreRepository.getTotalDistanceByPlayerId(playerId);
        return total != null ? total : 0;
    }

    @Transactional
    public Score updateScore(UUID scoreId, Score updatedScore) {
        Score existing = scoreRepository.findById(scoreId)
                .orElseThrow(() -> new RuntimeException("Score not found with ID: " + scoreId));
<<<<<<< HEAD
=======

>>>>>>> dcd5b0f617b3a3fa3659afd34a6a0a6b492c3130
        if (updatedScore.getValue() != null) {
            existing.setValue(updatedScore.getValue());
        }
        if (updatedScore.getCoinsCollected() != null) {
            existing.setCoinsCollected(updatedScore.getCoinsCollected());
        }
        if (updatedScore.getDistanceTravelled() != null) {
            existing.setDistanceTravelled(updatedScore.getDistanceTravelled());
        }
<<<<<<< HEAD
=======

>>>>>>> dcd5b0f617b3a3fa3659afd34a6a0a6b492c3130
        return scoreRepository.save(existing);
    }

    @Transactional
    public void deleteScore(UUID scoreId) {
        if (!scoreRepository.existsById(scoreId)) {
            throw new RuntimeException("Score not found with ID: " + scoreId);
        }
        scoreRepository.deleteById(scoreId);
    }

    @Transactional
    public void deleteScoresByPlayerId(UUID playerId) {
        List<Score> scores = scoreRepository.findByPlayer_PlayerId(playerId);
        scoreRepository.deleteAll(scores);
    }

    public Optional<Score> findById(UUID scoreId) {
        return getScoreById(scoreId);
    }
<<<<<<< HEAD
}
=======

}
>>>>>>> dcd5b0f617b3a3fa3659afd34a6a0a6b492c3130
