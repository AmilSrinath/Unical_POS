/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.configurations;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Sanjuka
 */
public class AutoGenerator {
    
    private static AutoGenerator instance = null;
    private JTextField textField;
    private boolean hideFlag = false;
    
    public static AutoGenerator getInstance() {
        if (instance == null) {
            instance = new AutoGenerator();
        }
        return instance;
    }
    
    public void completeText(final Vector dataVector, final JComboBox jComboBox) {
        this.textField = ((JTextField) jComboBox.getEditor().getEditorComponent());
        this.textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = AutoGenerator.this.textField.getText();

                        if (text != null) {
                            if (text.trim().length() == 0) {
                                jComboBox.hidePopup();
                                AutoGenerator.this.setModel(new DefaultComboBoxModel(dataVector), "", jComboBox);
                                
                            } else {
                                
                                DefaultComboBoxModel m = AutoGenerator.getSuggestedModel(dataVector, text);
                                if ((m.getSize() == 0) || (AutoGenerator.this.hideFlag)) {
                                    jComboBox.hidePopup();
                                    AutoGenerator.this.setModel(m, text, jComboBox);
                                    AutoGenerator.this.hideFlag = false;
                                } else {
                                    AutoGenerator.this.setModel(m, text, jComboBox);
                                    jComboBox.showPopup();
                                }
                            }
                        }
                    }
                });
//                }

            }

            @Override
            public void keyPressed(KeyEvent e) {
                    String text = AutoGenerator.this.textField.getText();
                    int code = e.getKeyCode();
                    if ((code == 10) && (text != null) && (!text.trim().equals(""))) {
                        if (!dataVector.contains(text)) {
                            AutoGenerator.this.setModel(AutoGenerator.getSuggestedModel(dataVector, text), text, jComboBox);
                        }
                        AutoGenerator.this.hideFlag = true;
                    } else if (code == 27) {
                        AutoGenerator.this.hideFlag = true;
                    } else if (code == 39) {
                        for (int i = 0; i < dataVector.size(); i++) {
                            String str = dataVector.elementAt(i).toString();
                            if (str.startsWith(text)) {
                                jComboBox.setSelectedIndex(-1);
                                AutoGenerator.this.textField.setText(str);
                                return;
                            }
                        }
                    }
            }
        });
        setModel(new DefaultComboBoxModel(dataVector), "", jComboBox);
    }
    
    public void setModel(DefaultComboBoxModel mdl, String str, JComboBox comboBox) {
        comboBox.setModel(mdl);
        comboBox.setSelectedIndex(-1);
        
        this.textField.setText(str);
    }
    
    private static DefaultComboBoxModel getSuggestedModel(List list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for (int index = 0; index < list.size(); index++) {
//            System.out.println(list.get(index).toString().toUpperCase()+" ----------");
            if (list.get(index).toString().toUpperCase().contains(text.toUpperCase())) {
                m.addElement(list.get(index));
            }
        }
        return m;
    }
}
