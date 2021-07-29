package com.example.coronavirus_stats_app.Controllers;

import com.example.coronavirus_stats_app.DataModels.PlaceData;
import com.example.coronavirus_stats_app.Services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="api/v1/country")
public class CountryPageController {

    /****
    // TODO: For each country page, get historical data from csse_covid_timeseries and plot graphs
    ****/
    private final CoronaVirusDataService coronaVirusDataService;

    @Autowired
    public CountryPageController(CoronaVirusDataService coronaVirusDataService) {
        this.coronaVirusDataService = coronaVirusDataService;
    }

    @GetMapping(value = "/US")
    public Map<String, List<PlaceData>> getUSData() {
        return coronaVirusDataService.getUSStateToCountyMap();
    }

    @GetMapping(value = "/province_state/{countryName}")
    public List<PlaceData> getNonUSCountryWithProvinceData(@PathVariable String countryName) {
        if (!coronaVirusDataService.getCountriesWithProvinceMap().containsKey(countryName)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country does not exist");
        }

        return coronaVirusDataService.getCountriesWithProvinceMap().get(countryName);
    }

    @GetMapping(value = "/{countryName}")
    public PlaceData getCountryWithoutProvinceData(@PathVariable String countryName) {
        if (!coronaVirusDataService.getCountriesWithoutProvinceMap().containsKey(countryName)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country does not exist");
        }
        return coronaVirusDataService.getCountriesWithoutProvinceMap().get(countryName);
    }

}
