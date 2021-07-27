package com.example.coronavirus_stats_app.Controllers;

import com.example.coronavirus_stats_app.DataModels.PlaceData;
import com.example.coronavirus_stats_app.Services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/home")
public class HomeController {

    private final CoronaVirusDataService coronaVirusDataService;

    @Autowired
    public HomeController(CoronaVirusDataService coronaVirusDataService) {
        this.coronaVirusDataService = coronaVirusDataService;
    }

    @GetMapping
    public List<PlaceData> getAllData() {
        return coronaVirusDataService.getAllData();
    }


}
