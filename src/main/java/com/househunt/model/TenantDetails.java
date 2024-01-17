package com.househunt.model;

public class TenantDetails {
    private int tenantId;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private String occupation;

    // Constructors, getters, setters

    // Default constructor
    public TenantDetails() {
    }

    // Parameterized constructor
    public TenantDetails(int tenantId, String fullName, String phone, String email, String address, String occupation) {
        this.tenantId = tenantId;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.occupation = occupation;
    }

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

    // Getters and Setters
    // (Add getters and setters for all fields)
    
    
}

