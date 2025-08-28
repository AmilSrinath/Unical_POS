/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.dto.MainPurchaseOrderDto;
import net.unical.pos.dto.PurchaseOrderDetailsDto;
import net.unical.pos.dto.PurchaseOrderDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.PurchaseOrderDetailsModel;
import net.unical.pos.model.PurchaseOrderModel;
import net.unical.pos.repository.custom.PurchaseOrderDetailsRepositoryCustom;
import net.unical.pos.repository.custom.PurchaseOrderRepositoryCustom;
import net.unical.pos.service.custom.PurchaseOrderServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class PurchaseOrderServiceImpl implements PurchaseOrderServiceCustom {

    private PurchaseOrderRepositoryCustom purchaseOrderRepositoryCustom;
    private PurchaseOrderDetailsRepositoryCustom purchaseOrderDetailsRepositoryCustom;

    public PurchaseOrderServiceImpl() {
        purchaseOrderRepositoryCustom = RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.PURCHASE_ORDER);
        purchaseOrderDetailsRepositoryCustom = RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.PURCHASE_ORDER_DETAILS);
    }

    @Override
    public boolean savePurchaseOrder(MainPurchaseOrderDto mainPurchaseOrderDto) {
        try {
            PurchaseOrderModel purchaseOrderModel = new PurchaseOrderModel();
            PurchaseOrderDetailsModel purchaseOrderDetailsModel = new PurchaseOrderDetailsModel();

            purchaseOrderModel.setPoId(0);
            purchaseOrderModel.setPoPrefix(mainPurchaseOrderDto.getPurchaseOrderDto().getPoPrefix());
            purchaseOrderModel.setPoCode(mainPurchaseOrderDto.getPurchaseOrderDto().getPoCode());
            purchaseOrderModel.setPoCodePrefix(mainPurchaseOrderDto.getPurchaseOrderDto().getPoCodePrefix());
            purchaseOrderModel.setSupplierId(mainPurchaseOrderDto.getPurchaseOrderDto().getSupplierId());
            purchaseOrderModel.setSupplierName(mainPurchaseOrderDto.getPurchaseOrderDto().getSupplierName());
            purchaseOrderModel.setPoDate(mainPurchaseOrderDto.getPurchaseOrderDto().getPoDate());
            purchaseOrderModel.setExpectedDate(mainPurchaseOrderDto.getPurchaseOrderDto().getExpectedDate());
            purchaseOrderModel.setTotalOrderPrice(mainPurchaseOrderDto.getPurchaseOrderDto().getTotalOrderPrice());
            purchaseOrderModel.setPaymentType(mainPurchaseOrderDto.getPurchaseOrderDto().getPaymentType());
            purchaseOrderModel.setStatus(mainPurchaseOrderDto.getPurchaseOrderDto().getStatus());
            purchaseOrderModel.setUserId(mainPurchaseOrderDto.getPurchaseOrderDto().getUserId());
            purchaseOrderModel.setVisible(mainPurchaseOrderDto.getPurchaseOrderDto().getVisible());
            Integer poId = purchaseOrderRepositoryCustom.Save(purchaseOrderModel);
            System.out.println("PO :" + poId);
            if (poId != null) {
                ArrayList<PurchaseOrderDetailsDto> poDetailslist = mainPurchaseOrderDto.getPurchaseOrderDetailsDtos();

                for (PurchaseOrderDetailsDto dto : poDetailslist) {
                    purchaseOrderDetailsModel.setPoDetailsId(0);
                    purchaseOrderDetailsModel.setPoId(poId);
                    purchaseOrderDetailsModel.setItemId(dto.getItemId());
                    purchaseOrderDetailsModel.setItemName(dto.getItemName());
                    purchaseOrderDetailsModel.setQty(dto.getQty());
                    purchaseOrderDetailsModel.setExpectedPrice(dto.getExpectedPrice());
                    purchaseOrderDetailsModel.setLastGrnPrice(dto.getLastGrnPrice());
                    purchaseOrderDetailsModel.setTotalPrice(dto.getTotalItemPrice());
                    purchaseOrderDetailsModel.setUserId(dto.getUserId());
                    boolean result = purchaseOrderDetailsRepositoryCustom.save(purchaseOrderDetailsModel);

                }
            }

        } catch (Exception ex) {
            Logger.getLogger(PurchaseOrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<PurchaseOrderDto> getAllPurchaseOrders() throws Exception {
        ArrayList<PurchaseOrderDto> purchaseOrderDtos = new ArrayList<>();
        ArrayList<PurchaseOrderModel> purchaseOrderModels = new ArrayList<>();
        purchaseOrderModels = purchaseOrderRepositoryCustom.getAllPurchaseOrders();

        for (PurchaseOrderModel purchaseOrderModel : purchaseOrderModels) {
            purchaseOrderDtos.add(new PurchaseOrderDto(purchaseOrderModel.getPoId(),
                    purchaseOrderModel.getPoPrefix(),
                    purchaseOrderModel.getPoCode(),
                    purchaseOrderModel.getPoCodePrefix(),
                    purchaseOrderModel.getSupplierId(),
                    purchaseOrderModel.getSupplierName(),
                    purchaseOrderModel.getPoDate(),
                    purchaseOrderModel.getExpectedDate(),
                    purchaseOrderModel.getTotalOrderPrice(),
                    purchaseOrderModel.getPaymentType(),
                    purchaseOrderModel.getStatus(),
                    purchaseOrderModel.getUserId(),
                    purchaseOrderModel.getVisible()));
        }
        return purchaseOrderDtos;
    }

    @Override
    public List<PurchaseOrderDto> getItemList(String itemCode) {
        ArrayList<PurchaseOrderDto> purchaseOrderDtos = new ArrayList<>();
        ArrayList<PurchaseOrderModel> purchaseOrderModels = purchaseOrderRepositoryCustom.getItemList(itemCode);
        
        for (PurchaseOrderModel purchaseOrderModel : purchaseOrderModels) {
            purchaseOrderDtos.add(new PurchaseOrderDto(purchaseOrderModel.getPoId(),
                    purchaseOrderModel.getPoPrefix(),
                    purchaseOrderModel.getPoCode(),
                    purchaseOrderModel.getPoCodePrefix(),
                    purchaseOrderModel.getSupplierId(),
                    purchaseOrderModel.getSupplierName(),
                    purchaseOrderModel.getPoDate(),
                    purchaseOrderModel.getExpectedDate(),
                    purchaseOrderModel.getTotalOrderPrice(),
                    purchaseOrderModel.getPaymentType(),
                    purchaseOrderModel.getStatus(),
                    purchaseOrderModel.getUserId(),
                    purchaseOrderModel.getVisible()));
        }
        return purchaseOrderDtos;
        
    }

}
