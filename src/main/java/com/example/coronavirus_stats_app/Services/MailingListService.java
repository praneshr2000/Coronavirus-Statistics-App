package com.example.coronavirus_stats_app.Services;

import com.example.coronavirus_stats_app.DataModels.MailingListUserDetails;
import com.example.coronavirus_stats_app.Repositories.MailingListUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.validator.routines.EmailValidator;

@Service
public class MailingListService {
    private final MailingListUserRepository mailingListUserRepository;

    @Autowired
    public MailingListService(MailingListUserRepository mailingListUserRepository) {
        this.mailingListUserRepository = mailingListUserRepository;
    }

    // Register the user with given details, raise exception if failure occurs
    public void saveUserDetails(MailingListUserDetails mailingListUserDetails) {
        if (!emailValidator(mailingListUserDetails.getEmailAddress())) {
            throw new IllegalStateException("Invalid email address.");
        }

        mailingListUserDetails.setEmailAddress(mailingListUserDetails.getEmailAddress().toLowerCase());

        if (mailingListUserRepository.existsById(mailingListUserDetails.getEmailAddress())) {
            throw new IllegalStateException("This email already exists in the mailing list.");
        }
        mailingListUserRepository.save(mailingListUserDetails);
    }

    // Helper to validate if email is valid
    private boolean emailValidator(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }

    // Delete the user from the database, raise exception if failure occurs
    public void deleteExistingUser(String email) {
        if (!mailingListUserRepository.existsById(email.toLowerCase())) {
            throw new IllegalStateException("This email is not there in the mailing list.");
        }

        mailingListUserRepository.deleteById(email);
    }
}
