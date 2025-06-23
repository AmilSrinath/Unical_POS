/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import net.unical.pos.dto.OrderDetailsDto;

/**
 *
 * @author Sanjuka
 */
public class DeliveryOrder {

    private Integer orderId;
    private String orderCode;
    private Integer customerId;
    private String customerName;
    private String address;
    private String payment_type;
    private Double cod;
    private String phoneOne;
    private String phoneTwo;
    private Double subTotalPrice;
    private Double deliveryFee;
    private String weight;
    private Integer status;
    private Integer statusType;
    private Integer isReturn;
    private Integer freeShip;
    private Double grandTotalPrice;
    private String date;
    private String remark;
    private Integer paymentTypeId;
    private Integer isPrint;
    private String customerNumber;
    private Double paidAmount;
    private Timestamp createDate;
    private Timestamp editedDate;
    private Integer userID;

    private Integer paymentId;
    private Double codPayment;
    private Double totalAmount;
    private Integer paymentStatus;
    private Integer isExchange;

    private ArrayList<OrderDetailsDto> orderDetailsDtos;

    public Integer getIsExchange() {
        return isExchange;
    }

    public void setIsExchange(Integer isExchange) {
        this.isExchange = isExchange;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }
    
    public void setPayment_Type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getPayment_Type() {
        return payment_type;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public Double getCod() {
        return cod;
    }

    public void setCod(Double cod) {
        this.cod = cod;
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

    public Double getSubTotalPrice() {
        return subTotalPrice;
    }

    public void setSubTotalPrice(Double subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatusType() {
        return statusType;
    }

    public void setStatusType(Integer statusType) {
        this.statusType = statusType;
    }

    public Integer getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Integer isReturn) {
        this.isReturn = isReturn;
    }

    public Integer getFreeShip() {
        return freeShip;
    }

    public void setFreeShip(Integer freeShip) {
        this.freeShip = freeShip;
    }

    public Double getGrandTotalPrice() {
        return grandTotalPrice;
    }

    public void setGrandTotalPrice(Double grandTotalPrice) {
        this.grandTotalPrice = grandTotalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Integer getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(Integer isPrint) {
        this.isPrint = isPrint;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
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

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Double getCodPayment() {
        return codPayment;
    }

    public void setCodPayment(Double codPayment) {
        this.codPayment = codPayment;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public ArrayList<OrderDetailsDto> getOrderDetailsDtos() {
        return orderDetailsDtos;
    }

    public void setOrderDetailsDtos(ArrayList<OrderDetailsDto> orderDetailsDtos) {
        this.orderDetailsDtos = orderDetailsDtos;
    }

    
}