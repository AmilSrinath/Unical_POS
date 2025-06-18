/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.Supplier;
import net.unical.pos.repository.custom.SupplierRepositoryCustom;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author Sanjuka
 */
public class SupplierRepositoryImpl implements SupplierRepositoryCustom{

    @Override
    public boolean saveSupplier(Supplier supplier) {
        try {
            return Statement.executeUpdate("Insert into pos_inv_supplier_tb values(?,?,?,?,?,?,?,?,?,?,?)",
                    supplier.getSupplierId(),
                    supplier.getSalesmanName(),
                    supplier.getCompanyName(),
                    supplier.getBrandName(),
                    supplier.getTelephone(),
                    supplier.getPhone(),
                    supplier.getAddress(),
                    supplier.getGmail(),
                    supplier.getStatus(),
                    LogInForm.userID,
                    supplier.getVisible())>0;
        } catch (Exception ex) {
            Logger.getLogger(ConfigTableRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "save Supplier error");
            return false;
        }
    }

    @Override
    public ArrayList<Supplier> getAllSuppliers()throws Exception{
        ResultSet rst=Statement.executeQuery("Select * from pos_inv_supplier_tb");
            
            ArrayList<Supplier> suppliers=new ArrayList<>();
            while(rst.next()){
                suppliers.add(new Supplier(rst.getInt(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getInt(5),
                        rst.getInt(6),
                        rst.getString(7),
                        rst.getString(8),
                        rst.getInt(9),
                        rst.getInt(10),
                        rst.getInt(11)
                ));
            }
            return suppliers;
    }
    
}
