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
import net.unical.pos.model.StatusRegModel;

/**
 *
 * @author Amil Srinath
 */
public class StatusRegRepositoryImpl {

    public void saveStatusReg(StatusRegModel statusRegModel) {
        String insertQuery = "INSERT INTO pos_status_reg (description, create_date, edited_date, status, user_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

            stmt.setString(1, statusRegModel.getDescription());
            stmt.setTimestamp(2, currentTimestamp);
            stmt.setTimestamp(3, currentTimestamp);
            stmt.setInt(4, statusRegModel.getStatus());
            stmt.setInt(5, statusRegModel.getUser_id());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Status registered successfully.");

        } catch (Exception e) {
            Logger.getLogger(StatusRegRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "save Status Reg error");
            JOptionPane.showMessageDialog(null, "Error saving status registration.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateStatusReg(Integer selectedStatusRegId, String statusReg) {
        String updateQuery = "UPDATE .pos_status_reg SET description = ?, edited_date = ? WHERE reg_id = ?";

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, statusReg);
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(3, selectedStatusRegId);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Status updated successfully.");

        } catch (Exception e) {
            Logger.getLogger(StatusRegRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "updaate Status Reg error");
            JOptionPane.showMessageDialog(null, "Error updating status registration.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<StatusRegModel> getAllStatusReg() {
        List<StatusRegModel> list = new ArrayList<>();
        String query = "SELECT * FROM pos_status_reg";

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                StatusRegModel model = new StatusRegModel();
                model.setReg_id(rs.getInt("reg_id"));
                model.setDescription(rs.getString("description"));
                model.setCreate_date(rs.getTimestamp("create_date"));
                model.setEdited_date(rs.getTimestamp("edited_date"));
                model.setStatus(rs.getInt("status"));
                model.setUser_id(rs.getInt("user_id"));
                list.add(model);
            }

        } catch (Exception e) {
            Logger.getLogger(StatusRegRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "get all Status Reg error");
            JOptionPane.showMessageDialog(null, "Error fetching status registration records.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return list;
    }

    public int getStatusRegIdByDes(Object selectedItem) {
        String query = "SELECT reg_id FROM pos_status_reg WHERE description = ?";
    
        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, selectedItem.toString());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("reg_id");
            }

        } catch (Exception e) {
            Logger.getLogger(StatusRegRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "get Status Reg Id ByDes error");
            JOptionPane.showMessageDialog(null, "Error retrieving reg_id by description.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return -1;
    }

}
