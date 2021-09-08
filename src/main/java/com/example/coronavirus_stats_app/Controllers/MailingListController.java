package com.example.coronavirus_stats_app.Controllers;

import com.example.coronavirus_stats_app.DataModels.MailingListUserDetails;
import com.example.coronavirus_stats_app.Services.MailingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="api/v1/mailingList")
public class MailingListController {

    private final MailingListService mailingListService;
    @Autowired
    public MailingListController(MailingListService mailingListService) {
        this.mailingListService = mailingListService;
    }

    @PostMapping(value = "/register")
    public String mailingListRegistration(@RequestBody MailingListUserDetails mailingListUserDetails) {
        try {
            mailingListService.saveUserDetails(mailingListUserDetails);
            return "Registration successful";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @DeleteMapping(value = "/delete/{email}")
    public String deleteUser(@PathVariable String email) {
        try {
            mailingListService.deleteExistingUser(email);
            return "The email has been removed from the mailing list. You will now stop receiving daily updates";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
