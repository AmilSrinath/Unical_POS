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
public class TestModel {
    
    private Integer customerId;
    private String customerName;
    private String nic;
    private String address;
    private Integer phone;
    private Date createdDate;
    private Integer isLoyalty;
    private Double loyaltyPoints;
    private Integer status;
    private Integer userId;
    private Integer visible;

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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
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
