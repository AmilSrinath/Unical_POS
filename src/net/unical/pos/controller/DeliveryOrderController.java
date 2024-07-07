/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.DeliveryOrderDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.DeliveryOrderServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class DeliveryOrderController {
    
    private DeliveryOrderServiceCustom deliveryOrderCustom;

    public DeliveryOrderController() {
        deliveryOrderCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.DELIVERY_ORDER);
    }
    
    public Integer saveDeliveryOrder(DeliveryOrderDto deliveryOrderDto)throws Exception{
        return deliveryOrderCustom.saveDeliveryOrder(deliveryOrderDto);
    }

    public ArrayList<DeliveryOrderDto> getAllOrders(String date,Integer paymentType) throws Exception{
        return deliveryOrderCustom.getAllOrders(date,paymentType);
    }
    
    public ArrayList<DeliveryOrderDto> getAllDurationOrders(String fromDate,String toDate,Integer paymentType) throws Exception{
        return deliveryOrderCustom.getAllDurationOrders(fromDate,toDate,paymentType);
    }
    
}
