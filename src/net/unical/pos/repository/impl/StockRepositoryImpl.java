/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.Connection;
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
public class StockRepositoryImpl implements StockRepositoryCustom {

    @Override
    public boolean Save(PosInvStock posInvStock) throws Exception {
        System.out.println("Come to this save method");
        String insertQuery = "INSERT INTO pos_inv_stock_tb VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(insertQuery)) {
            ps.setInt(1, posInvStock.getStockId());
            ps.setInt(2, posInvStock.getGrnId());
            ps.setInt(3, posInvStock.getMainItemCategoryId());
            ps.setInt(4, posInvStock.getSubItemCategoryId());
            ps.setInt(5, posInvStock.getItemId());
            ps.setInt(6, posInvStock.getItemBarCode());
            ps.setInt(7, posInvStock.getStockCategoryId());
            ps.setString(8, posInvStock.getStockName());
            ps.setInt(9, posInvStock.getUnitTypeId());
            ps.setDouble(10, posInvStock.getCostPrice());
            ps.setDouble(11, posInvStock.getLastGrnPrice());
            ps.setDouble(12, posInvStock.getQuantity());
            ps.setInt(13, posInvStock.getStatus());
            ps.setInt(14, LogInForm.userID);
            ps.setBoolean(15, posInvStock.getVisible() > 0);
            ps.setInt(16, posInvStock.getRegistryId());

            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    @Override
    public boolean Update(PosInvStock posInvStock) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE pos_inv_stock_tb SET status = ? WHERE item_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            
            pstm.setInt(1, 0);
            pstm.setInt(2, posInvStock.getItemId());
            boolean isUpdated = pstm.executeUpdate() > 0;

            if (isUpdated) {
                String insertQuery = "INSERT into pos_inv_stock_tb values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(insertQuery);
                ps.setInt(1, posInvStock.getStockId());
                ps.setInt(2, posInvStock.getGrnId());
                ps.setInt(3, posInvStock.getMainItemCategoryId());
                ps.setInt(4, posInvStock.getSubItemCategoryId());
                ps.setInt(5, posInvStock.getItemId());
                ps.setInt(6, posInvStock.getItemBarCode());
                ps.setInt(7, posInvStock.getStockCategoryId());
                ps.setString(8, posInvStock.getStockName());
                ps.setInt(9, posInvStock.getUnitTypeId());
                ps.setDouble(10, posInvStock.getCostPrice());
                ps.setDouble(11, posInvStock.getLastGrnPrice());
                ps.setDouble(12, posInvStock.getQuantity());
                ps.setInt(13, posInvStock.getStatus());
                ps.setInt(14, LogInForm.userID);
                ps.setBoolean(15, posInvStock.getVisible() > 0);
                ps.setInt(16, posInvStock.getRegistryId());

                boolean isInserted = ps.executeUpdate() > 0;

                if (isInserted) {
                    connection.commit();
                    return true;

                } else {
                    connection.rollback();
                    return false;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(StockRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public PosInvStock searchStock(Integer itemId) throws Exception {
        ResultSet rst = Statement.executeQuery("Select * from pos_inv_stock_tb where item_id=?", itemId);

        while (rst.next()) {
            return new PosInvStock(rst.getInt(1), rst.getInt(2), rst.getInt(3),
                    rst.getInt(4), rst.getInt(5), rst.getInt(6), rst.getInt(7),
                    rst.getString(8), rst.getInt(9), rst.getDouble(10),
                    rst.getDouble(11), rst.getDouble(12), rst.getInt(13), rst.getInt(14), rst.getInt(15));
        }
        return null;
    }

    @Override
    public Integer updateQty(Integer barCode, Integer qty
    ) {
        try {
            return Statement.executeUpdate("UPDATE pos_inv_stock_tb p SET p.quantity = p.quantity - " + qty + " WHERE p.item_bar_code=" + barCode + "");
        } catch (Exception ex) {
            Logger.getLogger(StockRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "update Qty error");
            return 0;
        }
    }

    @Override
    public ArrayList<PosInvStock> searchAllItems(Integer mainCategoryId, Integer subCategoryId
    ) {
        ArrayList<PosInvStock> posInvStocks = new ArrayList<>();
        try {
            String sql = "SELECT i.item_id, i.item_code_prefix, i.item_name, s.quantity FROM pos_inv_stock_tb s JOIN pos_main_item_tb i ON s.item_id = i. item_id WHERE s.main_item_category_id = ? AND s.sub_item_category_id = ? ";
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setInt(1, mainCategoryId);
            pstm.setInt(2, subCategoryId);
            ResultSet rst = pstm.executeQuery();
            while (rst.next()) {
                PosInvStock posInvStock = new PosInvStock();
                posInvStock.setItemId(rst.getInt("item_id"));
                posInvStock.setCodePrefix(rst.getString("item_code_prefix"));
                posInvStock.setItemName(rst.getString("item_name"));
                posInvStock.setQuantity((double) rst.getInt("quantity"));
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
