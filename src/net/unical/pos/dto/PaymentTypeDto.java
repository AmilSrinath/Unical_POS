/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.dto;

/**
 *
 * @author Sanjuka
 */
public class PaymentTypeDto {
    
    Integer paymentTypeId;
    String name;
    Integer status;
    Integer userd;
    Integer visible;

    public PaymentTypeDto() {
    }

    public PaymentTypeDto(Integer paymentTypeId, String name, Integer status, Integer userd, Integer visible) {
        this.paymentTypeId = paymentTypeId;
        this.name = name;
        this.status = status;
        this.userd = userd;
        this.visible = visible;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserd() {
        return userd;
    }

    public void setUserd(Integer userd) {
        this.userd = userd;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
    
    
}
