package Repository;

import Model.Player;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerRepository extends BaseRepository<Player, UUID> {

    @Override
    public void save(Player player) {
        UUID id = getId(player);
        if (id == null) {
            throw new IllegalStateException("Player ID is null");
        }

        if (map.containsKey(id)) {
            // replace existing in list
            for (int i = 0; i < list.size(); i++) {
                Player p = list.get(i);
                if (p.getPlayerId().equals(id)) {
                    list.set(i, player);
                    break;
                }
            }
        } else {
            list.add(player);
        }
        map.put(id, player);
    }

    @Override
    protected UUID getId(Player entity) {
        return entity.getPlayerId();
    }

    public Optional<Player> findByUsername(String username) {
        return map.values().stream()
                .filter(p -> p.getUsername() != null && p.getUsername().equals(username))
                .findFirst();
    }

    public List<Player> findTopPlayersByHighScore(int limit) {
        return map.values().stream()
                .sorted(Comparator.comparingInt(Player::getHighscore).reversed())
                .limit(Math.max(0, limit))
                .collect(Collectors.toList());
    }

    public List<Player> findByHighscoreGreaterThan(int minScore) {
        return map.values().stream()
                .filter(p -> p.getHighscore() > minScore)
                .collect(Collectors.toList());
    }

    public List<Player> findAllByOrderByTotalCoinsDesc() {
        return map.values().stream()
                .sorted(Comparator.comparingInt(Player::getTotalCoins).reversed())
                .collect(Collectors.toList());
    }

    public List<Player> findAllByOrderByTotalDistanceTravelledDesc() {
        return map.values().stream()
                .sorted(Comparator.comparingInt(Player::getTotalDistance).reversed())
                .collect(Collectors.toList());
    }
}
