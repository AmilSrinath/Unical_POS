/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.configurations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import net.unical.pos.view.employee.MessageDialog;

/**
 *
 * @author Sanjuka
 */
public class ConfigTimer {
    
    public String message;
    public void setMessageTimer(boolean result){
        
        MessageDialog messageDialog = new MessageDialog(new javax.swing.JFrame(), true, message);
        
            message=Configurations.SUCESS_DATA_ADDED_TO_DB.getConfigValue();
            if(result){
                messageDialog.setLocation(1000,100);
                messageDialog.setVisible(true);
                Timer timer=new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        messageDialog.dispose();
                    }
                });
                timer.start();
                
            }else{
                System.out.println("=========ssss");
            }
        
    }
}
