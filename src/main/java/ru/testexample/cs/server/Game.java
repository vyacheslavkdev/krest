package ru.testexample.cs.server;

import java.util.ArrayList;

public class Game {

    private int[] board = {2,2,2,2,2,2,2,2,2};

    private GameController gameController;
    private ArrayList<Player> players;
    private GamaResult result;

    public Game(GameController gameController){
        this.gameController = gameController;
        this.players = gameController.getPlayers();
        result = new GamaResult();
    }

    public void getStep(Player player, String field){
        int index = Integer.valueOf(field);
        if (board[index] == 2){
            if (player.equals(players.get(0))){
                board[index] = 0;
            } else if (player.equals(players.get(1))){
                board[index] = 1;
            }
            gameController.checkPlayer(player,false);
            gameController.checkPlayer(otherPlayer(player), true);

            System.out.println("game step: " + player.getPlayerName() + ": " + field);
            gameController.setButtonIcon(player, field);
            afterStep();
        } else {
            gameController.tellPlayer(player, "server:Неверный ход! Поле занято!");
        }
    }

    public void play(){
        gameController.checkPlayer(players.get(0), true);
    }

    private Player otherPlayer(Player player){
        if (player.equals(players.get(0))){
            return players.get(1);
        } else return players.get(0);
    }

    private void afterStep(){
        if (result.winTest(board)){
            gameController.chat("server:Game OVER");
            if (result.whoWin()){
                gameController.chat("server:Player1 win");
            } else {
                gameController.chat("server:Player2 win");
            }
            gameController.chat("server:write \"restart\" to restart game");
            gameController.checkPlayer(players.get(0),false);
            gameController.checkPlayer(players.get(1), false);
        }
    }
}
