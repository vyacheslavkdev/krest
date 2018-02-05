package ru.testexample.cs.client;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        String host = "localhost";
        if (args.length != 0){
            host = args[0];
        }

        ClientInterface clientInterface = new ClientInterface();
        try {
            Socket socket = new Socket(host,45123);
            ClientController clientController = new ClientController(clientInterface, socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
