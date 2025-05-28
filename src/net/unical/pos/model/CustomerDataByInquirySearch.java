/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.model;

/**
 *
 * @author Amil Srinath
 */
public class CustomerDataByInquirySearch {
    private String customerName;
    private String customerPhone1;
    private String customerPhone2;
    private int customerId;

    public CustomerDataByInquirySearch(String customerName, String customerPhone1, String customerPhone2, int customerId) {
        this.customerName = customerName;
        this.customerPhone1 = customerPhone1;
        this.customerPhone2 = customerPhone2;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    
    
}
