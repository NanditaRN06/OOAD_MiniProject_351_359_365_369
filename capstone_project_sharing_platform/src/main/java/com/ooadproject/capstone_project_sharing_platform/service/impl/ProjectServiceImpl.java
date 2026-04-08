package com.ooadproject.capstone_project_sharing_platform.service.impl;

import com.ooadproject.capstone_project_sharing_platform.dto.request.ProjectRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.ProjectResponse;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ooadproject.capstone_project_sharing_platform.entity.Project;
import com.ooadproject.capstone_project_sharing_platform.entity.ProjectStatus;
import com.ooadproject.capstone_project_sharing_platform.entity.Student;
import com.ooadproject.capstone_project_sharing_platform.repository.ProjectRepository;
import com.ooadproject.capstone_project_sharing_platform.repository.StudentRepository;
import com.ooadproject.capstone_project_sharing_platform.service.ProjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;
	
	private final StudentRepository studentRepository;
	@Override
	public ProjectResponse createProject(ProjectRequest request) {
		Student student = studentRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new RuntimeException("Student not found"));



		Project project = new Project();
		project.setTitle(request.getTitle());
		project.setDescription(request.getDescription());
		project.setDomain(request.getDomain());
		project.setTechnologies(request.getTechnologies());
		project.setStatus(ProjectStatus.PENDING);
	
		project.setStudent(student);
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

		

		existingProject.setTitle(request.getTitle());
		existingProject.setDescription(request.getDescription());
		existingProject.setDomain(request.getDomain());
		existingProject.setTechnologies(request.getTechnologies());
	

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
				project.getStatus());
	}
}
