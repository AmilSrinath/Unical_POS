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
            switch (value.toString()) {
                case "Not Done":
                    // Default look (no color set)
                    label.setBackground(Color.RED);
                    label.setForeground(Color.BLACK);
                    break;
                case "Done":
                    label.setBackground(Color.GREEN);
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
