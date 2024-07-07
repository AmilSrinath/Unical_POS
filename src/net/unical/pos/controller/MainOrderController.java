/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.MainOrderDto;
import net.unical.pos.dto.OrderDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.repository.custom.MainOrderRepositoryCustom;
import net.unical.pos.repository.custom.OrderRepositoryCustom;


/**
 *
 * @author Sanjuka
 */
public class MainOrderController {

    MainOrderRepositoryCustom mainOrderRepositoryCustom;
    OrderRepositoryCustom OrderRepositoryCustom;
    
    public MainOrderController() {
        mainOrderRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.MAIN_ORDER);
        OrderRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.ORDER);
    }

    public Integer saveMainOrder(MainOrderDto mainOrderDto) throws Exception{
        return mainOrderRepositoryCustom.saveMainOrder(mainOrderDto);
    }
    
    public ArrayList<OrderDto> getAllOrders(String query)throws Exception{
        return OrderRepositoryCustom.getAllOrders(query);
    }
}
