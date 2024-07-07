/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.dto;

/**
 *
 * @author HP
 */
public class UserDto {
    
    private Integer userId;
    private Integer employeeId;
    private Integer roleId;
    private String userName;
    private String password;
    private Integer status;
    private Integer visible;
    private String token;

    public UserDto() {
    }

    public UserDto(Integer userId, Integer employeeId, Integer roleId, String userName, String password, Integer status, Integer visible, String token) {
        this.userId = userId;
        this.employeeId = employeeId;
        this.roleId = roleId;
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.visible = visible;
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
}
