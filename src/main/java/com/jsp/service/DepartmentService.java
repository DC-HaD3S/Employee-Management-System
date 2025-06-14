package com.jsp.service;

import java.util.List;
import java.util.Scanner;
import com.jsp.dao.DepartmentDao;
import com.jsp.entity.Department;
import com.jsp.entity.Employee;

public class DepartmentService {
    static Scanner sc = new Scanner(System.in);

    public static void deptService() {
        while (true) {
            System.out.println("Enter 1 To Add New Department");
            System.out.println("Enter 2 To Fetch The Details Of The Existing Department");
            System.out.println("Enter 3 To Update The Existing Department Details");
            System.out.println("Enter 4 To Delete The Existing Department");
            System.out.println("Enter 5 To Fetch All Employees By Department ID");
            System.out.println("Enter 6 To Exit");
            int choice = sc.nextInt();
            System.out.println("---------------------------------------------------------------");
            switch (choice) {
                case 1:
                    addNewDepartment();
                    break;
                case 2:
                    getDeptById();
                    break;
                case 3:
                    updateDept();
                    break;
                case 4:
                    deleteDept();
                    break;
                case 5:
                    getEmployeesByDeptId();
                    break;
                case 6:
                    System.out.println("Exiting Department Service");
                    return;
                default:
                    System.out.println("Invalid option, please try again");
            }
        }
    }

    public static void addNewDepartment() {
        sc.nextLine();
        System.out.println("Enter The Department Name:");
        String deptName = sc.nextLine();
        System.out.println("Enter The Department Phone:");
        long deptPhone = sc.nextLong();
        sc.nextLine();
        System.out.println("Enter The Department Location:");
        String deptLocation = sc.nextLine();
        Department department = new Department(deptName, deptPhone, deptLocation);
        DepartmentDao.addNewDepartment(department);
        System.out.println("Department added successfully: " + deptName);
    }

    public static void getDeptById() {
        System.out.println("Enter The Department Id Whose Details Needs To Be Fetched:");
        int id = sc.nextInt();
        Department department = DepartmentDao.getDeptById(id);
        if (department != null) {
            System.out.println("Department Details Are:");
            System.out.println("Department Id Is: " + department.getId());
            System.out.println("Department Name Is: " + department.getName());
            System.out.println("Department Phone Is: " + department.getPhone());
            System.out.println("Department Location Is: " + department.getLoacation());
        } else {
            System.out.println("Department With Id " + id + " Does Not Exist");
        }
    }

    public static void updateDept() {
        System.out.println("Enter Department Id Whose Details Needs To Be Updated");
        int id = sc.nextInt();
        Department department = DepartmentDao.getDeptById(id);
        if (department != null) {
            sc.nextLine();
            System.out.println("Enter The New Department Name:");
            String deptName = sc.nextLine();
            System.out.println("Enter The New Department Phone:");
            long deptPhone = sc.nextLong();
            sc.nextLine();
            System.out.println("Enter The New Department Location:");
            String deptLocation = sc.nextLine();
            department.setName(deptName);
            department.setPhone(deptPhone);
            department.setLoacation(deptLocation);
            DepartmentDao.updateDept(department);
            System.out.println("Department updated successfully: " + deptName);
        } else {
            System.out.println("Department With Id " + id + " Does Not Exist");
        }
    }

    public static void deleteDept() {
        System.out.println("Enter Department Id Whose Details Needs To Be Deleted");
        int id = sc.nextInt();
        Department department = DepartmentDao.getDeptById(id);
        if (department != null) {
            DepartmentDao.deleteDept(department);
            System.out.println("Department deleted successfully");
        } else {
            System.out.println("Department With Id " + id + " Does Not Exist");
        }
    }

    public static void getEmployeesByDeptId() {
        System.out.println("Enter The Department Id Whose Employees Need To Be Fetched:");
        int id = sc.nextInt();
        Department department = DepartmentDao.getDeptById(id);
        if (department != null) {
            List<Employee> employees = department.getEmployees();
            if (employees != null && !employees.isEmpty()) {
                System.out.println("Employees in Department " + department.getName() + " (ID: " + id + "):");
                for (Employee employee : employees) {
                    System.out.println(" - Employee ID: " + employee.getId());
                    System.out.println("   Name: " + employee.getName());
                    System.out.println("   Phone: " + employee.getPhone());
                    System.out.println("   Email: " + employee.getEmail());
                }
            } else {
                System.out.println("Department with ID " + id + " has no employees");
            }
        } else {
            System.out.println("Department with ID " + id + " does not exist");
        }
    }
}