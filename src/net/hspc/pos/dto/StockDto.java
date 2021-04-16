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
public class StockDto {
    
    Integer id;
    Integer mainItemCategoryId;
    Integer subItemCategoryId;
    Integer itemBarCode;
    Integer stockCategoryId;
    String stockName;
    Integer unitType;
    Double costPrice;
    Double lastGrnPrice;
    Integer qty;
    Integer status;

    public StockDto(Integer id, Integer mainItemCategoryId, Integer subItemCategoryId, Integer itemBarCode, Integer stockCategoryId, String stockName, Integer unitType, Double costPrice, Double lastGrnPrice, Integer qty, Integer status) {
        this.id = id;
        this.mainItemCategoryId = mainItemCategoryId;
        this.subItemCategoryId = subItemCategoryId;
        this.itemBarCode = itemBarCode;
        this.stockCategoryId = stockCategoryId;
        this.stockName = stockName;
        this.unitType = unitType;
        this.costPrice = costPrice;
        this.lastGrnPrice = lastGrnPrice;
        this.qty = qty;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getStockCategoryId() {
        return stockCategoryId;
    }

    public void setStockCategoryId(Integer stockCategoryId) {
        this.stockCategoryId = stockCategoryId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getLastGrnPrice() {
        return lastGrnPrice;
    }

    public void setLastGrnPrice(Double lastGrnPrice) {
        this.lastGrnPrice = lastGrnPrice;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
}
