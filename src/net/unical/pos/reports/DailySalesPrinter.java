/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.reports;

import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import net.unical.pos.configurations.ConfigPropertyReader;

/**
 *
 * @author Sanjuka
 */
public class DailySalesPrinter {
    
    private DefaultTableModel dataTableModel;
    private String repTitle;
    private String repDateTime;
    private String repNoOfOrders;
    
    public void doPrint(boolean isPrintDirect, String source) throws JRException {
        Date d = new Date();
        HashMap<String, Object> hm = new HashMap<String, Object>();
        hm.put("REP_TITLE", repTitle);
        hm.put("REP_DATE_TIME", repDateTime);
        hm.put("REP_NO_OF_ORDERS", repNoOfOrders);
        
        try {
            JasperReport jr = JasperCompileManager.compileReport(source);
            JasperPrint jp = JasperFillManager.fillReport(jr, hm, new JRTableModelDataSource(dataTableModel));
            if (isPrintDirect) {
                JasperViewer view = new JasperViewer(jp, false);
                view.setVisible(true);
            } else {
                JasperPrintManager.printReport(jp, false);
            }
        } catch (JRException ex) {
            Logger.getLogger(DailySalesPrinter.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public DefaultTableModel getDataTableModel() {
        return dataTableModel;
    }

    public void setDataTableModel(DefaultTableModel dataTableModel) {
        this.dataTableModel = dataTableModel;
    }

    public String getRepTitle() {
        return repTitle;
    }

    public void setRepTitle(String repTitle) {
        this.repTitle = repTitle;
    }

    public String getRepDateTime() {
        return repDateTime;
    }

    public void setRepDateTime(String repDateTime) {
        this.repDateTime = repDateTime;
    }

    public String getRepNoOfOrders() {
        return repNoOfOrders;
    }

    public void setRepNoOfOrders(String repNoOfOrders) {
        this.repNoOfOrders = repNoOfOrders;
    }
    
    
}
