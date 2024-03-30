package com.pfe.elearning.topic.service.ServiceImpl;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.course.dto.CourseRequest;
import com.pfe.elearning.course.dto.CourseResponse;
import com.pfe.elearning.course.entity.Course;
import com.pfe.elearning.topic.dto.TopicMapper;
import com.pfe.elearning.topic.dto.TopicRequest;
import com.pfe.elearning.topic.dto.TopicResponse;
import com.pfe.elearning.topic.entity.Topic;
import com.pfe.elearning.topic.repository.TopicRepository;
import com.pfe.elearning.topic.service.TopicService;
import com.pfe.elearning.validator.ObjectsValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;
    private final ObjectsValidator<TopicRequest> validator;


    @Override
    public TopicResponse createTopic(TopicRequest topicRequest) {
        validator.validate(topicRequest);
        // Step 1: Convert TopicRequest to Topic entity
        Topic newTopic = topicMapper.toTopic(topicRequest);

        // Step 2: Save the created Topic entity to the database
        Topic savedTopic = topicRepository.save(newTopic);
        //topicRepository.save(newTopic);

        // Step 3: Convert the saved Topic entity to TopicResponse

        // Step 4: Return the created TopicResponse
        return topicMapper.toTopicResponse(savedTopic);
    }



    @Override
    public TopicResponse findById(Integer id) {
        return this.topicRepository.findById(id)
                .map(topicMapper::toTopicResponse)
                .orElse(new TopicResponse());
    }

    @Override
    public PageResponse<TopicResponse> findAll(int page, int size) {
        var pageResult = this.topicRepository.findAll(PageRequest.of(page, size));
        return PageResponse.<TopicResponse>builder()
                .content(
                        pageResult.getContent()
                                .stream()
                                .map(topicMapper::toTopicResponse)
                                .toList()
                )
                .totalPages(pageResult.getTotalPages())
                .build();
    }

    @Override
    public List<TopicResponse> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();
        return topics.stream()
                .map(topicMapper::toTopicResponse)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteById(Integer id) {
        this.topicRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return topicRepository.existsById(id);
    }

    @Override
    @Transactional
    public TopicResponse update(Integer id, TopicRequest topicRequest) {
        Topic existingTopic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found with id: " + id));

        existingTopic.setTitle(topicRequest.getTitle());
        existingTopic.setImageUrl(topicRequest.getImageUrl());
        // Update other fields as needed
        Topic updatedTopic = topicRepository.save(existingTopic);
        return topicMapper.toTopicResponse(updatedTopic);
    }



    @Override
    public PageResponse<TopicResponse> findByTitleContaining(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Topic> topicPage = topicRepository.findByTitleContaining(keyword, pageable);
        return mapToPageResponse(topicPage);
    }

    private PageResponse<TopicResponse> mapToPageResponse(Page<Topic> topicPage) {
        List<TopicResponse> topicResponses = topicPage.getContent().stream()
                .map(topicMapper::toTopicResponse)
                .collect(Collectors.toList());
        return new PageResponse<>(topicResponses, topicPage.getTotalPages(), topicPage.getTotalElements());
    }

}
