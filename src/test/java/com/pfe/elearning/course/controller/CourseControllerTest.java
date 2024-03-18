package com.pfe.elearning.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfe.elearning.course.dto.CourseRequest;
import com.pfe.elearning.course.service.CourseService;
import com.pfe.elearning.topic.dto.TopicRequest;
import com.pfe.elearning.user.entity.User;
import com.pfe.elearning.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.times;
@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService; // Define userService mock

    @Autowired
    private CourseService courseService; // Autowire the CourseService

    @Test
    public void createCourse_SuccessfullyCreatesCourse()  throws Exception {
        // Create a CourseRequest
        CourseRequest request = new CourseRequest();
        request.setTitle("Html course");
        request.setDescription("description");
        request.setTopicId(1);
        request.setPhoto("course.jpg");
        request.setDuration("3 weeks");

        // Simulate authenticated user (instructor)
        String instructorUsername = "instructor@example.com";

        // Create a mocked User object
        User publisher = new User();
        publisher.setEmail(instructorUsername);

        // Mock the behavior of getUserByEmail to return the mocked User object
        Mockito.when(userService.getUserByEmail(instructorUsername)).thenReturn(publisher);

        // Perform the POST request to create the course
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/courses")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request))
                                .with(SecurityMockMvcRequestPostProcessors.user(instructorUsername).roles("INSTRUCTOR"))
                )
                .andExpect(MockMvcResultMatchers.status().isOk()); // Assuming the response is OK for successful creation

        // Verify that userService.getUserByEmail was called once with the correct username
        verify(userService, times(1)).getUserByEmail(instructorUsername);

        // Verify that courseService.createCourse was called once
        verify(courseService, times(1)).createCourse(request, instructorUsername);
    }
}
