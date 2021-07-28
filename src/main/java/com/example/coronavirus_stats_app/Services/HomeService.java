package com.example.coronavirus_stats_app.Services;

import com.example.coronavirus_stats_app.DataModels.HomepageData;
import com.example.coronavirus_stats_app.DataModels.PlaceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class HomeService {

    private final CoronaVirusDataService coronaVirusDataService;

    @Autowired
    public HomeService(CoronaVirusDataService coronaVirusDataService) {
        this.coronaVirusDataService = coronaVirusDataService;
    }

    @PostConstruct
    public HomepageData getAllData() {
        int globalConfirmedCases = 0;
        int globalNewCases = 0;
        int globalDeaths = 0;
        int globalNewDeaths = 0;
        Set<HomepageData.CountryData> countryDataSet = new TreeSet<>();

        // Countries with no province data
        for(String country: coronaVirusDataService.getCountriesWithoutProvinceMap().keySet()) {
            PlaceData placeData = coronaVirusDataService.getCountriesWithoutProvinceMap().get(country);
            HomepageData.CountryData countryData = new HomepageData.CountryData(
                    placeData.getCountryName(),
                    placeData.getConfirmed(),
                    placeData.getDeaths(),
                    placeData.getNewCases(),
                    placeData.getNewDeaths()
            );
            countryDataSet.add(countryData);
            globalConfirmedCases += placeData.getConfirmed();
            globalNewCases += placeData.getNewCases();
            globalDeaths += placeData.getDeaths();
            globalNewDeaths += placeData.getNewDeaths();
        }

        // Countries with province data
        for (String country: coronaVirusDataService.getCountriesWithProvinceMap().keySet()) {
            int countryConfirmedCases = 0;
            int countryDeaths = 0;
            int countryNewCases = 0;
            int countryNewDeaths = 0;

            for (PlaceData placeData: coronaVirusDataService.getCountriesWithProvinceMap().get(country)) {
                countryConfirmedCases += placeData.getConfirmed();
                countryDeaths += placeData.getDeaths();
                countryNewCases += placeData.getNewCases();
                countryNewDeaths += placeData.getNewDeaths();
            }

            HomepageData.CountryData countryData = new HomepageData.CountryData(
                    country,
                    countryConfirmedCases,
                    countryDeaths,
                    countryNewCases,
                    countryNewDeaths
            );
            countryDataSet.add(countryData);

            globalConfirmedCases += countryConfirmedCases;
            globalDeaths += countryDeaths;
            globalNewCases += countryNewCases;
            globalNewDeaths += countryNewDeaths;
        }

        // For US counties
        for (String state: coronaVirusDataService.getUSStateToCountyMap().keySet()) {
            int stateConfirmedCases = 0;
            int stateDeaths = 0;
            int stateNewCases = 0;
            int stateNewDeaths = 0;

            for (PlaceData placeData: coronaVirusDataService.getUSStateToCountyMap().get(state)) {
                stateConfirmedCases += placeData.getConfirmed();
                stateDeaths += placeData.getDeaths();
                stateNewCases += placeData.getNewCases();
                stateNewDeaths += placeData.getNewDeaths();
            }

            HomepageData.CountryData countryData = new HomepageData.CountryData(
                    "US",
                    stateConfirmedCases,
                    stateDeaths,
                    stateNewCases,
                    stateNewDeaths
            );
            countryDataSet.add(countryData);

            globalConfirmedCases += stateConfirmedCases;
            globalDeaths += stateDeaths;
            globalNewCases += stateNewCases;
            globalNewDeaths += stateNewDeaths;
        }

        // Return the HomepageData object
        return new HomepageData(globalConfirmedCases,
                globalDeaths,
                globalNewCases,
                globalNewDeaths,
                countryDataSet);
    }
}
