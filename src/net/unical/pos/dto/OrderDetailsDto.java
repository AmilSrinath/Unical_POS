/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.dto;

/**
 *
 * @author Sanjuka
 */
public class OrderDetailsDto {
    
    private Integer orderDetailsId;
    private Integer orderId;
    private Integer itemId;
    private Integer itemBarCode;
    private Integer unitTypeId;
    private Integer printerTypeId;
    private Integer qty;
    private Double perItemPrice;
    private Double totalDiscountPrice;
    private Double totalItemPrice;
    private String remark;
    private Integer status;
    private Integer userId;

    public OrderDetailsDto() {
    }

    public OrderDetailsDto(Integer orderDetailsId, Integer orderId, Integer itemId, Integer itemBarCode, Integer unitTypeId, Integer printerTypeId, Integer qty, Double perItemPrice, Double totalDiscountPrice, Double totalItemPrice, String remark, Integer status, Integer userId) {
        this.orderDetailsId = orderDetailsId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.itemBarCode = itemBarCode;
        this.unitTypeId = unitTypeId;
        this.printerTypeId = printerTypeId;
        this.qty = qty;
        this.perItemPrice = perItemPrice;
        this.totalDiscountPrice = totalDiscountPrice;
        this.totalItemPrice = totalItemPrice;
        this.remark = remark;
        this.status = status;
        this.userId = userId;
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

    public Integer getUnitTypeId() {
        return unitTypeId;
    }

    public void setUnitTypeId(Integer unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    public Integer getPrinterTypeId() {
        return printerTypeId;
    }

    public void setPrinterTypeId(Integer printerTypeId) {
        this.printerTypeId = printerTypeId;
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

    public Double getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public void setTotalDiscountPrice(Double totalDiscountPrice) {
        this.totalDiscountPrice = totalDiscountPrice;
    }

    public Double getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(Double totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
    
    
}
