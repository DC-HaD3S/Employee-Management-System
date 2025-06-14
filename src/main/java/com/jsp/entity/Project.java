package com.jsp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projectId")
	private int id;
	@Column(name = "projectTitle")
	private String title;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate startdate;
	@Column(updatable = true, nullable = false)
	private LocalDate deadline;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "project_employees", joinColumns = @JoinColumn(name = "project_projectId"), inverseJoinColumns = @JoinColumn(name = "employees_empId"))
	private List<Employee> employees = new ArrayList<>();

	public void addEmployee(Employee employee) {
		if (employees == null) {
			employees = new ArrayList<>();
		}
		if (!employees.contains(employee)) {
			employees.add(employee);
			employee.addProject(this);
		}
	}

	public void removeEmployee(Employee employee) {
		if (employees != null && employees.contains(employee)) {
			employees.remove(employee);
			employee.removeProject(this);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Project() {
	}

	public Project(String title, LocalDate deadline) {
		this.title = title;
		this.deadline = deadline;
	}
}