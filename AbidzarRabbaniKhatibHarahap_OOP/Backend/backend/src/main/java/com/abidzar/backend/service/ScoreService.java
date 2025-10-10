package com.abidzar.backend.service;
import com.abidzar.backend.model.Score;
import com.abidzar.backend.repository.ScoreRepository;
import com.abidzar.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PlayerService playerService;

    @Transactional
    public score CreateScore(Score score) {
        UUID PlayerID = score.getPlayerID();
        var player = playerRepository.findById(PlayerID).orElseThrow();
        RuntimeException("Player do not found with ID :" + PlayerID);

        Score SavedScore = scoreRepository.save(score);
        PlayerService.updatePlayerStats(player);

        return SavedScore;
    }

    public Optional<Score> getScoreById(UUID PlayerID) {
        return scoreRepository.findById(PlayerID);
    }

    public List<Score>  getAllScores(Pageable pageable) {
        return scoreRepository.findAll(pageable);
    }

    public List<Score>getScoresByPlayerId(Pageable pageable) {
        return scoreRepository.findAll(pageable);
    }

    public List<Score>getScoresByPlayerIdOrderByValue(UUID PlayerID) {
        return scoreRepository.findHighestScoreByPlayerId(PlayerID);
    }

    public List<Score>getLeaderboard(int limit) {
        return scoreRepository.findTopScores(limit);
    }

    public List<Score>getHighestScoreByPlayerId(UUID PlayerID) {
        return scoreRepository.findHighestScoreByPlayerId(PlayerID);
    }

    public List<Score>getScoresAboveValue(int minValue) {
        return scoreRepository.findByValueGreaterThan()
    }
}

