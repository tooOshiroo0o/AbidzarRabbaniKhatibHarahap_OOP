package Repository;

import Model.Score;

import java.util.*;
import java.util.stream.Collectors;

public class ScoreRepository extends BaseRepository<Score, UUID> {

    @Override
    public void save(Score score) {
        UUID id = getId(score);
        if (id == null) {
            UUID newId = UUID.randomUUID();
            score.setScoreId(newId);
            id = newId;
        }

        if (map.containsKey(id)) {
            for (int i = 0; i < list.size(); i++) {
                Score s = list.get(i);
                if (s.getScoreId().equals(id)) {
                    list.set(i, score);
                    break;
                }
            }
        } else {
            list.add(score);
        }
        map.put(id, score);
    }

    @Override
    protected UUID getId(Score entity) {
        return entity.getScoreId();
    }

    public List<Score> findByPlayerId(UUID playerId) {
        return map.values().stream()
                .filter(s -> s.getPlayerId().equals(playerId))
                .collect(Collectors.toList());
    }

    public List<Score> findByPlayerIdOrderByValueDesc(UUID playerId) {
        return map.values().stream()
                .filter(s -> s.getPlayerId().equals(playerId))
                .sorted(Comparator.comparingInt(Score::getValue).reversed())
                .collect(Collectors.toList());
    }

    public List<Score> findTopScores(int limit) {
        return map.values().stream()
                .sorted(Comparator.comparingInt(Score::getValue).reversed())
                .limit(Math.max(0, limit))
                .collect(Collectors.toList());
    }

    public Optional<Score> findHighestScoreByPlayerId(UUID playerId) {
        return map.values().stream()
                .filter(s -> s.getPlayerId().equals(playerId))
                .max(Comparator.comparingInt(Score::getValue));
    }

    public List<Score> findByValueGreaterThan(int minValue) {
        return map.values().stream()
                .filter(s -> s.getValue() > minValue)
                .collect(Collectors.toList());
    }

    public List<Score> findAllByOrderByCreatedAtDesc() {
        return map.values().stream()
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public Integer getTotalCoinsByPlayerId(UUID playerId) {
        return map.values().stream()
                .filter(s -> s.getPlayerId().equals(playerId))
                .mapToInt(Score::getCoinsCollected)
                .sum();
    }

    public Integer getTotalDistanceByPlayerId(UUID playerId) {
        return map.values().stream()
                .filter(s -> s.getPlayerId().equals(playerId))
                .mapToInt(Score::getDistance)
                .sum();
    }


}