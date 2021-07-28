package com.example.coronavirus_stats_app.Controllers;

import com.example.coronavirus_stats_app.DataModels.HomepageData;
import com.example.coronavirus_stats_app.Services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/home")
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping
    public HomepageData getAllData() {
        return homeService.getAllData();
    }


}
