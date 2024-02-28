package com.pfe.elearning.topic.repository;

import com.pfe.elearning.topic.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {
    Page<Topic> findByTitleContaining(String keyword, Pageable pageable);
}
