package com.jsp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.hibernate.Hibernate;
import com.jsp.entity.Department;

public class DepartmentDao {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rohit");

    public static void addNewDepartment(Department department) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(department);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException("Failed to add department: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    public static Department getDeptById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Department department = entityManager.find(Department.class, id);
            if (department != null) {
                Hibernate.initialize(department.getEmployees()); // Initialize lazy collection
            }
            return department;
        } finally {
            entityManager.close();
        }
    }

    public static void updateDept(Department department) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.merge(department);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException("Failed to update department: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    public static void deleteDept(Department department) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Department managedDepartment = entityManager.merge(department);
            entityManager.remove(managedDepartment);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException("Failed to delete department: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }
}