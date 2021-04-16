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
public class MainItemCategoryDto {
    
    Integer mainItemCategeryId;
    String categoryName;
    String imagePath;
    Boolean status;

    public MainItemCategoryDto(Integer mainItemCategeryId, String categoryName, String imagePath, Boolean status) {
        this.mainItemCategeryId = mainItemCategeryId;
        this.categoryName = categoryName;
        this.imagePath = imagePath;
        this.status = status;
    }

    public Integer getMainItemCategeryId() {
        return mainItemCategeryId;
    }

    public void setMainItemCategeryId(Integer mainItemCategeryId) {
        this.mainItemCategeryId = mainItemCategeryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    
}
