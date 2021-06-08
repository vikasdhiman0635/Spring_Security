package com.example.springlogin.registration.token;

import com.example.springlogin.appuser.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConformationToken
{
    @SequenceGenerator(
            name = "creating_token_sequence",
            sequenceName = "creating_token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "creating_token_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expireAt;

    private LocalDateTime confirmAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name="app_user_id"
    )
    private AppUser appUser;

    public ConformationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expireAt,
                             AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expireAt = expireAt;
        this.appUser = appUser;
    }
}
