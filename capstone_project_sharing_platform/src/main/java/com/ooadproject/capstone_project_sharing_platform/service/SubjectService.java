package com.ooadproject.capstone_project_sharing_platform.service;

import com.ooadproject.capstone_project_sharing_platform.dto.request.SubjectRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.SubjectResponse;
import java.util.List;

public interface SubjectService {

	SubjectResponse createSubject(SubjectRequest request);

	List<SubjectResponse> getAllSubjects();

	SubjectResponse getSubjectById(Long id);
}
