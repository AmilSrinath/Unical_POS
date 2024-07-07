/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.dto;

import java.util.Date;

/**
 *
 * @author Sanjuka
 */
public class GoodReceivedNoteDto {
    
    Integer grnId;
    String invoiceNo;
    Integer supplierId;
    Double totalPrice;
    Double totalDiscount;
    Date createDate;
    Integer status;
    Integer userId;
    Integer visible;

    public GoodReceivedNoteDto() {
    }

    public GoodReceivedNoteDto(Integer grnId, String invoiceNo, Integer supplierId, Double totalPrice, Double totalDiscount, Date createDate, Integer status, Integer userId, Integer visible) {
        this.grnId = grnId;
        this.invoiceNo = invoiceNo;
        this.supplierId = supplierId;
        this.totalPrice = totalPrice;
        this.totalDiscount = totalDiscount;
        this.createDate = createDate;
        this.status = status;
        this.userId = userId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
