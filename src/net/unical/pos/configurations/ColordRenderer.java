/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.configurations;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Sanjuka
 */
public class ColordRenderer{
    
    public static void main(String[] args) {
        ColorRenderer renderer = new ColorRenderer();
        renderer.setColorForCell(0, 0, Color.YELLOW);
        renderer.setColorForCell(1, 0, Color.CYAN);
        renderer.setColorForCell(2, 0, Color.GRAY);
        renderer.setColorForCell(3, 0, Color.BLUE);
        renderer.setColorForCell(4, 0, Color.GREEN);
        JTable table = new JTable(10, 5);
        // set my renderer for all cells. 
        table.setDefaultRenderer(Object.class, renderer); 
        // Probably in your code you need to set it for each column by using
        // table.getColumnModel().getColumn(columnIndex).setCellRenderer(renderer);
        JFrame frm = new JFrame("Color test");
        frm.add(new JScrollPane(table));
        frm.pack();
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }
    
    private static class ColorRenderer extends DefaultTableCellRenderer {
        private final Map<String, Color> colorMap = new HashMap<>();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            setBackground(null);
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            getColorForCell(row, column).ifPresent(this::setBackground);
            return this;
        }

        public void setColorForCell(int row, int col, Color color) {
            colorMap.put(row + ":" + col, color);
        }

        public Optional<Color> getColorForCell(int row, int col) {
            return Optional.ofNullable(colorMap.get(row + ":" + col));
        }
    }
}
