package main.java.tracker;

import main.java.tracker.httpServer.HttpTaskServer;
import main.java.tracker.httpServer.KVServer;

import java.io.IOException;

public class MainStartServers {

    public static void main(String[] args) throws IOException, InterruptedException {
        new KVServer().start();
        new HttpTaskServer().start();



    }

}