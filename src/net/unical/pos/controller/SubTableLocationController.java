/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.SubTableLocationDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.SubTableLocationServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class SubTableLocationController {
    
    private SubTableLocationServiceCustom locationServiceCustom;
    
    public SubTableLocationController() {
         locationServiceCustom= ServiceFactory.getInstance().
                getService(ServiceFactory.ServiceType.SUB_TABLE_LOCATION);
    }
    
    public boolean saveSubTableLocation(SubTableLocationDto subTableLocationDto) throws Exception {
        return locationServiceCustom.saveSubTableLocation(subTableLocationDto);
    }

    public boolean updateSubTableLocation(SubTableLocationDto subTableLocationDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<SubTableLocationDto> searchSubLocation(String mainTableName) throws Exception {
        return locationServiceCustom.getAllSubTableLocation();
    }
    
    public ArrayList<SubTableLocationDto> getAllSubLocations()throws Exception{
        return locationServiceCustom.getAllSubTableLocation();
    }
    
}
