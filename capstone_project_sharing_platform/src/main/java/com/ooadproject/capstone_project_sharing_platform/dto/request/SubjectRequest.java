package com.ooadproject.capstone_project_sharing_platform.dto.request;

public class SubjectRequest {

	private String name;

	private String type;

	public SubjectRequest() {
	}

	public SubjectRequest(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
