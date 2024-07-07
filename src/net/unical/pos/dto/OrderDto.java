/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.dto;

import java.sql.Date;


/**
 *
 * @author Sanjuka
 */
public class OrderDto {
    
    private Integer orderId;
    private String billNo;
    private Integer discountId;
    private Double subTotalPrice;
    private Double totalDiscountPrice;
    private Double totalOrderPrice;
    private Integer paymentType;
    private Integer tableNo;
    private Date createdDate;
    private Date editedDate;
    private Integer adult;
    private Integer child;
    private String remark;
    private Integer userId;
    private Integer editedBy;
    private Integer status;
    private Integer visible;

    public OrderDto() {
    }

    public OrderDto(Integer orderId, String billNo, Integer discountId, Double subTotalPrice, Double totalDiscountPrice, Double totalOrderPrice, Integer paymentType, Integer tableNo, Date createdDate, Date editedDate, Integer adult, Integer child, String remark, Integer userId, Integer editedBy, Integer status, Integer visible) {
        this.orderId = orderId;
        this.billNo = billNo;
        this.discountId = discountId;
        this.subTotalPrice = subTotalPrice;
        this.totalDiscountPrice = totalDiscountPrice;
        this.totalOrderPrice = totalOrderPrice;
        this.paymentType = paymentType;
        this.tableNo = tableNo;
        this.createdDate = createdDate;
        this.editedDate = editedDate;
        this.adult = adult;
        this.child = child;
        this.remark = remark;
        this.userId = userId;
        this.editedBy = editedBy;
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

    public Double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(Double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getTableNo() {
        return tableNo;
    }

    public void setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
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
    
}
