package com.forohub.topic.dto;

import com.forohub.general.models.Course;
import com.forohub.general.models.Status;
import jakarta.validation.constraints.NotNull;


public record TopicDtoRegister(
        @NotNull
        String title,

        @NotNull
        String message,

        Status status,

        @NotNull
        Long user_id,

        @NotNull
        Course course
) {
}
