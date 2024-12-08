package com.forohub.topic.dto;

import com.forohub.general.models.Course;
import com.forohub.general.models.Status;
import com.forohub.topic.models.Topic;
import com.forohub.user.dto.UserDtoMainData;
import com.forohub.user.models.User;

import java.time.LocalDateTime;

public record TopicDtoShowAll(
        long id,
        String title,
        String message,
        Status status,
        UserDtoMainData author,
        Course course,
        LocalDateTime creationDate,
        LocalDateTime modificationDate
) {
    public TopicDtoShowAll(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus(),
                new UserDtoMainData(topic.getAuthor()),
                topic.getCourse(),
                topic.getCreationDate(),
                topic.getModificationDate()
        );
    }
}
