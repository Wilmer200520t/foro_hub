package com.forohub.user.dto;

import com.forohub.general.models.Gender;
import jakarta.validation.constraints.NotNull;

public record UserDtoCreate(
        @NotNull String username,
        @NotNull String name,
        @NotNull String surname,
        @NotNull Gender gender,
        @NotNull String email,
        @NotNull String password
) {
}
