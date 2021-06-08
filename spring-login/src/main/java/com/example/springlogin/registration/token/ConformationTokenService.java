package com.example.springlogin.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConformationTokenService
{

    private final ConformationTokenRepo conformationTokenRepo;

    public void saveConformationToken(ConformationToken token)
    {
        conformationTokenRepo.save(token);
    }

    public Optional<ConformationToken> getToken(String token) {
        return conformationTokenRepo.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return conformationTokenRepo.updateConfirmedAt(
                token, LocalDateTime.now());
    }


}
