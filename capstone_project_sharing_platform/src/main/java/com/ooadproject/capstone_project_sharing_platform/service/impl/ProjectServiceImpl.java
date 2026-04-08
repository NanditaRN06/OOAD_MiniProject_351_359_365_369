package com.ooadproject.capstone_project_sharing_platform.service.impl;

import com.ooadproject.capstone_project_sharing_platform.dto.request.ProjectRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.ProjectResponse;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ooadproject.capstone_project_sharing_platform.entity.Project;
import com.ooadproject.capstone_project_sharing_platform.entity.ProjectStatus;
import com.ooadproject.capstone_project_sharing_platform.entity.Subject;
import com.ooadproject.capstone_project_sharing_platform.repository.ProjectRepository;
import com.ooadproject.capstone_project_sharing_platform.repository.SubjectRepository;
import com.ooadproject.capstone_project_sharing_platform.service.ProjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;
	private final SubjectRepository subjectRepository;

	@Override
	public ProjectResponse createProject(ProjectRequest request) {
		Subject subject = subjectRepository.findById(request.getSubjectId())
				.orElseThrow(() -> new RuntimeException("Subject not found with id: " + request.getSubjectId()));

		Project project = new Project();
		project.setTitle(request.getTitle());
		project.setDescription(request.getDescription());
		project.setDomain(request.getDomain());
		project.setTechnologies(request.getTechnologies());
		project.setStatus(ProjectStatus.PENDING);
		project.setSubject(subject);

		Project savedProject = projectRepository.save(project);
		return mapToResponse(savedProject);
	}

	@Override
	public List<ProjectResponse> getAllProjects() {
		return projectRepository.findAll().stream().map(this::mapToResponse).toList();
	}

	@Override
	public ProjectResponse getProjectById(Long id) {
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
		return mapToResponse(project);
	}

	@Override
	public ProjectResponse updateProject(Long id, ProjectRequest request) {
		Project existingProject = projectRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Project not found with id: " + id));

		Subject subject = subjectRepository.findById(request.getSubjectId())
				.orElseThrow(() -> new RuntimeException("Subject not found with id: " + request.getSubjectId()));

		existingProject.setTitle(request.getTitle());
		existingProject.setDescription(request.getDescription());
		existingProject.setDomain(request.getDomain());
		existingProject.setTechnologies(request.getTechnologies());
		existingProject.setSubject(subject);

		Project updatedProject = projectRepository.save(existingProject);
		return mapToResponse(updatedProject);
	}

	@Override
	public void deleteProject(Long id) {
		Project existingProject = projectRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
		projectRepository.delete(existingProject);
	}

	private ProjectResponse mapToResponse(Project project) {
		return new ProjectResponse(
				project.getId(),
				project.getTitle(),
				project.getDescription(),
				project.getDomain(),
				project.getTechnologies(),
				project.getStatus(),
				project.getSubject() != null ? project.getSubject().getName() : null);
	}
}
