package com.househunt.model;


public class Booking {
    private int bookingId;
    private int tenantId;
    private int ownerId;
    private int rentalId;
    private String status;
    private TenantDetails tenantDetails;
    private OwnerDetails ownerDetails;
    private RentalData rentalData;
    
    // Constructors, getters, setters

    public RentalData getRentalData() {
		return rentalData;
	}

	public void setRentalData(RentalData rentalData) {
		this.rentalData = rentalData;
	}

	public OwnerDetails getOwnerDetails() {
		return ownerDetails;
	}

	public void setOwnerDetails(OwnerDetails ownerDetails) {
		this.ownerDetails = ownerDetails;
	}

	// Default constructor
    public Booking() {
    }

    public Booking(int tenantId, int ownerId, int rentalId, String status) {
		super();
		this.tenantId = tenantId;
		this.ownerId = ownerId;
		this.rentalId = rentalId;
		this.status = status;
	}

	// Parameterized constructor
    public Booking(int bookingId, int tenantId, int ownerId, int rentalId, String status) {
        this.bookingId = bookingId;
        this.tenantId = tenantId;
        this.ownerId = ownerId;
        this.rentalId = rentalId;
        this.status = status;
    }

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getRentalId() {
		return rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	    // Getter and setter for tenantDetails
	    public TenantDetails getTenantDetails() {
	        return tenantDetails;
	    }

	    public void setTenantDetails(TenantDetails tenantDetails) {
	        this.tenantDetails = tenantDetails;
	    }
}

