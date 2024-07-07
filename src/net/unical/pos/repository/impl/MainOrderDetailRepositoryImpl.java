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
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.PosMainOrderDetails;
import net.unical.pos.repository.custom.MainOrderDetailRepositoryCustom;

/**
 *
 * @author HP
 */
public class MainOrderDetailRepositoryImpl implements MainOrderDetailRepositoryCustom {

    @Override
    public ArrayList<PosMainOrderDetails> getOrderDetails(Integer orderId) {
        try {
            ResultSet resultSet = Statement.executeQuery("SELECT * FROM pos_main_order_details_tb WHERE status = " + 1);
            ArrayList<PosMainOrderDetails> mainOrderDetailses = new ArrayList<>();

            while (resultSet.next()) {
                PosMainOrderDetails mainOrderDetails = new PosMainOrderDetails(
                        resultSet.getInt("order_detail_id"),
                        resultSet.getInt("order_id"),
                        resultSet.getInt("item_id"),
                        resultSet.getInt("item_bar_code"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("per_item_price"),
                        resultSet.getDouble("per_item_discount_price"),
                        resultSet.getDouble("total_discount_price"),
                        resultSet.getDouble("total_price"),
                        resultSet.getInt("status"));
                mainOrderDetailses.add(mainOrderDetails);
            }
            return mainOrderDetailses;
        } catch (Exception ex) {
            return null;
        }
    }

}
