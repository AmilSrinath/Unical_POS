/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.DeliveryOrderDto;

/**
 *
 * @author Sanjuka
 */
public interface DeliveryOrderServiceCustom {
    public Integer saveDeliveryOrder(DeliveryOrderDto deliveryOrderDto)throws Exception;

    public ArrayList<DeliveryOrderDto> getAllOrders(String date,Integer paymentType)throws Exception;

    public ArrayList<DeliveryOrderDto> getAllDurationOrders(String fromDate, String toDate, Integer paymentType);

    public Double getSpecificWaight(Integer id);

    public String getOrderType(String deliveryID);
    
}
