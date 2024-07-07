/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.view.sales;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.unical.pos.configurations.POSConfigurationsobjects;
import net.unical.pos.controller.ConfigTabalesController;
import net.unical.pos.controller.MainItemCategoryController;
import net.unical.pos.controller.MainItemController;
import net.unical.pos.controller.MainOrderController;
import net.unical.pos.controller.MainTableLocationController;
import net.unical.pos.controller.StockController;
import net.unical.pos.controller.SubItemCategoryController;
import net.unical.pos.controller.SubTableLocationController;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.dto.ConfigTableDetailsDto;
import net.unical.pos.dto.ConfigTablesDto;
import net.unical.pos.dto.MainItemCategoryDto;
import net.unical.pos.dto.MainItemDto;
import net.unical.pos.dto.MainOrderDto;
import net.unical.pos.dto.MainTableLocationDto;
import net.unical.pos.dto.OrderDetailsDto;
import net.unical.pos.dto.OrderDto;
import net.unical.pos.dto.StockDto;
import net.unical.pos.dto.SubItemCategoryDto;
import net.unical.pos.dto.SubTableLocationDto;
import net.unical.pos.view.home.Dashboard;
import org.jdesktop.swingx.JXButton;

/**
 *
 * @author Sanjuka
 */
public class Order extends javax.swing.JFrame {

    /**
     * Creates new form Order
     */
    Dashboard dashboard;

    public DefaultTableModel mainCategoryTb = null;
    public DefaultTableModel subCategoryTb = null;
    public DefaultTableModel allItemsTb = null;
    DefaultTableModel tableModelSelectedItems = null;
    DefaultTableModel tableModel = null;

    private MainTableLocationController mainTableLocationController;
    private SubTableLocationController subTableLocationController;
    private ConfigTabalesController configTabalesController;
    
    private MainItemCategoryController mainItemCategoryController;
    private SubItemCategoryController subItemCategoryController;
    private MainItemController mainItemController;
    private MainOrderController mainOrderController;
    private StockController stockController;
    
    ArrayList<JToggleButton> tableSeatButtonArray = new ArrayList<>();
    ArrayList<OrderDetailsDto> orderDetailsDtos=new ArrayList<>();
    HashMap<Integer, MainItemDto> uIItemMap = new HashMap<>();
    private POSConfigurationsobjects pOSConfigurationsobjects = null;
    
    Component keySelectedComponent_ = null;
    
    int selectedIdItemRegistry_ = 0;
    double selectedQuantity_ = 0.0;
    String selectedSeat = "T";
    int currentSelectedColumn = 0;
    int currentSelectedRow = 0;
    
    Double finalSubTotal=0.00;
    Double finalDiscount=10.00;
    Double finalGrandTotal=0.00;
    
    public Order(Dashboard dashboard) {
        initComponents();
        
        this.dashboard = dashboard;
        Dimension fullScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
       
        
        this.setSize(fullScreenSize);
        this.setLocationRelativeTo(null);
        
        this.mainTableLocationController=new MainTableLocationController();
        this.subTableLocationController=new SubTableLocationController();
        this.configTabalesController=new ConfigTabalesController();
        
        loadTAbleStracture();
        tn_btn_1.setSelected(true);
        tn_btn_1.setBackground(new Color(153, 0, 0));
        calculateTxt.setText("0");
        
        this.mainItemCategoryController = new MainItemCategoryController();
        this.subItemCategoryController = new SubItemCategoryController();
        this.mainItemController = new MainItemController();
        this.mainOrderController = new MainOrderController();
        this.stockController=new StockController();
        
        searchText.requestFocus();
        
        mainCategoryTb = (DefaultTableModel) mainCategoryTbl.getModel();
        subCategoryTb = (DefaultTableModel) subCategoryTbl.getModel();
        allItemsTb = (DefaultTableModel) itemsTbl.getModel();
        
        tableModel = (DefaultTableModel) orderDetailsTbl.getModel();

        orderDetailsTbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderDetailsTbl.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
//        getAllSubCategories();
        loadMainCategory();
        
         //Load POS UI
        OrderDetails.setMinimumSize(fullScreenSize);
        OrderDetails.setVisible(true);
        OrderDetails.setLocationRelativeTo(null);
        OrderDetails.repaint();
        
//        tableModelSelectedItems = (DefaultTableModel) orderDetailsTbl.getModel();
        

        
//        loadSubCategory();
//        loadMainItems();
        
        
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        qtyDialogBox = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        btn7 = new org.jdesktop.swingx.JXButton();
        btn8 = new org.jdesktop.swingx.JXButton();
        btn9 = new org.jdesktop.swingx.JXButton();
        calculateTxt = new org.jdesktop.swingx.JXTextField();
        btn4 = new org.jdesktop.swingx.JXButton();
        btn5 = new org.jdesktop.swingx.JXButton();
        btn6 = new org.jdesktop.swingx.JXButton();
        btn1 = new org.jdesktop.swingx.JXButton();
        btn2 = new org.jdesktop.swingx.JXButton();
        btn3 = new org.jdesktop.swingx.JXButton();
        resetBtn = new org.jdesktop.swingx.JXButton();
        jXButton13 = new org.jdesktop.swingx.JXButton();
        btn10 = new org.jdesktop.swingx.JXButton();
        btn11 = new org.jdesktop.swingx.JXButton();
        btn12 = new org.jdesktop.swingx.JXButton();
        OrderDetails = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jTablePane2 = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        mainCategoryTbl = new org.jdesktop.swingx.JXTable();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        subCategoryTbl = new org.jdesktop.swingx.JXTable();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        itemsTbl = new org.jdesktop.swingx.JXTable();
        jLabel16 = new javax.swing.JLabel();
        searchText = new javax.swing.JTextField();
        searchBtn = new org.jdesktop.swingx.JXButton();
        discountBtn = new org.jdesktop.swingx.JXButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        saveBtn = new org.jdesktop.swingx.JXButton();
        closeBtn = new org.jdesktop.swingx.JXButton();
        printBillBtn = new org.jdesktop.swingx.JXButton();
        optionBtn = new org.jdesktop.swingx.JXButton();
        cancelBtn = new org.jdesktop.swingx.JXButton();
        removeBtn = new org.jdesktop.swingx.JXButton();
        messageLbl = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton49 = new javax.swing.JButton();
        jTextField10 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jButton63 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        orderDetailsTbl = new org.jdesktop.swingx.JXTable();
        jPanel8 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        paymentBtn = new org.jdesktop.swingx.JXButton();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel41 = new javax.swing.JPanel();
        jPanel71 = new javax.swing.JPanel();
        jTextField35 = new javax.swing.JTextField();
        jButton48 = new javax.swing.JButton();
        jPanel52 = new javax.swing.JPanel();
        jToggleButton16 = new javax.swing.JToggleButton();
        jToggleButton17 = new javax.swing.JToggleButton();
        jToggleButton18 = new javax.swing.JToggleButton();
        jToggleButton19 = new javax.swing.JToggleButton();
        jButton50 = new javax.swing.JButton();
        jPanel55 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jButton51 = new javax.swing.JButton();
        jPanel57 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton53 = new javax.swing.JButton();
        jPanel58 = new javax.swing.JPanel();
        jButton54 = new javax.swing.JButton();
        jPanel60 = new javax.swing.JPanel();
        jButton56 = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jPanel59 = new javax.swing.JPanel();
        jToggleButton20 = new javax.swing.JToggleButton();
        jToggleButton21 = new javax.swing.JToggleButton();
        jToggleButton24 = new javax.swing.JToggleButton();
        jToggleButton25 = new javax.swing.JToggleButton();
        jToggleButton26 = new javax.swing.JToggleButton();
        jToggleButton27 = new javax.swing.JToggleButton();
        btnRedeem = new javax.swing.JToggleButton();
        jButton55 = new javax.swing.JButton();
        jPanel62 = new javax.swing.JPanel();
        jPanel63 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jButton73 = new javax.swing.JButton();
        jPanel64 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        jButton52 = new javax.swing.JButton();
        jPanel42 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jXTable5 = new org.jdesktop.swingx.JXTable();
        jButton58 = new javax.swing.JButton();
        manualBtn = new javax.swing.JToggleButton();
        remarkBtn = new javax.swing.JToggleButton();
        tn_btn_2 = new javax.swing.JToggleButton();
        tn_btn_1 = new javax.swing.JToggleButton();
        tn_btn_3 = new javax.swing.JToggleButton();
        tn_btn_4 = new javax.swing.JToggleButton();
        tn_btn_5 = new javax.swing.JToggleButton();
        tn_btn_6 = new javax.swing.JToggleButton();
        tn_btn_9 = new javax.swing.JToggleButton();
        tn_btn_8 = new javax.swing.JToggleButton();
        tn_btn_7 = new javax.swing.JToggleButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        subTotLbl = new javax.swing.JLabel();
        discLbl = new javax.swing.JLabel();
        serviceChaLbl = new javax.swing.JLabel();
        grandTotLbl = new javax.swing.JLabel();
        PaymentDetails = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jXButton9 = new org.jdesktop.swingx.JXButton();
        jXButton10 = new org.jdesktop.swingx.JXButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jXButton4 = new org.jdesktop.swingx.JXButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jXButton1 = new org.jdesktop.swingx.JXButton();
        jXButton2 = new org.jdesktop.swingx.JXButton();
        jXButton5 = new org.jdesktop.swingx.JXButton();
        jXButton6 = new org.jdesktop.swingx.JXButton();
        jXButton7 = new org.jdesktop.swingx.JXButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();

        qtyDialogBox.setTitle("Insert Value & Press Enter");

        qtyDialogBox.setAlwaysOnTop(true);

        qtyDialogBox.setMinimumSize(new java.awt.Dimension(300, 440));

        qtyDialogBox.setResizable(false);

        qtyDialogBox.setSize(new java.awt.Dimension(300, 440));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        btn7.setBackground(new java.awt.Color(0, 0, 0));
        btn7.setForeground(new java.awt.Color(255, 255, 255));
        btn7.setText("7");
        btn7.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btn8.setBackground(new java.awt.Color(0, 0, 0));
        btn8.setForeground(new java.awt.Color(255, 255, 255));
        btn8.setText("8");
        btn8.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn9.setBackground(new java.awt.Color(0, 0, 0));
        btn9.setForeground(new java.awt.Color(255, 255, 255));
        btn9.setText("9");
        btn9.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        calculateTxt.setBackground(new java.awt.Color(255, 255, 255));
        calculateTxt.setForeground(new java.awt.Color(0, 102, 153));
        calculateTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        calculateTxt.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        btn4.setBackground(new java.awt.Color(0, 0, 0));
        btn4.setForeground(new java.awt.Color(255, 255, 255));
        btn4.setText("4");
        btn4.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn5.setBackground(new java.awt.Color(0, 0, 0));
        btn5.setForeground(new java.awt.Color(255, 255, 255));
        btn5.setText("5");
        btn5.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setBackground(new java.awt.Color(0, 0, 0));
        btn6.setForeground(new java.awt.Color(255, 255, 255));
        btn6.setText("6");
        btn6.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btn1.setBackground(new java.awt.Color(0, 0, 0));
        btn1.setForeground(new java.awt.Color(255, 255, 255));
        btn1.setText("1");
        btn1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setBackground(new java.awt.Color(0, 0, 0));
        btn2.setForeground(new java.awt.Color(255, 255, 255));
        btn2.setText("2");
        btn2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn3.setBackground(new java.awt.Color(0, 0, 0));
        btn3.setForeground(new java.awt.Color(255, 255, 255));
        btn3.setText("3");
        btn3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        resetBtn.setBackground(new java.awt.Color(153, 0, 0));
        resetBtn.setForeground(new java.awt.Color(255, 255, 255));
        resetBtn.setText("Reset");
        resetBtn.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        jXButton13.setBackground(new java.awt.Color(0, 102, 153));
        jXButton13.setForeground(new java.awt.Color(255, 255, 255));
        jXButton13.setText("Enter");
        jXButton13.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jXButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXButton13ActionPerformed(evt);
            }
        });

        btn10.setBackground(new java.awt.Color(0, 0, 0));
        btn10.setForeground(new java.awt.Color(255, 255, 255));
        btn10.setText(".");
        btn10.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10ActionPerformed(evt);
            }
        });

        btn11.setBackground(new java.awt.Color(0, 0, 0));
        btn11.setForeground(new java.awt.Color(255, 255, 255));
        btn11.setText("0");
        btn11.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn11ActionPerformed(evt);
            }
        });

        btn12.setBackground(new java.awt.Color(0, 0, 0));
        btn12.setForeground(new java.awt.Color(255, 255, 255));
        btn12.setText("Dec");
        btn12.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calculateTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jXButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(calculateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn11, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn12, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout qtyDialogBoxLayout = new javax.swing.GroupLayout(qtyDialogBox.getContentPane());
        qtyDialogBox.getContentPane().setLayout(qtyDialogBoxLayout);
        qtyDialogBoxLayout.setHorizontalGroup(
            qtyDialogBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        qtyDialogBoxLayout.setVerticalGroup(
            qtyDialogBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qtyDialogBoxLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        OrderDetails.setUndecorated(true);
        OrderDetails.setMaximumSize(new java.awt.Dimension(1280, 768));
        OrderDetails.setModal(true);
        OrderDetails.setResizable(false);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setForeground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(0, 102, 153));

        jLabel6.setBackground(new java.awt.Color(0, 102, 102));
        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("POS");
        jLabel6.setOpaque(true);

        jLabel19.setBackground(new java.awt.Color(204, 204, 204));
        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("March / 31");

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("18.00 PM");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel15.setBackground(new java.awt.Color(204, 204, 204));
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("User Name");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jTablePane2.setBackground(new java.awt.Color(255, 255, 255));
        jTablePane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Table"));
        jTablePane2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTablePane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTablePane2.setDoubleBuffered(true);
        jTablePane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTablePane2KeyPressed(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setForeground(new java.awt.Color(0, 102, 153));
        jPanel14.setPreferredSize(new java.awt.Dimension(400, 300));

        mainCategoryTbl.setBackground(new java.awt.Color(255, 255, 255));
        mainCategoryTbl.setForeground(new java.awt.Color(0, 0, 0));
        mainCategoryTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mainCategoryTbl.setCellSelectionEnabled(true);
        mainCategoryTbl.setFont(new java.awt.Font("Tahoma", 0, 14));
        mainCategoryTbl.setRowHeight(65);
        mainCategoryTbl.setRowMargin(8);
        mainCategoryTbl.getTableHeader().setReorderingAllowed(false);
        mainCategoryTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainCategoryTblMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mainCategoryTblMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(mainCategoryTbl);
        mainCategoryTbl.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (mainCategoryTbl.getColumnModel().getColumnCount() > 0) {
            mainCategoryTbl.getColumnModel().getColumn(1).setMinWidth(0);
            mainCategoryTbl.getColumnModel().getColumn(1).setPreferredWidth(0);
            mainCategoryTbl.getColumnModel().getColumn(1).setMaxWidth(0);
            mainCategoryTbl.getColumnModel().getColumn(3).setMinWidth(0);
            mainCategoryTbl.getColumnModel().getColumn(3).setPreferredWidth(0);
            mainCategoryTbl.getColumnModel().getColumn(3).setMaxWidth(0);
            mainCategoryTbl.getColumnModel().getColumn(5).setMinWidth(0);
            mainCategoryTbl.getColumnModel().getColumn(5).setPreferredWidth(0);
            mainCategoryTbl.getColumnModel().getColumn(5).setMaxWidth(0);
            mainCategoryTbl.getColumnModel().getColumn(7).setMinWidth(0);
            mainCategoryTbl.getColumnModel().getColumn(7).setPreferredWidth(0);
            mainCategoryTbl.getColumnModel().getColumn(7).setMaxWidth(0);
            mainCategoryTbl.getColumnModel().getColumn(9).setMinWidth(0);
            mainCategoryTbl.getColumnModel().getColumn(9).setPreferredWidth(0);
            mainCategoryTbl.getColumnModel().getColumn(9).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
        );

        jTablePane2.addTab("Main Category", new javax.swing.ImageIcon(getClass().getResource("/net/unical/pos/imagers/inventory/mainItemCategory-35.png")), jPanel14); // NOI18N

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setForeground(new java.awt.Color(0, 102, 153));

        subCategoryTbl.setBackground(new java.awt.Color(255, 255, 255));
        subCategoryTbl.setForeground(new java.awt.Color(0, 0, 0));
        subCategoryTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        subCategoryTbl.setCellSelectionEnabled(true);
        subCategoryTbl.setFont(new java.awt.Font("Tahoma", 0, 14));
        subCategoryTbl.setRowHeight(65);
        subCategoryTbl.setRowMargin(8);
        subCategoryTbl.getTableHeader().setReorderingAllowed(false);
        subCategoryTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                subCategoryTblMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                subCategoryTblMouseReleased(evt);
            }
        });
        jScrollPane9.setViewportView(subCategoryTbl);
        subCategoryTbl.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (subCategoryTbl.getColumnModel().getColumnCount() > 0) {
            subCategoryTbl.getColumnModel().getColumn(1).setMinWidth(0);
            subCategoryTbl.getColumnModel().getColumn(1).setPreferredWidth(0);
            subCategoryTbl.getColumnModel().getColumn(1).setMaxWidth(0);
            subCategoryTbl.getColumnModel().getColumn(3).setMinWidth(0);
            subCategoryTbl.getColumnModel().getColumn(3).setPreferredWidth(0);
            subCategoryTbl.getColumnModel().getColumn(3).setMaxWidth(0);
            subCategoryTbl.getColumnModel().getColumn(5).setMinWidth(0);
            subCategoryTbl.getColumnModel().getColumn(5).setPreferredWidth(0);
            subCategoryTbl.getColumnModel().getColumn(5).setMaxWidth(0);
            subCategoryTbl.getColumnModel().getColumn(7).setMinWidth(0);
            subCategoryTbl.getColumnModel().getColumn(7).setPreferredWidth(0);
            subCategoryTbl.getColumnModel().getColumn(7).setMaxWidth(0);
            subCategoryTbl.getColumnModel().getColumn(9).setMinWidth(0);
            subCategoryTbl.getColumnModel().getColumn(9).setPreferredWidth(0);
            subCategoryTbl.getColumnModel().getColumn(9).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
        );

        jTablePane2.addTab("Sub Category", new javax.swing.ImageIcon(getClass().getResource("/net/unical/pos/imagers/inventory/subItemCategory-35.png")), jPanel15); // NOI18N

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setForeground(new java.awt.Color(0, 102, 153));

        itemsTbl.setBackground(new java.awt.Color(255, 255, 255));
        itemsTbl.setForeground(new java.awt.Color(0, 0, 0));
        itemsTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        itemsTbl.setCellSelectionEnabled(true);
        itemsTbl.setFont(new java.awt.Font("Tahoma", 0, 14));
        itemsTbl.setRowHeight(65);
        itemsTbl.setRowMargin(8);
        itemsTbl.getTableHeader().setReorderingAllowed(false);
        itemsTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                itemsTblMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                itemsTblMouseReleased(evt);
            }
        });
        jScrollPane11.setViewportView(itemsTbl);
        itemsTbl.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (itemsTbl.getColumnModel().getColumnCount() > 0) {
            itemsTbl.getColumnModel().getColumn(1).setMinWidth(0);
            itemsTbl.getColumnModel().getColumn(1).setPreferredWidth(0);
            itemsTbl.getColumnModel().getColumn(1).setMaxWidth(0);
            itemsTbl.getColumnModel().getColumn(3).setMinWidth(0);
            itemsTbl.getColumnModel().getColumn(3).setPreferredWidth(0);
            itemsTbl.getColumnModel().getColumn(3).setMaxWidth(0);
            itemsTbl.getColumnModel().getColumn(5).setMinWidth(0);
            itemsTbl.getColumnModel().getColumn(5).setPreferredWidth(0);
            itemsTbl.getColumnModel().getColumn(5).setMaxWidth(0);
            itemsTbl.getColumnModel().getColumn(7).setMinWidth(0);
            itemsTbl.getColumnModel().getColumn(7).setPreferredWidth(0);
            itemsTbl.getColumnModel().getColumn(7).setMaxWidth(0);
            itemsTbl.getColumnModel().getColumn(9).setMinWidth(0);
            itemsTbl.getColumnModel().getColumn(9).setPreferredWidth(0);
            itemsTbl.getColumnModel().getColumn(9).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
        );

        jTablePane2.addTab("Items", new javax.swing.ImageIcon(getClass().getResource("/net/unical/pos/imagers/inventory/mainItems-35.png")), jPanel16); // NOI18N

        jTablePane2.setSelectedIndex(2);

        jLabel16.setForeground(new java.awt.Color(0, 102, 153));
        jLabel16.setText("Search Item");

        searchText.setBackground(new java.awt.Color(255, 255, 255));
        searchText.setForeground(new java.awt.Color(0, 0, 0));

        searchBtn.setBackground(new java.awt.Color(0, 102, 153));
        searchBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        discountBtn.setBackground(new java.awt.Color(0, 102, 153));
        discountBtn.setForeground(new java.awt.Color(255, 255, 255));
        discountBtn.setText("Discount");

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "10%", "20%", "50%" }));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTablePane2)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(discountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(discountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTablePane2))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        saveBtn.setBackground(new java.awt.Color(0, 102, 153));
        saveBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        closeBtn.setBackground(new java.awt.Color(153, 0, 0));
        closeBtn.setForeground(new java.awt.Color(255, 255, 255));
        closeBtn.setText("Close");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        printBillBtn.setBackground(new java.awt.Color(0, 102, 153));
        printBillBtn.setForeground(new java.awt.Color(255, 255, 255));
        printBillBtn.setText("Print Bill");

        optionBtn.setBackground(new java.awt.Color(51, 51, 51));
        optionBtn.setForeground(new java.awt.Color(255, 255, 255));
        optionBtn.setText("Options");

        cancelBtn.setBackground(new java.awt.Color(204, 204, 0));
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.setText("Cancel Order");

        removeBtn.setBackground(new java.awt.Color(0, 0, 0));
        removeBtn.setForeground(new java.awt.Color(255, 255, 255));
        removeBtn.setText("Remove Item");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        messageLbl.setForeground(new java.awt.Color(204, 0, 0));
        messageLbl.setText("Message ");

        jPanel39.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Details"));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Customer");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Name");

        jTextField9.setBackground(new java.awt.Color(255, 255, 204));
        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField9KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField9KeyTyped(evt);
            }
        });

        jButton49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton49.setForeground(new java.awt.Color(255, 255, 255));
        jButton49.setText("Search");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        jTextField10.setEditable(false);
        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Total Points");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Avilable Points");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("00.00");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("00.00");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel43.setText("History Credit");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText("00.00");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setText("Credit Limit");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel46.setText("00.00");

        jButton63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton63.setForeground(new java.awt.Color(255, 255, 255));
        jButton63.setText("Remove");
        jButton63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton63ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel39Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel39Layout.createSequentialGroup()
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel39Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29))
                                .addGap(62, 62, 62)
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(67, 67, 67))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jButton63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton49))
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton63, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(printBillBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(removeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(optionBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(closeBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(messageLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(messageLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printBillBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(optionBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        orderDetailsTbl.setBackground(new java.awt.Color(255, 255, 255));
        orderDetailsTbl.setForeground(new java.awt.Color(0, 0, 0));
        orderDetailsTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Name", "Qty", "Price", ""
            }
        ));
        orderDetailsTbl.setCellSelectionEnabled(true);

        orderDetailsTbl.setIntercellSpacing(new java.awt.Dimension(1, 5));

        orderDetailsTbl.setRowHeight(38);

        orderDetailsTbl.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(orderDetailsTbl);
        if (orderDetailsTbl.getColumnModel().getColumnCount() > 0) {
            orderDetailsTbl.getColumnModel().getColumn(0).setMinWidth(0);
            orderDetailsTbl.getColumnModel().getColumn(0).setPreferredWidth(0);
            orderDetailsTbl.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jButton10.setText("C");

        jButton11.setText("0");

        jButton12.setText(".");

        paymentBtn.setBackground(new java.awt.Color(0, 153, 0));
        paymentBtn.setForeground(new java.awt.Color(255, 255, 255));
        paymentBtn.setText("Pay");
        paymentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentBtnActionPerformed(evt);
            }
        });

        jTabbedPane4.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPane4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane4StateChanged(evt);
            }
        });

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));

        jPanel71.setBackground(new java.awt.Color(255, 255, 255));

        jTextField35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField35.setForeground(new java.awt.Color(0, 102, 0));
        jTextField35.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField35.setText("0.00");
        jTextField35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField35MousePressed(evt);
            }
        });
        jTextField35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField35ActionPerformed(evt);
            }
        });
        jTextField35.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField35KeyReleased(evt);
            }
        });

        jButton48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton48.setForeground(new java.awt.Color(255, 255, 255));
        jButton48.setText("Add");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
        jPanel71.setLayout(jPanel71Layout);
        jPanel71Layout.setHorizontalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel71Layout.setVerticalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jPanel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(59, 59, 59))
        );

        jTabbedPane4.addTab("Cash", jPanel41);

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));

        jToggleButton16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jToggleButton16.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton16.setText("Visa");
        jToggleButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton16ActionPerformed(evt);
            }
        });

        jToggleButton17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jToggleButton17.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton17.setText("Master");
        jToggleButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton17ActionPerformed(evt);
            }
        });

        jToggleButton18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jToggleButton18.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton18.setText("AMEX");
        jToggleButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton18ActionPerformed(evt);
            }
        });

        jToggleButton19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jToggleButton19.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton19.setText("Other");
        jToggleButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton19ActionPerformed(evt);
            }
        });

        jButton50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton50.setForeground(new java.awt.Color(255, 255, 255));
        jButton50.setText("Add");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jToggleButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(jToggleButton16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Card", jPanel52);

        jPanel55.setBackground(new java.awt.Color(255, 255, 255));

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel50.setText("Chq. #");

        jTextField14.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jTextField14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField14MousePressed(evt);
            }
        });

        jButton51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton51.setForeground(new java.awt.Color(255, 255, 255));
        jButton51.setText("Add");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel55Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Cheque", jPanel55);

        jPanel57.setBackground(new java.awt.Color(255, 255, 255));

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel54.setText("Employee");

        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton53.setForeground(new java.awt.Color(255, 255, 255));
        jButton53.setText("Add");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Duty Meal / Other", jPanel57);

        jPanel58.setBackground(new java.awt.Color(255, 255, 255));

        jButton54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton54.setForeground(new java.awt.Color(255, 255, 255));
        jButton54.setText("Add");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel58Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Loyalty", jPanel58);

        jPanel60.setBackground(new java.awt.Color(255, 255, 255));

        jButton56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton56.setForeground(new java.awt.Color(255, 255, 255));
        jButton56.setText("Add");
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel58.setText("Vou. #");

        jTextField20.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jTextField20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField20MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Voucher", jPanel60);

        jPanel59.setBackground(new java.awt.Color(255, 255, 255));

        jToggleButton20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jToggleButton20.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton20.setText("Paypal");
        jToggleButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton20ActionPerformed(evt);
            }
        });

        jToggleButton21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jToggleButton21.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton21.setText("Ez Cash");
        jToggleButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton21ActionPerformed(evt);
            }
        });

        jToggleButton24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jToggleButton24.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton24.setText("NFC");
        jToggleButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton24ActionPerformed(evt);
            }
        });

        jToggleButton25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jToggleButton25.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton25.setText("FriMi");
        jToggleButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton25ActionPerformed(evt);
            }
        });

        jToggleButton26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jToggleButton26.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton26.setText("SOLO");
        jToggleButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton26ActionPerformed(evt);
            }
        });

        jToggleButton27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jToggleButton27.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton27.setText("Genie");
        jToggleButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton27ActionPerformed(evt);
            }
        });

        btnRedeem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRedeem.setForeground(new java.awt.Color(255, 255, 255));
        btnRedeem.setText("Redeem");
        btnRedeem.setMaximumSize(new java.awt.Dimension(87, 25));
        btnRedeem.setMinimumSize(new java.awt.Dimension(87, 25));
        btnRedeem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedeemActionPerformed(evt);
            }
        });

        jButton55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton55.setForeground(new java.awt.Color(255, 255, 255));
        jButton55.setText("Add");
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jToggleButton25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRedeem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(jToggleButton21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel59Layout.createSequentialGroup()
                        .addComponent(jToggleButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToggleButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRedeem, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Electronic", jPanel59);

        jPanel62.setBackground(new java.awt.Color(255, 255, 255));

        jPanel63.setBackground(new java.awt.Color(255, 255, 255));

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel77.setText("Refund Type");

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel78.setText("Remark");

        jComboBox7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane15.setViewportView(jTextArea5);

        jButton73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton73.setForeground(new java.awt.Color(255, 255, 255));
        jButton73.setText("Add");
        jButton73.setEnabled(false);
        jButton73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton73ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel63Layout.createSequentialGroup()
                        .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel77)
                            .addComponent(jLabel78))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                            .addComponent(jComboBox7, 0, 340, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel63Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton73, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel78)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton73, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel62Layout.createSequentialGroup()
                .addComponent(jPanel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(53, 53, 53))
        );

        jTabbedPane4.addTab("Refund", jPanel62);

        jPanel64.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("D / D", jPanel64);

        jPanel56.setBackground(new java.awt.Color(255, 255, 255));

        jButton52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton52.setForeground(new java.awt.Color(255, 255, 255));
        jButton52.setText("Add");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Credit", jPanel56);

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));
        jPanel42.setBorder(javax.swing.BorderFactory.createTitledBorder("Payment Details"));

        jXTable5.setBackground(new java.awt.Color(255, 255, 255));
        jXTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Amount", "Type", "Remark", "PAYMENT_TYPE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jXTable5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jXTable5.setRowHeight(32);
        jXTable5.getTableHeader().setReorderingAllowed(false);
        jScrollPane10.setViewportView(jXTable5);

        jButton58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton58.setForeground(new java.awt.Color(255, 255, 255));
        jButton58.setText("Remove");
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addComponent(jButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        manualBtn.setBackground(new java.awt.Color(0, 102, 153));
        manualBtn.setForeground(new java.awt.Color(255, 255, 255));
        manualBtn.setText("Manual");
        manualBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualBtnActionPerformed(evt);
            }
        });

        remarkBtn.setBackground(new java.awt.Color(0, 102, 153));
        remarkBtn.setForeground(new java.awt.Color(255, 255, 255));
        remarkBtn.setText("Remark");
        remarkBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remarkBtnActionPerformed(evt);
            }
        });

        tn_btn_2.setText("2");

        tn_btn_1.setText("1");
        tn_btn_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tn_btn_1ActionPerformed(evt);
            }
        });

        tn_btn_3.setText("3");

        tn_btn_4.setText("4");

        tn_btn_5.setText("5");

        tn_btn_6.setText("6");

        tn_btn_9.setText("9");

        tn_btn_8.setText("8");

        tn_btn_7.setText("7");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(tn_btn_1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tn_btn_2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tn_btn_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(tn_btn_7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tn_btn_8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tn_btn_9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(tn_btn_4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tn_btn_5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tn_btn_6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paymentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manualBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(remarkBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(manualBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(remarkBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tn_btn_1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tn_btn_2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tn_btn_3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tn_btn_4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tn_btn_5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tn_btn_6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tn_btn_9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tn_btn_8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tn_btn_7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(paymentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(17, 17, 17))
                        .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Bill Details"));

        jLabel7.setText("Sub Total : ");

        jLabel8.setText("Discount :");

        jLabel9.setText("Service Charge :");

        jLabel10.setText("Grand Total :");

        subTotLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        subTotLbl.setText("0.00");

        discLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        discLbl.setText("0.00");

        serviceChaLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        serviceChaLbl.setText("0.00");

        grandTotLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grandTotLbl.setText("0.00");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subTotLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serviceChaLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grandTotLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(subTotLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serviceChaLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grandTotLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout OrderDetailsLayout = new javax.swing.GroupLayout(OrderDetails.getContentPane());
        OrderDetails.getContentPane().setLayout(OrderDetailsLayout);
        OrderDetailsLayout.setHorizontalGroup(
            OrderDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        OrderDetailsLayout.setVerticalGroup(
            OrderDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 768, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel21.setBackground(new java.awt.Color(0, 102, 153));

        jLabel12.setBackground(new java.awt.Color(0, 102, 102));
        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("UNICAL");
        jLabel12.setOpaque(true);

        jLabel23.setBackground(new java.awt.Color(204, 204, 204));
        jLabel23.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("March / 31");

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("18.00 PM");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel25.setBackground(new java.awt.Color(204, 204, 204));
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("User Name");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 353, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Payment Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 102, 153))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bill Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 102, 153))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );

        jXButton9.setBackground(new java.awt.Color(204, 0, 0));
        jXButton9.setForeground(new java.awt.Color(255, 255, 255));
        jXButton9.setText("Close");
        jXButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXButton9ActionPerformed(evt);
            }
        });

        jXButton10.setBackground(new java.awt.Color(0, 102, 153));
        jXButton10.setForeground(new java.awt.Color(255, 255, 255));
        jXButton10.setText("Proceed");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 341, Short.MAX_VALUE)
                        .addComponent(jXButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PaymentDetailsLayout = new javax.swing.GroupLayout(PaymentDetails.getContentPane());
        PaymentDetails.getContentPane().setLayout(PaymentDetailsLayout);
        PaymentDetailsLayout.setHorizontalGroup(
            PaymentDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PaymentDetailsLayout.setVerticalGroup(
            PaymentDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel20.setBackground(new java.awt.Color(0, 102, 153));

        jLabel11.setBackground(new java.awt.Color(0, 102, 102));
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("UNICAL");
        jLabel11.setOpaque(true);

        jXButton4.setBackground(new java.awt.Color(204, 0, 0));
        jXButton4.setForeground(new java.awt.Color(255, 255, 255));
        jXButton4.setText("Sign Out");
        jXButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXButton4ActionPerformed(evt);
            }
        });

        jLabel21.setBackground(new java.awt.Color(204, 204, 204));
        jLabel21.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("March / 31");

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("18.00 PM");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel17.setBackground(new java.awt.Color(204, 204, 204));
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("User Name");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jXButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Table Reservation", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 102, 153))); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jXButton1.setBackground(new java.awt.Color(0, 102, 153));
        jXButton1.setForeground(new java.awt.Color(255, 255, 255));
        jXButton1.setText("Dine In");
        jXButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXButton1ActionPerformed(evt);
            }
        });

        jXButton2.setBackground(new java.awt.Color(0, 102, 153));
        jXButton2.setForeground(new java.awt.Color(255, 255, 255));
        jXButton2.setText("Take Away");
        jXButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXButton2ActionPerformed(evt);
            }
        });

        jXButton5.setBackground(new java.awt.Color(0, 102, 153));
        jXButton5.setForeground(new java.awt.Color(255, 255, 255));
        jXButton5.setText("Online Orders");

        jXButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/unical/pos/imagers/buttons/Order/uberEats.png"))); // NOI18N

        jXButton7.setBackground(new java.awt.Color(0, 102, 153));
        jXButton7.setForeground(new java.awt.Color(255, 255, 255));
        jXButton7.setText("Previous Orders");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jXButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jXButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jXButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jXButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(jTabbedPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jXButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jXButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jXButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "On Going Orders", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 102, 153))); // NOI18N

        jXTable1.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane1.setViewportView(jXTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed

        if (calculateTxt.getText().equals("0")) {
            calculateTxt.setText("");
            calculateTxt.setText(calculateTxt.getText() + "7");
        } else {
            calculateTxt.setText(calculateTxt.getText() + "7");
        }

    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        if (calculateTxt.getText().equals("0")) {
            calculateTxt.setText("");
            calculateTxt.setText(calculateTxt.getText() + "8");
        } else {
            calculateTxt.setText(calculateTxt.getText() + "8");
        }
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        if (calculateTxt.getText().equals("0")) {
            calculateTxt.setText("");
            calculateTxt.setText(calculateTxt.getText() + "9");
        } else {
            calculateTxt.setText(calculateTxt.getText() + "9");
        }
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        if (calculateTxt.getText().equals("0")) {
            calculateTxt.setText("");
            calculateTxt.setText(calculateTxt.getText() + "4");
        } else {
            calculateTxt.setText(calculateTxt.getText() + "4");
        }
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        if (calculateTxt.getText().equals("0")) {
            calculateTxt.setText("");
            calculateTxt.setText(calculateTxt.getText() + "5");
        } else {
            calculateTxt.setText(calculateTxt.getText() + "5");
        }
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        if (calculateTxt.getText().equals("0")) {
            calculateTxt.setText("");
            calculateTxt.setText(calculateTxt.getText() + "6");
        } else {
            calculateTxt.setText(calculateTxt.getText() + "6");
        }
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        if (calculateTxt.getText().equals("0")) {
            calculateTxt.setText("");
            calculateTxt.setText(calculateTxt.getText() + "1");
        } else {
            calculateTxt.setText(calculateTxt.getText() + "1");
        }
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        if (calculateTxt.getText().equals("0")) {
            calculateTxt.setText("");
            calculateTxt.setText(calculateTxt.getText() + "2");
        } else {
            calculateTxt.setText(calculateTxt.getText() + "2");
        }
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        if (calculateTxt.getText().equals("0")) {
            calculateTxt.setText("");
            calculateTxt.setText(calculateTxt.getText() + "3");
        } else {
            calculateTxt.setText(calculateTxt.getText() + "3");
        }
    }//GEN-LAST:event_btn3ActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        calculateTxt.setText("0");
    }//GEN-LAST:event_resetBtnActionPerformed

    private void btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn10ActionPerformed

    private void btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn11ActionPerformed

    private void btn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn12ActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBtnActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        if (orderDetailsTbl.getSelectedRowCount() == 1) {
            int selectedRow = orderDetailsTbl.getSelectedRow();
            int mapRowValue = (int) orderDetailsTbl.getValueAt(selectedRow, 3); // map aginst row
            orderDetailsTbl.remove(mapRowValue);
        }
    }//GEN-LAST:event_removeBtnActionPerformed

    private void manualBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualBtnActionPerformed
        
    }//GEN-LAST:event_manualBtnActionPerformed

    private void remarkBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remarkBtnActionPerformed
        
    }//GEN-LAST:event_remarkBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        OrderDetails.dispose();
        Sales sales=new Sales(dashboard);
        sales.repaint();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void mainCategoryTblMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainCategoryTblMousePressed
        try {
            int selectedColumn = mainCategoryTbl.getSelectedColumn();
            int selectedRow = mainCategoryTbl.getSelectedRow();
            Object val = mainCategoryTbl.getValueAt(selectedRow, (selectedColumn + 1));
            if (val != null) {
                loadSubCategory((int) val);
                jTablePane2.setSelectedIndex(1);
            }
        } catch (Exception e) {
            // if error keep silent - no action
            //Logger.getLogger(POSMain_.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_mainCategoryTblMousePressed

    private void jXButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXButton1ActionPerformed
        
    }//GEN-LAST:event_jXButton1ActionPerformed

    private void jXButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXButton2ActionPerformed
        OrderDetails.setVisible(true);
        OrderDetails.setLocationRelativeTo(null);
        OrderDetails.repaint();
    }//GEN-LAST:event_jXButton2ActionPerformed

    private void jXButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXButton13ActionPerformed
        if (keySelectedComponent_ instanceof JTextField) {
            JTextField selectedTextfield = (JTextField) keySelectedComponent_;
            selectedTextfield.setText(calculateTxt.getText());
        } else if (keySelectedComponent_ instanceof JTextArea) {
            JTextArea selectedTextField = (JTextArea) keySelectedComponent_;
            selectedTextField.setText(calculateTxt.getText());
        } else if (Double.parseDouble(calculateTxt.getText()) > 0) {
            double tempQTY = Double.parseDouble(calculateTxt.getText().trim());
            selectedQuantity_ = tempQTY;
            // normally add item to bill 
            addItemToBill(selectedIdItemRegistry_, false, null, 0, 0);
        }
        selectedIdItemRegistry_ = 0;
        keySelectedComponent_ = null;
        calculateTxt.setText(null);
        qtyDialogBox.setVisible(false);
        OrderDetails.repaint();
    }//GEN-LAST:event_jXButton13ActionPerformed

    private void jTablePane2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePane2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTablePane2KeyPressed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try {
            int tableRows = orderDetailsTbl.getRowCount();
            
            if(tableRows!=0){
                long now =System.currentTimeMillis();
                Date sqlDate = new Date(now);

                OrderDto orderDto=new OrderDto();
                ArrayList<OrderDetailsDto> orderDetailsDtos=new ArrayList<>();

                orderDto.setOrderId(0);
                orderDto.setBillNo("");
                orderDto.setDiscountId(1);
                orderDto.setSubTotalPrice(Double.parseDouble(subTotLbl.getText()));
                orderDto.setTotalDiscountPrice(Double.parseDouble(discLbl.getText()));
                orderDto.setTotalOrderPrice(Double.parseDouble(grandTotLbl.getText()));
                orderDto.setPaymentType(1);
                orderDto.setTableNo(1);
                orderDto.setCreatedDate(sqlDate);
                orderDto.setEditedDate(sqlDate);
                orderDto.setAdult(1);
                orderDto.setChild(0);
                orderDto.setRemark("");
                orderDto.setUserId(dashboard.CREATED_USER_ID);
                orderDto.setEditedBy(dashboard.CREATED_USER_ID);
                orderDto.setStatus(1);
                orderDto.setVisible(1);


                for (int i = 0; tableRows > i; i++) {
                    OrderDetailsDto orderDetailsDto=new OrderDetailsDto();
                    orderDetailsDto.setOrderDetailsId(0);
                    orderDetailsDto.setOrderId(0);
                    orderDetailsDto.setItemId((Integer) orderDetailsTbl.getValueAt(i, 0));
                    orderDetailsDto.setItemBarCode(0);
                    orderDetailsDto.setUnitTypeId(1);
                    orderDetailsDto.setPrinterTypeId(1);
                    orderDetailsDto.setQty((Double) orderDetailsTbl.getValueAt(i, 2));
                    orderDetailsDto.setPerItemPrice((Double) orderDetailsTbl.getValueAt(i, 3));
                    orderDetailsDto.setTotalDiscountPrice(0.00);
                    orderDetailsDto.setTotalItemPrice((Double)orderDetailsTbl.getValueAt(i, 2)*
                            (Double)orderDetailsTbl.getValueAt(i, 3));
                    orderDetailsDto.setRemark("");
                    orderDetailsDto.setStatus(1);
                    orderDetailsDto.setUserId(dashboard.CREATED_USER_ID);
                    orderDetailsDtos.add(orderDetailsDto);

                }

                MainOrderDto mainOrderDto=new MainOrderDto(orderDto, orderDetailsDtos);

                Integer orderId=mainOrderController.saveMainOrder(mainOrderDto);
                
                printInvoice(orderId);
                
                if(orderId!=0){
                    JOptionPane.showMessageDialog(this, "Order Save Sauccessfull");
                    OrderDetails.dispose();
                    Sales sales=new Sales(dashboard);
                    sales.repaint();
                }else{
                    JOptionPane.showMessageDialog(this, "Order Save Fail");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Please Select items first");
            }
        } catch (Exception ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_saveBtnActionPerformed

    private void paymentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentBtnActionPerformed
        PaymentDetails.setVisible(true);
        OrderDetails.setVisible(false);
    }//GEN-LAST:event_paymentBtnActionPerformed

    private void jXButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXButton9ActionPerformed
        PaymentDetails.dispose();
        Order order=new Order(dashboard);
        order.OrderDetails.setVisible(true);
        OrderDetails.repaint();
    }//GEN-LAST:event_jXButton9ActionPerformed

    private void jXButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXButton4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jXButton4ActionPerformed

    private void mainCategoryTblMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainCategoryTblMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_mainCategoryTblMouseReleased

    private void jTextField35MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField35MousePressed

    }//GEN-LAST:event_jTextField35MousePressed

    private void jTextField35KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField35KeyReleased

    }//GEN-LAST:event_jTextField35KeyReleased

    private void jToggleButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton16ActionPerformed
        
    }//GEN-LAST:event_jToggleButton16ActionPerformed

    private void jToggleButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton17ActionPerformed
        
    }//GEN-LAST:event_jToggleButton17ActionPerformed

    private void jToggleButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton18ActionPerformed
        
    }//GEN-LAST:event_jToggleButton18ActionPerformed

    private void jToggleButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton19ActionPerformed
        
    }//GEN-LAST:event_jToggleButton19ActionPerformed

    private void jTextField14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField14MousePressed

    }//GEN-LAST:event_jTextField14MousePressed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed

       

    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
     
    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed

    }//GEN-LAST:event_jButton56ActionPerformed

    private void jTextField20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField20MousePressed

    }//GEN-LAST:event_jTextField20MousePressed

    private void jToggleButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton21ActionPerformed
        
    }//GEN-LAST:event_jToggleButton21ActionPerformed

    private void jToggleButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton25ActionPerformed
        
    }//GEN-LAST:event_jToggleButton25ActionPerformed

    private void btnRedeemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedeemActionPerformed
        
    }//GEN-LAST:event_btnRedeemActionPerformed

    private void jButton73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton73ActionPerformed

    }//GEN-LAST:event_jButton73ActionPerformed

    private void jTabbedPane4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane4StateChanged
        if (jTabbedPane4.getSelectedIndex() == 0) {
            jLabel18.setText("CASH");
        } else if (jTabbedPane4.getSelectedIndex() == 1) {
            jLabel18.setText("CARD");
        } else if (jTabbedPane4.getSelectedIndex() == 2) {
            jLabel18.setText("CHEQUE");
        } else if (jTabbedPane4.getSelectedIndex() == 3) {
            jLabel18.setText("CREDIT");
        } else if (jTabbedPane4.getSelectedIndex() == 4) {
            jLabel18.setText("DUTY");
        } else if (jTabbedPane4.getSelectedIndex() == 5) {
            jLabel18.setText("LOYALTY");
        } else if (jTabbedPane4.getSelectedIndex() == 6) {
            jLabel18.setText("VOUCHER");
        } else if (jTabbedPane4.getSelectedIndex() == 7) {
            jLabel18.setText("ELECTRONIC");
        } else if (jTabbedPane4.getSelectedIndex() == 8) {
            jLabel18.setText("REFUND");
        } else if (jTabbedPane4.getSelectedIndex() == 9) {
            jLabel18.setText("DIRECT DEBIT");
        }

    }//GEN-LAST:event_jTabbedPane4StateChanged

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton55ActionPerformed

    private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
       
    }//GEN-LAST:event_jButton58ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased
       
    }//GEN-LAST:event_jTextField9KeyReleased

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9KeyTyped

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
       
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton63ActionPerformed
       
    }//GEN-LAST:event_jButton63ActionPerformed

    private void jToggleButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton20ActionPerformed

    }//GEN-LAST:event_jToggleButton20ActionPerformed

    private void jToggleButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton26ActionPerformed

    }//GEN-LAST:event_jToggleButton26ActionPerformed

    private void jToggleButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton27ActionPerformed

    }//GEN-LAST:event_jToggleButton27ActionPerformed

    private void jToggleButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton24ActionPerformed

    }//GEN-LAST:event_jToggleButton24ActionPerformed

    private void jTextField35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField35ActionPerformed

    private void subCategoryTblMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subCategoryTblMousePressed
        try {
            int selectedColumn = subCategoryTbl.getSelectedColumn();
            int selectedRow = subCategoryTbl.getSelectedRow();
            Object val = subCategoryTbl.getValueAt(selectedRow, (selectedColumn + 1));
            if (val != null) {
                loadMainItems((int) val);
                jTablePane2.setSelectedIndex(2);
            }
        } catch (Exception e) {
            // if error keep silent - no action
            //Logger.getLogger(POSMain_.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_subCategoryTblMousePressed

    private void subCategoryTblMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subCategoryTblMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_subCategoryTblMouseReleased

    private void itemsTblMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsTblMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemsTblMousePressed

    private void itemsTblMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsTblMouseReleased
        try {
            double selectedQTY = checkQtyToggleButton();
            currentSelectedColumn = itemsTbl.getSelectedColumn();
            currentSelectedRow = itemsTbl.getSelectedRow();
            
            Object itemValuesTbl = itemsTbl.getValueAt(currentSelectedRow, (currentSelectedColumn + 1));
            Double availableQty=checkItemAvailability((Integer)itemValuesTbl);
//            if(availableQty!=0.00){
                

                ArrayList<Integer>itemIds=new ArrayList<>();

                DefaultTableModel dtm = (DefaultTableModel) orderDetailsTbl.getModel();


                ArrayList<MainItemDto> mainItemDtos = mainItemController.searchAllItems((Integer) itemValuesTbl);

                Double finSubTot=0.00;
                Double subTotal=0.00;
                Double discount=10.00;

                int tableRows = orderDetailsTbl.getRowCount();
                for(MainItemDto dto: mainItemDtos){   

                    itemIds.add(dto.getItemId());

                    subTotal=dto.getUnitPrice()*selectedQTY;
                    finSubTot+=finSubTot+subTotal;

                    finalSubTotal+=finSubTot;

                    finalDiscount=finalSubTotal*discount/100; 
                    finalGrandTotal=finalSubTotal-finalDiscount;


                    Integer isNotEqual=0;
                    
                    System.out.println("tableRows : "+tableRows);
                    for(int i = 0; tableRows > i; i++){
                        if(orderDetailsTbl.getValueAt(i, 0).equals(dto.getItemId())){
                            Double qty=(Double) orderDetailsTbl.getValueAt(i, 2);
                            qty=qty+selectedQTY;
                            tableModel.setValueAt(qty, i, 2);
                            isNotEqual=0;
                            break;
                        }else{
                            isNotEqual=1;
                        }
                    }

                    if(tableRows==0){
                        Object[] rowData = {
                            dto.getItemId(),
                            dto.getItemName(),
                            selectedQTY,
                            dto.getUnitPrice() * selectedQTY,
                            dto.getImagePath()
                        };
                        dtm.addRow(rowData);
                    }else if(isNotEqual==1){
                        Object[] rowData = {
                            dto.getItemId(),
                            dto.getItemName(),
                            selectedQTY,
                            dto.getUnitPrice() * selectedQTY,
                            dto.getImagePath()
                        };
                        dtm.addRow(rowData);
                    }


                    subTotLbl.setText(finalSubTotal.toString());
                    discLbl.setText(finalDiscount.toString());
                    grandTotLbl.setText(finalGrandTotal.toString());
                }
//            }else{
//                messageLbl.setText("Not available in stock");
//            }
            
            
            itemsTbl.getSelectionModel().clearSelection();
            
            
        } catch (Exception ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itemsTblMouseReleased

    private void tn_btn_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tn_btn_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tn_btn_1ActionPerformed

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
//            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Order().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDialog OrderDetails;
    private javax.swing.JDialog PaymentDetails;
    private org.jdesktop.swingx.JXButton btn1;
    private org.jdesktop.swingx.JXButton btn10;
    private org.jdesktop.swingx.JXButton btn11;
    private org.jdesktop.swingx.JXButton btn12;
    private org.jdesktop.swingx.JXButton btn2;
    private org.jdesktop.swingx.JXButton btn3;
    private org.jdesktop.swingx.JXButton btn4;
    private org.jdesktop.swingx.JXButton btn5;
    private org.jdesktop.swingx.JXButton btn6;
    private org.jdesktop.swingx.JXButton btn7;
    private org.jdesktop.swingx.JXButton btn8;
    private org.jdesktop.swingx.JXButton btn9;
    private javax.swing.JToggleButton btnRedeem;
    private org.jdesktop.swingx.JXTextField calculateTxt;
    private org.jdesktop.swingx.JXButton cancelBtn;
    private org.jdesktop.swingx.JXButton closeBtn;
    private javax.swing.JLabel discLbl;
    private org.jdesktop.swingx.JXButton discountBtn;
    private javax.swing.JLabel grandTotLbl;
    public org.jdesktop.swingx.JXTable itemsTbl;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton48;
    public javax.swing.JButton jButton49;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton58;
    public javax.swing.JButton jButton63;
    private javax.swing.JButton jButton73;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTablePane2;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField35;
    public javax.swing.JTextField jTextField9;
    private javax.swing.JToggleButton jToggleButton16;
    private javax.swing.JToggleButton jToggleButton17;
    private javax.swing.JToggleButton jToggleButton18;
    private javax.swing.JToggleButton jToggleButton19;
    private javax.swing.JToggleButton jToggleButton20;
    private javax.swing.JToggleButton jToggleButton21;
    private javax.swing.JToggleButton jToggleButton24;
    private javax.swing.JToggleButton jToggleButton25;
    private javax.swing.JToggleButton jToggleButton26;
    private javax.swing.JToggleButton jToggleButton27;
    private org.jdesktop.swingx.JXButton jXButton1;
    private org.jdesktop.swingx.JXButton jXButton10;
    private org.jdesktop.swingx.JXButton jXButton13;
    private org.jdesktop.swingx.JXButton jXButton2;
    private org.jdesktop.swingx.JXButton jXButton4;
    private org.jdesktop.swingx.JXButton jXButton5;
    private org.jdesktop.swingx.JXButton jXButton6;
    private org.jdesktop.swingx.JXButton jXButton7;
    private org.jdesktop.swingx.JXButton jXButton9;
    private org.jdesktop.swingx.JXTable jXTable1;
    private org.jdesktop.swingx.JXTable jXTable5;
    public org.jdesktop.swingx.JXTable mainCategoryTbl;
    private javax.swing.JToggleButton manualBtn;
    private javax.swing.JLabel messageLbl;
    private org.jdesktop.swingx.JXButton optionBtn;
    private org.jdesktop.swingx.JXTable orderDetailsTbl;
    private org.jdesktop.swingx.JXButton paymentBtn;
    private org.jdesktop.swingx.JXButton printBillBtn;
    private javax.swing.JDialog qtyDialogBox;
    private javax.swing.JToggleButton remarkBtn;
    private org.jdesktop.swingx.JXButton removeBtn;
    private org.jdesktop.swingx.JXButton resetBtn;
    private org.jdesktop.swingx.JXButton saveBtn;
    private org.jdesktop.swingx.JXButton searchBtn;
    public static javax.swing.JTextField searchText;
    private javax.swing.JLabel serviceChaLbl;
    public org.jdesktop.swingx.JXTable subCategoryTbl;
    private javax.swing.JLabel subTotLbl;
    private javax.swing.JToggleButton tn_btn_1;
    private javax.swing.JToggleButton tn_btn_2;
    private javax.swing.JToggleButton tn_btn_3;
    private javax.swing.JToggleButton tn_btn_4;
    private javax.swing.JToggleButton tn_btn_5;
    private javax.swing.JToggleButton tn_btn_6;
    private javax.swing.JToggleButton tn_btn_7;
    private javax.swing.JToggleButton tn_btn_8;
    private javax.swing.JToggleButton tn_btn_9;
    // End of variables declaration//GEN-END:variables
    
    public void loadTAbleStracture() {
        try {
            ArrayList<MainTableLocationDto> mainTableLocationList =mainTableLocationController.getAllLocations();
            ArrayList<SubTableLocationDto> subLocationList = subTableLocationController.getAllSubLocations();
            ArrayList<ConfigTablesDto> configTablesList=configTabalesController.getAllLocations();
            ArrayList<ConfigTableDetailsDto> configTableDetalsList=configTabalesController.getAllLocationDetails();
            
            for(int i=0; i<mainTableLocationList.size(); i++){
                MainTableLocationDto mainTableLocationDto=mainTableLocationList.get(i);
                JTabbedPane mainTablePane = new JTabbedPane(JTabbedPane.TOP);
                
                for(int j=0; j<subLocationList.size(); j++){
                    SubTableLocationDto subTableLocationDto=subLocationList.get(j);
                    if(mainTableLocationDto.getMainTableLocationId()==subTableLocationDto.getMainTableLocationId()){
                        JTabbedPane subTabbedPane = new JTabbedPane(JTabbedPane.TOP);
                        
                        
                        
                        for(int s=0; s<configTablesList.size();s++){
                            ConfigTablesDto configTablesDto=configTablesList.get(s);
                            
                            if(configTablesDto.getSubTableLocationId()==subTableLocationDto.getSubTableLocationId()){
                                JPanel tablesPane = new JPanel();
                                tablesPane.setLayout(null);
                                tablesPane.setSize(WIDTH, HEIGHT);
                                tablesPane.setBackground(Color.WHITE);
                                
                                for(int t=0; t<configTableDetalsList.size();t++){
                                    ConfigTableDetailsDto configTableDetailsDto=configTableDetalsList.get(t);
                                    if(configTableDetailsDto.getConfigTablesId()==configTablesDto.getConfigTablesId()){
                                        JXButton tabeBt = new JXButton();
                                        tabeBt.setText(null);
                                        tabeBt.setSize(100, 100);
                                        tabeBt.setMargin(new Insets(0, 0, 0, 0));
                                        tabeBt.setFont(new Font("Tahoma", Font.BOLD, 13));
                                        
                                        int xx = (int) configTableDetailsDto.getWidth();
                                        int yy = (int) configTableDetailsDto.getHeight();
                                        

                                        URL refIconURL = getClass().getResource("/net/unical/pos/imagers/Tables/Webp.net-resizeimage.png");
                                        ImageIcon refIcon = new ImageIcon(refIconURL);
                                        tabeBt.setIcon(refIcon);

                                        tabeBt.setLocation(xx, yy);
                                        tabeBt.addActionListener((ActionEvent e) -> {
                                            OrderDetails.setVisible(true);
                                            OrderDetails.setLocationRelativeTo(null);
                                            OrderDetails.repaint();
                                        });
                                        tablesPane.add(tabeBt);
                                        subTabbedPane.addTab("<html><body><table><tr><td height='28' width='100'><font size='4'>" + configTablesDto.getTableCodePrefix()+ "</font></td></tr></table></body></html>", tablesPane);
                                    }    
                                    
                                }
                                
                            }
                            
                        }

                        mainTablePane.addTab("<html><body><table><tr><td height='28' width='100'><font size='4'>" +subTableLocationDto.getSubName()+ "</font></td></tr></table></body></html>", subTabbedPane);
                    }
                }
                jTabbedPane1.addTab("<html><body><table><tr><td height='28' width='100'><font size='4'>" + mainTableLocationDto.getLocationName()+ "</font></td></tr></table></body></html>", mainTablePane);
                jTabbedPane1.setVisible(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadMainCategory() {
        try {
            System.out.println("Run");
            int rowCount = mainCategoryTb.getRowCount();
            //Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                mainCategoryTb.removeRow(i);
            }
            boolean isInserted = false;
            Object singleItem[] = new Object[10];
            int insertIndex = 0;

            String quary="WHERE status=1 and visible=1";
            ArrayList<MainItemCategoryDto> mainItemCategoryDtos = mainItemCategoryController.getAll(quary);
            for (int i = 0; i < mainItemCategoryDtos.size(); i++) {
                MainItemCategoryDto mainItemCategoryDto = mainItemCategoryDtos.get(i);
                //
                String itemDisplayName = mainItemCategoryDto.getCategoryName();
                int idItemSelected = (int) mainItemCategoryDto.getMainItemCategeryId();
                //          
                singleItem[insertIndex] = itemDisplayName;
                singleItem[insertIndex + 1] = idItemSelected;
                //
                
                if (insertIndex == 8) {
                    // insert new line
                    isInserted = true;
                    mainCategoryTb.addRow(singleItem);
                    //tableModelSuper.insertRow(0, singleItem);
                    insertIndex = -1;
                    singleItem = new Object[10];
                } else {
                    isInserted = false;
                }
                insertIndex += 2;
            }
            if (isInserted == false) {
                System.out.println("Insert");
                mainCategoryTb.addRow(singleItem);
                // tableModelSuper.insertRow(0, singleItem);
                // process final batch
            }
        } catch (Exception ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadSubCategory(int subCategories) {

        try {
            //
            int rowCount = subCategoryTb.getRowCount();
            //Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                subCategoryTb.removeRow(i);
            }
            boolean isInserted = false;
            Object singleItem[] = new Object[10];
            int insertIndex = 0;

            ArrayList<SubItemCategoryDto> subItemCategoryDtos = subItemCategoryController.getSubItemCategories(subCategories);
            for (int i = 0; i < subItemCategoryDtos.size(); i++) {
                SubItemCategoryDto subItemCategoryDto = subItemCategoryDtos.get(i);
                //
                String itemDisplayName = subItemCategoryDto.getSubCategoryName();
                int idItemSelected = (int) subItemCategoryDto.getSubItemCategoryId();
                //          
                singleItem[insertIndex] = itemDisplayName;
                singleItem[insertIndex + 1] = idItemSelected;
                //
                if (insertIndex == 8) {
                    // insert new line
                    isInserted = true;
                    subCategoryTb.addRow(singleItem);
                    //tableModelTrade.insertRow(0, singleItem);
                    insertIndex = -2;
                    singleItem = new Object[10];
                } else {
                    isInserted = false;
                }
                insertIndex += 2;
            }
            if (isInserted == false) {
                subCategoryTb.addRow(singleItem);
                //tableModelTrade.insertRow(0, singleItem);
                // process final batch
            }
        } catch (Exception ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadMainItems(int allItems) {
        try {
            System.out.println("Run");
            int rowCount = allItemsTb.getRowCount();
            //Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                allItemsTb.removeRow(i);
            }
            boolean isInserted = false;
            Object singleItem[] = new Object[10];
            int insertIndex = 0;

            ArrayList<MainItemDto> mainItemDtos = mainItemController.searchAllSubItems(allItems);
            for (int i = 0; i < mainItemDtos.size(); i++) {
                MainItemDto mainItemDto = mainItemDtos.get(i);
                //
                String itemDisplayName = mainItemDto.getItemName();
                int idItemSelected = (int) mainItemDto.getItemId();

                singleItem[insertIndex] =itemDisplayName +"\nRs." + mainItemDto.getUnitPrice();
                singleItem[insertIndex + 1] = idItemSelected;
                //
                if (insertIndex == 8) {
                    // insert new line
                    isInserted = true;
                    allItemsTb.addRow(singleItem);
                    //tableModelTrade.insertRow(0, singleItem);
                    insertIndex = -2;
                    singleItem = new Object[10];
                } else {
                    isInserted = false;
                }
                insertIndex += 2;
            }
            if (isInserted == false) {
                allItemsTb.addRow(singleItem);
                //tableModelTrade.insertRow(0, singleItem);
                // process final batch
            }
        } catch (Exception ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void addItemToBill(int idItemRegistry, boolean isCashItem, String cashItemName, double cashItemQTY, double cashAmount) {

        int largetKey = 0;
        for (Map.Entry<Integer, MainItemDto> entry : uIItemMap.entrySet()) {
            int currentKey = entry.getKey();
            if (currentKey > largetKey) {
                largetKey = entry.getKey();
            }
        }

        MainItemDto mainItemDto = pOSConfigurationsobjects.getMainItemListObjMap().get(idItemRegistry);
        if (mainItemDto != null) {
            // fill object          
            MainItemDto tempUIItemObj = new MainItemDto();
            // check for is cash item or stock item    
//            tempUIItemObj.setItemSeatNumber(selectedSeatPack_);
//            tempUIItemObj.setUniqueTimeStamp(new Date().getTime() + "");
//            tempUIItemObj.setViewItemListObj(viewItemListObj);
//            tempUIItemObj.setItemNote(" ");
//            if (!isCashItem) {
//                // inventory item              
//                tempUIItemObj.setItemQTY(selectedQuantity_);
//                tempUIItemObj.setItemSoldRawPrice(viewItemListObj.getSellingPrice());
//            } else {
//                // cash item
//                tempUIItemObj.setItemQTY(Double.parseDouble(jTextField61.getText().trim()));
//                tempUIItemObj.setIsCashSaleItem(true);
//                //                     
//                tempUIItemObj.setCashSaleITemName(cashItemName);
//                tempUIItemObj.setItemSoldRawPrice(cashAmount);
//                tempUIItemObj.setCashSaleItemPrice(cashAmount);
//            }
//
//            // add to table        
//            uIItemMap.put(largetKey + 1, tempUIItemObj);
//            //
        loadItemUitable();
//            // to unclok item QTY button in UI
//            if (isLockQTYSelectionButten && cxCode.equalsIgnoreCase("TAPH")) {
//                selectectQTYToggleButton(jToggleButton7);
//
//            } else if (isLockQTYSelectionButten) {
//                selectectQTYToggleButton(jToggleButton1);
//            }
//            // to reset key numeric pad value
//            jTextField2.setText("");
        }
    }

    private void loadItemUitable() {
        int rowCount = tableModelSelectedItems.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModelSelectedItems.removeRow(i);
        }
        double selectedQTY = checkQtyToggleButton();
        for (Map.Entry<Integer, MainItemDto> entry : uIItemMap.entrySet()) {
            MainItemDto mainItemDto = entry.getValue();
            if (mainItemDto != null) {
                //entry.getKey()
                String specialMark = "";
//                if ((mainItemDto.getItemNote() != null && !pOSMainUIItemObj.getItemNote().equalsIgnoreCase(" ")) || pOSMainUIItemObj.getRowDiscount() > 0) {
//                    specialMark = "INFO";
//                }
//                if (!mainItemDto.isIsCashSaleItem()) {
//                    // none cash sale item - not consider about cash allowed
//                    Object[] singleItem = {pOSMainUIItemObj.getViewItemListObj().getItemGenaralName(), pOSMainUIItemObj.getItemQTY(), pOSMainUIItemObj.getViewItemListObj().getSellingPrice(), specialMark, pOSMainUIItemObj.getItemSeatNumber(), entry.getKey()};
//                    tableModelSelectedItems.insertRow(0, singleItem);
//                } else {
                // cash sale item -other/ food / beverage / liquir / tobac
                Object[] singleItem = {mainItemDto.getItemName(), selectedQTY, mainItemDto.getUnitPrice(), ""};
                tableModelSelectedItems.insertRow(0, singleItem);
            }

        }
    }

    private void Orderset(JToggleButton toggleButton) {
        if(toggleButton.isSelected()){
            toggleButton.setBackground(new Color(0,153,0));
            OrderDetails.setVisible(true);
            OrderDetails.setLocationRelativeTo(null);
            OrderDetails.repaint();
        }else{
            
        }
    }

//    private void loadSubCategory() {
//        try {
//            ArrayList<SubItemCategoryDto> allCategories=subItemCategoryController.getAllCategories();
//        
//            DefaultTableModel dtm=(DefaultTableModel) subItemCategoryTbl.getModel();
//            dtm.setRowCount(0);
//            
//            for(SubItemCategoryDto dto: allCategories){
//                Object[] rowData={
//                    dto.getSubItemCategoryId(),dto.getMainItemCategoryId(),
//                    dto.getMainCategoryName(),
//                    dto.getSubCategoryName(),
//                    dto.getImagePath(),
//                    dto.getStatus()
//                };
//                dtm.addRow(rowData);
//            }
//            
//        } catch (Exception ex) {
//            Logger.getLogger(SubCategory.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    private Double checkItemAvailability(Integer itemId) {
        Double availableQty=0.00;
        try {
            StockDto stockDto=stockController.searchStock(itemId);
            availableQty=stockDto.getQty();
        } catch (Exception ex) {
            return 0.00;
        }
        return availableQty;
    }

    private void printInvoice(Integer orderId) {
        try {
            
            String sql="SELECT pos_main_order_tb.sub_total_price, pos_main_order_tb.total_discount_price,pos_main_order_tb.total_order_price,pos_main_order_tb.bill_no,\n" +
"pos_main_order_details_tb.quantity,pos_main_order_details_tb.total_item_price,pos_main_order_details_tb.item_id,pos_main_item_tb.item_name\n" +
"FROM pos_main_order_tb \n" +
"INNER JOIN pos_main_order_details_tb ON pos_main_order_details_tb.order_id=pos_main_order_tb.order_id\n" +
"INNER JOIN pos_main_item_tb ON pos_main_item_tb.item_id=pos_main_order_details_tb.item_id\n" +
"WHERE pos_main_order_tb.order_id='"+orderId+"'";
            
            ResultSet rst=Statement.executeQuery(sql);
//            while(rst.next()){
//                HashMap<String, Object>hashMap=new HashMap<>();
//                hashMap.put("bill_no", orderId);
//            }        
            
            
            JRDesignQuery jRDesignQuery=new JRDesignQuery();
            jRDesignQuery.setText(sql);
            
//            jasperDesign.setQuery(jRDesignQuery);
            HashMap<String, Object>hashMap=new HashMap<>();
            hashMap.put("bill_no", orderId);
            
            JasperReport jasperReport=JasperCompileManager.compileReport("./invoice/invoice80mm.jrxml");
            JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, hashMap);
            
            JasperViewer.viewReport(jasperPrint);
            
        } catch (JRException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private double checkQtyToggleButton() {
        double markQty = 0.00;
        // if no toggle alert
        if (tn_btn_1.isSelected()) {
            markQty = 1.00;
        } else if (tn_btn_2.isSelected()) {
            markQty = 2.00;
        } else if (tn_btn_3.isSelected()) {
            markQty = 3.00;
        } else if (tn_btn_4.isSelected()) {
            markQty = 4.00;
        } else if (tn_btn_5.isSelected()) {
            markQty = 5.00;
        } else if (tn_btn_6.isSelected()) {
            markQty = 6.00;
        } else if (tn_btn_7.isSelected()) {
            markQty = 7.00;
        } else if (tn_btn_8.isSelected()) {
            markQty = 8.00;
        } else if (tn_btn_9.isSelected()) {
            markQty = 9.00;
        } else if (manualBtn.isSelected()) {
            // manual
            qtyDialogBox.setLocationRelativeTo(null);
            qtyDialogBox.setVisible(true);
            qtyDialogBox.repaint();

        }
        return markQty;
    }
    
    public void getAllSubCategories() {

        try {
            //
            int rowCount = subCategoryTb.getRowCount();
            //Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                subCategoryTb.removeRow(i);
            }
            boolean isInserted = false;
            Object singleItem[] = new Object[10];
            int insertIndex = 0;

            ArrayList<SubItemCategoryDto> subItemCategoryDtos = subItemCategoryController.getAllCategories();
            for (int i = 0; i < subItemCategoryDtos.size(); i++) {
                SubItemCategoryDto subItemCategoryDto = subItemCategoryDtos.get(i);
                //
                String itemDisplayName = subItemCategoryDto.getSubCategoryName();
                int idItemSelected = (int) subItemCategoryDto.getSubItemCategoryId();
                //          
                singleItem[insertIndex] = itemDisplayName;
                singleItem[insertIndex + 1] = idItemSelected;
                //
                if (insertIndex == 8) {
                    // insert new line
                    isInserted = true;
                    subCategoryTb.addRow(singleItem);
                    //tableModelTrade.insertRow(0, singleItem);
                    insertIndex = -2;
                    singleItem = new Object[10];
                } else {
                    isInserted = false;
                }
                insertIndex += 2;
            }
            if (isInserted == false) {
                subCategoryTb.addRow(singleItem);
                //tableModelTrade.insertRow(0, singleItem);
                // process final batch
            }
        } catch (Exception ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
