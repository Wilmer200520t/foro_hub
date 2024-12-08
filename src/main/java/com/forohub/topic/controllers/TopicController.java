package com.forohub.topic.controllers;

import com.forohub.topic.dto.TopicDtoShowAll;
import com.forohub.topic.models.Topic;
import com.forohub.topic.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicRepository topicRepository;

    @GetMapping
    public Page<TopicDtoShowAll> getTopics(@PageableDefault(size = 25) Pageable pageable) {
        return topicRepository.findAll(pageable)
                .map(TopicDtoShowAll::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDtoShowAll> getTopic(@PathVariable long id) {
        Optional<Topic> topic = topicRepository.findById(id);

        return topic.map(value -> ResponseEntity.ok(new TopicDtoShowAll(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
