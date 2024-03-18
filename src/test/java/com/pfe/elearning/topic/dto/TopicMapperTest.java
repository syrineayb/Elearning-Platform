package com.pfe.elearning.topic.dto;

import com.pfe.elearning.topic.entity.Topic;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TopicMapperTest {
    private TopicMapper mapper;

    @BeforeEach
    void setUp() {
        this.mapper = new TopicMapper();
    }

    @Test
    void mapTopicRequestToTopic_Success() {
        // Arrange
        TopicRequest request = new TopicRequest("Web development");

        // Act
        Topic topic = mapper.toTopic(request);

        // Assert
        assertEquals(request.getTitle(), topic.getTitle());
        assertNotNull(topic.getTitle());
    }

    @Test
    void mapTopicRequestToTopic_NullRequest_ShouldThrowException() {
        // Act & Assert
        NullPointerException exception = assertThrows(NullPointerException.class, () -> mapper.toTopic(null));
        assertEquals("The topic request should not be null", exception.getMessage());
    }

    @Test
    void mapTopicToTopicResponse_Success() {
        // Arrange
        Topic topic = new Topic();
        topic.setId(1);
        topic.setTitle("Java Programming");
        LocalDateTime createdAt = LocalDateTime.now();
        topic.setCreatedAt(createdAt);
        LocalDateTime updatedAt = LocalDateTime.now();
        topic.setUpdatedAt(updatedAt);
        topic.setCourses(new ArrayList<>());

        // Act
        TopicResponse topicResponse = mapper.toTopicResponse(topic);

        // Assert
        assertEquals(topic.getId(), topicResponse.getId());
        assertEquals(topic.getTitle(), topicResponse.getTitle());
        assertEquals(topic.getCreatedAt(), topicResponse.getCreatedAt());
        assertEquals(topic.getUpdatedAt(), topicResponse.getUpdatedAt());
    }


}
