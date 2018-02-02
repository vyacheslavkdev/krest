package ru.testexample.cs.server;

import java.io.PrintWriter;
import java.util.ArrayList;

public class ClientWriter {

    ArrayList<Player> players;

    public ClientWriter(ArrayList<Player> players) {
        this.players = players;
    }

    public void tellEveryone(String message){
        for (Player player: players){
            PrintWriter writer = player.getWriter();
            writer.println("chat:" + message);
            writer.flush();
        }
    }

    public void tellEveryButtons(String message){
        for (Player player: players){
            PrintWriter writer = player.getWriter();
            writer.println("field:" + message);
            writer.flush();
        }
    }


    public void tellPlayer(String message, Player player){
        PrintWriter writer = player.getWriter();
        writer.println("chat:"+message);
        writer.flush();
    }

    public void commandPlayer(String command, Player player){
        PrintWriter writer = player.getWriter();
        writer.println("command:"+command);
        writer.flush();
    }

    public void commandAllPlayers(String command){
        for (Player player: players){
            PrintWriter writer = player.getWriter();
            writer.println("command:"+command);
            writer.flush();
        }
    }

    public void checkPlayer(Player player, boolean isCheck){
        String check;
        if (isCheck){
            check = "check";
        } else {
            check = "nocheck";
        }
        commandPlayer(check, player);
    }

}
