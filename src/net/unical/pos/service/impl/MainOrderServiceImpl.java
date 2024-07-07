/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.dto.MainOrderDto;
import net.unical.pos.model.PosMainOrder;
import net.unical.pos.model.PosMainOrderDetails;
import net.unical.pos.repository.custom.MainOrderDetailRepositoryCustom;
import net.unical.pos.repository.custom.MainOrderRepositoryCustom;
import net.unical.pos.repository.custom.PosMainPaymentRepositoryCustom;
import net.unical.pos.repository.custom.StockRepositoryCustom;
import net.unical.pos.service.custom.MainItemServiceCustom;
import net.unical.pos.service.custom.MainOrderServiceCustom;

/**
 *
 * @author HP
 */
public class MainOrderServiceImpl implements MainOrderServiceCustom {

    private MainOrderRepositoryCustom mainOrderRepositoryCustom;
    private StockRepositoryCustom stockRepositoryCustom;
    private MainOrderDetailRepositoryCustom orderDetailRepositoryCustom;
    private PosMainPaymentRepositoryCustom paymentRepositoryCustom;

    private MainItemServiceCustom mainItemServiceCustom;

    public MainOrderDto mainOrderSave(MainOrderDto mainOrderDto) {
//        Integer savedId = 0;
//        if (!mainOrderDto.getOrderDetailsDtos().isEmpty()) {
//            PosMainOrder mainOrder = new PosMainOrder(0,
//                    mainOrderDto.getBillNo(),
//                    mainOrderDto.getNetAmount(),
//                    mainOrderDto.getDiscountPrice(),
//                    mainOrderDto.getDiscountPercentage(),
//                    mainOrderDto.getTotalPrice(),
//                    mainOrderDto.getPaymentType(),
//                    mainOrderDto.getStatus(),
//                    mainOrderDto.getUserId(),
//                    mainOrderDto.getVisible());
//            savedId = mainOrderRepositoryCustom.mainOrderSave(mainOrder);
//            mainOrderDto.setOrderId(savedId);
//        }
//        if (savedId > 0) {
//            for (OrderDetailsDto detailsDto : mainOrderDto.getOrderDetailsDtos()) {
//                Integer updatedQty = stockRepositoryCustom.updateQty(detailsDto.getItemId(),detailsDto.getQty());             
//                
//            }
//
//        }
//        return mainOrderDto;
return null;
    }

    public MainOrderDto mainOrderConfirmAndSave(MainOrderDto mainOrderDto) throws Exception {
//        Integer savedId = 0;
//        Connection connection = DBConnection.getInstance().getConnection();
//        connection.setAutoCommit(false);
//
//        try {
//            PosMainOrder posMainOrder = mainOrderRepositoryCustom.findOne(mainOrderDto.getOrderId());
//            ArrayList<PosMainOrderDetails> mainOrderDetailses = orderDetailRepositoryCustom.getOrderDetails(mainOrderDto.getOrderId());
//
//            //get paiment detils
//            //PosMainPaymentTypes posMainPaymentTypes = paymentRepositoryCustom.getPaimentType(mainOrderDto.getPaymentType());
//            if (posMainOrder.getPaymentTypeId() == null || posMainOrder.getPaymentTypeId() <= 0) {
//                posMainOrder.setPaymentTypeId(mainOrderDto.getPaymentType());
//                //update order with paimentId
//                Integer updateOrder = mainOrderRepositoryCustom.mainOrderUpdate(posMainOrder.getPaymentTypeId(), mainOrderDto.getOrderId());
//                if (updateOrder <= 0) {
//                    connection.rollback();
//                    return null;
//                }
//            }
//
//            //stock updating
//            if (savedId > 0) {
//                for (PosMainOrderDetails details : mainOrderDetailses) {
//                    Integer updatedQty = stockRepositoryCustom.updateQty(details.getItemBarCode(), details.getQuantity());
//                    if (updatedQty <= 0) {
//                        connection.rollback();
//                        return null;
//                    }
//                }
//
//            } else {
//                connection.rollback();
//                return null;
//            }
//            connection.commit();
//            return mainOrderDto;
//        } catch (Exception e) {
//            e.getMessage();
//        } finally {
//            connection.setAutoCommit(true);
//        }
        return null;
    }

    @Override
    public boolean saveMainOrder(MainOrderDto mainOrderDto) throws Exception {
        return true;
    }
}
