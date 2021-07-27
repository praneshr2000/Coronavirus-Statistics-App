package com.example.coronavirus_stats_app.Services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GetCoronaVirusDataService {

    @PostConstruct
    public void getDataFromURL() throws IOException, InterruptedException {

        // URls
        // TODO: Update the URLs according to the system's time
        String previousDayURL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/07-25-2021.csv";
        String currentDayURL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/07-26-2021.csv";

        // Build the HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Request from previous day's URL for data
        HttpRequest previousDayRequest = HttpRequest.newBuilder()
                .uri(URI.create(previousDayURL))
                .build();

        // Store the previous day's HTTP response
        HttpResponse<String> previousDayResponse =
                client.send(previousDayRequest, HttpResponse.BodyHandlers.ofString());

        // Request from current day's URL for data
        HttpRequest currentDayRequest = HttpRequest.newBuilder()
                .uri(URI.create(currentDayURL))
                .build();

        // Store the current day's HTTP response
        HttpResponse<String> currentDayResponse =
                client.send(currentDayRequest, HttpResponse.BodyHandlers.ofString());


    }

}
