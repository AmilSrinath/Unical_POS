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
public class CourierBranchModel {
    private int branchId;
    private String branchName;
    private String branchContact;
    private Timestamp createDate;
    private Timestamp editedDate;
    private int status;
    private int userId;
    private String companyName;

    public CourierBranchModel() {}

    public CourierBranchModel(int branchId, String branchName, String branchContact, Timestamp createDate, Timestamp editedDate, int status, int userId, String companyName) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.branchContact = branchContact;
        this.createDate = createDate;
        this.editedDate = editedDate;
        this.status = status;
        this.userId = userId;
        this.companyName = companyName;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchContact() {
        return branchContact;
    }

    public void setBranchContact(String branchContact) {
        this.branchContact = branchContact;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    
}
