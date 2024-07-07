/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.configurations;

import java.util.Map;
import net.unical.pos.dto.MainItemDto;

/**
 *
 * @author Sanjuka
 */
public class POSConfigurationsobjects {
    
    private Map<Integer, MainItemDto> mainItemListObjMap;
    
    public Map<Integer, MainItemDto> getMainItemListObjMap() {
        return mainItemListObjMap;
    }
}
