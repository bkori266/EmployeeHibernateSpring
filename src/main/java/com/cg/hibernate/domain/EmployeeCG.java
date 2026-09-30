package com.cg.hibernate.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class EmployeeCG {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	private String name;
	
	@OneToMany(mappedBy = "employeeCG",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Address> address;
	
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	
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
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}	

}