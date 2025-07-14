/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.view.deliveryOrders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 *
 * @author Amil Srinath
 */
public class PromptTextField extends JTextField implements FocusListener{
    private String prompt;
    private boolean showingPrompt;

    public PromptTextField(String prompt) {
        this.prompt = prompt;
        this.showingPrompt = true;
        addFocusListener(this);
        showPrompt();
    }

    private void showPrompt() {
        if (getText().isEmpty()) {
            setText(prompt);
            setForeground(Color.GRAY);
            showingPrompt = true;
        }
    }

    private void hidePrompt() {
        if (showingPrompt) {
            setText("");
            setForeground(Color.BLACK);
            showingPrompt = false;
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        hidePrompt();
    }

    @Override
    public void focusLost(FocusEvent e) {
        showPrompt();
    }

    @Override
    public String getText() {
        return showingPrompt ? "" : super.getText();
    }
}
