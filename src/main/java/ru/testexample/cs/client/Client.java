package ru.testexample.cs.client;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        ClientInterface clientInterface = new ClientInterface();
        try {
            Socket socket = new Socket("172.24.202.180",45123);
            ClientController clientController = new ClientController(clientInterface, socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
