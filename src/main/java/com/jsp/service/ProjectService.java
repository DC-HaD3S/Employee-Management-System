package com.jsp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import com.jsp.dao.ProjectDao;
import com.jsp.dao.EmployeeDao;
import com.jsp.entity.Project;
import com.jsp.entity.Employee;

public class ProjectService {
	static Scanner sc = new Scanner(System.in);

	public static void projectService() {
		while (true) {
			System.out.println("Enter 1 To Add New Project");
			System.out.println("Enter 2 To Fetch The Details Of The Existing Project");
			System.out.println("Enter 3 To Update The Existing Project Details");
			System.out.println("Enter 4 To Delete The Existing Project");
			System.out.println("Enter 5 To Fetch Projects By Employee ID");
			System.out.println("Enter 6 To Exit");
			int choice = sc.nextInt();
			System.out.println("---------------------------------------------------------------");
			switch (choice) {
			case 1:
				addNewProject();
				break;
			case 2:
				getProjectById();
				break;
			case 3:
				updateProject();
				break;
			case 4:
				deleteProject();
				break;
			case 5:
				getProjectsByEmployeeId();
				break;
			case 6:
				System.out.println("Exiting Project Service");
				return;
			default:
				System.out.println("Invalid option, please try again");
			}
		}
	}

	public static void addNewProject() {
		sc.nextLine();
		System.out.println("Enter The Project Name:");
		String projectName = sc.nextLine();
		System.out.println("Project deadline details:");
		System.out.println("Enter project deadline day (1-31):");
		int day = getValidIntInput(1, 31);
		System.out.println("Enter project deadline month (1-12):");
		int month = getValidIntInput(1, 12);
		System.out.println("Enter project deadline year (e.g., 2025):");
		int year = getValidIntInput(2000, 2100);
		sc.nextLine();

		LocalDate deadline = LocalDate.of(year, month, day);
		Project project = new Project(projectName, deadline);
		ProjectDao.addNewProject(project);
		System.out.println("Project added successfully: " + projectName);
	}

	public static void getProjectById() {
		System.out.println("Enter The Project Id Whose Details Needs To Be Fetched:");
		int id = sc.nextInt();
		Project project = ProjectDao.getProjectById(id);
		if (project != null) {
			System.out.println("Project Details Are:");
			System.out.println("Project Id Is: " + project.getId());
			System.out.println("Project Title Is: " + project.getTitle());
			System.out.println("Start Date Is: " + project.getStartdate());
			System.out.println("Deadline Is: " + project.getDeadline());
			List<Employee> employees = project.getEmployees();
			if (employees != null && !employees.isEmpty()) {
				System.out.println("Employees:");
				for (Employee e : employees) {
					System.out.println(" - " + e.getName() + " (ID: " + e.getId() + ")");
				}
			}
		} else {
			System.out.println("Project With Id " + id + " Does Not Exist");
		}
	}

	public static void updateProject() {
		System.out.println("Enter Project Id Whose Details Needs To Be Updated:");
		int id = sc.nextInt();
		Project project = ProjectDao.getProjectById(id);
		if (project != null) {
			sc.nextLine();
			System.out.println("Enter The New Project Name:");
			String projectName = sc.nextLine();
			System.out.println("Enter new deadline day (1-31):");
			int day = getValidIntInput(1, 31);
			System.out.println("Enter new deadline month (1-12):");
			int month = getValidIntInput(1, 12);
			System.out.println("Enter new deadline year (e.g., 2025):");
			int year = getValidIntInput(2000, 2100);

			project.setTitle(projectName);
			project.setDeadline(LocalDate.of(year, month, day));
			ProjectDao.updatePro(project);
			System.out.println("Project updated successfully: " + projectName);
		} else {
			System.out.println("Project With Id " + id + " Does Not Exist");
		}
	}

	public static void deleteProject() {
		System.out.println("Enter Project Id Whose Details Needs To Be Deleted:");
		int id = sc.nextInt();
		Project project = ProjectDao.getProjectById(id);
		if (project != null) {
			ProjectDao.deletePro(project);
			System.out.println("Project deleted successfully");
		} else {
			System.out.println("Project With Id " + id + " Does Not Exist");
		}
	}

	public static void getProjectsByEmployeeId() {
		System.out.println("Enter The Employee Id Whose Projects Need To Be Fetched:");
		int empId = sc.nextInt();
		Employee employee = EmployeeDao.getEmpById(empId);
		if (employee != null) {
			List<Project> projects = employee.getProjects();
			if (projects != null && !projects.isEmpty()) {
				System.out.println("Projects for Employee " + employee.getName() + " (ID: " + empId + "):");
				for (Project project : projects) {
					System.out.println(" - Project ID: " + project.getId());
					System.out.println("   Title: " + project.getTitle());
					System.out.println("   Start Date: " + project.getStartdate());
					System.out.println("   Deadline: " + project.getDeadline());
				}
			} else {
				System.out.println("Employee with ID " + empId + " has no projects");
			}
		} else {
			System.out.println("Employee with ID " + empId + " does not exist");
		}
	}

	private static int getValidIntInput(int min, int max) {
		while (true) {
			try {
				int input = sc.nextInt();
				if (input >= min && input <= max) {
					return input;
				} else {
					System.out.println("Please enter a number between " + min + " and " + max + ":");
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ":");
				sc.nextLine();
			}
		}
	}
}