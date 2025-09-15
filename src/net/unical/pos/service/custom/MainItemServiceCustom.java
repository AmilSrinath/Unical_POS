/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.MainItemDto;

/**
 *
 * @author HP
 */
public interface MainItemServiceCustom {

    public boolean saveItem(MainItemDto itemDto) throws Exception;

    public boolean updateItem(MainItemDto itemDto) throws Exception;

    public ArrayList<MainItemDto> getAllItem(String quary) throws Exception;

    public MainItemDto searchItem(String key) throws Exception;

    public ArrayList<MainItemDto> getItem(String key) throws Exception;
    
    public ArrayList<MainItemDto> searchAllSubItems(Integer key) throws Exception;
    
    public ArrayList<MainItemDto> searchAllItems(Integer key) throws Exception;

    public ArrayList<MainItemDto> searchAllItems(Integer main, Integer sub)throws Exception;

    public ArrayList<MainItemDto> searchAllItems(String key)throws Exception;

    public String getmainItemid(String mainCat);

    public boolean isNewItem(String itemName);

    public Integer getRegistryId(Integer itemId);
}
