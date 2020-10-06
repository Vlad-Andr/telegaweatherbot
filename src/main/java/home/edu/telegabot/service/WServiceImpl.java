package home.edu.telegabot.service;

import com.google.gson.Gson;
import home.edu.telegabot.model.WeatherResponce;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static home.edu.telegabot.service.ApiConstans.API_KEY;
import static home.edu.telegabot.service.ApiConstans.API_URL;

@Service
public class WServiceImpl implements WeatherService{

    @SneakyThrows
    public String getCityName(String city) {

        Gson gson = new Gson();

        HttpClient build = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(API_URL + city + API_KEY))
                .build();

        HttpResponse<String> response = build.send(request, HttpResponse.BodyHandlers.ofString());

        WeatherResponce json = gson.fromJson(response.body(), WeatherResponce.class);

        return json.data[0].toString();
    }
}