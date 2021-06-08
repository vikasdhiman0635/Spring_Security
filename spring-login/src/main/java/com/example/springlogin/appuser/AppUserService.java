package com.example.springlogin.appuser;

import com.example.springlogin.registration.token.ConformationToken;
import com.example.springlogin.registration.token.ConformationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService
{

    private final static String USER_NOT_FOUND_MSG="user with email %s not found";

    private final AppUserRepo repo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ConformationTokenService conformationTokenService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        return repo.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }

    public String signupUser(AppUser appUser)
    {
        boolean userExist= repo.findByEmail(appUser.getEmail()).isPresent();
        if(userExist)
        {
            System.out.println("User exist"+userExist);
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword=bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        repo.save(appUser);

        String token= UUID.randomUUID().toString();
        ConformationToken conformationToken =new ConformationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        conformationTokenService.saveConformationToken(conformationToken);

        return token;
    }

    public int enableAppUser(String email) {
        return repo.enableAppUser(email);
    }
}
