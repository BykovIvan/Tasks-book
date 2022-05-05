package main.java.tracker.controllers;

import main.java.tracker.httpClient.KVTaskClient;

public class HTTPTaskManager extends FileBackedTasksManager {
    KVTaskClient kvClient;
    public HTTPTaskManager(String nameTestFile) {
        super(nameTestFile);
        kvClient = new KVTaskClient(nameTestFile);
    }
    String key = kvClient.getKey();


    @Override
    public void save() {
        kvClient.put(key, "Hello");
        //своя реализия
    }
}
