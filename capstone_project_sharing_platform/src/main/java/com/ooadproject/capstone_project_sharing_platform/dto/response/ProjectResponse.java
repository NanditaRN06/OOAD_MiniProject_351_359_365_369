package com.ooadproject.capstone_project_sharing_platform.dto.response;

import com.ooadproject.capstone_project_sharing_platform.entity.ProjectStatus;

/**
 * ProjectResponse DTO
 * 
 * Data Transfer Object for project response.
 * 
 * Behavioral Pattern: Command Pattern
 * - Returned as result from ProjectOperation command execution
 * - Encapsulates the result of project operations
 * - Part of the command execution pipeline
 * 
 * @see com.ooadproject.capstone_project_sharing_platform.service.ProjectService.ProjectOperation
 */
public class ProjectResponse {

	private Long id;
	private String title;
	private String description;
	private String domain;
	private String technologies;
	private ProjectStatus status;
	

	public ProjectResponse() {
	}

	public ProjectResponse(Long id, String title, String description, String domain,
			String technologies, ProjectStatus status) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.domain = domain;
		this.technologies = technologies;
		this.status = status;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getTechnologies() {
		return technologies;
	}

	public void setTechnologies(String technologies) {
		this.technologies = technologies;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	
}
