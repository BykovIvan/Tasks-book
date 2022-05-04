package main.java.tracker.gson;

import com.google.gson.*;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;
import main.java.tracker.util.Status;
import main.java.tracker.util.TypeOfTasks;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SubtaskSerializer implements JsonSerializer<Subtask>, JsonDeserializer<Subtask> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm");

    @Override
    public JsonElement serialize(Subtask subtask, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("typeOfTask", String.valueOf(subtask.getTypeOfTask()));
        result.addProperty("name", subtask.getName());
        result.addProperty("discription", subtask.getDiscription());
        result.addProperty("idTask", subtask.getIdTask());
        result.addProperty("status", String.valueOf(subtask.getStatus()));
        result.addProperty("ipEpic", subtask.getIdEpic());
        result.addProperty("startTime", subtask.getStartTime().format(formatter) );
        result.addProperty("duration", subtask.getDuration().toMinutes());
        return result;
    }

    @Override
    public Subtask deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Subtask subtask = new Subtask();
        subtask.setTypeOfTask(TypeOfTasks.valueOf(jsonObject.get("typeOfTask").getAsString()));
        subtask.setName(jsonObject.get("name").getAsString());
        subtask.setDiscription(jsonObject.get("discription").getAsString());
        subtask.setIdTask(jsonObject.get("idTask").getAsInt());
        subtask.setStatus(Status.valueOf(jsonObject.get("status").getAsString()));
        subtask.setIdEpic(jsonObject.get("ipEpic").getAsInt());
        subtask.setStartTime(LocalDateTime.parse(jsonObject.get("startTime").getAsString(), formatter));
        subtask.setDuration(jsonObject.get("duration").getAsLong());
        return subtask;
    }
}
