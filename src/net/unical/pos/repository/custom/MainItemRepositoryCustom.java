/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.sql.Connection;
import java.util.ArrayList;
import net.unical.pos.model.PosMainItem;

/**
 *
 * @author HP
 */
public interface MainItemRepositoryCustom {

    public boolean save(PosMainItem item, Connection connection)throws Exception;
    
    public ArrayList<PosMainItem> searchAllSubItems(Integer key)throws Exception;
    
    public ArrayList<PosMainItem> searchAllItems(Integer key)throws Exception;

    public ArrayList<PosMainItem> searchAllItems(Integer main, Integer sub)throws Exception;

    public ArrayList<PosMainItem> searchAllItems(String key)throws Exception;

    public ArrayList<PosMainItem> getAllItems(String quary)throws Exception;

    public String getItemId(String mainCat);

    public boolean isNewItem(String itemName);

    public Integer getRegistryId(Integer itemId);

    public boolean updateItem(PosMainItem item) throws Exception;
    
}
