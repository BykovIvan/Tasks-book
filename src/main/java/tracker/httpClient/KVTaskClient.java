package main.java.tracker.httpClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Класс для работы с KVserver
 *
 */
public class KVTaskClient {
    private String url;
    private final String API_KEY;

    /**
     * В конструкторе получаем АПИКЛЮЧ для авторизации, необходимо что бы можно было иметь несколько клинтов
     *
     * @param url
     */
    public KVTaskClient(String url) {
        this.url = url;
        URI uri = URI.create(url + "/register");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            API_KEY = response.body(); //получаю при создании объекта клиента для КВ сервера
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод для того что бы сохранить состояние менеджера по ключу
     * ключ придумаеваем сами
     *
     * @param key
     * @param json
     */
    public void put(String key, String json){
        URI uri = URI.create(url + "/save/" + key + "/?API_KEY=" + API_KEY);
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(body)
                .uri(uri)
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Код ответа: " + response.statusCode());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод восстановления состояния менеджера по ключу
     *
     * @param key
     * @return
     */
    public String load(String key){
        String line = null;
        URI uri = URI.create(url + "/load/" + key + "/?API_KEY=" + API_KEY);
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            line = response.body();
            System.out.println("Код ответа: " + response.statusCode());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return line;
    }

    public String getAPI_KEY() {
        return API_KEY;
    }
}
