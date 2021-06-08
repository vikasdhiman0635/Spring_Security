package com.example.springlogin.registration;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class RegistrationRequest
{
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
}
