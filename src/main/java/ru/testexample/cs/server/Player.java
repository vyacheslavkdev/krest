package ru.testexample.cs.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {

    String playerName;
    Socket socket;
    PrintWriter writer;
    BufferedReader reader;

    ClientReader clientReader;

    public Player(Socket socket, String playerName) {
        this.playerName = playerName;
        this.socket = socket;
        try {
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientReader getClientReader() {
        return clientReader;
    }

    public void setClientReader(ClientReader clientReader) {
        this.clientReader = clientReader;
    }

    public String getPlayerName() {
        return playerName;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public String getStep(){
        while (true){
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
