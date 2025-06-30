/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.model.AuthModuleWise;
import net.unical.pos.model.UserAuth;

/**
 *
 * @author Amil Srinath
 */
public class ManageUserAuthRepositoryImpl {

    public List<UserAuth> getAllUsers() {
        List<UserAuth> userList = new ArrayList<>();

        String query = "SELECT u.user_id, u.employee_id, u.role_id, e.employee_name, r.role " +
                       "FROM pos_main_user_tb u " +
                       "JOIN pos_emp_employee_management_tb e ON u.employee_id = e.employee_id " +
                       "JOIN pos_main_user_role_tb r ON u.role_id = r.role_id " +
                       "WHERE u.visible = 1 AND e.visible = 1 AND r.visible = 1";

        try (
            Connection conn = DBCon.getDatabaseConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                UserAuth user = new UserAuth();
                user.setUser_id(rs.getInt("user_id"));
                user.setEmp_id(rs.getInt("employee_id"));
                user.setRole_id(rs.getInt("role_id"));
                user.setEmp_name(rs.getString("employee_name"));
                user.setUser_role(rs.getString("role"));
                userList.add(user);
            }
        } catch (Exception e) {
            Logger.getLogger(ManageUserAuthRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            net.unical.pos.configurations.Log.error(e, "Get All Users error");
            e.printStackTrace();
            Log.error(e,"getAllUsers error");
        }

        return userList;
    }

    public boolean saveModuleWise(ArrayList<AuthModuleWise> authModuleWise) {
        String deleteQuery = "DELETE FROM pos_module_wise_tb WHERE user_id = ?";
        String insertQuery = "INSERT INTO pos_module_wise_tb " +
                             "(module_id, user_id, created_date, edited_date, status) " +
                             "VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement deleteStmt = null;
        PreparedStatement insertStmt = null;

        try {
            conn = DBCon.getDatabaseConnection();
            conn.setAutoCommit(false); // Begin transaction

            // First delete existing records for the user
            if (!authModuleWise.isEmpty()) {
                int userId = authModuleWise.get(0).getUser_id();
                deleteStmt = conn.prepareStatement(deleteQuery);
                deleteStmt.setInt(1, userId);
                deleteStmt.executeUpdate();
            }

            // Insert new records
            insertStmt = conn.prepareStatement(insertQuery);
            for (AuthModuleWise module : authModuleWise) {
                insertStmt.setInt(1, module.getModule_id());
                insertStmt.setInt(2, module.getUser_id());
                insertStmt.setTimestamp(3, module.getCreated_date());
                insertStmt.setTimestamp(4, module.getEdited_date());
                insertStmt.setInt(5, module.getStatus());
                insertStmt.addBatch();
            }

            insertStmt.executeBatch();
            conn.commit(); // Commit transaction
            return true;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback(); // Rollback on error
            } catch (Exception rollbackEx) {
                Logger.getLogger(ManageUserAuthRepositoryImpl.class.getName()).log(Level.SEVERE, null, rollbackEx);
                Log.error(rollbackEx,"not save Module Wise");
            }

            Logger.getLogger(ManageUserAuthRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            net.unical.pos.configurations.Log.error(e, "Error saving module-wise permissions");
            return false;

        } finally {
            try {
                if (deleteStmt != null) deleteStmt.close();
                if (insertStmt != null) insertStmt.close();
                if (conn != null) conn.setAutoCommit(true);
                if (conn != null) conn.close();
            } catch (Exception ex) {
                Logger.getLogger(ManageUserAuthRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                Log.error(ex,"not save Module Wise");
            }
        }
    }

    public ArrayList<AuthModuleWise> getAllModuleWiseByUserID(Integer selectedUserId) {
        ArrayList<AuthModuleWise> authList = new ArrayList<>();

        String query = "SELECT module_wise_id, module_id, user_id, created_date, edited_date, status " +
                       "FROM pos_module_wise_tb " +
                       "WHERE user_id = ?";

        try (
            Connection conn = DBCon.getDatabaseConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, selectedUserId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AuthModuleWise auth = new AuthModuleWise();
                    auth.setModule_wise_id(rs.getInt("module_wise_id"));
                    auth.setModule_id(rs.getInt("module_id"));
                    auth.setUser_id(rs.getInt("user_id"));
                    auth.setCreated_date(rs.getTimestamp("created_date"));
                    auth.setEdited_date(rs.getTimestamp("edited_date"));
                    auth.setStatus(rs.getInt("status"));
                    authList.add(auth);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ManageUserAuthRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            net.unical.pos.configurations.Log.error(e, "Error fetching module-wise list for user: " + selectedUserId);
            e.printStackTrace();
            Log.error(e,"getAllModuleWiseByUserID error");
        }

        return authList;
    }

}
