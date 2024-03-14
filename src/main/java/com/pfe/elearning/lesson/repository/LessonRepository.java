package com.pfe.elearning.lesson.repository;

import com.pfe.elearning.lesson.entity.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LessonRepository extends JpaRepository<Lesson,Long> {
    Page<Lesson> findByTitleContaining(String keyword, Pageable pageable);

}
