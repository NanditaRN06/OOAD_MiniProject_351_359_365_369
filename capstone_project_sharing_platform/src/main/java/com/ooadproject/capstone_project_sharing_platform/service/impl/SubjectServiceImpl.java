package com.ooadproject.capstone_project_sharing_platform.service.impl;

import com.ooadproject.capstone_project_sharing_platform.dto.request.SubjectRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.SubjectResponse;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ooadproject.capstone_project_sharing_platform.entity.Subject;
import com.ooadproject.capstone_project_sharing_platform.repository.SubjectRepository;
import com.ooadproject.capstone_project_sharing_platform.service.SubjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

	private final SubjectRepository subjectRepository;

	@Override
	public SubjectResponse createSubject(SubjectRequest request) {
		Subject subject = new Subject();
		subject.setName(request.getName());
		subject.setType(request.getType());

		Subject savedSubject = subjectRepository.save(subject);
		return mapToResponse(savedSubject);
	}

	@Override
	public List<SubjectResponse> getAllSubjects() {
		return subjectRepository.findAll().stream().map(this::mapToResponse).toList();
	}

	@Override
	public SubjectResponse getSubjectById(Long id) {
		Subject subject = subjectRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
		return mapToResponse(subject);
	}

	private SubjectResponse mapToResponse(Subject subject) {
		return new SubjectResponse(subject.getId(), subject.getName(), subject.getType());
	}
}
