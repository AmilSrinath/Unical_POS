/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.PurchaseOrderDetailsDto;

/**
 *
 * @author Sanjuka
 */
public interface PurchaseOrderDetailsServiceCustom {

    public ArrayList<PurchaseOrderDetailsDto> getAllPurchaseOrderDetails(Integer Code)throws Exception;
    
}
