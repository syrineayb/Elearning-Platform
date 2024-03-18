package com.pfe.elearning.lesson.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfe.elearning.lesson.dto.LessonRequest;
import com.pfe.elearning.user.service.UserService;
import org.junit.jupiter.api.Test;
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

@SpringBootTest
@AutoConfigureMockMvc
class LessonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService; // Define userService mock

    @Test
    public void createLesson_SuccessfullyCreatesLesson() throws Exception {
        // Create a lesson request
        LessonRequest lessonRequest = new LessonRequest();
        lessonRequest.setTitle("Test Lesson");
        lessonRequest.setDescription("This is a test lesson");
        lessonRequest.setCourseId(1);

        // Simulate authenticated user (instructor)
        String instructorUsername = "instructor@example.com";

        // Perform POST request to create lesson with user having role "INSTRUCTOR"
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/lessons")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(lessonRequest))
                                .with(SecurityMockMvcRequestPostProcessors.user(instructorUsername).roles("INSTRUCTOR"))
                )
                .andExpect(MockMvcResultMatchers.status().isOk()) // Verify request succeeded
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Lesson")) // Verify returned lesson details
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("This is a test lesson"));

        // Ensure lesson is created by instructor
        verify(userService, times(1)).getUserByEmail(instructorUsername);
        // Ensure other interactions (such as searching for courses) are also verified as expected
    }
}
