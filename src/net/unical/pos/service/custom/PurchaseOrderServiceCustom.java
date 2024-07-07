/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.MainPurchaseOrderDto;
import net.unical.pos.dto.PurchaseOrderDto;

/**
 *
 * @author Sanjuka
 */
public interface PurchaseOrderServiceCustom {

    public boolean savePurchaseOrder(MainPurchaseOrderDto mainPurchaseOrderDto)throws Exception;

    public ArrayList<PurchaseOrderDto> getAllPurchaseOrders()throws Exception;
    
}
