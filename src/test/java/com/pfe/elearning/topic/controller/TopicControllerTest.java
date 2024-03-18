package com.pfe.elearning.topic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfe.elearning.topic.dto.TopicRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@SpringBootTest(properties = {"spring.datasource.url=jdbc:mysql://localhost:3306/test-db?createDatabaseIfNotExist=true","spring.datasource.username=root","spring.datasource.password="})
@SpringBootTest
@AutoConfigureMockMvc
class TopicControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createTopic_SuccessfullyCreatesTopic()  throws Exception {
        TopicRequest request = new TopicRequest();
        request.setTitle("Web development");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/topics")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(request))
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"))


        ).andExpect(MockMvcResultMatchers.status().isAccepted()); // it creates topic in db
    }

    @Test
    void findById_Success() throws Exception {
        // Mocking the topic ID
        int topicId = 1;

        // Perform the GET request to fetch the topic by ID
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/topics/{topicId}", topicId)
                                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Add assertions for the response content if needed
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(topicId));
    }

   /* @Test
    void deleteById_Success() throws Exception {
        // Mocking the topic ID
        int topicId = 1;

        // Perform the DELETE request to delete the topic
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/topics/{topicId}", topicId)
                                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Add assertions for the response content if needed
                .andExpect(MockMvcResultMatchers.content().string("Topic " + topicId + " successfully deleted."));
    }



    */

    @Test
    void findByTitleContaining_Success() throws Exception {
        // Define the query parameters
        String keyword = "development";
        int page = 0;
        int size = 20;

        // Perform the GET request to fetch topics containing the keyword
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/topics/search")
                    //            .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"))

                                .param("keyword", keyword)
                                .param("page", String.valueOf(page))
                                .param("size", String.valueOf(size))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                // Verify the expected status code
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Verify the response content
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray()) // Check if the response content is an array
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[*].title").isNotEmpty()); // Check if titles of topics are not empty
    }

}