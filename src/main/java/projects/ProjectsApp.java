package projects;



//import java.sql.Connection;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;
import java.util.ArrayList;

public class ProjectsApp {
	
	private Scanner in = new Scanner(System.in);
	
	//	@formatter: off
	private List<String> operations = List.of(
		"1) Add a project",
		"2) List of projects",
		"3) Select a project",
		"4) Update project details",
		"5) Delete a project"
	);
	//	@formatter: on
	
	Project curProject = null;
	
	private ProjectService projectService = new ProjectService();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//I will need to show that my application can handle incorrect inputs from 
		//both letters and out of bound numbers
		new ProjectsApp().processUserSelections();
		//Will also need to show that I am succesfully able to add a project to the database
		
		//Attempts to create a connection to the Database
		//Connection conn = DbConnection.getConnection();

		
		
	}
	private void processUserSelections() {
		// TODO Auto-generated method stub
		boolean done = false; //This is an active flag that is use full for exeting a loop
		
		while (!done) {
			
			try {
				printOperation();
				int selection = getUserSelection();
				
				
				switch (selection) {
				
				case -1:
					done = exitMenue();
					break;
					
				default:
					System.out.println(selection + " is not a valid selection try again");
					break;
				case 1:
					createProject();
					break;
					
				case 2:
					listProject();
					break;
					
				case 3:
					selectProject();
					break;
					
				case 4:
					updateProjectDetails();
					break;
					
				case 5:
					deleteProject();
					
				}
				
				

			} catch (Exception e) {
				System.out.println("\nError: " + e + " Try again.");
			}
		}
	}
	
	private void deleteProject() {
		listProject();
		
		Integer projectId = getIntInput("Enter the ID of the project to be deleted");
		
		projectService.deleteProject(projectId); 
			
		System.out.println("Project with the project Id of " + projectId + " has been deleted");
		
		
	}
	
	private void updateProjectDetails() {
		if(Objects.isNull(curProject)) {
			System.out.println("\nPlease select a project");
			return;
		}
		
		String projectName = getStringInput("Enter the project name [" + curProject.getProjectName() + "]");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours [" + curProject.getEstimatedHours() + "]");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours [" + curProject.getActualHours() + "]");
		Integer diffuculty = getIntInput("Enter the project diffuculy (1 - 5) [" + curProject.getDifficulty() + "]");
		String notes = getStringInput("Enter the project notes [" + curProject.getNotes() + "]"); 
		
		Project project = new Project();
		
		project.setProjectId(curProject.getProjectId());
		project.setProjectName(Objects.isNull(projectName) ? curProject.getProjectName() : projectName);
		project.setEstimatedHours(Objects.isNull(projectName) ? curProject.getEstimatedHours() : estimatedHours);
		project.setActualHours(Objects.isNull(projectName) ? curProject.getActualHours() : actualHours);
		project.setDifficulty(Objects.isNull(projectName) ? curProject.getDifficulty() : diffuculty);
		project.setNotes(Objects.isNull(projectName) ? curProject.getNotes() : notes);
	
		projectService.modifyProjectDetails(project);
		
		curProject = projectService.fetchProjectById(curProject.getProjectId());
	}
	private void selectProject() {
		// TODO Auto-generated method stub
		listProject();
		
		Integer projectId = getIntInput("Enter a project ID to select a project");
		
		
		curProject = projectService.fetchProjectById(projectId);
		
		if(Objects.isNull(curProject)) {
			System.out.println("\nInvalid project ID selected");
		}
	}
	private void createProject() {
		// TODO Auto-generated method stub
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours");
		Integer diffuculty = getIntInput("Enter the project diffuculy (1 - 5)");
		String notes = getStringInput("Enter the project notes");
		
		Project project = new Project();
		
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(diffuculty);
		project.setNotes(notes);
		
		Project dbProject = projectService.addProject(project);
		
		System.out.println("You have sucessfully created project: " + dbProject);
		
	}


	private BigDecimal getDecimalInput(String prompt) {
		// TODO Auto-generated method stub
		
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
		}
		
		try {
			return new BigDecimal(input).setScale(2);
		} catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid decimal number.");
		}

	}
	private void listProject() {
		// TODO Auto-generated method stub
		List<Project> projects = projectService.fetchAllProjects();
		
		projects.forEach(project -> System.out.println("Project Name: " + project.getProjectName() +
				"\nProject ID: " + project.getProjectId() + "\nDifficulty: " + project.getDifficulty() +
				"\nEstimated Hours: " + project.getEstimatedHours() + "\nActual Hours: " + project.getActualHours() +
				"\nNotes: " + project.getNotes() + "\n"
				));
	}
	private boolean exitMenue() {
		// TODO Auto-generated method stub
		System.out.println("Bye Bye ;)");
		
		return true;
	}
	private int getUserSelection() {
		// TODO Auto-generated method stub
		
		
		Integer input = getIntInput("Enter a menu selection");
		
		return Objects.isNull(input) ? -1 : input;
	}
	
	private Integer getIntInput(String prompt) {
		// TODO Auto-generated method stub
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
		}
		
		try {
			return Integer.valueOf(input);
		} catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid number.");
		}
	
	}
	
	private String getStringInput(String prompt) {
		// TODO Auto-generated method stub
		System.out.print(prompt + ": ");
		String input = in.nextLine();
		return input.isBlank() ? null : input.trim();
	}
	private void printOperation() {
		// TODO Auto-generated method stub
		System.out.println("\nThese are the available selections. Press the Enter key to quit: ");
		operations.forEach(line -> System.out.println(" " + line));
		if(Objects.isNull(curProject)) {
			System.out.println("\nYou are not working with a project");
		} else {
			System.out.println("\nYou are working with project: " + curProject);
		}
	}

}
