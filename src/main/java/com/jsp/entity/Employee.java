package com.jsp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "emp")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empId")
	private int id;
	@Column(name = "empName")
	private String name;
	@Column(name = "empPhone")
	private long phone;
	@Column(name = "empEmail")
	private String email;
	@CreationTimestamp
	private LocalDate dateofjoining;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_deptId")
	private Department department;
	@OneToOne(mappedBy = "employee", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private Address address;
	@ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY)
	private List<Project> projects = new ArrayList<>();

	public void addProject(Project project) {
		if (projects == null) {
			projects = new ArrayList<>();
		}
		if (!projects.contains(project)) {
			projects.add(project);
			project.addEmployee(this);
		}
	}

	public void removeProject(Project project) {
		if (projects != null && projects.contains(project)) {
			projects.remove(project);
			project.removeEmployee(this);
		}
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateofjoining() {
		return dateofjoining;
	}

	public void setDateofjoining(LocalDate dateofjoining) {
		this.dateofjoining = dateofjoining;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	// Constructors
	public Employee() {
	}

	public Employee(String name, long phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
}