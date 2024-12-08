package com.forohub.topic.dto;

import com.forohub.general.models.Status;
import jakarta.validation.constraints.NotNull;

public record TopicDtoUpdate(

        @NotNull
        Long user_id,

        String title,

        String message,

        Status status
) {
}
