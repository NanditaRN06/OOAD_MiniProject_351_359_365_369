package com.ooadproject.capstone_project_sharing_platform;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import com.ooadproject.capstone_project_sharing_platform.controller.ProjectController;
import com.ooadproject.capstone_project_sharing_platform.controller.StudentController;
import com.ooadproject.capstone_project_sharing_platform.entity.Project;
import com.ooadproject.capstone_project_sharing_platform.entity.ProjectStatus;
import com.ooadproject.capstone_project_sharing_platform.repository.ProjectRepository;
import com.ooadproject.capstone_project_sharing_platform.service.ProjectService;

@SpringBootTest
@Transactional
class CapstoneProjectSharingPlatformApplicationTests {

	@Autowired
	private ProjectController projectController;

	@Autowired
	private StudentController studentController;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProjectRepository projectRepository;

	// 1. Context Load Test
	@Test
	void contextLoads() {
		assertNotNull(projectController);
		assertNotNull(projectService);
		assertNotNull(projectRepository);
	}

	// 2. Controller Tests (Project + Feedback)
	@Test
	void testProjectController() {
		Model model = new ConcurrentModel();
		String viewName = projectController.showCreateForm("student@test.com", model);
		assertEquals("create-project", viewName);
		assertTrue(model.containsAttribute("project"));
		assertEquals("student@test.com", model.getAttribute("email"));
	}

	@Test
	void testFeedbackController() {
		Model model = new ConcurrentModel();
		String viewName = studentController.feedbackForm("student@test.com", model);
		assertEquals("submit-feedback", viewName);
		assertTrue(model.containsAttribute("subjects"));
	}

	// 3. Service Test (basic logic)
	@Test
	void testProjectServiceLogic() {
		// Test getting all projects when empty or not
		var projects = projectService.getAllProjects();
		assertNotNull(projects);
	}

	// 4. Repository Test (basic DB)
	@Test
	void testProjectRepository() {
		Project p = new Project();
		p.setTitle("Repo Test");
		p.setDescription("Desc");
		p.setDomain("AI");
		p.setTechnologies("Python");
		p.setStatus(ProjectStatus.PENDING);
		
		Project saved = projectRepository.save(p);
		assertNotNull(saved.getId());
		
		List<Project> found = projectRepository.searchProjects("Repo Test");
		assertFalse(found.isEmpty());
	}

	// 5. One Integration Test
	@Test
	void testFullIntegrationFlow() {
		// A full flow: Save a project via repository and then search via the Controller's backing service
		Project p = new Project();
		p.setTitle("Integration Project Flow");
		p.setDescription("Full Flow description");
		p.setDomain("Web");
		p.setTechnologies("Java");
		p.setStatus(ProjectStatus.PENDING);
		projectRepository.save(p);

		// Now retrieve using service to prove integration
		var projects = projectService.getAllProjects();
		assertTrue(projects.stream().anyMatch(dto -> dto.getTitle().equals("Integration Project Flow")));
	}
}
