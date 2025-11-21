package com.abidzar.frontend.command;

import com.abidzar.frontend.Player;

public class JetPackCommand implements Command{
    private Player player;
    @Override
    public void execute() {
        if (!player.isDead()) {
            player.fly();
        }
    }

    public JetPackCommand (Player player){
        this.player = player;
    }

}
