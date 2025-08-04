/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.model;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class DiscountModel {
    Integer discountId;
    String discountName;
    Double percentage;
    Double amount;
    Integer status;
   

    public DiscountModel() {
    }

    public DiscountModel(Integer discountId, String discountName, Double percentage, Double amount, Integer status) {
        this.discountId = discountId;
        this.discountName = discountName;
        this.percentage = percentage;
        this.amount = amount;
        this.status = status;
    }



    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
