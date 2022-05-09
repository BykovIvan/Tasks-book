package main.java.tracker.controllers;

import com.google.gson.*;
import main.java.tracker.gson.EpicSerializer;
import main.java.tracker.gson.SubtaskSerializer;
import main.java.tracker.gson.TaskSerializer;
import main.java.tracker.httpClient.KVTaskClient;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;

/**
 * Класс менеджера для работы c httpServers
 */
public class HTTPTaskManager extends FileBackedTasksManager {
    private KVTaskClient kvClient;
    private String key = "manager";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Task.class, new TaskSerializer())
            .registerTypeAdapter(Subtask.class, new SubtaskSerializer())
            .registerTypeAdapter(Epic.class, new EpicSerializer())
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    public HTTPTaskManager(String nameTestFile) {
        super(nameTestFile);
        kvClient = new KVTaskClient(nameTestFile);
    }

    @Override
    public void save() {
        StringBuilder builder = new StringBuilder();
        builder.append(gson.toJson(getTasks()));
        builder.append(";");
        builder.append(gson.toJson(getEpics()));
        builder.append(";");
        builder.append(gson.toJson(getSubtasks()));
        builder.append(";");
        builder.append(gson.toJson(history()));
        kvClient.put(key, builder.toString());

    }

    public void restoreHttpTaskManager(){
        String lineJson = kvClient.load(key);
        String[] arrayList = lineJson.split(";");

        JsonElement jsonElement = JsonParser.parseString(arrayList[0]);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            JsonObject jsonObject2 = jsonObject.getAsJsonObject();
            int idTask = jsonObject2.get("idTask").getAsInt();
            mapTasks.put(idTask, gson.fromJson(jsonObject2, Task.class));
        }

        JsonElement jsonElement3 = JsonParser.parseString(arrayList[2]);
        JsonArray jsonArray3 = jsonElement3.getAsJsonArray();
        for (int i = 0; i < jsonArray3.size(); i++) {
            JsonObject jsonObject = jsonArray3.get(i).getAsJsonObject();
            JsonObject jsonObject2 = jsonObject.getAsJsonObject();
            int idTask = jsonObject2.get("idTask").getAsInt();
            mapSubtasks.put(idTask, gson.fromJson(jsonObject2, Subtask.class));
        }

        JsonElement jsonElement2 = JsonParser.parseString(arrayList[1]);
        JsonArray jsonArray2 = jsonElement2.getAsJsonArray();
        for (int i = 0; i < jsonArray2.size(); i++) {
            JsonObject jsonObject = jsonArray2.get(i).getAsJsonObject();
            JsonObject jsonObject2 = jsonObject.getAsJsonObject();
            int idTask = jsonObject2.get("idTask").getAsInt();
            mapEpics.put(idTask, gson.fromJson(jsonObject2, Epic.class));
        }


        JsonElement jsonElement4 = JsonParser.parseString(arrayList[3]);
        JsonArray jsonArray4 = jsonElement4.getAsJsonArray();
        for (int i = 0; i < jsonArray4.size(); i++) {
            JsonObject jsonObject = jsonArray4.get(i).getAsJsonObject();
            JsonObject jsonObject2 = jsonObject.getAsJsonObject();
            String typeTask = jsonObject2.get("typeOfTask").getAsString();
            switch (typeTask) {
                case "TASK":
                    historyList.add(gson.fromJson(jsonObject2, Task.class));
                    break;
                case "EPIC":
                    historyList.add(gson.fromJson(jsonObject2, Epic.class));
                    break;
                case "SUBTASK":
                    historyList.add(gson.fromJson(jsonObject2, Subtask.class));
                    break;
            }
        }

    }

    public KVTaskClient getKvClient() {
        return kvClient;
    }

    public void setKvClient(KVTaskClient kvClient) {
        this.kvClient = kvClient;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
