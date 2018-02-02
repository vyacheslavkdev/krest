package ru.testexample.cs.client;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerReader implements Runnable {

    ClientController clientController;
    BufferedReader reader;

    public ServerReader(ClientController clientController, BufferedReader reader){
        this.clientController = clientController;
        this.reader = reader;
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = reader.readLine()) != null){
                System.out.println("read message: " + message);
                clientController.read(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
