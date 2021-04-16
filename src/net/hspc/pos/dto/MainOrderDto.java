/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hspc.pos.dto;

/**
 *
 * @author Sanjuka
 */
public class MainOrderDto {
    
    Integer orderId;
    String billNo;
    Double netAmount;
    Double discountPrice;
    Double discountPercentage;
    Double totalPrice;
    Integer paymentType;
    Integer userId;
    Integer status;

    public MainOrderDto(Integer orderId, String billNo, Double netAmount, Double discountPrice, Double discountPercentage, Double totalPrice, Integer paymentType, Integer userId, Integer status) {
        this.orderId = orderId;
        this.billNo = billNo;
        this.netAmount = netAmount;
        this.discountPrice = discountPrice;
        this.discountPercentage = discountPercentage;
        this.totalPrice = totalPrice;
        this.paymentType = paymentType;
        this.userId = userId;
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPrecentage(Double discountPresentage) {
        this.discountPercentage = discountPresentage;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
}
