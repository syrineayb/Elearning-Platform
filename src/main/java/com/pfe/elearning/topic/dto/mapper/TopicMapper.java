package com.pfe.elearning.topic.dto.mapper;

import com.pfe.elearning.topic.dto.request.TopicRequest;
import com.pfe.elearning.topic.dto.response.TopicResponse;
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
        Topic topic = new Topic();
     //  domain.setId(domainRequest.getId());
        topic.setTitle(topicRequest.getTitle());
        return topic;
    }
}
