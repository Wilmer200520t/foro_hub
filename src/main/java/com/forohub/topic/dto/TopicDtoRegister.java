package com.forohub.topic.dto;

import com.forohub.general.models.Course;
import com.forohub.general.models.Status;
import jakarta.validation.constraints.NotNull;


public record TopicDtoRegister(
        @NotNull
        String title,

        @NotNull
        String message,

        @NotNull
        Course course
) {
}
