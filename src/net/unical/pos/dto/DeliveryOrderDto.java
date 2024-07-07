/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.dto;

import java.util.ArrayList;

/**
 *
 * @author Sanjuka
 */
public class DeliveryOrderDto {
    
    private Integer orderId;
    private String orderCode;
    private Integer customerId;
    private String customerName;
    private String address;
    private Double cod;
    private String phoneOne;
    private String phoneTwo;
    private Double subTotalPrice;
    private Double deliveryFee;
    private String weight;
    private Integer status;
    private Integer isReturn;
    private Integer freeShip;
    private Double grandTotalPrice;
    private String remark;
    private Integer PaymentTypeId;
    private Integer isPrint;
    
    private ArrayList<OrderDetailsDto> orderDetailsDtos;
    
    public DeliveryOrderDto() {
    }

    public DeliveryOrderDto(Integer orderId, String orderCode, String customerName, String address, Double cod, String phoneOne, String phoneTwo, Double subTotalPrice, Double deliveryFee, String weight, Integer status, Double grandTotalPrice, String remark, Integer PaymentTypeId, ArrayList<OrderDetailsDto> orderDetailsDtos) {
        this.orderId = orderId;
        this.orderCode = orderCode;
        this.customerName = customerName;
        this.address = address;
        this.cod = cod;
        this.phoneOne = phoneOne;
        this.phoneTwo = phoneTwo;
        this.subTotalPrice = subTotalPrice;
        this.deliveryFee = deliveryFee;
        this.weight = weight;
        this.status = status;
        this.grandTotalPrice = grandTotalPrice;
        this.remark = remark;
        this.PaymentTypeId = PaymentTypeId;
        this.orderDetailsDtos = orderDetailsDtos;
    }

    public DeliveryOrderDto(Integer orderId, String orderCode, Integer customerId, String customerName, String address, Double cod, String phoneOne, String phoneTwo, Double subTotalPrice, Double deliveryFee, String weight, Integer status,Integer freeShip, Double grandTotalPrice, String remark, Integer PaymentTypeId, ArrayList<OrderDetailsDto> orderDetailsDtos) {
        this.orderId = orderId;
        this.orderCode = orderCode;
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.cod = cod;
        this.phoneOne = phoneOne;
        this.phoneTwo = phoneTwo;
        this.subTotalPrice = subTotalPrice;
        this.deliveryFee = deliveryFee;
        this.weight = weight;
        this.status = status;
        this.status = freeShip;
        this.grandTotalPrice = grandTotalPrice;
        this.remark = remark;
        this.PaymentTypeId = PaymentTypeId;
        this.orderDetailsDtos = orderDetailsDtos;
    }


    public DeliveryOrderDto(Integer orderId, String orderCode, String customerName, String address, Double cod, String phoneOne, String phoneTwo, Double subTotalPrice, Double deliveryFee, Integer status, Double grandTotalPrice, String remark, Integer PaymentTypeId, Integer isPrint) {
        this.orderId = orderId;
        this.orderCode = orderCode;
        this.customerName = customerName;
        this.address = address;
        this.cod = cod;
        this.phoneOne = phoneOne;
        this.phoneTwo = phoneTwo;
        this.subTotalPrice = subTotalPrice;
        this.deliveryFee = deliveryFee;
        this.status = status;
        this.grandTotalPrice = grandTotalPrice;
        this.remark = remark;
        this.PaymentTypeId = PaymentTypeId;
        this.isPrint = isPrint;
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

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public Double getGrandTotalPrice() {
        return grandTotalPrice;
    }

    public void setGrandTotalPrice(Double grandTotalPrice) {
        this.grandTotalPrice = grandTotalPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPaymentTypeId() {
        return PaymentTypeId;
    }

    public void setPaymentTypeId(Integer PaymentTypeId) {
        this.PaymentTypeId = PaymentTypeId;
    }

    public Integer getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(Integer isPrint) {
        this.isPrint = isPrint;
    }

    public ArrayList<OrderDetailsDto> getOrderDetailsDtos() {
        return orderDetailsDtos;
    }

    public void setOrderDetailsDtos(ArrayList<OrderDetailsDto> orderDetailsDtos) {
        this.orderDetailsDtos = orderDetailsDtos;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getFreeShip() {
        return freeShip;
    }

    public void setFreeShip(Integer freeShip) {
        this.freeShip = freeShip;
    }
    
}
