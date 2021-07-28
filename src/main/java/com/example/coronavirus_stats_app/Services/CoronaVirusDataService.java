package com.example.coronavirus_stats_app.Services;

import com.example.coronavirus_stats_app.DataModels.PlaceData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class CoronaVirusDataService {

    private List<PlaceData> allData = new ArrayList<>();
    private Map<String, List<PlaceData>> countriesWithProvinceMap = new HashMap<>();
    private Map<String, PlaceData> countriesWithoutProvinceMap = new HashMap<>();
    private Map<String, List<PlaceData>> USStateToCountyMap = new HashMap<>();

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


        readDataFromURLs(previousDayResponse, currentDayResponse);

    }

    public void readDataFromURLs(HttpResponse<String> previousDayResponse,
                                 HttpResponse<String> currentDayResponse) throws IOException {

        Reader prevDayIn = new StringReader(previousDayResponse.body());
        Reader currDayIn = new StringReader(currentDayResponse.body());

        Iterable<CSVRecord> prevRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(prevDayIn);
        Iterable<CSVRecord> currRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(currDayIn);

        Iterator<CSVRecord> prevIterator = prevRecords.iterator();
        Iterator<CSVRecord> currIterator = currRecords.iterator();
        while(prevIterator.hasNext() && currIterator.hasNext()){
            CSVRecord pRecord = prevIterator.next();
            CSVRecord cRecord = currIterator.next();

            // If FIPS is not present, we set it to -1
            // If Latitude, Longitude, incident rate, case fertility ratio is not given, we set it to positive infinity
            PlaceData data = new PlaceData((cRecord.get("FIPS").isEmpty()) ? -1 : Integer.parseInt(cRecord.get("FIPS")),
                    cRecord.get("Admin2"),
                    cRecord.get("Province_State"),
                    cRecord.get("Country_Region"),
                    cRecord.get("Country_Region").equals("US"),
                    !cRecord.get("Province_State").equals(""),
                    cRecord.get("Last_Update"),
                    cRecord.get("Lat").isEmpty() ? Float.POSITIVE_INFINITY : Float.parseFloat(cRecord.get("Lat")),
                    cRecord.get("Long_").isEmpty() ? Float.POSITIVE_INFINITY : Float.parseFloat(cRecord.get("Long_")),
                    Integer.parseInt(cRecord.get("Confirmed")),
                    Integer.parseInt(cRecord.get("Deaths")),
                    cRecord.get("Combined_Key"),
                    cRecord.get("Incident_Rate").isEmpty() ? Float.POSITIVE_INFINITY : Float.parseFloat(cRecord.get("Incident_Rate")),
                    cRecord.get("Case_Fatality_Ratio").isEmpty() ? Float.POSITIVE_INFINITY : Float.parseFloat(cRecord.get("Case_Fatality_Ratio")),
                    Integer.parseInt(cRecord.get("Confirmed")) - Integer.parseInt(pRecord.get("Confirmed")),
                    Integer.parseInt(cRecord.get("Deaths")) - Integer.parseInt(pRecord.get("Deaths")));


            if (pRecord.get("Country_Region").equals("US") && cRecord.get("Country_Region").equals("US")) {
                String state = pRecord.get("Province_State");

                if (!USStateToCountyMap.containsKey(state)) {
                    List<PlaceData> list = new ArrayList<>();
                    list.add(data);
                    USStateToCountyMap.put(state, list);
                } else {
                    USStateToCountyMap.get(state).add(data);
                }

            } else if (!pRecord.get("Province_State").equals("") && !cRecord.get("Province_State").equals("")){
                String country = pRecord.get("Country_Region");

                if (!countriesWithProvinceMap.containsKey(country)) {
                    List<PlaceData> list = new ArrayList<>();
                    list.add(data);
                    countriesWithProvinceMap.put(country, list);
                } else {
                    countriesWithProvinceMap.get(country).add(data);
                }
            } else {
                countriesWithoutProvinceMap.put(cRecord.get("Country_Region"), data);
            }
            allData.add(data);
        }

    }

    public List<PlaceData> getAllData() {
        return allData;
    }

    public Map<String, List<PlaceData>> getCountriesWithProvinceMap() {
        return countriesWithProvinceMap;
    }

    public Map<String, List<PlaceData>> getUSStateToCountyMap() {
        return USStateToCountyMap;
    }

    public Map<String, PlaceData> getCountriesWithoutProvinceMap() {
        return countriesWithoutProvinceMap;
    }
}
