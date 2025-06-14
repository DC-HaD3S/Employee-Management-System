package com.jsp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.jsp.dao.EmployeeDao;
import com.jsp.dao.DepartmentDao;
import com.jsp.dao.ProjectDao;
import com.jsp.entity.Address;
import com.jsp.entity.Employee;
import com.jsp.entity.Department;
import com.jsp.entity.Project;

public class EmployeeService {
    static Scanner sc = new Scanner(System.in);

    public static void empService() {
        while (true) {
            System.out.println("Enter 1 To Add New Employee");
            System.out.println("Enter 2 To Fetch The Details Of The Existing Employee");
            System.out.println("Enter 3 To Update The Existing Employee Details");
            System.out.println("Enter 4 To Delete The Existing Employee");
            System.out.println("Enter 5 To Exit");
            int choice = sc.nextInt();
            System.out.println("---------------------------------------------------------------");
            switch (choice) {
                case 1:
                    addNewEmployee();
                    break;
                case 2:
                    getEmpById();
                    break;
                case 3:
                    updateEmp();
                    break;
                case 4:
                    deleteEmp();
                    break;
                case 5:
                    System.out.println("Exiting Employee Service");
                    return;
                default:
                    System.out.println("Invalid option, please try again");
            }
        }
    }

    public static void addNewEmployee() {
        sc.nextLine(); // Clear buffer
        System.out.println("Enter The Employee Name:");
        String empName = sc.nextLine();
        System.out.println("Enter The Employee Phone:");
        long empPhone = sc.nextLong();
        sc.nextLine();
        System.out.println("Enter The Employee Email:");
        String empEmail = sc.nextLine();
        System.out.println("Enter The Department ID (0 to skip):");
        int deptId = sc.nextInt();
        Department department = null;
        if (deptId != 0) {
            department = DepartmentDao.getDeptById(deptId);
            if (department == null) {
                System.out.println("Department with ID " + deptId + " does not exist");
                return;
            }
        }
        List<Project> projects = new ArrayList<>();
        System.out.println("Enter Project ID (0 to skip):");
        int projectId = sc.nextInt();
        sc.nextLine(); 
        if (projectId != 0) {
            Project project = ProjectDao.getProjectById(projectId);
            if (project == null) {
                System.out.println("Project with ID " + projectId + " does not exist");
                return;
            }
            projects.add(project);
        }
        System.out.println("--------------------------------------------");
        System.out.println("Enter The Address Details");
        System.out.println("--------------------------------------------");
        System.out.println("Enter The Street:");
        String street = sc.nextLine();
        System.out.println("Enter The City:");
        String city = sc.nextLine();
        System.out.println("Enter The State:");
        String state = sc.nextLine();
        System.out.println("Enter The Country:");
        String country = sc.nextLine();
        System.out.println("Enter The Pincode:");
        int pincode = sc.nextInt();
        sc.nextLine(); 

        Employee employee = new Employee(empName, empPhone, empEmail);
        if (department != null) {
            employee.setDepartment(department);
        }
        for (Project project : projects) {
            employee.addProject(project);
        }
        Address address = new Address(street, city, state, country, pincode, employee);
        employee.setAddress(address);

        try {
            EmployeeDao.addNewEmployee(employee, projects);
            System.out.println("Employee added successfully: " + empName);
        } catch (Exception e) {
            System.out.println("Failed to add employee: " + e.getMessage());
        }
    }

    public static void getEmpById() {
        System.out.println("Enter The Employee Id Whose Details Needs To Be Fetched:");
        int id = sc.nextInt();
        Employee employee = EmployeeDao.getEmpById(id);
        if (employee != null) {
            System.out.println("Employee Details Are:");
            System.out.println("Employee Id Is: " + employee.getId());
            System.out.println("Employee Name Is: " + employee.getName());
            System.out.println("Employee Phone Is: " + employee.getPhone());
            System.out.println("Employee Email Is: " + employee.getEmail());
            System.out.println("Employee Date of Joining Is: " + employee.getDateofjoining());
            if (employee.getDepartment() != null) {
                System.out.println("Department: " + employee.getDepartment().getName());
            }
            if (employee.getAddress() != null) {
                System.out.println("Address: " + employee.getAddress().getStreet() + ", " +
                    employee.getAddress().getCity() + ", " + employee.getAddress().getState() +
                    ", " + employee.getAddress().getCountry() + ", " + employee.getAddress().getPincode());
            }
            List<Project> projects = employee.getProjects();
            if (projects != null && !projects.isEmpty()) {
                System.out.println("Projects:");
                for (Project p : projects) {
                    System.out.println(" - " + p.getTitle() + " (ID: " + p.getId() + ")");
                }
            }
        } else {
            System.out.println("Employee With Id " + id + " Does Not Exist");
        }
    }

    public static void updateEmp() {
        System.out.println("Enter Employee Id Whose Details Needs To Be Updated");
        int id = sc.nextInt();
        Employee employee = EmployeeDao.getEmpById(id);
        if (employee != null) {
            sc.nextLine();
            System.out.println("Enter The New Employee Name:");
            String empName = sc.nextLine();
            System.out.println("Enter The New Employee Phone:");
            long empPhone = sc.nextLong();
            sc.nextLine();
            System.out.println("Enter The New Employee Email:");
            String empEmail = sc.nextLine();
            employee.setName(empName);
            employee.setPhone(empPhone);
            employee.setEmail(empEmail);
            EmployeeDao.updateEmp(employee);
            System.out.println("Employee updated successfully: " + empName);
        } else {
            System.out.println("Employee With Id " + id + " Does Not Exist");
        }
    }

    public static void deleteEmp() {
        System.out.println("Enter Employee Id Whose Details Needs To Be Deleted");
        int id = sc.nextInt();
        Employee employee = EmployeeDao.getEmpById(id);
        if (employee != null) {
            EmployeeDao.deleteEmp(employee);
            System.out.println("Employee deleted successfully");
        } else {
            System.out.println("Employee With Id " + id + " Does Not Exist");
        }
    }
}