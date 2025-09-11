/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.dto;

import java.util.Date;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class ItemRegistryDto {
    private Integer registryId;
    private Integer mainCategoryId;
    private Integer subCategoryId;
    private Integer status;
    private Date createdDate;
    private Date editedDate;
    private Integer visibility;
    private Integer userId;

    public ItemRegistryDto() {
    }

    public ItemRegistryDto(Integer registryId, Integer mainCategoryId, Integer subCategoryId, Integer status, Date createdDate, Date editedDate, Integer visibility, Integer userId) {
        this.registryId = registryId;
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.status = status;
        this.createdDate = createdDate;
        this.editedDate = editedDate;
        this.visibility = visibility;
        this.userId = userId;
    }

    public Integer getRegistryId() {
        return registryId;
    }

    public void setRegistryId(Integer registryId) {
        this.registryId = registryId;
    }

    public Integer getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(Integer mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    
}
