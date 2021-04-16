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
public class SubItemCategoryDto {
    
    Integer subItemCategoryId;
    Integer mainItemCategoryId;
    String mainCategoryName;
    String subCategoryName;
    String imagePath;
    boolean status;

    public SubItemCategoryDto(Integer subItemCategoryId, Integer mainItemCategoryId, String mainCategoryName, String subCategoryName, String imagePath, boolean status) {
        this.subItemCategoryId = subItemCategoryId;
        this.mainItemCategoryId = mainItemCategoryId;
        this.mainCategoryName = mainCategoryName;
        this.subCategoryName = subCategoryName;
        this.imagePath = imagePath;
        this.status = status;
    }

    public Integer getSubItemCategoryId() {
        return subItemCategoryId;
    }

    public void setSubItemCategoryId(Integer subItemCategoryId) {
        this.subItemCategoryId = subItemCategoryId;
    }

    public Integer getMainItemCategoryId() {
        return mainItemCategoryId;
    }

    public void setMainItemCategoryId(Integer mainItemCategoryId) {
        this.mainItemCategoryId = mainItemCategoryId;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
