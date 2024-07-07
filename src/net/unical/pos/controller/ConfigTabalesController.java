/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.ConfigTableDetailsDto;
import net.unical.pos.dto.ConfigTablesDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.ConfigTableServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class ConfigTabalesController {

    private ConfigTableServiceCustom tableServiceCustom;
    
    public ConfigTabalesController() {
         tableServiceCustom= ServiceFactory.getInstance().
                getService(ServiceFactory.ServiceType.CONFIG_TABLES);
    }
    
    public boolean saveTables(ConfigTablesDto configTablesDto) throws Exception {
        return tableServiceCustom.saveconfigTables(configTablesDto);
    }

    public boolean updateTables(ConfigTablesDto configTablesDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<ConfigTablesDto> getAllLocations()throws Exception{
        return tableServiceCustom.getAllconfigTables();
    }
    
    public ArrayList<ConfigTableDetailsDto> getAllLocationDetails()throws Exception{
        return tableServiceCustom.getAllconfigTableDetails();
    }
    
}
