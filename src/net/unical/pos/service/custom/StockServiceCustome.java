/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import net.unical.pos.dto.StockDto;

/**
 *
 * @author Sanjuka
 */
public interface StockServiceCustome {

    public boolean saveStock(StockDto stockDto)throws Exception;

    public boolean updateTock(StockDto stockDto)throws Exception;

    public StockDto searchStock(Integer itemId)throws Exception;
    
}
