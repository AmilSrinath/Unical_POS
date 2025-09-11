/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.repository.custom;

import java.sql.Connection;
import net.unical.pos.model.ItemRegistry;
import net.unical.pos.model.PosMainItem;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public interface ItemRegistryRepositoryCustom {

    public boolean saveRegistry(PosMainItem item, Connection connection);

    public ItemRegistry getLatestRegistry();

    
}
