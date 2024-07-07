/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import net.unical.pos.model.PosInvStock;

/**
 *
 * @author Sanjuka
 */
public interface StockRepositoryCustom {

    public boolean Save(PosInvStock posInvStock)throws Exception;

    public boolean Update(PosInvStock posInvStock)throws Exception;

    public PosInvStock searchStock(Integer itemId)throws Exception;
    
    public Integer updateQty(Integer  barCode, Integer qty);
}
