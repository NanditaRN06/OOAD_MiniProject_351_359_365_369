package com.ooadproject.capstone_project_sharing_platform.dto.request;

public class ProjectRequest {

	private String title;

	private String description;

	private String domain;

	private String technologies;

	private Long subjectId;

	public ProjectRequest() {
	}

	public ProjectRequest(String title, String description, String domain, String technologies, Long subjectId) {
		this.title = title;
		this.description = description;
		this.domain = domain;
		this.technologies = technologies;
		this.subjectId = subjectId;
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

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
}
