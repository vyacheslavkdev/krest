package ru.testexample.cs.client;

import java.io.PrintWriter;

public class ServerWriter {

    PrintWriter writer;

    public ServerWriter(PrintWriter writer){
        this.writer = writer;
    }

    public void sendToServer(String message){
        writer.println("chat:"+message);
        writer.flush();
    }

    public void checkToServer(String field){
        writer.println("field:"+field);
        writer.flush();
    }
}
