package com.example.coronavirus_stats_app.Repositories;

import com.example.coronavirus_stats_app.DataModels.MailingListUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailingListUserRepository extends JpaRepository<MailingListUserDetails, String> {
}
