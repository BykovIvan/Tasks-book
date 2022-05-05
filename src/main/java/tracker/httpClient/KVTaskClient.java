package main.java.tracker.httpClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class KVTaskClient {
    private String url;
    private String API_KEY;

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
            API_KEY = response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // TODO Получение апи через сервер
    }

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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TODO сохраняет состояние менеджера
        //  через POST /save/<ключ>?API_KEY=
    }

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
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        //TODO должен возвращать состояние менеджера задач через
        // запрос GET /load/<ключ>?API_KEY=
        return line;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public String getAPI_KEY() {
        return API_KEY;
    }
}
