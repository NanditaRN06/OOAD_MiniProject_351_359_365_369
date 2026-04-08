package com.ooadproject.capstone_project_sharing_platform.repository;

import com.ooadproject.capstone_project_sharing_platform.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
