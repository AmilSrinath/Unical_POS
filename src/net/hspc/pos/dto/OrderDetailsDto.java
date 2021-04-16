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
public class OrderDetailsDto {
    
    Integer orderDetailsId;
    Integer orderId;
    Integer itemId;
    Integer itemBarCode;
    Integer qty;
    Double perItemPrice;
    Double perItemDiscountPrice;
    Double totalDiscountPrice;
    Double totalPrice;
    Integer status;

    public OrderDetailsDto(Integer orderDetailsId, Integer orderId, Integer itemId, Integer itemBarCode, Integer qty, Double perItemPrice, Double perItemDiscountPrice, Double totalDiscountPrice, Double totalPrice, Integer status) {
        this.orderDetailsId = orderDetailsId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.itemBarCode = itemBarCode;
        this.qty = qty;
        this.perItemPrice = perItemPrice;
        this.perItemDiscountPrice = perItemDiscountPrice;
        this.totalDiscountPrice = totalDiscountPrice;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Integer getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(Integer orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemBarCode() {
        return itemBarCode;
    }

    public void setItemBarCode(Integer itemBarCode) {
        this.itemBarCode = itemBarCode;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPerItemPrice() {
        return perItemPrice;
    }

    public void setPerItemPrice(Double perItemPrice) {
        this.perItemPrice = perItemPrice;
    }

    public Double getPerItemDiscountPrice() {
        return perItemDiscountPrice;
    }

    public void setPerItemDiscountPrice(Double perItemDiscountPrice) {
        this.perItemDiscountPrice = perItemDiscountPrice;
    }

    public Double getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public void setTotalDiscountPrice(Double totalDiscountPrice) {
        this.totalDiscountPrice = totalDiscountPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
}
