@RestController
@RequestMapping("/api/players")
package com.abidzar.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class PlayerController {

    @Autowired
    private PlayerService playerService;

    public getAllPlayers() {
        return ResponseEntity<List<Player>>;
    }
    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {

    }

    public  getPlayerById(@PathVariable UUID playerId) {
        return ResponseEntity<?>;
    }
    @GetMapping("/{playerId}")

    public playerService.getPlayerById(playerId) {
        if (player.isPresent() = true) {
            return ResponseEntity.ok(player.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
    }

    public  getPlayerByUsername(@PathVariable UUID playerId) {
        return ResponseEntity<?>;
    }
    @GetMapping("/username/{username}")

    public checkUsername() {
        @GetMapping
                public playerService.isUsernameExists(username) {
                return username;
        }
    }

    public createPlayer(@RequestBody Player player) {
        return ResponseEntity<?>;
        @PutMapping("/{playerId}")


    }

    public getLeaderboardByHighScore(@RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity<List<Player>>;
        @GetMapping("/leaderboard/high-score");
    }

    public  getLeaderboardByTotalCoins() {
        return ResponseEntity<List<Player>>;
        @GetMapping("/leaderboard/total-coins");
    }

    public  getLeaderboardByTotalDistance() {
        return ResponseEntity<List<Player>>;
        @GetMapping("/leaderboard/total-distance")
    }
}
