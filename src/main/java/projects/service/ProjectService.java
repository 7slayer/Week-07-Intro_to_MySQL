package projects.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import projects.dao.ProjectDao;
import projects.entity.Project;
import projects.exception.DbException;

public class ProjectService {
	
	private ProjectDao projectDao = new ProjectDao();

	public Project addProject(Project project) {
		// TODO Auto-generated method stub
		
	return projectDao.insertProject(project);
	
	}

	public List<Project> fetchAllProjects() {
		// TODO Auto-generated method stub
		return projectDao.fetchAllProjects();
	}

	public Project fetchProjectById(Integer projectId) {
		// TODO Auto-generated method stub
		return projectDao.fetchProjectById(projectId).orElseThrow(
			() -> new NoSuchElementException("Project with Project ID=" + projectId + " doesn't exist")
			);
	}
	
	public void modifyProjectDetails(Project project) {
		if(!projectDao.modifyProjectDetails(project)) {
			throw new DbException("Project with ID=" + project.getProjectId() + " does not exist.");
		}
	}

	public void deleteProject(Integer projectId) {
		if (!projectDao.deleteProject(projectId)) {
			throw new DbException("Project with ID=" + projectId + " does not exist.");
		}
		
		
	}

}
