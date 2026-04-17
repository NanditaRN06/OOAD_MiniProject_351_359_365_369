package com.ooadproject.capstone_project_sharing_platform.dto.request;

/**
 * ProjectRequest DTO
 * 
 * Data Transfer Object for project creation and update requests.
 * 
 * Behavioral Pattern: Command Pattern
 * - Used as parameter for ProjectOperation commands
 * - Encapsulates the data needed to execute project operations
 * - Part of the command execution pipeline
 * 
 * @see com.ooadproject.capstone_project_sharing_platform.service.ProjectService.ProjectOperation
 */
public class ProjectRequest {

	private String title;

	private String description;

	private String domain;

	private String technologies;
	private String email;
	

	public ProjectRequest() {
	}

	public ProjectRequest(String title, String description, String domain, String technologies) {
		this.title = title;
		this.description = description;
		this.domain = domain;
		this.technologies = technologies;
		
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
    this.email = email;
}

	
}
