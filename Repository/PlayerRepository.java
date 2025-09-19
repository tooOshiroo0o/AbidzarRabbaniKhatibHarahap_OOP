package Repository;
import Model.player;

import java.util.Optional;


public class PlayerRepository <Player, UUID> extends BaseRepository {
    public Optional<Player> findByUsername(String username)
    {
        return [Map variabel].stream()
            .filter(player -> player.getUsername().equals(username))
            .findFirst();
    }
    findTopPlayersByHighScore(int limit) {
        return
    }
    findByHighscoreGreaterThan(int minScore) {
        return
    }
    findByHighscoreGreaterThan(int minScore) {
        return
    }
    findAllByOrderByTotalDistanceTravelledDesc() {

    }

}
