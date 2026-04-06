package com.ooadproject.capstone_project_sharing_platform.controller;

import com.ooadproject.capstone_project_sharing_platform.dto.request.SubjectRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.SubjectResponse;
import com.ooadproject.capstone_project_sharing_platform.service.SubjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

	private final SubjectService subjectService;

	@PostMapping
	public ResponseEntity<SubjectResponse> createSubject(@RequestBody SubjectRequest request) {
		return new ResponseEntity<>(subjectService.createSubject(request), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<SubjectResponse>> getAllSubjects() {
		return ResponseEntity.ok(subjectService.getAllSubjects());
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubjectResponse> getSubjectById(@PathVariable Long id) {
		return ResponseEntity.ok(subjectService.getSubjectById(id));
	}
}
