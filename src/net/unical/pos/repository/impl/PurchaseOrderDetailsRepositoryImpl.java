/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.PurchaseOrderDetailsModel;
import net.unical.pos.repository.custom.PurchaseOrderDetailsRepositoryCustom;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author Sanjuka
 */
public class PurchaseOrderDetailsRepositoryImpl implements PurchaseOrderDetailsRepositoryCustom{

    @Override
    public boolean save(PurchaseOrderDetailsModel purchaseOrderDetailsModel) throws Exception{
        return Statement.executeUpdate("Insert into pos_inv_purchase_order_details_tb values(?,?,?,?,?,?,?,?,?)",
                purchaseOrderDetailsModel.getPoDetailsId(),
                purchaseOrderDetailsModel.getPoId(),
                purchaseOrderDetailsModel.getItemId(),
                purchaseOrderDetailsModel.getItemName(),
                purchaseOrderDetailsModel.getQty(),
                purchaseOrderDetailsModel.getExpectedPrice(),
                purchaseOrderDetailsModel.getLastGrnPrice(),
                purchaseOrderDetailsModel.getTotalPrice(),
                LogInForm.userID)>0;
    }

    @Override
    public ArrayList<PurchaseOrderDetailsModel> getAllPurchaseOrderDetails(Integer poCode) throws Exception {
        ResultSet rst=Statement.executeQuery("SELECT * FROM pos_inv_purchase_order_details_tb where po_id='"+poCode+"'");
        System.err.println("SELECT * FROM pos_inv_purchase_order_details_tb where po_id='"+poCode+"'");
        ArrayList<PurchaseOrderDetailsModel> purchaseOrderModels=new ArrayList<>();
        while(rst.next()){
            purchaseOrderModels.add(new PurchaseOrderDetailsModel(rst.getInt(1),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getDouble(7),
                    rst.getDouble(8),
                    rst.getInt(9)
            ));
        }
        return purchaseOrderModels;
    }
}
