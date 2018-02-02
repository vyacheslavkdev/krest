package ru.testexample.cs.server;

import java.util.ArrayList;

public class Game {

    int[] board = {2,2,2,2,2,2,2,2,2};
    int stepConunt = 0;

    GameController gameController;
    ArrayList<Player> players;
    Player player1;
    Player player2;
    GamaResult result;

    public Game(GameController gameController){
        this.gameController = gameController;
        this.players = gameController.getPlayers();
        result = new GamaResult();
    }

    public void getStep(Player player, String field){
        int index = Integer.valueOf(field);
        if (board[index] == 2){
            stepConunt++;
            if (player.equals(players.get(0))){
                board[index] = 0;
            } else if (player.equals(players.get(1))){
                board[index] = 1;
            }
            gameController.checkPlayer(player,false);
            gameController.checkPlayer(enotherPlayer(player), true);

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

    private Player enotherPlayer(Player player){
        if (player.equals(players.get(0))){
            return players.get(1);
        } else return players.get(0);
    }

    private void afterStep(){
       result.winTest(board);
        System.out.println("win?: " + result.isWin());
        boardPrint();
        if (result.isWin()){
            gameController.chat("server:Game OVER");
            if (result.whoWin()){
                gameController.chat("server:Player1 win");
            } else {
                gameController.chat("server:Player2 win");
            }
            gameController.checkPlayer(players.get(0),false);
            gameController.checkPlayer(players.get(1), false);
        }
    }

    private void boardPrint(){
        int row = 0;
        for(int i = 0; i < 9; i++){
            System.out.print(board[i]);
            row++;
            if (row == 3){
                System.out.println();
                row = 0;
            }
        }
    }






}
