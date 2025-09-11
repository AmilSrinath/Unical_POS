/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.MainItemDto;
import net.unical.pos.dto.StockDto;
import net.unical.pos.model.PosInvStock;

/**
 *
 * @author Sanjuka
 */
public interface StockServiceCustome {

    public boolean saveStock(StockDto stockDto)throws Exception;

    public boolean updateTock(StockDto stockDto)throws Exception;

    public StockDto searchStock(Integer itemId)throws Exception;

    public ArrayList<StockDto> searchAllItems(Integer mainCategoryId, Integer subCategoryId);
    
}
