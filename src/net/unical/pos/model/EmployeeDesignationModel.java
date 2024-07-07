/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.model;

/**
 *
 * @author Sanjuka
 */
public class EmployeeDesignationModel {
    
    Integer designationId;
    String designationName;
    Integer status;
    Integer userId;
    Integer visible;

    public EmployeeDesignationModel() {
    }

    public EmployeeDesignationModel(Integer designationId, String designationName, Integer status, Integer userId, Integer visible) {
        this.designationId = designationId;
        this.designationName = designationName;
        this.status = status;
        this.userId = userId;
        this.visible = visible;
    }

    public Integer getDesignationId() {
        return designationId;
    }

    public void setDesignationId(Integer designationId) {
        this.designationId = designationId;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
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
