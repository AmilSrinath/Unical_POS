/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.OrderTypeDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.OrderTypeModel;
import net.unical.pos.repository.custom.OrderTypeRepositoryCustom;
import net.unical.pos.service.custom.OrderTypeServiceCustom;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class OrderTypeServiceImpl implements OrderTypeServiceCustom{
    private OrderTypeRepositoryCustom OrderTypeRepositoryCustom = RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.ORDER_TYPE);
    @Override
    public boolean saveOrderType(OrderTypeDto orderTypeDto) {
        return OrderTypeRepositoryCustom.saveOrderType(new OrderTypeModel(0, orderTypeDto.getType(), orderTypeDto.getStatus(), orderTypeDto.getCreatedDate(), orderTypeDto.getEditedDate()));
    }

    @Override
    public ArrayList<OrderTypeDto> getAllOrdrTypes() {
        ArrayList<OrderTypeDto> orderTypeDtos = new ArrayList<>();
        ArrayList<OrderTypeModel> orderTypeModels = OrderTypeRepositoryCustom.getAllOrdrTypes();
        for (OrderTypeModel orderTypeModel : orderTypeModels) {
            orderTypeDtos.add(new OrderTypeDto(
                    orderTypeModel.getId(),
                    orderTypeModel.getType(),
                    orderTypeModel.getStatus(),
                    orderTypeModel.getCreatedDate(),
                    orderTypeModel.getEditedDate())
            );
        }
        return orderTypeDtos;
    }

    @Override
    public boolean updateOrderType(OrderTypeDto orderTypeDto) {
        return OrderTypeRepositoryCustom.updateOrderType(orderTypeDto);
    }

    
    
}
