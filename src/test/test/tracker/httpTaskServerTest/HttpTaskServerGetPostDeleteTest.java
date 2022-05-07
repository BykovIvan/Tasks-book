package tracker.httpTaskServerTest;

import com.google.gson.*;
import main.java.tracker.gson.EpicSerializer;
import main.java.tracker.gson.SubtaskSerializer;
import main.java.tracker.gson.TaskSerializer;
import main.java.tracker.httpServer.HttpTaskServer;
import main.java.tracker.httpServer.KVServer;
import main.java.tracker.model.Epic;
import main.java.tracker.model.Subtask;
import main.java.tracker.model.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static main.java.tracker.util.Status.NEW;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpTaskServerGetPostDeleteTest {
    private static HttpTaskServer httpTaskServer;
    private static KVServer kvServer;
    private static Gson gson;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm");

    @BeforeEach
    public void beforeEach() throws IOException {
        kvServer = new KVServer();
        kvServer.start();
        httpTaskServer = new HttpTaskServer();
        httpTaskServer.start();
        gson = new GsonBuilder()
                .registerTypeAdapter(Task.class, new TaskSerializer())
                .registerTypeAdapter(Subtask.class, new SubtaskSerializer())
                .registerTypeAdapter(Epic.class, new EpicSerializer())
                .setPrettyPrinting()
                .serializeNulls()
                .create();

    }

    @AfterEach
    public void afterEach() {
        httpTaskServer.stop();
        kvServer.stop();


    }

    @Test
    public void addNewTaskTest() {
        URI url = URI.create("http://localhost:8080/tasks/task/");
        Task task1 = new Task("Имя1", "Что купить1", NEW);
        task1.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        task1.setDuration(34);
        String json = gson.toJson(task1);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Задача создана", responseBody, "Тело ответа создания не совпадает");
    }

    @Test
    public void addNewTaskWithOutTimeTest() {
        URI url = URI.create("http://localhost:8080/tasks/task/");
        Task task1 = new Task("Имя1", "Что купить1", NEW);
        String json = gson.toJson(task1);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Задача создана", responseBody, "Тело ответа создания не совпадает");

    }

    @Test
    public void addNewEpicTest() {
        URI url = URI.create("http://localhost:8080/tasks/epic/");
        Epic epic = new Epic("Epic 1", "Der1", NEW);
        String json = gson.toJson(epic);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Эпик создан", responseBody, "Тело ответа создания не совпадает");
    }

    @Test
    public void addNewSubtaskTest() {
        URI url = URI.create("http://localhost:8080/tasks/subtask/");
        Subtask subtask = new Subtask("Саб1", "Где взять", NEW);
        subtask.setIdEpic(1);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        String json = gson.toJson(subtask);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Подзадача создана", responseBody, "Тело ответа создания не совпадает");
    }

    @Test
    public void addNewSubtaskWithOutTimeTest() {
        URI url = URI.create("http://localhost:8080/tasks/subtask/");
        Subtask subtask = new Subtask("Саб1", "Где взять", NEW);
        subtask.setIdEpic(1);
        String json = gson.toJson(subtask);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Подзадача создана", responseBody, "Тело ответа создания не совпадает");
    }

    @Test
    public void deleteAllTaskTest() {
        URI url = URI.create("http://localhost:8080/tasks/task/");
        Task task1 = new Task("Имя1", "Что купить1", NEW);
        task1.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        task1.setDuration(34);
        String json = gson.toJson(task1);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Задача создана", responseBody, "Тело ответа создания не совпадает");

        URI url2 = URI.create("http://localhost:8080/tasks/task/");
        Task task2 = new Task("Имя2", "Что купить2", NEW);
        task2.setStartTime(LocalDateTime.parse("15.11.2021-12:21", formatter));
        task2.setDuration(33);
        String json2 = gson.toJson(task2);
        final HttpRequest.BodyPublisher body2 = HttpRequest.BodyPublishers.ofString(json2);
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(url2)
                .POST(body2)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient2 = HttpClient.newHttpClient();
        int responseCode2 = 0;
        String responseBody2 = "";
        try {
            HttpResponse<String> response2 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            responseCode2 = response2.statusCode();
            responseBody2 = response2.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode2, "Код ответа при создании не верный");
        assertEquals("Задача создана", responseBody2, "Тело ответа создания не совпадает");

        //запрос удаления
        URI urlForDelete = URI.create("http://localhost:8080/tasks/task/");
        HttpRequest requestForDelete = HttpRequest.newBuilder()
                .uri(urlForDelete)
                .DELETE()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForDel = HttpClient.newHttpClient();
        int responseCodeDel = 0;
        String responseBodyDel = "";
        try {
            HttpResponse<String> response = httpClientForDel.send(requestForDelete, HttpResponse.BodyHandlers.ofString());
            responseCodeDel = response.statusCode();
            responseBodyDel = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(200, responseCodeDel, "Код ответа при создании не верный");
        assertEquals("Удалены все задачи", responseBodyDel, "Тело ответа создания не совпадает");

    }


    @Test
    public void deleteTaskIDTest() {
        URI url = URI.create("http://localhost:8080/tasks/task/");
        Task task1 = new Task("Имя1", "Что купить1", NEW);
        task1.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        task1.setDuration(34);
        String json = gson.toJson(task1);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Задача создана", responseBody, "Тело ответа создания не совпадает");

        URI url2 = URI.create("http://localhost:8080/tasks/task/");
        Task task2 = new Task("Имя2", "Что купить2", NEW);
        task2.setStartTime(LocalDateTime.parse("15.11.2021-12:21", formatter));
        task2.setDuration(33);
        String json2 = gson.toJson(task2);
        final HttpRequest.BodyPublisher body2 = HttpRequest.BodyPublishers.ofString(json2);
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(url2)
                .POST(body2)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient2 = HttpClient.newHttpClient();
        int responseCode2 = 0;
        String responseBody2 = "";
        try {
            HttpResponse<String> response2 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            responseCode2 = response2.statusCode();
            responseBody2 = response2.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode2, "Код ответа при создании не верный");
        assertEquals("Задача создана", responseBody2, "Тело ответа создания не совпадает");

        //запрос удаления
        URI urlForDelete = URI.create("http://localhost:8080/tasks/task/?id=0");
        HttpRequest requestForDelete = HttpRequest.newBuilder()
                .uri(urlForDelete)
                .DELETE()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForDel = HttpClient.newHttpClient();
        int responseCodeDel = 0;
        String responseBodyDel = "";
        try {
            HttpResponse<String> response = httpClientForDel.send(requestForDelete, HttpResponse.BodyHandlers.ofString());
            responseCodeDel = response.statusCode();
            responseBodyDel = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(200, responseCodeDel, "Код ответа при создании не верный");
        assertEquals("Удалена задача", responseBodyDel, "Тело ответа создания не совпадает");

    }

    @Test
    public void deleteAllEpicTest() {
        URI url = URI.create("http://localhost:8080/tasks/epic/");
        Epic epic = new Epic("Epic 1", "Der1", NEW);
        String json = gson.toJson(epic);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Эпик создан", responseBody, "Тело ответа создания не совпадает");

        URI url2 = URI.create("http://localhost:8080/tasks/epic/");
        Epic epic2 = new Epic("Epic 2", "Der2", NEW);
        String json2 = gson.toJson(epic2);
        final HttpRequest.BodyPublisher body2 = HttpRequest.BodyPublishers.ofString(json2);
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(url2)
                .POST(body2)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient2 = HttpClient.newHttpClient();
        int responseCode2 = 0;
        String responseBody2 = "";
        try {
            HttpResponse<String> response2 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            responseCode2 = response2.statusCode();
            responseBody2 = response2.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode2, "Код ответа при создании не верный");
        assertEquals("Эпик создан", responseBody2, "Тело ответа создания не совпадает");

        //запрос удаления
        URI urlForDelete = URI.create("http://localhost:8080/tasks/epic/");
        HttpRequest requestForDelete = HttpRequest.newBuilder()
                .uri(urlForDelete)
                .DELETE()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForDel = HttpClient.newHttpClient();
        int responseCodeDel = 0;
        String responseBodyDel = "";
        try {
            HttpResponse<String> response = httpClientForDel.send(requestForDelete, HttpResponse.BodyHandlers.ofString());
            responseCodeDel = response.statusCode();
            responseBodyDel = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(200, responseCodeDel, "Код ответа при создании не верный");
        assertEquals("Удалены все эпики", responseBodyDel, "Тело ответа создания не совпадает");
    }


    @Test
    public void deleteEpicIDTest() {
        URI url = URI.create("http://localhost:8080/tasks/epic/");
        Epic epic = new Epic("Epic 1", "Der1", NEW);
        String json = gson.toJson(epic);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Эпик создан", responseBody, "Тело ответа создания не совпадает");

        URI url2 = URI.create("http://localhost:8080/tasks/epic/");
        Epic epic2 = new Epic("Epic 2", "Der2", NEW);
        String json2 = gson.toJson(epic2);
        final HttpRequest.BodyPublisher body2 = HttpRequest.BodyPublishers.ofString(json2);
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(url2)
                .POST(body2)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient2 = HttpClient.newHttpClient();
        int responseCode2 = 0;
        String responseBody2 = "";
        try {
            HttpResponse<String> response2 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            responseCode2 = response2.statusCode();
            responseBody2 = response2.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode2, "Код ответа при создании не верный");
        assertEquals("Эпик создан", responseBody2, "Тело ответа создания не совпадает");

        //запрос удаления
        URI urlForDelete = URI.create("http://localhost:8080/tasks/epic/?id=1");
        HttpRequest requestForDelete = HttpRequest.newBuilder()
                .uri(urlForDelete)
                .DELETE()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForDel = HttpClient.newHttpClient();
        int responseCodeDel = 0;
        String responseBodyDel = "";
        try {
            HttpResponse<String> response = httpClientForDel.send(requestForDelete, HttpResponse.BodyHandlers.ofString());
            responseCodeDel = response.statusCode();
            responseBodyDel = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(200, responseCodeDel, "Код ответа при создании не верный");
        assertEquals("Удален эпик", responseBodyDel, "Тело ответа создания не совпадает");
    }


    @Test
    public void deleteAllSubtaskTest() {
        URI url = URI.create("http://localhost:8080/tasks/subtask/");
        Subtask subtask = new Subtask("Саб1", "Где взять", NEW);
        subtask.setIdEpic(1);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        String json = gson.toJson(subtask);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Подзадача создана", responseBody, "Тело ответа создания не совпадает");

        URI url2 = URI.create("http://localhost:8080/tasks/subtask/");
        Subtask subtask2 = new Subtask("Саб1", "Где взять", NEW);
        subtask2.setIdEpic(1);
        subtask2.setStartTime(LocalDateTime.parse("15.11.2021-12:21", formatter));
        subtask2.setDuration(34);
        String json2 = gson.toJson(subtask2);
        final HttpRequest.BodyPublisher body2 = HttpRequest.BodyPublishers.ofString(json2);
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(url2)
                .POST(body2)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient2 = HttpClient.newHttpClient();
        int responseCode2 = 0;
        String responseBody2 = "";
        try {
            HttpResponse<String> response2 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            responseCode2 = response2.statusCode();
            responseBody2 = response2.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode2, "Код ответа при создании не верный");
        assertEquals("Подзадача создана", responseBody2, "Тело ответа создания не совпадает");

        //запрос удаления
        URI urlForDelete = URI.create("http://localhost:8080/tasks/subtask/");
        HttpRequest requestForDelete = HttpRequest.newBuilder()
                .uri(urlForDelete)
                .DELETE()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForDel = HttpClient.newHttpClient();
        int responseCodeDel = 0;
        String responseBodyDel = "";
        try {
            HttpResponse<String> response = httpClientForDel.send(requestForDelete, HttpResponse.BodyHandlers.ofString());
            responseCodeDel = response.statusCode();
            responseBodyDel = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(200, responseCodeDel, "Код ответа при создании не верный");
        assertEquals("Удалены все подзадачи", responseBodyDel, "Тело ответа создания не совпадает");

    }


    @Test
    public void deleteSubtaskIDTest() {
        URI url = URI.create("http://localhost:8080/tasks/subtask/");
        Subtask subtask = new Subtask("Саб1", "Где взять", NEW);
        subtask.setIdEpic(1);
        subtask.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask.setDuration(34);
        String json = gson.toJson(subtask);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Подзадача создана", responseBody, "Тело ответа создания не совпадает");

        URI url2 = URI.create("http://localhost:8080/tasks/subtask/");
        Subtask subtask2 = new Subtask("Саб1", "Где взять", NEW);
        subtask2.setIdEpic(1);
        subtask2.setStartTime(LocalDateTime.parse("15.11.2021-12:21", formatter));
        subtask2.setDuration(34);
        String json2 = gson.toJson(subtask2);
        final HttpRequest.BodyPublisher body2 = HttpRequest.BodyPublishers.ofString(json2);
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(url2)
                .POST(body2)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient2 = HttpClient.newHttpClient();
        int responseCode2 = 0;
        String responseBody2 = "";
        try {
            HttpResponse<String> response2 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            responseCode2 = response2.statusCode();
            responseBody2 = response2.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode2, "Код ответа при создании не верный");
        assertEquals("Подзадача создана", responseBody2, "Тело ответа создания не совпадает");

        //запрос удаления
        URI urlForDelete = URI.create("http://localhost:8080/tasks/subtask/?id=1");
        HttpRequest requestForDelete = HttpRequest.newBuilder()
                .uri(urlForDelete)
                .DELETE()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForDel = HttpClient.newHttpClient();
        int responseCodeDel = 0;
        String responseBodyDel = "";
        try {
            HttpResponse<String> response = httpClientForDel.send(requestForDelete, HttpResponse.BodyHandlers.ofString());
            responseCodeDel = response.statusCode();
            responseBodyDel = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(200, responseCodeDel, "Код ответа при создании не верный");
        assertEquals("Удалена подзадача", responseBodyDel, "Тело ответа создания не совпадает");

    }

    @Test
    public void getAllTaskTest() {
        URI url = URI.create("http://localhost:8080/tasks/task/");
        Task task1 = new Task("Имя1", "Что купить1", NEW);
        task1.setStartTime(LocalDateTime.parse("15.01.2022-12:21", formatter));
        task1.setDuration(34);
        String json = gson.toJson(task1);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Задача создана", responseBody, "Тело ответа создания не совпадает");

        URI url2 = URI.create("http://localhost:8080/tasks/task/");
        Task task2 = new Task("Имя2", "Что купить2", NEW);
        task2.setStartTime(LocalDateTime.parse("15.03.2021-12:21", formatter));
        task2.setDuration(33);
        task2.setIdTask(1);
        String json2 = gson.toJson(task2);
        final HttpRequest.BodyPublisher body2 = HttpRequest.BodyPublishers.ofString(json2);
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(url2)
                .POST(body2)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient2 = HttpClient.newHttpClient();
        int responseCode2 = 0;
        String responseBody2 = "";
        try {
            HttpResponse<String> response2 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            responseCode2 = response2.statusCode();
            responseBody2 = response2.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode2, "Код ответа при создании не верный");
        assertEquals("Задача создана", responseBody2, "Тело ответа создания не совпадает");

        //запрос задач
        URI urlForGet = URI.create("http://localhost:8080/tasks/task/");
        HttpRequest requestForGet = HttpRequest.newBuilder()
                .uri(urlForGet)
                .GET()
                .header("Accept", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForGet = HttpClient.newHttpClient();
        int responseCodeGet = 0;
        Task taskGet1 = null;
        Task taskGet2 = null;
        try {
            HttpResponse<String> responseGet = httpClientForGet.send(requestForGet, HttpResponse.BodyHandlers.ofString());
            responseCodeGet = responseGet.statusCode();
            if (responseCodeGet == 200) {
                JsonElement jsonElement = JsonParser.parseString(responseGet.body());
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                taskGet1 = gson.fromJson(jsonArray.get(0), Task.class);
                taskGet2 = gson.fromJson(jsonArray.get(1), Task.class);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(200, responseCodeGet, "Код ответа при создании не верный");
        assertEquals(task1, taskGet1, "Задачи1 не совпадают");
        assertEquals(task2, taskGet2, "Задачи2 не совпадают");

    }

    @Test
    public void getTaskIDTest() {
        URI url = URI.create("http://localhost:8080/tasks/task/");
        Task task1 = new Task("Имя1", "Что купить1", NEW);
        task1.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        task1.setDuration(34);
        String json = gson.toJson(task1);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Задача создана", responseBody, "Тело ответа создания не совпадает");

        URI url2 = URI.create("http://localhost:8080/tasks/task/");
        Task task2 = new Task("Имя2", "Что купить2", NEW);
        task2.setStartTime(LocalDateTime.parse("15.11.2021-12:21", formatter));
        task2.setDuration(33);
        task2.setIdTask(1);
        String json2 = gson.toJson(task2);
        final HttpRequest.BodyPublisher body2 = HttpRequest.BodyPublishers.ofString(json2);
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(url2)
                .POST(body2)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient2 = HttpClient.newHttpClient();
        int responseCode2 = 0;
        String responseBody2 = "";
        try {
            HttpResponse<String> response2 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            responseCode2 = response2.statusCode();
            responseBody2 = response2.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode2, "Код ответа при создании не верный");
        assertEquals("Задача создана", responseBody2, "Тело ответа создания не совпадает");

        //запрос задач
        URI urlForGet = URI.create("http://localhost:8080/tasks/task/?id=0");
        HttpRequest requestForGet = HttpRequest.newBuilder()
                .uri(urlForGet)
                .GET()
                .header("Accept", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForGet = HttpClient.newHttpClient();
        int responseCodeGet = 0;
        Task taskGet1 = null;
        try {
            HttpResponse<String> responseGet = httpClientForGet.send(requestForGet, HttpResponse.BodyHandlers.ofString());
            responseCodeGet = responseGet.statusCode();
            if (responseCodeGet == 200) {
                JsonElement jsonElement = JsonParser.parseString(responseGet.body());
                taskGet1 = gson.fromJson(jsonElement, Task.class);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(200, responseCodeGet, "Код ответа при создании не верный");
        assertEquals(task1, taskGet1, "Задачи1 не совпадают");

    }


    @Test
    public void getAllEpicTest() {
        URI url = URI.create("http://localhost:8080/tasks/epic/");
        Epic epic = new Epic("Epic 1", "Der1", NEW);
        String json = gson.toJson(epic);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Эпик создан", responseBody, "Тело ответа создания не совпадает");

        URI url2 = URI.create("http://localhost:8080/tasks/epic/");
        Epic epic2 = new Epic("Epic 2", "Der2", NEW);
        epic2.setIdTask(1);
        String json2 = gson.toJson(epic2);
        final HttpRequest.BodyPublisher body2 = HttpRequest.BodyPublishers.ofString(json2);
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(url2)
                .POST(body2)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient2 = HttpClient.newHttpClient();
        int responseCode2 = 0;
        String responseBody2 = "";
        try {
            HttpResponse<String> response2 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            responseCode2 = response2.statusCode();
            responseBody2 = response2.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode2, "Код ответа при создании не верный");
        assertEquals("Эпик создан", responseBody2, "Тело ответа создания не совпадает");

        //запрос задач
        URI urlForGet = URI.create("http://localhost:8080/tasks/epic/");
        HttpRequest requestForGet = HttpRequest.newBuilder()
                .uri(urlForGet)
                .GET()
                .header("Accept", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForGet = HttpClient.newHttpClient();
        int responseCodeGet = 0;
        Epic epicGet1 = null;
        Epic epicGet2 = null;
        try {
            HttpResponse<String> responseGet = httpClientForGet.send(requestForGet, HttpResponse.BodyHandlers.ofString());
            responseCodeGet = responseGet.statusCode();
            if (responseCodeGet == 200) {
                JsonElement jsonElement = JsonParser.parseString(responseGet.body());
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                epicGet1 = gson.fromJson(jsonArray.get(0), Epic.class);
                epicGet2 = gson.fromJson(jsonArray.get(1), Epic.class);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(200, responseCodeGet, "Код ответа при создании не верный");
        assertEquals(epic, epicGet1, "Эпик1 не совпадает");
        assertEquals(epic2, epicGet2, "Эпик2 не совпадает");

    }

    @Test
    public void getEpicIDTest() {
        URI url = URI.create("http://localhost:8080/tasks/epic/");
        Epic epic = new Epic("Epic 1", "Der1", NEW);
        String json = gson.toJson(epic);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Эпик создан", responseBody, "Тело ответа создания не совпадает");

        URI url2 = URI.create("http://localhost:8080/tasks/epic/");
        Epic epic2 = new Epic("Epic 2", "Der2", NEW);
        epic2.setIdTask(1);
        String json2 = gson.toJson(epic2);
        final HttpRequest.BodyPublisher body2 = HttpRequest.BodyPublishers.ofString(json2);
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(url2)
                .POST(body2)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient2 = HttpClient.newHttpClient();
        int responseCode2 = 0;
        String responseBody2 = "";
        try {
            HttpResponse<String> response2 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            responseCode2 = response2.statusCode();
            responseBody2 = response2.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode2, "Код ответа при создании не верный");
        assertEquals("Эпик создан", responseBody2, "Тело ответа создания не совпадает");

        //запрос задач
        URI urlForGet = URI.create("http://localhost:8080/tasks/epic/?id=1");
        HttpRequest requestForGet = HttpRequest.newBuilder()
                .uri(urlForGet)
                .GET()
                .header("Accept", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForGet = HttpClient.newHttpClient();
        int responseCodeGet = 0;
        Epic epicGet1 = null;
        try {
            HttpResponse<String> responseGet = httpClientForGet.send(requestForGet, HttpResponse.BodyHandlers.ofString());
            responseCodeGet = responseGet.statusCode();
            if (responseCodeGet == 200) {
                JsonElement jsonElement = JsonParser.parseString(responseGet.body());
                epicGet1 = gson.fromJson(jsonElement, Epic.class);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(200, responseCodeGet, "Код ответа при создании не верный");
        assertEquals(epic2, epicGet1, "Эпик не совпадает");

    }


    @Test
    public void getAllSubtaskTest() {
        URI url = URI.create("http://localhost:8080/tasks/subtask/");
        Subtask subtask = new Subtask("Саб1", "Где взять", NEW);
        subtask.setIdEpic(1);
        subtask.setStartTime(LocalDateTime.parse("15.08.2020-12:21", formatter));
        subtask.setDuration(34);
        String json = gson.toJson(subtask);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Подзадача создана", responseBody, "Тело ответа создания не совпадает");

        URI url2 = URI.create("http://localhost:8080/tasks/subtask/");
        Subtask subtask2 = new Subtask("Саб1", "Где взять", NEW);
        subtask2.setIdTask(1);
        subtask2.setIdEpic(1);
        subtask2.setStartTime(LocalDateTime.parse("15.07.2023-12:21", formatter));
        subtask2.setDuration(34);
        String json2 = gson.toJson(subtask2);
        final HttpRequest.BodyPublisher body2 = HttpRequest.BodyPublishers.ofString(json2);
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(url2)
                .POST(body2)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient2 = HttpClient.newHttpClient();
        int responseCode2 = 0;
        String responseBody2 = "";
        try {
            HttpResponse<String> response2 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            responseCode2 = response2.statusCode();
            responseBody2 = response2.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode2, "Код ответа при создании не верный");
        assertEquals("Подзадача создана", responseBody2, "Тело ответа создания не совпадает");

        //запрос задач
        URI urlForGet = URI.create("http://localhost:8080/tasks/subtask/");
        HttpRequest requestForGet = HttpRequest.newBuilder()
                .uri(urlForGet)
                .GET()
                .header("Accept", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForGet = HttpClient.newHttpClient();
        int responseCodeGet = 0;
        Subtask subtaskGet1 = null;
        Subtask subtaskGet2 = null;
        try {
            HttpResponse<String> responseGet = httpClientForGet.send(requestForGet, HttpResponse.BodyHandlers.ofString());
            responseCodeGet = responseGet.statusCode();
            if (responseCodeGet == 200) {
                JsonElement jsonElement = JsonParser.parseString(responseGet.body());
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                subtaskGet1 = gson.fromJson(jsonArray.get(0), Subtask.class);
                subtaskGet2 = gson.fromJson(jsonArray.get(1), Subtask.class);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(200, responseCodeGet, "Код ответа при создании не верный");
        assertEquals(subtask, subtaskGet1, "Подзадача1 не совпадает");
        assertEquals(subtask2, subtaskGet2, "Подзадача2 не совпадает");

    }

    @Test
    public void getSubtaskIDTest() {
        URI url = URI.create("http://localhost:8080/tasks/subtask/");
        Subtask subtask = new Subtask("Саб1", "Где взять", NEW);
        subtask.setIdEpic(1);
        subtask.setStartTime(LocalDateTime.parse("15.11.2021-12:21", formatter));
        subtask.setDuration(34);
        String json = gson.toJson(subtask);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Подзадача создана", responseBody, "Тело ответа создания не совпадает");

        URI url2 = URI.create("http://localhost:8080/tasks/subtask/");
        Subtask subtask2 = new Subtask("Саб1", "Где взять", NEW);
        subtask2.setIdEpic(1);
        subtask2.setIdTask(1);
        subtask2.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        subtask2.setDuration(34);
        String json2 = gson.toJson(subtask2);
        final HttpRequest.BodyPublisher body2 = HttpRequest.BodyPublishers.ofString(json2);
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(url2)
                .POST(body2)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient2 = HttpClient.newHttpClient();
        int responseCode2 = 0;
        String responseBody2 = "";
        try {
            HttpResponse<String> response2 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            responseCode2 = response2.statusCode();
            responseBody2 = response2.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode2, "Код ответа при создании не верный");
        assertEquals("Подзадача создана", responseBody2, "Тело ответа создания не совпадает");

        //запрос задач
        URI urlForGet = URI.create("http://localhost:8080/tasks/subtask/?id=1");
        HttpRequest requestForGet = HttpRequest.newBuilder()
                .uri(urlForGet)
                .GET()
                .header("Accept", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForGet = HttpClient.newHttpClient();
        int responseCodeGet = 0;
        Subtask subtaskGet1 = null;
        try {
            HttpResponse<String> responseGet = httpClientForGet.send(requestForGet, HttpResponse.BodyHandlers.ofString());
            responseCodeGet = responseGet.statusCode();
            if (responseCodeGet == 200) {
                JsonElement jsonElement = JsonParser.parseString(responseGet.body());
                subtaskGet1 = gson.fromJson(jsonElement, Subtask.class);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(200, responseCodeGet, "Код ответа при создании не верный");
        assertEquals(subtask2, subtaskGet1, "Подзадача не совпадает");

    }


    @Test
    public void getSubtaskOfIDEpicTest() {

        URI url = URI.create("http://localhost:8080/tasks/epic/");
        Epic epic = new Epic("Epic 1", "Der1", NEW);
        String json = gson.toJson(epic);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Эпик создан", responseBody, "Тело ответа создания не совпадает");

        URI url1 = URI.create("http://localhost:8080/tasks/subtask/");
        Subtask subtask1 = new Subtask("Саб1", "Где взять", NEW);
        subtask1.setIdEpic(0);
        subtask1.setIdTask(1);
        subtask1.setStartTime(LocalDateTime.parse("15.12.2018-12:21", formatter));
        subtask1.setDuration(34);
        String json1 = gson.toJson(subtask1);
        final HttpRequest.BodyPublisher body1 = HttpRequest.BodyPublishers.ofString(json1);
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(url1)
                .POST(body1)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient1 = HttpClient.newHttpClient();
        int responseCode1 = 0;
        String responseBody1 = "";
        try {
            HttpResponse<String> response1 = httpClient1.send(request1, HttpResponse.BodyHandlers.ofString());
            responseCode1 = response1.statusCode();
            responseBody1 = response1.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode1, "Код ответа при создании не верный");
        assertEquals("Подзадача создана", responseBody1, "Тело ответа создания не совпадает");

        URI url2 = URI.create("http://localhost:8080/tasks/subtask/");
        Subtask subtask2 = new Subtask("Саб2", "Где взять2", NEW);
        subtask2.setIdEpic(0);
        subtask2.setIdTask(2);
        subtask2.setStartTime(LocalDateTime.parse("15.11.2019-12:21", formatter));
        subtask2.setDuration(32);
        String json2 = gson.toJson(subtask2);
        final HttpRequest.BodyPublisher body2 = HttpRequest.BodyPublishers.ofString(json2);
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(url2)
                .POST(body2)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient2 = HttpClient.newHttpClient();
        int responseCode2 = 0;
        String responseBody2 = "";
        try {
            HttpResponse<String> response2 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            responseCode2 = response2.statusCode();
            responseBody2 = response2.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode2, "Код ответа при создании не верный");
        assertEquals("Подзадача создана", responseBody2, "Тело ответа создания не совпадает");

        //запрос задач
        URI urlForGet = URI.create("http://localhost:8080/tasks/epic/?id=0");
        HttpRequest requestForGet = HttpRequest.newBuilder()
                .uri(urlForGet)
                .GET()
                .header("Accept", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForGet = HttpClient.newHttpClient();
        int responseCodeGet = 0;
        Subtask subtaskGet1 = null;
        Subtask subtaskGet2 = null;
        try {
            HttpResponse<String> responseGet = httpClientForGet.send(requestForGet, HttpResponse.BodyHandlers.ofString());
            responseCodeGet = responseGet.statusCode();
            if (responseCodeGet == 200) {
                JsonElement jsonElement = JsonParser.parseString(responseGet.body());
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray("subtasksOfEpic");
                subtaskGet1 = gson.fromJson(jsonArray.get(0), Subtask.class);
                subtaskGet2 = gson.fromJson(jsonArray.get(1), Subtask.class);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(200, responseCodeGet, "Код ответа при создании не верный");
        assertEquals(subtask1, subtaskGet1, "Подзадача1 не совпадает");
        assertEquals(subtask2, subtaskGet2, "Подзадача2 не совпадает");
    }

    @Test
    public void getHisoryTest() {

        URI url = URI.create("http://localhost:8080/tasks/epic/");
        Epic epic = new Epic("Epic 1", "Der1", NEW);
        String json = gson.toJson(epic);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Эпик создан", responseBody, "Тело ответа создания не совпадает");

        URI url1 = URI.create("http://localhost:8080/tasks/subtask/");
        Subtask subtask1 = new Subtask("Саб1", "Где взять", NEW);
        subtask1.setIdEpic(0);
        subtask1.setIdTask(1);
        subtask1.setStartTime(LocalDateTime.parse("15.12.2018-12:21", formatter));
        subtask1.setDuration(34);
        String json1 = gson.toJson(subtask1);
        final HttpRequest.BodyPublisher body1 = HttpRequest.BodyPublishers.ofString(json1);
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(url1)
                .POST(body1)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient1 = HttpClient.newHttpClient();
        int responseCode1 = 0;
        String responseBody1 = "";
        try {
            HttpResponse<String> response1 = httpClient1.send(request1, HttpResponse.BodyHandlers.ofString());
            responseCode1 = response1.statusCode();
            responseBody1 = response1.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode1, "Код ответа при создании не верный");
        assertEquals("Подзадача создана", responseBody1, "Тело ответа создания не совпадает");

        URI url2 = URI.create("http://localhost:8080/tasks/task/");
        Task task2 = new Task("Имя1", "Что купить1", NEW);
        task2.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        task2.setDuration(34);
        task2.setIdTask(2);
        String json2 = gson.toJson(task2);
        final HttpRequest.BodyPublisher body2 = HttpRequest.BodyPublishers.ofString(json2);
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(url2)
                .POST(body2)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient2 = HttpClient.newHttpClient();
        int responseCode2 = 0;
        String responseBody2 = "";
        try {
            HttpResponse<String> response2 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            responseCode2 = response2.statusCode();
            responseBody2 = response2.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode2, "Код ответа при создании не верный");
        assertEquals("Задача создана", responseBody2, "Тело ответа создания не совпадает");

        //запрос задач
        URI urlForGet = URI.create("http://localhost:8080/tasks/subtask/?id=1");
        HttpRequest requestForGet = HttpRequest.newBuilder()
                .uri(urlForGet)
                .GET()
                .header("Accept", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForGet = HttpClient.newHttpClient();
        int responseCodeGet = 0;
        Subtask subtaskGet1 = null;
        try {
            HttpResponse<String> responseGet = httpClientForGet.send(requestForGet, HttpResponse.BodyHandlers.ofString());
            responseCodeGet = responseGet.statusCode();
            if (responseCodeGet == 200) {
                JsonElement jsonElement = JsonParser.parseString(responseGet.body());
                subtaskGet1 = gson.fromJson(jsonElement, Subtask.class);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(200, responseCodeGet, "Код ответа при создании не верный");
        assertEquals(subtask1, subtaskGet1, "Подзадача не совпадает");

        //запрос задач
        URI urlForGet2 = URI.create("http://localhost:8080/tasks/task/?id=2");
        HttpRequest requestForGet2 = HttpRequest.newBuilder()
                .uri(urlForGet2)
                .GET()
                .header("Accept", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForGet2 = HttpClient.newHttpClient();
        int responseCodeGet2 = 0;
        Task taskGet2 = null;
        try {
            HttpResponse<String> responseGet2 = httpClientForGet2.send(requestForGet2, HttpResponse.BodyHandlers.ofString());
            responseCodeGet2 = responseGet2.statusCode();
            if (responseCodeGet2 == 200) {
                JsonElement jsonElement = JsonParser.parseString(responseGet2.body());
                taskGet2 = gson.fromJson(jsonElement, Task.class);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(200, responseCodeGet2, "Код ответа при создании не верный");
        assertEquals(task2, taskGet2, "Задачи1 не совпадают");

        //запрос истории
        URI urlForHis = URI.create("http://localhost:8080/tasks/history");
        HttpRequest requestForHis = HttpRequest.newBuilder()
                .uri(urlForHis)
                .GET()
                .header("Accept", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForHis = HttpClient.newHttpClient();
        int responseCodeHis = 0;
        Subtask subtaskHis1 = null;
        Task taskHis2 = null;
        try {
            HttpResponse<String> responseHis = httpClientForHis.send(requestForHis, HttpResponse.BodyHandlers.ofString());
            responseCodeHis = responseHis.statusCode();
            if (responseCodeHis == 200) {
                JsonElement jsonElement = JsonParser.parseString(responseHis.body());
                JsonArray jsonArray = jsonElement.getAsJsonArray();

                subtaskHis1 = gson.fromJson(jsonArray.get(0), Subtask.class);
                taskHis2 = gson.fromJson(jsonArray.get(1), Task.class);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(200, responseCodeHis, "Код ответа при создании не верный");
        assertEquals(subtask1, subtaskHis1, "Подзадача1 не совпадает");
        assertEquals(task2, taskHis2, "Задача2 не совпадает");


    }

    @Test
    public void getAllTaskEpicSubtaskTest() {

        URI url = URI.create("http://localhost:8080/tasks/epic/");
        Epic epic = new Epic("Epic 1", "Der1", NEW);
        String json = gson.toJson(epic);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .POST(body)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        int responseCode = 0;
        String responseBody = "";
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode, "Код ответа при создании не верный");
        assertEquals("Эпик создан", responseBody, "Тело ответа создания не совпадает");

        URI url1 = URI.create("http://localhost:8080/tasks/subtask/");
        Subtask subtask1 = new Subtask("Саб1", "Где взять", NEW);
        subtask1.setIdEpic(0);
        subtask1.setIdTask(1);
        subtask1.setStartTime(LocalDateTime.parse("15.12.2018-12:21", formatter));
        subtask1.setDuration(34);
        String json1 = gson.toJson(subtask1);
        final HttpRequest.BodyPublisher body1 = HttpRequest.BodyPublishers.ofString(json1);
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(url1)
                .POST(body1)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient1 = HttpClient.newHttpClient();
        int responseCode1 = 0;
        String responseBody1 = "";
        try {
            HttpResponse<String> response1 = httpClient1.send(request1, HttpResponse.BodyHandlers.ofString());
            responseCode1 = response1.statusCode();
            responseBody1 = response1.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode1, "Код ответа при создании не верный");
        assertEquals("Подзадача создана", responseBody1, "Тело ответа создания не совпадает");

        URI url2 = URI.create("http://localhost:8080/tasks/task/");
        Task task2 = new Task("Имя1", "Что купить1", NEW);
        task2.setStartTime(LocalDateTime.parse("15.11.2022-12:21", formatter));
        task2.setDuration(34);
        task2.setIdTask(2);
        String json2 = gson.toJson(task2);
        final HttpRequest.BodyPublisher body2 = HttpRequest.BodyPublishers.ofString(json2);
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(url2)
                .POST(body2)
                .header("Content-Type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient2 = HttpClient.newHttpClient();
        int responseCode2 = 0;
        String responseBody2 = "";
        try {
            HttpResponse<String> response2 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            responseCode2 = response2.statusCode();
            responseBody2 = response2.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(201, responseCode2, "Код ответа при создании не верный");
        assertEquals("Задача создана", responseBody2, "Тело ответа создания не совпадает");

        //запрос всех задач
        URI urlForAll = URI.create("http://localhost:8080/tasks/");
        HttpRequest requestForAll = HttpRequest.newBuilder()
                .uri(urlForAll)
                .GET()
                .header("Accept", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClientForAll = HttpClient.newHttpClient();
        int responseCodeAll = 0;
        Subtask subtaskAll1 = null;
        Task taskAll2 = null;
        try {
            HttpResponse<String> responseAll = httpClientForAll.send(requestForAll, HttpResponse.BodyHandlers.ofString());
            responseCodeAll = responseAll.statusCode();
            if (responseCodeAll == 200) {
                JsonElement jsonElement = JsonParser.parseString(responseAll.body());
                JsonArray jsonArray = jsonElement.getAsJsonArray();

                subtaskAll1 = gson.fromJson(jsonArray.get(0), Subtask.class);
                taskAll2 = gson.fromJson(jsonArray.get(1), Task.class);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(200, responseCodeAll, "Код ответа при создании не верный");
        assertEquals(subtask1, subtaskAll1, "Подзадача1 не совпадает");
        assertEquals(task2, taskAll2, "Задача2 не совпадает");

    }

}




