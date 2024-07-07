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
public class PosMainUserRole {

    private Integer roleId;
    private String role;
    private Integer status;
    private Integer userId;
    private Integer visible;

    public PosMainUserRole() {
    }

    public PosMainUserRole(Integer roleId, String role, Integer status, Integer userId, Integer visible) {
        this.roleId = roleId;
        this.role = role;
        this.status = status;
        this.userId = userId;
        this.visible = visible;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
