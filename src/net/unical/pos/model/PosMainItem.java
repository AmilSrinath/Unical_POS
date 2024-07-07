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
public class PosMainItem {

    private Integer itemId;
    private Integer barCode;
    private Integer mainItemCategoryId;
    private Integer subItemCategoryId;
    private String prefix;
    private String codePrefix;
    private Double discount;
    private String itemName;
    private String unitType;
    private String priterType;
    private Double costPrice;
    private Double unitPrice;
    private String imagePath;
    private Integer grnStatus;
    private Integer sellingItem;
    private Integer status;
    private Integer userId;
    private Integer visible;
    private Double weight;

    public PosMainItem() {
    }

    public PosMainItem(Integer itemId, Integer barCode, Integer mainItemCategoryId, Integer subItemCategoryId, String prefix, String codePrefix, Double discount, String itemName, String unitType, String priterType, Double costPrice, Double unitPrice, String imagePath, Integer grnStatus, Integer sellingItem, Integer status, Integer userId, Integer visible, Double weight) {
        this.itemId = itemId;
        this.barCode = barCode;
        this.mainItemCategoryId = mainItemCategoryId;
        this.subItemCategoryId = subItemCategoryId;
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
        this.visible = visible;
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

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
    
    public Double getWeight(){
        return weight;
    }
    
    public void setWeight(Double weight){
        this.weight = weight;
    }
    
}
