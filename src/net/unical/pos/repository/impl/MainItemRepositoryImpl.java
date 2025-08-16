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
    public boolean save(PosMainItem item)throws Exception{
        return Statement.executeUpdate("INSERT into pos_main_item_tb values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                0,
                item.getBarCode(),
                item.getMainItemCategoryId(),
                item.getSubItemCategoryId(),
                item.getPrefix(),
                item.getCodePrefix(),
                item.getDiscount(),
                item.getItemName(),
                item.getUnitType(),
                item.getPriterType(),
                item.getCostPrice(),
                item.getUnitPrice(),
                item.getImagePath(),
                item.getStatus(),
                item.getGrnStatus(),
                item.getSellingItem(),
                LogInForm.userID,
                item.getVisible(),
                item.getWeight()) > 0 ;
    }

    @Override
    public ArrayList<PosMainItem> searchAllSubItems(Integer key) throws Exception {
        ResultSet rst=Statement.executeQuery("SELECT * FROM pos_main_item_tb WHERE sub_item_category_id='"+key+"' AND status=1 and selling_status=1 and visible=1");
        
        ArrayList<PosMainItem> posMainItems=new ArrayList<>();
        while(rst.next()){
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
        ResultSet rst=Statement.executeQuery("SELECT * FROM pos_main_item_tb WHERE item_id='"+key+"' AND status=1 and grn_status=1 and visible=1");
        ArrayList<PosMainItem> posMainItems=new ArrayList<>();
        while(rst.next()){
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
    public ArrayList<PosMainItem> searchAllItems(Integer main, Integer sub)throws Exception{
        ResultSet rst=Statement.executeQuery("SELECT * FROM pos_main_item_tb WHERE main_item_category_id='"+main+"' AND sub_item_category_id='"+sub+"' AND status=1 and grn_status=1 and visible=1");
        System.out.println("SELECT * FROM pos_main_item_tb WHERE main_item_category_id='"+main+"' AND sub_item_category_id='"+sub+"' AND status=1 and grn_status=1 and visible=1");
        ArrayList<PosMainItem> posMainItems=new ArrayList<>();
        while(rst.next()){
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
    public ArrayList<PosMainItem> searchAllItems(String key)throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PosMainItem> getAllItems(String quary) throws Exception {
        ResultSet rst=Statement.executeQuery("SELECT * FROM pos_main_item_tb "+quary);
        ArrayList<PosMainItem> posMainItems=new ArrayList<>();
        while(rst.next()){
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

}
