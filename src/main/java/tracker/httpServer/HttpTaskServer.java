package main.java.tracker.httpServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import main.java.tracker.controllers.Managers;
import main.java.tracker.controllers.TaskManager;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;
import main.java.tracker.util.DurationAdapter;
import main.java.tracker.util.LocalDateAdapter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static main.java.tracker.util.Status.NEW;

public class HttpTaskServer {
    private static final int PORT = 8080;
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static TaskManager manager = Managers.getDefault();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm");

    static Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
            .registerTypeAdapter(Duration.class, new DurationAdapter())
            .setPrettyPrinting()
            .serializeNulls()
            .create();
    public static void main(String[] args) {

        Task task1 = new Task("Имя1", "Что купить1", NEW);
        Task task2 = new Task("Имя2", "Что купить2", NEW);
        task1.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        task1.setDuration(34);
        task2.setStartTime(LocalDateTime.parse("14.04.2022-12:21", formatter));
        task2.setDuration(43);
        int idTask1 = manager.createNewTask(task1);
        int idTask2 = manager.createNewTask(task2);

        Epic epic = new Epic("Epic 1", "Der1", NEW);
        int idEpic1 = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Саб1", "Где взять", NEW);
        subtask.setIdEpic(idEpic1);
        subtask.setStartTime(LocalDateTime.parse("17.11.2022-12:24", formatter));
        subtask.setDuration(78);

        Subtask subtask2 = new Subtask("Саб2", "Где взять2", NEW);
        subtask2.setIdEpic(idEpic1);
        subtask2.setStartTime(LocalDateTime.parse("13.12.2021-15:21", formatter));
        subtask2.setDuration(65);

        int idSubtask1 = manager.createNewSubTask(subtask);
        int idSubtask2 = manager.createNewSubTask(subtask2);

        manager.getTask(idTask2);
        manager.getEpic(idEpic1);
        manager.getTask(idTask1);
        manager.getSubtask(idSubtask2);
//        System.out.println(manager.getEpic(idEpic1).getStartTime());
//        System.out.println(manager.getEpic(idEpic1).getDuration().toMinutes());
//        System.out.println(manager.getEpic(idEpic1).getEndTime());
//        System.out.println(manager.getTasks());
//        System.out.println(manager.getSubtasks());
//        System.out.println(manager.getEpics());

        try {
            HttpServer httpServer = HttpServer.create();
            httpServer.bind(new InetSocketAddress(PORT), 0);
            httpServer.createContext("/tasks", new TasksHandler());
            httpServer.start();
            System.out.println("HTTP-сервер запущен на " + PORT + " порту!");


        } catch (IOException e) {
            System.out.println("HTTP-сервер не запущен!");
        }
    }

    static class TasksHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            String method = httpExchange.getRequestMethod();

            switch(method){
                case "GET":
                    String path = httpExchange.getRequestURI().getPath();
                    //получение всех задач
                    if (path.endsWith("tasks")) {
                        httpExchange.sendResponseHeaders(200, 0);
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write(gson.toJson(manager.getPrioritizedTasks()).getBytes());
                        }
                    }
                    String tyteOfTask = path.split("/")[2];
                    //получение только таксов
                    if (tyteOfTask.equals("task")){
                        httpExchange.sendResponseHeaders(200, 0);
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write(gson.toJson(manager.getTasks()).getBytes());
                        }
                    //получение только эпиков
                    }else if (tyteOfTask.equals("epic")){
                        httpExchange.sendResponseHeaders(200, 0);
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write(gson.toJson(manager.getEpics()).getBytes());
                        }
                    //получение только сабтасков
                    }else if (tyteOfTask.equals("subtask")){
                        httpExchange.sendResponseHeaders(200, 0);
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write(gson.toJson(manager.getSubtasks()).getBytes());
                        }
                        //получение истории
                    }else if (tyteOfTask.equals("history")){
                        httpExchange.sendResponseHeaders(200, 0);
                        System.out.println(gson.toJson(manager.history()));
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write("Ответ".getBytes());
                        }
                    }
                    break;
                case "POST":

                    break;
                case "DELETE":

                    break;
                default:

                    break;
            }



        }
    }
}




