package com.pfe.elearning.topic.dto;

import com.pfe.elearning.topic.entity.Topic;
import org.springframework.stereotype.Service;

@Service
public class TopicMapper {
    public TopicResponse toTopicResponse(Topic topic) {
        return TopicResponse.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .createdAt(topic.getCreatedAt())
                .updatedAt(topic.getUpdatedAt())
                .build();
    }
    public Topic toTopic(TopicRequest topicRequest) {
        if(topicRequest == null){
            throw new NullPointerException("The topic request should not be null");
        }
        Topic topic = new Topic();
        topic.setTitle(topicRequest.getTitle());
        return topic;
    }
}
