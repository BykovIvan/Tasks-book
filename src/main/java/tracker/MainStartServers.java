package main.java.tracker;

import main.java.tracker.httpClient.KVTaskClient;
import main.java.tracker.httpServer.HttpTaskServer;
import main.java.tracker.httpServer.KVServer;

import java.io.IOException;

public class MainStartServers {
    public static void main(String[] args) throws IOException {
        new KVServer().start();
//        new HttpTaskServer().start();

        KVTaskClient client = new KVTaskClient("http://localhost:8078");
        client.getAPI_KEY();
        client.put("Task", "TaskGson");
        client.put("Task2", "TaskGson2");
        System.out.println(client.load("Task"));
        System.out.println(client.load("Task2"));
    }
}
