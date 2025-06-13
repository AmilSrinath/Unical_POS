/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.view.deliveryOrders;

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
    public Component getTableCellRendererComponent(JTable table, Object value,
           boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel label = new JLabel(value.toString());
        label.setOpaque(true);
        label.setHorizontalAlignment(LEFT); // Optional: center-align

        if (value != null) {
            switch (value.toString()) {
                case "Pending":
                    // Default look (no color set)
                    label.setBackground(Color.WHITE);
                    label.setForeground(Color.BLACK);
                    break;
                case "Cancel":
                    label.setBackground(new Color(255, 204, 204)); // light red
                    label.setForeground(new Color(178, 34, 34));   // dark red
                    break;
                case "Wrapping":
                    label.setBackground(new Color(255, 255, 102)); // yellow
                    label.setForeground(new Color(128, 128, 0));   // dark yellow
                    break;
                case "Delivered":
                    label.setBackground(new Color(198, 239, 206)); // green
                    label.setForeground(new Color(0, 97, 0));       // dark green
                    break;
                case "Out of Delivery":
                    label.setBackground(new Color(230, 204, 255)); // light purple
                    label.setForeground(new Color(102, 0, 153));   // purple
                    break;
                case "Return":
                    label.setBackground(new Color(255, 178, 102)); // orange
                    label.setForeground(new Color(207, 83, 0));    // darker orange
                    break;
                default:
                    label.setBackground(Color.WHITE);
                    label.setForeground(Color.BLACK);
                    break;
            }
        }

        if (isSelected) {
            label.setBackground(table.getSelectionBackground());
            label.setForeground(table.getSelectionForeground());
        }

        return label;
    }
}
