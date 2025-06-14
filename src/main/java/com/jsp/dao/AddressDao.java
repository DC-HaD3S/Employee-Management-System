package com.jsp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.jsp.entity.Address;

public class AddressDao {
    
    private static EntityManagerFactory entityManagerFactory = null;
    private static EntityManager entityManager = null;
    
    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("rohit");
        entityManager = entityManagerFactory.createEntityManager();
    }
    
    public static void addNewAddress(Address address) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(address);
        entityTransaction.commit();
    }
    
    public static Address getAddressById(int id) {
        return entityManager.find(Address.class, id);
    }
    
    public static void updateAddress(Address address) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(address);
        entityTransaction.commit();
    }
    
    public static void deleteAddress(Address address) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(address);
        entityTransaction.commit();
    }
}