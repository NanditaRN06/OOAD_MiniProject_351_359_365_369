package com.ooadproject.capstone_project_sharing_platform.service;

import java.util.List;

import com.ooadproject.capstone_project_sharing_platform.dto.request.ProjectRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.ProjectResponse;

/**
 * ProjectService Interface with Command Pattern Support
 * 
 * This interface defines project operations following the Command Pattern (Behavioral).
 * Each method encapsulates a specific project operation that can be tracked and managed.
 * 
 * Pattern: Command Pattern (Behavioral)
 * - Encapsulates project operations as method requests
 * - Enables operation tracking and history management
 * - Supports undo functionality through operation objects
 * - Decouples caller from operation implementation
 */
public interface ProjectService {

	/* ===== CORE OPERATIONS (Encapsulated as Commands) ===== */
	
	ProjectResponse createProject(ProjectRequest request);

	List<ProjectResponse> getAllProjects();

	ProjectResponse getProjectById(Long id);

	ProjectResponse updateProject(Long id, ProjectRequest request);

	void deleteProject(Long id);
	
	/* ===== COMMAND PATTERN: Operation Execution ===== */
	
	/**
	 * Execute a project operation with command pattern
	 * Tracks operation history for audit trail
	 */
	ProjectResponse executeOperation(ProjectOperation operation);
	
	/**
	 * Get the operation history (audit trail)
	 */
	List<String> getOperationHistory();
	
	/**
	 * Clear operation history
	 */
	void clearOperationHistory();
	
	/* ===== COMMAND PATTERN: Operation Interface ===== */
	
	/**
	 * ProjectOperation - Command Interface
	 * Encapsulates a project operation as an object
	 */
	@FunctionalInterface
	interface ProjectOperation {
		ProjectResponse execute();
		
		default String getDescription() {
			return this.getClass().getSimpleName();
		}
	}
}
