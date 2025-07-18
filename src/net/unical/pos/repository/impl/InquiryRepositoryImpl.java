/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.repository.impl;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.model.CustomerDataByInquirySearch;
import net.unical.pos.model.DeliveryOrder;
import net.unical.pos.model.InquiryModel;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author Amil Srinath
 */
public class InquiryRepositoryImpl {

    public CustomerDataByInquirySearch getCustomerDataByWayBill(String wayBill) {
        String query = "SELECT c.customer_name, c.phone_one, c.phone_two , c.customer_id "
                + "FROM pos_main_delivery_order_tb d "
                + "JOIN pos_main_customer_tb c ON d.customer_id = c.customer_id "
                + "WHERE d.order_code = ?";

        try (
                Connection conn = DBCon.getDatabaseConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, wayBill);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String customerName = rs.getString("customer_name");
                String phone1 = rs.getString("phone_one");
                String phone2 = rs.getString("phone_two");
                int customerId = rs.getInt("customer_id");

                return new CustomerDataByInquirySearch(customerName, phone1, phone2, customerId);
            }
        } catch (Exception e) {
            Logger.getLogger(InquiryRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "getCustomerDataByWayBill faild");
            e.printStackTrace(); // Consider logging instead
        }
        return null;
    }

    public void saveInquiry(InquiryModel inquiryModel) {
        String insertQuery = "INSERT INTO pos_inquiry_tb "
                + "(way_bill, customer_id, customer_name, customer_phone_1, customer_phone_2, "
                + "company, branch, branch_contact, reson, remark, status, created_date, edited_date, user_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBCon.getDatabaseConnection(); PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setString(1, inquiryModel.getWayBill());
            stmt.setInt(2, Integer.parseInt(inquiryModel.getCustomerId()));
            stmt.setString(3, inquiryModel.getCustomerName());
            stmt.setString(4, inquiryModel.getCustomerPhone1());
            stmt.setString(5, inquiryModel.getCustomerPhone2());
            stmt.setString(6, inquiryModel.getCompany());
            stmt.setString(7, inquiryModel.getBranch());
            stmt.setString(8, inquiryModel.getBranchContact());
            stmt.setString(9, inquiryModel.getReason());
            stmt.setString(10, inquiryModel.getRemark());
            stmt.setInt(11, LogInForm.userID);

            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            stmt.setDate(12, currentDate); // created_date
            stmt.setDate(13, currentDate); // edited_date

            stmt.setInt(14, 1); // user_id

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inquiry saved successfully.");
            Log.info(inquiryModel, "Inquiry saved successfully");
        } catch (MySQLIntegrityConstraintViolationException dupEx) {
            Logger.getLogger(InquiryRepositoryImpl.class.getName()).log(Level.SEVERE, null, dupEx);
            Log.error(dupEx, "saveInquiry faild");
            JOptionPane.showMessageDialog(null,
                    "Waybill '" + inquiryModel.getWayBill() + "' already exists!",
                    "Duplicate Entry",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            Logger.getLogger(InquiryRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "saveInquiry faild");
            e.printStackTrace(); // For debugging
            JOptionPane.showMessageDialog(null,
                    "An error occurred while saving the inquiry.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update(String wayBill, int status) {
        String updateQuery = "UPDATE pos_inquiry_tb SET status_id = ?, edited_date = ? WHERE way_bill = ?";

        try (
                Connection conn = DBCon.getDatabaseConnection(); PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setInt(1, status);
            stmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            stmt.setString(3, wayBill);

            int rowsUpdated = stmt.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(InquiryRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "update inquary faild");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while updating the inquiry.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<InquiryModel> getAllInquiryDuration(String fromDate, String toDate, int status) {
        ArrayList<InquiryModel> list = new ArrayList<>();
        
        System.out.println("status : "+status);

        String baseQuery = "SELECT i.*, c.customer_number " +
                       "FROM pos_inquiry_tb i " +
                       "JOIN pos_main_customer_tb c ON i.customer_id = c.customer_id " +
                       "WHERE i.created_date BETWEEN ? AND ?";
        
        if (status != 0) {
            baseQuery += " AND status_id = ?";
        }

        try (
                Connection conn = DBCon.getDatabaseConnection(); PreparedStatement stmt = conn.prepareStatement(baseQuery)) {
            stmt.setDate(1, java.sql.Date.valueOf(fromDate));
            stmt.setDate(2, java.sql.Date.valueOf(toDate));

            if (status != 0) {
                stmt.setInt(3, status);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                InquiryModel model = new InquiryModel();

                model.setWayBill(rs.getString("way_bill"));
                model.setCustomerId(String.valueOf(rs.getInt("customer_number")));
                model.setCustomerName(rs.getString("customer_name"));
                model.setCustomerPhone1(rs.getString("customer_phone_1"));
                model.setCustomerPhone2(rs.getString("customer_phone_2"));
                model.setCompany(rs.getString("company"));
                model.setBranch(rs.getString("branch"));
                model.setBranchContact(rs.getString("branch_contact"));
                model.setReason(rs.getString("reson"));
                model.setRemark(rs.getString("remark"));
                model.setStatus(rs.getInt("status"));
                model.setStatusId(rs.getInt("status_id"));
                model.setCreateDate(rs.getDate("created_date"));

                list.add(model);
            }

        } catch (Exception e) {
            Logger.getLogger(InquiryRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "get All Inquiry Duration faild");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching inquiry records.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return list;
    }

    public ArrayList<InquiryModel> searchInquiryByWayBill(String wayBillPart) {
        ArrayList<InquiryModel> list = new ArrayList<>();

        String query = "SELECT * FROM pos_inquiry_tb WHERE way_bill LIKE ?";

        try (
                Connection conn = DBCon.getDatabaseConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + wayBillPart + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                InquiryModel model = new InquiryModel();

                model.setWayBill(rs.getString("way_bill"));
                model.setCustomerId(String.valueOf(rs.getInt("customer_id")));
                model.setCustomerName(rs.getString("customer_name"));
                model.setCustomerPhone1(rs.getString("customer_phone_1"));
                model.setCustomerPhone2(rs.getString("customer_phone_2"));
                model.setCompany(rs.getString("company"));
                model.setBranch(rs.getString("branch"));
                model.setBranchContact(rs.getString("branch_contact"));
                model.setReason(rs.getString("reson"));
                model.setRemark(rs.getString("remark"));
                model.setStatusId(rs.getInt("status_id"));

                list.add(model);
            }

        } catch (Exception e) {
            Logger.getLogger(InquiryRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "search Inquiry By WayBill faild");
            e.printStackTrace();
        }

        return list;
    }

    public void updateInquiry(InquiryModel inquiry) {
        String updateQuery = "UPDATE pos_inquiry_tb SET "
                + "customer_id = ?, "
                + "customer_name = ?, "
                + "customer_phone_1 = ?, "
                + "customer_phone_2 = ?, "
                + "company = ?, "
                + "branch = ?, "
                + "branch_contact = ?, "
                + "reson = ?, "
                + "remark = ?, "
                + "edited_date = ?, "
                + "user_id = ? "
                + "WHERE way_bill = ?";

        try (
            Connection conn = DBCon.getDatabaseConnection();
            PreparedStatement stmt = conn.prepareStatement(updateQuery)
        ) {
            stmt.setInt(1, Integer.parseInt(inquiry.getCustomerId()));
            stmt.setString(2, inquiry.getCustomerName());
            stmt.setString(3, inquiry.getCustomerPhone1());
            stmt.setString(4, inquiry.getCustomerPhone2());
            stmt.setString(5, inquiry.getCompany());
            stmt.setString(6, inquiry.getBranch());
            stmt.setString(7, inquiry.getBranchContact());
            stmt.setString(8, inquiry.getReason());
            stmt.setString(9, inquiry.getRemark());

            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            stmt.setDate(10, currentDate); // edited_date

            stmt.setInt(11, LogInForm.userID); // user_id (set dynamically if needed)
            stmt.setString(12, inquiry.getWayBill());

            System.out.println("inquiry.getWayBill() : "+inquiry.getWayBill());
            
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Inquiry updated successfully.");
                Log.info(inquiry, "Inquiry updated successfully");
            } else {
                JOptionPane.showMessageDialog(null, "No inquiry found with the given way bill.");
            }
        } catch (Exception e) {
            Logger.getLogger(InquiryRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "updateInquiry failed");
            JOptionPane.showMessageDialog(null, "An error occurred while updating the inquiry.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
