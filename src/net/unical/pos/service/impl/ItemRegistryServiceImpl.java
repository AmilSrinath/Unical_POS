/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.service.impl;

import java.sql.Connection;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.ItemRegistry;
import net.unical.pos.model.PosMainItem;
import net.unical.pos.repository.custom.ItemRegistryRepositoryCustom;
import net.unical.pos.service.custom.ItemRegistryServiceCustom;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class ItemRegistryServiceImpl implements ItemRegistryServiceCustom{
    private ItemRegistryRepositoryCustom itemRegistryRepositoryCustom = RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.ITEM_REGISTRY);

    @Override
    public boolean saveRegistry(PosMainItem item, Connection connection) {
       return itemRegistryRepositoryCustom.saveRegistry(item,connection);
    }

    @Override
    public ItemRegistry getLatestRegistry() {
        return itemRegistryRepositoryCustom.getLatestRegistry();
    }
   
    
}
