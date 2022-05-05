package main.java.tracker.controllers;

import main.java.tracker.httpClient.KVTaskClient;
/**
 * Класс менеджера для работы c httpServers
 */
public class HTTPTaskManager extends FileBackedTasksManager {
    KVTaskClient kvClient;
    String key = "manager";

    public HTTPTaskManager(String nameTestFile) {
        super(nameTestFile);
        kvClient = new KVTaskClient(nameTestFile);
    }

    @Override
    public void save() {
        kvClient.put(key, "BODY OF MANAGER");
        //TODO своя реализия
    }
}
