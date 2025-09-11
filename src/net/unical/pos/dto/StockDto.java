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
public class StockDto {
    
    Integer stockId;
    Integer grnId;
    Integer mainItemCategoryId;
    Integer subItemCategoryId;
    Integer itemId;
    Integer itemBarCode;
    Integer stockCategoryId;
    String stockName;
    Integer unitType;
    Double costPrice;
    Double lastGrnPrice;
    Double qty;
    Integer status;
    Integer userId;
    Integer visible;
    String itemName;
    String codePrefix;

    public StockDto() {
    }

    public StockDto(Integer stockId, Integer grnId, Integer mainItemCategoryId, Integer subItemCategoryId, Integer itemId, Integer itemBarCode, Integer stockCategoryId, String stockName, Integer unitType, Double costPrice, Double lastGrnPrice, Double qty, Integer status, Integer userId, Integer visible) {
        this.stockId = stockId;
        this.grnId = grnId;
        this.mainItemCategoryId = mainItemCategoryId;
        this.subItemCategoryId = subItemCategoryId;
        this.itemId = itemId;
        this.itemBarCode = itemBarCode;
        this.stockCategoryId = stockCategoryId;
        this.stockName = stockName;
        this.unitType = unitType;
        this.costPrice = costPrice;
        this.lastGrnPrice = lastGrnPrice;
        this.qty = qty;
        this.status = status;
        this.userId = userId;
        this.visible = visible;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getGrnId() {
        return grnId;
    }

    public void setGrnId(Integer grnId) {
        this.grnId = grnId;
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

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCodePrefix() {
        return codePrefix;
    }

    public void setCodePrefix(String codePrefix) {
        this.codePrefix = codePrefix;
    }
    
    
}
