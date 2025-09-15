/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.model;

import java.util.Date;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class StockLocation {
    private String stockCode;
    private String codePrefix;
    private String stockName;
    private Integer contact;
    private String address;
    private Integer status;
    private Integer userId;
    private Date createdDate;

    public StockLocation() {
    }

    public StockLocation(String stockCode, String codePrefix, String stockName, Integer contact, String address, Integer status, Integer userId, Date createdDate) {
        this.stockCode = stockCode;
        this.codePrefix = codePrefix;
        this.stockName = stockName;
        this.contact = contact;
        this.address = address;
        this.status = status;
        this.userId = userId;
        this.createdDate = createdDate;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getCodePrefix() {
        return codePrefix;
    }

    public void setCodePrefix(String codePrefix) {
        this.codePrefix = codePrefix;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    
}
