/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.dto.OrderDto;

/**
 *
 * @author Sanjuka
 */
public interface OrderRepositoryCustom {

    public ArrayList<OrderDto> getAllOrders(String query)throws Exception;
    
}
