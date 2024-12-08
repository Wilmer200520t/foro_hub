package com.forohub.topic.controllers;

import com.forohub.topic.dto.TopicDtoRegister;
import com.forohub.topic.dto.TopicDtoShowAll;
import com.forohub.topic.dto.TopicDtoUpdate;
import com.forohub.topic.models.Topic;
import com.forohub.topic.repository.TopicRepository;
import com.forohub.topic.service.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicService topicService;

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

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<TopicDtoShowAll> createTopic(@RequestBody @Valid TopicDtoRegister topicDto) {
        Topic topic = topicService.registerTopic(topicDto);
        return ResponseEntity.ok(new TopicDtoShowAll(topic));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDtoShowAll> updateTopic(@RequestBody @Valid TopicDtoUpdate topicDto, @PathVariable long id) {
        return topicService.updateTopic(topicDto, id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteTopic(@PathVariable long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}
