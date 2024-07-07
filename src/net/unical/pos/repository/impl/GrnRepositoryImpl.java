/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.PosInvGrn;
import net.unical.pos.repository.custom.GrnRepositoryCustom;

/**
 *
 * @author Sanjuka
 */
public class GrnRepositoryImpl implements GrnRepositoryCustom{

    @Override
    public Integer SaveGrn(PosInvGrn entity) throws Exception {
        
        Statement.executeUpdate("INSERT into pos_inv_grn_tb values (?,?,?,?,?,?,?,?,?)",
                entity.getGrnId(),
                entity.getInvoiceNo(),
                entity.getSupplierId(),
                entity.getTotalPrice(),
                entity.getTotalDiscount(),
                entity.getCreatedDate(),
                entity.getStatus(),
                entity.getUserId(),
                entity.getVisible());
        
        Integer grnId=0;
        ResultSet rst=Statement.executeQuery("SELECT MAX(grn_id) FROM pos_inv_grn_tb");
        while(rst.next()){
            grnId=rst.getInt(1);
        }
        return grnId;
    }

    @Override
    public boolean Update(PosInvGrn entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Delete(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PosInvGrn Search(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PosInvGrn> getAll() throws Exception {
        ResultSet rst=Statement.executeQuery("SELECT * FROM pos_inv_grn_tb WHERE visible=1");
        ArrayList<PosInvGrn> posInvGrns=new ArrayList<>();
        while(rst.next()){
            posInvGrns.add(new PosInvGrn(rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDate(6),
                    rst.getInt(7),
                    rst.getInt(8),
                    rst.getInt(9)
            ));
        }
        return posInvGrns;
    }

    @Override
    public ArrayList<PosInvGrn> getSearchItems(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Save(PosInvGrn entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
