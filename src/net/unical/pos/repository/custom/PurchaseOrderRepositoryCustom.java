/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.model.PurchaseOrderModel;

/**
 *
 * @author Sanjuka
 */
public interface PurchaseOrderRepositoryCustom {

    public Integer Save(PurchaseOrderModel purchaseOrderModel)throws Exception;

    public ArrayList<PurchaseOrderModel> getAllPurchaseOrders()throws Exception;
    
}
