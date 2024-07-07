/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.dto.MainOrderDto;
import net.unical.pos.dto.OrderDto;
import net.unical.pos.model.PosMainOrder;

/**
 *
 * @author HP
 */
public interface MainOrderRepositoryCustom {

//    public Integer mainOrderSave(PosMainOrder mainOrder);
//
//    public PosMainOrder findOne(Integer orderId);
//
//    public Integer mainOrderUpdate(Integer paymentTypeId, Integer orderId);

    public Integer saveMainOrder(MainOrderDto mainOrderDto)throws Exception;

    public ArrayList<OrderDto> getAllOrders(String query)throws Exception;

   
    
}
