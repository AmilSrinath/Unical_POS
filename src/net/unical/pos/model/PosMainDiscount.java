/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.model;

/**
 *
 * @author HP
 */
public class PosMainDiscount {
    private Integer discountId;
    private String discountNmme;
    private Double percentage;
    private Double amount;
    private Integer status;

    /**
     * @return the discountId
     */
    public Integer getDiscountId() {
        return discountId;
    }

    /**
     * @param discountId the discountId to set
     */
    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    /**
     * @return the discountNmme
     */
    public String getDiscountNmme() {
        return discountNmme;
    }

    /**
     * @param discountNmme the discountNmme to set
     */
    public void setDiscountNmme(String discountNmme) {
        this.discountNmme = discountNmme;
    }

    /**
     * @return the percentage
     */
    public Double getPercentage() {
        return percentage;
    }

    /**
     * @param percentage the percentage to set
     */
    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
