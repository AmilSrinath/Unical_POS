/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.view.Inquiry;

import net.unical.pos.view.Payment.*;
import net.unical.pos.view.deliveryOrders.*;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Amil Srinath
 */
public class StatusCellRenderer extends DefaultTableCellRenderer{
    @Override
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    JLabel label = new JLabel(value != null ? value.toString() : ""); // prevent NPE
    label.setOpaque(true);
    label.setHorizontalAlignment(LEFT);
    
    if (value != null) {
        String status = value.toString();
        System.out.println("Value " + value);
        String deliveredStatus = ViewInquiry.statusTypes.get(0).getStatus_type();
        String notDeliveredStatus = ViewInquiry.statusTypes.get(1).getStatus_type();
        String returnedDeliveryStatus = ViewInquiry.statusTypes.get(2).getStatus_type();
        String cancelStatus = ViewInquiry.statusTypes.get(3).getStatus_type();
        
        if (status.equals(deliveredStatus)) {
            label.setBackground(new Color(198, 239, 206));
            label.setForeground(new Color(0, 97, 0));
        } else if (status.equals(notDeliveredStatus)) {
            label.setBackground(new Color(255, 204, 204));
            label.setForeground(new Color(178, 34, 34));
        } else if (status.equals(returnedDeliveryStatus)){
            label.setBackground(new Color(255, 229, 180)); 
            label.setForeground(new Color(178, 34, 34));
        } else if (status.equals(cancelStatus)){
            label.setBackground(new Color(255, 204, 0)); 
            label.setForeground(new Color(178, 34, 34));
        }
    }

    if (isSelected) {
        label.setBackground(table.getSelectionBackground());
        label.setForeground(table.getSelectionForeground());
    }

    return label;
}
}
