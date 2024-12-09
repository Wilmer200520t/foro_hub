package com.forohub.topic.dto;

import com.forohub.general.models.Status;
import jakarta.validation.constraints.NotNull;

public record TopicDtoUpdate(

        String title,

        String message,

        Status status
) {
}
