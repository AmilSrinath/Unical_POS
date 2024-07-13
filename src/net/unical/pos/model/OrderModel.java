/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.model;

import java.util.Date;

/**
 *
 * @author apple
 */
public class OrderModel {
    private Integer orderId;
    private Integer customerId;
    private Integer deliveryOrderId;
    private String billNo;
    private Integer discountId;
    private Double subTotalPrice;
    private Double totalDiscountPrice;
    private Double deliveryFee;
    private Double totalOrderPrice;
    private Integer paymentTypeId;
    private Integer tableId;
    private Date createdDate;
    private Date editedDate;
    private Integer adult;
    private Integer child;
    private String remark;
    private Integer userId;
    private Integer editedBy;
    private Integer status;
    private Integer visible;
    private Integer isPrint;
    
    public OrderModel() {}

    public OrderModel(Integer orderId, Integer customerId, Integer deliveryOrderId, String billNo, Integer discountId, Double subTotalPrice, Double totalDiscountPrice, Double deliveryFee, Double totalOrderPrice, Integer paymentTypeId, Integer tableId, Date createdDate, Date editedDate, Integer adult, Integer child, String remark, Integer userId, Integer editedBy, Integer status, Integer visible, Integer isPrint) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliveryOrderId = deliveryOrderId;
        this.billNo = billNo;
        this.discountId = discountId;
        this.subTotalPrice = subTotalPrice;
        this.totalDiscountPrice = totalDiscountPrice;
        this.deliveryFee = deliveryFee;
        this.totalOrderPrice = totalOrderPrice;
        this.paymentTypeId = paymentTypeId;
        this.tableId = tableId;
        this.createdDate = createdDate;
        this.editedDate = editedDate;
        this.adult = adult;
        this.child = child;
        this.remark = remark;
        this.userId = userId;
        this.editedBy = editedBy;
        this.status = status;
        this.visible = visible;
        this.isPrint = isPrint;
    }
    
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(Integer deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public Double getSubTotalPrice() {
        return subTotalPrice;
    }

    public void setSubTotalPrice(Double subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
    }

    public Double getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public void setTotalDiscountPrice(Double totalDiscountPrice) {
        this.totalDiscountPrice = totalDiscountPrice;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(Double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
    }

    public Integer getAdult() {
        return adult;
    }

    public void setAdult(Integer adult) {
        this.adult = adult;
    }

    public Integer getChild() {
        return child;
    }

    public void setChild(Integer child) {
        this.child = child;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(Integer editedBy) {
        this.editedBy = editedBy;
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

    public Integer getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(Integer isPrint) {
        this.isPrint = isPrint;
    }
    
    @Override
    public String toString() {
        return "OrderModel{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", deliveryOrderId=" + deliveryOrderId +
                ", billNo='" + billNo + '\'' +
                ", discountId=" + discountId +
                ", subTotalPrice=" + subTotalPrice +
                ", totalDiscountPrice=" + totalDiscountPrice +
                ", deliveryFee=" + deliveryFee +
                ", totalOrderPrice=" + totalOrderPrice +
                ", paymentTypeId=" + paymentTypeId +
                ", tableId=" + tableId +
                ", createdDate=" + createdDate +
                ", editedDate=" + editedDate +
                ", adult=" + adult +
                ", child=" + child +
                ", remark='" + remark + '\'' +
                ", userId=" + userId +
                ", editedBy=" + editedBy +
                ", status=" + status +
                ", visible=" + visible +
                ", isPrint=" + isPrint +
                '}';
    }
}
