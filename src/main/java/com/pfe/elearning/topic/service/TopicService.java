package com.pfe.elearning.topic.service;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.topic.dto.request.TopicRequest;
import com.pfe.elearning.topic.dto.response.TopicResponse;

public interface TopicService {
    TopicResponse createTopic(TopicRequest topic);
    TopicResponse findById(Long id);
    PageResponse<TopicResponse> findAll(int page, int size);
    void deleteById(Long id);
    boolean existsById(Long id);
    TopicResponse update(Long id, TopicRequest domainRequest); // New method for updating a domain
    PageResponse<TopicResponse> findByTitleContaining(String keyword, int page, int size);

}
