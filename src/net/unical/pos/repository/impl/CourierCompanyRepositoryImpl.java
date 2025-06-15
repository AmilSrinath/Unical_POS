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
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.model.CourierCompanyModel;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author Amil Srinath
 */
public class CourierCompanyRepositoryImpl {

    public void saveCourierCompany(CourierCompanyModel courierCompanyModel) {
        String sql = "INSERT INTO pos_courier_company_tb (company_name, company_contact, address, email, created_date, edited_date, status, user_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, courierCompanyModel.getCompanyName());
            stmt.setString(2, courierCompanyModel.getCompanyContact());
            stmt.setString(3, courierCompanyModel.getAddress());
            stmt.setString(4, courierCompanyModel.getEmail());
            stmt.setTimestamp(5, courierCompanyModel.getCreateDate());
            stmt.setTimestamp(6, courierCompanyModel.getEditedDate());
            stmt.setInt(7, courierCompanyModel.getStatus());
            stmt.setInt(8, LogInForm.userID);

            stmt.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(CourierCompanyRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void updateCourierCompany(Integer selectedResonId, String companyNameText, String companyContactText, String address, String email) {
        String sql = "UPDATE pos_courier_company_tb SET company_name = ?, company_contact = ?, address = ?, email = ?, edited_date = ? WHERE company_id = ?";

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, companyNameText);
            stmt.setString(2, companyContactText);
            stmt.setString(3, address);
            stmt.setString(4, email);
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(4, selectedResonId);

            stmt.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(CourierCompanyRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public List<CourierCompanyModel> getAllCourierCompanies() {
        List<CourierCompanyModel> list = new ArrayList<>();
        String sql = "SELECT * FROM pos_courier_company_tb";

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CourierCompanyModel model = new CourierCompanyModel();
                model.setCompanyId(rs.getInt("company_id"));
                model.setCompanyName(rs.getString("company_name"));
                model.setCompanyContact(rs.getString("company_contact"));
                model.setAddress(rs.getString("address"));
                model.setEmail(rs.getString("email"));
                model.setCreateDate(rs.getTimestamp("created_date"));
                model.setEditedDate(rs.getTimestamp("edited_date"));
                model.setStatus(rs.getInt("status"));
                model.setUserId(rs.getInt("user_id"));
                list.add(model);
            }

        } catch (Exception e) {
            Logger.getLogger(CourierCompanyRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        return list;
    }

}
