package com.pfe.elearning.course.repository;

import com.pfe.elearning.course.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findByTitleContaining(String keyword, Pageable pageable);

}
