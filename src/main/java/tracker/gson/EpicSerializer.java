package main.java.tracker.gson;

import com.google.gson.*;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;
import main.java.tracker.util.Status;
import main.java.tracker.util.TypeOfTasks;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EpicSerializer implements JsonSerializer<Epic>, JsonDeserializer<Epic> {
    @Override
    public JsonElement serialize(Epic epic, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("typeOfTask", String.valueOf(epic.getTypeOfTask()));
        result.addProperty("name", epic.getName());
        result.addProperty("discription", epic.getDiscription());
        result.addProperty("idTask", epic.getIdTask());
        result.addProperty("status", String.valueOf(epic.getStatus()));
        if (!epic.getStartTime().equals("null")){
            result.addProperty("startTime", epic.getStartTime());
        }
        if (epic.getDuration().toMinutes() != 0){
            result.addProperty("duration", epic.getDuration().toMinutes());
        }
        if (!epic.getEndTime().equals("null")){
            result.addProperty("endTime", epic.getEndTime());
        }
        JsonArray subtasks = new JsonArray();
        result.add("subtasksOfEpic", subtasks);
        for(Subtask subtask : epic.getSubtasksOfEpic()) {
            subtasks.add(jsonSerializationContext.serialize(subtask));
        }
        return result;
    }

    @Override
    public Epic deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Epic epic = new Epic();
        ArrayList<Subtask> listSub = new ArrayList<>();
        epic.setTypeOfTask(TypeOfTasks.valueOf(jsonObject.get("typeOfTask").getAsString()));
        epic.setName(jsonObject.get("name").getAsString());
        epic.setDiscription(jsonObject.get("discription").getAsString());
        epic.setIdTask(jsonObject.get("idTask").getAsInt());
        epic.setStatus(Status.valueOf(jsonObject.get("status").getAsString()));

        if (jsonObject.has("subtasksOfEpic")){
            JsonArray jsonArray = jsonObject.getAsJsonArray("subtasksOfEpic");
            if (jsonArray.size() != 0){
                for (int i = 0; i < jsonArray.size(); i++) {
                    Subtask subtask = jsonDeserializationContext.deserialize(jsonArray.get(i), Subtask.class);
                    listSub.add(subtask);
                }
                epic.setSubtasksOfEpic(listSub);

            }
        }


        return epic;
    }
}
