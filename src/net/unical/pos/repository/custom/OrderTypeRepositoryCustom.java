/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.dto.OrderTypeDto;
import net.unical.pos.model.OrderTypeModel;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public interface OrderTypeRepositoryCustom {

    public boolean saveOrderType(OrderTypeModel orderTypeModel);

    public ArrayList<OrderTypeModel> getAllOrdrTypes();

    public boolean updateOrderType(OrderTypeDto orderTypeDto);
    
}
