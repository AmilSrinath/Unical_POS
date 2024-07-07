/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.SupplierDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.Supplier;
import net.unical.pos.repository.custom.SupplierRepositoryCustom;
import net.unical.pos.service.custom.SupplierServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class SupplierServiceImpl implements SupplierServiceCustom{
    
    private SupplierRepositoryCustom supplierRepositoryCustom;
    
    public SupplierServiceImpl() {
        supplierRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.SUPPLIER);
    }

    @Override
    public boolean saveSupplier(SupplierDto supplierDto) throws Exception {
        Supplier supplier = new Supplier(
                0, supplierDto.getSalesmanName(), supplierDto.getCompanyName(), 
                supplierDto.getBrandName(), supplierDto.getTelephone(),
                supplierDto.getPhone(),supplierDto.getAddress(),supplierDto.getGmail(),
                supplierDto.getStatus(),supplierDto.getUserId(),1);
        return supplierRepositoryCustom.saveSupplier(supplier);
    }

    @Override
    public ArrayList<SupplierDto> getAllSuppliers() throws Exception {
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        suppliers = supplierRepositoryCustom.getAllSuppliers();

        for (Supplier supplier : suppliers) {
            supplierDtos.add(new SupplierDto(supplier.getSupplierId(),supplier.getSalesmanName(), supplier.getCompanyName(),
                    supplier.getBrandName(),supplier.getTelephone(),supplier.getPhone(),
                    supplier.getAddress(),supplier.getGmail(),
                    supplier.getStatus(), supplier.getUserId(), supplier.getVisible()));
        }
        return supplierDtos;
    }
    
}
