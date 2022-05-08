package main.java.tracker.httpServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import main.java.tracker.controllers.Managers;
import main.java.tracker.controllers.TaskManager;
import main.java.tracker.gson.EpicSerializer;
import main.java.tracker.gson.SubtaskSerializer;
import main.java.tracker.gson.TaskSerializer;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.stream.Collectors;


public class HttpTaskServer {
    private static final int PORT = 8080;
    private static TaskManager manager = Managers.getDefault();
    private HttpServer httpServer;

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Task.class, new TaskSerializer())
            .registerTypeAdapter(Subtask.class, new SubtaskSerializer())
            .registerTypeAdapter(Epic.class, new EpicSerializer())
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    public HttpTaskServer() {
        try {
            httpServer = HttpServer.create();
            httpServer.bind(new InetSocketAddress(PORT), 0);
            httpServer.createContext("/tasks/task", new TaskHandler());
            httpServer.createContext("/tasks/epic", new EpicHandler());
            httpServer.createContext("/tasks/subtask", new SubtaskHandler());
            httpServer.createContext("/tasks/subtask/epic", new SubtaskOfEpicHandler());
            httpServer.createContext("/tasks/history", new HistoryHandler());
            httpServer.createContext("/tasks", new TasksHandler());

        } catch (IOException e) {
            System.out.println("HTTP-сервер не запущен!");
        }
    }

    public void start() {
        System.out.println("Запускаем свой сервер на порту " + PORT);
        httpServer.start();
    }

    public void stop() {
        System.out.println("HttpСервер остановлен");
        httpServer.stop(0);
    }

    /**
     * Энпоинты для всех задач
     */
    static class TasksHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            String method = httpExchange.getRequestMethod();
            try {
                if (method.equals("GET")) {
                    httpExchange.sendResponseHeaders(200, 0);
                    try (OutputStream os = httpExchange.getResponseBody()) {
                        os.write(gson.toJson(manager.getPrioritizedTasks()).getBytes());
                    }
                }
            } finally {
                httpExchange.close();
            }
        }
    }

    /**
     * Энпоинты для тасков
     */
    static class TaskHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            String method = httpExchange.getRequestMethod();
            String query = httpExchange.getRequestURI().getRawQuery();

            try {
                switch (method) {
                    case "GET":
                        if (query == null || query.isEmpty()) {
                            httpExchange.sendResponseHeaders(200, 0);
                            try (OutputStream os = httpExchange.getResponseBody()) {
                                os.write(gson.toJson(manager.getTasks()).getBytes());
                            }
                        } else {
                            int taskID = Integer.parseInt(query.split("=")[1]);
                            httpExchange.sendResponseHeaders(200, 0);
                            try (OutputStream os = httpExchange.getResponseBody()) {
                                Task task = manager.getTask(taskID);
                                os.write(gson.toJson(task).getBytes());
                            }
                        }
                        break;

                    case "POST":
                        InputStreamReader streamReader = new InputStreamReader(httpExchange.getRequestBody());
                        BufferedReader bufferedReader = new BufferedReader(streamReader);
                        String body = bufferedReader.lines().collect(Collectors.joining("\n"));
                        Task task = gson.fromJson(body, Task.class);

                        for (Task managerTask : manager.getTasks()) {
                            if (managerTask.getIdTask() == task.getIdTask()) {
                                httpExchange.sendResponseHeaders(200, 0);
                                try (OutputStream os = httpExchange.getResponseBody()) {
                                    os.write("Задача обновлена".getBytes());
                                }
                                manager.updateTask(task);
                                break;
                            }
                        }
                        httpExchange.sendResponseHeaders(201, 0);
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write("Задача создана".getBytes());
                        }
                        manager.createNewTask(task);
                        break;

                    case "DELETE":
                        if (query == null || query.isEmpty()) {
                            httpExchange.sendResponseHeaders(200, 0);
                            manager.deleteAllTasks();
                            try (OutputStream os = httpExchange.getResponseBody()) {
                                os.write("Удалены все задачи".getBytes());
                            }
                        } else {
                            int taskID = Integer.parseInt(query.split("=")[1]);
                            httpExchange.sendResponseHeaders(200, 0);
                            manager.deleteTask(taskID);
                            try (OutputStream os = httpExchange.getResponseBody()) {
                                os.write("Удалена задача".getBytes());
                            }
                        }
                        break;
                }
            } finally {
                httpExchange.close();
            }

        }
    }

    /**
     * Энпоинты для эпиков
     */
    static class EpicHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            String method = httpExchange.getRequestMethod();
            String query = httpExchange.getRequestURI().getRawQuery();

            try {
                switch (method) {
                    case "GET":
                        if (query == null || query.isEmpty()) {
                            httpExchange.sendResponseHeaders(200, 0);
                            try (OutputStream os = httpExchange.getResponseBody()) {
                                os.write(gson.toJson(manager.getEpics()).getBytes());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            int taskID = Integer.parseInt(query.split("=")[1]);
                            httpExchange.sendResponseHeaders(200, 0);
                            try (OutputStream os = httpExchange.getResponseBody()) {
                                os.write(gson.toJson(manager.getEpic(taskID)).getBytes());
                            }
                        }

                        break;
                    case "POST":
                        InputStreamReader streamReader = new InputStreamReader(httpExchange.getRequestBody());
                        BufferedReader bufferedReader = new BufferedReader(streamReader);
                        String body = bufferedReader.lines().collect(Collectors.joining("\n"));
                        Epic epic = gson.fromJson(body, Epic.class);
                        httpExchange.sendResponseHeaders(201, 0);
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write("Эпик создан".getBytes());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        manager.createNewEpic(epic);
                        break;

                    case "DELETE":
                        if (query == null || query.isEmpty()) {
                            httpExchange.sendResponseHeaders(200, 0);
                            manager.deleteAllEpics();
                            try (OutputStream os = httpExchange.getResponseBody()) {
                                os.write("Удалены все эпики".getBytes());
                            }
                        } else {
                            int taskID = Integer.parseInt(query.split("=")[1]);
                            httpExchange.sendResponseHeaders(200, 0);
                            manager.deleteEpic(taskID);
                            try (OutputStream os = httpExchange.getResponseBody()) {
                                os.write("Удален эпик".getBytes());
                            }
                        }
                        break;
                }
            } finally {
                httpExchange.close();
            }


        }
    }

    /**
     * Энпоинты для сабтасков
     */
    static class SubtaskHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            String method = httpExchange.getRequestMethod();
            String query = httpExchange.getRequestURI().getRawQuery();

            try {
                switch (method) {
                    case "GET":
                        if (query == null || query.isEmpty()) {
                            httpExchange.sendResponseHeaders(200, 0);
                            try (OutputStream os = httpExchange.getResponseBody()) {
                                os.write(gson.toJson(manager.getSubtasks()).getBytes());
                            }
                        } else {
                            int taskID = Integer.parseInt(query.split("=")[1]);
                            httpExchange.sendResponseHeaders(200, 0);
                            try (OutputStream os = httpExchange.getResponseBody()) {
                                os.write(gson.toJson(manager.getSubtask(taskID)).getBytes());
                            }
                        }
                        break;

                    case "POST":
                        InputStreamReader streamReader = new InputStreamReader(httpExchange.getRequestBody());
                        BufferedReader bufferedReader = new BufferedReader(streamReader);
                        String body = bufferedReader.lines().collect(Collectors.joining("\n"));
                        Subtask subtask = gson.fromJson(body, Subtask.class);
                        httpExchange.sendResponseHeaders(201, 0);
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write("Подзадача создана".getBytes());
                        }
                        manager.createNewSubTask(subtask);
                        break;

                    case "DELETE":
                        if (query == null || query.isEmpty()) {
                            httpExchange.sendResponseHeaders(200, 0);
                            manager.deleteAllSubtasks();
                            try (OutputStream os = httpExchange.getResponseBody()) {
                                os.write("Удалены все подзадачи".getBytes());
                            }
                        } else {
                            int taskID = Integer.parseInt(query.split("=")[1]);
                            httpExchange.sendResponseHeaders(200, 0);
                            manager.deleteSubtask(taskID);
                            try (OutputStream os = httpExchange.getResponseBody()) {
                                os.write("Удалена подзадача".getBytes());
                            }
                        }
                        break;
                }
            } finally {
                httpExchange.close();
            }

        }
    }

    /**
     * Энпоинты для истории
     */
    static class HistoryHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            String method = httpExchange.getRequestMethod();

            try {
                if (method.equals("GET")) {
                    httpExchange.sendResponseHeaders(200, 0);
                    try (OutputStream os = httpExchange.getResponseBody()) {
                        os.write(gson.toJson(manager.history()).getBytes());
                    }
                }
            } finally {
                httpExchange.close();
            }

        }
    }

    /**
     * Энпоинты для получения сабтасков по ид эпика
     */
    static class SubtaskOfEpicHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            String method = httpExchange.getRequestMethod();
            String query = httpExchange.getRequestURI().getRawQuery();

            try {
                int epicID = Integer.parseInt(query.split("=")[1]);
                if (method.equals("GET")) {
                    httpExchange.sendResponseHeaders(200, 0);
                    try (OutputStream os = httpExchange.getResponseBody()) {
                        os.write(gson.toJson(manager.getEpic(epicID).getSubtasksOfEpic()).getBytes());
                    }
                }
            } finally {
                httpExchange.close();
            }

        }
    }

}




