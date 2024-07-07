/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.ItemUnitTypeDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.UnitTypesServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class UnitTypesController {

    private UnitTypesServiceCustom unitTypesServiceCustom;
    
    public UnitTypesController() {
        unitTypesServiceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.UNIT_TYPE);
    }
    
    public boolean saveUnitType(ItemUnitTypeDto itemUnitTypeDto)throws Exception{
        return unitTypesServiceCustom.saveUnitType(itemUnitTypeDto);
    }

    public ArrayList<ItemUnitTypeDto> getUnitTypes(String quary) throws Exception{
        return unitTypesServiceCustom.getUnitTypes(quary);
    }
    
}
