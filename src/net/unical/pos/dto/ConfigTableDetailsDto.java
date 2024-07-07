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
public class ConfigTableDetailsDto {
 
    Integer configDetailTablesId;
    Integer configTablesId;
    Integer mainTableLocationId;
    Integer subTableLocationId;
    String tableName;
    Integer width;
    Integer height;
    String imagePath;
    Integer status;
    Integer userId;
    Integer visible;

    public ConfigTableDetailsDto(Integer configDetailTablesId, Integer configTablesId, Integer mainTableLocationId, Integer subTableLocationId, String tableName, Integer width, Integer height, String imagePath, Integer status, Integer userId, Integer visible) {
        this.configDetailTablesId = configDetailTablesId;
        this.configTablesId = configTablesId;
        this.mainTableLocationId = mainTableLocationId;
        this.subTableLocationId = subTableLocationId;
        this.tableName = tableName;
        this.width = width;
        this.height = height;
        this.imagePath = imagePath;
        this.status = status;
        this.userId = userId;
        this.visible = visible;
    }

    public Integer getConfigDetailTablesId() {
        return configDetailTablesId;
    }

    public void setConfigDetailTablesId(Integer configDetailTablesId) {
        this.configDetailTablesId = configDetailTablesId;
    }

    public Integer getConfigTablesId() {
        return configTablesId;
    }

    public void setConfigTablesId(Integer configTablesId) {
        this.configTablesId = configTablesId;
    }

    public Integer getMainTableLocationId() {
        return mainTableLocationId;
    }

    public void setMainTableLocationId(Integer mainTableLocationId) {
        this.mainTableLocationId = mainTableLocationId;
    }

    public Integer getSubTableLocationId() {
        return subTableLocationId;
    }

    public void setSubTableLocationId(Integer subTableLocationId) {
        this.subTableLocationId = subTableLocationId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
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
    
    
}
