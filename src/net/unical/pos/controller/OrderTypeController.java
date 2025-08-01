/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.OrderTypeDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.model.OrderTypeModel;
import net.unical.pos.service.custom.OrderTypeServiceCustom;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class OrderTypeController {
    private OrderTypeServiceCustom orderTypeService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ORDER_TYPE);
    public boolean saveOrderType(OrderTypeDto orderTypeDto) {
        return orderTypeService.saveOrderType(orderTypeDto);
    }

    public ArrayList<OrderTypeDto> getAllOrderType() {
        return orderTypeService.getAllOrdrTypes();
    }
    
    public boolean updateOrderType(OrderTypeDto orderTypeDto) {
        return orderTypeService.updateOrderType(orderTypeDto);
    }
}
