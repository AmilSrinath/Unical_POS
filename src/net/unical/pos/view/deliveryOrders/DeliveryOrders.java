/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.view.deliveryOrders;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.unical.pos.configurations.AutoGenerator;
import net.unical.pos.controller.CustomerController;
import net.unical.pos.controller.DeliveryOrderController;
import net.unical.pos.controller.MainItemController;
import net.unical.pos.controller.PaymentTypesController;
import net.unical.pos.controller.UserAccountManagementController;
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.dto.CustomerDto;
import net.unical.pos.dto.DeliveryOrderDto;
import net.unical.pos.dto.MainItemDto;
import net.unical.pos.dto.OrderDetailsDto;
import net.unical.pos.dto.OrderDto;
import net.unical.pos.dto.PaymentTypeDto;
import net.unical.pos.dto.UserDto;
import net.unical.pos.log.Log;
import net.unical.pos.model.CustomerModel;
import net.unical.pos.model.DeliveryOrder;
import net.unical.pos.model.DeliveryOrderAmounts;
import net.unical.pos.model.OrderDetails;
import net.unical.pos.model.OrderModel;
import net.unical.pos.model.PosMainItem;
import net.unical.pos.model.PosMainOrder;
import net.unical.pos.model.PosMainOrderDetails;
import net.unical.pos.model.PosMainUser;
import net.unical.pos.model.TestModel;
import net.unical.pos.repository.custom.MainItemCategoryRepositoryCustom;
import net.unical.pos.repository.impl.CustomerRepositoryImpl;
import net.unical.pos.repository.impl.DeliveryOrderRepositoryImpl;
import net.unical.pos.repository.impl.MainItemRepositoryImpl;
import net.unical.pos.repository.impl.MainOrderDetailRepositoryImpl;
import net.unical.pos.repository.impl.MainOrderRepositoryImpl;
import net.unical.pos.repository.impl.TestClass;
import net.unical.pos.service.impl.CustomerServiceImpl;
import net.unical.pos.view.home.Dashboard;
import net.unical.pos.view.main.LogInForm;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Sanjuka
 */
public class DeliveryOrders extends javax.swing.JInternalFrame {

    /**
     * Creates new form DeliveryOrders
     */
    
    Dashboard dashboard;
    
    private MainItemController newItemController;
    private PaymentTypesController paymentTypesController;
    
    private ArrayList<Integer> itemIds=new ArrayList<>();
    private ArrayList<Double> itemPriceList=new ArrayList<>();
    private ArrayList<Integer> paymentTypeIds=new ArrayList<>();
    private ArrayList<Integer> paymentTypeIds_2=new ArrayList<>();
    private ArrayList<Integer> customersList=new ArrayList<>();
    private ArrayList<Double> itemWeightList=new ArrayList<>();
    private ArrayList<PosMainItem> posMainItems;
    private ArrayList<OrderModel> orders = new ArrayList<>();
    
    private MainOrderRepositoryImpl mainOrderRepositoryImpl;
    private MainItemRepositoryImpl mainItemRepositoryImpl;
    private MainOrderDetailRepositoryImpl mainOrderDetailRepositoryImpl;
    private DeliveryOrderController deliveryOrderController;
    private DeliveryOrderRepositoryImpl deliveryOrderRepositoryImpl;
    private CustomerController customerController;
    private UserAccountManagementController userAccountManagementController;
    CustomerRepositoryImpl customerRepositoryImpl=new CustomerRepositoryImpl();
    TestClass testClass=new TestClass();
    
    DefaultTableModel itemListTableModel=null;
    DefaultTableModel orderListTableModel=null;
    int default_paymentType=0;
    String orderCode = null;
    String delivery_id = null;
    Integer customer_id=null;
    boolean customer_exist=false;
    
    
    public DeliveryOrders(Dashboard dashboard) throws FileNotFoundException, IOException, Exception {
        initComponents();
        
        this.dashboard=dashboard;
        
        this.mainOrderRepositoryImpl = new MainOrderRepositoryImpl();
        this.newItemController=new MainItemController();
        this.paymentTypesController=new PaymentTypesController();
        this.deliveryOrderController=new DeliveryOrderController();
        this.customerController=new CustomerController();
        this.deliveryOrderRepositoryImpl=new DeliveryOrderRepositoryImpl();
        this.mainOrderDetailRepositoryImpl = new MainOrderDetailRepositoryImpl();
        this.mainItemRepositoryImpl = new MainItemRepositoryImpl();
        this.userAccountManagementController=new UserAccountManagementController();
        
        itemListTableModel=(DefaultTableModel) itemListTable.getModel();
        orderListTableModel=(DefaultTableModel) deliveryOrdersTable.getModel();
        
        instalGUI();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date current_date = new Date();
        String CURRENT_DATE=dateFormat.format(current_date);
        
        getPhone_Number_One();
        getPhone_Number_Two();
        getOrderCode();
        getItems();
        getPaymentTypes();
        getAllOrders(CURRENT_DATE,CURRENT_DATE,default_paymentType);
        setCurrentDate();

        fr_de_chb.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (fr_de_chb.getState()) {
                    deliveyFeeLbl.setText("0.00");
                } else {
                    deliveyFeeLbl.setText("350");
                }
                addUpdateTotals();
            }
        });

        FileInputStream fis = new FileInputStream("config.txt");
        Properties props = new Properties();
        props.load(fis);
        
        deliveyFeeLbl.setText(props.getProperty("DELIVERY_FEE"));
        weightTxt.setText("0");
        
        
        posMainItems = mainItemRepositoryImpl.getAllItems("");
        
        
        //Load All Orders to orders Array
        orders = mainOrderRepositoryImpl.getAllOrders();
        
        paymentTypeCombo.addActionListener(new ActionListener() {
            
            private String previousPaymentType = null;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPaymentType = (String) paymentTypeCombo.getSelectedItem();

                if (selectedPaymentType != null && !selectedPaymentType.equals(previousPaymentType)) {
                    if (selectedPaymentType.equals("Card")) {
                        saveOrderBtn1.setEnabled(false);
                        PaidAmountTxt.setText(codTxt.getText());
                        codTxt.setText("0");
                    } else {
                        saveOrderBtn1.setEnabled(true);
                        codTxt.setText(PaidAmountTxt.getText());
                        PaidAmountTxt.setText("0");
                    }
                    previousPaymentType = selectedPaymentType;
                }
            }
        });
        
        PaidAmountTxt.setText("0");
        
        orderIDCmb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new javax.swing.Timer(10, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        String selectedOrderId = orderIDCmb.getSelectedItem().toString();
                        filterOrdersByOrderId(selectedOrderId);
                        ((javax.swing.Timer) evt.getSource()).stop(); // Stop timer after running once
                    }
                }).start();
            }
        });
        
        System.out.println("userRole : "+LogInForm.userID);        
        
        PosMainUser userDto = userAccountManagementController.getUserByUserID(LogInForm.userID);
        
        if (userDto.getRoleId() == 1 || userDto.getRoleId() == 2) {
            deliveryFormDetailPanel.setVisible(true);
        }else{
            deliveryFormDetailPanel.setVisible(false);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        check_customer = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        customerOrderDetailsTbl = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        Phone1Label = new javax.swing.JLabel();
        Phone2Label = new javax.swing.JLabel();
        NameLabel = new javax.swing.JLabel();
        AddressLabel = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        orderIDCmb = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        netTotalLbl = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        order_options = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        btnEdit = new javax.swing.JButton();
        btnDeliverd = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnOutForDelivery = new javax.swing.JButton();
        btnWrapping = new javax.swing.JButton();
        btnActive = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        orderCodeTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarkTxt = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        customerNameTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        codTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        saveOrderBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        itemCombo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        qtyTxt = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        totAmountLbl = new javax.swing.JLabel();
        removeBtn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        itemListTable = new org.jdesktop.swingx.JXTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        deliveyFeeLbl = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        subTotAmountLbl = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        addressTxt = new javax.swing.JTextArea();
        paymentTypeCombo = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        weightTxt = new javax.swing.JTextField();
        saveOrderBtn1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        phoneTwoCmb = new javax.swing.JComboBox<>();
        phoneOneCmb = new javax.swing.JComboBox<>();
        fr_de_chb = new java.awt.Checkbox();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        customerNumberTxt = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        PaidAmountTxt = new javax.swing.JTextField();
        radioExchange = new java.awt.Checkbox();
        jScrollPane6 = new javax.swing.JScrollPane();
        deliveryOrdersTable = new org.jdesktop.swingx.JXTable();
        jPanel4 = new javax.swing.JPanel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jButton1 = new javax.swing.JButton();
        total_orders_count_txt = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        paymentTypeCombo2 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        deliveryFormDetailPanel = new java.awt.Panel();
        jLabel26 = new javax.swing.JLabel();
        amountTotTxt = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        deliveriesTotTxt = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        returnsTotTxt = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        deliveryChargeTotTxt = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        codTotTxt = new javax.swing.JLabel();
        printAllOrdersBtn = new javax.swing.JButton();

        check_customer.setAlwaysOnTop(true);

        check_customer.setMinimumSize(new java.awt.Dimension(300, 300));

        check_customer.setResizable(false);

        check_customer.setSize(new java.awt.Dimension(700, 500));
        check_customer.setAlwaysOnTop(true);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(0, 102, 153));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Check Customer History");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        customerOrderDetailsTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Name", "Quantity", "Per Item Price", "COD Amount", "Total Item Price", "Total Order Price", "Delivery Fee", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(customerOrderDetailsTbl);

        jLabel22.setText("Phone Number 1 :");

        jLabel23.setText("Phone Number 2 :");

        jLabel24.setText("Order Code :");

        jLabel25.setText("Customer Address :");

        orderIDCmb.setEditable(true);
        orderIDCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderIDCmbActionPerformed(evt);
            }
        });

        jLabel31.setText("Customer Name :");

        jLabel32.setText("Total :");

        jLabel33.setText("Date :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(466, 466, 466)
                        .addComponent(jLabel32)
                        .addGap(18, 18, 18)
                        .addComponent(netTotalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Phone1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orderIDCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(124, 124, 124)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AddressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Phone2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Phone1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(orderIDCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Phone2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(netTotalLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout check_customerLayout = new javax.swing.GroupLayout(check_customer.getContentPane());
        check_customer.getContentPane().setLayout(check_customerLayout);
        check_customerLayout.setHorizontalGroup(
            check_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        check_customerLayout.setVerticalGroup(
            check_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, check_customerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        order_options.setAlwaysOnTop(true);

        order_options.setResizable(false);

        order_options.setSize(new java.awt.Dimension(509, 128));
        order_options.setAlwaysOnTop(true);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 153, 204))); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(1300, 100));

        btnEdit.setBackground(new java.awt.Color(0, 102, 153));
        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit Order");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDeliverd.setBackground(new java.awt.Color(51, 153, 0));
        btnDeliverd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDeliverd.setForeground(new java.awt.Color(255, 255, 255));
        btnDeliverd.setText("Deliverd");
        btnDeliverd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeliverdActionPerformed(evt);
            }
        });

        btnReturn.setBackground(new java.awt.Color(255, 153, 0));
        btnReturn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnReturn.setForeground(new java.awt.Color(255, 255, 255));
        btnReturn.setText("Return");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(204, 0, 0));
        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel Order");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnOutForDelivery.setBackground(new java.awt.Color(51, 51, 255));
        btnOutForDelivery.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnOutForDelivery.setForeground(new java.awt.Color(255, 255, 255));
        btnOutForDelivery.setText("Out for delivery");
        btnOutForDelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOutForDeliveryActionPerformed(evt);
            }
        });

        btnWrapping.setBackground(new java.awt.Color(204, 204, 0));
        btnWrapping.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnWrapping.setForeground(new java.awt.Color(255, 255, 255));
        btnWrapping.setText("Wrapping");
        btnWrapping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWrappingActionPerformed(evt);
            }
        });

        btnActive.setBackground(new java.awt.Color(0, 204, 204));
        btnActive.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnActive.setForeground(new java.awt.Color(255, 255, 255));
        btnActive.setText("Active");
        btnActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActiveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeliverd, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOutForDelivery)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActive, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnWrapping, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(401, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btnDeliverd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReturn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOutForDelivery, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActive, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnWrapping, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout order_optionsLayout = new javax.swing.GroupLayout(order_options.getContentPane());
        order_options.getContentPane().setLayout(order_optionsLayout);
        order_optionsLayout.setHorizontalGroup(
            order_optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(order_optionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(494, Short.MAX_VALUE))
        );
        order_optionsLayout.setVerticalGroup(
            order_optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, order_optionsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
        );

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        orderCodeTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderCodeTxtActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Order Code :");

        remarkTxt.setColumns(20);
        remarkTxt.setRows(5);
        jScrollPane1.setViewportView(remarkTxt);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Address :");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Phone number 1 :");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Remark :");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Customer Name :");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("COD :");

        codTxt.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        codTxt.setForeground(new java.awt.Color(153, 0, 0));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Phone number 2 :");

        saveOrderBtn.setBackground(new java.awt.Color(0, 102, 153));
        saveOrderBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        saveOrderBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveOrderBtn.setText("Save Order");
        saveOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveOrderBtnActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        itemCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemComboActionPerformed(evt);
            }
        });

        jLabel8.setText("Select Item :");

        qtyTxt.setText("1");

        addBtn.setBackground(new java.awt.Color(51, 153, 0));
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("QTY : ");

        totAmountLbl.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        totAmountLbl.setForeground(new java.awt.Color(204, 0, 0));
        totAmountLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totAmountLbl.setText("0.00");

        removeBtn.setBackground(new java.awt.Color(204, 0, 0));
        removeBtn.setForeground(new java.awt.Color(255, 255, 255));
        removeBtn.setText("Remove");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        itemListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Item Name", "Item Price", "Qty", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        itemListTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                itemListTablePropertyChange(evt);
            }
        });
        itemListTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                itemListTableKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(itemListTable);
        if (itemListTable.getColumnModel().getColumnCount() > 0) {
            itemListTable.getColumnModel().getColumn(0).setMinWidth(0);
            itemListTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            itemListTable.getColumnModel().getColumn(0).setMaxWidth(0);
            itemListTable.getColumnModel().getColumn(2).setMinWidth(0);
            itemListTable.getColumnModel().getColumn(2).setPreferredWidth(0);
            itemListTable.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Grand Total :");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Delivery Fee :");

        deliveyFeeLbl.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        deliveyFeeLbl.setForeground(new java.awt.Color(0, 153, 0));
        deliveyFeeLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 153));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Sub Total :");

        subTotAmountLbl.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        subTotAmountLbl.setForeground(new java.awt.Color(0, 102, 153));
        subTotAmountLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        subTotAmountLbl.setText("0.00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(itemCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(qtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deliveyFeeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(totAmountLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(removeBtn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(subTotAmountLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addBtn)
                            .addComponent(removeBtn))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(subTotAmountLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deliveyFeeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totAmountLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        addressTxt.setColumns(20);
        addressTxt.setRows(5);
        jScrollPane3.setViewportView(addressTxt);

        paymentTypeCombo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentTypeComboMouseClicked(evt);
            }
        });
        paymentTypeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentTypeComboActionPerformed(evt);
            }
        });
        paymentTypeCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paymentTypeComboKeyReleased(evt);
            }
        });

        jLabel12.setText("Payment Type :");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Paid Amount :");

        saveOrderBtn1.setBackground(new java.awt.Color(153, 153, 0));
        saveOrderBtn1.setForeground(new java.awt.Color(255, 255, 255));
        saveOrderBtn1.setText("Add");
        saveOrderBtn1.setPreferredSize(new java.awt.Dimension(52, 25));
        saveOrderBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveOrderBtn1ActionPerformed(evt);
            }
        });

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Weight(kg) :");

        phoneTwoCmb.setEditable(true);
        phoneTwoCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneTwoCmbActionPerformed(evt);
            }
        });

        phoneOneCmb.setEditable(true);
        phoneOneCmb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                phoneOneCmbMouseClicked(evt);
            }
        });
        phoneOneCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneOneCmbActionPerformed(evt);
            }
        });
        phoneOneCmb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                phoneOneCmbKeyReleased(evt);
            }
        });

        fr_de_chb.setLabel("Free Ship");
        fr_de_chb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fr_de_chbMouseClicked(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 204, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Check");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(51, 153, 0));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Edit");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(0, 0, 0));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Clear");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        customerNumberTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerNumberTxtActionPerformed(evt);
            }
        });

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Customer Number :");

        PaidAmountTxt.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        PaidAmountTxt.setForeground(new java.awt.Color(153, 0, 0));

        radioExchange.setLabel("Exchange");
        radioExchange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioExchangeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(paymentTypeCombo, 0, 76, Short.MAX_VALUE)
                            .addComponent(codTxt)
                            .addComponent(PaidAmountTxt))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveOrderBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(radioExchange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(fr_de_chb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(saveOrderBtn))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel15)
                                        .addGap(5, 5, 5)
                                        .addComponent(weightTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 24, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(9, 9, 9)
                                .addComponent(phoneOneCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(phoneTwoCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(customerNameTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(orderCodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(customerNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneOneCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneTwoCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(orderCodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)
                    .addComponent(customerNameTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(customerNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(weightTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(paymentTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PaidAmountTxt)
                            .addComponent(saveOrderBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7)
                .addComponent(radioExchange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(codTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(saveOrderBtn)
                        .addComponent(jLabel4))
                    .addComponent(fr_de_chb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        deliveryOrdersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Order Code", "Customer Name", "Phone One", "Phone Two", "COD", "Total Amount", "Date", "Delivery Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        deliveryOrdersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deliveryOrdersTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                deliveryOrdersTableMousePressed(evt);
            }
        });
        deliveryOrdersTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                deliveryOrdersTablePropertyChange(evt);
            }
        });
        deliveryOrdersTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                deliveryOrdersTableKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(deliveryOrdersTable);
        if (deliveryOrdersTable.getColumnModel().getColumnCount() > 0) {
            deliveryOrdersTable.getColumnModel().getColumn(0).setMinWidth(0);
            deliveryOrdersTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            deliveryOrdersTable.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        total_orders_count_txt.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        total_orders_count_txt.setForeground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Total Orders :");

        paymentTypeCombo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any" }));
        paymentTypeCombo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentTypeCombo2MouseClicked(evt);
            }
        });
        paymentTypeCombo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentTypeCombo2ActionPerformed(evt);
            }
        });
        paymentTypeCombo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paymentTypeCombo2KeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Payment Type");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("From");

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("To");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel17)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(77, 77, 77)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(paymentTypeCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addComponent(jLabel16))
                .addGap(75, 75, 75)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_orders_count_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(total_orders_count_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(paymentTypeCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );

        jLabel26.setBackground(new java.awt.Color(0, 0, 0));
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setText("Total Amount : ");

        amountTotTxt.setBackground(new java.awt.Color(0, 0, 0));
        amountTotTxt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        amountTotTxt.setText("0.00");

        jLabel27.setBackground(new java.awt.Color(0, 0, 0));
        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setText("Total Deliveries : ");

        deliveriesTotTxt.setBackground(new java.awt.Color(0, 0, 0));
        deliveriesTotTxt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        deliveriesTotTxt.setText("0.00");

        jLabel28.setBackground(new java.awt.Color(0, 0, 0));
        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setText("Total Returns : ");

        returnsTotTxt.setBackground(new java.awt.Color(0, 0, 0));
        returnsTotTxt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        returnsTotTxt.setText("0.00");

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("Delivery Chargers :");

        deliveryChargeTotTxt.setBackground(new java.awt.Color(0, 0, 0));
        deliveryChargeTotTxt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        deliveryChargeTotTxt.setText("0.00");

        jLabel29.setBackground(new java.awt.Color(0, 0, 0));
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setText("Total COD : ");

        codTotTxt.setBackground(new java.awt.Color(0, 0, 0));
        codTotTxt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        codTotTxt.setText("0.00");

        printAllOrdersBtn.setBackground(new java.awt.Color(51, 153, 0));
        printAllOrdersBtn.setForeground(new java.awt.Color(255, 255, 255));
        printAllOrdersBtn.setText("Print all orders");
        printAllOrdersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printAllOrdersBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deliveryFormDetailPanelLayout = new javax.swing.GroupLayout(deliveryFormDetailPanel);
        deliveryFormDetailPanel.setLayout(deliveryFormDetailPanelLayout);
        deliveryFormDetailPanelLayout.setHorizontalGroup(
            deliveryFormDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deliveryFormDetailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amountTotTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deliveriesTotTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(returnsTotTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deliveryChargeTotTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(codTotTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(printAllOrdersBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        deliveryFormDetailPanelLayout.setVerticalGroup(
            deliveryFormDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deliveryFormDetailPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(deliveryFormDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(deliveryFormDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(codTotTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(printAllOrdersBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(deliveryFormDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(deliveryFormDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deliveriesTotTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(returnsTotTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deliveryChargeTotTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(amountTotTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)))
                    .addComponent(deliveryFormDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addComponent(deliveryFormDetailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void orderCodeTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderCodeTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orderCodeTxtActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:

        Double totalAmount = 0.00;
        Double totalWeight = 0.00;

        if (qtyTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "QTY..?");
            return;
        }

        Integer itemIndex = itemCombo.getSelectedIndex();
        Double qty = Double.parseDouble(qtyTxt.getText());

        String itemName = (String) itemCombo.getSelectedItem();
        Integer itemId = itemIds.get(itemIndex);
        Double itemPrice = itemPriceList.get(itemIndex);
        Double itemWeight = itemWeightList.get(itemIndex);

        Double amount = itemPrice * qty;
        boolean itemFound = false;

        for (int i = 0; i < itemListTableModel.getRowCount(); i++) {
            Integer existingItemId = (Integer) itemListTableModel.getValueAt(i, 0);
            if (existingItemId.equals(itemId)) {
                Number existingQtyValue = (Number) itemListTableModel.getValueAt(i, 3); // Use Number to avoid ClassCastException
                Double existingQty = existingQtyValue.doubleValue();
                Double newQty = existingQty + qty;
                Double newAmount = itemPrice * newQty;

                itemListTableModel.setValueAt(newQty, i, 3);
                itemListTableModel.setValueAt(newAmount, i, 4);

                itemFound = true;
                break;
            }
        }


        if (!itemFound) {
            Object itemData[] = {itemId, itemName, itemPrice, qty, amount};
            itemListTableModel.addRow(itemData);
        }

        addUpdateTotals();
        
        Properties props = loadProperties();
        if (props != null) {
            calculateDeliveryFee(props);
        }
        
        addUpdateTotals();
    }//GEN-LAST:event_addBtnActionPerformed

    private void updateTotals() {
        double totalAmount = 0.0;
        double totalWeight = 0.0;

        if (itemListTable.getRowCount() == 0) {
            subTotAmountLbl.setText("0.00");
            totAmountLbl.setText("0.00");
            weightTxt.setText("0");
        } else {
            for (int i = 0; i < itemListTable.getRowCount(); i++) {
                int itemId = (Integer) itemListTable.getValueAt(i, 0);

                double price = ((Number) itemListTable.getValueAt(i, 2)).doubleValue();
                int qty = ((Number) itemListTable.getValueAt(i, 3)).intValue();

                double weight = itemWeightList.get(itemIds.indexOf(itemId));

                totalAmount += price * qty;
                totalWeight += weight * qty;
            }

            subTotAmountLbl.setText(String.format("%.2f", totalAmount));

            double deliveryFee = Double.parseDouble(deliveyFeeLbl.getText());
            totAmountLbl.setText(String.format("%.2f", totalAmount + deliveryFee));

            //codTxt.setText(totAmountLbl.getText());
            
            weightTxt.setText(String.format("%.2f", totalWeight));
        }
    }
    
    private void addUpdateTotals() {
        double totalAmount = 0.0;
        double totalWeight = 0.0;

        if (itemListTable.getRowCount() == 0) {
            subTotAmountLbl.setText("0.00");
            totAmountLbl.setText("0.00");
            weightTxt.setText("0");
        } else {
            for (int i = 0; i < itemListTable.getRowCount(); i++) {
                int itemId = (Integer) itemListTable.getValueAt(i, 0);

                double price = ((Number) itemListTable.getValueAt(i, 2)).doubleValue();
                int qty = ((Number) itemListTable.getValueAt(i, 3)).intValue();

                double weight = itemWeightList.get(itemIds.indexOf(itemId));

                totalAmount += price * qty;
                totalWeight += weight * qty;
            }

            subTotAmountLbl.setText(String.format("%.2f", totalAmount));

            double deliveryFee = Double.parseDouble(deliveyFeeLbl.getText());
            
            totAmountLbl.setText(String.format("%.2f", totalAmount + deliveryFee));
            
            if (paymentTypeCombo.getSelectedItem().equals("Card")) {
                PaidAmountTxt.setText(totAmountLbl.getText());
            }else {
                codTxt.setText(totAmountLbl.getText());
            }
            
            weightTxt.setText(String.format("%.2f", totalWeight));
        }
    }
    
    private Properties loadProperties() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.txt")) {
            props.load(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, "Config file not found", ex);
            Log.error(ex, ex);
        } catch (IOException ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, "Error loading config file", ex);
            Log.error(ex, ex);
        }
        return props;
    }

    private void calculateDeliveryFee(Properties props) {
        String deliveryFeeStr = props.getProperty("DELIVERY_FEE");
        Double DELIVERY_FEE = Double.parseDouble(deliveryFeeStr);

        String addCostPer1KgStr = props.getProperty("ADD_COST_PER_1KG");
        Double ADD_COST_PER_1KG = Double.parseDouble(addCostPer1KgStr);

        Double w = Double.parseDouble(weightTxt.getText());

        if (w >= 1000) {
            Integer kylo = (int) (w / 1000);
            Double value = kylo * ADD_COST_PER_1KG;
            Double totalFee = value + DELIVERY_FEE;
            deliveyFeeLbl.setText(String.format("%.2f", totalFee));
        }
        System.out.println(deliveyFeeLbl.getText());
    }
    
    
    private boolean validateInputs() {
//        if (orderCodeTxt.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Order code cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
//            orderCodeTxt.requestFocus();
//            return false;
//        }

        if (customerNameTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Customer name cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
            customerNameTxt.requestFocus();
            return false;
        }

        if (addressTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Address cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
            addressTxt.requestFocus();
            return false;
        }

        if (phoneOneCmb.getSelectedItem() == null || phoneOneCmb.getSelectedItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone number 1 cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
            phoneOneCmb.requestFocus();
            return false;
        } else if (phoneOneCmb.getSelectedItem().toString().trim().length() != 10) {
            JOptionPane.showMessageDialog(this, "Phone number 1 must be exactly 10 characters", "Validation Error", JOptionPane.ERROR_MESSAGE);
            phoneOneCmb.requestFocus();
            return false;
        }

        if (phoneTwoCmb.getSelectedItem() == null || phoneTwoCmb.getSelectedItem().toString().trim().isEmpty()) {
            phoneTwoCmb.setSelectedItem("");
        } else {
            if (phoneTwoCmb.getSelectedItem().toString().trim().length() != 10) {
                JOptionPane.showMessageDialog(this, "Phone number 2 must be exactly 10 characters if provided", "Validation Error", JOptionPane.ERROR_MESSAGE);
                phoneTwoCmb.requestFocus();
                return false;
            }
        }

        if (paymentTypeCombo.getSelectedItem() == null || paymentTypeCombo.getSelectedItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Payment type cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
            paymentTypeCombo.requestFocus();
            return false;
        }

        if (itemListTable == null || itemListTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Item table cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
            itemListTable.requestFocus();
            return false;
        }

        return true;
    }


    private void saveOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveOrderBtnActionPerformed
        if (!validateInputs()) {
            return;
        }
        
        if (delivery_id == null) {
            if (!deliveryOrderRepositoryImpl.isLastOrderDelivered(phoneOneCmb.getSelectedItem().toString())) {
                JOptionPane.showMessageDialog(this, "The customer's latest order is still pending delivery.");
                delivery_id = null;
                clearText();
                DefaultTableModel model = (DefaultTableModel) itemListTable.getModel();
                model.setRowCount(0);
                return;
            }
            saveOrder();
            return;
        }
        
        for (OrderModel om : orders) {
            if (om.getDeliveryOrderId() == Integer.parseInt(delivery_id)) {
                updateOrder(om.getOrderId(), delivery_id); //orderId --> main order tb
                delivery_id = null;
                break;
            }
        }
        
        DefaultTableModel model = (DefaultTableModel) itemListTable.getModel();
        model.setRowCount(0);
        delivery_id = null;
        clearText();
    }//GEN-LAST:event_saveOrderBtnActionPerformed

    private void updateOrder(Integer orderId, String delivery_id) { //orderId --> main order tb
        ArrayList<OrderDetailsDto> orderDetailsDtos = new ArrayList<>();

        for (int i = 0; i < itemListTable.getRowCount(); i++) {
            Integer itemId = (Integer) itemListTable.getValueAt(i, 0);

            Number priceValue = (Number) itemListTable.getValueAt(i, 2);
            Double price = priceValue.doubleValue();

            Number qtyValue = (Number) itemListTable.getValueAt(i, 3);
            Integer qty = qtyValue.intValue();

            OrderDetailsDto dto = new OrderDetailsDto(0, null, itemId, null, null, 1, qty, price, 0.00, price * qty, "", 1, 1);
            orderDetailsDtos.add(dto);
        }

        String phoneTwo = null;
        if (phoneTwoCmb.getSelectedItem() != null) {
            phoneTwo = phoneTwoCmb.getSelectedItem().toString();
        }

        int index = paymentTypeCombo.getSelectedIndex();

        Boolean free_shipping_check = fr_de_chb.getState();
        int free_ship = 0;
        if (free_shipping_check) {
            free_ship = 1;
        }
        System.out.println("free_ship : " + free_ship);
        try {
            if (phoneOneCmb.getSelectedItem() != null) {
                DeliveryOrder deliveryOrderDto = new DeliveryOrder();
                deliveryOrderDto.setOrderCode(orderCodeTxt.getText());
                if (customer_exist) {
                    deliveryOrderDto.setCustomerId(customer_id);
                } else {
                    deliveryOrderDto.setCustomerId(null);
                }
                deliveryOrderDto.setCustomerName(customerNameTxt.getText());
                deliveryOrderDto.setAddress(addressTxt.getText());
                deliveryOrderDto.setCod(Double.parseDouble(codTxt.getText()));
                
                String phoneNumber = phoneOneCmb.getSelectedItem().toString();
                phoneNumber = phoneNumber.replaceAll("[\\s\\-()]", "");
                deliveryOrderDto.setPhoneOne(phoneNumber);
                
                deliveryOrderDto.setPhoneTwo(phoneTwo);
                deliveryOrderDto.setSubTotalPrice(Double.parseDouble(subTotAmountLbl.getText()));
                deliveryOrderDto.setDeliveryFee(Double.parseDouble(deliveyFeeLbl.getText()));
                deliveryOrderDto.setWeight(weightTxt.getText().toString());
                deliveryOrderDto.setFreeShip(free_ship);
                deliveryOrderDto.setGrandTotalPrice(Double.parseDouble(totAmountLbl.getText()));
                deliveryOrderDto.setCustomerNumber(customerNumberTxt.getText());
                deliveryOrderDto.setPaidAmount(Double.parseDouble(PaidAmountTxt.getText()));
                deliveryOrderDto.setOrderId(orderId);
                
                Timestamp now = new Timestamp(System.currentTimeMillis());
                deliveryOrderDto.setEditedDate(now);

                deliveryOrderDto.setRemark(remarkTxt.getText());
                deliveryOrderDto.setPaymentTypeId(paymentTypeIds.get(index));
                deliveryOrderDto.setOrderDetailsDtos(orderDetailsDtos);

                // Assuming there is a method in the repository to update the order
                boolean isUpdated = deliveryOrderRepositoryImpl.update(deliveryOrderDto, orderId, delivery_id);
                if (isUpdated) {
                    JOptionPane.showMessageDialog(this, "Order updated successfully");

                    // Refresh orders list and UI components
                    Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String fromDate = formatter.format(jXDatePicker1.getDate());
                    String toDate = formatter.format(jXDatePicker2.getDate());
                    getAllOrders(fromDate, toDate, default_paymentType);
                    getPhone_Number_One();
                    getPhone_Number_Two();
                    clearText();
                    phoneOneCmb.requestFocus();
                    customer_exist = false;

                    DefaultTableModel model = (DefaultTableModel) itemListTable.getModel();
                    model.setRowCount(0);

                    FileInputStream fis = new FileInputStream("config.txt");
                    Properties props = new Properties();
                    props.load(fis);
                    
                    customerNameTxt.setText("");
                    addressTxt.setText("");
                    codTxt.setText("");
                    subTotAmountLbl.setText("0.00");
                    totAmountLbl.setText("0.00");
                    customerNumberTxt.setText("");
                    remarkTxt.setText("");
                    orderCodeTxt.setText("");
                    PaidAmountTxt.setText("0");
                    
                    itemListTableModel.setRowCount(0);
                } else {
                    JOptionPane.showMessageDialog(this, "Update failed.(Duplicate order code found! or another error!)");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Add Phone Number");
            }
        } catch (Exception ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, ex);
        }

        try {
            orders = mainOrderRepositoryImpl.getAllOrders();
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "update order error");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "update order error");
        }
    }

    
    private void saveOrder(){
        ArrayList<OrderDetailsDto> orderDetailsDtos=new ArrayList<>();
        
        for (int i = 0; i < itemListTable.getRowCount(); i++) {
            Integer itemId = (Integer) itemListTable.getValueAt(i, 0);

            Number priceValue = (Number) itemListTable.getValueAt(i, 2);
            Double price = priceValue.doubleValue();

            Number qtyValue = (Number) itemListTable.getValueAt(i, 3);
            Integer qty = qtyValue.intValue();

            OrderDetailsDto dto = new OrderDetailsDto(0, null, itemId, null, null, 1, qty, price, 0.00, price * qty, "", 1, 1);
            orderDetailsDtos.add(dto);
        }

        String phoneTwo=null;
        if(phoneTwoCmb.getSelectedItem()!=null){
            phoneTwo=phoneTwoCmb.getSelectedItem().toString();
            
        }
        
        int index=paymentTypeCombo.getSelectedIndex();
        Integer orderId=null;
        
        Boolean free_shiping_check=fr_de_chb.getState();
        
        int free_ship=0;
        if(free_shiping_check){
            free_ship=1;
        }
        
        int isExch = 0;
        Boolean isExchange = radioExchange.getState();
        if (isExchange) {
            isExch = 1;
        }
        
        try {
                if(phoneOneCmb.getSelectedItem()!=null){
                    DeliveryOrder deliveryOrderDto=new DeliveryOrder();
                    deliveryOrderDto.setOrderCode(orderCodeTxt.getText());
                    if(customer_exist){
                        deliveryOrderDto.setCustomerId(customer_id);
                    }else{
                        deliveryOrderDto.setCustomerId(null);
                    }
                    deliveryOrderDto.setCustomerName(customerNameTxt.getText());
                    deliveryOrderDto.setAddress(addressTxt.getText());
                    deliveryOrderDto.setCod(Double.parseDouble(codTxt.getText()));
                    
                    String phoneNumber = phoneOneCmb.getSelectedItem().toString();
                    phoneNumber = phoneNumber.replaceAll("[\\s\\-()]", "");
                    deliveryOrderDto.setPhoneOne(phoneNumber);
                    
                    deliveryOrderDto.setPhoneTwo(phoneTwo);
                    deliveryOrderDto.setSubTotalPrice(Double.parseDouble(subTotAmountLbl.getText()));
                    deliveryOrderDto.setDeliveryFee(Double.parseDouble(deliveyFeeLbl.getText()));
                    deliveryOrderDto.setWeight(weightTxt.getText().toString());
                    deliveryOrderDto.setFreeShip(free_ship);
                    deliveryOrderDto.setGrandTotalPrice(Double.parseDouble(totAmountLbl.getText()));
                    deliveryOrderDto.setCustomerNumber(customerNumberTxt.getText());
                    deliveryOrderDto.setPaidAmount(Double.parseDouble(PaidAmountTxt.getText()));
                    Timestamp now = new Timestamp(System.currentTimeMillis());
                    deliveryOrderDto.setCreateDate(now);
                    deliveryOrderDto.setEditedDate(now);
                    deliveryOrderDto.setUserID(1);
                    deliveryOrderDto.setIsExchange(isExch);

                    deliveryOrderDto.setRemark(remarkTxt.getText());
                    deliveryOrderDto.setPaymentTypeId(paymentTypeIds.get(index));
                    deliveryOrderDto.setOrderDetailsDtos(orderDetailsDtos);
                    orderId=deliveryOrderRepositoryImpl.save(deliveryOrderDto);
                    
                    System.out.println("paymentTypeIds.get(index) : "+paymentTypeIds.get(index));
                }else{
                    JOptionPane.showMessageDialog(this, "Add Phone Number");
                }
            
        
            if(orderId!=null){
                JOptionPane.showMessageDialog(this, "Order saved sucessfully");
                
                //Bill print
//                printBill(orderId);
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String fromDate = formatter.format(jXDatePicker1.getDate());
                String toDate = formatter.format(jXDatePicker2.getDate());
                getAllOrders(fromDate,toDate,default_paymentType);
                getPhone_Number_One();
                getPhone_Number_Two();
                clearText();
                phoneOneCmb.requestFocus();
                customer_exist=false;
                
                FileInputStream fis = new FileInputStream("config.txt");
                Properties props = new Properties();
                props.load(fis);
                
                customerNameTxt.setText("");
                addressTxt.setText("");
                customerNumberTxt.setText("");
                remarkTxt.setText("");
                orderCodeTxt.setText("");
                Log.info(DeliveryOrders.class, orderId+" Order Save. User ID: "+LogInForm.userID);
                
                subTotAmountLbl.setText("0.00");
                totAmountLbl.setText("0.00");
                itemListTableModel.setRowCount(0);
                codTxt.setText("");
                PaidAmountTxt.setText("0");
            }else{
                JOptionPane.showMessageDialog(this, "Save fail..");
            }
        } catch (Exception ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "save order error");
        }
        
        try {
            orders = mainOrderRepositoryImpl.getAllOrders();
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "save order error");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "save order error");
        }
        delivery_id = null;
    }
    
    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        int row = itemListTable.getSelectedRow();
        if (row != -1) {
            itemListTableModel.removeRow(row);
        }

        Double totalAmount = 0.00;
        Double totalWeight = 0.00;

        if (itemListTable != null && itemListTable.getRowCount() > 0) {
            for (int i = 0; i < itemListTable.getRowCount(); i++) {
                Number priceValue = (Number) itemListTable.getValueAt(i, 2);
                Number qtyValue = (Number) itemListTable.getValueAt(i, 3);
                Integer itemId = (Integer) itemListTable.getValueAt(i, 0);
                Double weight = itemWeightList.get(itemIds.indexOf(itemId));

                Double price = priceValue.doubleValue();
                Double qty = qtyValue.doubleValue();

                totalAmount += price * qty;
                totalWeight += weight * qty;
            }

            Double deliveryFee = calculateDeliveryFee(totalWeight);
            deliveyFeeLbl.setText(String.format("%.2f", deliveryFee));
            totAmountLbl.setText(String.format("%.2f", totalAmount + deliveryFee));
        } else {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream("config.txt");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Item Remove error");
            }
            Properties props = new Properties();
            try {
                props.load(fis);
            } catch (IOException ex) {
                Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Item Remove error");
            }

                deliveyFeeLbl.setText(props.getProperty("DELIVERY_FEE"));
            
            
            totAmountLbl.setText(String.format("%.2f", 0.00));
        }

        subTotAmountLbl.setText(String.format("%.2f", totalAmount));
        codTxt.setText(totAmountLbl.getText());
        weightTxt.setText(String.format("%.2f", totalWeight));
    }//GEN-LAST:event_removeBtnActionPerformed

    private Double calculateDeliveryFee(Double totalWeight) {
        Properties props = loadPropertie();
        if (props == null) {
            return 0.00;
        }

        String deliveryFeeStr = props.getProperty("DELIVERY_FEE");
        Double DELIVERY_FEE = Double.parseDouble(deliveryFeeStr);

        String addCostPer1KgStr = props.getProperty("ADD_COST_PER_1KG");
        Double ADD_COST_PER_1KG = Double.parseDouble(addCostPer1KgStr);

        if (totalWeight >= 1000) {
            Integer kylo = (int) (totalWeight / 1000);
            Double value = kylo * ADD_COST_PER_1KG;
            return value + DELIVERY_FEE;
        } else {
            return DELIVERY_FEE;
        }
    }

    private Properties loadPropertie() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.txt")) {
            props.load(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, "Config file not found", ex);
            Log.error(ex, "FileNotFoundException error");
        } catch (IOException ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, "Error loading config file", ex);
            Log.error(ex, "IOException error");
        }
        return props;
    }
    
    private void itemListTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_itemListTablePropertyChange

    }//GEN-LAST:event_itemListTablePropertyChange

    private void itemListTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemListTableKeyReleased
        
    }//GEN-LAST:event_itemListTableKeyReleased

    private void deliveryOrdersTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_deliveryOrdersTablePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_deliveryOrdersTablePropertyChange

    private void deliveryOrdersTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deliveryOrdersTableKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_deliveryOrdersTableKeyReleased

    private void printAllOrdersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printAllOrdersBtnActionPerformed
        Integer row=deliveryOrdersTable.getSelectedRow();
        Integer orderId=(Integer) deliveryOrdersTable.getValueAt(row, 0);
        String orderCode=(String) deliveryOrdersTable.getValueAt(row, 1);
        Double cod=(Double) deliveryOrdersTable.getValueAt(row, 3);
        Double totalPrice=(Double) deliveryOrdersTable.getValueAt(row, 4);
        Double paidAmount=totalPrice-cod;
        
        try {
            HashMap<String, Object> hm = new HashMap<>();
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            
            
            
            hm.put("DATE", formatter.format(date));
            hm.put("PAID_AMOUNT", paidAmount);
            hm.put("ORDER_ID", orderId);
            
            JasperReport jr = JasperCompileManager.compileReport("D:/Unical/Unical-Pos-System/src/net/unical/pos/reports/DeliveryOrderBill.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(jr, hm, DBCon.getDatabaseConnection());
//            JasperViewer view = new JasperViewer(jp, false);
//            view.setVisible(true);
            String path="C:/Users/Sanjuka/Documents/Petal Pink/Payment recepts/"+orderCode+".pdf";
            System.out.println(path);
            JasperExportManager.exportReportToPdfFile(jp,path);
        
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "printAllOrdersBtnActionPerformed error");
        }
    }//GEN-LAST:event_printAllOrdersBtnActionPerformed

    private void saveOrderBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveOrderBtn1ActionPerformed
        Double amount=Double.parseDouble(PaidAmountTxt.getText());
        Double total=Double.parseDouble(totAmountLbl.getText());
        
        Double cod=total-amount;
        codTxt.setText(cod+"");
    }//GEN-LAST:event_saveOrderBtn1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = formatter.format(jXDatePicker1.getDate());
        String toDate = formatter.format(jXDatePicker2.getDate());
        
        int index=0;
        if(paymentTypeCombo2.getSelectedIndex()!=0){
            index=paymentTypeCombo2.getSelectedIndex();
            getAllOrders(fromDate,toDate,paymentTypeIds_2.get(index-1));
        }else{
            getAllOrders(fromDate,toDate,0);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void phoneTwoCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneTwoCmbActionPerformed
        if(phoneTwoCmb.getSelectedIndex()!=-1){
            try {
                String number=phoneTwoCmb.getSelectedItem()+"";
                
                if(number.startsWith("")){
                    System.out.println("TRUE");
                }else{
                    List<CustomerDto> customerDtos = customerController.getCustomer("WHERE `phone_one`='"+number+"' OR `phone_two`='"+number+"'");
                    for (CustomerDto customerDto : customerDtos) {
                        phoneOneCmb.setSelectedItem("0"+customerDto.getPhoneOne());
                        phoneTwoCmb.setSelectedItem("0"+customerDto.getPhoneTwo());
                        customerNameTxt.setText(customerDto.getCustomerName());
                        addressTxt.setText(customerDto.getAddress());
                        customer_id=customerDto.getCustomerId();
                    }
                    customer_exist=true;
                }
                
                
            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Phone number seleting error");
            }
        }
    }//GEN-LAST:event_phoneTwoCmbActionPerformed

    private void paymentTypeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentTypeComboActionPerformed
        
    }//GEN-LAST:event_paymentTypeComboActionPerformed

    private void paymentTypeComboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentTypeComboKeyReleased
        
    }//GEN-LAST:event_paymentTypeComboKeyReleased

    private void paymentTypeComboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentTypeComboMouseClicked
//        int index=paymentTypeCombo.getSelectedIndex()+1;
//        System.out.println("Payment Type ID : "+paymentTypeIds.get(index));
    }//GEN-LAST:event_paymentTypeComboMouseClicked

    private void paymentTypeCombo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentTypeCombo2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentTypeCombo2MouseClicked

    private void paymentTypeCombo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentTypeCombo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentTypeCombo2ActionPerformed

    private void paymentTypeCombo2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentTypeCombo2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentTypeCombo2KeyReleased

    private void phoneOneCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneOneCmbActionPerformed
        if(phoneOneCmb.getSelectedIndex()!=-1){
            try {
                String number=phoneOneCmb.getSelectedItem()+"";
                
                List<CustomerDto> customerDtos = customerController.getCustomer("WHERE `phone_one`='"+number+"' OR `phone_two`='"+number+"'");
                for (CustomerDto customerDto : customerDtos) {
                    phoneOneCmb.setSelectedItem("0"+customerDto.getPhoneOne());
                    
                    if (customerDto.getPhoneTwo() == 0) {
                        phoneTwoCmb.setSelectedItem("");
                    }else {
                        phoneTwoCmb.setSelectedItem("0"+customerDto.getPhoneTwo());
                    }
                    customerNameTxt.setText(customerDto.getCustomerName());
                    addressTxt.setText(customerDto.getAddress());
                    customer_id=customerDto.getCustomerId();
                    customerNumberTxt.setText(customerDto.getCustomerNumber());
                }
                
                customer_exist=true;
            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Phone number seleting error");
            }
        }
    }//GEN-LAST:event_phoneOneCmbActionPerformed

    private void phoneOneCmbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phoneOneCmbMouseClicked
        
    }//GEN-LAST:event_phoneOneCmbMouseClicked

    private void phoneOneCmbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneOneCmbKeyReleased
        
    }//GEN-LAST:event_phoneOneCmbKeyReleased

    private void fr_de_chbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fr_de_chbMouseClicked
        
    }//GEN-LAST:event_fr_de_chbMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (phoneOneCmb.getSelectedItem() != null) {
            try {
                setupTableModel(customer_id);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "This customer not have log!", "Error", JOptionPane.ERROR_MESSAGE);
                Log.error(e, phoneOneCmb.getSelectedItem()+" This customer not have log!");
                return;
            }
//            check_customer.addWindowListener(new java.awt.event.WindowAdapter() {
//                @Override
//                public void windowClosed(java.awt.event.WindowEvent e) {
//                    customer_id = null;
//                }
//
//                @Override
//                public void windowClosing(java.awt.event.WindowEvent e) {
//                    customer_id = null;
//                }
//            });
            
            check_customer.setLocationRelativeTo(null);
            check_customer.setVisible(true);
            check_customer.repaint();

            Phone1Label.setText((String) phoneOneCmb.getSelectedItem());
            Phone2Label.setText((String) phoneTwoCmb.getSelectedItem());
            NameLabel.setText(customerNameTxt.getText());
            AddressLabel.setText(addressTxt.getText());
        }else{
            JOptionPane.showMessageDialog(this, "Please select mobile number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void setupTableModel(Integer customer_id) {
        String[] columnNames = {"Item Name", "Quantity", "Per Item Price", "Total Item Price", "Total Order Price", "Delivery Fee"};
        DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);

        ArrayList<OrderDetails[]> orderDetailsList = mainOrderDetailRepositoryImpl.getOrderDetailsByCustomerId(customer_id);

        Set<String> uniqueOrderCodes = new HashSet<>();

        for (OrderDetails[] orderDetailsArray : orderDetailsList) {
            for (OrderDetails orderDetails : orderDetailsArray) {
                dtm.addRow(orderDetails.toArray());
                uniqueOrderCodes.add(orderDetails.getOrderCode());
                System.out.println("> : "+orderDetails.getOrderCode().toString());
            }
        }
        customerOrderDetailsTbl.removeAll();
        customerOrderDetailsTbl.setModel(dtm);

        orderIDCmb.removeAllItems();
        for (String orderCode : uniqueOrderCodes) {
            orderIDCmb.addItem(orderCode.toString());
        }
    }

    
    
    private void filterOrdersByOrderId(String orderCode) {
        String[] columnNames = {"Item Name", "Quantity", "Per Item Price", "Total Item Price", "Delivery Fee", "Total Order Price","Status"};
        DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);

        ArrayList<OrderDetails[]> orderDetailsList = mainOrderDetailRepositoryImpl.getOrderDetailsByCustomerId(customer_id);

        double netTotal = 0.0;

        for (OrderDetails[] orderDetailsArray : orderDetailsList) {
            for (OrderDetails orderDetails : orderDetailsArray) {
                if (orderDetails.getOrderCode().equals(orderCode)) {
                    dtm.addRow(orderDetails.toArray());
                    netTotal = orderDetails.getTotal_order_price();
                    lblDate.setText(orderDetails.getDate());
                }
            }
        }

        customerOrderDetailsTbl.removeAll();
        customerOrderDetailsTbl.setModel(dtm);
        netTotalLbl.setText(String.format("%.2f", netTotal));
    }
    
    private void deliveryOrdersTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deliveryOrdersTableMousePressed
        if (evt.getClickCount() == 2) {
            int selectedRow = deliveryOrdersTable.getSelectedRow();
            if (selectedRow != -1) {
                String status = deliveryOrdersTable.getValueAt(selectedRow, 8).toString();
                
                PosMainUser userDto = null;
                try {
                    userDto = userAccountManagementController.getUserByUserID(LogInForm.userID);
                } catch (Exception ex) {
                    Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                if (!(userDto.getRoleId() == 1 || userDto.getRoleId() == 2)) {
                    switch (status) {
                        case "Pending":
                            btnEdit.setEnabled(true);
                            btnWrapping.setEnabled(true);
                            btnCancel.setEnabled(true);
                            btnActive.setEnabled(false);
                            btnOutForDelivery.setEnabled(false);
                            btnActive.setEnabled(false);
                            btnReturn.setEnabled(false);
                            btnDeliverd.setEnabled(false);
                            break;

                        case "Wrapping":
                            btnEdit.setEnabled(true);
                            btnWrapping.setEnabled(false);
                            btnCancel.setEnabled(true);
                            btnActive.setEnabled(false);
                            btnOutForDelivery.setEnabled(true);
                            btnActive.setEnabled(false);
                            btnReturn.setEnabled(false);
                            btnDeliverd.setEnabled(false);
                            break;

                        case "Out of Delivery":
                            btnEdit.setEnabled(false);
                            btnWrapping.setEnabled(false);
                            btnCancel.setEnabled(false);
                            btnActive.setEnabled(false);
                            btnOutForDelivery.setEnabled(false);
                            btnActive.setEnabled(false);
                            btnReturn.setEnabled(true);
                            btnDeliverd.setEnabled(true);
                            break;

                        case "Delivered":
                            btnEdit.setEnabled(false);
                            btnWrapping.setEnabled(false);
                            btnCancel.setEnabled(false);
                            btnActive.setEnabled(false);
                            btnOutForDelivery.setEnabled(false);
                            btnActive.setEnabled(false);
                            btnReturn.setEnabled(false);
                            btnDeliverd.setEnabled(false);
                            break;

                        case "Return":
                            btnEdit.setEnabled(false);
                            btnWrapping.setEnabled(false);
                            btnCancel.setEnabled(false);
                            btnActive.setEnabled(false);
                            btnOutForDelivery.setEnabled(false);
                            btnActive.setEnabled(false);
                            btnReturn.setEnabled(false);
                            btnDeliverd.setEnabled(false);
                            break;

                        case "Cancel":
                            btnEdit.setEnabled(false);
                            btnWrapping.setEnabled(false);
                            btnCancel.setEnabled(false);
                            btnActive.setEnabled(false);
                            btnOutForDelivery.setEnabled(false);
                            btnActive.setEnabled(false);
                            btnReturn.setEnabled(false);
                            btnDeliverd.setEnabled(false);
                            break;
                        default:
                            throw new AssertionError();
                    }
                }
               
                String deliveryID = deliveryOrdersTable.getValueAt(selectedRow, 0).toString();
                if (!deliveryID.isEmpty()) {
                    try {
                        delivery_id = deliveryID;
                        order_options.setLocationRelativeTo(null);
                        order_options.setSize(935, 115);
                        order_options.setTitle("Actions");
                        
                        // Add a window listener to reset delivery_id when closed
                        order_options.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosing(java.awt.event.WindowEvent e) {
                                delivery_id = null;
                                System.out.println("Window closed. delivery_id reset to null");
                            }
                        });
                        
                        order_options.setVisible(true);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid delivery ID: " + deliveryID);
                        JOptionPane.showMessageDialog(this, "Invalid order code");
                        Log.error(e, "Delivery table error");
                    }
                } else {
                    System.out.println("Delivery ID is empty");
                    JOptionPane.showMessageDialog(this, "Delivery ID is empty");
                }
            } else {
                System.out.println("No row selected");
            }
        }
    }//GEN-LAST:event_deliveryOrdersTableMousePressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        clearText();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void itemComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemComboActionPerformed
        qtyTxt.requestFocus();
    }//GEN-LAST:event_itemComboActionPerformed

    private void customerNumberTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerNumberTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerNumberTxtActionPerformed

    private void deliveryOrdersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deliveryOrdersTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_deliveryOrdersTableMouseClicked

    private void orderIDCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderIDCmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orderIDCmbActionPerformed

    private void radioExchangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioExchangeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_radioExchangeMouseClicked

    private void btnActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActiveActionPerformed
        if(delivery_id!=null){
            try {
                deliveryOrderRepositoryImpl.update(delivery_id, 1);

                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String fromDate = formatter.format(jXDatePicker1.getDate());
                String toDate = formatter.format(jXDatePicker2.getDate());

                int index=0;
                if(paymentTypeCombo2.getSelectedIndex()!=0){
                    index=paymentTypeCombo2.getSelectedIndex();
                    getAllOrders(fromDate,toDate,paymentTypeIds_2.get(index-1));
                }else{
                    getAllOrders(fromDate,toDate,0);
                }
                order_options.dispose();
            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Status Change error");
            }
        }
        clearText();
        delivery_id = null;
    }//GEN-LAST:event_btnActiveActionPerformed

    private void btnWrappingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWrappingActionPerformed
        if(delivery_id!=null){
            try {
                deliveryOrderRepositoryImpl.update(delivery_id, 3);

                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String fromDate = formatter.format(jXDatePicker1.getDate());
                String toDate = formatter.format(jXDatePicker2.getDate());

                int index=0;
                if(paymentTypeCombo2.getSelectedIndex()!=0){
                    index=paymentTypeCombo2.getSelectedIndex();
                    getAllOrders(fromDate,toDate,paymentTypeIds_2.get(index-1));
                }else{
                    getAllOrders(fromDate,toDate,0);
                }
                order_options.dispose();
            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Status Change error");
            }
        }
        clearText();
        delivery_id = null;
    }//GEN-LAST:event_btnWrappingActionPerformed

    private void btnOutForDeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOutForDeliveryActionPerformed
        if(delivery_id!=null){
            try {
                deliveryOrderRepositoryImpl.update(delivery_id, 4);

                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String fromDate = formatter.format(jXDatePicker1.getDate());
                String toDate = formatter.format(jXDatePicker2.getDate());

                int index=0;
                if(paymentTypeCombo2.getSelectedIndex()!=0){
                    index=paymentTypeCombo2.getSelectedIndex();
                    getAllOrders(fromDate,toDate,paymentTypeIds_2.get(index-1));
                }else{
                    getAllOrders(fromDate,toDate,0);
                }
                order_options.dispose();
            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Status Change error");
            }
        }
        clearText();
        delivery_id = null;
    }//GEN-LAST:event_btnOutForDeliveryActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        if(delivery_id!=null){
            try {
                deliveryOrderRepositoryImpl.update(delivery_id, 7);

                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String fromDate = formatter.format(jXDatePicker1.getDate());
                String toDate = formatter.format(jXDatePicker2.getDate());

                int index=0;
                if(paymentTypeCombo2.getSelectedIndex()!=0){
                    index=paymentTypeCombo2.getSelectedIndex();
                    getAllOrders(fromDate,toDate,paymentTypeIds_2.get(index-1));
                }else{
                    getAllOrders(fromDate,toDate,0);
                }
                order_options.dispose();
            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Status Change error");
            }
        }
        clearText();
        delivery_id = null;
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        if(delivery_id!=null){
            try {
                deliveryOrderRepositoryImpl.update(delivery_id, 6);

                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String fromDate = formatter.format(jXDatePicker1.getDate());
                String toDate = formatter.format(jXDatePicker2.getDate());

                int index=0;
                if(paymentTypeCombo2.getSelectedIndex()!=0){
                    index=paymentTypeCombo2.getSelectedIndex();
                    getAllOrders(fromDate,toDate,paymentTypeIds_2.get(index-1));
                }else{
                    getAllOrders(fromDate,toDate,0);
                }
                order_options.dispose();
            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Status Change error");
            }
        }
        clearText();
        delivery_id = null;
    }//GEN-LAST:event_btnReturnActionPerformed

    private void btnDeliverdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeliverdActionPerformed
        if (delivery_id != null) {
            try {
                deliveryOrderRepositoryImpl.update(delivery_id, 5);

                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String fromDate = formatter.format(jXDatePicker1.getDate());
                String toDate = formatter.format(jXDatePicker2.getDate());

                int index = 0;
                if (paymentTypeCombo2.getSelectedIndex() != 0) {
                    index = paymentTypeCombo2.getSelectedIndex();
                    getAllOrders(fromDate, toDate, paymentTypeIds_2.get(index - 1));
                } else {
                    getAllOrders(fromDate, toDate, 0);
                }
                order_options.dispose();
            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Status Change error");
            }
        }
        clearText();
        delivery_id = null;
    }//GEN-LAST:event_btnDeliverdActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int selectedRow = deliveryOrdersTable.getSelectedRow();

        if (selectedRow != -1) {
            String deliveryID = deliveryOrdersTable.getValueAt(selectedRow, 0).toString();
            String orderCodeStr = deliveryOrdersTable.getValueAt(selectedRow, 1).toString();
            if (deliveryID != null && !deliveryID.isEmpty()) {
                orderCode = orderCodeStr;
                System.out.println("orderCode : "+orderCode);
                System.out.println("deliveryID : "+deliveryID);
                delivery_id = deliveryID;

                try {
                    String phoneOne = deliveryOrdersTable.getValueAt(selectedRow, 3) != null ? deliveryOrdersTable.getValueAt(selectedRow, 3).toString() : "";
                    String phoneTwo = deliveryOrdersTable.getValueAt(selectedRow, 4) != null ? deliveryOrdersTable.getValueAt(selectedRow, 4).toString() : "";
                    String customerName = deliveryOrdersTable.getValueAt(selectedRow, 2) != null ? deliveryOrdersTable.getValueAt(selectedRow, 2).toString() : "";
                    Double cod = deliveryOrdersTable.getValueAt(selectedRow, 5) != null ? Double.parseDouble(deliveryOrdersTable.getValueAt(selectedRow, 5).toString()) : 0.0;
                    Double totalAmount = deliveryOrdersTable.getValueAt(selectedRow, 6) != null ? Double.parseDouble(deliveryOrdersTable.getValueAt(selectedRow, 6).toString()) : 0.0;

                    phoneOneCmb.setSelectedItem(phoneOne);
                    phoneTwoCmb.setSelectedItem(phoneTwo);
                    customerNameTxt.setText(customerName);
                    orderCodeTxt.setText(orderCodeStr);

                    totAmountLbl.setText(totalAmount.toString());

                    if (cod == 0.0) {
                        // Card payment
                        paymentTypeCombo.setSelectedIndex(1);
                        PaidAmountTxt.setText(totalAmount.toString());
                        codTxt.setText("0");
                    } else {
                        // Cash payment
                        paymentTypeCombo.setSelectedIndex(0);
                        double remainingAmount = totalAmount - cod;
                        PaidAmountTxt.setText(Double.toString(remainingAmount));
                        codTxt.setText(cod+"");
                    }

                    // Additional logic for updating totals and other details...
                    double subTot = totalAmount - Double.parseDouble(deliveyFeeLbl.getText());
                    subTotAmountLbl.setText(String.format("%.2f", subTot));

                    String order_ID = deliveryOrdersTable.getValueAt(selectedRow, 0).toString();
                    String orderID = deliveryOrderRepositoryImpl.getOrderIDByBillNo(order_ID);

                    if (orderID != null) {
                        Integer oid = Integer.parseInt(orderID);
                        ArrayList<PosMainOrderDetails> orderDetails = mainOrderDetailRepositoryImpl.getOrderDetailsByOrderId(oid);
                        DefaultTableModel itemListTableModel = (DefaultTableModel) itemListTable.getModel();
                        itemListTableModel.setRowCount(0);

                        double totalWeight = 0.00;

                        for (PosMainOrderDetails p : orderDetails) {
                            for (PosMainItem posMainItem : posMainItems) {
                                if (p.getItemId() == posMainItem.getItemId()) {
                                    double qty = p.getQuantity();
                                    double itemWeight = itemWeightList.get(itemIds.indexOf(p.getItemId()));
                                    totalWeight += itemWeight * qty;

                                    itemListTableModel.addRow(new Object[]{
                                        p.getItemId(),
                                        posMainItem.getItemName(),
                                        p.getPerItemPrice(),
                                        qty,
                                        p.getPerItemDiscountPrice(),
                                        p.getTotalDiscountPrice(),
                                        p.getTotalPrice()
                                    });
                                }
                            }
                        }

                        double deliveryFee = calculateDeliveryFee(totalWeight);
                        deliveyFeeLbl.setText(String.format("%.2f", deliveryFee));
                        totAmountLbl.setText(String.format("%.2f", totalAmount + deliveryFee));

                    } else {
                        System.out.println("Order ID not found for the given bill number.");
                    }
                } catch (NullPointerException e) {
                    phoneTwoCmb.setSelectedItem(null);
                    System.out.println("NullPointerException: " + e.getMessage());
                    Log.error(e, "Order Edit error");
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException: " + e.getMessage());
                    Log.error(e, "Order Edit error");
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                    Log.error(e, "Order Edit error");
                }
                order_options.dispose();
            } else {
                System.out.println("Order code is empty.");
            }
        } else {
            System.out.println("No row selected.");
        }

        updateTotals();
    }//GEN-LAST:event_btnEditActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(DeliveryOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DeliveryOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DeliveryOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DeliveryOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new DeliveryOrders().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddressLabel;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JTextField PaidAmountTxt;
    private javax.swing.JLabel Phone1Label;
    private javax.swing.JLabel Phone2Label;
    private javax.swing.JButton addBtn;
    private javax.swing.JTextArea addressTxt;
    private javax.swing.JLabel amountTotTxt;
    private javax.swing.JButton btnActive;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDeliverd;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnOutForDelivery;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnWrapping;
    private javax.swing.JDialog check_customer;
    private javax.swing.JLabel codTotTxt;
    private javax.swing.JTextField codTxt;
    private javax.swing.JTextField customerNameTxt;
    private javax.swing.JTextField customerNumberTxt;
    private javax.swing.JTable customerOrderDetailsTbl;
    private javax.swing.JLabel deliveriesTotTxt;
    private javax.swing.JLabel deliveryChargeTotTxt;
    private java.awt.Panel deliveryFormDetailPanel;
    private org.jdesktop.swingx.JXTable deliveryOrdersTable;
    private javax.swing.JLabel deliveyFeeLbl;
    private javax.swing.Box.Filler filler1;
    private java.awt.Checkbox fr_de_chb;
    private javax.swing.JComboBox<String> itemCombo;
    private org.jdesktop.swingx.JXTable itemListTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel netTotalLbl;
    private javax.swing.JTextField orderCodeTxt;
    private javax.swing.JComboBox<String> orderIDCmb;
    private javax.swing.JDialog order_options;
    private javax.swing.JComboBox<String> paymentTypeCombo;
    private javax.swing.JComboBox<String> paymentTypeCombo2;
    private javax.swing.JComboBox<String> phoneOneCmb;
    private javax.swing.JComboBox<String> phoneTwoCmb;
    private javax.swing.JButton printAllOrdersBtn;
    private javax.swing.JTextField qtyTxt;
    private java.awt.Checkbox radioExchange;
    private javax.swing.JTextArea remarkTxt;
    private javax.swing.JButton removeBtn;
    private javax.swing.JLabel returnsTotTxt;
    private javax.swing.JButton saveOrderBtn;
    private javax.swing.JButton saveOrderBtn1;
    private javax.swing.JLabel subTotAmountLbl;
    private javax.swing.JLabel totAmountLbl;
    private javax.swing.JLabel total_orders_count_txt;
    private javax.swing.JTextField weightTxt;
    // End of variables declaration//GEN-END:variables

    private void getItems() {
        try {
            String quary="WHERE visible=1 AND status=1";
            ArrayList<MainItemDto> itemList=newItemController.getAllItems(quary);
            
            for(MainItemDto mainItem:itemList){
                itemCombo.addItem(mainItem.getItemName());
                itemIds.add(mainItem.getItemId());
                itemPriceList.add(mainItem.getUnitPrice());
                itemWeightList.add(mainItem.getWeight());
            }
            System.out.println(itemWeightList);
        } catch (Exception ex) {
            Log.error(DeliveryOrder.class, "", ex);
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getPaymentTypes() {
        try {
            String quary="WHERE visible=1 AND status=1";
            ArrayList<PaymentTypeDto> paymentTypes=paymentTypesController.getPaymentTypes(quary);
            
            for(PaymentTypeDto typeDto:paymentTypes){
                paymentTypeCombo.addItem(typeDto.getName());
                paymentTypeIds.add(typeDto.getPaymentTypeId());
                paymentTypeCombo2.addItem(typeDto.getName());
                 paymentTypeIds_2.add(typeDto.getPaymentTypeId());
            }
            
        } catch (Exception ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "getPaymentTypes error");
        }
    }

    private void getAllOrders(String fromDate, String toDate, Integer paymentType) {
        try {
            ArrayList<DeliveryOrder> deliveryOrderDtos = deliveryOrderRepositoryImpl.getAllDuration(fromDate, toDate, paymentType, 0);
            ArrayList<DeliveryOrderAmounts> deliveryOrderAmountDto = deliveryOrderRepositoryImpl.getCalculation(fromDate, toDate, paymentType);

            DefaultTableModel dtm = (DefaultTableModel) deliveryOrdersTable.getModel();
            dtm.setRowCount(0);

            String status = null;
            boolean isPrint = false;

            int orderId = 0;
            int count = 0;
            double totAmount = 0.00;
            double totDeliveryFee = 0.00;
            double totCod = 0.00;
            double totReturns = 0.00;
            double totDeliveries = 0.00;

            for (DeliveryOrder dto : deliveryOrderDtos) {
                count++;

                if (dto.getStatusType() == 1) {
                    status = "Active";
                } else if (dto.getStatusType() == 2) {
                    status = "Pending";
                } else if (dto.getStatusType() == 3) {
                    status = "Wrapping";
                } else if (dto.getStatusType() == 4) {
                    status = "Out of Delivery";
                } else if (dto.getStatusType() == 5) {
                    status = "Delivered";
                } else if (dto.getStatusType() == 6) {
                    status = "Return";
                } else {
                    status = "Cancel";
                }

                isPrint = dto.getIsPrint() == 1;

                Object[] rowData = {
                    dto.getOrderId(),
                    dto.getOrderCode(),
                    dto.getCustomerName(),
                    dto.getPhoneOne(),
                    dto.getPhoneTwo(),
                    dto.getCod(),
                    dto.getGrandTotalPrice(),
                    dto.getCreateDate(),
                    status
                };

                dtm.addRow(rowData);

                orderId = dto.getOrderId();
            }

            for (DeliveryOrderAmounts amounts : deliveryOrderAmountDto) {
                totAmount = amounts.getTotalAmount();
                totDeliveryFee = amounts.getTotalDeliveryCharge();
                totCod = amounts.getTotalCod();
                totReturns = amounts.getTotalReturns();
                totDeliveries = amounts.getTotalDeliverds();
            }

            amountTotTxt.setText(totAmount + "");
            deliveryChargeTotTxt.setText((totDeliveryFee) + "");
            codTotTxt.setText(totCod + "");
            returnsTotTxt.setText(totReturns + "");
            total_orders_count_txt.setText(count + "");
            deliveriesTotTxt.setText(totDeliveries+"");

        } catch (Exception ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "getAllOrders error");
        }
        
        // Assuming status is at column index 7 in your table
        deliveryOrdersTable.getColumnModel().getColumn(8).setCellRenderer(new StatusCellRenderer());
    }


    private void printBill(Integer orderId) {
        try {
            HashMap<String, Object> hm = new HashMap<>();
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            Double paidAmount=Double.parseDouble(weightTxt.getText());
            
            String orderCode=orderCodeTxt.getText();
            hm.put("DATE", formatter.format(date));
            hm.put("PAID_AMOUNT", paidAmount);
            hm.put("ORDER_ID", orderId);
            hm.put("WEIGHT", weightTxt.getText());
            
            JasperReport jr = JasperCompileManager.compileReport("D:/Unical/Unical-Pos-System/reports/Delivery_Receipt.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(jr, hm, DBCon.getDatabaseConnection());
//            JasperViewer view = new JasperViewer(jp, false);
//            view.setVisible(true);

            JasperExportManager.exportReportToPdfFile(jp,"C:/Users/Sanjuka/Documents/Petal Pink/Payment recepts/"+orderCode+".pdf");
        
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "printBill error");
        }
    }

    private void setCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        jXDatePicker1.setDate(date);
        jXDatePicker2.setDate(date);
    }

    private void getPhone_Number_One() {
        try {
            Vector<String> numbers=new Stack<>();
            
            List<CustomerDto> customerDtos = customerController.getCustomer("");
            
            for (CustomerDto customerDto : customerDtos) {
               numbers.add("0"+customerDto.getPhoneOne());
               numbers.add("0"+customerDto.getPhoneTwo());
               customersList.add(customerDto.getCustomerId());
            }
            
            AutoGenerator autoGenerator = new AutoGenerator();
            autoGenerator.completeText(numbers, phoneOneCmb);
        } catch (Exception ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "getPhone_Number_One error");
        }
    }
    
    private void getPhone_Number_Two() {
        try {
            Vector<String> numbers=new Stack<>();
            
            List<CustomerDto> customerDtos = customerController.getCustomer("");
            
            for (CustomerDto customerDto : customerDtos) {
               numbers.add("0"+customerDto.getPhoneOne());
               numbers.add("0"+customerDto.getPhoneTwo());
            }
            
            AutoGenerator autoGenerator = new AutoGenerator();
            autoGenerator.completeText(numbers, phoneTwoCmb);
        } catch (Exception ex) {
            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "getPhone_Number_Two error");
        }
    }
    

    private void clearText() {
        phoneOneCmb.setSelectedIndex(-1);
        phoneTwoCmb.setSelectedIndex(-1);
        addressTxt.setText("");
        remarkTxt.setText("");
        customerNameTxt.setText("");
        customerNumberTxt.setText("");
        orderCodeTxt.setText("");
//        phone2Txt.setText("");
    }

    private void getOrderCode() {
//        try {
//            Vector<Integer> numbers=new Stack<>();
//            
//            List<TestModel> testModels = deliveryOrderController.getAllOrders(null, "");
//            
//            for (TestModel testModel : testModels) {
//               numbers.add(testModel.get);
//            }
//            
//            AutoGenerator autoGenerator = new AutoGenerator();
//            autoGenerator.completeText(numbers, jComboBox1);
//        } catch (ClassNotFoundException | SQLException ex) {
////            Log.info(PatientSave.class, "" + ex);
////            Log.error(PatientSave.class, "" + ex);
//            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void instalGUI() {
        phoneTwoCmb.setBackground(Color.WHITE);
//        jComboBox1.setBorder(border);
    }

//    private void textNull() {
//        phone2Txt.setText(null);
//    }
    
}
