package ru.testexample.cs.server;

import java.net.Socket;
import java.util.ArrayList;

public class GameController {


    private ArrayList<Player> players;
    private int playerCount;
    private ClientReader clientReader;
    private ClientWriter clientWriter;
    private Game game;

    public GameController(){
        players = new ArrayList<Player>();
        clientWriter = new ClientWriter(players);
    }

    public void startGame(){
        game = new Game(this);
        game.play();
    }

    public void addPlayer(Socket socket){
        String playerName = "player";
        playerCount++;
        if (playerCount < 3){
            Player player = new Player(socket, playerName + String.valueOf(playerCount));
            players.add(player);
            System.out.println("player" + playerCount + " added!");
            checkPlayer(player, false);
            ClientReader clientReader = new ClientReader(this, players.get(playerCount-1));
            player.setClientReader(clientReader);
            Thread t = new Thread(clientReader);
            t.start();
        }
    }

    public void removePlayer(Player player){
        if (playerCount > 0){
            System.out.println(player.getPlayerName() + " removed");
            players.remove(player);
            playerCount--;
        }
    }

    public void chat(String message) {
        System.out.println(message);
        clientWriter.tellEveryone(message);
    }

    public void restartGame(){
        commandAllPlayers("restart");
        startGame();
    }

    public void commandAllPlayers(String message) {
        System.out.println(message);
        clientWriter.commandAllPlayers(message);
    }

    public void tellPlayer(Player player, String message){
        clientWriter.tellPlayer(message, player);
    }

    public void step(Player player, String field){
        game.getStep(player, field);
    }

    public void setButtonIcon(Player player, String field){
        String playerCode;
        if (player.getPlayerName().equals("player1")){
            playerCode = "1";
        } else {
            playerCode = "2";
        }
        clientWriter.tellEveryButtons(playerCode+":"+field);
    }

    public void checkPlayer(Player player, boolean isCheck){
        clientWriter.checkPlayer(player, isCheck);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
