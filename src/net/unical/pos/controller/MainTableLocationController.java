/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.MainTableLocationDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.MainTableLocationServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class MainTableLocationController {
    
    private MainTableLocationServiceCustom mainTableLocationCustom;
    
    public MainTableLocationController() {
         mainTableLocationCustom= ServiceFactory.getInstance().
                getService(ServiceFactory.ServiceType.MAIN_TABLE_LOCATION);
    }

    public boolean saveMainTableLocation(MainTableLocationDto mainTableLocationDto) throws Exception {
        return mainTableLocationCustom.saveMainTableLocation(mainTableLocationDto);
    }

//    public boolean updateMainTableLocation(MainTableLocationDto mainTableLocationDto) throws Exception {
//        return mainTableLocationCustom.updateMainTableLocation(mainTableLocationDto);
//    }
    
    public ArrayList<MainTableLocationDto> getAllLocations()throws Exception{
        return mainTableLocationCustom.getAllMainTableLocation();
    }
    
}
