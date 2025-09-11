/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.model.StatusTypeModel;
import net.unical.pos.repository.custom.StatusTypeRepositoryCustom;

/**
 *
 * @author Amil Srinath
 */
public class StatusTypeRepositoryImpl implements StatusTypeRepositoryCustom{

    public void saveStatusType(StatusTypeModel statusTypeModel) {
        String insertQuery = "INSERT INTO pos_status_types "
                           + "(reg_id, status_type, user_id, created_date, edited_date, status) "
                           + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            Timestamp now = new Timestamp(System.currentTimeMillis());

            stmt.setInt(1, statusTypeModel.getReg_id());
            stmt.setString(2, statusTypeModel.getStatus_type());
            stmt.setInt(3, statusTypeModel.getUser_id());
            stmt.setTimestamp(4, now);
            stmt.setTimestamp(5, now);
            stmt.setInt(6, statusTypeModel.getStatus());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Status type saved successfully.");

        } catch (Exception e) {
            Logger.getLogger(StatusTypeRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "save Status Type error");
            JOptionPane.showMessageDialog(null, "Error saving status type.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateStatusType(Integer selectedStatusTypeId, String statusType) {
        String updateQuery = "UPDATE pos_status_types SET status_type = ?, edited_date = ? WHERE status_id = ?";

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, statusType);
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(3, selectedStatusTypeId);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Status type updated successfully.");

        } catch (Exception e) {
            Logger.getLogger(StatusTypeRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "update Status Type error");
            JOptionPane.showMessageDialog(null, "Error updating status type.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<StatusTypeModel> getAllStatusType() {
        List<StatusTypeModel> list = new ArrayList<>();
        String query = "SELECT st.*, sr.description AS reg_description " +
                       "FROM pos_status_types st " +
                       "LEFT JOIN pos_status_reg sr ON st.reg_id = sr.reg_id";

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                StatusTypeModel model = new StatusTypeModel();
                model.setStatus_id(rs.getInt("status_id"));
                model.setReg_id(rs.getInt("reg_id"));
                model.setStatus_type(rs.getString("status_type"));
                model.setUser_id(rs.getInt("user_id"));
                model.setCreate_date(rs.getTimestamp("created_date"));
                model.setEdited_date(rs.getTimestamp("edited_date"));
                model.setStatus(rs.getInt("status"));
                model.setReg_des(rs.getString("reg_description"));

                list.add(model);
            }

        } catch (Exception e) {
            Logger.getLogger(StatusTypeRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "get all Status Type error");
            JOptionPane.showMessageDialog(null, "Error fetching status types.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return list;
    }

    public List<StatusTypeModel> getAllStatusTypeByRegID(int regID) {
        List<StatusTypeModel> list = new ArrayList<>();
        String query = "SELECT st.*, sr.description AS reg_description " +
                       "FROM pos_status_types st " +
                       "LEFT JOIN pos_status_reg sr ON st.reg_id = sr.reg_id Where st.reg_id = "+regID;

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                StatusTypeModel model = new StatusTypeModel();
                model.setStatus_id(rs.getInt("status_id"));
                model.setReg_id(rs.getInt("reg_id"));
                model.setStatus_type(rs.getString("status_type"));
                model.setUser_id(rs.getInt("user_id"));
                model.setCreate_date(rs.getTimestamp("created_date"));
                model.setEdited_date(rs.getTimestamp("edited_date"));
                model.setStatus(rs.getInt("status"));
                model.setReg_des(rs.getString("reg_description"));

                list.add(model);
            }

        } catch (Exception e) {
            Logger.getLogger(StatusTypeRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "get all Status Type error");
            JOptionPane.showMessageDialog(null, "Error fetching status types.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return list;
    }

    @Override
    public int getStatusIdByStatus(String status) {
        try {
            String sql = "SELECT status_id FROM pos_status_types WHERE status_type = ? ";
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1, status);
            ResultSet rst = pstm.executeQuery();
            if(rst.next()) {
                return Integer.parseInt(rst.getString("status_id"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StatusTypeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(StatusTypeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "Status id fetched error");
        }
        return 2;
    }

}
