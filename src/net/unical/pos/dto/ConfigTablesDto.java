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
public class ConfigTablesDto {
    
    Integer configTablesId;
    Integer mainTableLocationId;
    Integer subTableLocationId;
    String mainLocationName;
    String subLocationName;
    String tableCodePrefix;
    Integer numOfTables;
    String imagePath;
    Integer status;
    Integer userId;
    Integer visible;

    public ConfigTablesDto(Integer configTablesId, Integer mainTableLocationId, Integer subTableLocationId, String mainLocationName, String subLocationName, String tableCodePrefix, Integer numOfTables, String imagePath, Integer status, Integer userId, Integer visible) {
        this.configTablesId = configTablesId;
        this.mainTableLocationId = mainTableLocationId;
        this.subTableLocationId = subTableLocationId;
        this.mainLocationName = mainLocationName;
        this.subLocationName = subLocationName;
        this.tableCodePrefix = tableCodePrefix;
        this.numOfTables = numOfTables;
        this.imagePath = imagePath;
        this.status = status;
        this.userId = userId;
        this.visible = visible;
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

    public String getMainLocationName() {
        return mainLocationName;
    }

    public void setMainLocationName(String mainLocationName) {
        this.mainLocationName = mainLocationName;
    }

    public String getSubLocationName() {
        return subLocationName;
    }

    public void setSubLocationName(String subLocationName) {
        this.subLocationName = subLocationName;
    }

    public String getTableCodePrefix() {
        return tableCodePrefix;
    }

    public void setTableCodePrefix(String tableCodePrefix) {
        this.tableCodePrefix = tableCodePrefix;
    }

    public Integer getNumOfTables() {
        return numOfTables;
    }

    public void setNumOfTables(Integer numOfTables) {
        this.numOfTables = numOfTables;
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
