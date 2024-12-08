package com.forohub.user.models;

import com.forohub.general.models.Gender;
import com.forohub.general.models.Status;
import com.forohub.user.dto.UserDtoCreate;
import com.forohub.user.dto.UserDtoUpdate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity(name = "user")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true)
    private String username;
    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @Column(columnDefinition = "DATETIME2 DEFAULT CURRENT_TIMESTAMP")
    @NotNull
    private LocalDateTime created;


    public User(UserDtoCreate data) {
        this.username = data.username();
        this.name = data.name();
        this.surname = data.surname();
        this.gender = data.gender();
        this.email = data.email();
        this.password = data.password();
    }

    @PrePersist
    public void prePersist() {
        if (this.gender == null) {
            this.gender = Gender.Other;
        }
        if (this.status == null) {
            this.status = Status.Active;
        }
        if (this.created == null) {
            this.created = LocalDateTime.now();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_USER")
        );
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void update(UserDtoUpdate data) {
        if (data.email() != null) this.email = data.email();
        if (data.gender() != null) this.gender = data.gender();
        if (data.name() != null) this.name = data.name();
        if (data.surname() != null) this.surname = data.surname();
        if (data.password() != null) this.password = data.password();
        if (data.status() != null) this.status = data.status();
    }

    public void block() {
        this.status = Status.Blocked;
    }

}
