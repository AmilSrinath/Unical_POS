/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package net.unical.pos.view.Payment;

import net.unical.pos.view.OrderFilter.*;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.view.home.Dashboard;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.unical.pos.controller.DeliveryOrderController;
import net.unical.pos.controller.PaymentTypesController;
import net.unical.pos.dto.PaymentTypeDto;
import net.unical.pos.log.Log;
import net.unical.pos.model.DeliveryOrder;
import net.unical.pos.model.DeliveryOrderAmounts;
import net.unical.pos.repository.impl.DeliveryOrderRepositoryImpl;
import net.unical.pos.repository.impl.PaymentRepositoryImpl;
import net.unical.pos.view.deliveryOrders.DeliveryOrders;
/**
 *
 * @author apple
 */
public class Payment extends JInternalFrame {
    private ArrayList<Integer> paymentTypeIds=new ArrayList<>();
    private PaymentTypesController paymentTypesController;
    private ArrayList<Integer> paymentTypeIds_2=new ArrayList<>();
    private DeliveryOrderRepositoryImpl deliveryOrderRepositoryImpl;
    private PaymentRepositoryImpl paymentRepositoryImpl;

    /**
     * Creates new form OrderFilter
     */
    public Payment() {
        initComponents();
        setTitle("Order Filter");
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        pack();
    }
    
    Dashboard dashboard;
    
    public Payment(Dashboard dashboard){
        this();dashboard = dashboard;
        this.deliveryOrderRepositoryImpl=new DeliveryOrderRepositoryImpl();
        this.paymentTypesController=new PaymentTypesController();
        this.paymentRepositoryImpl=new PaymentRepositoryImpl();
        
        setCurrentDate();
        getPaymentTypes();
        
        
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = formatter.format(jXDatePicker1.getDate());
        String toDate = formatter.format(jXDatePicker2.getDate());
        
        getAllOrders(fromDate, toDate, 0, 0);
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
        jScrollPane6 = new javax.swing.JScrollPane();
        paymentOrdersTable = new org.jdesktop.swingx.JXTable();
        jPanel4 = new javax.swing.JPanel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jButton1 = new javax.swing.JButton();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        total_orders_count_txt = new javax.swing.JLabel();
        paymentTypeCombo1 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        paymentStatusCombo = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();

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

        paymentOrdersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Order ID", "Order Code", "Customer ID", "COD", "Total Amount", "Payment Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        paymentOrdersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentOrdersTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                paymentOrdersTableMousePressed(evt);
            }
        });
        paymentOrdersTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                paymentOrdersTablePropertyChange(evt);
            }
        });
        paymentOrdersTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paymentOrdersTableKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(paymentOrdersTable);
        if (paymentOrdersTable.getColumnModel().getColumnCount() > 0) {
            paymentOrdersTable.getColumnModel().getColumn(0).setResizable(false);
        }
        if (paymentOrdersTable.getColumnModel().getColumnCount() > 0) {
            paymentOrdersTable.getColumnModel().getColumn(0).setMinWidth(0);
            paymentOrdersTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            paymentOrdersTable.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("From");

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("To");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Order ID");

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Total Orders :");

        total_orders_count_txt.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        total_orders_count_txt.setForeground(new java.awt.Color(255, 255, 255));

        paymentTypeCombo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any" }));
        paymentTypeCombo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentTypeCombo1MouseClicked(evt);
            }
        });
        paymentTypeCombo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentTypeCombo1ActionPerformed(evt);
            }
        });
        paymentTypeCombo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paymentTypeCombo1KeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Payment Type");

        paymentStatusCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any", "Paid", "Not Paid" }));
        paymentStatusCombo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentStatusComboMouseClicked(evt);
            }
        });
        paymentStatusCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentStatusComboActionPerformed(evt);
            }
        });
        paymentStatusCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paymentStatusComboKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Payment Status");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel18)))
                .addGap(80, 80, 80)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel17))
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel19))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(paymentTypeCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel16)))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(paymentStatusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton1)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total_orders_count_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel21))
                .addGap(68, 68, 68))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(total_orders_count_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(paymentTypeCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(jLabel16)
                                .addComponent(jLabel21))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(1, 1, 1))
                        .addComponent(paymentStatusCombo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = formatter.format(jXDatePicker1.getDate());
        String toDate = formatter.format(jXDatePicker2.getDate());

        int paymentTypeIndex = paymentTypeCombo1.getSelectedIndex();
        int paymentType = paymentTypeIndex != 0 ? paymentTypeIds_2.get(paymentTypeIndex - 1) : 0;
        
        int paymentStatus = paymentStatusCombo.getSelectedIndex();
        
        getAllOrders(fromDate, toDate, paymentType,paymentStatus);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void setCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        jXDatePicker1.setDate(date);
        jXDatePicker2.setDate(date);
    }
    
    private void getPaymentTypes() {
        try {
            String quary="WHERE visible=1 AND status=1";
            ArrayList<PaymentTypeDto> paymentTypes=paymentTypesController.getPaymentTypes(quary);
            
            for(PaymentTypeDto typeDto:paymentTypes){
                paymentTypeIds.add(typeDto.getPaymentTypeId());
                paymentTypeCombo1.addItem(typeDto.getName());
                paymentTypeIds_2.add(typeDto.getPaymentTypeId());
            }
        } catch (Exception ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(DeliveryOrder.class, "Cannot load Items : ", ex);
        }
    }
    
    
    private void getAllOrders(String fromDate, String toDate, Integer paymentType, int status) {
    try {
        ArrayList<DeliveryOrder> deliveryOrderDtos = paymentRepositoryImpl.getAllPaymentDuration(fromDate, toDate, paymentType, status);
        
        DefaultTableModel dtm = (DefaultTableModel) paymentOrdersTable.getModel();
        dtm.setRowCount(0);
        
        String statusText = null;
        boolean isPrint = false;
        
        int count = 0;
        double totAmount = 0.00;
        double totDeliveryFee = 0.00;
        double totCod = 0.00;
        double totReturns = 0.00;
                
        for (DeliveryOrder dto : deliveryOrderDtos) {
            count++;
            switch (dto.getPaymentStatus()) {
                case 0:
                    statusText = "Not Paid";
                    break;
                case 1:
                    statusText = "Paid";
                    break;
            }
            
            isPrint = dto.getIsPrint() == 1;
            Object[] rowData = {
                dto.getAddress(),
                dto.getOrderId(),
                dto.getOrderCode(),
                dto.getCustomerId(),
                dto.getCod(),
                dto.getSubTotalPrice(),
                statusText
            };
            dtm.addRow(rowData);
        }
        
        total_orders_count_txt.setText(String.valueOf(count));
    } catch (Exception ex) {
        Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    
    private void paymentOrdersTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentOrdersTableKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentOrdersTableKeyReleased

    private void paymentOrdersTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_paymentOrdersTablePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentOrdersTablePropertyChange

    private void paymentOrdersTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentOrdersTableMousePressed
        if (evt.getClickCount() == 2) {
            int selectedRow = paymentOrdersTable.getSelectedRow();
            if (selectedRow != -1) {
                orderID = paymentOrdersTable.getValueAt(selectedRow, 1).toString();
                
                paymentOptions.setLocationRelativeTo(null);
                paymentOptions.setSize(240, 110);
                paymentOptions.setVisible(true);
            }
        }
    }//GEN-LAST:event_paymentOrdersTableMousePressed

    private void paymentOrdersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentOrdersTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentOrdersTableMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void paymentTypeCombo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentTypeCombo1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentTypeCombo1MouseClicked

    private void paymentTypeCombo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentTypeCombo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentTypeCombo1ActionPerformed

    private void paymentTypeCombo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentTypeCombo1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentTypeCombo1KeyReleased

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String orderIdText = jTextField1.getText();

        if (!orderIdText.isEmpty()) {
            try {
                getOrderById(orderIdText);
            } catch (NumberFormatException e) {
                Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, e);
                Log.error(Payment.class, "Invalid Order ID: ", e);
            }
        } else {
            Logger.getLogger(Payment.class.getName()).log(Level.WARNING, "Order ID is empty");
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    String orderID=null;
    
    private void btnPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaidActionPerformed
        if (orderID != null) {
            paymentRepositoryImpl.update(orderID,1);
            
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String fromDate = formatter.format(jXDatePicker1.getDate());
            String toDate = formatter.format(jXDatePicker2.getDate());

            getAllOrders(fromDate, toDate, 0,0);
            
            paymentOptions.dispose();
        }
    }//GEN-LAST:event_btnPaidActionPerformed

    private void btnNotPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotPaidActionPerformed
        if (orderID != null) {
            paymentRepositoryImpl.update(orderID,0);
            
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String fromDate = formatter.format(jXDatePicker1.getDate());
            String toDate = formatter.format(jXDatePicker2.getDate());

            getAllOrders(fromDate, toDate, 0,0);
            
            paymentOptions.dispose();
        }
    }//GEN-LAST:event_btnNotPaidActionPerformed

    private void paymentStatusComboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentStatusComboMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentStatusComboMouseClicked

    private void paymentStatusComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentStatusComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentStatusComboActionPerformed

    private void paymentStatusComboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentStatusComboKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentStatusComboKeyReleased

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
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNotPaid;
    private javax.swing.JButton btnPaid;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private javax.swing.JDialog paymentOptions;
    private org.jdesktop.swingx.JXTable paymentOrdersTable;
    private javax.swing.JComboBox<String> paymentStatusCombo;
    private javax.swing.JComboBox<String> paymentTypeCombo1;
    private javax.swing.JLabel total_orders_count_txt;
    // End of variables declaration//GEN-END:variables

    private void getOrderById(String orderId) {
        try {
            DeliveryOrder order = deliveryOrderRepositoryImpl.getOrderById(orderId);

            DefaultTableModel dtm = (DefaultTableModel) paymentOrdersTable.getModel();
            dtm.setRowCount(0);

            if (order != null) {
                String status = null;
                if (order.getStatusType() == 1) {
                    status = "Active";
                } else if (order.getStatusType() == 2) {
                    status = "Pending";
                } else if (order.getStatusType() == 3) {
                    status = "Wrapping";
                } else if (order.getStatusType() == 4) {
                    status = "Out of Delivery";
                } else if (order.getStatusType() == 5) {
                    status = "Delivered";
                } else if (order.getStatusType() == 6) {
                    status = "Return";
                } else {
                    status = "Cancel";
                }

                Object[] rowData = {
                    order.getOrderId(),
                    order.getOrderCode(),
                    order.getCustomerId(),
                    order.getCod(),
                    order.getGrandTotalPrice(),
                    status
                };
                dtm.addRow(rowData);
            } else {
                Logger.getLogger(Payment.class.getName()).log(Level.WARNING, "No order found for Order ID: " + orderId);
            }

        } catch (Exception ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(Payment.class, "Cannot load order details: ", ex);
        }
    }

//    private void getAllOrders(String fromDate, String toDate, int paymentType) {
//        try {
//            ArrayList<DeliveryOrderAmounts> deliveryOrderAmountDto = deliveryOrderRepositoryImpl.getCalculation(fromDate, toDate, paymentType);
//
//            DefaultTableModel dtm = (DefaultTableModel) paymentOrdersTable.getModel();
//            dtm.setRowCount(0);
//
//            String statusText = null;
//            boolean isPrint = false;
//
//            int count = 0;
//            double totAmount = 0.00;
//            double totDeliveryFee = 0.00;
//            double totCod = 0.00;
//            double totReturns = 0.00;
//
//            for (DeliveryOrderAmounts amounts : deliveryOrderAmountDto) {
//                totAmount = amounts.getTotalAmount();
//                totDeliveryFee = amounts.getTotalDeliveryCharge();
//                totCod = amounts.getTotalCod();
//                totReturns = amounts.getTotalReturns();
//            }
//
//            total_orders_count_txt.setText(String.valueOf(count));
//        } catch (Exception ex) {
//            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}