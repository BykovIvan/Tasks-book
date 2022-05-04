package main.java.tracker.gson;

import com.google.gson.*;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;

import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;

public class EpicSerializer implements JsonSerializer<Epic> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm");

    @Override
    public JsonElement serialize(Epic epic, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("typeOfTask", String.valueOf(epic.getTypeOfTask()));
        result.addProperty("name", epic.getName());
        result.addProperty("discription", epic.getDiscription());
        result.addProperty("idTask", epic.getIdTask());
        result.addProperty("status", String.valueOf(epic.getStatus()));
        result.addProperty("startTime", epic.getStartTime().format(formatter));
        result.addProperty("duration", epic.getDuration().toMinutes());
        result.addProperty("startTime", epic.getEndTime().format(formatter));
        JsonArray subtasks = new JsonArray();
        result.add("subtasksOfEpic", subtasks);
        for(Subtask subtask : epic.getSubtasksOfEpic()) {
            subtasks.add(jsonSerializationContext.serialize(subtask));
        }
        return result;
    }
}
