/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.model.TestModel;

/**
 *
 * @author Sanjuka
 */
public class TestClass {
    
    public List<TestModel> getCustomers(Connection con,String query) throws ClassNotFoundException, SQLException {
        Vector<TestModel> customerModels = new Vector<>();
        boolean isLocalConnection = false;
        Statement st = null;
        ResultSet rst = null;
        try {
            if (con == null) {
                con = DBCon.getDatabaseConnection();
                isLocalConnection = true;
            }
            String sql = "SELECT * FROM pos_main_customer_tb " + query;
//            System.err.println(sql);
            st = con.createStatement();
            rst = st.executeQuery(sql);
            while (rst.next()) {
                TestModel customerModel=new TestModel();
                    customerModel.setCustomerId(rst.getInt("customer_id"));
                    customerModel.setCustomerName(rst.getString("customer_name"));
                    customerModel.setNic(rst.getString("nic"));
                    customerModel.setAddress(rst.getString("address"));
                    customerModel.setPhone(rst.getInt("phone_one"));
                    customerModel.setCreatedDate(rst.getDate("created_Date"));
                    customerModel.setIsLoyalty(rst.getInt("is_loyalty"));
                    customerModel.setLoyaltyPoints(rst.getDouble("loyalty_amount"));
                    customerModel.setStatus(rst.getInt("status"));
                    customerModel.setUserId(rst.getInt("user_id"));
                    customerModel.setVisible(rst.getInt("visible"));
                    customerModels.add(customerModel);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        } finally {
            if (rst != null) {
                rst.close();
            }

            if (st != null) {
                st.close();
            }
            if (isLocalConnection) {
                if (con != null) {
                    con.close();
                }
            }
        }
        return customerModels;
    }
}
