package main.java.tracker;

import main.java.tracker.httpClient.KVTaskClient;
import main.java.tracker.httpServer.KVServer;

import java.io.IOException;

public class MainStartServers {
    public static void main(String[] args) throws IOException {
        new KVServer().start();
        KVTaskClient client = new KVTaskClient("http://localhost:8078/");
        client.getKey();
//        new HttpTaskServer().start();
    }
}
