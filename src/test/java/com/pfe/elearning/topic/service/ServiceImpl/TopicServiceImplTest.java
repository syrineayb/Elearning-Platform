package com.pfe.elearning.topic.service.ServiceImpl;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.topic.dto.TopicMapper;
import com.pfe.elearning.topic.dto.TopicRequest;
import com.pfe.elearning.topic.dto.TopicResponse;
import com.pfe.elearning.topic.entity.Topic;
import com.pfe.elearning.topic.repository.TopicRepository;
import com.pfe.elearning.validator.ObjectsValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class TopicServiceImplTest {

    @Mock
    private TopicRepository repository;

    @Mock
    private TopicMapper topicMapper;


    @Mock
    private ObjectsValidator<TopicRequest> validator;

    @InjectMocks
    private TopicServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTopic_SuccessfullyCreatesTopic()  {
        // Given
        TopicRequest request = new TopicRequest("Web development");
        Topic topic = new Topic("Web development");
        Topic createdTopic = new Topic("Web development");
        createdTopic.setId(1);

            // Mock the calls
            when(topicMapper.toTopic(request)).thenReturn(topic);
            when(repository.save(topic)).thenReturn(createdTopic);
            when(topicMapper.toTopicResponse(createdTopic)).thenReturn(new TopicResponse(
                    1,
                    "Web development",
                    LocalDateTime.now(),
                    LocalDateTime.now()
            ));
            doNothing().when(validator).validate(request);

        // When
        TopicResponse response = service.createTopic(request);

        // Then
        assertNotNull(response);
        assertEquals(request.getTitle(), response.getTitle());
        assertEquals(createdTopic.getId(), response.getId());
        assertNotNull(response.getCreatedAt());
        assertNotNull(response.getUpdatedAt());

        // Verify interactions
        verify(  validator).validate(request);
        verify(topicMapper).toTopic(request);
        verify(repository).save(topic);
        verify(topicMapper).toTopicResponse(createdTopic);
    }

    @Test
    public void findAll_ReturnsAllTopics() {
        // Given
        List<Topic> topics = new ArrayList<>();
        topics.add(new Topic("Web development"));
        topics.add(new Topic("Software Engineering"));

        // Mock the calls
        when(repository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(topics));
        when(topicMapper.toTopicResponse(any(Topic.class))).thenReturn(new TopicResponse(
                1,
                "Web development",
                LocalDateTime.now(),
                LocalDateTime.now()
        ));

        // When
        PageResponse<TopicResponse> response = service.findAll(0, 10);

        // Then
        assertEquals(topics.size(), response.getContent().size());

        // Verify interactions
        verify(repository).findAll(PageRequest.of(0, 10));
        verify(topicMapper, times(topics.size())).toTopicResponse(any(Topic.class));
    }

    @Test
    public void existsById_ReturnsTrueWhenTopicsExists(){
        // Given
        Integer id= 1;
        when(repository.existsById(id)).thenReturn(true);
        // When
        boolean exists = service.existsById(id);
        // Then
        assertTrue(exists);
        // Verify interactions
        verify(repository).existsById(id);
    }
    @Test
    public void existsById_ReturnsFalseWhenTopicsExists(){
        // Given
        Integer id= 1;
        when(repository.existsById(id)).thenReturn(false);
        // When
        boolean exists = service.existsById(id);
        // Then
        assertFalse(exists);
        // Verify interactions
        verify(repository).existsById(id);
    }
    @Test
    public void findById_ReturnsTopicResponse(){
        // Given
        Integer id= 1;
        Topic topic = new Topic("Web Development");
        topic.setId(id);
        // Mock the calls
        when(repository.findById(id))
                .thenReturn(Optional.of(topic));
        when(topicMapper.toTopicResponse(any(Topic.class))).thenReturn(new TopicResponse(
                1,
                "Web Development",
                LocalDateTime.now(),
                LocalDateTime.now()
        ));
        // When
        TopicResponse response = service.findById(id);
        // Then
        assertNotNull(response);
        assertEquals(response.getTitle(), topic.getTitle());
        assertEquals(response.getId(), topic.getId());
        assertNotNull(response.getCreatedAt());
        assertNotNull(response.getUpdatedAt());
        // Verify
        verify(repository,times(1)).findById(id);

    }
    @Test
    public void findByTitleContaining_ReturnsPageResponse() {
        // Given
        String keyword = "Web";
        int page = 0;
        int size = 10;
        List<Topic> topics = Arrays.asList(
                new Topic("Web Development"),
                new Topic("Web Design"),
                new Topic("Biology")
        );

// Filter topics based on the keyword
        List<Topic> filteredTopics = topics.stream()
                .filter(topic -> topic.getTitle().contains(keyword))
                .collect(Collectors.toList());

        Page<Topic> topicPage = new PageImpl<>(filteredTopics);

// Mock the repository call
        when(repository.findByTitleContaining(eq(keyword), any(Pageable.class)))
                .thenReturn(topicPage);

// Mock the mapper call
        when(topicMapper.toTopicResponse(any(Topic.class)))
                .thenAnswer(invocation -> {
                    Topic topic = invocation.getArgument(0);
                    return new TopicResponse(
                            topic.getId(),
                            topic.getTitle(),
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    );
                });

// When
        PageResponse<TopicResponse> response = service.findByTitleContaining(keyword, page, size);

// Then
        assertNotNull(response);
        assertEquals(1, response.getTotalPages());
        assertEquals(filteredTopics.size(), response.getContent().size()); // Use filteredTopics.size() here
        // Verify that findByTitleContaining method is called with the correct arguments
        verify(repository).findByTitleContaining(eq(keyword), any(Pageable.class));

        // Verify that toTopicResponse method is called for each topic in the content
        verify(topicMapper, times(filteredTopics.size())).toTopicResponse(any(Topic.class));
    }

    @Test
    public void deleteById_DeletesTopicSuccessfully() {
        // Given
        Integer topicId = 1;

        // Mock behavior
        doNothing().when(repository).deleteById(topicId);

        // When
        service.deleteById(topicId);

        // Then
        // Verify that deleteById method of the mocked repository is called once with the expected ID
        verify(repository, times(1)).deleteById(topicId);
    }
    @Test
    public void updateTopic_UpdateSuccessfully(){
        // Given
        Integer id = 1;
        TopicRequest topicRequest = new TopicRequest("Updated Title");
        Topic existingTopic = new Topic("Existing Title");
        existingTopic.setId(id);

        Topic updatedTopic = new Topic("Updated Title");
        updatedTopic.setId(id);
        // Mock behavior
        when(repository.findById(id)).thenReturn(Optional.of(existingTopic));
        when(repository.save(any(Topic.class))).thenReturn(updatedTopic);
        when(topicMapper.toTopicResponse(updatedTopic)).thenReturn(new TopicResponse(
                id,
                "Updated Title",
                LocalDateTime.now(),
                LocalDateTime.now()
        ));

        // When
        TopicResponse response = service.update(id, topicRequest);
        // Then
        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals("Updated Title", response.getTitle());

        // Verify interactions
        verify(repository).findById(id);
        verify(repository).save(existingTopic);
        verify(topicMapper).toTopicResponse(updatedTopic);
    }




}
