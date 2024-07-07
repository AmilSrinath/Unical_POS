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
public class PosMainOrderDetails {
    private Integer orderDetailId;
    private Integer orderId;
    private Integer itemId;
    private Integer itemBarCode;
    private Integer quantity;
    private Double perItemPrice;
    private Double perItemDiscountPrice;
    private Double totalDiscountPrice;
    private Double totalPrice;
    private Integer status;

    public PosMainOrderDetails() {
    }

    public PosMainOrderDetails(Integer orderDetailId, Integer orderId, Integer itemId, Integer itemBarCode, Integer quantity, Double perItemPrice, Double perItemDiscountPrice, Double totalDiscountPrice, Double totalPrice, Integer status) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.itemBarCode = itemBarCode;
        this.quantity = quantity;
        this.perItemPrice = perItemPrice;
        this.perItemDiscountPrice = perItemDiscountPrice;
        this.totalDiscountPrice = totalDiscountPrice;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
