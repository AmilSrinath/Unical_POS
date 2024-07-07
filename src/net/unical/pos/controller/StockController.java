/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import net.unical.pos.dto.StockDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.StockServiceCustome;

/**
 *
 * @author Sanjuka
 */
public class StockController {

    StockServiceCustome stockServiceCustome;
    
    public StockController() {
        stockServiceCustome=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.STOCK);
    }

    public boolean saveStock(StockDto stockDto) throws Exception{
        return stockServiceCustome.saveStock(stockDto);
    }
    
    public boolean updateStock(StockDto stockDto)throws Exception{
        return stockServiceCustome.updateTock(stockDto);
    }
    
    public StockDto searchStock(Integer itemId)throws Exception{
        return stockServiceCustome.searchStock(itemId);
    }
    
}
