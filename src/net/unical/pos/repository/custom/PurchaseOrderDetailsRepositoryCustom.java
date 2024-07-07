/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.model.PurchaseOrderDetailsModel;

/**
 *
 * @author Sanjuka
 */
public interface PurchaseOrderDetailsRepositoryCustom {

    public boolean save(PurchaseOrderDetailsModel purchaseOrderDetailsModel)throws Exception;

    public ArrayList<PurchaseOrderDetailsModel> getAllPurchaseOrderDetails(Integer poCode)throws Exception;
    
}
