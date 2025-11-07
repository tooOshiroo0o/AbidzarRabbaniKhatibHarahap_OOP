package com.abidzar.backend.repository;

import com.abidzar.backend.model.Score;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScoreRepository extends JpaRepository<Score, UUID> {

    @Query("SELECT s FROM Score s ORDER BY s.value DESC")
    List<Score> findTopScores(Pageable pageable);

    List<Score> findByPlayer_PlayerId(UUID playerId);

    List<Score> findByPlayer_PlayerIdOrderByValueDesc(UUID playerId);

    @Query("SELECT s FROM Score s WHERE s.player.playerId = :playerId ORDER BY s.value DESC")
    List<Score> findHighestScoreByPlayerId(@Param("playerId") UUID playerId);

    @Query("SELECT COALESCE(SUM(s.coinsCollected), 0) FROM Score s WHERE s.player.playerId = :playerId")
    Integer getTotalCoinsByPlayerId(@Param("playerId") UUID playerId);

    @Query("SELECT COALESCE(SUM(s.distanceTravelled), 0) FROM Score s WHERE s.player.playerId = :playerId")
    Integer getTotalDistanceByPlayerId(@Param("playerId") UUID playerId);

    List<Score> findByValueGreaterThan(Integer minValue);

    @Query("SELECT s FROM Score s ORDER BY s.createdAt DESC")
    List<Score> findRecentScores(Pageable pageable);

    void deleteByPlayer_PlayerId(UUID playerId);

    List<Score> findAllByOrderByCreatedAtDesc();
}
