/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.configurations;

/**
 *
 * @author Sanjuka
 */
public enum Configurations {
    
    SE_IMAGE_PATH("SE_IMAGE_PATH"),
    
    SUCESS_DATA_ADDED_TO_DB("Value Saved Sucessfully"),
    SUCESS_DATA_UPDATE_TO_DB("Value Update Sucessfully"),
    
    SYS_EXIT_BUTTON_CONFIRMATION("Do you need to exit?"),
    SYS_EXIT_BUTTON_CONFIRMATION_TITLE("Are you sure?");
    
    private final String configValue;

    private Configurations(String s) {
        configValue = s;
    }

    public String getConfigValue() {
        return configValue;
    }
}
