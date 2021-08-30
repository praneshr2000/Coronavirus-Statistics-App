package com.example.coronavirus_stats_app.Services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class NewsService {

    public void callNewsAPI() throws IOException {
        URL url = new URL("https://api.newscatcherapi.com/v2/search?q=Tesla");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("x-api-key", "dBAawuV3SL3ryr_I4Jvzaxfj6Jgb8KoidmiTn0IlJNA");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();


    }
}
