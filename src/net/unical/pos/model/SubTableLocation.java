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
public class SubTableLocation {

    Integer subTableLocationId;
    Integer mainTableLocationId;
    String mainName;
    String subName;
    String imagePath;
    Integer status;
    Integer userId;
    Integer visible;

    public SubTableLocation() {
    }

    public SubTableLocation(Integer subTableLocationId, Integer mainTableLocationId, String mainName, String subName, String imagePath, Integer status, Integer userId, Integer visible) {
        this.subTableLocationId = subTableLocationId;
        this.mainTableLocationId = mainTableLocationId;
        this.mainName = mainName;
        this.subName = subName;
        this.imagePath = imagePath;
        this.status = status;
        this.userId = userId;
        this.visible = visible;
    }

    public Integer getSubTableLocationId() {
        return subTableLocationId;
    }

    public void setSubTableLocationId(Integer subTableLocationId) {
        this.subTableLocationId = subTableLocationId;
    }

    public Integer getMainTableLocationId() {
        return mainTableLocationId;
    }

    public void setMainTableLocationId(Integer mainTableLocationId) {
        this.mainTableLocationId = mainTableLocationId;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
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
