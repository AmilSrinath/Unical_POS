/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.model.OrderDetails;
import net.unical.pos.model.PosMainOrderDetails;

/**
 *
 * @author HP
 */
public interface MainOrderDetailRepositoryCustom {

    public ArrayList<PosMainOrderDetails> getOrderDetails(Integer orderId);
    
    public ArrayList<PosMainOrderDetails> getOrderDetailsByOrderId(Integer orderId);
    
    public ArrayList<OrderDetails[]> getOrderDetailsByCustomerId(Integer customerId);
    
}
