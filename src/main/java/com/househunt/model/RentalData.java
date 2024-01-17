package com.househunt.model;
public class RentalData {
    private int rentalId;
    private int ownerId;
    private String fullName;
    private String phone;
    private String address;
    private String area;
    private String rent;
    private String size;
    private String familyStatus;

    // Constructors, getters, setters

    // Default constructor
    public RentalData() {
    }

    // Parameterized constructor
    public RentalData(int rentalId, int ownerId, String fullName, String phone, String address,
                      String area, String rent, String size, String familyStatus) {
        this.rentalId = rentalId;
        this.ownerId = ownerId;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.area = area;
        this.rent = rent;
        this.size = size;
        this.familyStatus = familyStatus;
    }

	public int getRentalId() {
		return rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRent() {
		return rent;
	}

	public void setRent(String rent) {
		this.rent = rent;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFamilyStatus() {
		return familyStatus;
	}

	public void setFamilyStatus(String familyStatus) {
		this.familyStatus = familyStatus;
	}

    // Getters and Setters
    // (Add getters and setters for all fields)
    
    
}
