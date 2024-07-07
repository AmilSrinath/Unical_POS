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
    public class MainTableLocation {
     Integer mainTableLocationId;
    String locationName;
    String imagePath;
    Integer status;
    Integer userId;
    Integer visible;

    public MainTableLocation() {
    }

    
    public MainTableLocation(Integer mainTableLocationId, String locationName, String imagePath, Integer status, Integer userId, Integer visible) {
        this.mainTableLocationId = mainTableLocationId;
        this.locationName = locationName;
        this.imagePath = imagePath;
        this.status = status;
        this.userId = userId;
        this.visible = visible;
    }

    

    public Integer getMainTableLocationId() {
        return mainTableLocationId;
    }

    public void setMainTableLocationId(Integer mainTableLocationId) {
        this.mainTableLocationId = mainTableLocationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
   
}
