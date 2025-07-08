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
        JLabel label = new JLabel(value.toString());
        label.setOpaque(true);
        label.setHorizontalAlignment(LEFT);
        
        if (value != null) {
            String status = value.toString();

            String deliveredStatus = ViewInquiry.statusTypes.get(0).getStatus_type();
            String notDeliveredStatus = ViewInquiry.statusTypes.get(1).getStatus_type();

            if (status.equals(deliveredStatus)) {
                label.setBackground(new Color(198, 239, 206)); // green
                label.setForeground(new Color(0, 97, 0));       // dark green
            } else if (status.equals(notDeliveredStatus)) {
                label.setBackground(new Color(255, 204, 204)); // light red
                label.setForeground(new Color(178, 34, 34));   // dark red
            }
        }

        if (isSelected) {
            label.setBackground(table.getSelectionBackground());
            label.setForeground(table.getSelectionForeground());
        }

        return label;
    }
}
