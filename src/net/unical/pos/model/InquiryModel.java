/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.model;

import java.sql.Date;

/**
 *
 * @author Amil Srinath
 */
public class InquiryModel {
    private String wayBill;
    private String customerId;
    private String customerName;
    private String customerPhone1;
    private String customerPhone2;
    private String branch;
    private String branchContact;
    private String reason;
    private String remark;
    private int status;
    private int userId;
    private Date createDate;
    private Date editedDate;
    
    public InquiryModel(){}

    public InquiryModel(String wayBill, String customerId, String customerName, String customerPhone1, String customerPhone2, String branch, String branchContact, String reason, String remark, int status, int userId, Date createDate, Date editedDate) {
        this.wayBill = wayBill;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone1 = customerPhone1;
        this.customerPhone2 = customerPhone2;
        this.branch = branch;
        this.branchContact = branchContact;
        this.reason = reason;
        this.remark = remark;
        this.status = status;
        this.userId = userId;
        this.createDate = createDate;
        this.editedDate = editedDate;
    }

    public String getWayBill() {
        return wayBill;
    }

    public void setWayBill(String wayBill) {
        this.wayBill = wayBill;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone1() {
        return customerPhone1;
    }

    public void setCustomerPhone1(String customerPhone1) {
        this.customerPhone1 = customerPhone1;
    }

    public String getCustomerPhone2() {
        return customerPhone2;
    }

    public void setCustomerPhone2(String customerPhone2) {
        this.customerPhone2 = customerPhone2;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranchContact() {
        return branchContact;
    }

    public void setBranchContact(String branchContact) {
        this.branchContact = branchContact;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
    }
    
    
}
