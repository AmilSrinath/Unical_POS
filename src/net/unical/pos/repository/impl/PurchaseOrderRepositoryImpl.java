/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.PurchaseOrderModel;
import net.unical.pos.repository.custom.PurchaseOrderRepositoryCustom;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author Sanjuka
 */
public class PurchaseOrderRepositoryImpl implements PurchaseOrderRepositoryCustom{

    @Override
    public Integer Save(PurchaseOrderModel purchaseOrderModel) throws Exception{
        
               Statement.executeUpdate("Insert into pos_inv_purchase_order_tb values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
                purchaseOrderModel.getPoId(),
                purchaseOrderModel.getPoPrefix(),
                purchaseOrderModel.getPoCode(),
                purchaseOrderModel.getPoCodePrefix(),
                purchaseOrderModel.getSupplierId(),
                purchaseOrderModel.getSupplierName(),
                purchaseOrderModel.getPoDate(),
                purchaseOrderModel.getExpectedDate(),
                purchaseOrderModel.getTotalOrderPrice(),
                purchaseOrderModel.getPaymentType(),
                purchaseOrderModel.getStatus(),
                LogInForm.userID,
                purchaseOrderModel.getVisible());
               
               Integer poId=0;
               ResultSet rst=Statement.executeQuery("SELECT MAX(po_id) FROM pos_inv_purchase_order_tb");
               while(rst.next()){
                   poId=rst.getInt(1);
               }
               return poId;
        }

    @Override
    public ArrayList<PurchaseOrderModel> getAllPurchaseOrders() throws Exception {
        ResultSet rst=Statement.executeQuery("SELECT * FROM pos_inv_purchase_order_tb where status=1");
        
        ArrayList<PurchaseOrderModel> purchaseOrderModels=new ArrayList<>();
        while(rst.next()){
            purchaseOrderModels.add(new PurchaseOrderModel(rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getString(6),
                    rst.getDate(7),
                    rst.getDate(8),
                    rst.getDouble(9),
                    rst.getInt(10),
                    rst.getInt(11),
                    rst.getInt(12),
                    rst.getInt(13)
            ));
        }
        return purchaseOrderModels;
    }

    @Override
    public ArrayList<PurchaseOrderModel> getItemList(String itemCode) {
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM pos_inv_purchase_order_tb where status=1 AND po_code_prefix LIKE ?");
            pstm.setString(1, "%" + itemCode + "%");
            ResultSet rst = pstm.executeQuery();
            
            ArrayList<PurchaseOrderModel> purchaseOrderModels=new ArrayList<>();
            while(rst.next()){
                purchaseOrderModels.add(new PurchaseOrderModel(rst.getInt(1),
                        rst.getString(2),
                        rst.getInt(3),
                        rst.getString(4),
                        rst.getInt(5),
                        rst.getString(6),
                        rst.getDate(7),
                        rst.getDate(8),
                        rst.getDouble(9),
                        rst.getInt(10),
                        rst.getInt(11),
                        rst.getInt(12),
                        rst.getInt(13)
                ));
            }
            return purchaseOrderModels;
        } catch (Exception ex) {
            Logger.getLogger(PurchaseOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
