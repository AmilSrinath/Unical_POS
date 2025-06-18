/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.model.StatusTypeModel;

/**
 *
 * @author Amil Srinath
 */
public class StatusTypeRepositoryImpl {

    public void saveStatusType(StatusTypeModel statusTypeModel) {
        String insertQuery = "INSERT INTO pos_status_types "
                           + "(reg_id, status_type, user_id, create_date, edited_date, status) "
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
        String query = "SELECT * FROM pos_status_types";

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                StatusTypeModel model = new StatusTypeModel();
                model.setStatus_id(rs.getInt("status_id"));
                model.setReg_id(rs.getInt("reg_id"));
                model.setStatus_type(rs.getString("status_type"));
                model.setUser_id(rs.getInt("user_id"));
                model.setCreate_date(rs.getTimestamp("create_date"));
                model.setEdited_date(rs.getTimestamp("edited_date"));
                model.setStatus(rs.getInt("status"));

                list.add(model);
            }

        } catch (Exception e) {
            Logger.getLogger(StatusTypeRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "get all Status Type error");
            JOptionPane.showMessageDialog(null, "Error fetching status types.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return list;
    }

}
