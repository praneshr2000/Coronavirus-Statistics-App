package com.example.coronavirus_stats_app.Services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

@Service
@EnableScheduling
public class NewsService {

    private JSONObject newsJSONObject = new JSONObject();

    @PostConstruct
    @Scheduled(cron = "0 30 12 ? * *", zone = "UTC")
    public void callNewsAPI() throws IOException, InterruptedException, JSONException {

        // Date Format for URL
        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd" );
        // Set UTC time zone
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        // Get the dates
        String toDate = dateFormat.format(Date.from(Instant.now()));
        String fromDate = dateFormat.format(Date.from(Instant.now().minus(Duration.ofHours(24))));

        // URL with set parameters. Refer: https://docs.newscatcherapi.com/api-docs/endpoints/search-news
        URL url = new URL("https://api.newscatcherapi.com/v2/search?q=coronavirus%20OR%20covid-19&sort_by=rank&lang=en&to="
                                + toDate + "&from=" + fromDate + "&search_in=title");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        // Set the API key
        http.setRequestProperty("x-api-key", "dBAawuV3SL3ryr_I4Jvzaxfj6Jgb8KoidmiTn0IlJNA");

        System.out.println("LOLOLOLOLOLOLOLLO");
        // Input stream and string builder
        InputStream stream = http.getInputStream();
        StringBuilder builder = new StringBuilder();

        try (Reader reader = new BufferedReader(new InputStreamReader
                (stream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                builder.append((char) c);
            }
        }

        // Convert String to JSON object and store it in the field
        newsJSONObject = new JSONObject(builder.toString());

        System.out.println(newsJSONObject);
        http.disconnect();
    }

    public JSONObject getNewsJSONObject() {
        return newsJSONObject;
    }
}
