/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.PosInvStock;
import net.unical.pos.repository.custom.StockRepositoryCustom;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author Sanjuka
 */
public class StockRepositoryImpl implements StockRepositoryCustom{

    @Override
    public boolean Save(PosInvStock posInvStock)throws Exception{
        return Statement.executeUpdate("INSERT into pos_inv_stock_tb values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                posInvStock.getStockId(),
                posInvStock.getGrnId(),
                posInvStock.getMainItemCategoryId(),
                posInvStock.getSubItemCategoryId(),
                posInvStock.getItemId(),
                posInvStock.getItemBarCode(),
                posInvStock.getStockCategoryId(),
                posInvStock.getStockName(),
                posInvStock.getUnitTypeId(),
                posInvStock.getCostPrice(),
                posInvStock.getLastGrnPrice(),
                posInvStock.getQuantity(),
                posInvStock.getStatus(),
                LogInForm.userID,
                posInvStock.getVisible()) > 0 ;
    }

    @Override
    public boolean Update(PosInvStock posInvStock) throws Exception {
        return Statement.executeUpdate("UPDATE pos_inv_stock_tb SET cost_price=?,last_grn_price=?,quantity=? WHERE item_id=?",
                posInvStock.getCostPrice(),posInvStock.getLastGrnPrice(),posInvStock.getQuantity(),posInvStock.getItemId())>0;
    }

    @Override
    public PosInvStock searchStock(Integer itemId) throws Exception {
        ResultSet rst=Statement.executeQuery("Select * from pos_inv_stock_tb where item_id=?", itemId);
        
        while(rst.next()){
            return new PosInvStock(rst.getInt(1),rst.getInt(2), rst.getInt(3), 
                    rst.getInt(4), rst.getInt(5), rst.getInt(6), rst.getInt(7), 
                    rst.getString(8), rst.getInt(9), rst.getDouble(10), 
                    rst.getDouble(11), rst.getDouble(12), rst.getInt(13), rst.getInt(14), rst.getInt(15));
        }
        return null;
    }

    @Override
    public Integer updateQty(Integer barCode, Integer qty) {
        try {
            return Statement.executeUpdate("UPDATE pos_inv_stock_tb p SET p.quantity = p.quantity - " + qty + " WHERE p.item_bar_code=" + barCode + "");
        } catch (Exception ex) {
            Logger.getLogger(StockRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "update Qty error");
            return 0;
        }
    }

    @Override
    public ArrayList<PosInvStock> searchAllItems(Integer mainCategoryId, Integer subCategoryId) {
        ArrayList<PosInvStock> posInvStocks = new ArrayList<>();
        try {
            String sql = "SELECT i.item_id, i.item_code_prefix, i.item_name, s.quantity FROM pos_inv_stock_tb s JOIN pos_main_item_tb i ON s.item_id = i. item_id WHERE s.main_item_category_id = ? AND s.sub_item_category_id = ? ";
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setInt(1, mainCategoryId);
            pstm.setInt(2, subCategoryId);
            ResultSet rst = pstm.executeQuery();
            while(rst.next()) {
                PosInvStock posInvStock = new PosInvStock();
                posInvStock.setItemId(rst.getInt("item_id"));
                posInvStock.setCodePrefix(rst.getString("item_code_prefix"));
                posInvStock.setItemName(rst.getString("item_name"));
                posInvStock.setQuantity((double)rst.getInt("quantity"));
                posInvStocks.add(posInvStock);
            }
            return posInvStocks;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(StockRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "Stock Search Error!");
        }
        return null;
    }
    
}
