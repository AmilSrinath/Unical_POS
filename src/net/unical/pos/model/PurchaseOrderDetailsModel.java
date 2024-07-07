/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.model;

/**
 *
 * @author Sanjuka
 */
public class PurchaseOrderDetailsModel {
    Integer poDetailsId;
    Integer poId;
    Integer itemId;
    String itemName;
    Double qty;
    Double expectedPrice;
    Double lastGrnPrice;
    Double totalPrice;
    Integer userId;

    public PurchaseOrderDetailsModel() {
    }

    public PurchaseOrderDetailsModel(Integer poDetailsId, Integer poId, Integer itemId, String itemName, Double qty, Double expectedPrice, Double lastGrnPrice, Double totalPrice, Integer userId) {
        this.poDetailsId = poDetailsId;
        this.poId = poId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.qty = qty;
        this.expectedPrice = expectedPrice;
        this.lastGrnPrice = lastGrnPrice;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

    public Integer getPoDetailsId() {
        return poDetailsId;
    }

    public void setPoDetailsId(Integer poDetailsId) {
        this.poDetailsId = poDetailsId;
    }

    public Integer getPoId() {
        return poId;
    }

    public void setPoId(Integer poId) {
        this.poId = poId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(Double expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    public Double getLastGrnPrice() {
        return lastGrnPrice;
    }

    public void setLastGrnPrice(Double lastGrnPrice) {
        this.lastGrnPrice = lastGrnPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    
}
