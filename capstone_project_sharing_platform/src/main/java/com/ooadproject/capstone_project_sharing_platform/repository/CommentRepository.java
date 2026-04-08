package com.ooadproject.capstone_project_sharing_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ooadproject.capstone_project_sharing_platform.entity.Comment;
import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProject_Id(Long projectId);
}