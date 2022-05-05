package main.java.tracker.httpClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class KVTaskClient {
    private String url;
    private String key;

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
            key = response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // TODO Получение апи через сервер
    }

    public void put(String key, String json){
        // TODO сохраняет состояние менеджера
        //  через POST /save/<ключ>?API_KEY=
    }

    public String load(String key){
        //TODO должен возвращать состояние менеджера задач через
        // запрос GET /load/<ключ>?API_KEY=
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
