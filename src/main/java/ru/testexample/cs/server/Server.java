package ru.testexample.cs.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(45123);

            GameController gameController = new GameController();
            for (int i = 0; i < 2; i++){
                Socket socket = serverSocket.accept();
                gameController.addPlayer(socket);
            }

            gameController.startGame();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
