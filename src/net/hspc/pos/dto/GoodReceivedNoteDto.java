/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hspc.pos.dto;

import java.util.Date;

/**
 *
 * @author Sanjuka
 */
public class GoodReceivedNoteDto {
    
    Integer grnId;
    String invoiceNo;
    Integer supplierId;
    Integer mainItemCategoryId;
    Integer subItemCategoryId;
    Integer itemBarCode;
    String itemName;
    Integer qty;
    Double discount;
    Integer unitTypeId;
    Double costPrice;
    Double unitPrice;
    Date itemExpireDate;
    Integer userId;
    Integer status;

    public GoodReceivedNoteDto(Integer grnId, String invoiceNo, Integer supplierId, Integer mainItemCategoryId, Integer subItemCategoryId, Integer itemBarCode, String itemName, Integer qty, Double discount, Integer unitTypeId, Double costPrice, Double unitPrice, Date itemExpireDate, Integer userId, Integer status) {
        this.grnId = grnId;
        this.invoiceNo = invoiceNo;
        this.supplierId = supplierId;
        this.mainItemCategoryId = mainItemCategoryId;
        this.subItemCategoryId = subItemCategoryId;
        this.itemBarCode = itemBarCode;
        this.itemName = itemName;
        this.qty = qty;
        this.discount = discount;
        this.unitTypeId = unitTypeId;
        this.costPrice = costPrice;
        this.unitPrice = unitPrice;
        this.itemExpireDate = itemExpireDate;
        this.userId = userId;
        this.status = status;
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

    public Integer getMainItemCategoryId() {
        return mainItemCategoryId;
    }

    public void setMainItemCategoryId(Integer mainItemCategoryId) {
        this.mainItemCategoryId = mainItemCategoryId;
    }

    public Integer getSubItemCategoryId() {
        return subItemCategoryId;
    }

    public void setSubItemCategoryId(Integer subItemCategoryId) {
        this.subItemCategoryId = subItemCategoryId;
    }

    public Integer getItemBarCode() {
        return itemBarCode;
    }

    public void setItemBarCode(Integer itemBarCode) {
        this.itemBarCode = itemBarCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getUnitTypeId() {
        return unitTypeId;
    }

    public void setUnitTypeId(Integer unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getItemExpireDate() {
        return itemExpireDate;
    }

    public void setItemExpireDate(Date itemExpireDate) {
        this.itemExpireDate = itemExpireDate;
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
