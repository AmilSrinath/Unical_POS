/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.model;

import java.util.Date;

/**
 *
 * @author HP
 */
public class PosInvGrn implements SuperModel{
    private Integer grnId;
    private String invoiceNo;
    private Integer supplierId;
    private Double totalPrice;
    private Double totalDiscount;
    private Date createdDate;
    private Integer userId;
    private Integer status;
    private Integer visible;

    public PosInvGrn() {
    }

    public PosInvGrn(Integer grnId, String invoiceNo, Integer supplierId, Double totalPrice, Double totalDiscount, Date createdDate, Integer userId, Integer status, Integer visible) {
        this.grnId = grnId;
        this.invoiceNo = invoiceNo;
        this.supplierId = supplierId;
        this.totalPrice = totalPrice;
        this.totalDiscount = totalDiscount;
        this.createdDate = createdDate;
        this.userId = userId;
        this.status = status;
        this.visible = visible;
    }

    public Integer getGrnId() {
        return grnId;
    }

    public void setGrnId(Integer grnId) {
        this.grnId = grnId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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
