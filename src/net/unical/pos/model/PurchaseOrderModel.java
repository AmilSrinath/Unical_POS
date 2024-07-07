/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Sanjuka
 */
public class PurchaseOrderModel {
    
    Integer poId;
    String poPrefix;
    Integer poCode;
    String poCodePrefix;
    Integer supplierId;
    String supplierName;
    Date poDate;
    Date expectedDate;
    Double totalOrderPrice;
    Integer paymentType;
    Integer status;
    Integer userId;
    Integer visible;

    public PurchaseOrderModel() {
    }

    
    public PurchaseOrderModel(Integer poId, String poPrefix, Integer poCode, String poCodePrefix, Integer supplierId, String supplierName, Date poDate, Date expectedDate, Double totalOrderPrice, Integer paymentType, Integer status, Integer userId, Integer visible) {
        this.poId = poId;
        this.poPrefix = poPrefix;
        this.poCode = poCode;
        this.poCodePrefix = poCodePrefix;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.poDate = poDate;
        this.expectedDate = expectedDate;
        this.totalOrderPrice = totalOrderPrice;
        this.paymentType = paymentType;
        this.status = status;
        this.userId = userId;
        this.visible = visible;
    }

    public Integer getPoId() {
        return poId;
    }

    public void setPoId(Integer poId) {
        this.poId = poId;
    }

    public String getPoPrefix() {
        return poPrefix;
    }

    public void setPoPrefix(String poPrefix) {
        this.poPrefix = poPrefix;
    }

    public Integer getPoCode() {
        return poCode;
    }

    public void setPoCode(Integer poCode) {
        this.poCode = poCode;
    }

    public String getPoCodePrefix() {
        return poCodePrefix;
    }

    public void setPoCodePrefix(String poCodePrefix) {
        this.poCodePrefix = poCodePrefix;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Date getPoDate() {
        return poDate;
    }

    public void setPoDate(Date poDate) {
        this.poDate = poDate;
    }

    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
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
