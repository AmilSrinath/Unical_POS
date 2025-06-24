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
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.log.Log;
import net.unical.pos.model.ResonModel;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author Amil Srinath
 */
public class ResonRepositoryImpl {

    public void saveReson(ResonModel resonModel) {
        String insertQuery = "INSERT INTO pos_config_reson_tb "
                + "(reson, status, created_date, edited_date, user_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (
            Connection conn = DBCon.getDatabaseConnection(); 
            PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setString(1, resonModel.getReson());
            stmt.setInt(2, resonModel.getStatus());
            stmt.setTimestamp(3, resonModel.getCreateDate());
            stmt.setTimestamp(4, resonModel.getEditedDate());
            stmt.setInt(5, LogInForm.userID); // Prefer setInt over converting to String

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inquiry saved successfully.");
        } catch (Exception e) {
            Logger.getLogger(ResonRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            net.unical.pos.configurations.Log.error(e,"save reson error");
            
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "An error occurred while saving the inquiry.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<ResonModel> getAllResons() {
        List<ResonModel> resonList = new ArrayList<>();

        String query = "SELECT * FROM pos_config_reson_tb";

        try (
            Connection conn = DBCon.getDatabaseConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                ResonModel model = new ResonModel();
                model.setResonId(rs.getInt("reson_id"));
                model.setReson(rs.getString("reson"));
                model.setStatus(rs.getInt("status"));
                model.setUserId(rs.getInt("user_id"));
                model.setCreateDate(rs.getTimestamp("created_date"));
                model.setEditedDate(rs.getTimestamp("edited_date"));
                resonList.add(model);
            }
        } catch (Exception e) {
            Logger.getLogger(ResonRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            net.unical.pos.configurations.Log.error(e,"get All Resons error");
            e.printStackTrace();
            Log.error(e, e);
        }

        return resonList;
    }

    public void updateResonTextOnly(int resonId, String resonText) {
        String updateQuery = "UPDATE pos_config_reson_tb SET reson = ?, edited_date = ? WHERE reson_id = ?";

        try (
            Connection conn = DBCon.getDatabaseConnection(); 
            PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, resonText);
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(3, resonId);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Reson updated successfully.");
        } catch (Exception e) {
            Logger.getLogger(ResonRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "Reson updated not update");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "An error occurred while updating the reson.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


}
