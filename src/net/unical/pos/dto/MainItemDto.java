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
public class MainItemDto {
    
    Integer itemId;
    Integer barCode;
    Integer mainCategoryId;
    Integer subCataegoryId;
    String prefix;
    String codePrefix;
    Double discount;
    String itemName;
    String unitType;
    String priterType;
    Double costPrice;
    Double unitPrice;
    String imagePath;
    Integer grnStatus;
    Integer sellingItem;
    Integer status;
    Integer userId;
    Double weight;
    private Integer registryId;

    public MainItemDto() {
    }

    public MainItemDto(Integer itemId, Integer barCode, Integer mainCategoryId, Integer subCataegoryId, String prefix, String codePrefix, Double discount, String itemName, String unitType, String priterType, Double costPrice, Double unitPrice, String imagePath, Integer grnStatus, Integer sellingItem, Integer status, Integer userId, Double weight) {
        this.itemId = itemId;
        this.barCode = barCode;
        this.mainCategoryId = mainCategoryId;
        this.subCataegoryId = subCataegoryId;
        this.prefix = prefix;
        this.codePrefix = codePrefix;
        this.discount = discount;
        this.itemName = itemName;
        this.unitType = unitType;
        this.priterType = priterType;
        this.costPrice = costPrice;
        this.unitPrice = unitPrice;
        this.imagePath = imagePath;
        this.grnStatus = grnStatus;
        this.sellingItem = sellingItem;
        this.status = status;
        this.userId = userId;
        this.weight = weight;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getBarCode() {
        return barCode;
    }

    public void setBarCode(Integer barCode) {
        this.barCode = barCode;
    }

    public Integer getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(Integer mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public Integer getSubCataegoryId() {
        return subCataegoryId;
    }

    public void setSubCataegoryId(Integer subCataegoryId) {
        this.subCataegoryId = subCataegoryId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getCodePrefix() {
        return codePrefix;
    }

    public void setCodePrefix(String codePrefix) {
        this.codePrefix = codePrefix;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getPriterType() {
        return priterType;
    }

    public void setPriterType(String priterType) {
        this.priterType = priterType;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getGrnStatus() {
        return grnStatus;
    }

    public void setGrnStatus(Integer grnStatus) {
        this.grnStatus = grnStatus;
    }

    public Integer getSellingItem() {
        return sellingItem;
    }

    public void setSellingItem(Integer sellingItem) {
        this.sellingItem = sellingItem;
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
    
    public Double getWeight(){
        return weight;
    }
    
    public void setWeight(Double weight){
        this.weight = weight;
    }

    public Integer getRegistryId() {
        return registryId;
    }

    public void setRegistryId(Integer registryId) {
        this.registryId = registryId;
    }
    
    
}
