/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.PurchaseOrderDetailsDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.PurchaseOrderDetailsModel;
import net.unical.pos.repository.custom.PurchaseOrderDetailsRepositoryCustom;
import net.unical.pos.service.custom.PurchaseOrderDetailsServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class PurchaseOrderDetailsServiceImpl implements PurchaseOrderDetailsServiceCustom{

    private PurchaseOrderDetailsRepositoryCustom purchaseOrderDetailsRepositoryCustom;
    
    public PurchaseOrderDetailsServiceImpl() {
        purchaseOrderDetailsRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.PURCHASE_ORDER_DETAILS);
    }

    @Override
    public ArrayList<PurchaseOrderDetailsDto> getAllPurchaseOrderDetails(Integer poCode) throws Exception {
        ArrayList<PurchaseOrderDetailsDto> purchaseOrderDetailsDtos = new ArrayList<>();
        ArrayList<PurchaseOrderDetailsModel> purchaseOrderDetailsModels = new ArrayList<>();
        purchaseOrderDetailsModels = purchaseOrderDetailsRepositoryCustom.getAllPurchaseOrderDetails(poCode);

        for (PurchaseOrderDetailsModel orderDetailsModel : purchaseOrderDetailsModels) {
            purchaseOrderDetailsDtos.add(new PurchaseOrderDetailsDto(orderDetailsModel.getPoDetailsId(),
                    orderDetailsModel.getPoId(),
                    orderDetailsModel.getItemId(), 
                    orderDetailsModel.getItemName(), 
                    orderDetailsModel.getQty(), 
                    orderDetailsModel.getExpectedPrice(),
                    orderDetailsModel.getLastGrnPrice(),
                    orderDetailsModel.getTotalPrice(),
                    orderDetailsModel.getUserId()
            ));
        }
        return purchaseOrderDetailsDtos;
    }
    
}
