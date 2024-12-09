package com.forohub.topic.service;

import com.forohub.general.infra.errors.ValidationException;
import com.forohub.topic.dto.TopicDtoRegister;
import com.forohub.topic.dto.TopicDtoShowAll;
import com.forohub.topic.dto.TopicDtoUpdate;
import com.forohub.topic.models.Topic;
import com.forohub.topic.repository.TopicRepository;
import com.forohub.user.models.User;
import com.forohub.user.repository.UserRespository;
import com.forohub.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserService userService;

    public Topic registerTopic(TopicDtoRegister topicDtoRegister) {
        User user = validateUser(userService.getUserIdAuthenticated());
        Topic topic = new Topic(topicDtoRegister, user);
        return topicRepository.save(topic);
    }

    public User validateUser(Long user_id) {
        Optional<User> user = userRespository.findById(user_id);

        if (user.isEmpty()) {
            throw new ValidationException("User whith id " + user_id +" not found.");
        }

        return user.get();
    }

    public ResponseEntity<TopicDtoShowAll> updateTopic(TopicDtoUpdate topicDtoUpdate, Long topic_id) {
        User user = validateUser(userService.getUserIdAuthenticated());

        //who is login

        Optional<Topic> topicOptional = topicRepository.findById(topic_id);

        if (topicOptional.isEmpty()) {
            throw new ValidationException("Topic whith id " + topic_id +" not found.");
        }

        Topic topic = topicOptional.get();
        topic.update(topicDtoUpdate);

        return ResponseEntity.ok(new TopicDtoShowAll(topic));
    }

    public void deleteTopic(long id) {
        Optional<Topic> topic = topicRepository.findById(id);

        //who is login

        if (topic.isEmpty()) {
            throw new ValidationException("Topic whith id " + id +" not found.");
        }

        topic.ifPresent(value -> topicRepository.delete(value));
    }
}
