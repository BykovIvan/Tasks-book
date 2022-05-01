package main.java.tracker.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import main.java.tracker.model.Task;

import java.io.IOException;

public class TaskAdapterGson extends TypeAdapter<Task> {

    @Override
    public Task read(JsonReader jsonReader) throws IOException {
        Task task = new Task();
        jsonReader.beginObject();
        String fieldname = null;

        while (jsonReader.hasNext()) {
            JsonToken token = jsonReader.peek();
            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldname = jsonReader.nextName();
            }
            if ("name".equals(fieldname)) {
                //move to next token
                token = jsonReader.peek();
                task.setName(jsonReader.nextString());
            }
            if ("discription".equals(fieldname)) {
                //move to next token
                token = jsonReader.peek();
                task.setDiscription(jsonReader.nextString());
            }
//            if("status".equals(fieldname)) {
//                //move to next token
//                token = jsonReader.peek();
//                task.setStatus(jsonReader.nextInt());
//            }
        }
        jsonReader.endObject();
        return task;


    }

    @Override
    public void write(JsonWriter jsonWriter, Task task) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("name").value(task.getName());
//        jsonWriter.name("discription").value(task.getDiscription());
//        jsonWriter.name("status");
//        jsonWriter.value(task.getStatus().toString());
        jsonWriter.beginObject();
    }
}
