/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.SupplierDto;

/**
 *
 * @author Sanjuka
 */
public interface SupplierServiceCustom {
    
    public boolean saveSupplier(SupplierDto supplierDto)throws Exception;
    
    public ArrayList<SupplierDto> getAllSuppliers() throws Exception;
}
