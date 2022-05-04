package main.java.tracker.gson;

import com.google.gson.*;
import main.java.tracker.model.Task;
import main.java.tracker.util.Status;
import main.java.tracker.util.TypeOfTasks;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskSerializer implements JsonSerializer<Task>, JsonDeserializer<Task> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm");

    @Override
    public JsonElement serialize(Task task, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("typeOfTask", String.valueOf(task.getTypeOfTask()));
        result.addProperty("name", task.getName());
        result.addProperty("discription", task.getDiscription());
        result.addProperty("idTask", task.getIdTask());
        result.addProperty("status", String.valueOf(task.getStatus()));
        result.addProperty("startTime", task.getStartTime().format(formatter) );
        result.addProperty("duration", task.getDuration().toMinutes());
        return result;
    }

    @Override
    public Task deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Task task = new Task();
        task.setTypeOfTask(TypeOfTasks.valueOf(jsonObject.get("typeOfTask").getAsString()));
        task.setName(jsonObject.get("name").getAsString());
        task.setDiscription(jsonObject.get("discription").getAsString());
        task.setIdTask(jsonObject.get("idTask").getAsInt());
        task.setStatus(Status.valueOf(jsonObject.get("status").getAsString()));
        task.setStartTime(LocalDateTime.parse(jsonObject.get("startTime").getAsString(), formatter));
        task.setDuration(jsonObject.get("duration").getAsLong());
        return task;
    }
}
