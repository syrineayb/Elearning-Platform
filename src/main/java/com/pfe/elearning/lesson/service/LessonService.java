package com.pfe.elearning.lesson.service;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.lesson.dto.LessonRequest;
import com.pfe.elearning.lesson.dto.LessonResponse;

public interface LessonService {
    LessonResponse createLesson(LessonRequest lessonRequest,String publisherUsername);
    LessonResponse getLessonById(Long lessonId);
    LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest, String publisherUsername);
    void deleteLesson(Long lessonId, String publisherUsername);
    PageResponse<LessonResponse> getAllLessons(int page, int size);
    PageResponse<LessonResponse> findLessonByTitleContaining(String keyword,int page, int size);


}
