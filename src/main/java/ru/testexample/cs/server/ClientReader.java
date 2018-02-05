package ru.testexample.cs.server;

import java.io.IOException;

public class ClientReader implements Runnable {

    private GameController controller;
    private Player player;

    public ClientReader(GameController gameController, Player player){
        this.player = player;
        this.controller = gameController;
    }

    @Override
    public void run() {
        String message;
        String[] text;
        try {
            while ((message = player.getReader().readLine()) != null){
                text = message.split(":");
                if (text[0].equals("chat")){
                    if (text[1].equals("restart")){
                        controller.restartGame();
                    }else{
                        controller.chat(player.getPlayerName() + ":" + text[1]);
                    }
                } else if (text[0].equals("field")){
                    controller.step(player, text[1]);
                }
            }
        } catch (IOException e) {
            controller.removePlayer(player);
        }
    }
}
