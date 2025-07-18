/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.DBCon;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Amil Srinath
 */
public class LoginRepositoryImpl {

    public boolean login(int userID, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            try {
                con = DBCon.getDatabaseConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

            String sql = "SELECT password FROM pos_main_user_tb WHERE user_id = ? AND status = 1 AND visible = 1";
            ps = con.prepareStatement(sql);
            System.out.println("userID : "+userID);
            ps.setInt(1, userID);
            rs = ps.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                return BCrypt.checkpw(password, hashedPassword);
            } else {
                return false; // user not found or inactive
            }

        } catch (SQLException e) {
            Logger.getLogger(LoginRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e,userID+" login error");
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                Logger.getLogger(LoginRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e,"login error");
            }
        }
    }

    public String getUserRoleByUserID(int userID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBCon.getDatabaseConnection();

            String sql = "SELECT r.role " +
                         "FROM pos_main_user_tb u " +
                         "JOIN pos_main_user_role_tb r ON u.role_id = r.role_id " +
                         "WHERE u.user_id = ? AND u.status = 1 AND u.visible = 1 AND r.status = 1 AND r.visible = 1";

            ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            } else {
                return null; // role not found or not visible/active
            }

        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(LoginRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, userID + " role fetching error");
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                Logger.getLogger(LoginRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e, "role fetch close error");
            }
        }
    }

}
