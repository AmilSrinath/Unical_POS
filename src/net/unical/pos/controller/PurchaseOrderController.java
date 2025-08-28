/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import java.util.List;
import net.unical.pos.dto.MainPurchaseOrderDto;
import net.unical.pos.dto.PurchaseOrderDetailsDto;
import net.unical.pos.dto.PurchaseOrderDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.PurchaseOrderDetailsServiceCustom;
import net.unical.pos.service.custom.PurchaseOrderServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class PurchaseOrderController {

    private PurchaseOrderServiceCustom purchaseOrderServiceCustom;
    private PurchaseOrderDetailsServiceCustom purchaseOrderDetailsServiceCustom;
    
    public PurchaseOrderController() {
        purchaseOrderServiceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.PURCHASE_ORDER);
        purchaseOrderDetailsServiceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.PURCHASE_ORDER_DETAILS);
    }
    
    public boolean savePurchaseOrder(MainPurchaseOrderDto mainPurchaseOrderDto) throws Exception{
        return purchaseOrderServiceCustom.savePurchaseOrder(mainPurchaseOrderDto);
    }

    public ArrayList<PurchaseOrderDto> getAllPurchaseOrder() throws Exception {
        return purchaseOrderServiceCustom.getAllPurchaseOrders();
    }

    public ArrayList<PurchaseOrderDetailsDto> getAllPurchaseOrderDetails(Integer poCode)throws Exception{
        return purchaseOrderDetailsServiceCustom.getAllPurchaseOrderDetails(poCode);
    }

    public List<PurchaseOrderDto> getItemList(String itemCode) {
        return purchaseOrderServiceCustom.getItemList(itemCode);
    }
}
