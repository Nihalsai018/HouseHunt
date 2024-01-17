package com.househunt.model;

import java.sql.Timestamp;

public class Contact {
    private int id;
    private String name;
    private String email;
    private String message;
    private Timestamp createdAt;
	public Contact(int id, String name, String email, String message, Timestamp createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.message = message;
		this.createdAt = createdAt;
	}
	public Contact() {
		super();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

    // Getters and setters...

    // You can generate these methods using your IDE or write them manually
    
}
