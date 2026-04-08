package com.ooadproject.capstone_project_sharing_platform.dto.response;

import com.ooadproject.capstone_project_sharing_platform.entity.ProjectStatus;

public class ProjectResponse {

	private Long id;
	private String title;
	private String description;
	private String domain;
	private String technologies;
	private ProjectStatus status;
	private String subjectName;

	public ProjectResponse() {
	}

	public ProjectResponse(Long id, String title, String description, String domain,
			String technologies, ProjectStatus status, String subjectName) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.domain = domain;
		this.technologies = technologies;
		this.status = status;
		this.subjectName = subjectName;
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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}
