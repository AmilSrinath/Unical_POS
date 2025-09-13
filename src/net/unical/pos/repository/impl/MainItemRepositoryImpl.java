/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.PosMainItem;
import net.unical.pos.model.SubItemCategoryModel;
import net.unical.pos.repository.custom.MainItemRepositoryCustom;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author HP
 */
public class MainItemRepositoryImpl implements MainItemRepositoryCustom {
    @Override
    public ArrayList<PosMainItem> searchAllSubItems(Integer key) throws Exception {
        ResultSet rst = Statement.executeQuery("SELECT * FROM pos_main_item_tb WHERE sub_item_category_id='" + key + "' AND status=1 and selling_status=1 and visible=1");

        ArrayList<PosMainItem> posMainItems = new ArrayList<>();
        while (rst.next()) {
            posMainItems.add(new PosMainItem(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getDouble(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getDouble(11),
                    rst.getDouble(12),
                    rst.getString(13),
                    rst.getInt(14),
                    rst.getInt(15),
                    rst.getInt(16),
                    rst.getInt(17),
                    rst.getInt(18),
                    rst.getDouble(19)
            ));
        }
        return posMainItems;
    }

    @Override
    public ArrayList<PosMainItem> searchAllItems(Integer key) throws Exception {
        ResultSet rst = Statement.executeQuery("SELECT * FROM pos_main_item_tb WHERE item_id='" + key + "' AND status=1 and grn_status=1 and visible=1");
        ArrayList<PosMainItem> posMainItems = new ArrayList<>();
        while (rst.next()) {
            posMainItems.add(new PosMainItem(rst.getInt(1),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getDouble(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getDouble(11),
                    rst.getDouble(12),
                    rst.getString(13),
                    rst.getInt(14),
                    rst.getInt(15),
                    rst.getInt(16),
                    rst.getInt(17),
                    rst.getInt(18),
                    rst.getDouble(19)
            ));
        }
        return posMainItems;
    }

    @Override
    public ArrayList<PosMainItem> searchAllItems(Integer main, Integer sub) throws Exception {
        ResultSet rst = Statement.executeQuery("SELECT * FROM pos_main_item_tb WHERE main_item_category_id='" + main + "' AND sub_item_category_id='" + sub + "' AND status=1 and grn_status=1 and visible=1");
        System.out.println("SELECT * FROM pos_main_item_tb WHERE main_item_category_id='" + main + "' AND sub_item_category_id='" + sub + "' AND status=1 and grn_status=1 and visible=1");
        ArrayList<PosMainItem> posMainItems = new ArrayList<>();
        while (rst.next()) {
            posMainItems.add(new PosMainItem(rst.getInt(1),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getDouble(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getDouble(11),
                    rst.getDouble(12),
                    rst.getString(13),
                    rst.getInt(14),
                    rst.getInt(15),
                    rst.getInt(16),
                    rst.getInt(17),
                    rst.getInt(18),
                    rst.getDouble(19)
            ));
        }
        return posMainItems;
    }

    @Override
    public ArrayList<PosMainItem> searchAllItems(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PosMainItem> getAllItems(String quary) throws Exception {
        System.err.println("SELECT * FROM pos_main_item_tb " + quary);
        ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM pos_main_item_tb " + quary).executeQuery();
        ArrayList<PosMainItem> posMainItems = new ArrayList<>();
        while (rst.next()) {
            posMainItems.add(new PosMainItem(rst.getInt(1),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getDouble(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getDouble(11),
                    rst.getDouble(12),
                    rst.getString(13),
                    rst.getInt(14),
                    rst.getInt(15),
                    rst.getInt(16),
                    rst.getInt(17),
                    rst.getInt(18),
                    rst.getDouble(19)
            ));
        }
        return posMainItems;
    }

    public Integer getItemCode(String product_name) {
        String sql = "SELECT item_id FROM pos_main_item_tb WHERE item_name = ?";
        try {
            ResultSet rst = Statement.executeQuery(sql, product_name);
            if (rst.next()) {
                return rst.getInt("item_id");
            }
        } catch (Exception e) {
            Logger.getLogger(MainItemRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "ProductId fetched error");
        }
        return 0;
    }

    @Override
    public String getItemId(String mainCat) {
        return null;
    }

    @Override
    public boolean isNewItem(String itemName) {
        try {
            String sql = "SELECT item_name FROM pos_main_item_tb WHERE item_name = ? ";
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1, itemName);
            return pstm.executeQuery().next();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainItemRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainItemRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean save(PosMainItem item, Connection connection) throws Exception {
        String sql = "INSERT into pos_main_item_tb (item_id, item_bar_code, main_item_category_id, sub_item_category_id, item_prefix, item_code_prefix, discount, item_name, unit_type, printer_type, cost_price, unit_price, image_path, grn_status, selling_status, status, user_id, visible, weight, registry_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, 0);
        pstm.setInt(2, item.getBarCode());
        pstm.setInt(3, item.getMainItemCategoryId());
        pstm.setInt(4, item.getSubItemCategoryId());
        pstm.setString(5, item.getPrefix());
        pstm.setString(6, item.getCodePrefix());
        pstm.setDouble(7, item.getDiscount());
        pstm.setString(8, item.getItemName());
        pstm.setString(9, item.getUnitType());
        pstm.setString(10, item.getPriterType());
        pstm.setDouble(11, item.getCostPrice());
        pstm.setDouble(12, item.getUnitPrice());
        pstm.setString(13, item.getImagePath());
        pstm.setInt(14, item.getStatus());
        pstm.setInt(15, item.getGrnStatus());
        pstm.setInt(16, item.getSellingItem());
        pstm.setInt(17, LogInForm.userID);
        pstm.setInt(18, item.getVisible());
        pstm.setDouble(19, item.getWeight());
        pstm.setInt(20, item.getRegistryId());
        
        return pstm.executeUpdate() > 0;
    }

}
