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
import main.java.tracker.util.TaskAdapterGson;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static main.java.tracker.util.Status.NEW;

public class HttpTaskServer {
    private static final int PORT = 8080;
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static TaskManager manager = Managers.getDefault();
    private static int idTask1 = 0;

    public static void main(String[] args) {
        Task task1 = new Task("Имя1", "Что купить1", NEW);
        Task task2 = new Task("Имя2", "Что купить2", NEW);
        task1.setStartTime("15.11.2022-12:21");
        task1.setDuration(34);
        task2.setStartTime("14.04.2022-12:21");
        task2.setDuration(43);
        idTask1 = manager.createNewTask(task1);
        int idTask2 = manager.createNewTask(task2);

        Epic epic = new Epic("Epic 1", "Der1", NEW);
        int idEpic1 = manager.createNewEpic(epic);

        Subtask subtask = new Subtask("Саб1", "Где взять", NEW);
        subtask.setIdEpic(idEpic1);
        subtask.setStartTime("16.11.2022-12:24");
        subtask.setDuration(78);

        Subtask subtask2 = new Subtask("Саб2", "Где взять2", NEW);
        subtask2.setIdEpic(idEpic1);
        subtask2.setStartTime("13.12.2021-15:21");
        subtask2.setDuration(65);

        int idSubtask1 = manager.createNewSubTask(subtask);
        int idSubtask2 = manager.createNewSubTask(subtask2);

        manager.getTask(idTask2);
        manager.getEpic(idEpic1);
        manager.getTask(idTask1);
        manager.getSubtask(idSubtask2);
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

    static class TasksHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Task.class, new TaskAdapterGson())
                    .setPrettyPrinting()
                    .create();

            String method =  httpExchange.getRequestMethod();
            switch(method){
                case "GET":
                    String path = httpExchange.getRequestURI().getPath();
                    //получение всех задач
                    if (path.endsWith("tasks")) {
                        httpExchange.sendResponseHeaders(200, 1012);
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write(gson.toJson(manager).getBytes());

                        }
                    }
                    String tyteOfTask = path.split("/")[2];
                    //получение только таксов
                    if (tyteOfTask.equals("task")){
                        httpExchange.getResponseHeaders().set("Content-Type", "application/json");
                        String request = gson.toJson(manager.getTask(idTask1));
                        System.out.println(request);
                        httpExchange.sendResponseHeaders(200, request.length());
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write(request.getBytes());
                            System.out.println("Ушел ответ!");
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
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write(gson.toJson(manager.getSubtasks()).getBytes());
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
