/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.MainItemDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.MainItemServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class MainItemController {
    
    private MainItemServiceCustom mainItemServiceCustom;

    public MainItemController() {
        mainItemServiceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.MAIN_ITEM);
    }
    
    
    public boolean saveItem(MainItemDto mainItemDto) throws Exception{
        return mainItemServiceCustom.saveItem(mainItemDto);
    }
    
    public boolean updateItem(MainItemDto itemDto) throws Exception{
//        return .saveDoctor(mainItemCategoryDto);

    return true;
    }
    
    public ArrayList<MainItemDto> getAllItems(String quary)throws Exception{
        return mainItemServiceCustom.getAllItem(quary);
    }
    
    public ArrayList<MainItemDto> searchAllSubItems(Integer key)throws Exception{
        return mainItemServiceCustom.searchAllSubItems(key);
    }

    public ArrayList<MainItemDto> searchAllItems(Integer key) throws Exception{
        return mainItemServiceCustom.searchAllItems(key);
    }
    
    public ArrayList<MainItemDto> searchAllItems(String key) throws Exception{
        return mainItemServiceCustom.searchAllItems(key);
    }
    
    public ArrayList<MainItemDto> searchAllItems(Integer main,Integer sub) throws Exception{
        return mainItemServiceCustom.searchAllItems(main, sub);
    }
}
