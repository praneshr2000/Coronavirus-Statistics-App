package com.example.coronavirus_stats_app.Services;

import com.example.coronavirus_stats_app.DataModels.PlaceData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
@EnableScheduling
public class CoronaVirusDataService {

    private final List<PlaceData> allData = new ArrayList<>();
    private final Map<String, List<PlaceData>> countriesWithProvinceMap = new HashMap<>();
    private final Map<String, PlaceData> countriesWithoutProvinceMap = new HashMap<>();
    private final Map<String, List<PlaceData>> USStateToCountyMap = new TreeMap<>();
    private final Map<String, List<String>> countryToISO2Code = new HashMap<>();

    @PostConstruct
    @Scheduled(cron = "0 45 7 ? * *", zone = "UTC")
    public void getDataFromURL() throws IOException, InterruptedException {
        // The above @Scheduled cron executes this method everyday at 7:45am UTC

        allData.clear();
        countriesWithoutProvinceMap.clear();
        countriesWithProvinceMap.clear();
        USStateToCountyMap.clear();
        countryToISO2Code.clear();

        // Date Format for URL
        SimpleDateFormat dateFormat = new SimpleDateFormat( "MM-dd-yyyy" );
        // Set UTC time zone
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        // Get the dates
        String currentDate = dateFormat.format(Date.from(Instant.now().minus(Duration.ofHours(24))));
        String oldDate = dateFormat.format(Date.from(Instant.now().minus(Duration.ofHours(48))));

        // URls
        String previousDayURL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/" +
                                "csse_covid_19_data/csse_covid_19_daily_reports/" + oldDate + ".csv";
        String currentDayURL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/" +
                                "csse_covid_19_data/csse_covid_19_daily_reports/" + currentDate + ".csv";

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

        getISOCodesAndFlagURL();
        readDataFromURLs(previousDayResponse, currentDayResponse);

    }

    public void getISOCodesAndFlagURL() throws IOException, InterruptedException {

        // Initialize with some exceptions (Refer to README UIUD lookup table logic)
        // FOR CRUISE SHIPS INITIALIZE TO EMPTY STRING
        countryToISO2Code.put("Diamond Princess", new ArrayList<>(Arrays.asList("", "")));
        countryToISO2Code.put("MS Zaandam", new ArrayList<>(Arrays.asList("", "")));

        countryToISO2Code.put("Denmark", new ArrayList<>(Arrays.asList("DK", "https://flagcdn.com/h120/dk.png")));
        countryToISO2Code.put("France", new ArrayList<>(Arrays.asList("FR", "https://flagcdn.com/h120/fr.png")));
        countryToISO2Code.put("Netherlands", new ArrayList<>(Arrays.asList("NL", "https://flagcdn.com/h120/nl.png")));
        countryToISO2Code.put("United Kingdom", new ArrayList<>(Arrays.asList("GB", "https://flagcdn.com/h120/gb.png")));
        countryToISO2Code.put("Australia", new ArrayList<>(Arrays.asList("AU", "https://flagcdn.com/h120/au.png")));
        countryToISO2Code.put("Canada", new ArrayList<>(Arrays.asList("CA", "https://flagcdn.com/h120/ca.png")));
        countryToISO2Code.put("China", new ArrayList<>(Arrays.asList("CN", "https://flagcdn.com/h120/cn.png")));
        countryToISO2Code.put("Germany", new ArrayList<>(Arrays.asList("DE", "https://flagcdn.com/h120/de.png")));
        countryToISO2Code.put("Italy", new ArrayList<>(Arrays.asList("IT", "https://flagcdn.com/h120/it.png")));
        countryToISO2Code.put("US", new ArrayList<>(Arrays.asList("US", "https://flagcdn.com/h120/us.png")));

        String UIDLookUpTableLink = "https://raw.githubusercontent.com/CSSEGISandData/" +
                "COVID-19/master/csse_covid_19_data/UID_ISO_FIPS_LookUp_Table.csv";

        // Build the HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Request from UID URL for data
        HttpRequest UIDRequest = HttpRequest.newBuilder()
                .uri(URI.create(UIDLookUpTableLink))
                .build();

        // Store the UID HTTP response
        HttpResponse<String> UIDResponse =
                client.send(UIDRequest, HttpResponse.BodyHandlers.ofString());

        // Create StringReader
        Reader UIDIn = new StringReader(UIDResponse.body());

        // Create Iterable CSVRecord with excluding the Header line
        Iterable<CSVRecord> UIDRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(UIDIn);

        // Create an iterator
        for (CSVRecord uRecord : UIDRecords) {
            String country = uRecord.get(7);
            String iso2 = uRecord.get(1);
            if (!countryToISO2Code.containsKey(country)) {
                String flagURL = "https://flagcdn.com/h120/" + iso2.toLowerCase() + ".png";
                countryToISO2Code.put(country, new ArrayList<>(Arrays.asList(iso2, flagURL)));
            }
        }
    }

    public void readDataFromURLs(HttpResponse<String> previousDayResponse,
                                 HttpResponse<String> currentDayResponse) throws IOException {

        // Create StringReaders for the response body of previous day and current day
        Reader prevDayIn = new StringReader(previousDayResponse.body());
        Reader currDayIn = new StringReader(currentDayResponse.body());

        // Create Iterable CSVRecord with excluding the Header line
        Iterable<CSVRecord> prevRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(prevDayIn);
        Iterable<CSVRecord> currRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(currDayIn);

        // Create an iterator
        Iterator<CSVRecord> prevIterator = prevRecords.iterator();
        Iterator<CSVRecord> currIterator = currRecords.iterator();
        while(prevIterator.hasNext() && currIterator.hasNext()){
            CSVRecord pRecord = prevIterator.next();
            CSVRecord cRecord = currIterator.next();

            // If FIPS is not present, we set it to -1
            // If Latitude is not given, we set it to positive infinity
            // If Longitude is not given, we set it to positive infinity
            // If Case Fertility Ratio is not given, we set it to positive infinity
            // If Incident rate is not given, we set it to positive infinity
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
                    Integer.parseInt(cRecord.get("Deaths")) - Integer.parseInt(pRecord.get("Deaths")),
                    countryToISO2Code.get(cRecord.get("Country_Region")).get(0),
                    countryToISO2Code.get(cRecord.get("Country_Region")).get(1));


            // Check if the country is US in the current row
            if (pRecord.get("Country_Region").equals("US") && cRecord.get("Country_Region").equals("US")) {
                String state = pRecord.get("Province_State");

                // Add each state as key, and add counties to the value list
                if (!USStateToCountyMap.containsKey(state)) {
                    List<PlaceData> list = new ArrayList<>();
                    list.add(data);
                    USStateToCountyMap.put(state, list);
                } else {
                    USStateToCountyMap.get(state).add(data);
                }

            // Check if the country has province or state data
            } else if (!pRecord.get("Province_State").equals("") && !cRecord.get("Province_State").equals("")){
                String country = pRecord.get("Country_Region");

                // Add country as a key and add province/state data in the value list
                if (!countriesWithProvinceMap.containsKey(country)) {
                    List<PlaceData> list = new ArrayList<>();
                    list.add(data);
                    countriesWithProvinceMap.put(country, list);
                } else {
                    countriesWithProvinceMap.get(country).add(data);
                }
            } else {
                // If the country is not US and does not have any province or state data
                // add the country name as the key and its PlaceData as object
                countriesWithoutProvinceMap.put(cRecord.get("Country_Region"), data);
            }
            // All rows in the data are added to this list
            allData.add(data);
        }
    }

    /*
    *
    * Getter methods below
    *
    * */
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

    public Map<String, List<String>> getCountryToISO2Code() {
        return countryToISO2Code;
    }
}
