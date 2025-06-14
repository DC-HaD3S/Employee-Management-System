package com.jsp.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.hibernate.Hibernate;
import com.jsp.entity.Employee;
import com.jsp.entity.Project;

public class EmployeeDao {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rohit");

    public static void addNewEmployee(Employee employee, List<Project> projects) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            // Merge department if not null
            if (employee.getDepartment() != null) {
                employee.setDepartment(entityManager.merge(employee.getDepartment()));
            }
            // Persist employee first to generate ID
            entityManager.persist(employee);
            // Synchronize bidirectional ManyToMany relationship
            if (projects != null && !projects.isEmpty()) {
                for (Project project : projects) {
                    Project managedProject = entityManager.merge(project);
                    List<Employee> projectEmployees = managedProject.getEmployees();
                    if (projectEmployees != null && !projectEmployees.contains(employee)) {
                        projectEmployees.add(employee);
                        managedProject.setEmployees(projectEmployees);
                    }
                    List<Project> employeeProjects = employee.getProjects();
                    if (employeeProjects != null && !employeeProjects.contains(managedProject)) {
                        employeeProjects.add(managedProject);
                        employee.setProjects(employeeProjects);
                    }
                }
            }
            // Merge employee to update relationships
            employee = entityManager.merge(employee);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException("Failed to persist employee: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    public static Employee getEmpById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Employee employee = entityManager.find(Employee.class, id);
            if (employee != null) {
                // Initialize lazy collections and associations
                if (employee.getProjects() != null) {
                    employee.getProjects().size(); // Force initialize projects
                }
                if (employee.getDepartment() != null) {
                    Hibernate.initialize(employee.getDepartment()); // Force initialize department
                }
            }
            return employee;
        } finally {
            entityManager.close();
        }
    }

    public static void updateEmp(Employee employee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.merge(employee);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException("Failed to update employee: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    public static void deleteEmp(Employee employee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Employee managedEmployee = entityManager.merge(employee);
            entityManager.remove(managedEmployee);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException("Failed to delete employee: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }
}