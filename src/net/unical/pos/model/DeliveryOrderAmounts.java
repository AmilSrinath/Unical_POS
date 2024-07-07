/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.model;

/**
 *
 * @author user
 */
public class DeliveryOrderAmounts {
    
    private Double totalAmount;
    private Double totalDeliverds;
    private Double totalCod;
    private Double totalDeliveryCharge;
    private Double totalReturns;

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalDeliveryCharge() {
        return totalDeliveryCharge;
    }

    public void setTotalDeliveryCharge(Double totalDeliveryCharge) {
        this.totalDeliveryCharge = totalDeliveryCharge;
    }

    public Double getTotalDeliverds() {
        return totalDeliverds;
    }

    public void setTotalDeliverds(Double totalDeliverds) {
        this.totalDeliverds = totalDeliverds;
    }

    public Double getTotalReturns() {
        return totalReturns;
    }

    public void setTotalReturns(Double totalReturns) {
        this.totalReturns = totalReturns;
    }

    public Double getTotalCod() {
        return totalCod;
    }

    public void setTotalCod(Double totalCod) {
        this.totalCod = totalCod;
    }
    
    
}
