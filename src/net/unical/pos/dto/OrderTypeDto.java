/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.dto;

import java.sql.Date;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class OrderTypeDto {
    private int id;
    private String type;
    private int status;
    private Date createdDate;
    private Date editedDate;

    public OrderTypeDto(int id, String type, int status, Date createdDate, Date editedDate) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.createdDate = createdDate;
        this.editedDate = editedDate;
    }

    public OrderTypeDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
    
}
