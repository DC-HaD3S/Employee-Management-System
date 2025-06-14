package com.jsp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.hibernate.Hibernate;
import com.jsp.entity.Project;

public class ProjectDao {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rohit");

    public static void addNewProject(Project project) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(project);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException("Failed to add project: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    public static Project getProjectById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Project project = entityManager.find(Project.class, id);
            if (project != null) {
                Hibernate.initialize(project.getEmployees()); // Initialize lazy collection
            }
            return project;
        } finally {
            entityManager.close();
        }
    }

    public static void updatePro(Project project) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.merge(project);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException("Failed to update project: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    public static void deletePro(Project project) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Project managedProject = entityManager.merge(project);
            entityManager.remove(managedProject);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException("Failed to delete project: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }
}