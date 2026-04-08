package com.ooadproject.capstone_project_sharing_platform.repository;

import com.ooadproject.capstone_project_sharing_platform.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {
    List<Feedback> findBySubjectId(Long subjectId);
}
