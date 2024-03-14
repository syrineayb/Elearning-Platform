package com.pfe.elearning.topic.service;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.topic.dto.TopicRequest;
import com.pfe.elearning.topic.dto.TopicResponse;

public interface TopicService {
    TopicResponse createTopic(TopicRequest topic);
    TopicResponse findById(Integer id);
    PageResponse<TopicResponse> findAll(int page, int size);
    void deleteById(Integer id);
    boolean existsById(Integer id);
    TopicResponse update(Integer id, TopicRequest domainRequest); // New method for updating a domain
    PageResponse<TopicResponse> findByTitleContaining(String keyword, int page, int size);

}
