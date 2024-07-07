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
public class PosMainItemCategory {
    private Integer itemCategoryId;
    private Integer itemCategoryName;
    private Integer status;

    /**
     * @return the itemCategoryId
     */
    public Integer getItemCategoryId() {
        return itemCategoryId;
    }

    /**
     * @param itemCategoryId the itemCategoryId to set
     */
    public void setItemCategoryId(Integer itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    /**
     * @return the itemCategoryName
     */
    public Integer getItemCategoryName() {
        return itemCategoryName;
    }

    /**
     * @param itemCategoryName the itemCategoryName to set
     */
    public void setItemCategoryName(Integer itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    /**
     * @return the Status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(Integer Status) {
        this.status = Status;
    }
}
