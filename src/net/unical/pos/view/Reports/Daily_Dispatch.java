/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.view.Reports;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.model.WrapperOrder;
import net.unical.pos.reports.DailySalesPrinter;
import net.unical.pos.repository.impl.DeliveryOrderRepositoryImpl;
import net.unical.pos.view.OrderFilter.OrderFilter;
import net.unical.pos.view.home.Dashboard;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Sanjuka
 */
public class Daily_Dispatch extends javax.swing.JInternalFrame {

    /**
     * Creates new form Daily_Income
     */
    
    Dashboard dashboard;
    private DeliveryOrderRepositoryImpl deliveryOrderRepositoryImpl;
    
    SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
    private DefaultTableModel dataTableModel;
    
    public Daily_Dispatch(Dashboard dashboard) {
        initComponents();
        this.dashboard=dashboard;
        this.deliveryOrderRepositoryImpl = new DeliveryOrderRepositoryImpl();
        
        setCurrentDate();
    }

    private void setCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        jXDatePickerFrom.setDate(date);
        jXDatePickerTo.setDate(date);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jXDatePickerFrom = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jXDatePickerTo = new org.jdesktop.swingx.JXDatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("From");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Generate Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("To");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jXDatePickerTo, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXDatePickerFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButton1)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jXDatePickerFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jXDatePickerTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ArrayList<WrapperOrder> wrapperOrders = null;
        try {
            wrapperOrders = deliveryOrderRepositoryImpl.getDaliyOutOfDeliveryOrder(jXDatePickerFrom.getDate(), jXDatePickerTo.getDate());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Daily_Dispatch.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex,"getDaliyOutOfDeliveryOrdertes error");
        }
        if (!wrapperOrders.isEmpty()) {
            try {
                generateExcel(wrapperOrders); // method to be created
            } catch (Exception ex) {
                Logger.getLogger(OrderFilter.class.getName()).log(Level.SEVERE, null, ex);
                Log.error(ex,"getDaliyOutOfDeliveryOrdertes error");
            }
            JOptionPane.showMessageDialog(this, "Excel file generated successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "No records found for the selected dates.");
            Log.info(Daily_Dispatch.class,"No records found for the selected dates.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void generateExcel(ArrayList<WrapperOrder> orders) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Wrapping Orders");

        // Header row
        String[] headers = {"TrackingNumber","Reference","PackageDescription", "ReceiverName", "ReceiverAddress", "ReceiverCity", "ReceiverContactNo", "NoOfPcs", "Kilo", "Gram", "Amount", "Exchange", "Remark"};
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Data rows
        int rowNum = 1;
        for (WrapperOrder order : orders) {
            double totalWeightKg = order.getWeight() / 1000.0;
            int kilos = (int) totalWeightKg;
            int grams = (int) ((totalWeightKg - kilos) * 1000);

            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(order.getOrderCode());
            row.createCell(1).setCellValue(order.getDeliveryId());
            row.createCell(2).setCellValue(0);
            row.createCell(3).setCellValue(order.getCustomerName());
            row.createCell(4).setCellValue(order.getAddress());
            row.createCell(5).setCellValue(0);
            row.createCell(6).setCellValue(order.getPhoneOne()+" / "+order.getPhoneTwo());
            row.createCell(7).setCellValue(1);
            row.createCell(8).setCellValue(kilos);
            row.createCell(9).setCellValue(grams);
            row.createCell(10).setCellValue(order.getCodAmount()+"");
            row.createCell(11).setCellValue(0);
            row.createCell(12).setCellValue(0);
        }

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        try {
            // File chooser for user to pick save location
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Excel File");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }

                FileOutputStream out = new FileOutputStream(filePath);
                workbook.write(out);
                out.close();
                workbook.close();

                JOptionPane.showMessageDialog(null, "Excel file generated successfully at:\n" + filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.error(e,"generateExcel error");
            JOptionPane.showMessageDialog(null, "Error saving Excel file: " + e.getMessage());
        }
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Daily_Income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Daily_Income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Daily_Income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Daily_Income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Daily_Income().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private org.jdesktop.swingx.JXDatePicker jXDatePickerFrom;
    private org.jdesktop.swingx.JXDatePicker jXDatePickerTo;
    // End of variables declaration//GEN-END:variables
}
