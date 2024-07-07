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
public class EmployeeTitleDto {
    
    Integer titleId;
    String titleName;
    Integer status;
    Integer userId;
    Integer visible;

    public EmployeeTitleDto() {
    }

    public EmployeeTitleDto(Integer titleId, String titleName, Integer status, Integer userId, Integer visible) {
        this.titleId = titleId;
        this.titleName = titleName;
        this.status = status;
        this.userId = userId;
        this.visible = visible;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
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
