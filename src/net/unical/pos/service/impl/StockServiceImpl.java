/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import net.unical.pos.dto.StockDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.PosInvStock;
import net.unical.pos.repository.custom.StockRepositoryCustom;
import net.unical.pos.service.custom.StockServiceCustome;

/**
 *
 * @author Sanjuka
 */
public class StockServiceImpl implements StockServiceCustome{

    StockRepositoryCustom stockRepositoryCustom;
    
    public StockServiceImpl() {
        stockRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.STOCK);
    }

    
    @Override
    public boolean saveStock(StockDto stockDto) throws Exception {
        PosInvStock posInvStock=new PosInvStock(stockDto.getStockId(),
                stockDto.getGrnId(),
                stockDto.getMainItemCategoryId(),
                stockDto.getSubItemCategoryId(),
                stockDto.getItemId(),
                stockDto.getItemBarCode(),
                stockDto.getStockCategoryId(),
                stockDto.getStockName(),
                stockDto.getUnitType(),
                stockDto.getCostPrice(),
                stockDto.getLastGrnPrice(),
                stockDto.getQty(),
                stockDto.getStatus(),
                stockDto.getUserId(),
                1);
        return stockRepositoryCustom.Save(posInvStock);
    }

    @Override
    public boolean updateTock(StockDto stockDto) throws Exception {
        PosInvStock posInvStock=new PosInvStock(stockDto.getStockId(),
                stockDto.getGrnId(),
                stockDto.getMainItemCategoryId(),
                stockDto.getSubItemCategoryId(),
                stockDto.getItemId(),
                stockDto.getItemBarCode(),
                stockDto.getStockCategoryId(),
                stockDto.getStockName(),
                stockDto.getUnitType(),
                stockDto.getCostPrice(),
                stockDto.getLastGrnPrice(),
                stockDto.getQty(),
                stockDto.getStatus(),
                stockDto.getUserId(),
                1);
        return stockRepositoryCustom.Update(posInvStock);
    }

    @Override
    public StockDto searchStock(Integer itemId) throws Exception {
        PosInvStock posInvStock=stockRepositoryCustom.searchStock(itemId);
        if(posInvStock!=null){
            return new StockDto(posInvStock.getStockId(),
                posInvStock.getGrnId(),
                posInvStock.getMainItemCategoryId(), posInvStock.getSubItemCategoryId(), 
                posInvStock.getItemId(), posInvStock.getItemBarCode(), 
                posInvStock.getStockCategoryId(), posInvStock.getStockName(), 
                posInvStock.getUnitTypeId(), posInvStock.getCostPrice(), 
                posInvStock.getLastGrnPrice(), posInvStock.getQuantity(), 
                posInvStock.getStatus(), posInvStock.getUserId(), posInvStock.getVisible());
        }else{
            return null;
        }
        
    }
    
}
