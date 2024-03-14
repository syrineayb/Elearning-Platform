package com.pfe.elearning.course.repository;

import com.pfe.elearning.course.entity.Course;
import com.pfe.elearning.topic.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Page<Course> findByTitleContaining(String keyword, Pageable pageable);
    Page<Course> findByTopic(Topic topic, Pageable pageable);

}
