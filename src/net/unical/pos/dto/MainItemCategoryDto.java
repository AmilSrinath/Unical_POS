/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.dto;

import java.util.Date;

/**
 *
 * @author Sanjuka
 */
public class MainItemCategoryDto {
    
    private Integer mainItemCategeryId;
    private String categoryName;
    private String imagePath;
    private Integer status;
    private Integer userId;
    private Integer visible;
    private Date createdDate;
    private Integer editedBy;
    private Date editedDate;

    public MainItemCategoryDto() {
    }

    public MainItemCategoryDto(Integer mainItemCategeryId, String categoryName, String imagePath, Integer status, Integer userId, Integer visible, Date createdDate, Integer editedBy, Date editedDate) {
        this.mainItemCategeryId = mainItemCategeryId;
        this.categoryName = categoryName;
        this.imagePath = imagePath;
        this.status = status;
        this.userId = userId;
        this.visible = visible;
        this.createdDate = createdDate;
        this.editedBy = editedBy;
        this.editedDate = editedDate;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(Integer editedBy) {
        this.editedBy = editedBy;
    }

    public Date getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
    }
    
}
