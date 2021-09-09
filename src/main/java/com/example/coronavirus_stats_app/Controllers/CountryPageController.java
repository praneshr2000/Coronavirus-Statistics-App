package com.example.coronavirus_stats_app.Controllers;

import com.example.coronavirus_stats_app.DataModels.PlaceData;
import com.example.coronavirus_stats_app.Services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(path="api/v1/country")
public class CountryPageController {

    private final CoronaVirusDataService coronaVirusDataService;

    // Inject coronaVirusDataService
    @Autowired
    public CountryPageController(CoronaVirusDataService coronaVirusDataService) {
        this.coronaVirusDataService = coronaVirusDataService;
    }

    // Get the country data for the US
    @GetMapping(value = "/US")
    public Map<String, List<PlaceData>> getUSData() {
        return coronaVirusDataService.getUSStateToCountyMap();
    }

    // Get the country data for which Province/State stats are available
    @GetMapping(value = "/province_state/{countryName}")
    public List<PlaceData> getNonUSCountryWithProvinceData(@PathVariable String countryName) {
        if (!coronaVirusDataService.getCountriesWithProvinceMap().containsKey(countryName)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country does not exist");
        }

        return coronaVirusDataService.getCountriesWithProvinceMap().get(countryName);
    }

    // Get the country data for which no Province/State stats are available
    @GetMapping(value = "/{countryName}")
    public PlaceData getCountryWithoutProvinceData(@PathVariable String countryName) {
        if (!coronaVirusDataService.getCountriesWithoutProvinceMap().containsKey(countryName)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country does not exist");
        }
        return coronaVirusDataService.getCountriesWithoutProvinceMap().get(countryName);
    }

}
