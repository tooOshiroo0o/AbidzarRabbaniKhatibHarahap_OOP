package com.abidzar.frontend.command;

import com.abidzar.frontend.GameManager;
import com.abidzar.frontend.Player;

public class RestartCommand implements Command{
    private Player player;
    private GameManager gameManager;
    @Override
    public void execute() {
        player.reset();
        gameManager.setScore(0);
    }

    public RestartCommand(Player player, GameManager gameManager) {
        this.player = player;
        this.gameManager = gameManager;
    }
}
