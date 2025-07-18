/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package net.unical.pos.view.Inquiry;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import net.unical.pos.view.Inquiry.*;
import net.unical.pos.view.Payment.*;
import net.unical.pos.view.OrderFilter.*;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import static java.time.LocalDate.now;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import net.unical.pos.view.home.Dashboard;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.unical.pos.controller.DeliveryOrderController;
import net.unical.pos.controller.PaymentTypesController;
import net.unical.pos.dto.PaymentTypeDto;
import net.unical.pos.log.Log;
import net.unical.pos.model.CourierCompanyModel;
import net.unical.pos.model.CustomerDataByInquirySearch;
import net.unical.pos.model.DeliveryOrder;
import net.unical.pos.model.DeliveryOrderAmounts;
import net.unical.pos.model.InquiryModel;
import net.unical.pos.model.ResonModel;
import net.unical.pos.repository.impl.CourierBranchRepositoryImpl;
import net.unical.pos.repository.impl.CourierCompanyRepositoryImpl;
import net.unical.pos.repository.impl.DeliveryOrderRepositoryImpl;
import net.unical.pos.repository.impl.InquiryRepositoryImpl;
import net.unical.pos.repository.impl.PaymentRepositoryImpl;
import net.unical.pos.repository.impl.ResonRepositoryImpl;
import net.unical.pos.view.deliveryOrders.DeliveryOrders;
import org.apache.commons.compress.harmony.unpack200.bytecode.forms.ThisFieldRefForm;
/**
 *
 * @author apple
 */
public class AddInquiry extends JInternalFrame {
    private PaymentTypesController paymentTypesController;
    private InquiryRepositoryImpl inquiryRepositoryImpl;
    private ResonRepositoryImpl resonRepositoryImpl;
    private CourierCompanyRepositoryImpl courierCompanyRepositoryImpl;
    private CourierBranchRepositoryImpl courierBranchRepositoryImpl;

    /**
     * Creates new form OrderFilter
     */
    
    public AddInquiry() {
        this.dashboard=dashboard;
        this.setTitle("Add Inquiry");
        
        initComponents();
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        pack();
    }
    
    private InquiryModel inquiryModel;
    
    Dashboard dashboard;
    
    public AddInquiry(Dashboard dashboard){
        this();
        this.dashboard = dashboard;
        this.paymentTypesController=new PaymentTypesController();
        this.resonRepositoryImpl = new ResonRepositoryImpl();
        this.inquiryRepositoryImpl = new InquiryRepositoryImpl();
        this.courierCompanyRepositoryImpl = new CourierCompanyRepositoryImpl();
        this.courierBranchRepositoryImpl = new CourierBranchRepositoryImpl();
        loadReasonsToComboBox();
        
        preloadBranches(cmbBranch);
        cmbBranch.setEnabled(false);
        loadDataCmbCompany();
    }
    
    String fromDate = null;
    String toDate = null;
    int status = -1;

    //This is inquary edit option
    AddInquiry(Dashboard dashboard, InquiryModel inquiryModel, String fromDate, String toDate, int status) {
        this.inquiryModel = inquiryModel;
        this.paymentTypesController=new PaymentTypesController();
        this.resonRepositoryImpl = new ResonRepositoryImpl();
        this.inquiryRepositoryImpl = new InquiryRepositoryImpl();
        this.courierCompanyRepositoryImpl = new CourierCompanyRepositoryImpl();
        this.courierBranchRepositoryImpl = new CourierBranchRepositoryImpl();
        
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
        
        initComponents();
        new AddInquiry(dashboard);
        this.dashboard = dashboard;
        loadReasonsToComboBox();
        preloadBranches(cmbBranch);
        cmbBranch.setEnabled(false);
        loadDataCmbCompany();
        
        txtCustomerName.setText(inquiryModel.getCustomerName());
        txtCustomerContact1.setText(inquiryModel.getCustomerPhone1());
        txtCustomerContact2.setText(inquiryModel.getCustomerPhone2());
        txtCustomerName.setText(inquiryModel.getCustomerName());
        cmbCompany.setSelectedItem(inquiryModel.getCompany());
        cmbBranch.setSelectedItem(inquiryModel.getBranch());
        txtBranchContact.setText(inquiryModel.getBranchContact());
        cmbReason.setSelectedItem(inquiryModel.getReason());
        txtRemark.setText(inquiryModel.getRemark());
        
        btnAddInquiry.setText("Update Inquiry");
        jPanel4.hide();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paymentOptions = new javax.swing.JDialog();
        btnPaid = new javax.swing.JButton();
        btnNotPaid = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        total_orders_count_txt = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtWayBill = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnAddInquiry = new javax.swing.JButton();
        txtCustomerName = new javax.swing.JTextField();
        txtCustomerContact1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtBranchContact = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtRemark = new javax.swing.JTextField();
        txtCustomerContact2 = new javax.swing.JTextField();
        cmbReason = new javax.swing.JComboBox<>();
        cmbBranch = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cmbCompany = new javax.swing.JComboBox<>();

        paymentOptions.setResizable(false);

        btnPaid.setBackground(new java.awt.Color(51, 153, 0));
        btnPaid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPaid.setForeground(new java.awt.Color(255, 255, 255));
        btnPaid.setText("Paid");
        btnPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaidActionPerformed(evt);
            }
        });

        btnNotPaid.setBackground(new java.awt.Color(255, 0, 51));
        btnNotPaid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNotPaid.setForeground(new java.awt.Color(255, 255, 255));
        btnNotPaid.setText("Not Paid");
        btnNotPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotPaidActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("Action");

        javax.swing.GroupLayout paymentOptionsLayout = new javax.swing.GroupLayout(paymentOptions.getContentPane());
        paymentOptions.getContentPane().setLayout(paymentOptionsLayout);
        paymentOptionsLayout.setHorizontalGroup(
            paymentOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNotPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentOptionsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(97, 97, 97))
        );
        paymentOptionsLayout.setVerticalGroup(
            paymentOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentOptionsLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paymentOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnPaid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btnNotPaid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));

        total_orders_count_txt.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        total_orders_count_txt.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tracking No");

        btnSearch.setBackground(new java.awt.Color(255, 255, 0));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addGap(44, 44, 44)
                .addComponent(txtWayBill, javax.swing.GroupLayout.PREFERRED_SIZE, 1127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(total_orders_count_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addGap(27, 27, 27))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(total_orders_count_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtWayBill, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        btnAddInquiry.setBackground(new java.awt.Color(51, 153, 0));
        btnAddInquiry.setText("Add Inquiry");
        btnAddInquiry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddInquiryActionPerformed(evt);
            }
        });

        txtCustomerName.setEnabled(false);

        txtCustomerContact1.setEnabled(false);

        jLabel3.setText("Customer Name");

        jLabel4.setText("Customer Contact");

        jLabel5.setText("Branch");

        jLabel6.setText("Branch Contact");

        jLabel7.setText("Reason");

        jLabel8.setText("Remark");

        txtCustomerContact2.setEnabled(false);

        cmbBranch.setEditable(true);
        cmbBranch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBranchActionPerformed(evt);
            }
        });

        jLabel9.setText("Company");

        cmbCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCompanyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(380, 380, 380)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCustomerContact1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCustomerContact2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBranchContact, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbBranch, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbReason, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRemark, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(btnAddInquiry, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCustomerContact1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCustomerContact2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCompany, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBranch, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBranchContact, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(74, 74, 74)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbReason, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRemark, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(84, 84, 84)
                .addComponent(btnAddInquiry, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
    private void btnPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaidActionPerformed
        
    }//GEN-LAST:event_btnPaidActionPerformed

    private void btnNotPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotPaidActionPerformed
        
    }//GEN-LAST:event_btnNotPaidActionPerformed

    private void btnAddInquiryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddInquiryActionPerformed
        InquiryModel inquiry = new InquiryModel();
        
        if (btnAddInquiry.getText().equals("Add Inquiry")) {
            //Add Inquiry
            inquiry.setWayBill(txtWayBill.getText());
            inquiry.setCustomerId(searchCustomerID+"");
            inquiry.setCustomerName(txtCustomerName.getText());
            inquiry.setCustomerPhone1(txtCustomerContact1.getText());
            inquiry.setCustomerPhone2(txtCustomerContact2.getText());
            inquiry.setCompany(cmbCompany.getSelectedItem().toString());
            inquiry.setBranch(cmbBranch.getSelectedItem().toString());
            inquiry.setBranchContact(txtBranchContact.getText());
            inquiry.setReason(cmbReason.getSelectedItem().toString());
            inquiry.setRemark(txtRemark.getText());

            inquiryRepositoryImpl.saveInquiry(inquiry);

            txtWayBill.setText("");
            txtCustomerName.setText("");
            txtCustomerContact1.setText("");
            txtCustomerContact2.setText("");
            txtBranchContact.setText("");
            cmbReason.setSelectedIndex(0);
            txtRemark.setText("");
            cmbBranch.setSelectedIndex(0);
            cmbCompany.setSelectedIndex(0);
        } else{
            //Update Inquiry
            inquiry.setWayBill(inquiryModel.getWayBill());
            inquiry.setCustomerId(inquiryRepositoryImpl.getCustomerDataByWayBill(inquiryModel.getWayBill()).getCustomerId()+"");
            inquiry.setCustomerName(txtCustomerName.getText());
            inquiry.setCustomerPhone1(txtCustomerContact1.getText());
            inquiry.setCustomerPhone2(txtCustomerContact2.getText());
            inquiry.setCompany(cmbCompany.getSelectedItem().toString());
            inquiry.setBranch(cmbBranch.getSelectedItem().toString());
            inquiry.setBranchContact(txtBranchContact.getText());
            inquiry.setReason(cmbReason.getSelectedItem().toString());
            inquiry.setRemark(txtRemark.getText());
            
            inquiryRepositoryImpl.updateInquiry(inquiry);
            
            ViewInquiry viewInquiry = new ViewInquiry(dashboard,fromDate, toDate, status);
            dashboard.desktopPane.add(viewInquiry);
            Dimension d = dashboard.desktopPane.getSize();
            viewInquiry.setLayer(dashboard.desktopPane.POPUP_LAYER);
            viewInquiry.setSize(d);
            viewInquiry.setVisible(true);
        }
    }//GEN-LAST:event_btnAddInquiryActionPerformed

    private void loadReasonsToComboBox() {
        cmbReason.removeAllItems();
        cmbReason.addItem("Select Reson");
        List<ResonModel> resonList = resonRepositoryImpl.getAllResons();
        for (ResonModel reson : resonList) {
            cmbReason.addItem(reson.getReson());
        }
    }
    
    private void enableBranchAutocomplete(JComboBox<String> cmbBranch) {
        cmbBranch.setEditable(true);

        JTextField editor = (JTextField) cmbBranch.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String input = editor.getText().toLowerCase();
                cmbBranch.removeAllItems();

                branchList.stream()
                    .filter(branch -> branch.toLowerCase().contains(input))
                    .forEach(cmbBranch::addItem);

                editor.setText(input); // Retain typed text
                cmbBranch.showPopup();
            }
        });
    }

    private void preloadBranches(JComboBox<String> cmbBranch) {
        cmbBranch.removeAllItems();
        for (String branch : branchList) {
            cmbBranch.addItem(branch);
        }
    }
    
    int searchCustomerID = 0;
    
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        CustomerDataByInquirySearch customerDataByInquirySearch = inquiryRepositoryImpl.getCustomerDataByWayBill(txtWayBill.getText());
        
        if (customerDataByInquirySearch != null) {
            txtCustomerName.setText(customerDataByInquirySearch.getCustomerName());
            txtCustomerContact1.setText(customerDataByInquirySearch.getCustomerPhone1());
            txtCustomerContact2.setText(customerDataByInquirySearch.getCustomerPhone2());
            searchCustomerID = customerDataByInquirySearch.getCustomerId();
        }else{
            txtCustomerName.setText("");
            txtCustomerContact1.setText("");
            txtCustomerContact2.setText("");
            searchCustomerID = 0;
            
            JOptionPane.showMessageDialog(this, "Customer not found for the given traking number.", "Search Result", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    public void loadDataCmbCompany() {
        cmbCompany.removeAllItems();
        cmbCompany.addItem("Select Company");
        List<CourierCompanyModel> companyModels = courierCompanyRepositoryImpl.getAllCourierCompanies();
        for (CourierCompanyModel companyModel : companyModels) {
            cmbCompany.addItem(companyModel.getCompanyName());
        }
    }
    
    List<String> branchList = new ArrayList<>();
    
    private void cmbCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCompanyActionPerformed
        String selectedCompany = cmbCompany.getSelectedItem().toString();
        if (selectedCompany.equals("Select Company")) {
            cmbBranch.setEnabled(false);  // Disable cmbBranch
        } else {
            cmbBranch.setEnabled(true);   // Enable cmbBranch
            
            //Load data for branch by selected company
            cmbBranch.removeAllItems();
            cmbBranch.addItem("Select Branch");
            try {
                branchList = courierBranchRepositoryImpl.getBranchByCompanyName(selectedCompany);
                enableBranchAutocomplete(cmbBranch);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddInquiry.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (String branch : branchList) {
                cmbBranch.addItem(branch);
            }
        }
    }//GEN-LAST:event_cmbCompanyActionPerformed

    private void cmbBranchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBranchActionPerformed
        try{
            String selectedBranch = cmbBranch.getSelectedItem().toString();
            String selectedCompany = cmbCompany.getSelectedItem().toString();

            if (!selectedBranch.equals("Select Branch")) {
                try {
                    String contact = courierBranchRepositoryImpl.getBranchContactByNameAndCompany(selectedBranch, selectedCompany);
                    txtBranchContact.setText(contact);
                } catch (Exception ex) {
                    Logger.getLogger(AddInquiry.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                txtBranchContact.setText("");
            }
        }catch(NullPointerException e){}
    }//GEN-LAST:event_cmbBranchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddInquiry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddInquiry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddInquiry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddInquiry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddInquiry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddInquiry;
    private javax.swing.JButton btnNotPaid;
    private javax.swing.JButton btnPaid;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cmbBranch;
    private javax.swing.JComboBox<String> cmbCompany;
    private javax.swing.JComboBox<String> cmbReason;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JDialog paymentOptions;
    private javax.swing.JLabel total_orders_count_txt;
    private javax.swing.JTextField txtBranchContact;
    private javax.swing.JTextField txtCustomerContact1;
    private javax.swing.JTextField txtCustomerContact2;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtRemark;
    private javax.swing.JTextField txtWayBill;
    // End of variables declaration//GEN-END:variables

    
}
