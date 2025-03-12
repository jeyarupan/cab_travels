package bean;

import java.sql.Timestamp;

public class RideRequestBean {
    private int requestId;
    private int customerId;
    private String pickupAddress;
    private String dropoffAddress;
    private double estimatedDistance;
    private double estimatedFare;
    private String status;
    private Timestamp completionTime;
    
    // Constructor
    public RideRequestBean() {}
    
    public RideRequestBean(int requestId, int customerId, String pickupAddress, String dropoffAddress, double estimatedDistance, double estimatedFare, String status) {
        this.requestId = requestId;
        this.customerId = customerId;
        this.pickupAddress = pickupAddress;
        this.dropoffAddress = dropoffAddress;
        this.estimatedDistance = estimatedDistance;
        this.estimatedFare = estimatedFare;
        this.status = status;
    }
    
    public RideRequestBean(int requestId, int customerId, String pickupAddress, String dropoffAddress, double estimatedDistance, double estimatedFare, String status, Timestamp completionTime) {
        this.requestId = requestId;
        this.customerId = customerId;
        this.pickupAddress = pickupAddress;
        this.dropoffAddress = dropoffAddress;
        this.estimatedDistance = estimatedDistance;
        this.estimatedFare = estimatedFare;
        this.status = status;
        this.completionTime = completionTime;
    }
    
    // Getters and Setters
    public int getRequestId() {
        return requestId;
    }
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getPickupAddress() {
        return pickupAddress;
    }
    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }
    public String getDropoffAddress() {
        return dropoffAddress;
    }
    public void setDropoffAddress(String dropoffAddress) {
        this.dropoffAddress = dropoffAddress;
    }
    public double getEstimatedDistance() {
        return estimatedDistance;
    }
    public void setEstimatedDistance(double estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }
    public double getEstimatedFare() {
        return estimatedFare;
    }
    public void setEstimatedFare(double estimatedFare) {
        this.estimatedFare = estimatedFare;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Timestamp getCompletionTime() {
        return completionTime;
    }
    public void setCompletionTime(Timestamp completionTime) {
        this.completionTime = completionTime;
    }
}
