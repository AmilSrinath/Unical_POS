/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hspc.pos.model;

/**
 *
 * @author Sanjuka
 */
public class MainItemCategoryModel implements SuperModel{
    Integer mainItemCategeryId;
    String categoryName;
    String imagePath;
    Integer status;

    public MainItemCategoryModel(Integer mainItemCategeryId, String categoryName, String imagePath, Integer status) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
}
