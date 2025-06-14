package com.jsp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dept")
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deptId")
    private int id;
    @Column(name = "deptName", nullable = false)
    private String name;
    @Column(name = "deptPhone", unique = true)
    private long phone;
    @Column(name = "deptLocation", nullable = false)
    private String loacation;
    
    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Employee> employees;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getPhone() {
        return phone;
    }
    public void setPhone(long phone) {
        this.phone = phone;
    }
    public String getLoacation() {
        return loacation;
    }
    public void setLoacation(String loacation) {
        this.loacation = loacation;
    }
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Department() {
        super();
    }
    public Department(String name, long phone, String loacation) {
        super();
        this.name = name;
        this.phone = phone;
        this.loacation = loacation;
    }
    public Department(String name, long phone, String loacation, List<Employee> employees) {
        super();
        this.name = name;
        this.phone = phone;
        this.loacation = loacation;
        this.employees = employees;
    }
}