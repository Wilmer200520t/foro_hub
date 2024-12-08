package com.forohub.user.dto;

import com.forohub.general.models.Gender;
import com.forohub.general.models.Status;
import com.forohub.user.models.User;

public record UserDtoMainData(
        Long id,
        String username,
        String name,
        String surname,
        Gender gender,
        Status status
) {
    public UserDtoMainData(User author) {
        this(
                author.getId(),
                author.getUsername(),
                author.getName(),
                author.getSurname(),
                author.getGender(),
                author.getStatus()
        );
    }
}
