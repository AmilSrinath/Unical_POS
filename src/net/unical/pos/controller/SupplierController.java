/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.SupplierDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.SupplierServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class SupplierController {
    
    private SupplierServiceCustom supplierServiceCustom;

    public SupplierController() {
        supplierServiceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.SUPPLIER);
    }
    
    public ArrayList<SupplierDto> getAll() throws Exception {
        return supplierServiceCustom.getAllSuppliers();
    }

    public boolean saveSupplier(SupplierDto supplierDto) throws Exception {
        return supplierServiceCustom.saveSupplier(supplierDto);
    }

    public boolean updateSupplier(SupplierDto supplierDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
