/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.model;

/**
 *
 * @author Amil Srinath
 */
public class WrapperOrder {
    private String orderCode;
    private String deliveryId;
    private String customerName;
    private String address;
    private double codAmount;
    private String phoneOne;
    private String phoneTwo;
    private double weight;

    public WrapperOrder(String orderCode, String deliveryId, String customerName, String address, double codAmount, String phoneOne, String phoneTwo, double weight) {
        this.orderCode = orderCode;
        this.deliveryId = deliveryId;
        this.customerName = customerName;
        this.address = address;
        this.codAmount = codAmount;
        this.phoneOne = phoneOne;
        this.phoneTwo = phoneTwo;
        this.weight = weight;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getCodAmount() {
        return codAmount;
    }

    public void setCodAmount(double codAmount) {
        this.codAmount = codAmount;
    }

    public String getPhoneOne() {
        return phoneOne;
    }

    public void setPhoneOne(String phoneOne) {
        this.phoneOne = phoneOne;
    }

    public String getPhoneTwo() {
        return phoneTwo;
    }

    public void setPhoneTwo(String phoneTwo) {
        this.phoneTwo = phoneTwo;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    

}
