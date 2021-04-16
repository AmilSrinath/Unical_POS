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
public class ItemDto {
    
    Integer itemId;
    String mainCategoryId;
    String subCataegoryId;
    Integer barCode;
    String prefix;
    String codePrefix;
    Double discount;
    String itemName;
    String unitType;
    String priterType;
    Double costPrice;
    Double unitPrice;
    String imagePath;
    boolean grnStatus;
    boolean status;

    public ItemDto(Integer itemId, String mainCategoryId, String subCataegoryId, Integer barCode, String prefix, String codePrefix, Double discount, String itemName, String unitType, String priterType, Double costPrice, Double unitPrice, String imagePath, boolean grnStatus, boolean status) {
        this.itemId = itemId;
        this.mainCategoryId = mainCategoryId;
        this.subCataegoryId = subCataegoryId;
        this.barCode = barCode;
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
        this.status = status;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(String mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public String getSubCataegoryId() {
        return subCataegoryId;
    }

    public void setSubCataegoryId(String subCataegoryId) {
        this.subCataegoryId = subCataegoryId;
    }

    public Integer getBarCode() {
        return barCode;
    }

    public void setBarCode(Integer barCode) {
        this.barCode = barCode;
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

    public boolean isGrnStatus() {
        return grnStatus;
    }

    public void setGrnStatus(boolean grnStatus) {
        this.grnStatus = grnStatus;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
