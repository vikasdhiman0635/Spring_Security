package com.example.springlogin.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConformationTokenRepo extends JpaRepository<ConformationToken,Long>
{
    Optional<ConformationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConformationToken c " +
            "SET c.confirmAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);
}
