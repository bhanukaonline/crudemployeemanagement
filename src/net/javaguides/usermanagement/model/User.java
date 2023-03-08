package net.javaguides.usermanagement.model;

public class User {
	private int NIC;
	private String name;
	private String Department;
	private String date;
	private String Designation;
	

	public User(int nIC, String name, String department, String date, String designation) {
		super();
		NIC = nIC;
		this.name = name;
		Department = department;
		this.date = date;
		Designation = designation;
	}
	
	public User(String name, String department, String date, String designation) {
		super();
		this.name = name;
		Department = department;
		this.date = date;
		Designation = designation;
	}

	public int getNIC() {
		return NIC;
	}
	public void setNIC(int nIC) {
		NIC = nIC;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String designation) {
		Designation = designation;
	}
	
	
	

}
