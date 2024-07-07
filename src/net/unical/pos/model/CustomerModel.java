/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.model;

import java.util.Date;

/**
 *
 * @author Sanjuka
 */
public class CustomerModel {
    
    private Integer customerId;
    private String customerName;
    private String nic;
    private String address;
    private Integer phoneOne;
    private Integer phoneTwo;
    private Date createdDate;
    private Integer isLoyalty;
    private Double loyaltyPoints;
    private Integer status;
    private Integer userId;
    private Integer visible;

    public CustomerModel() {
    }

    public CustomerModel(Integer customerId, String customerName, String nic, String address, Integer phoneOne, Date createdDate, Integer isLoyalty, Double loyaltyPoints, Integer status, Integer userId, Integer visible) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.nic = nic;
        this.address = address;
        this.phoneOne = phoneOne;
        this.createdDate = createdDate;
        this.isLoyalty = isLoyalty;
        this.loyaltyPoints = loyaltyPoints;
        this.status = status;
        this.userId = userId;
        this.visible = visible;
    }
    
    public CustomerModel(Integer customerId, String customerName, String nic, String address, Integer phoneOne, Integer phoneTwo, Date createdDate, Integer isLoyalty, Double loyaltyPoints, Integer status, Integer userId, Integer visible) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.nic = nic;
        this.address = address;
        this.phoneOne = phoneOne;
        this.phoneTwo = phoneTwo;
        this.createdDate = createdDate;
        this.isLoyalty = isLoyalty;
        this.loyaltyPoints = loyaltyPoints;
        this.status = status;
        this.userId = userId;
        this.visible = visible;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhoneOne() {
        return phoneOne;
    }

    public void setPhoneOne(Integer phoneOne) {
        this.phoneOne = phoneOne;
    }

    public Integer getPhoneTwo() {
        return phoneTwo;
    }

    public void setPhoneTwo(Integer phoneTwo) {
        this.phoneTwo = phoneTwo;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getIsLoyalty() {
        return isLoyalty;
    }

    public void setIsLoyalty(Integer isLoyalty) {
        this.isLoyalty = isLoyalty;
    }

    public Double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Double loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
    
    
}
