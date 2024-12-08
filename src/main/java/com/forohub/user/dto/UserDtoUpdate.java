package com.forohub.user.dto;

import com.forohub.general.models.Gender;
import com.forohub.general.models.Status;

public record UserDtoUpdate(
        String name,
        String surname,
        Gender gender,
        String email,
        String password,
        Status status
) {
}
