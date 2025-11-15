package com.abidzar.frontend.command;


import com.abidzar.frontend.Main;
import com.abidzar.frontend.Player;
import com.abidzar.frontend.GameManager;


public class RestartCommand implements Command {


    private Player player;
    private GameManager gameManager;


    public RestartCommand(Player player, GameManager gameManager) {
        this.player = player;
        this.gameManager = gameManager;
    }

    public RestartCommand(Main main) {
    }


    @Override
    public void execute() {
        player.reset();
        gameManager.setScore(0);
    }
}
