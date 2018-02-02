package ru.testexample.cs.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController {
    ClientInterface clientInterface;
    Socket socket;
    ServerReader srvReader;
    ServerWriter srvWriter;

    public ClientController (ClientInterface clientInterface, Socket socket) throws IOException {
        this.clientInterface = clientInterface;
        this.socket = socket;
        clientInterface.setClientController(this);

        srvWriter = new ServerWriter(new PrintWriter(socket.getOutputStream()));
        srvReader = new ServerReader(this, new BufferedReader(new InputStreamReader(socket.getInputStream())));
        Thread thread = new Thread(srvReader);
        thread.start();
        srvWriter.sendToServer("helloServer");
    }


    public void read(String message){
        String[] msg = message.split(":");
        if (msg[0].equals("chat")){
            String text = msg[1] + ": " + msg[2];
            clientInterface.getTextArea().append(text + "\n");
        } else if(msg[0].equals("command")){
            if (msg[1].equals("check")){
                clientInterface.enableButtons(true);
            } else if(msg[1].equals("nocheck")){
                clientInterface.enableButtons(false);
            } else if (msg[1].equals("restart")){
                clientInterface.restart();
            }
        } else if (msg[0].equals("field")){
            boolean cross;
            if (msg[1].equals("1")){
                cross = true;
            } else {
                cross = false;
            }
            System.out.println(message);
            clientInterface.setButtonIcon(Integer.valueOf(msg[2]), cross);
        }
    }

    public void sendMessage(String message){
        System.out.println("clientController.sendMessage: " + message);
        srvWriter.sendToServer(message);
    }

    public void sendField(String field){
        System.out.println("clientController.field:" + field);
        srvWriter.checkToServer(field);
    }


}
