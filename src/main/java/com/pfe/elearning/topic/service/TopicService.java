package com.pfe.elearning.topic.service;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.topic.dto.TopicRequest;
import com.pfe.elearning.topic.dto.TopicResponse;

import java.util.List;

public interface TopicService {
    TopicResponse createTopic(TopicRequest topic);
    TopicResponse findById(Integer id);
    PageResponse<TopicResponse> findAll(int page, int size);
    List<TopicResponse> getAllTopics();
    void deleteById(Integer id);
    boolean existsById(Integer id);
    TopicResponse update(Integer id, TopicRequest domainRequest); // New method for updating a domain
    PageResponse<TopicResponse> findByTitleContaining(String keyword, int page, int size);

}
