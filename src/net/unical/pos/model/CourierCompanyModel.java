/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.model;

import java.sql.Timestamp;

/**
 *
 * @author Amil Srinath
 */
public class CourierCompanyModel {
    private int companyId;
    private String companyName;
    private String companyContact;
    private Timestamp createDate;
    private Timestamp editedDate;
    private int status;
    private int userId;

    public CourierCompanyModel() {}

    public CourierCompanyModel(int companyId, String companyName, String companyContact, Timestamp createDate, Timestamp editedDate, int status, int userId) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyContact = companyContact;
        this.createDate = createDate;
        this.editedDate = editedDate;
        this.status = status;
        this.userId = userId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Timestamp editedDate) {
        this.editedDate = editedDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
}
