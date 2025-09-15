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
public class PosInvStock {
    private Integer stockId;
    private Integer grnId;
    private Integer mainItemCategoryId;
    private Integer subItemCategoryId;
    private Integer itemId;
    private Integer itemBarCode;
    private Integer stockCategoryId;
    private String StockName;
    private Integer unitTypeId;
    private Double costPrice;
    private Double lastGrnPrice;
    private Double quantity;
    private Integer status;
    private Integer userId;
    private Integer visible;
    private String codePrefix;
    private String itemName;
    private Integer registryId;

    public PosInvStock() {
    }

    public PosInvStock(Integer stockId, Integer grnId, Integer mainItemCategoryId, Integer subItemCategoryId, Integer itemId, Integer itemBarCode, Integer stockCategoryId, String StockName, Integer unitTypeId, Double costPrice, Double lastGrnPrice, Double quantity, Integer status, Integer userId, Integer visible) {
        this.stockId = stockId;
        this.grnId = grnId;
        this.mainItemCategoryId = mainItemCategoryId;
        this.subItemCategoryId = subItemCategoryId;
        this.itemId = itemId;
        this.itemBarCode = itemBarCode;
        this.stockCategoryId = stockCategoryId;
        this.StockName = StockName;
        this.unitTypeId = unitTypeId;
        this.costPrice = costPrice;
        this.lastGrnPrice = lastGrnPrice;
        this.quantity = quantity;
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
        return StockName;
    }

    public void setStockName(String StockName) {
        this.StockName = StockName;
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

    public Double getLastGrnPrice() {
        return lastGrnPrice;
    }

    public void setLastGrnPrice(Double lastGrnPrice) {
        this.lastGrnPrice = lastGrnPrice;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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

    public String getCodePrefix() {
        return codePrefix;
    }

    public void setCodePrefix(String codePrefix) {
        this.codePrefix = codePrefix;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getRegistryId() {
        return registryId;
    }

    public void setRegistryId(Integer registryId) {
        this.registryId = registryId;
    }
    
    
}
