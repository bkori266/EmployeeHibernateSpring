package com.cg.hibernate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String street;
	
	@ManyToOne
	private EmployeeCG employeeCG;
	
	public EmployeeCG getEmployeeCG() {
		return employeeCG;
	}
	public void setEmployeeCG(EmployeeCG employeeCG) {
		this.employeeCG = employeeCG;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	

}
