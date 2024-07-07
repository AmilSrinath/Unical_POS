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
public class PosMainOrder {
  private Integer orderId;
  private String billNo;
  private Double netAmount;
  private Double discountPrice;
  private Double discountPrasentage;
  private Double totalPrice;
  private Integer paymentTypeId;
  private Integer userId;
  private Integer status;
  private Integer visible;

    public PosMainOrder() {
    }

    public PosMainOrder(Integer orderId, String billNo, Double netAmount, Double discountPrice, Double discountPrasentage, Double totalPrice, Integer paymentTypeId, Integer userId, Integer status, Integer visible) {
        this.orderId = orderId;
        this.billNo = billNo;
        this.netAmount = netAmount;
        this.discountPrice = discountPrice;
        this.discountPrasentage = discountPrasentage;
        this.totalPrice = totalPrice;
        this.paymentTypeId = paymentTypeId;
        this.userId = userId;
        this.status = status;
        this.visible = visible;
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

    public Double getDiscountPrasentage() {
        return discountPrasentage;
    }

    public void setDiscountPrasentage(Double discountPrasentage) {
        this.discountPrasentage = discountPrasentage;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
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

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
 
  
}
