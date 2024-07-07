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
public class MainPurchaseOrderDto {
    
    PurchaseOrderDto purchaseOrderDto;
    ArrayList<PurchaseOrderDetailsDto> purchaseOrderDetailsDtos;

    
    public MainPurchaseOrderDto(PurchaseOrderDto purchaseOrderDto, ArrayList<PurchaseOrderDetailsDto> purchaseOrderDetailsDtos) {
        this.purchaseOrderDto = purchaseOrderDto;
        this.purchaseOrderDetailsDtos = purchaseOrderDetailsDtos;
    }

    public PurchaseOrderDto getPurchaseOrderDto() {
        return purchaseOrderDto;
    }

    public void setPurchaseOrderDto(PurchaseOrderDto purchaseOrderDto) {
        this.purchaseOrderDto = purchaseOrderDto;
    }

    public ArrayList<PurchaseOrderDetailsDto> getPurchaseOrderDetailsDtos() {
        return purchaseOrderDetailsDtos;
    }

    public void setPurchaseOrderDetailsDtos(ArrayList<PurchaseOrderDetailsDto> purchaseOrderDetailsDtos) {
        this.purchaseOrderDetailsDtos = purchaseOrderDetailsDtos;
    }
    
    
}
