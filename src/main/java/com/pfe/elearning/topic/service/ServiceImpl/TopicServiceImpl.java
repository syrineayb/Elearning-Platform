package com.pfe.elearning.topic.service.ServiceImpl;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.course.dto.request.CourseRequest;
import com.pfe.elearning.course.dto.response.CourseResponse;
import com.pfe.elearning.course.entity.Course;
import com.pfe.elearning.topic.dto.mapper.TopicMapper;
import com.pfe.elearning.topic.dto.request.TopicRequest;
import com.pfe.elearning.topic.dto.response.TopicResponse;
import com.pfe.elearning.topic.entity.Topic;
import com.pfe.elearning.topic.repository.TopicRepository;
import com.pfe.elearning.topic.service.TopicService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Override
    public TopicResponse createTopic(TopicRequest topicRequest) {
        // Step 1: Convert TopicRequest to Topic entity
        Topic newTopic = topicMapper.toTopic(topicRequest);

        // Step 2: Save the created Topic entity to the database
        Topic savedTopic = topicRepository.save(newTopic);

        // Step 3: Convert the saved Topic entity to TopicResponse
        TopicResponse topicResponse = topicMapper.toTopicResponse(savedTopic);

        // Step 4: Return the created TopicResponse
        return topicResponse;
    }


    /*@Override
    public Long save(DomainRequest d) {
        Domain domain = mapper.toDomain(d);
        return this.domainRepository.save(domain).getId();
    }

     */


    @Override
    public TopicResponse findById(Long id) {
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
    public void deleteById(Long id) {
        this.topicRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return topicRepository.existsById(id);
    }

    @Override
    @Transactional
    public TopicResponse update(Long id, TopicRequest topicRequest) {
        Topic existingTopic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found with id: " + id));

        existingTopic.setTitle(topicRequest.getTitle());
        // Update other fields as needed

        Topic updatedTopic = topicRepository.save(existingTopic);
        return topicMapper.toTopicResponse(updatedTopic);
    }



    @Override
    public PageResponse<TopicResponse> findByTitleContaining(String keyword, int page, int size) {
        Page<Topic> topicPage = topicRepository.findByTitleContaining(keyword, PageRequest.of(page, size));
        return PageResponse.<TopicResponse>builder()
                .content(
                        topicPage.getContent()
                                .stream()
                                .map(topicMapper::toTopicResponse)
                                .toList()
                )
                .totalPages(topicPage.getTotalPages())
                .build();
    }

}
