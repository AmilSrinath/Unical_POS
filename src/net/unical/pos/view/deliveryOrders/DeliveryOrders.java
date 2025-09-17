/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.view.deliveryOrders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.java.accessibility.util.SwingEventMonitor;
import java.awt.Color;
import java.awt.Dimension;
import java.lang.reflect.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
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
import net.unical.pos.api.ApiClient;
import net.unical.pos.configurations.AutoGenerator;
import net.unical.pos.controller.CustomerController;
import net.unical.pos.controller.DeliveryOrderController;
import net.unical.pos.controller.DiscountController;
import net.unical.pos.controller.MainItemController;
import net.unical.pos.controller.OrderTypeController;
import net.unical.pos.controller.PaymentTypesController;
import net.unical.pos.controller.StatusTypeController;
import net.unical.pos.controller.UserAccountManagementController;
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.dto.CustomerDto;
import net.unical.pos.dto.DeliveryOrderDto;
import net.unical.pos.dto.DiscountDto;
import net.unical.pos.dto.MainItemDto;
import net.unical.pos.dto.OrderDetailsDto;
import net.unical.pos.dto.OrderDto;
import net.unical.pos.dto.OrderTypeDto;
import net.unical.pos.dto.PaymentTypeDto;
import net.unical.pos.dto.UserDto;
import net.unical.pos.dto.WebsiteDto.WebsiteOrderDetailDto;
import net.unical.pos.dto.WebsiteDto.WebsiteOrderDto;
import net.unical.pos.log.Log;
import net.unical.pos.model.CustomerDataByInquirySearch;
import net.unical.pos.model.CustomerModel;
import net.unical.pos.model.DeliveryOrder;
import net.unical.pos.model.DeliveryOrderAmounts;
import net.unical.pos.model.InquiryModel;
import net.unical.pos.model.OrderDetails;
import net.unical.pos.model.OrderModel;
import net.unical.pos.model.PosMainItem;
import net.unical.pos.model.PosMainOrder;
import net.unical.pos.model.PosMainOrderDetails;
import net.unical.pos.model.PosMainUser;
import net.unical.pos.model.StatusTypeModel;
import net.unical.pos.model.TestModel;
import net.unical.pos.repository.custom.MainItemCategoryRepositoryCustom;
import net.unical.pos.repository.impl.CustomerRepositoryImpl;
import net.unical.pos.repository.impl.DeliveryOrderRepositoryImpl;
import net.unical.pos.repository.impl.InquiryRepositoryImpl;
import net.unical.pos.repository.impl.MainItemRepositoryImpl;
import net.unical.pos.repository.impl.MainOrderDetailRepositoryImpl;
import net.unical.pos.repository.impl.MainOrderRepositoryImpl;
import net.unical.pos.repository.impl.StatusTypeRepositoryImpl;
import net.unical.pos.repository.impl.TestClass;
import net.unical.pos.service.impl.CustomerServiceImpl;
import net.unical.pos.view.home.Dashboard;
import net.unical.pos.view.main.LogInForm;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.json.JSONObject;

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

    private ArrayList<Integer> itemIds = new ArrayList<>();
    private ArrayList<Double> itemPriceList = new ArrayList<>();
    private ArrayList<Integer> paymentTypeIds = new ArrayList<>();
    private ArrayList<Integer> paymentTypeIds_2 = new ArrayList<>();
    private ArrayList<Integer> customersList = new ArrayList<>();
    private ArrayList<Double> itemWeightList = new ArrayList<>();
    private ArrayList<PosMainItem> posMainItems;
    private ArrayList<OrderModel> orders = new ArrayList<>();
    public static List<StatusTypeModel> statusTypes = new ArrayList<>();

    private MainOrderRepositoryImpl mainOrderRepositoryImpl;
    private MainItemRepositoryImpl mainItemRepositoryImpl;
    private MainOrderDetailRepositoryImpl mainOrderDetailRepositoryImpl;
    private DeliveryOrderController deliveryOrderController;
    private DeliveryOrderRepositoryImpl deliveryOrderRepositoryImpl;
    private StatusTypeRepositoryImpl statusTypeRepositoryImpl;
    private InquiryRepositoryImpl inquiryRepositoryImpl;
    private DiscountController discountController = new DiscountController();
    private OrderTypeController orderTypeController = new OrderTypeController();

    private CustomerController customerController;
    private UserAccountManagementController userAccountManagementController;
    CustomerRepositoryImpl customerRepositoryImpl = new CustomerRepositoryImpl();
    private StatusTypeController statusTypeController;
    TestClass testClass = new TestClass();

    DefaultTableModel itemListTableModel = null;
    DefaultTableModel orderListTableModel = null;
    int default_paymentType = 0;
    String orderCode = null;
    String delivery_id = null;
    Integer customer_id = null;
    boolean customer_exist = false;
    private double discount = 0.0;
    private String symbol = "%";
    private boolean isDoublePressedItemTable = false;
    private Double clickedItemPrice = 0.0;
    private int row = -1;
    private boolean isOrder = false;
    private boolean isChooseComboDiscount = false;
    private boolean hasRowLevelDiscount = false;
    private boolean comboSelected = false;
    private boolean comboOrderType = false;
    private boolean isWebsiteOrder = false;
    private List<WebsiteOrderDto> websiteOrders = new ArrayList<>();
    private List<WebsiteOrderDetailDto> itemList = new ArrayList<>();
    private static boolean isOrderThreadRunning = false;

    public DeliveryOrders(Dashboard dashboard) throws FileNotFoundException, IOException, Exception {
        initComponents();
        setDiscountCmb();
        addTableChangeLisener();

        this.dashboard = dashboard;

        this.mainOrderRepositoryImpl = new MainOrderRepositoryImpl();
        this.newItemController = new MainItemController();
        this.paymentTypesController = new PaymentTypesController();
        this.deliveryOrderController = new DeliveryOrderController();
        this.customerController = new CustomerController();
        this.deliveryOrderRepositoryImpl = new DeliveryOrderRepositoryImpl();
        this.mainOrderDetailRepositoryImpl = new MainOrderDetailRepositoryImpl();
        this.mainItemRepositoryImpl = new MainItemRepositoryImpl();
        this.userAccountManagementController = new UserAccountManagementController();
        this.statusTypeRepositoryImpl = new StatusTypeRepositoryImpl();
        this.inquiryRepositoryImpl = new InquiryRepositoryImpl();
        this.statusTypeController = new StatusTypeController();

        itemListTableModel = (DefaultTableModel) itemListTable.getModel();
        orderListTableModel = (DefaultTableModel) deliveryOrdersTable.getModel();

        instalGUI();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date current_date = new Date();
        String CURRENT_DATE = dateFormat.format(current_date);

        getPhone_Number_One();
        getPhone_Number_Two();
        getOrderCode();
        getItems();
        getPaymentTypes();
        statusTypes = statusTypeRepositoryImpl.getAllStatusTypeByRegID(1);
        getAllOrders(CURRENT_DATE, CURRENT_DATE, default_paymentType);
        setCurrentDate();

        fr_de_chb.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                if (fr_de_chb.getState()) {
                    System.out.println("Check eka hari nam mekata enava");
                    deliveyFeeLbl.setText("0.00");
                } else {
                    System.out.println("Check eka empty nam mekata enava");
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

        System.out.println("userRole : " + LogInForm.userID);

        PosMainUser userDto = userAccountManagementController.getUserByUserID(LogInForm.userID);

        if (userDto.getRoleId() == 1 || userDto.getRoleId() == 2) {
            deliveryFormDetailPanel.setVisible(true);
        } else {
            deliveryFormDetailPanel.setVisible(false);
        }

    }

    public DeliveryOrders() {

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
        btnChecking = new javax.swing.JButton();
        btnReturning = new javax.swing.JButton();
        btnSpecialNote = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        remark = new javax.swing.JDialog();
        btnSaveRemark = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        txtRemark = new javax.swing.JTextField();
        discountInfo = new javax.swing.JDialog();
        cmbDiscount = new javax.swing.JComboBox<>();
        lblDiscountSelected = new javax.swing.JLabel();
        txtCustomeDiscount = new javax.swing.JTextField();
        cmbSymbol = new javax.swing.JComboBox<>();
        lblIsCustom = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        applyBtn = new javax.swing.JButton();
        isDiscountForOrder = new javax.swing.JCheckBox();
        clearBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        orderCodeTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        addressTxt = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarkTxt = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        customerNameTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        codTxt = new javax.swing.JTextField();
        saveOrderBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        itemCombo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        qtyTxt = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        itemListTable = new org.jdesktop.swingx.JXTable();
        btnDiscount = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
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
        jLabel13 = new javax.swing.JLabel();
        subTotAmountLbl = new javax.swing.JLabel();
        deliveyFeeLbl = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        totAmountLbl = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        discountLabel = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        cmbOrderType = new javax.swing.JComboBox<>();
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
        btnSyncWebsiteOrders = new javax.swing.JButton();
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
        btnReturn.setText("Returned");
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
        btnOutForDelivery.setText("Despatch");
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

        btnChecking.setBackground(new java.awt.Color(153, 153, 153));
        btnChecking.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnChecking.setForeground(new java.awt.Color(255, 255, 255));
        btnChecking.setText("Checking");
        btnChecking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckingActionPerformed(evt);
            }
        });

        btnReturning.setBackground(new java.awt.Color(204, 102, 255));
        btnReturning.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnReturning.setForeground(new java.awt.Color(255, 255, 255));
        btnReturning.setText("Returning");
        btnReturning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturningActionPerformed(evt);
            }
        });

        btnSpecialNote.setBackground(new java.awt.Color(255, 204, 255));
        btnSpecialNote.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSpecialNote.setForeground(new java.awt.Color(255, 255, 255));
        btnSpecialNote.setText("Special Note");
        btnSpecialNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpecialNoteActionPerformed(evt);
            }
        });

        jLabel34.setForeground(new java.awt.Color(0, 51, 255));
        jLabel34.setText("Action");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnOutForDelivery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnActive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDeliverd, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnWrapping, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(btnReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnReturning, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(btnChecking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSpecialNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabel34)))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnReturning, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReturn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeliverd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOutForDelivery, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActive, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnWrapping, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSpecialNote, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChecking, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout order_optionsLayout = new javax.swing.GroupLayout(order_options.getContentPane());
        order_options.getContentPane().setLayout(order_optionsLayout);
        order_optionsLayout.setHorizontalGroup(
            order_optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(order_optionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        order_optionsLayout.setVerticalGroup(
            order_optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, order_optionsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        remark.setAlwaysOnTop(true);
        remark.setResizable(false);

        btnSaveRemark.setBackground(new java.awt.Color(51, 153, 0));
        btnSaveRemark.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSaveRemark.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveRemark.setText("Save");
        btnSaveRemark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveRemarkActionPerformed(evt);
            }
        });

        jLabel35.setForeground(new java.awt.Color(0, 51, 255));
        jLabel35.setText("Enter Special Note");

        javax.swing.GroupLayout remarkLayout = new javax.swing.GroupLayout(remark.getContentPane());
        remark.getContentPane().setLayout(remarkLayout);
        remarkLayout.setHorizontalGroup(
            remarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remarkLayout.createSequentialGroup()
                .addGroup(remarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(remarkLayout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addGroup(remarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, remarkLayout.createSequentialGroup()
                                .addComponent(btnSaveRemark, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))))
                    .addGroup(remarkLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(txtRemark, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        remarkLayout.setVerticalGroup(
            remarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, remarkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(txtRemark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSaveRemark)
                .addGap(11, 11, 11))
        );

        discountInfo.setMinimumSize(new java.awt.Dimension(650, 160));

        cmbDiscount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a discount" }));
        cmbDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDiscountActionPerformed(evt);
            }
        });

        txtCustomeDiscount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCustomeDiscount.setText("0.00");
        txtCustomeDiscount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCustomeDiscountMouseClicked(evt);
            }
        });
        txtCustomeDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomeDiscountActionPerformed(evt);
            }
        });

        cmbSymbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSymbolActionPerformed(evt);
            }
        });

        cancelBtn.setBackground(new java.awt.Color(204, 0, 51));
        cancelBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        applyBtn.setBackground(new java.awt.Color(51, 153, 0));
        applyBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        applyBtn.setForeground(new java.awt.Color(255, 255, 255));
        applyBtn.setText("Apply");
        applyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyBtnActionPerformed(evt);
            }
        });

        isDiscountForOrder.setSelected(true);
        isDiscountForOrder.setText("discount for order");
        isDiscountForOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isDiscountForOrderActionPerformed(evt);
            }
        });

        clearBtn.setBackground(new java.awt.Color(153, 153, 153));
        clearBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout discountInfoLayout = new javax.swing.GroupLayout(discountInfo.getContentPane());
        discountInfo.getContentPane().setLayout(discountInfoLayout);
        discountInfoLayout.setHorizontalGroup(
            discountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(discountInfoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(discountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(discountInfoLayout.createSequentialGroup()
                        .addComponent(cmbDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDiscountSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(discountInfoLayout.createSequentialGroup()
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(applyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(discountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(discountInfoLayout.createSequentialGroup()
                        .addComponent(txtCustomeDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbSymbol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(lblIsCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(discountInfoLayout.createSequentialGroup()
                        .addComponent(isDiscountForOrder)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        discountInfoLayout.setVerticalGroup(
            discountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(discountInfoLayout.createSequentialGroup()
                .addGroup(discountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, discountInfoLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(discountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCustomeDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(cmbSymbol)
                            .addComponent(lblIsCustom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(discountInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(discountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDiscountSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(discountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn)
                    .addComponent(applyBtn)
                    .addComponent(isDiscountForOrder)
                    .addComponent(clearBtn))
                .addContainerGap(54, Short.MAX_VALUE))
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

        jLabel1.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Order Code");

        addressTxt.setColumns(20);
        addressTxt.setRows(5);
        jScrollPane3.setViewportView(addressTxt);

        remarkTxt.setColumns(20);
        remarkTxt.setRows(5);
        jScrollPane1.setViewportView(remarkTxt);

        jLabel2.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Address :");

        jLabel3.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Phone");

        jLabel7.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Remark :");

        jLabel5.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Customer Name");

        jLabel4.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("COD :");

        codTxt.setFont(new java.awt.Font("Poppins SemiBold", 1, 14)); // NOI18N
        codTxt.setForeground(new java.awt.Color(153, 0, 0));

        saveOrderBtn.setBackground(new java.awt.Color(0, 102, 153));
        saveOrderBtn.setFont(new java.awt.Font("Poppins Medium", 1, 12)); // NOI18N
        saveOrderBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveOrderBtn.setText("Save Order");
        saveOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveOrderBtnActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        itemCombo.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        itemCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemComboActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        jLabel8.setText("Select Item :");

        qtyTxt.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        qtyTxt.setText("1");
        qtyTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtyTxtActionPerformed(evt);
            }
        });

        addBtn.setBackground(new java.awt.Color(51, 153, 0));
        addBtn.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        removeBtn.setBackground(new java.awt.Color(204, 0, 0));
        removeBtn.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
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
                "ID", "Item Name", "Item Price", "Qty", "Discount", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        itemListTable.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        itemListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemListTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                itemListTableMousePressed(evt);
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

        btnDiscount.setBackground(new java.awt.Color(153, 153, 153));
        btnDiscount.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        btnDiscount.setForeground(new java.awt.Color(255, 255, 255));
        btnDiscount.setText("Discount");
        btnDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscountActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        jLabel9.setText("Qty :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(itemCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(qtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(removeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(165, 165, 165)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(qtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(removeBtn)
                    .addComponent(btnDiscount))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );

        paymentTypeCombo.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
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

        jLabel12.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        jLabel12.setText("Payment Type :");

        jLabel14.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Paid Amount :");

        weightTxt.setFont(new java.awt.Font("Poppins SemiBold", 0, 13)); // NOI18N

        saveOrderBtn1.setBackground(new java.awt.Color(153, 153, 0));
        saveOrderBtn1.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        saveOrderBtn1.setForeground(new java.awt.Color(255, 255, 255));
        saveOrderBtn1.setText("Add");
        saveOrderBtn1.setPreferredSize(new java.awt.Dimension(52, 25));
        saveOrderBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveOrderBtn1ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
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

        fr_de_chb.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        fr_de_chb.setForeground(new java.awt.Color(0, 0, 0));
        fr_de_chb.setLabel("Free Ship");
        fr_de_chb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fr_de_chbMouseClicked(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 102, 153));
        jButton2.setFont(new java.awt.Font("Poppins Light", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Check");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(51, 153, 0));
        jButton7.setFont(new java.awt.Font("Poppins Light", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Edit");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(0, 0, 0));
        jButton8.setFont(new java.awt.Font("Poppins Light", 1, 12)); // NOI18N
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

        jLabel30.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Customer Number :");

        PaidAmountTxt.setFont(new java.awt.Font("Poppins SemiBold", 1, 12)); // NOI18N
        PaidAmountTxt.setForeground(new java.awt.Color(153, 0, 0));

        radioExchange.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        radioExchange.setForeground(new java.awt.Color(0, 0, 0));
        radioExchange.setLabel("Exchange");
        radioExchange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioExchangeMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Poppins SemiBold", 1, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 153));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Sub Total :");

        subTotAmountLbl.setFont(new java.awt.Font("Poppins SemiBold", 1, 15)); // NOI18N
        subTotAmountLbl.setForeground(new java.awt.Color(0, 102, 153));
        subTotAmountLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        subTotAmountLbl.setText("0.00");

        deliveyFeeLbl.setFont(new java.awt.Font("Poppins SemiBold", 1, 15)); // NOI18N
        deliveyFeeLbl.setForeground(new java.awt.Color(0, 153, 0));
        deliveyFeeLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel11.setFont(new java.awt.Font("Poppins SemiBold", 1, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Delivery Fee :");

        jLabel10.setFont(new java.awt.Font("Poppins SemiBold", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Grand Total :");

        totAmountLbl.setFont(new java.awt.Font("Poppins SemiBold", 1, 15)); // NOI18N
        totAmountLbl.setForeground(new java.awt.Color(204, 0, 0));
        totAmountLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totAmountLbl.setText("0.00");

        jLabel36.setFont(new java.awt.Font("Poppins SemiBold", 1, 15)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel36.setText("Discount");

        discountLabel.setFont(new java.awt.Font("Poppins SemiBold", 1, 15)); // NOI18N
        discountLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        discountLabel.setText("0.00");

        jLabel37.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        jLabel37.setText("Order Type      :");

        cmbOrderType.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        cmbOrderType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbOrderTypeMouseClicked(evt);
            }
        });
        cmbOrderType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbOrderTypeActionPerformed(evt);
            }
        });
        cmbOrderType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbOrderTypeKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customerNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(PaidAmountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveOrderBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(codTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fr_de_chb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioExchange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 283, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(saveOrderBtn)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(totAmountLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(subTotAmountLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(deliveyFeeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel36)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(discountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbOrderType, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(weightTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(customerNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(phoneOneCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(orderCodeTxt))
                                    .addComponent(phoneTwoCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(paymentTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneOneCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneTwoCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(orderCodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customerNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jButton8)
                            .addComponent(jButton2))))
                .addGap(33, 33, 33)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(cmbOrderType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(paymentTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PaidAmountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveOrderBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(weightTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radioExchange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(fr_de_chb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subTotAmountLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(discountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliveyFeeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totAmountLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveOrderBtn)
                .addGap(25, 25, 25))
        );

        deliveryOrdersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Order Code", "Customer Name", "Phone One", "Phone Two", "COD", "Total Amount", "Order Type", "Date", "Delivery Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
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
        deliveryOrdersTable.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
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

        jXDatePicker1.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N

        jButton1.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        total_orders_count_txt.setFont(new java.awt.Font("Poppins Light", 1, 15)); // NOI18N
        total_orders_count_txt.setForeground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Poppins Light", 1, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Total Orders :");

        paymentTypeCombo2.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
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

        jLabel16.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Payment Type");

        jXDatePicker2.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("From");

        jLabel19.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("To");

        btnSyncWebsiteOrders.setText("Sync Website Orders");
        btnSyncWebsiteOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSyncWebsiteOrdersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(410, 410, 410))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(paymentTypeCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSyncWebsiteOrders)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total_orders_count_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(total_orders_count_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnSyncWebsiteOrders, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(paymentTypeCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane6)
                                .addGap(0, 0, 0))))
                    .addComponent(deliveryFormDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(deliveryFormDetailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void orderCodeTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderCodeTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orderCodeTxtActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:

        Log.info(DeliveryOrder.class, "Click Add item button");

        double totalAmount = 0.0;
        double totalWeight = 0.0;

        // Validate quantity
        if (qtyTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter quantity (QTY)");
            return;
        }

        int itemIndex = itemCombo.getSelectedIndex();
        if (itemIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item");
            return;
        }

        double qty = Double.parseDouble(qtyTxt.getText());

        // Get item details
        String itemName = (String) itemCombo.getSelectedItem();
        int itemId = itemIds.get(itemIndex);
        double itemPrice = itemPriceList.get(itemIndex);
        double itemWeight = itemWeightList.get(itemIndex);

        Log.info(DeliveryOrder.class, "Item is " + itemName);

        // Apply discount only if item row was double-clicked
        double discount = isDoublePressedItemTable ? setDiscount(itemPrice) : 0.0;
        double totalDiscount = discount * qty;
        double amount = (itemPrice * qty) - totalDiscount;

        boolean itemFound = false;

        // Check if item already exists in the table
        for (int i = 0; i < itemListTableModel.getRowCount(); i++) {
            int existingItemId = (Integer) itemListTableModel.getValueAt(i, 0);
            if (existingItemId == itemId) {
                // Merge quantities
                double existingQty = ((Number) itemListTableModel.getValueAt(i, 3)).doubleValue();
                double newQty = existingQty + qty;

                double existingDiscount = ((Number) itemListTableModel.getValueAt(i, 4)).doubleValue();
                double newDiscount = existingDiscount + totalDiscount;

                double newAmount = (itemPrice * newQty) - newDiscount;

                itemListTableModel.setValueAt(newQty, i, 3);       // Qty
                itemListTableModel.setValueAt(newDiscount, i, 4);  // Total Discount
                itemListTableModel.setValueAt(newAmount, i, 5);    // Final Amount

                itemFound = true;
                break;
            }
        }

        if (!itemFound) {
            // New item row
            Object[] itemRow = {
                itemId,
                itemName,
                itemPrice,
                qty,
                totalDiscount,
                amount
            };
            itemListTableModel.addRow(itemRow);
        }

        // Refresh delivery fee and totals
        Properties props = loadProperties();
        if (props != null) {
            calculateDeliveryFee(props);
        }
        System.out.println("add update call wenna calin :" + isChooseComboDiscount);
        addUpdateTotals(); // Final recalculation

        // Reset double-click flag
        isDoublePressedItemTable = false;
    }//GEN-LAST:event_addBtnActionPerformed

    private Double setDiscount(Double itemPrice) {
        System.out.println("symbol : " + symbol);
        Double discounts = 0.0;
        if (isChooseComboDiscount || cmbSymbol.getSelectedItem().equals("%")) {
            System.out.println("Symbol" + symbol);
            System.out.println("Mekata envnm waradi");
            discounts = itemPrice * this.discount / 100;
            return discounts;
        } else if (cmbSymbol.getSelectedItem().equals("Rs")) {
            System.out.println("Mekata thamai enne");
            discounts = this.discount;
            return discounts;
        } else {
            discounts = itemPrice * this.discount / 100;
            return discounts;
        }
    }

    private void updateTotals() {
        double totalAmount = 0.0;
        double totalWeight = 0.0;
        double totalDiscount = 0.0;

        int rowCount = itemListTable.getRowCount();

        if (rowCount == 0) {
            subTotAmountLbl.setText("0.00");
            totAmountLbl.setText("0.00");
            discountLabel.setText("0.00");
            weightTxt.setText("0.00");
            codTxt.setText("0.00");
            return;
        }

        for (int i = 0; i < rowCount; i++) {
            // Make sure itemId column (0) is castable to Integer
            int itemId;
            try {
                itemId = (Integer) itemListTable.getValueAt(i, 0);
            } catch (Exception e) {
                System.err.println("Invalid item ID at row " + i);
                continue;
            }

            double price = ((Number) itemListTable.getValueAt(i, 2)).doubleValue();
            int qty = ((Number) itemListTable.getValueAt(i, 3)).intValue();

            // Calculate total price
            totalAmount += price * qty;

            // Per-item discount (if applied)
            if (isDoublePressedItemTable) {
                double rowDiscount = ((Number) itemListTable.getValueAt(i, 4)).doubleValue();
                totalDiscount += rowDiscount;
            }

            // Weight calculation (safe index check)
            int weightIndex = itemIds.indexOf(itemId);
            if (weightIndex >= 0 && weightIndex < itemWeightList.size()) {
                double itemWeight = itemWeightList.get(weightIndex);
                totalWeight += itemWeight * qty;
            } else {
                System.err.println("Item weight not found for item ID: " + itemId);
            }
        }

        // If full-order discount is used
        if (!isDoublePressedItemTable) {
            System.out.println("Calculating full order discount...");
            totalDiscount = setDiscount(totalAmount);
            if (totalDiscount > totalAmount) {
                totalDiscount = totalAmount; // safety check
            }
            System.out.println("Full order discount : " + totalDiscount);
        }

        // Parse delivery fee
        double deliveryFee = 0.0;
        try {
            deliveryFee = Double.parseDouble(deliveyFeeLbl.getText());
        } catch (NumberFormatException e) {
            System.err.println("Invalid delivery fee format, defaulting to 0.");
        }

        // Final calculations
        double subTotal = totalAmount - totalDiscount;
        double grandTotal = totalAmount + deliveryFee - totalDiscount;

        if (grandTotal < 0) {
            grandTotal = 0;
        }

        // Set values to labels
        subTotAmountLbl.setText(String.format("%.2f", subTotal));
        discountLabel.setText(String.format("-%.2f", totalDiscount));
        totAmountLbl.setText(String.format("%.2f", grandTotal));
        weightTxt.setText(String.format("%.2f", totalWeight));
        codTxt.setText(String.format("%.2f", grandTotal));
    }

    private void addUpdateTotals() {

        double totalAmount = 0.0;
        double totalWeight = 0.0;
        double totalDiscount = 0.0;

        int rowCount = itemListTable.getRowCount();

//        if (rowCount == 0) {
//            subTotAmountLbl.setText("0.00");
//            totAmountLbl.setText("0.00");
//            discountLabel.setText("0.00");
//            weightTxt.setText("0");
//            PaidAmountTxt.setText("0.00");
//            codTxt.setText("0.00");
//            return;
//        }
        hasRowLevelDiscount = false;

        for (int i = 0; i < rowCount; i++) {
            int itemId = ((Number) itemListTable.getValueAt(i, 0)).intValue();
            double price = ((Number) itemListTable.getValueAt(i, 2)).doubleValue();
            double qty = ((Number) itemListTable.getValueAt(i, 3)).doubleValue();
            double disc = ((Number) itemListTable.getValueAt(i, 4)).doubleValue(); // total discount for that row

            double weight = itemWeightList.get(itemIds.indexOf(itemId));

            totalAmount += price * qty;
            totalWeight += weight * qty;

            if (disc > 0) {
                hasRowLevelDiscount = true;
            }
            totalDiscount += disc;
        }

        // Only apply order-wide discount if no row-level discounts
        if (!isDoublePressedItemTable && !hasRowLevelDiscount) {
            System.out.println("Mekata enne nadda ethota");
            double orderDiscount = setDiscount(totalAmount);

            // Safety check to avoid over-discounting
            if (orderDiscount > totalAmount) {
                orderDiscount = totalAmount;
            }

            totalDiscount = orderDiscount;
        }

        // Calculate totals
        double deliveryFee = Double.parseDouble(deliveyFeeLbl.getText());
        double grandTotal = totalAmount + deliveryFee - totalDiscount;

        // Prevent negative grand total
        if (grandTotal < 0) {
            grandTotal = 0;
        }

        // Set values to labels
        subTotAmountLbl.setText(String.format("%.2f", totalAmount - totalDiscount));               // before discount
        discountLabel.setText(String.format("-%.2f", totalDiscount));             // for display
        totAmountLbl.setText(String.format("%.2f", grandTotal));
        weightTxt.setText(String.format("%.2f", totalWeight));

        if ("Card".equals(paymentTypeCombo.getSelectedItem())) {
            PaidAmountTxt.setText(String.format("%.2f", grandTotal));
            codTxt.setText("0.00");
        } else {
            codTxt.setText(String.format("%.2f", grandTotal));
            PaidAmountTxt.setText("0.00");
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
                if (LogInForm.userID == 1) {
                    int result = JOptionPane.showConfirmDialog(this, "The customer's latest order is still pending delivery.Do you want add another order");

                    if (result == JOptionPane.YES_OPTION) {
                        saveOrder();
                        return;
                    } else if (result == JOptionPane.NO_OPTION) {
                        delivery_id = null;
                        clearText();
                        DefaultTableModel model = (DefaultTableModel) itemListTable.getModel();
                        model.setRowCount(0);
                        return;
                    } else if (result == JOptionPane.CANCEL_OPTION) {
                        delivery_id = null;
                        clearText();
                        DefaultTableModel model = (DefaultTableModel) itemListTable.getModel();
                        model.setRowCount(0);
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "This customer already have order.", "Error", JOptionPane.ERROR_MESSAGE);
                    clearText();

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
                    return;
                }
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

            Number discountValue = (Number) itemListTable.getValueAt(i, 4);
            Double discount = discountValue.doubleValue();

            OrderDetailsDto dto = new OrderDetailsDto(0, null, itemId, null, null, 1, qty, price, discount, (price * qty) - discount, "", 1, 1);
            System.out.println("OrderDto : " + dto);
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
                deliveryOrderDto.setOrderType(cmbOrderType.getSelectedItem().toString());
                deliveryOrderDto.setRemark(remarkTxt.getText());
                deliveryOrderDto.setPaymentTypeId(paymentTypeIds.get(index));
                deliveryOrderDto.setOrderDetailsDtos(orderDetailsDtos);

                String status = deliveryOrdersTable.getValueAt(selectedRow, 9).toString();
                int status_id = statusTypeController.getStatusIdByStatusType(status);
                deliveryOrderDto.setStatus(status_id);

                if (isOrder) {
                    System.out.println("Come to this");
                    deliveryOrderDto.setDiscountId(discountController.getDiscountId(this.discount));
                }

                // Assuming there is a method in the repository to update the order
                boolean isUpdated = deliveryOrderRepositoryImpl.update(deliveryOrderDto, orderId, delivery_id, isOrder);
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
                    if (!cmbOrderType.isEnabled()) {
                        cmbOrderType.setEnabled(true);
                        cmbOrderType.setSelectedIndex(0);
                    }
                    sendOrderCode(delivery_id);
                    updateWebOrderStatus(deliveryOrderRepositoryImpl.getOrderByDeliveryId(delivery_id), "Pending");
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

    private void saveOrder() {
        ArrayList<OrderDetailsDto> orderDetailsDtos = new ArrayList<>();
        Integer orderId = null;
        if (!websiteOrders.isEmpty()) {
            int index = 0;
            for (WebsiteOrderDto websiteOrder : websiteOrders) {
                System.out.println("Index in the first loop : " + index);
                try {
                    for (int i = 0; i < websiteOrders.get(index).getItems().size(); i++) {
                        System.out.println("Index in the second loop : " + index);
                        try {
                            Integer item_id = getItemId(websiteOrders.get(index).getItems().get(i).getProduct_name());
                            System.out.println("Product name : " + websiteOrders.get(index).getItems().get(i).getProduct_name());
                            System.out.println("Item id : " + item_id);
                            orderDetailsDtos.add(new OrderDetailsDto(
                                    0,
                                    null,
                                    item_id,
                                    null,
                                    null,
                                    1,
                                    websiteOrders.get(index).getItems().get(i).getQuantity(),
                                    websiteOrders.get(index).getItems().get(i).getPrice(),
                                    0.0,
                                    websiteOrders.get(index).getItems().get(i).getQuantity() * websiteOrders.get(index).getItems().get(i).getPrice(),
                                    "",
                                    1,
                                    1
                            ));
                        } catch (Exception ex) {
                            Logger.getLogger(DeliveryOrders.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    index++;
                    DeliveryOrder deliveryOrderDto = new DeliveryOrder();
                    deliveryOrderDto.setCustomerName(websiteOrder.getFirst_name());
                    CustomerDto customer = customerController.searchCustomer(websiteOrder.getPhone_1());
                    Timestamp now = new Timestamp(System.currentTimeMillis());
                    LocalDate date = LocalDate.now();
                    java.sql.Date sqlDate = java.sql.Date.valueOf(date);
                    if (customer == null) {
                        try {
                            System.out.println("Come to this....");
                            customerController.saveCustomer(new CustomerDto(
                                    0,
                                    websiteOrder.getFirst_name(),
                                    "",
                                    websiteOrder.getAddress1() + ", " + websiteOrder.getAddress2() + " " + websiteOrder.getCity(),
                                    Integer.valueOf(websiteOrder.getPhone_1()),
                                    sqlDate,
                                    0,
                                    0.0,
                                    1,
                                    1,
                                    1
                            ));
                            customer = customerController.searchCustomer(websiteOrder.getPhone_1());
                        } catch (Exception ex) {
                            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    FileInputStream fis = new FileInputStream("config.txt");
                    Properties props = new Properties();
                    props.load(fis);
                    System.out.println("Customer : " + customer.getCustomerName());
                    deliveryOrderDto.setCustomerId(customer.getCustomerId());
                    deliveryOrderDto.setAddress(websiteOrder.getAddress1() + ", " + websiteOrder.getAddress2() + ", " + websiteOrder.getCity());
                    deliveryOrderDto.setCod(websiteOrder.getSub_total());
                    deliveryOrderDto.setPhoneOne(websiteOrder.getPhone_1());
                    deliveryOrderDto.setPhoneTwo(websiteOrder.getPhone_2());
                    deliveryOrderDto.setSubTotalPrice(websiteOrder.getTotal());
                    deliveryOrderDto.setDeliveryFee(Double.valueOf(props.getProperty("DELIVERY_FEE")));
                    deliveryOrderDto.setWeight("0.0");
                    deliveryOrderDto.setFreeShip(0);
                    deliveryOrderDto.setGrandTotalPrice(websiteOrder.getSub_total());
                    deliveryOrderDto.setCustomerNumber(customer.getCustomerNumber());
                    deliveryOrderDto.setPaidAmount(0.0);
                    deliveryOrderDto.setCreateDate(now);
                    deliveryOrderDto.setEditedDate(now);
                    deliveryOrderDto.setUserID(1);
                    deliveryOrderDto.setIsExchange(0);
                    deliveryOrderDto.setOrderType("Website");
                    deliveryOrderDto.setRemark("");
                    deliveryOrderDto.setPaymentTypeId(1);
                    deliveryOrderDto.setWebsiteOrderId(websiteOrder.getOrder_id());
                    deliveryOrderDto.setOrderDetailsDtos(orderDetailsDtos);
                    System.out.println("Save count" + index);
                    orderId = deliveryOrderRepositoryImpl.save(deliveryOrderDto, isOrder);
                } catch (Exception ex) {
                    Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
                }
                orderDetailsDtos.clear();
            }
            itemList.clear();
            System.out.println("order dto length : " + orderDetailsDtos.size());

        } else {

            for (int i = 0; i < itemListTable.getRowCount(); i++) {
                Integer itemId = (Integer) itemListTable.getValueAt(i, 0);

                Number priceValue = (Number) itemListTable.getValueAt(i, 2);

                Number discountValue = (Number) itemListTable.getValueAt(i, 4);
                Double applyDiscount;
                if (isOrder) {
                    applyDiscount = Double.valueOf(discountLabel.getText().replace("-", ""));
                } else {
                    applyDiscount = discountValue.doubleValue();
                }
                Double perItemPrice = priceValue.doubleValue();

                Double price = priceValue.doubleValue() - applyDiscount;

                Number qtyValue = (Number) itemListTable.getValueAt(i, 3);
                Integer qty = qtyValue.intValue();

                OrderDetailsDto dto = new OrderDetailsDto(0, null, itemId, null, null, 1, qty, perItemPrice, applyDiscount, price * qty, "", 1, 1);
                orderDetailsDtos.add(dto);
            }

            String phoneTwo = null;
            if (phoneTwoCmb.getSelectedItem() != null) {
                phoneTwo = phoneTwoCmb.getSelectedItem().toString();

            }

            int index = paymentTypeCombo.getSelectedIndex();

            Boolean free_shiping_check = fr_de_chb.getState();

            int free_ship = 0;
            if (free_shiping_check) {
                free_ship = 1;
            }

            int isExch = 0;
            Boolean isExchange = radioExchange.getState();
            if (isExchange) {
                isExch = 1;
            }

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
                    Timestamp now = new Timestamp(System.currentTimeMillis());
                    deliveryOrderDto.setCreateDate(now);
                    deliveryOrderDto.setEditedDate(now);
                    deliveryOrderDto.setUserID(1);
                    deliveryOrderDto.setIsExchange(isExch);
                    deliveryOrderDto.setOrderType(cmbOrderType.getSelectedItem().toString());
                    System.out.println("Order Type : " + cmbOrderType.getSelectedItem().toString());

                    deliveryOrderDto.setRemark(remarkTxt.getText());
                    deliveryOrderDto.setPaymentTypeId(paymentTypeIds.get(index));
                    deliveryOrderDto.setOrderDetailsDtos(orderDetailsDtos);
                    if (!isOrder) {
                        System.out.println("Come to this" + discount);
                        deliveryOrderDto.setDiscountId(1);
                    }
                    deliveryOrderDto.setDiscountId(1);
                    orderId = deliveryOrderRepositoryImpl.save(deliveryOrderDto, isOrder);

                    System.out.println("paymentTypeIds.get(index) : " + paymentTypeIds.get(index));
                } else {
                    JOptionPane.showMessageDialog(this, "Add Phone Number");

                }

            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class
                        .getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "save order error");
            }
        }
        if (orderId != null) {
            FileInputStream fis = null;
            try {
                JOptionPane.showMessageDialog(this, "Order saved sucessfully");
                //Bill print
//                printBill(orderId);
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String fromDate = formatter.format(jXDatePicker1.getDate());
                String toDate = formatter.format(jXDatePicker2.getDate());
                getAllOrders(fromDate, toDate, default_paymentType);
                getPhone_Number_One();
                getPhone_Number_Two();
                clearText();
                phoneOneCmb.requestFocus();
                customer_exist = false;
                fis = new FileInputStream("config.txt");
                Properties props = new Properties();
                props.load(fis);
                customerNameTxt.setText("");
                addressTxt.setText("");
                customerNumberTxt.setText("");
                remarkTxt.setText("");
                orderCodeTxt.setText("");
                Log.info(DeliveryOrders.class, orderId + " Order Save. User ID: " + LogInForm.userID);
                subTotAmountLbl.setText("0.00");
                totAmountLbl.setText("0.00");
                itemListTableModel.setRowCount(0);
                codTxt.setText("");
                PaidAmountTxt.setText("0");
                sendOrderConfirmation();
                Integer deliveryId = deliveryOrderRepositoryImpl.getLastDeliveryId();
                sendOrderCode(String.valueOf(deliveryId));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DeliveryOrders.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (IOException ex) {
                Logger.getLogger(DeliveryOrders.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fis.close();

                } catch (IOException ex) {
                    Logger.getLogger(DeliveryOrders.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Save fail..");
        }

        try {
            orders = mainOrderRepositoryImpl.getAllOrders();

        } catch (SQLException ex) {
            Logger.getLogger(DeliveryOrders.class
                    .getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "save order error");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeliveryOrders.class
                    .getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "save order error");
        }
        delivery_id = null;
    }

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        Log.info(DeliveryOrder.class, "Click Item remove button");

        int row = itemListTable.getSelectedRow();
        if (row != -1) {
            itemListTableModel.removeRow(row);
        }

        double totalAmount = 0.0;
        double totalWeight = 0.0;
        double totalDiscount = 0.0;

        int rowCount = itemListTable.getRowCount();

        if (rowCount > 0) {
            for (int i = 0; i < rowCount; i++) {
                try {
                    Integer itemId = (Integer) itemListTable.getValueAt(i, 0);
                    Number priceValue = (Number) itemListTable.getValueAt(i, 2);
                    Number qtyValue = (Number) itemListTable.getValueAt(i, 3);
                    Double discountValue = (Double) itemListTable.getValueAt(i, 4);

                    Log.info(DeliveryOrder.class, "Remove Item is " + itemId);

                    if (priceValue == null || qtyValue == null || discountValue == null || itemId == null) {
                        continue;
                    }

                    double price = priceValue.doubleValue();
                    double qty = qtyValue.doubleValue();
                    double discount = discountValue;

                    // Accumulate amount
                    totalAmount += price * qty;

                    // Weight
                    int weightIndex = itemIds.indexOf(itemId);
                    if (weightIndex != -1 && weightIndex < itemWeightList.size()) {
                        double weight = itemWeightList.get(weightIndex);
                        totalWeight += weight * qty;
                    }

                    // Apply row discount only in row-level discount mode
                    if (isDoublePressedItemTable) {
                        totalDiscount += discount;
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger
                            .getLogger(DeliveryOrders.class
                                    .getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Full-order discount (only if not in row-discount mode)
            if (!isDoublePressedItemTable) {
                totalDiscount = setDiscount(totalAmount);
                if (totalDiscount > totalAmount) {
                    totalDiscount = totalAmount; // safety
                }
            }

            // Recalculate delivery fee
            double deliveryFee = 0.0;
            if (!fr_de_chb.getState()) {
                deliveryFee = calculateDeliveryFee(totalWeight);
                deliveyFeeLbl.setText(String.format("%.2f", deliveryFee));
            }

            // Update labels
            subTotAmountLbl.setText(String.format("%.2f", totalAmount));
            discountLabel.setText(String.format("-%.2f", totalDiscount));
            weightTxt.setText(String.format("%.2f", totalWeight));

            double grandTotal = totalAmount + deliveryFee - totalDiscount;
            if (grandTotal < 0) {
                grandTotal = 0;
            }
            totAmountLbl.setText(String.format("%.2f", grandTotal));
            codTxt.setText(String.format("%.2f", grandTotal));

            System.out.println("Total Discount: " + totalDiscount);
            System.out.println("Total Weight: " + totalWeight);
            System.out.println("Total Amount: " + totalAmount);
            System.out.println("Delivery Fee: " + deliveryFee);
        } else if (isOrder) {
            // When table is empty but it's an order edit, clear discount
            double removedDiscount = setDiscount(0.0);
            discountLabel.setText(String.format("-%.2f", removedDiscount));
        } else if (isDoublePressedItemTable) {
            boolean freeSippingCheck = fr_de_chb.getState();
            if (freeSippingCheck) {
                System.out.println("mekata envada");
            }

        }
        if (itemListTable.getRowCount() == 0) {
            discountLabel.setText("0.00");
            subTotAmountLbl.setText("0.00");
            totAmountLbl.setText("0.00");
            weightTxt.setText("0.00");
            codTxt.setText("0.00");
        }


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
            Logger.getLogger(DeliveryOrders.class
                    .getName()).log(Level.SEVERE, "Config file not found", ex);
            Log.error(ex, "FileNotFoundException error");

        } catch (IOException ex) {
            Logger.getLogger(DeliveryOrders.class
                    .getName()).log(Level.SEVERE, "Error loading config file", ex);
            Log.error(ex, "IOException error");
        }
        return props;
    }
//    
    private void itemListTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_itemListTablePropertyChange

    }//GEN-LAST:event_itemListTablePropertyChange
    private void addTableChangeLisener() {
        DefaultTableModel model = (DefaultTableModel) itemListTable.getModel();
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                System.out.println("Table changed");
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    Double discount = (Double) itemListTable.getValueAt(row, column);
                    addUpdateTotals();
                    updateTotals();
                }
            }

        });
    }
    private void itemListTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemListTableKeyReleased

    }//GEN-LAST:event_itemListTableKeyReleased

    private void deliveryOrdersTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_deliveryOrdersTablePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_deliveryOrdersTablePropertyChange

    private void deliveryOrdersTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deliveryOrdersTableKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_deliveryOrdersTableKeyReleased

    private void printAllOrdersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printAllOrdersBtnActionPerformed
        Integer row = deliveryOrdersTable.getSelectedRow();
        Integer orderId = (Integer) deliveryOrdersTable.getValueAt(row, 0);
        String orderCode = (String) deliveryOrdersTable.getValueAt(row, 1);
        Double cod = (Double) deliveryOrdersTable.getValueAt(row, 5);
        Double totalPrice = (Double) deliveryOrdersTable.getValueAt(row, 6);
        Double paidAmount = totalPrice - cod;

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
            String path = "C:/Users/Sanjuka/Documents/Petal Pink/Payment recepts/" + orderCode + ".pdf";
            System.out.println(path);
            JasperExportManager.exportReportToPdfFile(jp, path);

        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DeliveryOrders.class
                    .getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "printAllOrdersBtnActionPerformed error");
        }
    }//GEN-LAST:event_printAllOrdersBtnActionPerformed

    private void saveOrderBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveOrderBtn1ActionPerformed
        Double amount = Double.parseDouble(PaidAmountTxt.getText());
        Double total = Double.parseDouble(totAmountLbl.getText());

        Double cod = total - amount;
        codTxt.setText(cod + "");


    }//GEN-LAST:event_saveOrderBtn1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
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

    }//GEN-LAST:event_jButton1ActionPerformed

    private void phoneTwoCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneTwoCmbActionPerformed
        if (phoneTwoCmb.getSelectedIndex() != -1) {
            try {
                String number = phoneTwoCmb.getSelectedItem() + "";

                if (number.startsWith("")) {
                    System.out.println("TRUE");
                } else {
                    List<CustomerDto> customerDtos = customerController.getCustomer("WHERE `phone_one`='" + number + "' OR `phone_two`='" + number + "'");
                    for (CustomerDto customerDto : customerDtos) {
                        phoneOneCmb.setSelectedItem("0" + customerDto.getPhoneOne());
                        phoneTwoCmb.setSelectedItem("0" + customerDto.getPhoneTwo());
                        customerNameTxt.setText(customerDto.getCustomerName());
                        addressTxt.setText(customerDto.getAddress());
                        customer_id = customerDto.getCustomerId();
                    }
                    customer_exist = true;

                }

            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class
                        .getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Phone number seleting error");
            }
        }
    }//GEN-LAST:event_phoneTwoCmbActionPerformed

    private void paymentTypeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentTypeComboActionPerformed
//        Double delivery_fee = 0.0;
//        Properties props = new Properties();
//
//        try {
//            FileInputStream fis = new FileInputStream("config.txt");
//            props.load(fis);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(DeliveryOrders.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        if (paymentTypeCombo.getSelectedItem().toString().equals("Card")) {
//            codTxt.setText(String.valueOf(Double.parseDouble(totAmountLbl.getText()) - Double.parseDouble(deliveyFeeLbl.getText())));
//            totAmountLbl.setText(String.valueOf(Double.parseDouble(totAmountLbl.getText()) - Double.parseDouble(deliveyFeeLbl.getText())));
//            deliveyFeeLbl.setText(props.getProperty("CARD_FEE"));
//
//        } else {
//
//            if (deliveyFeeLbl.getText().equals("0.00")) {
//                delivery_fee = Double.valueOf(props.getProperty("DELIVERY_FEE"));
//                codTxt.setText(String.valueOf(Double.valueOf(totAmountLbl.getText()) + delivery_fee));
//                totAmountLbl.setText(String.valueOf(Double.valueOf(totAmountLbl.getText()) + delivery_fee));
//                deliveyFeeLbl.setText(props.getProperty("DELIVERY_FEE"));
//            } else {
//                codTxt.setText(String.valueOf(Double.parseDouble(totAmountLbl.getText())));
//                totAmountLbl.setText(String.valueOf(Double.parseDouble(totAmountLbl.getText())));
//
//            }
//
//        }
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
        Log.info(DeliveryOrder.class, "Click phone1 numer combobox");
        if (phoneOneCmb.getSelectedIndex() != -1) {
            try {
                String number = phoneOneCmb.getSelectedItem() + "";

                Log.info(DeliveryOrder.class, number);
                Log.error(DeliveryOrder.class, "Click phone1 numer combobox");

                List<CustomerDto> customerDtos = customerController.getCustomer("WHERE `phone_one`='" + number + "' OR `phone_two`='" + number + "'");
                for (CustomerDto customerDto : customerDtos) {
                    phoneOneCmb.setSelectedItem("0" + customerDto.getPhoneOne());

                    if (customerDto.getPhoneTwo() == 0) {
                        phoneTwoCmb.setSelectedItem("");
                    } else {
                        phoneTwoCmb.setSelectedItem("0" + customerDto.getPhoneTwo());
                    }
                    customerNameTxt.setText(customerDto.getCustomerName());
                    addressTxt.setText(customerDto.getAddress());
                    customer_id = customerDto.getCustomerId();
                    customerNumberTxt.setText(customerDto.getCustomerNumber());
                }

                customer_exist = true;

            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class
                        .getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Phone number seleting error");
            }
        }
    }//GEN-LAST:event_phoneOneCmbActionPerformed

    private void phoneOneCmbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phoneOneCmbMouseClicked
        System.out.println("A");
    }//GEN-LAST:event_phoneOneCmbMouseClicked

    private void phoneOneCmbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneOneCmbKeyReleased
        Log.info(DeliveryOrder.class, "Click phone1 numer combobox");
        System.out.println("A");
    }//GEN-LAST:event_phoneOneCmbKeyReleased

    private void fr_de_chbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fr_de_chbMouseClicked

    }//GEN-LAST:event_fr_de_chbMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Log.info(DeliveryOrder.class, "Click check button");
        if (phoneOneCmb.getSelectedItem() != null) {
            try {
                setupTableModel(customer_id);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "This customer not have log!", "Error", JOptionPane.ERROR_MESSAGE);
                Log.error(e, phoneOneCmb.getSelectedItem() + " This customer not have log!");
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
        } else {
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
                System.out.println("> : " + orderDetails.getOrderCode().toString());
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
        String[] columnNames = {"Item Name", "Quantity", "Per Item Price", "Total Item Price", "Delivery Fee", "Total Order Price", "Status"};
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

    int selectedRow = -1;

    private void deliveryOrdersTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deliveryOrdersTableMousePressed
        Log.info(DeliveryOrder.class, "Click table row");
        if (evt.getClickCount() == 2) {
            selectedRow = deliveryOrdersTable.getSelectedRow();
            Log.info(DeliveryOrder.class, "Click table " + selectedRow + " row");
            if (selectedRow != -1) {
                String status = deliveryOrdersTable.getValueAt(selectedRow, 9).toString();

                PosMainUser userDto = null;
                try {
                    userDto = userAccountManagementController.getUserByUserID(LogInForm.userID);

                } catch (Exception ex) {
                    Logger.getLogger(DeliveryOrders.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

                if (!(userDto.getRoleId() == 1 || userDto.getRoleId() == 2)) {
                    // Disable all buttons by default
                    btnWrapping.setEnabled(false);
                    btnCancel.setEnabled(false);
                    btnActive.setEnabled(false);
                    btnOutForDelivery.setEnabled(false);
                    btnReturn.setEnabled(false);
                    btnDeliverd.setEnabled(false);
                    btnReturning.setEnabled(false);
                    btnChecking.setEnabled(false);
                    btnEdit.setEnabled(false);

                    // Enable buttons based on status
                    if (statusTypes.get(0).getStatus_type().equals(status) || statusTypes.get(1).getStatus_type().equals(status)) {
                        btnWrapping.setEnabled(true);
                        btnEdit.setEnabled(true);
                        btnCancel.setEnabled(true);
                        btnChecking.setEnabled(true);
                    } else if (statusTypes.get(2).getStatus_type().equals(status)) {
                        btnWrapping.setEnabled(false);
                        btnCancel.setEnabled(true);
                        btnEdit.setEnabled(true);
                        btnOutForDelivery.setEnabled(true);
                    } else if (statusTypes.get(3).getStatus_type().equals(status)) {
                        btnReturning.setEnabled(true);
                        btnDeliverd.setEnabled(true);
                        btnChecking.setEnabled(true);
                    } else if (statusTypes.get(7).getStatus_type().equals(status)) {
                        btnReturn.setEnabled(true);
                    } else if (statusTypes.get(8).getStatus_type().equals(status)) {
                        btnReturning.setEnabled(true);
                        btnDeliverd.setEnabled(true);
                    } else if (statusTypes.get(4).getStatus_type().equals(status)
                            || statusTypes.get(5).getStatus_type().equals(status)
                            || statusTypes.get(6).getStatus_type().equals(status)
                            || statusTypes.get(7).getStatus_type().equals(status)
                            || statusTypes.get(8).getStatus_type().equals(status)) {
                        // All buttons remain disabled
                    } else {
                        throw new AssertionError("Unknown status: " + status);
                    }

                    if (status.equals("Delivered")) {
                        JOptionPane.showMessageDialog(this, "This order has already been delivered and cannot be changed.");
                        return;
                    }
                }

                String deliveryID = deliveryOrdersTable.getValueAt(selectedRow, 0).toString();
                if (!deliveryID.isEmpty()) {
                    try {
                        delivery_id = deliveryID;
                        order_options.setLocationRelativeTo(null);
                        order_options.setSize(620, 160);
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
        Log.info(DeliveryOrder.class, "Click edit button");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        clearText();
        Log.info(DeliveryOrder.class, "Click clear button");
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
        if (delivery_id != null) {
            try {
                deliveryOrderRepositoryImpl.update(delivery_id, 1);

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
                Logger.getLogger(DeliveryOrders.class
                        .getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Status Change error");
            }
        }
        clearText();
        delivery_id = null;
    }//GEN-LAST:event_btnActiveActionPerformed

    private void btnWrappingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWrappingActionPerformed
        if (delivery_id != null) {
            try {
                deliveryOrderRepositoryImpl.update(delivery_id, 3);

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

                updateWebOrderStatus(deliveryOrderRepositoryImpl.getOrderByDeliveryId(delivery_id), "Preparing");

                order_options.dispose();

            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class
                        .getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Status Change error");
            }
        }
        clearText();
        delivery_id = null;
    }//GEN-LAST:event_btnWrappingActionPerformed

    private void btnOutForDeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOutForDeliveryActionPerformed
        if (delivery_id != null) {
            try {
                deliveryOrderRepositoryImpl.update(delivery_id, 4);

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
                updateWebOrderStatus(deliveryOrderRepositoryImpl.getOrderByDeliveryId(delivery_id), "On the way");
                order_options.dispose();

            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class
                        .getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Status Change error");
            }
        }
        clearText();
        delivery_id = null;
    }//GEN-LAST:event_btnOutForDeliveryActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        if (delivery_id != null) {
            try {
                deliveryOrderRepositoryImpl.update(delivery_id, 7);

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
                updateWebOrderStatus(deliveryOrderRepositoryImpl.getOrderByDeliveryId(delivery_id), "Cancel");
                order_options.dispose();

            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class
                        .getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Status Change error");
            }
        }
        clearText();
        delivery_id = null;
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        if (delivery_id != null) {
            try {
                deliveryOrderRepositoryImpl.update(delivery_id, 6);

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

                updateWebOrderStatus(deliveryOrderRepositoryImpl.getOrderByDeliveryId(delivery_id), "Return");
                order_options.dispose();

            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class
                        .getName()).log(Level.SEVERE, null, ex);
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

                Date now = new Date();
                deliveryOrderRepositoryImpl.addDeliveredDate(delivery_id, now);

                int index = 0;
                if (paymentTypeCombo2.getSelectedIndex() != 0) {
                    index = paymentTypeCombo2.getSelectedIndex();
                    getAllOrders(fromDate, toDate, paymentTypeIds_2.get(index - 1));
                } else {
                    getAllOrders(fromDate, toDate, 0);
                }
                String websiteOrderId = deliveryOrderRepositoryImpl.getOrderByDeliveryId(delivery_id);
                System.out.println("WebsiteOrderId : " + websiteOrderId);
                updateWebOrderStatus(websiteOrderId, "Delivered");

            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class
                        .getName()).log(Level.SEVERE, null, ex);
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
            System.out.println("DeliveryId : " + deliveryID);
            Object value = deliveryOrdersTable.getValueAt(selectedRow, 1);
            String orderCodeStr = (value != null) ? value.toString() : "";

            System.out.println("Order code : " + orderCodeStr);
            if (deliveryID != null && !deliveryID.isEmpty()) {
                orderCode = orderCodeStr;
                System.out.println("orderCode : " + orderCode);
                System.out.println("deliveryID : " + deliveryID);
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

                    // Additional logic for updating totals and other details...
                    double subTot = totalAmount - Double.parseDouble(deliveyFeeLbl.getText());

                    String delivery_id = deliveryOrdersTable.getValueAt(selectedRow, 0).toString();
                    String order_id = deliveryOrderController.getOrderId(delivery_id);
                    String orderID = deliveryOrderRepositoryImpl.getOrderIDByBillNo(order_id);

                    System.out.println("delivery_ID : " + delivery_id);
                    System.out.println("order_ID : " + orderID);

                    if (order_id != null) {
                        Integer oid = Integer.parseInt(order_id);
                        ArrayList<PosMainOrderDetails> orderDetails = mainOrderDetailRepositoryImpl.getOrderDetailsByOrderId(oid);
                        DefaultTableModel itemListTableModel = (DefaultTableModel) itemListTable.getModel();
                        itemListTableModel.setRowCount(0);
                        double totalWeight = 0.00;
                        double discountPrice = 0.00;
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
                                        p.getTotalDiscountPrice(),
                                        p.getTotalPrice()
                                    });
                                }
                            }
                        }

                        for (int i = 0; i < itemListTable.getRowCount(); i++) {
                            discountPrice += Double.parseDouble(itemListTable.getValueAt(i, 4).toString());
                        }
                        String orderType = deliveryOrderController.getOrderType(deliveryID);
                        if (orderType.equals("Website")) {
                            cmbOrderType.setSelectedIndex(1);
                        } else {
                            cmbOrderType.setSelectedIndex(0);
                        }
                        cmbOrderType.setEnabled(false);
                        PaidAmountTxt.setText(String.format("%.2f", totalAmount - cod));
                        codTxt.setText(String.format("%.2f", cod));
                        subTotAmountLbl.setText(String.format("%.2f", subTot));
                        double deliveryFee = calculateDeliveryFee(totalWeight);
                        deliveyFeeLbl.setText(String.format("%.2f", deliveryFee));
                        totAmountLbl.setText(String.format("%.2f", totalAmount));
                        Integer id = Integer.valueOf(deliveryOrdersTable.getValueAt(selectedRow, 0).toString());
                        Double weight = deliveryOrderController.getSpecificWaight(id);
                        discountLabel.setText(String.format("-%.2f", discountPrice));
                        weightTxt.setText(String.valueOf(weight));
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

//        updateTotals();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnCheckingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckingActionPerformed
        if (delivery_id != null) {
            try {
                deliveryOrderRepositoryImpl.update(delivery_id, 13);

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

                CustomerDataByInquirySearch customerDataByInquirySearch = inquiryRepositoryImpl.getCustomerDataByWayBill(deliveryOrdersTable.getValueAt(selectedRow, 1).toString());

                //Save as inquary
                InquiryModel inquiryModel = new InquiryModel();

                inquiryModel.setWayBill(deliveryOrdersTable.getValueAt(selectedRow, 1).toString());
                inquiryModel.setCustomerId(customerDataByInquirySearch.getCustomerId() + "");
                inquiryModel.setCustomerName(deliveryOrdersTable.getValueAt(selectedRow, 2).toString());
                inquiryModel.setCustomerPhone1(deliveryOrdersTable.getValueAt(selectedRow, 3).toString());
                inquiryModel.setCustomerPhone2(deliveryOrdersTable.getValueAt(selectedRow, 4).toString());
//            inquiryModel.setCompany(cmbCompany.getSelectedItem().toString());
//            inquiryModel.setBranch(cmbBranch.getSelectedItem().toString());
//            inquiryModel.setBranchContact(txtBranchContact.getText());
//            inquiryModel.setReason(cmbReason.getSelectedItem().toString());
//            inquiryModel.setRemark(txtRemark.getText());
                inquiryModel.setStatusId(11);

                inquiryRepositoryImpl.saveInquiry(inquiryModel);

            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class
                        .getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Status Change error");
            }
        }
        clearText();
        delivery_id = null;
    }//GEN-LAST:event_btnCheckingActionPerformed

    private void btnReturningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturningActionPerformed
        if (delivery_id != null) {
            try {
                deliveryOrderRepositoryImpl.update(delivery_id, 12);

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
                Logger.getLogger(DeliveryOrders.class
                        .getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, "Status Change error");
            }
        }
        clearText();
        delivery_id = null;
    }//GEN-LAST:event_btnReturningActionPerformed

    private void btnSpecialNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpecialNoteActionPerformed
        remark.setLocationRelativeTo(null);
        remark.setSize(590, 150);
        remark.setVisible(true);
        order_options.dispose();

        txtRemark.setText(deliveryOrderRepositoryImpl.getRemarkWithDeliveryId(delivery_id));
    }//GEN-LAST:event_btnSpecialNoteActionPerformed

    private void btnSaveRemarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveRemarkActionPerformed
        if (delivery_id != null) {
            try {
                deliveryOrderRepositoryImpl.updateOrderRemarkWithDeliveryId(delivery_id, txtRemark.getText());

                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String fromDate = formatter.format(jXDatePicker1.getDate());
                String toDate = formatter.format(jXDatePicker2.getDate());

                getAllOrders(fromDate, toDate, 0);

                remark.dispose();

            } catch (Exception ex) {
                Logger.getLogger(DeliveryOrders.class
                        .getName()).log(Level.SEVERE, null, ex);
                Log.error(ex, ex);
            }
        }
    }//GEN-LAST:event_btnSaveRemarkActionPerformed

    private void btnDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscountActionPerformed
        if (hasRowLevelDiscount) {
            int confimation = JOptionPane.showConfirmDialog(this, "You allready choose a discount option.Do you want reset?");
            switch (confimation) {
                case 0:
                    setDefault();
                    subTotAmountLbl.setText("0.00");
                    totAmountLbl.setText("0.00");
                    discountLabel.setText("0.00");
                    weightTxt.setText("0.00");
                    codTxt.setText("0.00");
                    discountLabel.setText("0.00");
                    hasRowLevelDiscount = false;
                    DefaultTableModel model = (DefaultTableModel) itemListTable.getModel();
                    model.setRowCount(0);
                    this.discount = 0;
                    break;
                default:
                    throw new AssertionError();
            }
        } else {
            isOrder = true;
            isDiscountForOrder.setSelected(true);
            isDiscountForOrder.setEnabled(false);
            isDoublePressedItemTable = false;
            openDIscountInfo();
        }

    }//GEN-LAST:event_btnDiscountActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        isDoublePressedItemTable = false;
        isOrder = false;
        setDefault();
        isDiscountForOrder.setSelected(false);
        isChooseComboDiscount = false;
        this.discountInfo.dispose();

    }//GEN-LAST:event_cancelBtnActionPerformed

    private void applyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyBtnActionPerformed

        try {
            // Edge case: if subtotal is 0.00
            if (subTotAmountLbl.getText().equals("0.00")) {
                if (isChooseComboDiscount) {
                    discount = Double.parseDouble(cmbDiscount.getSelectedItem().toString().replace("%", ""));
                } else {
                    discount = Double.parseDouble(txtCustomeDiscount.getText());
                }
            }

            // Item-level discount (when row in table is double-clicked)
            if (isDoublePressedItemTable) {
                String customDiscountText = txtCustomeDiscount.getText().trim();
                String customDiscountType = cmbSymbol.getSelectedItem() != null
                        ? cmbSymbol.getSelectedItem().toString().trim()
                        : "";
                String comboDiscount = cmbDiscount.getSelectedItem() != null
                        ? cmbDiscount.getSelectedItem().toString().trim()
                        : "";

                if (!"0.00".equals(customDiscountText) && !customDiscountText.isEmpty()) {
                    try {
                        discount = Double.parseDouble(customDiscountText);
                        if ("Rs".equals(customDiscountType)) {
                            isChooseComboDiscount = false;
                        } else if ("%".equals(customDiscountType)) {
                            isChooseComboDiscount = true;
                        } else {
                            Log.error(new Logger("Exception", "Invalid discount type selected") {
                            }, evt,
                                    new RuntimeException("Invalid discount type"));
                            return;
                        }
                    } catch (NumberFormatException e) {
                        Log.error(new Logger("Exception", "Invalid custom discount format") {
                        }, evt, e);
                        return;
                    }
                } else if (!"Choose a discount".equals(comboDiscount)) {
                    try {
                        if (comboDiscount.contains("%")) {
                            discount = Double.parseDouble(comboDiscount.replace("%", "").trim());
                            isChooseComboDiscount = true;
                        } else {
                            discount = Double.parseDouble(comboDiscount.trim());
                            isChooseComboDiscount = false;
                        }
                    } catch (NumberFormatException e) {
                        Log.error(new Logger("Exception", "Invalid combo discount format") {
                        }, evt, e);
                        return;
                    }
                } else {
                    Log.error(new Logger("Exception", "Please Choose an option before apply") {
                    }, evt,
                            new RuntimeException("Please Choose an option before apply"));
                    return;
                }

                // Apply to item
                Double discountAmount = setDiscount(clickedItemPrice);
                System.out.println("Item-level Discount applied: " + discountAmount);

                itemListTableModel.setValueAt(discountAmount, row, 4);
                Double newItemPrice = clickedItemPrice - discountAmount;
                if (newItemPrice < 0) {
                    newItemPrice = 0.0;
                }
                itemListTableModel.setValueAt(newItemPrice, row, 5);

                ((AbstractTableModel) itemListTable.getModel()).fireTableCellUpdated(row, 4);
                ((AbstractTableModel) itemListTable.getModel()).fireTableCellUpdated(row, 5);

                addUpdateTotals();
                discountInfo.dispose();
                discount = 0.0;
                return;
            }

            // ----- Order-Level Discount -----
            String customDiscountText = txtCustomeDiscount.getText().trim();
            String customDiscountType = cmbSymbol.getSelectedItem() != null
                    ? cmbSymbol.getSelectedItem().toString().trim()
                    : "";
            String comboDiscount = cmbDiscount.getSelectedItem() != null
                    ? cmbDiscount.getSelectedItem().toString().trim()
                    : "";

            if (!"0.00".equals(customDiscountText) && !customDiscountText.isEmpty()) {
                try {
                    discount = Double.parseDouble(customDiscountText);
                    if ("Rs".equals(customDiscountType)) {
                        isChooseComboDiscount = false;
                    } else if ("%".equals(customDiscountType)) {
                        isChooseComboDiscount = true;
                    } else {
                        Log.error(new Logger("Exception", "Invalid discount type selected") {
                        }, evt,
                                new RuntimeException("Invalid discount type"));
                        return;
                    }
                } catch (NumberFormatException e) {
                    Log.error(new Logger("Exception", "Invalid custom discount format") {
                    }, evt, e);
                    return;
                }
            } else if (!"Choose a discount".equals(comboDiscount)) {
                try {
                    if (comboDiscount.contains("%")) {
                        discount = Double.parseDouble(comboDiscount.replace("%", "").trim());
                        isChooseComboDiscount = true;
                    } else {
                        discount = Double.parseDouble(comboDiscount.trim());
                        isChooseComboDiscount = false;
                    }
                } catch (NumberFormatException e) {
                    Log.error(new Logger("Exception", "Invalid combo discount format") {
                    }, evt, e);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please choose a discount option");
                Log.error(new Logger("Exception", "Please Choose an option before apply") {
                }, evt,
                        new RuntimeException("Please Choose an option before apply"));
                return;
            }

            // ✅ FIX: Apply discount to the subtotal instead of clicked item
            Double subTotal = Double.parseDouble(subTotAmountLbl.getText());  // get total
            Double discountAmount = setDiscount(subTotal);  // apply discount on subtotal

            System.out.println("Order-level Discount applied: " + discountAmount);
            addUpdateTotals();
            discountInfo.dispose();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isDiscountForOrder.setSelected(true);
//            isChooseComboDiscount = false;
            comboSelected = false;
            setDefault();
        }
    }//GEN-LAST:event_applyBtnActionPerformed

    private void cmbDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDiscountActionPerformed
        // TODO add your handling code here:
        isChooseComboDiscount = true;

    }//GEN-LAST:event_cmbDiscountActionPerformed

    private void cmbSymbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSymbolActionPerformed
//        symbol = cmbSymbol.getSelectedItem().toString();
    }//GEN-LAST:event_cmbSymbolActionPerformed

    private void txtCustomeDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomeDiscountActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_txtCustomeDiscountActionPerformed

    private void qtyTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtyTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qtyTxtActionPerformed

    private void itemListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemListTableMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_itemListTableMouseClicked

    private void itemListTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemListTableMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            if (isOrder) {
                int confimation = JOptionPane.showConfirmDialog(this, "You allready choose a discount option.Do you want reset?");
                switch (confimation) {
                    case 0:
                        setDefault();
                        subTotAmountLbl.setText("0.00");
                        totAmountLbl.setText("0.00");
                        discountLabel.setText("0.00");
                        weightTxt.setText("0.00");
                        codTxt.setText("0.00");
                        discountLabel.setText("0.00");
                        DefaultTableModel model = (DefaultTableModel) itemListTable.getModel();
                        model.setRowCount(0);
                        isOrder = false;
                        isDoublePressedItemTable = false;
                        this.discount = 0;
                        break;
                    default:
                        throw new AssertionError();
                }
            } else {
                this.row = itemListTable.getSelectedRow();
                this.clickedItemPrice = Double.valueOf(itemListTable.getValueAt(row, 2).toString());
                isDoublePressedItemTable = true;
                isDiscountForOrder.setSelected(false);
                isDiscountForOrder.setEnabled(false);
                isOrder = false;
                openDIscountInfo();
            }
        }
    }//GEN-LAST:event_itemListTableMousePressed

    private void isDiscountForOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isDiscountForOrderActionPerformed
        // TODO add your handling code here:
        if (isDiscountForOrder.isSelected()) {
            isOrder = true;
        } else {
            isOrder = false;
        }
    }//GEN-LAST:event_isDiscountForOrderActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // TODO add your handling code here:
        setDefault();
        isChooseComboDiscount = false;
        discount = 0;
    }//GEN-LAST:event_clearBtnActionPerformed

    private void txtCustomeDiscountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCustomeDiscountMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            setDefault();
            isChooseComboDiscount = false;

        }
    }//GEN-LAST:event_txtCustomeDiscountMouseClicked

    private void cmbOrderTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbOrderTypeMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 0) {
            this.comboOrderType = true;
        }
    }//GEN-LAST:event_cmbOrderTypeMouseClicked

    private void cmbOrderTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOrderTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbOrderTypeActionPerformed

    private void cmbOrderTypeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbOrderTypeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbOrderTypeKeyReleased

    private void btnSyncWebsiteOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSyncWebsiteOrdersActionPerformed
        // TODO add your handling code here:
        getWebsiteOrders();
    }//GEN-LAST:event_btnSyncWebsiteOrdersActionPerformed

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
    private javax.swing.JButton applyBtn;
    private javax.swing.JButton btnActive;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChecking;
    private javax.swing.JButton btnDeliverd;
    private javax.swing.JButton btnDiscount;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnOutForDelivery;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnReturning;
    private javax.swing.JButton btnSaveRemark;
    private javax.swing.JButton btnSpecialNote;
    private javax.swing.JButton btnSyncWebsiteOrders;
    private javax.swing.JButton btnWrapping;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JDialog check_customer;
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> cmbDiscount;
    private javax.swing.JComboBox<String> cmbOrderType;
    private javax.swing.JComboBox<String> cmbSymbol;
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
    private javax.swing.JDialog discountInfo;
    private javax.swing.JLabel discountLabel;
    private javax.swing.Box.Filler filler1;
    private java.awt.Checkbox fr_de_chb;
    private javax.swing.JCheckBox isDiscountForOrder;
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
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JLabel lblDiscountSelected;
    private javax.swing.JLabel lblIsCustom;
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
    private javax.swing.JDialog remark;
    private javax.swing.JTextArea remarkTxt;
    private javax.swing.JButton removeBtn;
    private javax.swing.JLabel returnsTotTxt;
    private javax.swing.JButton saveOrderBtn;
    private javax.swing.JButton saveOrderBtn1;
    private javax.swing.JLabel subTotAmountLbl;
    private javax.swing.JLabel totAmountLbl;
    private javax.swing.JLabel total_orders_count_txt;
    private javax.swing.JTextField txtCustomeDiscount;
    private javax.swing.JTextField txtRemark;
    private javax.swing.JTextField weightTxt;
    // End of variables declaration//GEN-END:variables

    private void getItems() {
        try {
            String quary = "WHERE visible=1 AND status=1";
            ArrayList<MainItemDto> itemList = newItemController.getAllItems(quary);

            for (MainItemDto mainItem : itemList) {
                itemCombo.addItem(mainItem.getItemName());
                itemIds.add(mainItem.getItemId());
                itemPriceList.add(mainItem.getUnitPrice());
                itemWeightList.add(mainItem.getWeight());
            }
            System.out.println(itemWeightList);

        } catch (Exception ex) {
            Log.error(DeliveryOrder.class,
                    "", ex);
            Logger
                    .getLogger(DeliveryOrders.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getPaymentTypes() {
        try {
            String quary = "WHERE visible=1 AND status=1";
            ArrayList<PaymentTypeDto> paymentTypes = paymentTypesController.getPaymentTypes(quary);

            for (PaymentTypeDto typeDto : paymentTypes) {
                paymentTypeCombo.addItem(typeDto.getName());
                paymentTypeIds.add(typeDto.getPaymentTypeId());
                paymentTypeCombo2.addItem(typeDto.getName());
                paymentTypeIds_2.add(typeDto.getPaymentTypeId());

            }

        } catch (Exception ex) {
            Logger.getLogger(DeliveryOrders.class
                    .getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "getPaymentTypes error");
        }
    }

    private void getAllOrders(String fromDate, String toDate, Integer paymentType) {
        try {
            ArrayList<DeliveryOrder> deliveryOrderDtos = deliveryOrderRepositoryImpl.getAllDuration(fromDate, toDate, paymentType, 0, "Any");
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

                if (dto.getStatusID() == statusTypes.get(0).getStatus_id()) { //Active
                    status = statusTypes.get(0).getStatus_type();
                } else if (dto.getStatusID() == statusTypes.get(1).getStatus_id()) { //Pending
                    status = statusTypes.get(1).getStatus_type();
                } else if (dto.getStatusID() == statusTypes.get(2).getStatus_id()) { //Wrapping
                    status = statusTypes.get(2).getStatus_type();
                } else if (dto.getStatusID() == statusTypes.get(3).getStatus_id()) { //Out of Delivery
                    status = statusTypes.get(3).getStatus_type();
                } else if (dto.getStatusID() == statusTypes.get(4).getStatus_id()) { //Delivered
                    status = statusTypes.get(4).getStatus_type();
                } else if (dto.getStatusID() == statusTypes.get(5).getStatus_id()) { //Retured
                    status = statusTypes.get(5).getStatus_type();
                } else if (dto.getStatusID() == statusTypes.get(6).getStatus_id()) { //Cancel
                    status = statusTypes.get(6).getStatus_type();
                } else if (dto.getStatusID() == statusTypes.get(7).getStatus_id()) { //Returning
                    status = statusTypes.get(7).getStatus_type();
                } else if (dto.getStatusID() == statusTypes.get(8).getStatus_id()) { //Checking
                    status = statusTypes.get(8).getStatus_type();
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
                    dto.getOrderType(),
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
            deliveriesTotTxt.setText(totDeliveries + "");

        } catch (Exception ex) {
            Logger.getLogger(DeliveryOrders.class
                    .getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "getAllOrders error");
        }

        // Assuming status is at column index 7 in your table
        deliveryOrdersTable.getColumnModel().getColumn(9).setCellRenderer(new StatusCellRenderer());
    }

    private void printBill(Integer orderId) {
        try {
            HashMap<String, Object> hm = new HashMap<>();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            Double paidAmount = Double.parseDouble(weightTxt.getText());

            String orderCode = orderCodeTxt.getText();
            hm.put("DATE", formatter.format(date));
            hm.put("PAID_AMOUNT", paidAmount);
            hm.put("ORDER_ID", orderId);
            hm.put("WEIGHT", weightTxt.getText());

            JasperReport jr = JasperCompileManager.compileReport("D:/Unical/Unical-Pos-System/reports/Delivery_Receipt.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(jr, hm, DBCon.getDatabaseConnection());
//            JasperViewer view = new JasperViewer(jp, false);
//            view.setVisible(true);

            JasperExportManager.exportReportToPdfFile(jp, "C:/Users/Sanjuka/Documents/Petal Pink/Payment recepts/" + orderCode + ".pdf");

        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DeliveryOrders.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Vector<String> numbers = new Stack<>();

            List<CustomerDto> customerDtos = customerController.getCustomer("");

            for (CustomerDto customerDto : customerDtos) {
                numbers.add("0" + customerDto.getPhoneOne());
                numbers.add("0" + customerDto.getPhoneTwo());
                customersList.add(customerDto.getCustomerId());
            }

            AutoGenerator autoGenerator = new AutoGenerator();
            autoGenerator.completeText(numbers, phoneOneCmb);

        } catch (Exception ex) {
            Logger.getLogger(DeliveryOrders.class
                    .getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "getPhone_Number_One error");
        }
    }

    private void getPhone_Number_Two() {
        try {
            Vector<String> numbers = new Stack<>();

            List<CustomerDto> customerDtos = customerController.getCustomer("");

            for (CustomerDto customerDto : customerDtos) {
                numbers.add("0" + customerDto.getPhoneOne());
                numbers.add("0" + customerDto.getPhoneTwo());
            }

            AutoGenerator autoGenerator = new AutoGenerator();
            autoGenerator.completeText(numbers, phoneTwoCmb);

        } catch (Exception ex) {
            Logger.getLogger(DeliveryOrders.class
                    .getName()).log(Level.SEVERE, null, ex);
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
        discountLabel.setText(" ");
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
    private void setDiscountCmb() {
        ArrayList<DiscountDto> discounts = discountController.getAllDiscounts();
        for (DiscountDto discount : discounts) {
            cmbDiscount.addItem(String.valueOf(discount.getPercentage()) + "%");
        }
        String[] symbols = {"Rs", "%"};
        for (String symbol : symbols) {
            cmbSymbol.addItem(symbol);
        }

        ArrayList<OrderTypeDto> orderTypes = orderTypeController.getAllOrderType();
        for (OrderTypeDto orderType : orderTypes) {
            cmbOrderType.addItem(orderType.getType());
        }
    }

    private void openDIscountInfo() {
        discountInfo.setLocationRelativeTo(null);
        discountInfo.setMaximumSize(new Dimension(650, 160));
        discountInfo.setVisible(true);
        isChooseComboDiscount = false;
        txtCustomeDiscount.requestFocus();
    }

    private void setDefault() {
        txtCustomeDiscount.setText("0.00");
        cmbDiscount.setSelectedIndex(0);
        cmbSymbol.setSelectedIndex(0);

    }

    private void getWebsiteOrders() {
        try {
            HttpURLConnection connection = ApiClient.getURLConnection("/customerOrderSave/getNewOrderDetails");
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                String jsonResponse = response.toString();
                System.out.println("Json row: " + jsonResponse);

                Gson gson = new Gson();
                Type orderListType = new TypeToken<List<WebsiteOrderDto>>() {
                }.getType();
                websiteOrders = gson.fromJson(jsonResponse, orderListType);
                int index = 0;
                for (WebsiteOrderDto order : websiteOrders) {
                    for (WebsiteOrderDetailDto websiteOrderDetailDto : websiteOrders.get(index).getItems()) {
                        System.out.println("Product_list: " + websiteOrderDetailDto.getProduct_name());
                        itemList.add(websiteOrderDetailDto);
                    }
                    index++;
                    System.out.println("Customer Name: " + order.getItems());
                }
                System.out.println("retrieve successfully");
                saveOrder();
            }
        } catch (IOException ex) {
            Logger.getLogger(DeliveryOrders.class
                    .getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Please Check The Internet Connection!", "Network Error", 0);
        }
    }

    private Integer getItemId(String product_name) {
        return mainItemRepositoryImpl.getItemCode(product_name);
    }

    private void sendOrderConfirmation() {
        try {
            HttpURLConnection replyConnection = ApiClient.getURLConnection("/customerOrderSave/updateOrderTb");
            replyConnection.setRequestMethod("PUT");
            replyConnection.setDoOutput(true);
            replyConnection.setRequestProperty("Content-Type", "application/json");

            // Example payload — change based on backend requirements
            String payload = "{\"status\": \"saved\"}";
            try (OutputStream os = replyConnection.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = replyConnection.getResponseCode();
            System.out.println("Confirmation status code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Backend updated successfully");
            } else {
                System.out.println("Backend update failed");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openThread() {
        if (isOrderThreadRunning) {
            return;
        }
        isOrderThreadRunning = true;
        new Thread(() -> {
            getWebsiteOrders();
        }).start();
    }

    private void updateWebOrderStatus(String websiteOrderId, String status) {
        try {
            // Get connection from your API client
            HttpURLConnection uRLConnection = ApiClient.getURLConnection("/order/updateStatus");

            // Set request method and headers
            uRLConnection.setRequestMethod("PUT");
            uRLConnection.setRequestProperty("Content-Type", "application/json");
            uRLConnection.setDoOutput(true);

            // Build JSON body
            JSONObject json = new JSONObject();
            json.put("orderId", websiteOrderId);
            json.put("status", status);

            // Write JSON to output stream
            try (OutputStream os = uRLConnection.getOutputStream()) {
                byte[] input = json.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Check response code
            int responseCode = uRLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
                System.out.println("Status updated successfully");
            } else {
                System.out.println("Failed to update status. HTTP Code: " + responseCode);
            }

            // Close dialog
            order_options.dispose();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendOrderCode(String delivery_id) throws IOException {
        System.out.println("Call before delivery_id : " + delivery_id);
        DeliveryOrder order = deliveryOrderRepositoryImpl.getOrderCodeByDelioveryId(delivery_id);
        System.out.println("orderId : " + order.getWebsiteOrderId());
        HttpURLConnection uRLConnection = ApiClient.getURLConnection("/customerOrderSave/updateTrackingNumber/" + order.getWebsiteOrderId());
        uRLConnection.setRequestMethod("PUT");
        uRLConnection.setRequestProperty("Content-Type", "application/json");
        uRLConnection.setDoOutput(true);

        // Build JSON body
        JSONObject json = new JSONObject();
        json.put("orderId", order.getWebsiteOrderId());
        json.put("tracking_number", order.getOrderCode());

        // Write JSON to output stream
        try (OutputStream os = uRLConnection.getOutputStream()) {
            byte[] input = json.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Check response code
        int responseCode = uRLConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
            System.out.println("Tracking updated successfully");
        } else {
            System.out.println("Failed to update Tracking. HTTP Code: " + responseCode);
        }

    }

}
