package com.househunt.model;

public class Tenant {
    private int tenantId;
    private String username;
    private String password;

    // Constructors, getters, setters

    // Default constructor
    public Tenant() {
    }

    // Parameterized constructor
    public Tenant(int tenantId, String username, String password) {
        this.tenantId = tenantId;
        this.username = username;
        this.password = password;
    }

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    // Getters and Setters
    // (Add getters and setters for all fields)
    
}
