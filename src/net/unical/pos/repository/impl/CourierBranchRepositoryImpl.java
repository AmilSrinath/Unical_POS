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
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.model.CourierBranchModel;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author Amil Srinath
 */
public class CourierBranchRepositoryImpl {

    public void saveCourierCompany(CourierBranchModel courierBranchModel) {
        String sql = "INSERT INTO pos_courier_branch_tb " +
                     "(branch_name, branch_contact, created_date, edited_date, company_id, status, user_id) " +
                     "VALUES (?, ?, ?, ?, (SELECT company_id FROM pos_courier_company_tb WHERE company_name = ?), ?, ?)";

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, courierBranchModel.getBranchName());
            stmt.setString(2, courierBranchModel.getBranchContact());
            stmt.setTimestamp(3, courierBranchModel.getCreateDate());
            stmt.setTimestamp(4, courierBranchModel.getEditedDate());
            stmt.setString(5, courierBranchModel.getCompanyName());
            stmt.setInt(6, courierBranchModel.getStatus());
            stmt.setInt(7, LogInForm.userID);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCourierCompany(Integer selectedBranchId, String branchNameText, String branchContactText, String companyText) {
        String sql = "UPDATE pos_courier_branch_tb " +
                     "SET branch_name = ?, branch_contact = ?, edited_date = ?, " +
                     "company_id = (SELECT company_id FROM pos_courier_company_tb WHERE company_name = ?) " +
                     "WHERE branch_id = ?";

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, branchNameText);
            stmt.setString(2, branchContactText);
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stmt.setString(4, companyText);
            stmt.setInt(5, selectedBranchId);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CourierBranchModel> getAllCourierBranchs() {
        List<CourierBranchModel> list = new ArrayList<>();
        String sql = "SELECT b.*, c.company_name " +
                     "FROM pos_courier_branch_tb b " +
                     "LEFT JOIN pos_courier_company_tb c ON b.company_id = c.company_id";

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CourierBranchModel model = new CourierBranchModel();
                model.setBranchId(rs.getInt("branch_id"));
                model.setBranchName(rs.getString("branch_name"));
                model.setBranchContact(rs.getString("branch_contact"));
                model.setCreateDate(rs.getTimestamp("created_date"));
                model.setEditedDate(rs.getTimestamp("edited_date"));
                model.setStatus(rs.getInt("status"));
                model.setUserId(rs.getInt("user_id"));
                model.setCompanyName(rs.getString("company_name")); // from join
                list.add(model);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<String> getBranchByCompanyName(String selectedCompany) throws ClassNotFoundException {
        List<String> branchList = new ArrayList<>();
        String query = "SELECT b.branch_name " +
                       "FROM pos_courier_branch_tb b " +
                       "JOIN pos_courier_company_tb c ON b.company_id = c.company_id " +
                       "WHERE c.company_name = ? AND b.status = 1";

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, selectedCompany);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                branchList.add(rs.getString("branch_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return branchList;
    }

    public String getBranchContactByNameAndCompany(String selectedBranch, String selectedCompany) throws ClassNotFoundException {
        String contact = "";
        String query = "SELECT b.branch_contact " +
                       "FROM pos_courier_branch_tb b " +
                       "JOIN pos_courier_company_tb c ON b.company_id = c.company_id " +
                       "WHERE b.branch_name = ? AND c.company_name = ? AND b.status = 1";

        try (Connection conn = DBCon.getDatabaseConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, selectedBranch);
            ps.setString(2, selectedCompany);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                contact = rs.getString("branch_contact");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contact;
    }

}
