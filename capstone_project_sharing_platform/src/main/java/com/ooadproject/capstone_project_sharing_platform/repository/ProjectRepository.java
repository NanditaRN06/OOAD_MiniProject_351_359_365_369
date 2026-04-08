package com.ooadproject.capstone_project_sharing_platform.repository;

import com.ooadproject.capstone_project_sharing_platform.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
