/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.view.deliveryOrders;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import net.unical.pos.model.StatusTypeModel;
import static net.unical.pos.view.deliveryOrders.DeliveryOrders.statusTypes;

/**
 *
 * @author Amil Srinath
 */
public class StatusCellRenderer extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel(value.toString());
        label.setOpaque(true);
        label.setHorizontalAlignment(LEFT); // Optional: center-align

        if (value != null) {
            String status = value.toString();

            String activetatus = DeliveryOrders.statusTypes.get(0).getStatus_type();
            String defaultStatus = DeliveryOrders.statusTypes.get(1).getStatus_type();
            String wrappingStatus = DeliveryOrders.statusTypes.get(2).getStatus_type();
            String outForDeliveryStatus = DeliveryOrders.statusTypes.get(3).getStatus_type();
            String deliveredStatus = DeliveryOrders.statusTypes.get(4).getStatus_type();
            String returnStatus = DeliveryOrders.statusTypes.get(5).getStatus_type();
            String cancelStatus = DeliveryOrders.statusTypes.get(6).getStatus_type();
            String returningStatus = DeliveryOrders.statusTypes.get(7).getStatus_type();
            String checkingStatus = DeliveryOrders.statusTypes.get(8).getStatus_type();

            if (status.equals(defaultStatus)) {
                label.setBackground(Color.WHITE);
                label.setForeground(Color.BLACK);
            } else if (status.equals(activetatus)) {
                label.setBackground(new Color(145, 200, 228)); // blue
                label.setForeground(new Color(92, 136, 196));   // dark blue
            } else if (status.equals(wrappingStatus)) {
                label.setBackground(new Color(255, 255, 102)); // yellow
                label.setForeground(new Color(128, 128, 0));   // dark yellow
            } else if (status.equals(outForDeliveryStatus)) {
                label.setBackground(new Color(230, 204, 255)); // light purple
                label.setForeground(new Color(102, 0, 153));   // purple
            } else if (status.equals(deliveredStatus)) {
                label.setBackground(new Color(198, 239, 206)); // green
                label.setForeground(new Color(0, 97, 0));       // dark green
            } else if (status.equals(returnStatus)) {
                label.setBackground(new Color(255, 178, 102)); // orange
                label.setForeground(new Color(207, 83, 0));    // darker orange
            } else if (status.equals(cancelStatus)) {
                label.setBackground(new Color(255, 204, 204)); // light red
                label.setForeground(new Color(178, 34, 34));   // dark red
            } else if (status.equals(returningStatus)) {
                label.setBackground(new Color(255, 180, 180)); // light orange
                label.setForeground(new Color(255, 68, 52));   // dark orange
            }else if (status.equals(checkingStatus)) {
                label.setBackground(new Color(152, 161, 188)); // light gray
                label.setForeground(new Color(85, 88, 121));   // dark gray
            }else {
                label.setBackground(Color.WHITE);
                label.setForeground(Color.BLACK);
            }
        }



        if (isSelected) {
            label.setBackground(table.getSelectionBackground());
            label.setForeground(table.getSelectionForeground());
        }

        return label;
    }
}
